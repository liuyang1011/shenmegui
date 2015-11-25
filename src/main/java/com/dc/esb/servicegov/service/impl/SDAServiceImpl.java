package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.SDADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.entity.SDAHis;
import com.dc.esb.servicegov.service.SDAService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.DateUtils;
import com.dc.esb.servicegov.util.EasyUiTreeUtil;
import com.dc.esb.servicegov.util.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class SDAServiceImpl extends AbstractBaseService<SDA, String> implements SDAService {

    @Autowired
    private SDADAOImpl sdaDAO;
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private SDAHisServiceImpl sdaHisService;
    @Autowired
    private MetadataServiceImpl metadataService;
    @Autowired
    private VersionServiceImpl versionService;
    public List<SDA> genderSDAAuto(String serviceId, String operationId){
        List<SDA> result = new ArrayList<SDA>();
        SDA sdaRoot = new SDA();
        sdaRoot.setId(UUID.randomUUID().toString());
        sdaRoot.setStructName(Constants.ElementAttributes.ROOT_NAME);
        sdaRoot.setStructAlias(Constants.ElementAttributes.ROOT_ALIAS);
        sdaRoot.setXpath(Constants.ElementAttributes.ROOT_XPATH);
        sdaRoot.setSeq(0);
        sdaRoot.setServiceId(serviceId);
        sdaRoot.setOperationId(operationId);

        sdaDAO.save(sdaRoot);
        result.add(sdaRoot);

        SDA sdaReq = new SDA();
        sdaReq.setId(UUID.randomUUID().toString());
        sdaReq.setStructName(Constants.ElementAttributes.REQUEST_NAME);
        sdaReq.setStructAlias(Constants.ElementAttributes.REQUEST_ALIAS);
        sdaReq.setXpath(Constants.ElementAttributes.REQUEST_XPATH);
        sdaReq.setSeq(1);
        sdaReq.setServiceId(serviceId);
        sdaReq.setOperationId(operationId);
        sdaReq.setParentId(sdaRoot.getId());

        sdaDAO.save(sdaReq);
        result.add(sdaReq);

        SDA sdaRes = new SDA();
        sdaRes.setId(UUID.randomUUID().toString());
        sdaRes.setStructName(Constants.ElementAttributes.RESPONSE_NAME);
        sdaRes.setStructAlias(Constants.ElementAttributes.RESPONSE_ALIAS);
        sdaRes.setXpath(Constants.ElementAttributes.RESPONSE_XPATH);
        sdaRes.setSeq(2);
        sdaRes.setServiceId(serviceId);
        sdaRes.setOperationId(operationId);
        sdaRes.setParentId(sdaRoot.getId());
        sdaDAO.save(sdaRes);
        result.add(sdaRes);
        return result;
    }
    public Map<String, SDA> genderSDAAuto(String headId){
        Map<String, SDA> result = new LinkedHashMap<String, SDA>();
        SDA sdaRoot = new SDA();
        sdaRoot.setId(UUID.randomUUID().toString());
        sdaRoot.setStructName(Constants.ElementAttributes.ROOT_NAME);
        sdaRoot.setStructAlias(Constants.ElementAttributes.ROOT_ALIAS);
        sdaRoot.setXpath(Constants.ElementAttributes.ROOT_XPATH);
        sdaRoot.setHeadId(headId);
        sdaRoot.setSeq(0);
        sdaDAO.save(sdaRoot);
        result.put(Constants.ElementAttributes.ROOT_NAME, sdaRoot);

        SDA sdaReq = new SDA();
        sdaReq.setId(UUID.randomUUID().toString());
        sdaReq.setStructName(Constants.ElementAttributes.REQUEST_NAME);
        sdaReq.setStructAlias(Constants.ElementAttributes.REQUEST_ALIAS);
        sdaReq.setXpath(Constants.ElementAttributes.REQUEST_XPATH);
        sdaReq.setHeadId(headId);
        sdaReq.setSeq(1);
        sdaReq.setParentId(sdaRoot.getId());
        sdaDAO.save(sdaReq);
        result.put(Constants.ElementAttributes.REQUEST_NAME, sdaReq);

        SDA sdaRes = new SDA();
        sdaRes.setId(UUID.randomUUID().toString());
        sdaRes.setStructName(Constants.ElementAttributes.RESPONSE_NAME);
        sdaRes.setStructAlias(Constants.ElementAttributes.RESPONSE_ALIAS);
        sdaRes.setXpath(Constants.ElementAttributes.RESPONSE_XPATH);
        sdaRes.setHeadId(headId);
        sdaRes.setSeq(2);
        sdaRes.setParentId(sdaRoot.getId());
        sdaDAO.save(sdaRes);
        result.put(Constants.ElementAttributes.RESPONSE_NAME, sdaRes);
        return result;
    }
    public ModelAndView sdaPage(String operationId, String serviceId,
                                HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("service/sda/sdaPage");
        // 根据serviceId获取service信息
        if (StringUtils.isNotEmpty(serviceId)) {
            com.dc.esb.servicegov.entity.Service service = serviceService
                    .getUniqueByServiceId(serviceId);
            if (service != null) {
                mv.addObject("service", service);
            }
            if (StringUtils.isNotEmpty(operationId)) {
                // 根据serviceId,operationId获取operation信息
                Operation operation = operationService.getOperation(
                        serviceId, operationId);
                if (operation != null) {
                    mv.addObject("operation", operation);
                }
            }
        }
        return mv;
    }

    public List<SDA> getSDAListBySO(String serviceId, String operationId) {
        List<SDA> list = sdaDAO.find(
                " from SDA where operationId=? and serviceId=? order by seq asc",
                operationId, serviceId);
        return list;
    }

    public List<TreeNode> genderSDATree(String serviceId, String operationId) {
        List<SDA> list = getSDAListBySO(serviceId, operationId);
        List<SDABean> tempList = new ArrayList<SDABean>();
        //TODO 台行  类型和长度合并显示
        for(SDA per : list){
            SDABean sdaBean = new SDABean(per);
            if(null != sdaBean.getType() && !"STRUCT".equals(sdaBean.getType()) && !"ARRAY".equals(sdaBean.getType())){
                sdaBean.setType(sdaBean.getType() + "("+sdaBean.getLength()+")");
            }
            tempList.add(sdaBean);
        }
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "sdaId");
        fields.put("text", "structName");
        fields.put("append1", "structAlias");
        fields.put("append2", "type");
        fields.put("append3", "xpath");
        fields.put("append4", "metadataId");
        fields.put("append5", "required");
        fields.put("append6", "remark");
        fields.put("append7", "constraint");
        fields.put("append8", "serviceId");
        fields.put("append9", "operationId");
        fields.put("attributes", "seq");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        List<TreeNode> nodeList = eUtil.convertTree(tempList, fields);
        return nodeList;

    }

    public List<TreeNode> genderSDATree2(String serviceId, String operationId) {
        List<SDA> list = getSDAListBySO(serviceId, operationId);

        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "sdaId");
        fields.put("text", "structAlias");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        List<TreeNode> nodeList = eUtil.convertTree(list, fields);
        return nodeList;

    }

    public List<TreeNode> genderSDATree(String headId) {
        String hql = " from SDA where headId = ? order by seq asc";
        List<SDA> list = sdaDAO.find(hql, headId);
        List<SDABean> tempList = new ArrayList<SDABean>();
        //TODO 台行  类型和长度合并显示
        for(SDA per : list){
            SDABean sdaBean = new SDABean(per);
            if(null != sdaBean.getType() && !"STRUCT".equals(sdaBean.getType()) && !"ARRAY".equals(sdaBean.getType())){
                sdaBean.setType(sdaBean.getType() + "("+sdaBean.getLength()+")");
            }
            tempList.add(sdaBean);
        }
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "sdaId");
        fields.put("text", "structName");
        fields.put("append1", "structAlias");
        fields.put("append2", "type");
        fields.put("append3", "xpath");
        fields.put("append4", "metadataId");
        fields.put("append5", "required");
        fields.put("append6", "remark");
        fields.put("append7", "constraint");
        fields.put("append8", "headId");
        fields.put("attributes", "seq");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        List<TreeNode> nodeList = eUtil.convertTree(tempList, fields);
        return nodeList;

    }

    public List<TreeNode> genderSDATree2(String headId) {
        String hql = " from SDA where headId = ? order by seq asc";
        List<SDA> list = sdaDAO.find(hql, headId);

        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "sdaId");
        fields.put("text", "structName");
        fields.put("append1", "structAlias");
        fields.put("append2", "xpath");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        List<TreeNode> nodeList = eUtil.convertTree(list, fields);
        return nodeList;

    }

    public static class SDABean {

        private String sdaId;

        private String structName;

        private String structAlias;

        private String metadataId;

        private int seq = 0;

        private String parentId;

        private String serviceId;

        private String optUser;

        private String optDate;

        private String operationId;

        private String desc;

        private String remark;

        private String headId;

        private String version;

        private String type;

        private String length;

        private String required;

        private String argType;

        private String constraint;

        private String xpath;

        public SDABean(SDA sda){
            setSdaId(sda.getId());
            setStructName(sda.getStructName());
            setStructAlias(sda.getStructAlias());
            setMetadataId(sda.getMetadataId());
            setSeq(sda.getSeq());
            setParentId(sda.getParentId());
            setServiceId(sda.getServiceId());
            setOptUser(sda.getOptUser());
            setOptDate(sda.getOptDate());
            setOperationId(sda.getOperationId());
            setDesc(sda.getDesc());
            setRemark(sda.getRemark());
            setHeadId(sda.getHeadId());
            setVersion(sda.getVersion());
            setType(sda.getType());
            setLength(sda.getLength());
            setRequired(sda.getRequired());
            setArgType(sda.getArgType());
            setConstraint(sda.getConstraint());
            setXpath(sda.getXpath());

        }

        public String getSdaId() {
            return sdaId;
        }

        public void setSdaId(String sdaId) {
            this.sdaId = sdaId;
        }

        public String getStructName() {
            return structName;
        }

        public void setStructName(String structName) {
            this.structName = structName;
        }

        public String getStructAlias() {
            return structAlias;
        }

        public void setStructAlias(String structAlias) {
            this.structAlias = structAlias;
        }

        public String getMetadataId() {
            return metadataId;
        }

        public void setMetadataId(String metadataId) {
            this.metadataId = metadataId;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getServiceId() {
            return serviceId;
        }

        public void setServiceId(String serviceId) {
            this.serviceId = serviceId;
        }

        public String getOptUser() {
            return optUser;
        }

        public void setOptUser(String optUser) {
            this.optUser = optUser;
        }

        public String getOptDate() {
            return optDate;
        }

        public void setOptDate(String optDate) {
            this.optDate = optDate;
        }

        public String getOperationId() {
            return operationId;
        }

        public void setOperationId(String operationId) {
            this.operationId = operationId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHeadId() {
            return headId;
        }

        public void setHeadId(String headId) {
            this.headId = headId;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getRequired() {
            return required;
        }

        public void setRequired(String required) {
            this.required = required;
        }

        public String getArgType() {
            return argType;
        }

        public void setArgType(String argType) {
            this.argType = argType;
        }

        public String getConstraint() {
            return constraint;
        }

        public void setConstraint(String constraint) {
            this.constraint = constraint;
        }

        public String getXpath() {
            return xpath;
        }

        public void setXpath(String xpath) {
            this.xpath = xpath;
        }
    }

    public String save(SDA[] sdas) {
        String logParam = "SDA:";
        if (sdas != null && sdas.length > 0) {
            for (SDA sda : sdas) {
                //TODO TZB类型和长度合并了
                String type = sda.getType();
                type.replaceAll("（","(").replaceAll("）",")");
                if(type.indexOf("(")>=0){
                    sda.setType(type.split("[()]+")[0]);
                    sda.setLength(type.split("[()]+")[1]);
                }
                sda.setOptDate(DateUtils.format(new Date()));
                //TODO TZB元数据修改对应sda的structName修改,长度，类型，精度
                sda.setStructName(sda.getMetadataId());
                Metadata metadata = metadataService.getById(sda.getMetadataId());
                sda.setType(metadata.getType());
                sda.setLength(metadata.getLength());
                sda.setStructAlias(metadata.getChineseName());
                sdaDAO.save(sda);
                logParam += "[服务ID:" + sda.getServiceId() + ", 场景ID:" + sda.getOperationId() + ", SDA名称:" + sda.getStructName() + "],";
            }
            operationService.editReleate(sdas[0].getServiceId(), sdas[0].getOperationId());
        }

        return logParam.substring(0, logParam.length() -2 );
    }

    public String saveHeadSDA(SDA[] sdas){
        String logParam = "SDA:";
        if (sdas != null && sdas.length > 0) {
            for (SDA sda : sdas) {
                //TODO TZB类型和长度合并了
                String type = sda.getType();
                type.replaceAll("（","(").replaceAll("）",")");
                if(type.indexOf("(")>=0){
                    sda.setType(type.split("[()]+")[0]);
                    sda.setLength(type.split("[()]+")[1]);
                }
                sda.setOptDate(DateUtils.format(new Date()));
                //TODO TZB元数据修改对应sda的structName修改,长度，类型，精度
                sda.setStructName(sda.getMetadataId());
                Metadata metadata = metadataService.getById(sda.getMetadataId());
                sda.setType(metadata.getType());
                sda.setLength(metadata.getLength());
                sda.setStructAlias(metadata.getChineseName());
                sdaDAO.save(sda);
                logParam += "[报文头ID:" + sda.getHeadId()+ ", SDA名称:" + sda.getStructName() + "],";
            }
        }

        return logParam.substring(0, logParam.length() -2 );
    }

    public String delete(String[] delIds) {
        String logParam = "SDA:";
        if (delIds != null && delIds.length > 0) {
            SDA sda = sdaDAO.findUniqueBy("sdaId", delIds[0]);
            if(sda != null){
                operationService.editReleate(sda.getServiceId(), sda.getOperationId());
                for (String id : delIds) {
                    SDA entity = sdaDAO.findUniqueBy("sdaId", id);
                    if(entity != null){
                        logParam += "[服务ID：" + entity.getServiceId() +", 场景ID:" + entity.getOperationId() + ", SDA:" + entity.getStructName() + "],";
                    }
                    sdaDAO.delete(id);

                }
            }
        }
        return logParam.substring(0, logParam.length() - 2);
    }
    /**
     * 将一个节点上移
     */
    public boolean moveUp(String sdaId) {
        SDA sda = sdaDAO.findUnique(" from SDA where sdaId=?", sdaId);
        if(sda.getServiceId() != null && sda.getOperationId() != null){
            operationService.editReleate(sda.getServiceId(), sda.getOperationId());
        }
        String hql = " from SDA where parentId = ? order by seq asc";
        List<SDA> list = sdaDAO.find(hql, sda.getParentId());//查询兄弟节点
        int position = list.indexOf(sda);
        if(position == 0){
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            SDA node = list.get(i);
            if ( i == position) {
                //于之前的节点seq互换
                SDA beforeSDA = list.get(i - 1);
                int seq = node.getSeq();
                sda.setSeq(beforeSDA.getSeq());
                beforeSDA.setSeq(seq);

                sdaDAO.save(sda);
                return true;
            }
            else if(i < position){
                node.setSeq(node.getSeq() - 1);//所有当前节点之前的节点，seq-1；
            }
        }

        return false;
    }

    public boolean moveDown(String sdaId) {
        SDA sda = sdaDAO.findUnique(" from SDA where sdaId=?", sdaId);
        if(sda.getServiceId() != null && sda.getOperationId() != null){
            operationService.editReleate(sda.getServiceId(), sda.getOperationId());
        }
        String hql = " from SDA where parentId = ? order by seq asc";
        List<SDA> list = sdaDAO.find(hql, sda.getParentId());//查询兄弟节点
        int position = list.indexOf(sda);
        if(position == (list.size()-1)){
            return false;
        }
        for (int i = list.size() -1; i >= 0; i--) {
            SDA node = list.get(i);
            if ( i == position) {
                //于之前的节点seq互换
                SDA beforeSDA = list.get(i + 1);
                int seq = node.getSeq();
                sda.setSeq(beforeSDA.getSeq());
                beforeSDA.setSeq(seq);

                sdaDAO.save(sda);
                return true;
            }
            else if(i > position){
                node.setSeq(node.getSeq() + 1);//所有当前节点之前的节点，seq-1；
            }
        }

        return false;
    }

    /**
     * 备份sda
     * @return
     */
    public void backUpSdaByCondition(Map<String, String> params, String autoId){
        List<SDA> sdaList = findBy(params);
        if(sdaList != null && sdaList.size() > 0){
            for(SDA sda : sdaList){
                SDAHis sdaHis = new SDAHis(sda, autoId);
                sdaHisService.save(sdaHis);
            }
        }
    }

    @Override
    public HibernateDAO<SDA, String> getDAO() {
        return sdaDAO;
    }

    public boolean deleteByOperationId(String OperationId,String serviceId){
        Map map = new HashMap();
        map.put("operationId",OperationId);
        map.put("serviceId",serviceId);
        List<SDA> list = sdaDAO.findBy(map);
        sdaDAO.delete(list);
        operationService.editReleate(serviceId, OperationId);
        return true;
    }
    public boolean deleteByOS(String serviceId, String operationId){//不做版本更新
       String hql = " delete from SDA where serviceId = ? and operationId = ?";
        sdaDAO.exeHql(hql, serviceId, operationId);
        return true;
    }

    public boolean deleteByHeadId(String headId){
        String hql = " delete from SDA where headId = ?";
        sdaDAO.exeHql(hql, headId);
        return true;
    }

    public boolean judgeCanModifyOperation(String serviceId, String OperationId){
        Map map = new HashMap();
        map.put("operationId",OperationId);
        map.put("serviceId",serviceId);
        Operation operation = operationService.findUniqueBy(map);
        if(Constants.Operation.OPT_STATE_UNAUDIT.equals(operation.getState()) || Constants.Operation.OPT_STATE_REVISE.equals(operation.getState())){
            return true;
        }
        return false;
    }

    //数组自动生成一个结尾sda
    public SDA genderArrayEnd(SDA sda){
        SDA endSda = new SDA();
        endSda.setId(UUID.randomUUID().toString());
        endSda.setStructAlias(sda.getStructAlias());
        endSda.setStructName(sda.getStructName());
        endSda.setRemark("end");
        endSda.setType(sda.getType());
        endSda.setXpath(sda.getXpath().substring(0, sda.getXpath().lastIndexOf("/")) + "/");
        sdaDAO.save(endSda);
        return endSda;
    }

}
