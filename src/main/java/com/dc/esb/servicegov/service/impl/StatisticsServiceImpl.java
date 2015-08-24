package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.StatisticsService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.EasyUiTreeUtil;
import com.dc.esb.servicegov.util.TreeNode;
import com.dc.esb.servicegov.vo.ReleaseVO;
import com.dc.esb.servicegov.vo.ReuseRateVO;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2015/8/14.
 */
@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService{
    @Autowired
    private SystemDAOImpl systemDAO;
    @Autowired
    private ServiceDAOImpl serviceDAO;
    @Autowired
    private OperationDAOImpl operationDAO;
    @Autowired
    private OperationHisDAOImpl operationHisDAO;
    @Autowired
    private ServiceInvokeDAOImpl serviceInvokeDAO;
    @Autowired
    private ServiceCategoryDAOImpl serviceCategoryDAO;
    @Override
    public long getReuseRateCount(Map<String, String[]> values) {
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " si where 1=1";
        if(values.get("type") != null && values.get("type").length > 0){
            if (StringUtils.isNotEmpty(values.get("type")[0])){
                hql += " and si.type = " + values.get("type")[0];
            }
        }
        if(values.get("systemId") != null && values.get("systemId").length > 0){
            if (StringUtils.isNotEmpty(values.get("systemId")[0])) {
                hql += " and si.systemId like '%" + values.get("systemId")[0] + "%'";
            }
        }
        hql += " group by si.systemId, si.type";
        long count = serviceInvokeDAO.find(hql).size();
        return count;
    }
    /**
     * 根据系统id，类型分组
     */
    public List<Object[]> groupBySystemIdType(Map<String, String[]> values, Page page){
        String hql = "select si.systemId, si.type from " + ServiceInvoke.class.getName() + " as si where 1=1";
        if(values.get("type") != null && values.get("type").length > 0){
            if (StringUtils.isNotEmpty(values.get("type")[0])) {
                hql += " and si.type = " + values.get("type")[0];
            }
        }
        if(values.get("systemId") != null && values.get("systemId").length > 0){
            if (StringUtils.isNotEmpty(values.get("systemId")[0])) {
                hql += " and si.systemId like '%" + values.get("systemId")[0] + "%'";
            }
        }
        hql += " group by systemId, type";
        List<Object[]> list = serviceInvokeDAO.findBy(hql, page, new ArrayList<SearchCondition>());
        return list;
    }

    @Override
    public List<ReuseRateVO> getReuseRate(Map<String, String[]> values, Page page) {

        List<Object[]> list = groupBySystemIdType(values, page);

        List<ReuseRateVO> voList = new ArrayList<ReuseRateVO>();
        for(Object[] strs: list){
            com.dc.esb.servicegov.entity.System system = systemDAO.findUniqueBy("systemId", strs[0]);
            ReuseRateVO vo = new ReuseRateVO();
            vo.setType(String.valueOf(strs[1]));
            vo.setSystemChineseName(system.getSystemChineseName());
            vo.setSystemId(String.valueOf(strs[0]));
            long operationNum = getOperationRelaCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setOperationNum(String.valueOf(operationNum));//关联场景数
            long operationInvokeNum = getOperationInvokeCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setOperationInvokeNum(String.valueOf(operationInvokeNum));//场景消费者系统数
            long serviceNum = getServiceRelaCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setServiceNum(String.valueOf(serviceNum));//关联服务数
            long sum = getServiceInvokeCount( String.valueOf(strs[1]));
            vo.setSum(String.valueOf(sum));//提供者或消费者被调用总数
            long useNum = getServiceInvokeCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setUseNum(String.valueOf(useNum));//当前系统作为提供者或消费者被调用次数
            if(operationInvokeNum > operationNum && operationNum > 0){
                float r = (operationInvokeNum - operationNum + 0f)/operationInvokeNum;
                NumberFormat nt = NumberFormat.getPercentInstance();
                nt.setMinimumFractionDigits(2);
                vo.setReuseRate(nt.format(r));
            }else{
                vo.setReuseRate("0");
            }
            voList.add(vo);
        }
        return voList;
    }
    /**
     * 根据系统id查询被调用场景数
     * @param systemId
     * @return
     */
    public long getOperationRelaCount(String systemId, String type){
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " as si where si.systemId = ? and si.type = ? group by serviceId, operationId";
        long count = serviceInvokeDAO.find(hql, systemId, type).size();
        return count;
    }

    /**
     * @param systemId 系统id
     * @param type 类型
     * @return 系统相关所有场景消费者数量或提供者数量
     */
    public long getOperationInvokeCount(String systemId, String type){
        long count = 0;
        String hql = " select o from " + ServiceInvoke.class.getName() + " as si, " +
                Operation.class.getName() + " as o  where si.serviceId = o.serviceId and si.operationId = o.operationId and si.systemId = ? and si.type = ? ";
        List<Operation> operations = operationDAO.find(hql, systemId, type);
        for(Operation operation : operations){
            String hql2 = " select count(*) from " + ServiceInvoke.class.getName() + " as si where si.serviceId = ? and si.operationId = ? and si.type = ?";
            long singleCount = serviceInvokeDAO.findUnique(hql2, operation.getServiceId(), operation.getOperationId(), type);
            count += singleCount;
        }
        return count;
    }
    /**
     * 根据系统id查询被调用服务数
     * @param systemId
     * @return
     */
    public long getServiceRelaCount(String systemId, String type){
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " as si where si.systemId = ? and si.type = ? group by serviceId";
        long count = serviceInvokeDAO.find(hql, systemId, type).size();
        return count;
    }
    /**
     * 根据类型查询调用总数
     */
    public long getServiceInvokeCount(String type){
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " as si where si.type = ?";

        long count = (Long)serviceInvokeDAO.findUnique(hql, type);
        return count;
    }
    /**
     * 根据系统id。类型查询调用总数
     */
    public long getServiceInvokeCount(String systemId, String type){
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " as si where si.systemId = ? and si.type = ?";
        long count = (Long)serviceInvokeDAO.findUnique(hql, systemId, type);
        return count;
    }

    @Override
    public long getReleaseVOCount(Map<String, String[]> values) {
        String hql = "select count(*) from " + ServiceInvoke.class.getName() + " si where 1=1";
        if(values.get("type") != null && values.get("type").length > 0){
            if (StringUtils.isNotEmpty(values.get("type")[0])){
                hql += " and si.type = " + values.get("type")[0];
            }
        }
        if(values.get("systemId") != null && values.get("systemId").length > 0){
            if (StringUtils.isNotEmpty(values.get("systemId")[0])) {
                hql += " and si.systemId like '%" + values.get("systemId")[0] + "%'";
            }
        }
        hql += " group by si.systemId, si.type";
        long count = serviceInvokeDAO.find(hql).size();
        return count;
    }

    @Override
    public List<ReleaseVO> getReleaseVO(Map<String, String[]> values, Page page) {

        List<Object[]> list = groupBySystemIdType(values, page);

        List<ReleaseVO> voList = new ArrayList<ReleaseVO>();
        for(Object[] strs : list){
            com.dc.esb.servicegov.entity.System system = systemDAO.findUniqueBy("systemId", strs[0]);
            ReleaseVO vo = new ReleaseVO();
            vo.setType(String.valueOf(strs[1]));
            vo.setSystemChineseName(system.getSystemChineseName());
            vo.setSystemId(String.valueOf(strs[0]));
            setReleaseCount(vo, values);
            voList.add(vo);
        }
        return voList;
    }
    /**
     * 根据系统id, 时间 查询发布场景数
     * @return
     */
    public void setReleaseCount(ReleaseVO vo, Map<String, String[]> values){
        String hql = "select new " + OperationPK.class.getName() + "(si.serviceId, si.operationId) from " + ServiceInvoke.class.getName() + " as si where si.systemId = ? and si.type = ? group by serviceId, operationId";
        List pkList = serviceInvokeDAO.find(hql, vo.getSystemId(), vo.getType());
        long operationReleaseNum = 0;
        List<String> serviceIds = new ArrayList<String>();
        for(int i = 0; i < pkList.size(); i++){
            OperationPK pk = (OperationPK)pkList.get(i);
            String hql2 = " select count(*) from " + OperationHis.class.getName() + " as o where o.serviceId=? and operationId=? ";
            if(values.get("startDate") != null && values.get("startDate").length > 0){
                if (StringUtils.isNotEmpty(values.get("startDate")[0])) {
                    hql2 += " and o.optDate > '" + values.get("startDate")[0] + "' ";
                }
            }
            if(values.get("endDate") != null && values.get("endDate").length > 0){
                if (StringUtils.isNotEmpty(values.get("endDate")[0])) {
                    hql2 += " and o.optDate < '" + values.get("endDate")[0] + "' ";
                }
            }
            long hisNum = (Long)operationHisDAO.findUnique(hql2, pk.getServiceId(), pk.getOperationId());
            operationReleaseNum += hisNum;
            if(!serviceIds.contains(pk.getServiceId()) && hisNum > 0){
                serviceIds.add(pk.getServiceId());
            }
        }
        vo.setOperationReleaseNum(String.valueOf(operationReleaseNum));
        vo.setServiceReleaseNum(String.valueOf(serviceIds.size()));
    }
    /**
     * 从服务分类维度计算复用率
     * @return 复用率
     */
    @Override
    public List<TreeNode> getServiceReuseRate(){
        TreeNode root = new TreeNode();//真是让人蛋疼的数据库设计，为什么不在数据库中直接插一个root节点，每次要手动拼，服务分类和服务明明就可以一张表
        root.setId("root");
        root.setText("服务类");

        List<ServiceCategory> categories = serviceCategoryDAO.getAll();
        Map<String, String > fields =  new HashMap<String, String>();
        fields.put("id", "categoryId");
        fields.put("text", "categoryName");

        List<TreeNode> categoryNodes = EasyUiTreeUtil.getInstance().convertTree(categories,fields );//将分类拼接成树
        root.setChildren(categoryNodes);
        genderCategoryService(root);
        genderServiceReuseRate(root);

        List<TreeNode> result = new ArrayList<TreeNode>();
        result.add(root);
        return result;
    }
    public void genderCategoryService(TreeNode categoryNode){//构建服务树
        if (StringUtils.isNotEmpty(categoryNode.getParentId()) && categoryNode.getChildren() == null) {
            List<com.dc.esb.servicegov.entity.Service> services = getService(categoryNode.getId());
            if (services != null && services.size() > 0) {
                List<TreeNode> serviceNodes = new ArrayList<TreeNode>();
                for (com.dc.esb.servicegov.entity.Service service : services) {
                    TreeNode serviceNode = new TreeNode();
                    serviceNode.setId(service.getServiceId());
                    serviceNode.setText(service.getServiceName());
                    serviceNode.setAppend1("service");
                    serviceNodes.add(serviceNode);
                }
                categoryNode.setChildren(serviceNodes);
            }
        }
        List<TreeNode> children = categoryNode.getChildren();
        if(children != null && children.size() > 0){
            for(TreeNode child : children){
                genderCategoryService(child);
            }
        }
    }
    /**
     * @param treeNode 计算服务分类或服务复用率
     */
    public void genderServiceReuseRate(TreeNode treeNode) {
        String id = treeNode.getId();
        String type = treeNode.getAppend1();
        List<com.dc.esb.servicegov.entity.Service> services;
        if (StringUtils.isNotEmpty(type) && "service".equals(type)) {  //判断传入的id是分类还是服务
            services = serviceDAO.findBy("serviceId", id);
        }
        else{
            services = getService(id);
        }
        long serviceNum = services.size();
        treeNode.setAppend2(String.valueOf(serviceNum)); //服务数
        List<String> serviceIds = new ArrayList<String>();
        for(int i=0; i < services.size(); i++){
            serviceIds.add(services.get(i).getServiceId());
        }
        long operationNum = 0 ;
        long operationInvokeNum = 0;
        if(serviceIds.size() > 0){
            String optNumHql = "select count(*) from  "+ Operation.class.getName() + " as o where o.serviceId in (:serviceIds)";
            Map<String, Object> p1 = new HashMap<String, Object>();
            p1.put("serviceIds", serviceIds);
            p1.put("type", Constants.INVOKE_TYPE_CONSUMER);
            operationNum = operationDAO.findUnique(optNumHql,p1 );


            String conNumHql = "select count(*)  from " + ServiceInvoke.class.getName() + " as si where si.type=:type and si.serviceId  in (:serviceIds)";
            operationInvokeNum = serviceInvokeDAO.findUnique(conNumHql,p1 );

        }

//        List<Operation> operations = getOperation(services);
//        long operationNum = operations.size();

//        List<ServiceInvoke> consumers = getServiceInvoke(operations, Constants.INVOKE_TYPE_CONSUMER);
//        long operationInvokeNum = consumers.size();

        treeNode.setAppend3(String.valueOf(operationNum));//场景数
        treeNode.setAppend4(String.valueOf(operationInvokeNum));//场景数

        if(operationInvokeNum > operationNum && operationNum > 0){
            float r = (operationInvokeNum - operationNum + 0f)/operationInvokeNum;
            NumberFormat nt = NumberFormat.getPercentInstance();
            nt.setMinimumFractionDigits(2);
            treeNode.setAppend5(nt.format(r));//复用率
        }else{
            treeNode.setAppend5("0");
        }

        List<TreeNode> children = treeNode.getChildren();
        if(children != null && children.size() > 0){
            for(TreeNode child : children){
                genderServiceReuseRate(child);
            }
        }
    }

    /**
     * 迭代：根据分类id查询服务
     * @param categoryId
     * @return
     */
    public List<com.dc.esb.servicegov.entity.Service> getService(String categoryId){
        List<com.dc.esb.servicegov.entity.Service> list = new ArrayList<com.dc.esb.servicegov.entity.Service>();
        List<ServiceCategory> children = serviceCategoryDAO.findBy("parentId", categoryId);
        if("root".equals(categoryId)){
            String hql = " from " + ServiceCategory.class.getName() + " where parentId is null ";
            children = serviceCategoryDAO.find(hql);
        }
        if(children != null && children.size() > 0){
            for(ServiceCategory serviceCategory : children){
                List<com.dc.esb.servicegov.entity.Service> childList = getService(serviceCategory.getCategoryId());
                list.addAll(childList);
            }
        }
        else{
            list = serviceDAO.findBy("categoryId", categoryId);
        }
        return list;
    }
    /**
     * @return
     */
    public List<Operation> getOperation(List<com.dc.esb.servicegov.entity.Service> services){
        List<Operation> operations = new ArrayList<Operation>();
        for(com.dc.esb.servicegov.entity.Service service : services){
            List<Operation> childOperations = operationDAO.findBy("serviceId", service.getServiceId());
            operations.addAll(childOperations);
        }
        return operations;
    }

    public List<ServiceInvoke> getServiceInvoke(List<Operation> operations, String type){
        List<ServiceInvoke> list = new ArrayList<ServiceInvoke>();
        for(Operation operation : operations){
            String hql = " from " + ServiceInvoke.class.getName() + " as si where si.serviceId=? and si.operationId = ? and si.type=?";
            List<ServiceInvoke> childList = serviceInvokeDAO.find(hql, operation.getServiceId(), operation.getOperationId(), type);
            list.addAll(childList);
        }
        return list;
    }
}
