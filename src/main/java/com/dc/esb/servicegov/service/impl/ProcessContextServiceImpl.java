package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.ProcessContextDAOImpl;
import com.dc.esb.servicegov.dao.impl.TaskOpinionServiceImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.ProcessContext;
import com.dc.esb.servicegov.entity.TaskOpinion;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.impl.GetAllFlowServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.jbpm.task.query.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by vincentfxz on 15/9/14.
 */
@Service
@Transactional
public class ProcessContextServiceImpl extends AbstractBaseService<ProcessContext, String> {

    @Autowired
    private ProcessContextDAOImpl processContextDAO;
    @Autowired
    private GetAllFlowServiceImpl getAllFlowService;
    @Autowired
    private TaskOpinionServiceImpl taskOpinionService;

    private final static String METADATA_CREAT = "创建元数据";
    private final static String METADATA_CHECK = "元数据审核";
    private final static String INTERFACE_DEMAND = "接口需求文件上传";
    private final static String INTERFACE_DEF = "接口定义";
    private final static String SERVICE_DEF = "服务定义";
    private final static String SERVICE_CHECK = "服务审核";
    private final static String SERVICE_RELEASE = "服务发布";
    private final static String SERVICE_DEV = "服务开发";
    private final static String SERVICE_TEST = "服务测试";
    private final static String SERVICE_ONLINE = "服务上线";
    private final static String PUBCODE_CREAT = "创建公共代码";
    private final static String PUBCODE_CHECK = "公共代码审核";

    @Override
    public HibernateDAO<ProcessContext, String> getDAO() {
        return processContextDAO;
    }

    /**
     * 根据流程号和节点名称获得所有意见
     * @param nodeName
     * @param flowID
     * @return
     */
    public String getAllOpinion(String nodeName,String flowID){
        String flag = "0";
        List<TaskOpinion> taskOpinions = taskOpinionService.getOpinionByProId(flowID);
        if(nodeName.endsWith(METADATA_CREAT)||nodeName.endsWith(INTERFACE_DEMAND)||nodeName.endsWith(PUBCODE_CREAT)) {
            flag = "1";
        }else if(nodeName.endsWith(METADATA_CHECK)||nodeName.endsWith(INTERFACE_DEF)||nodeName.endsWith(PUBCODE_CHECK)){
            flag = "2";
        }else if(nodeName.endsWith(SERVICE_DEF)){
            flag = "3";
        }else if(nodeName.endsWith(SERVICE_CHECK)){
            flag = "4";
        }else if(nodeName.endsWith(SERVICE_RELEASE)){
            flag = "5";
        }else if(nodeName.endsWith(SERVICE_DEV)){
            flag = "6";
        }else if(nodeName.endsWith(SERVICE_TEST)){
            flag = "7";
        }else if(nodeName.endsWith(SERVICE_ONLINE)){
            flag = "8";
        }
        if(null == taskOpinions){
            taskOpinions = new ArrayList<TaskOpinion>();
        }
        for(TaskOpinion opinion:taskOpinions){
            if(opinion.getTaskStatus().endsWith(flag)){
                String opinion2 =  opinion.getOpinion();
                String rollbackOpi = opinion.getRollbackOpinion();
                if(null == opinion2){
                    opinion2 = "";
                }
                if(null == rollbackOpi){
                    rollbackOpi = "";
                }
                return opinion2+rollbackOpi;
            }
        }
        return null;
    }
    /**
     * 删除
     * @param processInstanceId
     */
    public void obsolete(Long processInstanceId){
        String sql = "update task set status='Obsolete' where processInstanceId=" + processInstanceId;
        Session session = getDAO().getSession();
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 得到当前流程所有节点的操作人员
     * @param processInstanceId
     * @return
     */
    public List<String> getPioneerTaskUser(Long processInstanceId){
        Session sesssion=getDAO().getSession();
        Query query=sesssion.createSQLQuery("SELECT ACTUALOWNER_ID FROM TASK WHERE ID =(SELECT MIN(ID) FROM TASK WHERE PROCESSINSTANCEID = " + processInstanceId + ")");
        return query.list();
    }

    /**
     * 回退
     * @param processInstanceId
     * @param taskId
     */
    public void rollback(Long processInstanceId,Long taskId){
        String sql = "update task set status='Exited' where id="+taskId;
        String sql2 = "update task set status='InProgress',PREVIOUSSTATUS=2 where id="+"(select max(id) from task where processInstanceId="+processInstanceId+"and status='Completed')";
        Session sesssion=getDAO().getSession();
        Query query=sesssion.createSQLQuery(sql);
        Query query2=sesssion.createSQLQuery(sql2);
        query.executeUpdate();
        query2.executeUpdate();
    }

    /**
     * 检查回退
     * @param taskId
     */
    public void checkRollback(Long taskId){
        String sql = "update task set status='InProgress',previousStatus=2 where id=(select min(id) from task where status='Exited'and processInstanceId=(select processInstanceId from task where id="+taskId+"))";
        Session session = getDAO().getSession();
        Query query=session.createSQLQuery(sql);
        query.executeUpdate();
    }

    /**
     * 完成任务意见加入
     * @param taskId
     * @param opinion
     */
    public void completeOpinion(Long taskId,String opinion){
        TaskBean task = getTaskById(taskId);
        TaskOpinion taskOpinion = new TaskOpinion();
        long proInsId = Long.valueOf(task.getProcessInstanceId()).longValue();//流程号

        taskOpinion.setOpinion(opinion);
        taskOpinion.setProcessInstanceId(proInsId);
        taskOpinion.setTaskId(taskId);
        taskOpinion.setTaskType(task.getProcessId());
        taskOpinion.setTaskStatus(setOpinionStatus(proInsId, taskId));

        taskOpinionService.save(taskOpinion);
    }

    /**
     * 获得当前的节点状态
     * @param proInsId
     * @param taskId
     * @return
     */
    public String setOpinionStatus(long proInsId,long taskId){
        //根据流程号获取所有节点
        List<TaskBean> taskBeans = getTaskByProInsId(proInsId);
        int flag = 0;
        for(TaskBean taskBean:taskBeans){
            flag++;//flag记录了当前流程处于哪个节点
            long id = Long.valueOf(taskBean.getId()).longValue();//将String类型的流程号转化为long型
            if(taskId==id){
                break;
            }
        }
        return flag+"";
    }
    /**
     * 插入回退的意见
     * @param taskId
     * @param rollbackOpinion
     */
    public void rollbackOpinion(Long taskId,String rollbackOpinion){
        TaskBean task = getTaskById(taskId);
        TaskOpinion taskOpinion = taskOpinionService.getEntiyById(taskId);
        if(null == taskOpinion){//若意见表中没有这条数据就新增
            taskOpinion = new TaskOpinion();
            long proInsId = Long.valueOf(task.getProcessInstanceId()).longValue();
            taskOpinion.setTaskId(taskId);
            taskOpinion.setProcessInstanceId(proInsId);
            taskOpinion.setTaskType(task.getProcessId());
            taskOpinion.setTaskStatus(setOpinionStatus(proInsId, taskId));
            taskOpinion.setRollbackOpinion(rollbackOpinion);
            taskOpinionService.save(taskOpinion);
        }else{
            String userName = (String) SecurityUtils.getSubject().getPrincipal();//获得当前用户
            StringBuffer rbOpinion = new StringBuffer();
            if(null!=taskOpinion.getRollbackOpinion()){//若已有回退意见
                rbOpinion.append(taskOpinion.getRollbackOpinion());
            }
            rbOpinion.append("用户"+userName+"执行回退：");
            rbOpinion.append(rollbackOpinion+"。");
            taskOpinion.setRollbackOpinion(rbOpinion.toString());
            taskOpinionService.save(taskOpinion);
        }
    }
    /**
     * 通过Id获取Task实体类
     * @param taskId
     * @return
     */
    public TaskBean getTaskById(Long taskId){
        String sql = "select * from task  where id="+taskId;
        Session session = getDAO().getSession();
        Query query=session.createSQLQuery(sql);
        List<Object[]> taskBeans = query.list();
        Object[] taskObj = taskBeans.get(0);
        TaskBean task = filtrateAllTask(taskObj);
        return task;
    }

    /**
     * 根据processInstanceId获得一串TaskBean
     * @param proInsId
     * @return
     */
    public List<TaskBean> getTaskByProInsId(Long proInsId){
        String sql = "select * from task where processInstanceId="+proInsId+" order by id";
        Session session = getDAO().getSession();
        Query query=session.createSQLQuery(sql);
        List<Object[]> taskBeans = query.list();
        List<TaskBean> taskBeanList = new ArrayList();
        for(Object[] obj:taskBeans){
            taskBeanList.add(filtrateAllTask(obj));
        }
        if(null==taskBeanList){
            return null;
        }
        return taskBeanList;
    }

    /**
     * 对得到的List集合中的元素进行集中处理，强行匹配为TaskBean类型
     * @return
     */
    public TaskBean filtrateAllTask(Object[] task){
        TaskBean taskBean = new TaskBean();
        //接受拿到的数据
        if(null!=task[0]){taskBean.setId(task[0].toString());}
        if(null!=task[1]){taskBean.setAllowedToDelegate(task[1].toString());}
        if(null!=task[2]){taskBean.setPriority(task[2].toString());}
        if(null!=task[3]){taskBean.setActivationTime(task[3].toString());}
        if(null!=task[4]){taskBean.setCreatedOn(task[4].toString());}
        if(null!=task[5]){taskBean.setDocumentAccessType(task[5].toString());}
        if(null!=task[6]){taskBean.setDocumentContentId(task[6].toString());}
        if(null!=task[7]){taskBean.setDocumentType(task[7].toString());}
        if(null!=task[8]){taskBean.setExpirationTime(task[8].toString());}
        if(null!=task[9]){taskBean.setFaultAccessType(task[9].toString());}
        if(null!=task[10]){taskBean.setFaultContentId(task[10].toString());}
        if(null!=task[11]){taskBean.setFaultName(task[11].toString());}
        if(null!=task[12]){taskBean.setFaultType(task[12].toString());}
        if(null!=task[13]){taskBean.setOutputType(task[13].toString());}
        if(null!=task[14]){taskBean.setOutputContentId(task[14].toString());}
        if(null!=task[15]){taskBean.setOutputType(task[15].toString());}
        if(null!=task[16]){taskBean.setParentId(task[16].toString());}
        if(null!=task[17]){taskBean.setPreviousStatus(task[17].toString());}
        if(null!=task[18]){taskBean.setProcessId(task[18].toString());}
        if(null!=task[19]){taskBean.setProcessInstanceId(task[19].toString());}
        if(null!=task[20]){taskBean.setProcessSessionId(task[20].toString());}
        if(null!=task[21]){taskBean.setSkipable(task[21].toString());}
        if(null!=task[22]){taskBean.setStatus(task[22].toString());}
        if(null!=task[23]){taskBean.setWorkItemId(task[23].toString());}
        if(null!=task[24]){taskBean.setTaskInItiayorId(task[24].toString());}
        if(null!=task[25]){taskBean.setActualOwnerId(task[25].toString());}
        if(null!=task[26]){taskBean.setCreateById(task[26].toString());}
        return taskBean;
    }
    public static class TaskBean{
        private String id;//0
        private String allowedToDelegate;//1
        private String priority;//2
        private String activationTime;//3
        private String createdOn;//4
        private String documentAccessType;//5
        private String documentContentId;//6
        private String documentType;//7
        private String expirationTime;//8
        private String faultAccessType;//9
        private String faultContentId;//10
        private String faultName;//11
        private String faultType;//12
        private String outputAccessType;//13
        private String outputContentId;//14
        private String outputType;//15
        private String parentId;//16
        private String previousStatus;//17
        private String processId;//18
        private String processInstanceId;//19
        private String processSessionId;//20
        private String skipable;//21
        private String Status;//22
        private String workItemId;//23
        private String taskInItiayorId;//24
        private String actualOwnerId;//25
        private String createById;//26

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAllowedToDelegate() {
            return allowedToDelegate;
        }

        public void setAllowedToDelegate(String allowedToDelegate) {
            this.allowedToDelegate = allowedToDelegate;
        }

        public String getPriority() {
            return priority;
        }

        public void setPriority(String priority) {
            this.priority = priority;
        }

        public String getActivationTime() {
            return activationTime;
        }

        public void setActivationTime(String activationTime) {
            this.activationTime = activationTime;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

        public String getDocumentAccessType() {
            return documentAccessType;
        }

        public void setDocumentAccessType(String documentAccessType) {
            this.documentAccessType = documentAccessType;
        }

        public String getDocumentContentId() {
            return documentContentId;
        }

        public void setDocumentContentId(String documentContentId) {
            this.documentContentId = documentContentId;
        }

        public String getDocumentType() {
            return documentType;
        }

        public void setDocumentType(String documentType) {
            this.documentType = documentType;
        }

        public String getExpirationTime() {
            return expirationTime;
        }

        public void setExpirationTime(String expirationTime) {
            this.expirationTime = expirationTime;
        }

        public String getFaultAccessType() {
            return faultAccessType;
        }

        public void setFaultAccessType(String faultAccessType) {
            this.faultAccessType = faultAccessType;
        }

        public String getFaultContentId() {
            return faultContentId;
        }

        public void setFaultContentId(String faultContentId) {
            this.faultContentId = faultContentId;
        }

        public String getFaultName() {
            return faultName;
        }

        public void setFaultName(String faultName) {
            this.faultName = faultName;
        }

        public String getFaultType() {
            return faultType;
        }

        public void setFaultType(String faultType) {
            this.faultType = faultType;
        }

        public String getOutputAccessType() {
            return outputAccessType;
        }

        public void setOutputAccessType(String outputAccessType) {
            this.outputAccessType = outputAccessType;
        }

        public String getOutputContentId() {
            return outputContentId;
        }

        public void setOutputContentId(String outputContentId) {
            this.outputContentId = outputContentId;
        }

        public String getOutputType() {
            return outputType;
        }

        public void setOutputType(String outputType) {
            this.outputType = outputType;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getPreviousStatus() {
            return previousStatus;
        }

        public void setPreviousStatus(String previousStatus) {
            this.previousStatus = previousStatus;
        }

        public String getProcessId() {
            return processId;
        }

        public void setProcessId(String processId) {
            this.processId = processId;
        }

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getProcessSessionId() {
            return processSessionId;
        }

        public void setProcessSessionId(String processSessionId) {
            this.processSessionId = processSessionId;
        }

        public String getSkipable() {
            return skipable;
        }

        public void setSkipable(String skipable) {
            this.skipable = skipable;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getWorkItemId() {
            return workItemId;
        }

        public void setWorkItemId(String workItemId) {
            this.workItemId = workItemId;
        }

        public String getTaskInItiayorId() {
            return taskInItiayorId;
        }

        public void setTaskInItiayorId(String taskInItiayorId) {
            this.taskInItiayorId = taskInItiayorId;
        }

        public String getActualOwnerId() {
            return actualOwnerId;
        }

        public void setActualOwnerId(String actualOwnerId) {
            this.actualOwnerId = actualOwnerId;
        }

        public String getCreateById() {
            return createById;
        }

        public void setCreateById(String createById) {
            this.createById = createById;
        }
    }
}




























