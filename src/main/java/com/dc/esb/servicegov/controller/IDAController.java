package com.dc.esb.servicegov.controller;

import java.util.*;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.io.SAXEventRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.crypto.spec.OAEPParameterSpec;

@Controller
@RequestMapping("/ida")
public class IDAController {
	@Autowired
	private SystemLogServiceImpl systemLogService;

	@Autowired
	IdaService idaService;
	@Autowired
	SystemService systemService;
	@Autowired
	ServiceServiceImpl serviceService;
	@Autowired
	InterfaceService interfaceService;
	@Autowired
	OperationServiceImpl operationService;
	@Autowired
	SDAServiceImpl sdaService;

	@RequiresPermissions({"system-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/getHeads/{headId}", headers = "Accept=application/json")
	public @ResponseBody
	Map<String,Object> getHeads(@PathVariable String headId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("headId", headId);
		List<Ida> idas = idaService.findBy(reqMap, "seq");
		for(Ida ida:idas){
			ida.setHeads(null);
		}
		map.put("total", idas.size());
		map.put("rows", idas);
		return map;
	}

//	@RequiresPermissions({"system-get"})
	@RequiresPermissions({"ida-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/getInterfaces/{interfaceId}", headers = "Accept=application/json")
	public @ResponseBody
	Map<String,Object> getInterfaces(@PathVariable String interfaceId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("interfaceId", interfaceId);
		reqMap.put("state", Constants.IDA_STATE_COMMON);
//		List<Ida> idas = idaService.findBy(reqMap, "seq");
		//TODO 空和0都要显示
		String hql = "from Ida t where t.interfaceId = '"+interfaceId+"'";
		hql += " and (t.state = '"+Constants.IDA_STATE_COMMON+"' or t.state is null) order by seq";

		List<Ida> idas = idaService.findBy(hql);
		for(Ida ida:idas){
			ida.setHeads(null);
		}
		map.put("total", idas.size());
		map.put("rows", idas);
		return map;
	}

//	@RequiresPermissions({"system-add"})
	@RequiresPermissions({"ida-add"})
	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	boolean save(@RequestBody
	Ida [] idas) {
		OperationLog operationLog = systemLogService.record("IDA","批量保存","" );
		String logParam = "列表:";
		for(int i=0; i < idas.length; i++){
			logParam += "[接口ID:" + idas[i].getInterfaceId() + ",字段名称：" +idas[i].getStructName() + "],";
		}

		idaService.saveOrUpdate(idas);

		operationLog.setParams(logParam.substring(0, logParam.length() - 2));
		systemLogService.updateResult(operationLog);
		return true;
	}
	@RequiresPermissions({"ida-add"})
	@RequestMapping(method = RequestMethod.POST, value = "/headAdd", headers = "Accept=application/json")
	public @ResponseBody
	boolean headAdd(@RequestBody
				 Ida [] idas) {
		OperationLog operationLog = systemLogService.record("IDA","批量保存","" );
		String logParam = "列表:";

		idaService.saveOrUpdate(idas);

		for(int i=0; i < idas.length; i++){
			logParam += "[报文头:" + idas[i].getHeads().getHeadName() + ",字段名称：" +idas[i].getStructName() + "],";
		}
		operationLog.setParams(logParam.substring(0, logParam.length() - 2));
		systemLogService.updateResult(operationLog);
		return true;
	}

	/**
	 * ida的structName唯一性验证
	 *
	 * @param structName
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/uniqueValid", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean uniqueValid(String structName,String headId) {
		return idaService.uniqueValid(structName,headId);
	}

//	@RequiresPermissions({"system-delete"})
	@RequiresPermissions({"ida-delete"})
	@RequestMapping(method = RequestMethod.POST, value = "/delete", headers = "Accept=application/json")
	public @ResponseBody
	boolean delete(@RequestBody
	String [] ids) {
		OperationLog operationLog = systemLogService.record("IDA","批量删除","删除ida数：" + ids.length );

		idaService.deletes(ids);

		systemLogService.updateResult(operationLog);
		return true;
	}

	/**
	 * TODO不满足需求，不要
	 * @param id
	 * @param seq
	 * @param id2
	 * @param seq2
	 * @return
	 */
//	@RequiresPermissions({"system-update"})
	@RequiresPermissions({"ida-update"})
	@RequestMapping(method = RequestMethod.GET, value = "/modifySEQ/{id}/{seq}/{id2}/{seq2}", headers = "Accept=application/json")
	public @ResponseBody
	boolean modifySEQ(@PathVariable
	String id,@PathVariable int seq,@PathVariable String id2,@PathVariable int seq2) {
		
		String hql = "update Ida set seq = ? where id=?";
//		idaService.exeHql(hql, seq,id);
//		idaService.exeHql(hql, seq2,id2);
		return true;
	}

	/**
	 * @param metadataId
	 * @param id
	 * @return
	 */
//	@RequiresPermissions({"system-update"})
	@RequiresPermissions({"ida-update"})
	@RequestMapping(method = RequestMethod.POST, value = "/updateMetadataId", headers = "Accept=application/json")
	public @ResponseBody boolean updateMetadataId( String metadataId, String id){
		OperationLog operationLog = systemLogService.record("IDA","更新元数据id","IDA ID:"+ id +"； 元数据id" + metadataId);

		boolean result = idaService.updateMetadataId(metadataId, id);

		systemLogService.updateResult(operationLog);
		return result;
	}

	@RequiresPermissions({"ida-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/idaMapping/{serviceId}/{operationId}/{interfaceId}/{systemId}", headers = "Accept=application/json")
	public @ResponseBody ModelAndView idaMapping( @PathVariable String serviceId, @PathVariable String operationId, @PathVariable String interfaceId, @PathVariable String systemId){
		Interface inter = interfaceService.getById(interfaceId);
		com.dc.esb.servicegov.entity.System system = systemService.getById(systemId);

		Service service = serviceService.getById(serviceId);
		Operation operation = operationService.getOperation(serviceId,operationId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("system", system);
		modelAndView.addObject("interface", inter);
		modelAndView.addObject("service", service);
		modelAndView.addObject("operation", operation);

		modelAndView.setViewName("service/operation/idaMapping");
		return modelAndView;
	}

//	@RequiresPermissions({"system-update"})
	@RequiresPermissions({"ida-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/getIdaMapping/{interfaceId}/{serviceId}/{operationId}", headers = "Accept=application/json")
	public @ResponseBody Map<String,Object> getIdaMapping(@PathVariable String interfaceId, @PathVariable String serviceId, @PathVariable String operationId){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("interfaceId", interfaceId);
		reqMap.put("state", Constants.IDA_STATE_COMMON);
		List<IdaServiceImpl.IdaMappingBean> list = idaService.findIdaMappingBy(reqMap, "seq", serviceId, operationId);
//		for(Ida ida:idas){
//			ida.setHeads(null);
//		}
		map.put("total", list.size());
		map.put("rows", list);
		return map;
	}

	@RequiresPermissions({"system-update"})
	@RequestMapping(method = RequestMethod.GET, value = "/getSdaMapping/{serviceId}/{operationId}", headers = "Accept=application/json")
	public @ResponseBody List<SDA> getSdaMapping( @PathVariable String serviceId, @PathVariable String operationId){
		List<SDA> sdaList = sdaService.getSDAListBySO(serviceId, operationId);
		for (int i = 0; i < sdaList.size(); i++) {
			SDA sda = sdaList.get(i);
			if(sda.getStructName().equalsIgnoreCase("root") || sda.getStructName().equalsIgnoreCase("request") || sda.getStructName().equalsIgnoreCase("response") || sda.getType().equalsIgnoreCase("ARRAY")){
				sdaList.remove(i);
			}
		}
		return sdaList;
	}

//	@RequiresPermissions({"system-update"})
	@RequiresPermissions({"ida-update"})
	@RequestMapping(method = RequestMethod.POST, value = "/saveIdaMapping", headers = "Accept=application/json")
	public @ResponseBody boolean saveIdaMapping(@RequestBody List list){
		OperationLog operationLog = systemLogService.record("IDA","保存映射关系","");
		String logParam = "; ida数量：" + list.size() ;
		String serviceId = "";
		String operationId = "";

		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
			String idaId = map.get("id");
			String sdaId = map.get("sdastructAlias");    //前台用sdastructAlias做field展示，选中节点后值变为sdaId
			SDA sda = sdaService.findUniqueBy("sdaId", sdaId);
			Ida ida = idaService.getById(idaId);
			ida.setSdaId(sdaId);
			if(sda != null){
				ida.setMetadataId(sda.getMetadataId());
				ida.setXpath(sda.getXpath());

				serviceId = sda.getServiceId();
				operationId = sda.getOperationId();

				if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){ //如果是数组，生成一个结束元素
					Ida arrayEndIda = new Ida();
					SDA arrayEndSda = sdaService.genderArrayEnd(sda);

					Map<String, String> params = new HashMap<String, String>();
					params.put("state", Constants.IDA_STATE_DISABLE);
					params.put("_parentId", ida.get_parentId());
					params.put("xpath", arrayEndSda.getXpath());
					List<Ida> endIdas = idaService.findBy(params);
					if(endIdas != null && endIdas.size() > 0 ){
						arrayEndIda = endIdas.get(0);
					}

					arrayEndIda.setSdaId(arrayEndSda.getSdaId());
					arrayEndIda.setXpath(arrayEndSda.getXpath());
					arrayEndIda.setInterfaceId(ida.getInterfaceId());
					arrayEndIda.setState(Constants.IDA_STATE_DISABLE);
					arrayEndIda.setSeq(ida.getSeq() + 1);
					arrayEndIda.set_parentId(ida.get_parentId());
					idaService.save(arrayEndIda);
				}
			}
			idaService.save(ida);


		}
		logParam = "服务ID:" + serviceId + "; 场景ID:" + operationId + logParam;
		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return true;
	}

//	@RequiresPermissions({"system-update"})
	@RequiresPermissions({"ida-delete"})
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteIdaMapping", headers = "Accept=application/json")
	public @ResponseBody boolean deleteIdaMapping(@RequestBody List list){
		OperationLog operationLog = systemLogService.record("IDA", "删除映射关系", "");
		String logParam = "; ida:" ;
		String serviceId = "";
		String operationId = "";
		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
			String idaId = map.get("id");
			Ida ida = idaService.getById(idaId);
			ida.setMetadataId(null);
			ida.setSdaId(null);
			ida.setXpath(null);
			idaService.save(ida);
			logParam += ida.getStructName() + ",";
			String sdaId = map.get("sdaId");
			SDA sda = sdaService.findUniqueBy("sdaId", sdaId);
			if(sda != null){
				serviceId = sda.getServiceId();
				operationId = sda.getOperationId();
			}
		}
		logParam = "服务ID:" + serviceId + "; 场景ID:" + operationId + logParam.substring(0, logParam.length() -2);
		operationLog.setParams(logParam.substring(0, logParam.length() - 2 ));
		systemLogService.updateResult(operationLog);
		return true;
	}

	@RequiresPermissions({"ida-update"})
	@RequestMapping("/moveUp")
	@ResponseBody
	public boolean moveUp(String id){
		OperationLog operationLog = systemLogService.record("IDA","位置上移","");
		String logParam = "列表:";
		Ida entity = idaService.findUniqueBy("id", id);
		if(entity != null){
			logParam += "[接口ID:" + entity.getInterfaceId() +", 字段名称：" +  entity.getStructName() + "],";
		}

		boolean result = idaService.moveUp(id);

		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return result;
	}

	@RequiresPermissions({"ida-update"})
	@RequestMapping("/moveDown")
	@ResponseBody
	public boolean moveDown(String id){
		OperationLog operationLog = systemLogService.record("IDA","位置下移","");
		String logParam = "列表:";
		Ida entity = idaService.findUniqueBy("id", id);
		if(entity != null){
			logParam += "[接口ID:" + entity.getInterfaceId() +", 字段名称：" +  entity.getStructName() + "],";
		}

		boolean result = idaService.moveDown(id);

		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return result;
	}

	@RequiresPermissions({"ida-update"})
	@RequestMapping("/headMoveUp")
	@ResponseBody
	public boolean headMoveUp(String id){
		OperationLog operationLog = systemLogService.record("IDA","位置上移","");
		String logParam = "列表:";
		Ida entity = idaService.findUniqueBy("id", id);
		if(entity != null){
			logParam += "[报文头:" + entity.getHeads().getHeadName() +", 字段名称：" +  entity.getStructName() + "],";
		}

		boolean result = idaService.moveUp(id);

		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return result;
	}

	@RequiresPermissions({"ida-update"})
	@RequestMapping("/headMoveDown")
	@ResponseBody
	public boolean headMoveDown(String id){
		OperationLog operationLog = systemLogService.record("IDA","位置下移","");
		String logParam = "列表:";
		Ida entity = idaService.findUniqueBy("id", id);
		if(entity != null){
			logParam += "[报文头:" + entity.getHeads().getHeadName() +", 字段名称：" +  entity.getStructName() + "],";
		}

		boolean result = idaService.moveDown(id);

		operationLog.setParams(logParam);
		systemLogService.updateResult(operationLog);
		return result;
	}
	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
