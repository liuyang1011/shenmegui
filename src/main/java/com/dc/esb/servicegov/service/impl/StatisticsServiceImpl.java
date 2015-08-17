package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.*;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.StatisticsService;
import com.dc.esb.servicegov.vo.ReleaseVO;
import com.dc.esb.servicegov.vo.ReuseRateVO;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            long serviceNum = getServiceRelaCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setServiceNum(String.valueOf(serviceNum));//关联服务数
            long sum = getServiceInvokeCount( String.valueOf(strs[1]));
            vo.setSum(String.valueOf(sum));//提供者或消费者被调用总数
            long useNum = getServiceInvokeCount(String.valueOf(strs[0]), String.valueOf(strs[1]));
            vo.setUseNum(String.valueOf(useNum));//当前系统作为提供者或消费者被调用次数
            if(useNum > 1){
                float r = (useNum - 1.0f)/sum;
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
}
