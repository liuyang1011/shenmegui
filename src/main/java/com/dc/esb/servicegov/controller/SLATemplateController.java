package com.dc.esb.servicegov.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dc.esb.servicegov.entity.SLA;
import com.dc.esb.servicegov.entity.SLATemplate;
import com.dc.esb.servicegov.service.impl.SLAServiceImpl;
import com.dc.esb.servicegov.service.impl.SLATemplateServiceImpl;

@Controller
@RequestMapping("/slaTemplate")
public class SLATemplateController {
	@Autowired
	private SystemLogServiceImpl systemLogService;

	@Autowired
	private SLATemplateServiceImpl slaTemplateServiceImpl;
	@Autowired
	private SLAServiceImpl slaServiceImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/getSLA/{templateId}", headers = "Accept=application/json")
	public @ResponseBody
	List<SLA> getSLA(@PathVariable(value = "templateId") String templateId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("slaTemplateId", templateId);
		return slaServiceImpl.getTemplateSLA(params);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
	public @ResponseBody
	List<SLATemplate> getAll() {
		return slaTemplateServiceImpl.getAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	   boolean save(@RequestBody List list) {
		OperationLog operationLog = systemLogService.record("SLA模板","批量保存元素","数量：" + list.size());

  	 for (int i = 0; i < list.size(); i++) {
           LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
           Set<String> keySet = map.keySet();
           SLATemplate slaTemplate=new SLATemplate();
           if(map.get("slaTemplateId")!=null&&!"".equals(map.get("slaTemplateId"))){
        	   slaTemplate.setSlaTemplateId(map.get("slaTemplateId"));
           }
           slaTemplate.setTemplateName(map.get("templateName"));
           slaTemplate.setDesc(map.get("desc"));
           slaTemplateServiceImpl.save(slaTemplate);
  	 }

		systemLogService.updateResult(operationLog);
      return true;
  }


	@RequestMapping(method = RequestMethod.DELETE, value = "/delete", headers = "Accept=application/json")
	public @ResponseBody
	boolean delete(@RequestBody List list) {
		OperationLog operationLog = systemLogService.record("SLA模板","批量删除元素","数量：" +  list.size());

        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
            Set<String> keySet = map.keySet();
            String id = map.get("slaTemplateId");
            slaTemplateServiceImpl.deleteById(id);
        }

		systemLogService.updateResult(operationLog);
        return true;
 
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addSla/{serviceId}/{operationId}/{slaTemplateId}", headers = "Accept=application/json")
	public @ResponseBody
	   boolean saveOla(@RequestBody List list,@PathVariable(value = "serviceId") String serviceId,
				@PathVariable(value = "operationId") String operationId,@PathVariable(value = "slaTemplateId") String slaTemplateId) {
		OperationLog operationLog = systemLogService.record("SLA模板","根据服务场景添加SLA模板","服务ID" + serviceId + "; 场景ID:" + operationId +"; SLA模板ID:" + slaTemplateId );

   	 for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
            Set<String> keySet = map.keySet();
           SLA sla=new SLA();
            if(map.get("slaId")!=null&&!"".equals(map.get("slaId"))){
            	sla.setSlaId(map.get("slaId"));
            }
//            sla.setOperationId(operationId);
//            sla.setServiceId(serviceId);
            sla.setSlaName(map.get("slaName"));
            sla.setSlaValue(map.get("slaValue"));
            sla.setSlaDesc(map.get("slaDesc"));
            sla.setSlaRemark(map.get("slaRemark"));
            sla.setSlaTemplateId(slaTemplateId);
            slaServiceImpl.saveTemplate(sla);
   	 }

		systemLogService.updateResult(operationLog);
       return true;
   }

	@RequestMapping(method = RequestMethod.GET, value = "/setTemplateData/{serviceId}/{operationId}/{slaTemplateId}", headers = "Accept=application/json")
	public @ResponseBody
	boolean setTemplateData(@PathVariable(value = "serviceId") String serviceId,
					@PathVariable(value = "operationId") String operationId,@PathVariable(value = "slaTemplateId") String slaTemplateId) {
		OperationLog operationLog = systemLogService.record("SLA模板","根据服务场景添加SLA模板","服务ID" + serviceId + "; 场景ID:" + operationId +"; SLA模板ID:" + slaTemplateId );

		//查询模版sla数据
		Map<String,String> map = new HashMap<String, String>();
		map.put("slaTemplateId",slaTemplateId);
		List<SLA> templateList = slaServiceImpl.getTemplateSLABy(map);

		//查询非模版sla数据
		Map<String, String> params = new HashMap<String, String>();
		params.put("serviceId", serviceId);
		params.put("operationId", operationId);
		List<SLA> slaList = slaServiceImpl.findBy(params);

		for (int i = 0; i < templateList.size(); i++) {
			SLA temp = templateList.get(i);
			boolean exist = false;
			for (int j = 0; j < slaList.size(); j++) {
				SLA sla = slaList.get(j);
				if(sla.getSlaName().equals(temp.getSlaName())){
					exist = true;
					sla.setSlaValue(temp.getSlaValue());
					sla.setSlaDesc(temp.getSlaDesc());
					sla.setSlaRemark(temp.getSlaRemark());
					slaServiceImpl.save(sla);
				}
			}
			if(!exist){
				SLA sla = new SLA();
				sla.setServiceId(serviceId);
				sla.setOperationId(operationId);
				sla.setSlaName(temp.getSlaName());
				sla.setSlaValue(temp.getSlaValue());
				sla.setSlaDesc(temp.getSlaDesc());
				sla.setSlaRemark(temp.getSlaRemark());
				slaServiceImpl.insert(sla);
			}
		}

		systemLogService.updateResult(operationLog);
		return true;
	}

	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
   }
}
