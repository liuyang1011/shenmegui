package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.SDADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.entity.SDAHis;
import com.dc.esb.servicegov.service.SDAService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.util.DateUtils;
import com.dc.esb.servicegov.util.EasyUiTreeUtil;
import com.dc.esb.servicegov.util.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
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
    public boolean genderSDAAuto(Operation operation){
        SDA sdaRoot = new SDA();
        sdaRoot.setSdaId(UUID.randomUUID().toString());
        sdaRoot.setStructName("root");
        sdaRoot.setStructAlias("根元素");
        sdaRoot.setSeq(0);
        sdaRoot.setServiceId(operation.getServiceId());
        sdaRoot.setOperationId(operation.getOperationId());
        sdaDAO.save(sdaRoot);

        SDA sdaReq = new SDA();
        sdaReq.setSdaId(UUID.randomUUID().toString());
        sdaReq.setStructName("request");
        sdaReq.setStructAlias("请求头");
        sdaReq.setSeq(1);
        sdaReq.setServiceId(operation.getServiceId());
        sdaReq.setOperationId(operation.getOperationId());
        sdaReq.setParentId(sdaRoot.getSdaId());
        sdaDAO.save(sdaReq);

        SDA sdaRes = new SDA();
        sdaRes.setSdaId(UUID.randomUUID().toString());
        sdaRes.setStructName("response");
        sdaRes.setStructAlias("响应头");
        sdaRes.setSeq(2);
        sdaRes.setServiceId(operation.getServiceId());
        sdaRes.setOperationId(operation.getOperationId());
        sdaRes.setParentId(sdaRoot.getSdaId());
        sdaDAO.save(sdaRes);
        return true;
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
        fields.put("append3", "length");
        fields.put("append4", "metadataId");
        fields.put("append5", "required");
        fields.put("append6", "remark");
        fields.put("attributes", "seq");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        List<TreeNode> nodeList = eUtil.convertTree(tempList, fields);
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

        public SDABean(SDA sda){
            setSdaId(sda.getSdaId());
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
    }

    public boolean save(SDA[] sdas) {
        if (sdas != null && sdas.length > 0) {
            for (SDA sda : sdas) {
                sda.setOptDate(DateUtils.format(new Date()));
                sdaDAO.save(sda);
            }
            return true;
        }
        return false;
    }

    public boolean delete(String[] delIds) {
        if (delIds != null && delIds.length > 0) {
            for (String id : delIds) {
                sdaDAO.delete(id);
            }
            return true;
        }
        return false;
    }

    public boolean moveUp(String sdaId) {
        SDA sda = sdaDAO.findUnique(" from SDA where sdaId=?", sdaId);

        List<SDA> list;
        if (sda.getParentId() == null) {
            String hql = " from SDA where parentId is null order by seq asc";
            list = sdaDAO.find(hql);
        } else {
            String hql = " from SDA where parentId = ? order by seq asc";
            list = sdaDAO.find(hql, sda.getParentId());
        }

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSdaId().equals(sdaId)) {
                    if (i == 0) {
                        return false;
                    } else {
                        SDA beforeSDA = list.get(i - 1);
                        int seq = beforeSDA.getSeq();
                        seq --;
                        sda.setSeq(seq );
                        sdaDAO.save(sda);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean moveDown(String sdaId) {
        SDA sda = sdaDAO.findUnique(" from SDA where sdaId=?", sdaId);

        List<SDA> list;
        if (sda.getParentId() == null) {
            String hql = " from SDA where parentId is null order by seq asc";
            list = sdaDAO.find(hql);
        } else {
            String hql = " from SDA where parentId = ? order by seq asc";
            list = sdaDAO.find(hql, sda.getParentId());
        }

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSdaId().equals(sdaId)) {
                    if (i == (list.size() - 1)) {
                        return false;
                    } else {
                        SDA nextSda = list.get(i + 1);
                        int seq = nextSda.getSeq();
                        seq ++;
                        sda.setSeq(seq );
                        sdaDAO.save(sda);
                        return true;
                    }
                }
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
        return true;
    }
}
