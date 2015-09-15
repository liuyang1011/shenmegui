package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.IdaService;
import com.dc.esb.servicegov.service.InterfaceService;
import com.dc.esb.servicegov.service.SystemService;
import com.dc.esb.servicegov.service.impl.*;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/idaHis")
public class IdaHisController {
	
	@Autowired
	IdaHISServiceImpl idaHISService;

	@RequestMapping(method = RequestMethod.GET, value = "/idaHisTree", headers = "Accept=application/json")
	public @ResponseBody
	Map<String,Object> getInterfaces(String interfaceHisId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> reqMap = new HashMap<String,String>();
		reqMap.put("interfaceHisId", interfaceHisId);
		List<IdaHIS> idaHISs = idaHISService.findBy(reqMap,"seq");
		for(IdaHIS idaHIS:idaHISs){
			idaHIS.setHeads(null);
		}
		map.put("total", idaHISs.size());
		map.put("rows", idaHISs);
		return map;
	}
	public String processUnauthorizedException() {
		return "403";
	}
}
