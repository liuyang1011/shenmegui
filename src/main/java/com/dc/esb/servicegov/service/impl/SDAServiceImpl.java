package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.SDADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.SDA;
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
    public boolean genderSDAAuto(Operation operation){
        SDA sdaRoot = new SDA();
        sdaRoot.setSdaId(UUID.randomUUID().toString());
        sdaRoot.setStructName("root");
        sdaRoot.setSeq("0");
        sdaRoot.setServiceId(operation.getServiceId());
        sdaRoot.setOperationId(operation.getOperationId());
        sdaDAO.save(sdaRoot);

        SDA sdaReq = new SDA();
        sdaReq.setSdaId(UUID.randomUUID().toString());
        sdaReq.setStructName("request");
        sdaReq.setSeq("0");
        sdaReq.setServiceId(operation.getServiceId());
        sdaReq.setOperationId(operation.getOperationId());
        sdaReq.setParentId(sdaRoot.getSdaId());
        sdaDAO.save(sdaReq);

        SDA sdaRes = new SDA();
        sdaRes.setSdaId(UUID.randomUUID().toString());
        sdaRes.setStructName("response");
        sdaRes.setSeq("0");
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
                " from SDA where operationId=? and serviceId=? order by seq desc",
                operationId, serviceId);
        return list;
    }

    public List<TreeNode> genderSDATree(String serviceId, String operationId) {
        List<SDA> list = getSDAListBySO(serviceId, operationId);
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "sdaId");
        fields.put("text", "structName");
        fields.put("append1", "structAlias");
        fields.put("append2", "desc");
        fields.put("append3", "remark");

        EasyUiTreeUtil eUtil = new EasyUiTreeUtil();

        return eUtil.convertTree(list, fields);

    }

    public boolean save(SDA[] sdas) {
        if (sdas != null && sdas.length > 0) {
            for (SDA sda : sdas) {
                sda.setOptDate(DateUtils.format(new Date()));
                if (sda.getSeq() == null) {
                    sda.setSeq("0");
                }
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
            String hql = " from SDA where parentId is null order by seq desc";
            list = sdaDAO.find(hql);
        } else {
            String hql = " from SDA where parentId = ? order by seq desc";
            list = sdaDAO.find(hql, sda.getParentId());
        }

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSdaId().equals(sdaId)) {
                    if (i == 0) {
                        return false;
                    } else {
                        SDA beforeSDA = list.get(i - 1);
                        String seq = beforeSDA.getSeq();
                        Integer newSeq = Integer.parseInt(seq) + 1;
                        sda.setSeq(String.valueOf(newSeq));
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
            String hql = " from SDA where parentId is null order by seq desc";
            list = sdaDAO.find(hql);
        } else {
            String hql = " from SDA where parentId = ? order by seq desc";
            list = sdaDAO.find(hql, sda.getParentId());
        }

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSdaId().equals(sdaId)) {
                    if (i == (list.size() - 1)) {
                        return false;
                    } else {
                        SDA nextSda = list.get(i + 1);
                        String seq = nextSda.getSeq();
                        Integer newSeq = Integer.parseInt(seq) - 1;
                        sda.setSeq(String.valueOf(newSeq));
                        sdaDAO.save(sda);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public HibernateDAO<SDA, String> getDAO() {
        return sdaDAO;
    }
}