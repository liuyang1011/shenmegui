package com.dc.esb.servicegov.service.impl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.dc.esb.servicegov.dao.impl.OperationDAOImpl;
import com.dc.esb.servicegov.dao.impl.OperationHisDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.jsonObj.ServiceInvokeJson;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.service.support.Constants;

import com.dc.esb.servicegov.vo.OperationHisVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dc.esb.servicegov.dao.impl.BaseLineDAOImpl;
import com.dc.esb.servicegov.util.DateUtils;

@Service
@Transactional
public class BaseLineServiceImpl extends AbstractBaseService<BaseLine, String> {
    @Autowired
    private BaseLineDAOImpl baseLineDAO;
    @Autowired
    private VersionServiceImpl versionServiceImpl;
    @Autowired
    private VersionHisServiceImpl versionHisServiceImpl;
    @Autowired
    private OperationServiceImpl operationServiceImpl;
    @Autowired
    private OperationHisServiceImpl operationHisServiceImpl;
    @Autowired
    private BVServiceImpl bvServiceImpl;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeServiceImpl;
    @Autowired
    private OperationHisDAOImpl operationHisDAO;
    @Autowired
    private OperationDAOImpl operationDAO;

    public boolean release(HttpServletRequest req, String code, String blDesc, String versionHisIds) {
        //新建基线
        BaseLine bl = new BaseLine();
        bl.setBaseId(UUID.randomUUID().toString());
        bl.setCode(code);
        bl.setBlDesc(blDesc);
        bl.setOptDate(DateUtils.format(new Date()));
        bl.setOptUser(SecurityUtils.getSubject().getPrincipal().toString());
        String versionId = versionServiceImpl.addVersion(Constants.Version.TARGET_TYPE_BASELINE, bl.getBaseId(), Constants.Version.TARGET_TYPE_BASELINE);
        bl.setVersionId(versionId);
        baseLineDAO.save(bl);

        //保存基线版本关系
        if (!StringUtils.isEmpty(versionHisIds)) {
            String[] vids = versionHisIds.split("\\,");
            for (String versionHisId : vids) {
                BaseLineVersionHisMapping bvm = new BaseLineVersionHisMapping();
                bvm.setBaseLineId(bl.getBaseId());
                bvm.setVersionHisId(versionHisId);
                bvServiceImpl.save(bvm);
                //TODO 更改versionHis的基线版本号和type(基线版本号根据BaseLineVersionHisMapping查找基线）
                VersionHis vh = versionHisServiceImpl.getById(versionHisId);
//                vh.setType(Constants.Version.TYPE_BASELINE);
                //更新服务状态为上线
                List<OperationHis> operationHises = operationHisDAO.findBy("versionHisId", versionHisId);
                for (OperationHis operationHis : operationHises) {
                    String serviceId = operationHis.getServiceId();
                    String operationId = operationHis.getOperationId();
                    Operation operation = operationServiceImpl.getOperation(serviceId, operationId);
                    if (null != operation) {
                        operation.setState(Constants.Operation.LIFE_CYCLE_STATE_ONLINE);
                        operationServiceImpl.save(operation);
                    }

                }
            }
            //TODO versionHis的type到底是什么
            versionHisServiceImpl.updateVerionHis(Constants.Version.TARGET_TYPE_BASELINE, vids);
        }


        return true;
    }

    public List<BaseLine> getBaseLine(String code, String blDesc) {

        return baseLineDAO.getBaseLine(code, blDesc);
    }

    public List<?> getBLOperationHiss(String baseId) {
        return operationHisServiceImpl.getBLOperationHiss(baseId);
    }

    /**
     * 版本公告服务规范定义列表与服务接口映射列表数据合并
     * @param baseId
     * @return
     */
    public List<?> getOneRow(String baseId,BaseLineServiceImpl baseLineServiceImpl){
        List<OperationHis> operationHises=(List<OperationHis>)baseLineServiceImpl.getBLOperationHiss(baseId);
        List<ServiceInvokeJson> serviceInvokeJsons=(List<ServiceInvokeJson>)serviceInvokeServiceImpl.getBLInvoke(baseId);
        List<OperationHisVO> operationHisVOList=new ArrayList<OperationHisVO>();
        for(OperationHis op:operationHises){
            OperationHisVO opVO=new OperationHisVO();
            String opServiceId=op.getServiceId();
            for(ServiceInvokeJson se:serviceInvokeJsons){
                String seServiceId=se.getServiceId();
                if(opServiceId.equals(seServiceId)){
                    opVO.setService(op.getService());
                    opVO.setOperationId(op.getOperationId());
                    opVO.setOperationName(op.getOperationName());
                    opVO.setOperationDesc(op.getOperationDesc());
                    opVO.setVersionHis(op.getVersionHis());
                    opVO.setAutoId(op.getVersionHis().getAutoId());
                    opVO.setTargetId(op.getAutoId());
                    if("1".equals(se.getType())){
                        //消费者
                        opVO.setCustomer(se.getSystemChineseName());
                    }
                    if("0".equals(se.getType())){
                        //提供者
                        opVO.setPrivater(se.getSystemChineseName());
                    }

                }
            }
            operationHisVOList.add(opVO);
        }
        //获取最新版本的内容
        return operationHisVOList;
    }

   public List<OperationHisVO> getColorOneRow(List<OperationHisVO> baseLine0,List<OperationHisVO> baseLine){
       List<OperationHisVO> operationHisVOList=new ArrayList<OperationHisVO>();
       //确定是否是最新的版本
       if(baseLine.containsAll(baseLine0)){
           operationHisVOList.addAll(baseLine);
           return operationHisVOList;
       }else{
           //判断新增或修改的服务内容
           StringBuilder compareStr=new StringBuilder("");
           for(int i=0;i<baseLine0.size();i++){
               OperationHisVO vo=(OperationHisVO)baseLine0.get(i);
               compareStr.append(vo.getService().getServiceId()+vo.getOperationId());
               compareStr.append("|");
               compareStr.append(vo.getVersionHis().getCode());
               compareStr.append(",");
           }
           for(OperationHisVO vo:baseLine){
               String unineBaseLine=vo.getService().getServiceId()+vo.getOperationId();
                int i=compareStr.indexOf(unineBaseLine);
               if(i==-1){
                   vo.setColorType("green");
               }else{
                    String oneRow=compareStr.substring(i,compareStr.indexOf(",",i+1));
                   int ii=oneRow.indexOf("|");
                   String code=oneRow.substring(ii+1);
                   if(!code.equals(vo.getVersionHis().getCode())){
                       vo.setColorType("yellow");
                   }

               }
           }
            operationHisVOList.addAll(baseLine);

       }

        return operationHisVOList;
   }
    @Override
    public HibernateDAO<BaseLine, String> getDAO() {
        return baseLineDAO;
    }
}
