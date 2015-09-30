package com.dc.esb.servicegov.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.dc.esb.servicegov.entity.SDA;
import com.dc.esb.servicegov.service.impl.SDAServiceImpl;
import com.dc.esb.servicegov.util.TreeNode;

@Controller
@RequestMapping("sda")
public class SDAController {
	@Autowired
	SDAServiceImpl serviceImpl;

	@RequiresPermissions({"service-get"})
	//根据serviceId,operationId获取服务数据协议信息
	@RequestMapping("/sdaPage")
	public ModelAndView sdaPage(String operationId, String serviceId, HttpServletRequest req){
		return serviceImpl.sdaPage(operationId, serviceId, req);
	}

	@RequiresPermissions({"service-get"})
	//根据serviceId，operationId获取sda树
	@RequestMapping("/sdaTree")
	@ResponseBody
	public List<TreeNode> getSDATree(String serviceId, String operationId){
		return serviceImpl.genderSDATree(serviceId, operationId);
	}

	@RequiresPermissions({"service-get"})
	//生成一个sdaId
	@RequestMapping("/genderSDAUuid")
	@ResponseBody
	public String genderSDAUuid(){
		String result = UUID.randomUUID().toString();
		return result;
	}

	@RequiresPermissions({"service-update"})
	//保存对象数组
	@RequestMapping(method = RequestMethod.POST, value = "/saveSDA", headers = "Accept=application/json")
	@ResponseBody
	public boolean saveSDA(@RequestBody SDA[] sdas){
		//判断场景状态是否为服务定义或修订
		boolean canModifyOperation = serviceImpl.judgeCanModifyOperation(sdas[0].getServiceId(), sdas[0].getOperationId());
		if(!canModifyOperation){
			return false;
		}
		return serviceImpl.save(sdas);
	}

	@RequiresPermissions({"service-update"})
	//删除数据
	@RequestMapping(method = RequestMethod.POST, value = "/deleteSDA", headers = "Accept=application/json")
	@ResponseBody
	public boolean deleteSDA(@RequestBody String[] delIds){
		return serviceImpl.delete(delIds);
	}

	@RequiresPermissions({"service-update"})
	@RequestMapping("/moveUp")
	@ResponseBody
	public boolean moveUp(String sdaId){
		return serviceImpl.moveUp(sdaId);
	}

	@RequiresPermissions({"service-update"})
	@RequestMapping("/moveDown")
	@ResponseBody
	public boolean moveDown(String sdaId){
		return serviceImpl.moveDown(sdaId);
	}

	@RequestMapping("comparePage")
	@ResponseBody
	public boolean comparePage(String serviceId, String operationId){
//		return serviceImpl.comparePage(serviceId, operationId);
		return true;
	}


	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
