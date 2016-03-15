package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.EsbDAOImpl;
import com.dc.esb.servicegov.dao.impl.InterfaceInvokeDAOImpl;
import com.dc.esb.servicegov.dao.impl.OperationDAOImpl;
import com.dc.esb.servicegov.dao.impl.ServiceDAOImpl;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.IpExpVO;
import com.dc.esb.servicegov.vo.OperationExpVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/15.
 */
@Service
@Transactional
public class IpSearchServiceImpl {
    @Autowired
    private EsbDAOImpl esbDAO;
    @Autowired
    private InterfaceInvokeDAOImpl interfaceInvokeDAO;
    @Autowired
    private ServiceDAOImpl serviceDAO;
    @Autowired
    private OperationDAOImpl operationDAO;

    public String getIpInfo(String serviceId, String operationId, String systemAb){
        String sql = "";//从esb数据库中查询ip信息
        List list = esbDAO.exeSQL(sql);
        return "";
    }

    public String genderQueryHql(Map<String, String[]> values){
        String hql = "";
        if (values != null && values.size() > 0) {
            for (String key : values.keySet()) {
                if (key.equals("serviceId") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        hql += " and ii.provider.serviceId like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if (key.equals("operationId") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        hql += " and ii.provider.operationId like '%" + values.get(key)[0] + "%' ";
                    }
                }
                if (key.equals("providerId") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        hql += " and ii.provider.systemId like '%" + values.get(key)[0] + "%' ";
                        hql += " and ii.provider.type like '%" + Constants.INVOKE_TYPE_PROVIDER + "%' ";

                    }
                }
                if (key.equals("consumerId") && values.get(key) != null && values.get(key).length > 0) {
                    if (StringUtils.isNotEmpty(values.get(key)[0])) {
                        if (StringUtils.isNotEmpty(values.get(key)[0])) {
                            hql += " and ii.consumer.systemId like '%" + values.get(key)[0] + "%' ";
                            hql += " and ii.consumer.type like '%" +Constants.INVOKE_TYPE_CONSUMER+ "%' ";

                        }
                    }
                }

            }
        }
        return hql;
    }
    public long queryCount(Map<String, String[]> values){
        String hql = "from InterfaceInvoke as ii where 1=1 ";
        hql += genderQueryHql(values);
        return interfaceInvokeDAO.find(hql).size();
    }
    public List<IpExpVO> queryByCondition(Map<String, String[]> values, Page page){
        List<IpExpVO> voList = new ArrayList<IpExpVO>();
        String hql = "from InterfaceInvoke as ii where 1=1 ";
        hql += genderQueryHql(values);
        List<InterfaceInvoke> list = interfaceInvokeDAO.findFree(hql);
        if(null != list && 0 < list.size()){
            for(InterfaceInvoke interfaceInvoke : list){
                if(null !=interfaceInvoke.getProvider()){
                    IpExpVO ipExpVO = new IpExpVO();
                    String serviceId = interfaceInvoke.getProvider().getServiceId();
                    String operationId = interfaceInvoke.getProvider().getOperationId();

                    ipExpVO.setServiceId(serviceId);
                    ipExpVO.setOperationId(operationId);

                    com.dc.esb.servicegov.entity.Service service = serviceDAO.get(serviceId);
                    if(null != service){
                        ipExpVO.setServiceName(service.getServiceName());//服务名称
                    }
                    Operation operation = operationDAO.getBySO(serviceId, operationId);
                    if(null != operation){
                        ipExpVO.setOperationName(operation.getOperationName());//场景名称
                    }

                    com.dc.esb.servicegov.entity.System proSystem = interfaceInvoke.getProvider().getSystem();
                    if(null != proSystem){
                        ipExpVO.setProvider(proSystem.getSystemChineseName());//提供方系统
                        String proIp = getIpInfo(serviceId, operationId, proSystem.getSystemAb());//提供方ip
                        ipExpVO.setConIp(proIp);
                    }

                    if(null != interfaceInvoke.getConsumer()){
                        com.dc.esb.servicegov.entity.System conSystem = interfaceInvoke.getConsumer().getSystem();
                        ipExpVO.setConsumer(conSystem.getSystemChineseName());//调用方系统
                        String conIp = getIpInfo(serviceId, operationId, conSystem.getSystemAb());//调用方ip
                        ipExpVO.setConIp(conIp);
                    }
                    voList.add(ipExpVO);
                }
            }
        }
        return voList;
    }


}
