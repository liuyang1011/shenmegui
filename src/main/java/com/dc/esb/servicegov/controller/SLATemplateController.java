package com.dc.esb.servicegov.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.net.URLDecoder;
import java.util.Date;

import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
	@Autowired
	private OperationServiceImpl operationService;


	@RequestMapping(method = RequestMethod.GET, value = "/getSLA/{templateId}", headers = "Accept=application/json")
	public @ResponseBody
	List<SLA> getSLA(@PathVariable(value = "templateId") String templateId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("slaTemplateId", templateId);
		return slaServiceImpl.getTemplateSLA(params);
	}

	@RequiresPermissions({"slaTemp-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
	public @ResponseBody
	List<SLATemplate> getAll() {
		return slaTemplateServiceImpl.getAll();
	}

	@RequiresPermissions({"slaTemp-add"})
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
			Date date =new Date();
			String time=DateUtils.format(date);
			slaTemplate.setUpdateTime(time);
			slaTemplate.setUpdateUser(map.get("updateUser"));
			slaTemplate.setCreatUser(map.get("creatUser"));
			slaTemplate.setTemplateName(map.get("templateName"));
			slaTemplate.setTemplateNo(map.get("templateNo"));
			slaTemplate.setDesc(map.get("desc"));
			slaTemplateServiceImpl.save(slaTemplate);
		}

		systemLogService.updateResult(operationLog);
		return true;
	}



	@RequiresPermissions({"slaTemp-delete"})
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete", headers = "Accept=application/json")
	public @ResponseBody
	boolean delete(@RequestBody List list) {
		OperationLog operationLog = systemLogService.record("SLA模板", "删除SLA模板元素", "数量：" + list.size());

		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
			Set<String> keySet = map.keySet();
			String id = map.get("slaTemplateId");
			slaTemplateServiceImpl.deleteById(id);
			Map<String, String> params = new HashMap<String, String>();
			params.put("slaTemplateId", id);
			List<SLA> sla = slaServiceImpl.getTemplateSLA(params);
			if(sla!=null && sla.size()>0){
				for(int j=0;j<sla.size();j++){
					slaServiceImpl.deleteById(sla.get(j).getSlaId());
				}
			}
		}

		systemLogService.updateResult(operationLog);
		return true;

	}

	@RequiresPermissions({"slaTemp-update"})
	@RequestMapping(method = RequestMethod.POST, value = "/addSla/{serviceId}/{operationId}/{slaTemplateId}", headers = "Accept=application/json")
	public @ResponseBody
	boolean saveOla(@RequestBody List list,@PathVariable(value = "serviceId") String serviceId,
					@PathVariable(value = "operationId") String operationId,@PathVariable(value = "slaTemplateId") String slaTemplateId) {
		OperationLog operationLog = systemLogService.record("SLA模板","新增SLA模板元素","" );
		String logParam = "服务ID：" + serviceId + "， 场景ID:" + operationId  ;

		SLATemplate template = slaTemplateServiceImpl.findUniqueBy("slaTemplateId", slaTemplateId);
		if(template != null){
			logParam += "， SLA模板:" + template.getTemplateName() + "， 数量：" + list.size();
		}

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
			template.setUpdateTime(DateUtils.format(new Date()));
			template.setUpdateUser((String) SecurityUtils.getSubject().getPrincipal());
			slaTemplateServiceImpl.save(template);
		}
		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return true;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/setTemplateData/{serviceId}/{operationId}/{slaTemplateId}", headers = "Accept=application/json")
	public @ResponseBody
	boolean setTemplateData(@PathVariable(value = "serviceId") String serviceId,
							@PathVariable(value = "operationId") String operationId,@PathVariable(value = "slaTemplateId") String slaTemplateId) {
		OperationLog operationLog = systemLogService.record("SLA模板","新增SLA模板元素","服务ID" + serviceId + "; 场景ID:" + operationId +"; SLA模板ID:" + slaTemplateId );
		String logParam = "服务ID：" + serviceId + "， 场景ID:" + operationId  ;

		SLATemplate template = slaTemplateServiceImpl.findUniqueBy("slaTemplateId", slaTemplateId);
		if(template != null){
			logParam += "， SLA模板:" + template.getTemplateName() ;
		}
		//查询模版sla数据
		Map<String,String> map = new HashMap<String, String>();
		map.put("slaTemplateId",slaTemplateId);
		List<SLA> templateList = slaServiceImpl.getTemplateSLABy(map);
		logParam += "， 数量：" + templateList.size();

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
		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return true;
	}


	@RequestMapping(method = RequestMethod.POST, value = "/relateAll/{slaTemplateId}", headers = "Accept=application/json")
	public @ResponseBody
	boolean setTemplateData(@PathVariable(value = "slaTemplateId") String slaTemplateId){
		String hql = " from SLA where slaTemplateId = ?";
		List<SLA> slaList = slaServiceImpl.find(hql, slaTemplateId);
		for(int i=0; i < slaList.size(); i++){
			SLA temSla = slaList.get(i);
			List<Operation> operationList = operationService.getAll();
			for(int j=0; j < operationList.size(); j++){
				Operation operation = operationList.get(j);
				String hql2 = " from SLA where operationId=? and serviceId=? and slaName=?";
				List<SLA> list = slaServiceImpl.find(hql2, operation.getOperationId(), operation.getServiceId(), temSla.getSlaName());
				if(list != null && list.size() > 0){
					continue;
				}else{
					SLA sla = new SLA(temSla);
					sla.setOperationId(operation.getOperationId());
					sla.setServiceId(operation.getServiceId());
					slaServiceImpl.save(sla);
				}
			}
		}
		return true;
	}


	@RequestMapping(method = RequestMethod.GET, value = "/uniqueValid1", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean uniqueValid1(String slaTemplate) {
		return slaTemplateServiceImpl.uniqueValid1(slaTemplate);
	}




	@RequestMapping(method = RequestMethod.GET, value = "/uniqueValid2", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean uniqueValid2(String slaTemplate2,String slaTemplateId) {
		try {
			slaTemplate2 = URLDecoder.decode(URLDecoder.decode(slaTemplate2, "utf-8"),"utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return slaServiceImpl.uniqueValid2(slaTemplate2, slaTemplateId);
	}





	@RequestMapping(method = RequestMethod.GET, value = "/uniqueValid3", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean uniqueValid3(String slaTemplate) {
		try {
			slaTemplate = URLDecoder.decode(URLDecoder.decode(slaTemplate, "utf-8"),"utf-8");
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return slaTemplateServiceImpl.uniqueValid3(slaTemplate);
	}
	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
