package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.process.impl.JbpmSupport;
import com.dc.esb.servicegov.service.InterfaceService;
import com.dc.esb.servicegov.service.ServiceIdentifyService;
import com.dc.esb.servicegov.service.SystemService;
import com.dc.esb.servicegov.service.impl.InterfaceServiceImpl;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.task.TaskService;
import org.jbpm.task.query.TaskSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/serviceIdentify")
public class ServiceIdentifyController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private OperationDAOImpl operataionDAO;
    @Autowired
    private InterfaceServiceImpl interfaceService;
    @Autowired
    private ServiceDAOImpl serviceDAO;
    @Autowired
    private ServiceCategoryDAOImpl serviceCategoryDAOImpl;
    @Autowired
    private ServiceInvokeDAOImpl serviceInvokeDAO;
    @Autowired
    private ServiceIdentifyService serviceIdentifyService;
    @Autowired
    private SystemDAOImpl systemDAO;

    @Autowired
    private ServiceCategoryDAOImpl serviceCategoryDAO;
    private static final Log log = LogFactory.getLog(ProcessController.class);
    @Autowired
    private ProcessContextServiceImpl processContextService;
    @Autowired
    private JbpmSupport jbpmSupport;
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    SystemService systemService;

//    /**
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.GET, value = "/doTask/{processInstanceId}", headers = "Accept=application/json")
//    public ModelAndView doTask(@PathVariable
//                                  String processInstanceId) {
//        ModelAndView mv = new ModelAndView("serviceIdentify/edit");
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("processId", processInstanceId);
//        List<ProcessContext> processContexts = processContextService.findBy(params,"optDate");
//        if(null == processContexts){
//            processContexts = new ArrayList<ProcessContext>();
//            String value=processContexts.get(0).getValue();
//            int i=value.indexOf("interface:[");
//            int s=value.indexOf("],serviceId:[ ");
//            int o=value.indexOf("],operationId:[");
//            int end=value.indexOf("].");
//            String interfaceId=value.substring(i+11,s);
//            String serviceId=value.substring(s+13,o);
//            String operationId=value.substring(o+15,end);
//
//        }
//
////        mv.addObject("ecode",ecode);
////        mv.addObject("interfaceName",interfaceName);
////        String hql="select systemId from ServiceInvoke where interfaceId='"+interfaceId+"' and type='"+Constants.INVOKE_TYPE_PROVIDER+"'";
////        List<Object> system=systemDAO.findBy(hql.toString());
////        for(Object o:system){
////            System consumerId = systemDAO.findUniqueBy("systemId", String.valueOf(o));
////            mv.addObject("systemId",consumerId.getSystemChineseName());
////        }
////        mv.addObject("iId",interfaceId);
//        return mv;
//    }
    /**
     *
     * @param user
     * @param type
     * @param params
     * @return
     */
    @RequestMapping(value = "{user}/create/{type}/{interfaceId}/{serviceId}/{operationId}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String,String> doCreate(@PathVariable("user") String user, @PathVariable("type") String type,@PathVariable("interfaceId") String interfaceId, @PathVariable("serviceId") String serviceId,@PathVariable("operationId") String operationId,  @RequestBody Map<String, Object> params) {
        log.info(user + " create a process [" + type + "]");
        StatefulKnowledgeSession ksession = jbpmSupport.getKsession();
        Map<String,String> map=new HashMap<String, String>();
        String taskId=null;
        try{
            long processId =ksession.startProcess("com.dc.esb.servicegov.process." + type, params).getId();
            ksession.fireAllRules();
            String process=String.valueOf(processId);
            TaskService taskService = jbpmSupport.getTaskService();

            List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
            for (TaskSummary taskSummary : tasks) {
                String id=String.valueOf(taskSummary.getProcessInstanceId());
                if(id.equals(process)){
                     taskId=String.valueOf(taskSummary.getId());
                    map.put("taskId",taskId);
                    break;
                }


            }
            ProcessContext processContext = new ProcessContext();
            processContext.setName("新增服务识别");
            processContext.setProcessId(process);
            processContext.setKey("interface");
            processContext.setValue(interfaceId);
            processContext.setType("result");
            String op=operationId.substring(1,operationId.length());
            processContext.setRemark("interface:[" + interfaceId + "],serviceId:[" +serviceId+"],operationId:["+op+ "].");
            String optDate=DateUtils.format(new Date());
            String optUser = (String) SecurityUtils.getSubject().getPrincipal();
            processContext.setOptDate(optDate);
            processContext.setOptUser(optUser);
            processContextService.save(processContext);
            map.put("processId",process);

        }catch (Exception e){
            log.error(e, e);
        }


        return map;
    }
    /**
     *获取服务大类
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getCategoryAll", headers = "Accept=application/json")
    public @ResponseBody List<Map<String,Object>> getSystemAll(HttpServletRequest request) {
        List<Map<String,Object>> resList = new ArrayList<Map<String, Object>>();
        StringBuffer hql1=new StringBuffer("select sc.categoryId,sc.categoryName,sc.desc from ServiceCategory as sc where parentId is null");
        Map<String,Object> map = new HashMap<String, Object>();
        List<Object[]> s=serviceCategoryDAOImpl.findBy(hql1.toString());
        for(Object[] o:s){
            map = new HashMap<String, Object>();
            map.put("id",String.valueOf(o[0]));
            map.put("text",String.valueOf(o[2]));
            map.put("chineseName",String.valueOf(o[1]));
            resList.add(map);
        }
        return resList;
    }
    /**
     *
     */

    /**
     *添加接口id与服务关联关系
     */
//    @RequestMapping(method = RequestMethod.POST, value = "/reuse", headers = "Accept=application/json")
    @RequestMapping("/save")
    public @ResponseBody
    boolean save(String interfaceId,String serviceId,String serviceName,String operationId,String operationName,String consumers,String providers,String categoryName) throws UnsupportedEncodingException {
        OperationLog operationLog = systemLogService.record("服务识别", "识别", "接口：" + interfaceId);
        String categoryId=URLDecoder.decode(categoryName);
        if(categoryId!=null&&!"".equals(categoryId)){
            ServiceCategory category = serviceCategoryDAO.findUniqueBy("categoryName", categoryId);
            categoryId=category.getCategoryId();
        }
        String consumerId=null;
        String providerId=null;
        String consumerName= URLDecoder.decode(consumers, "utf-8");
        if(consumerName!=null&&!"".equals(consumerName)){
            System system = systemDAO.findUniqueBy("systemChineseName", consumerName);
             consumerId=system.getSystemId();
        }
        String providerName= URLDecoder.decode(providers,"utf-8");
        if(providerName!=null&&!"".equals(providerName)){
            System system = systemDAO.findUniqueBy("systemChineseName", providerName);
             providerId=system.getSystemId();
        }
        if(serviceId==null||"".equals(serviceId)||operationId==null||"".equals(operationId)){
            return false;
        }
        List<Object> list=serviceIdentifyService.getSystemId(serviceId, operationId);
        if(list.size()>0){
         if(list.get(0).toString().equals(providerId)){
            return false;
            }
        }

        boolean sFlag=serviceIdentifyService.uniqueServiceId(serviceId);
        if(sFlag){
            boolean oFlag= serviceIdentifyService.uniqueOperationId(serviceId,operationId);
            if(oFlag){
                setConsumer(interfaceId, operationId, serviceId, consumerId);
                setProvider(interfaceId, operationId, serviceId, providerId);
                systemLogService.updateResult(operationLog);
                serviceIdentifyService.changeInterfaceState(interfaceId,"6");
                return true;
            }else {
                setOperation(operationId,serviceId,operationName);
                setConsumer(interfaceId, operationId, serviceId, consumerId);
                setProvider(interfaceId, operationId, serviceId, providerId);
                systemLogService.updateResult(operationLog);
                serviceIdentifyService.changeInterfaceState(interfaceId,"6");
                return true;
            }
        }else {
            setService(serviceId,serviceName,categoryId);
            setOperation(operationId,serviceId,operationName);
            setConsumer(interfaceId, operationId, serviceId, consumerId);
            setProvider(interfaceId, operationId, serviceId, providerId);
            systemLogService.updateResult(operationLog);
            serviceIdentifyService.changeInterfaceState(interfaceId,"6");
            return true;
        }


    }

    public void setConsumer(String interfaceId,String operationId,String serviceId,String consumerId){
        ServiceInvoke c=new ServiceInvoke();
        c.setInterfaceId(interfaceId);
        c.setIsStandard("0");
        c.setOperationId(operationId);
        c.setServiceId(serviceId);
        c.setSystemId(consumerId);
        c.setType(Constants.INVOKE_TYPE_CONSUMER);
        serviceInvokeDAO.insert(c);
    }
    public void setProvider(String interfaceId,String operationId,String serviceId,String providerId){
        StringBuffer hql1=new StringBuffer("select distinct si.systemId from ServiceInvoke as si where si.interfaceId= '"+interfaceId+"' and si.type='"+Constants.INVOKE_TYPE_PROVIDER+"' and si.systemId='"+providerId+"'");
        int count=serviceInvokeDAO.findBy(hql1.toString()).size();
        if(count>0){
            StringBuffer hql2=new StringBuffer("update ServiceInvoke set serviceId='"+serviceId+"', operationId='"+operationId+"' where interfaceId = ? and systemId = ? and type= '0'");
            serviceInvokeDAO.exeHql(hql2.toString(),interfaceId,providerId);
        }else {
            ServiceInvoke c1=new ServiceInvoke();
            c1.setInterfaceId(interfaceId);
            c1.setIsStandard("0");
            c1.setOperationId(operationId);
            c1.setServiceId(serviceId);
            c1.setSystemId(providerId);
            c1.setType(Constants.INVOKE_TYPE_PROVIDER);
            serviceInvokeDAO.insert(c1);
        }

    }
    public void setService(String serviceId,String serviceName,String categoryId){
        Service s=new Service();
        s.setServiceId(serviceId);
        s.setServiceName(serviceName);
        s.setCategoryId(categoryId);
        serviceDAO.insert(s);
    }
    public void setOperation(String operationId,String serviceId,String operationName){
        Operation o=new Operation();
        o.setOperationId(operationId);
        o.setServiceId(serviceId);
        o.setDeleted("0");
        o.setOperationName(operationName);
        o.setOptDate(DateUtils.format(new Date()));
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        o.setOptUser(userName);
        o.setProcessId("undefined");
        o.setState("0");
        operataionDAO.insert(o);
        sdaService.genderSDAAuto(serviceId,operationId);
    }

    /**
     * 根据交易码和交易名称跳转到服务识别界面
     * @param ecode
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/edit/{ecode}/{interfaceId}", headers = "Accept=application/json")
    public ModelAndView getSystem(@PathVariable String ecode,@PathVariable String interfaceId) {
        ModelAndView mv = new ModelAndView("serviceIdentify/edit");
        mv.addObject("ecode",ecode);
        Interface inter=interfaceService.findUniqueBy("ecode", ecode);
        if(inter!=null){
            String interfaceName=inter.getInterfaceName();
            mv.addObject("interfaceName",interfaceName);
        }

        String hql="select systemId from ServiceInvoke where interfaceId='"+interfaceId+"' and type='"+Constants.INVOKE_TYPE_PROVIDER+"'";
        List<Object> system=systemDAO.findBy(hql.toString());
        for(Object o:system){
            System consumerId = systemDAO.findUniqueBy("systemId", String.valueOf(o));
            mv.addObject("systemId", consumerId.getSystemChineseName());
        }
        mv.addObject("iId",interfaceId);
        return mv;
    }
    /**
     * 根据接口Id查询是否关联
     * @param interfaceId
     * @return
     */
    @RequestMapping("/judgeByInterfaceId")
    @ResponseBody
    public boolean judgeByMetadataId(String interfaceId){
        boolean flag=serviceIdentifyService.judgeByMetadataId(interfaceId);
        return flag;
    }
    /**
     * 根据元数据ID查询场景列表
     * @param interfaceId
     * @return
     */
    @RequestMapping("/getByMetadataId/{interfaceId}")
    @ResponseBody
    public List<ServiceInvoke> getByMetadataId(@PathVariable(value = "interfaceId") String interfaceId){
        List<ServiceInvoke> sio=new ArrayList<ServiceInvoke>();
        StringBuffer hql1=new StringBuffer("select distinct si.serviceId,si.operationId,si.systemId from ServiceInvoke as si where si.interfaceId= '"+interfaceId+"' and si.type='"+Constants.INVOKE_TYPE_PROVIDER+"'");
        List<Object[]> s=serviceInvokeDAO.findBy(hql1.toString());
        for(Object[] o:s){
            ServiceInvoke ss=new ServiceInvoke();
            String serviceId=String.valueOf(o[0]);
            String operationId=String.valueOf(o[1]);
            ss.setServiceId(serviceId);
            ss.setOperationId(operationId);
            System provider = systemDAO.findUniqueBy("systemId", String.valueOf(o[2]));

            StringBuffer hql=new StringBuffer("select si.systemId from ServiceInvoke as si where si.interfaceId= '"+interfaceId+"' and si.type='"+Constants.INVOKE_TYPE_CONSUMER+"'");
            List<Object> consumer=serviceInvokeDAO.findBy(hql.toString());
            for(Object consumers:consumer){
                System consumerId = systemDAO.findUniqueBy("systemId", consumers);
                ss.setSystemId("provider:["+provider.getSystemChineseName()+"],consumer:["+consumerId.getSystemChineseName()+"]");
            }
            sio.add(ss);

        }
        return sio;
    }
}
