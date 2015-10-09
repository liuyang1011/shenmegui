package com.dc.esb.servicegov.controller;

import java.util.*;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.*;
import com.dc.esb.servicegov.service.impl.IdaServiceImpl;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ida")
public class IDAController {
	
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

	@RequiresPermissions({"system-get"})
	@RequestMapping(method = RequestMethod.GET, value = "/getInterfaces/{interfaceId}", headers = "Accept=application/json")
	public @ResponseBody
	Map<String,Object> getInterfaces(@PathVariable String interfaceId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("interfaceId", interfaceId);
		List<Ida> idas = idaService.findBy(reqMap,"seq");
		for(Ida ida:idas){
			ida.setHeads(null);
		}
		map.put("total", idas.size());
		map.put("rows", idas);
		return map;
	}

	@RequiresPermissions({"system-add"})
	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	boolean save(@RequestBody
	Ida [] idas) {
		idaService.saveOrUpdate(idas);
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

	@RequiresPermissions({"system-delete"})
	@RequestMapping(method = RequestMethod.POST, value = "/delete", headers = "Accept=application/json")
	public @ResponseBody
	boolean delete(@RequestBody
	String [] ids) {
		idaService.deletes(ids);

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
	@RequiresPermissions({"system-update"})
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
	@RequiresPermissions({"system-update"})
	@RequestMapping(method = RequestMethod.POST, value = "/updateMetadataId", headers = "Accept=application/json")
	public @ResponseBody boolean updateMetadataId( String metadataId, String id){
		return idaService.updateMetadataId(metadataId, id);
	}

	@RequiresPermissions({"system-update"})
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

	@RequiresPermissions({"system-update"})
	@RequestMapping(method = RequestMethod.GET, value = "/getIdaMapping/{interfaceId}/{serviceId}/{operationId}", headers = "Accept=application/json")
	public @ResponseBody Map<String,Object> getIdaMapping(@PathVariable String interfaceId, @PathVariable String serviceId, @PathVariable String operationId){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("interfaceId", interfaceId);
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

	@RequiresPermissions({"system-update"})
	@RequestMapping(method = RequestMethod.POST, value = "/saveIdaMapping", headers = "Accept=application/json")
	public @ResponseBody boolean saveIdaMapping(@RequestBody List list){
		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
			String idaId = map.get("id");
			String metadaId = map.get("metadataId");
			Ida ida = idaService.getById(idaId);
			ida.setMetadataId(metadaId);
			idaService.save(ida);
		}
		return true;
	}

	@RequiresPermissions({"system-update"})
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteIdaMapping", headers = "Accept=application/json")
	public @ResponseBody boolean deleteIdaMapping(@RequestBody List list){
		for (int i = 0; i < list.size(); i++) {
			LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) list.get(i);
			String idaId = map.get("id");
			Ida ida = idaService.getById(idaId);
			ida.setMetadataId(null);
			idaService.save(ida);
		}
		return true;
	}

	@RequestMapping("/moveUp")
	@ResponseBody
	public boolean moveUp(String id){
		return idaService.moveUp(id);
	}

	@RequestMapping("/moveDown")
	@ResponseBody
	public boolean moveDown(String id){
		return idaService.moveDown(id);
	}
	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
