package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.SystemSLADAOImpl;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.SLA;
import com.dc.esb.servicegov.entity.SystemSLA;
import com.dc.esb.servicegov.service.impl.SLAServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemSLAServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by Administrator on 2016/3/8 0008.
 */
@Controller
@RequestMapping("/systemSla")
public class SystemSLAController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private SystemSLAServiceImpl systemSLAService;
    @Autowired
    private SLAServiceImpl slaServiceImpl;

    /**
     * 获取系统级的SLA协议
     * @param systemId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getAll/{systemId}", headers = "Accept=application/json")
    public @ResponseBody
    List<SystemSLA> getSystemAll(@PathVariable("systemId") String systemId) {
        Map<String,String> param=new HashMap<String,String>();
        param.put("systemId",systemId);
        List<SystemSLA> sla=systemSLAService.findBy(param);
        return sla;
    }

    /**
     * 判断slaName是否唯一
     * @param req
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/uniqueValid")
    public @ResponseBody
    boolean uniqueValid(HttpServletRequest req) {
        String slaName=req.getParameter("slaName");
        String systemId=req.getParameter("systemId");
        Map<String,String> param=new HashMap<String,String>();
        param.put("slaName",slaName);
        param.put("systemId", systemId);
        List<SystemSLA> sals=systemSLAService.findBy(param);
        if(sals.size()==0||sals==null){
            return true;
        }
        return false;
    }

    /**
     * 添加系统级的SLA
     * @param list
     * @param systemId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add/{systemId}")
    public @ResponseBody
    boolean add(@RequestBody List list,@PathVariable("systemId") String systemId) {
        OperationLog operationLog = systemLogService.record("SLA", "添加", "");
        String logParam = "元素：";
        try {
            for (int i = 0; i < list.size(); i++) {
                LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
                SystemSLA sla = new SystemSLA();
                sla.setSlaName(map.get("slaName"));
                sla.setSlaValue(map.get("slaValue"));
                sla.setSlaDesc(map.get("slaDesc"));
                sla.setSlaRemark(map.get("slaRemark"));
                sla.setSystemId(systemId);
                Map<String,String> param=new HashMap<String, String>();
                param.put("slaId", map.get("slaId"));
                List<SystemSLA> slas=systemSLAService.findBy(param);
                if(slas.size()==0||slas==null){
                    systemSLAService.save(sla);
                }else{
                    SystemSLA updateSla=slas.get(0);
                    updateSla.setSlaDesc(sla.getSlaDesc());
                    updateSla.setSlaName(sla.getSlaName());
                    updateSla.setSlaValue(sla.getSlaValue());
                    updateSla.setSlaRemark(sla.getSlaRemark());
                    systemSLAService.update(updateSla);
                }
                logParam += sla.getSlaName() + ",";
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        operationLog.setParams(logParam.substring(0, logParam.length() - 2));
        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public @ResponseBody
    boolean delete(@RequestBody SystemSLA sla) {
        OperationLog operationLog = systemLogService.record("SLA","删除","");
        try{
            systemSLAService.delete(sla);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        systemLogService.updateResult(operationLog);
        return true;
    }

    /**
     * 设置SYSTEM的模板关联
     * @param systemId
     * @param slaTemplateId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/setTemplateData/{slaTemplateId}/{systemId}", headers = "Accept=application/json")
    public @ResponseBody
    boolean setTemplateData(@PathVariable("systemId") String systemId,@PathVariable("slaTemplateId") String slaTemplateId) {
        //查询模版sla数据
        Map<String,String> map = new HashMap<String, String>();
        map.put("slaTemplateId",slaTemplateId);
        List<SLA> templateList = slaServiceImpl.getTemplateSLABy(map);
        //设置模板内容到system
        for(int i=0;i<templateList.size();i++){
            Map<String,String> params=new HashMap<String,String>();
            params.put("slaName", templateList.get(i).getSlaName());
            params.put("systemId", systemId);
            List<SystemSLA> sals=systemSLAService.findBy(params);
            if(sals.size()==0){
                SystemSLA sla = new SystemSLA();
                sla.setSlaName( templateList.get(i).getSlaName());
                sla.setSlaValue( templateList.get(i).getSlaValue());
                sla.setSlaDesc( templateList.get(i).getSlaDesc());
                sla.setSlaRemark( templateList.get(i).getSlaRemark());
                sla.setSystemId(systemId);
                systemSLAService.save(sla);
            }

        }

        return true;
    }

    /**
     * SLA指标
     * @param templateId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getSLA/{templateId}", headers = "Accept=application/json")
    public @ResponseBody
    List<SLA> getSLA(@PathVariable(value = "templateId") String templateId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("slaTemplateId", templateId);
        return slaServiceImpl.getTemplateSLA(params);
    }

}
