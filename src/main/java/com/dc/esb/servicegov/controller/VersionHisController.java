package com.dc.esb.servicegov.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.esb.servicegov.service.impl.VersionHisServiceImpl;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/versionHis")
public class VersionHisController {
	@Autowired
	private VersionHisServiceImpl versionHisServiceImpl;
	@Autowired
	private OperationServiceImpl operationService;
	@Autowired
	private ServiceServiceImpl serviceService;

	@RequestMapping("/hisVersionList")
	@ResponseBody
	public Map<String, Object> hisVersionList(String keyValue,HttpServletRequest req) {
		if(null == keyValue){
			keyValue = "";
		}
		try{
			keyValue = URLDecoder.decode(keyValue, "utf-8");
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}

		int pageNo = Integer.parseInt(req.getParameter("page"));
		int rowCount = Integer.parseInt(req.getParameter("rows"));
//		Page page = versionHisServiceImpl.getAll(rowCount);

		Map<String, Object> result = new HashMap<String, Object>();
		//过滤按钮功能不对。过滤按照服务码，服务名称，场景码，场景名称进行。
		Map<String,String> map = new HashMap<String, String>();
		map.put("serviceId",keyValue);
		List<Service> serviceList = serviceService.findLikeAnyWhere(map);
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("serviceName",keyValue);
		List<Service> serviceList2 = serviceService.findLikeAnyWhere(map2);
		serviceList.addAll(serviceList2);
		String serviceStr = "";
		for (int i = 0; i < serviceList.size(); i++) {
			if(i==0){
				serviceStr += "'" + serviceList.get(i).getServiceId() + "'";
			}else{
				serviceStr += ",'" +serviceList.get(i).getServiceId() + "'";
			}
		}

		String hql = " from OperationHis oh left join oh.versionHis";
		if(StringUtils.isNotEmpty(keyValue)){
			hql += " where oh.operationId like '%"+keyValue+"%' or oh.operationName like '%"+keyValue+"%'";
			if ("".equals(serviceStr)) {
				hql += " order by oh.versionHis.optDate desc";
			}else{
				hql += " or oh.serviceId in ("+serviceStr+") order by oh.versionHis.optDate desc";
			}
		} else {
			hql += " order by oh.versionHis.optDate desc";
		}

//		String hql = " from VersionHis v left join v.operationHis";
//		if(StringUtils.isNotEmpty(keyValue)){
////			hql += " where code like '%"+keyValue+"%' or versionDesc like '%"+keyValue+"%' or remark like '%"+keyValue+"%' order by optDate desc";
//			hql += " where v.operationHis.operationId like '%"+keyValue+"%' or v.operationHis.operationName like '%"+keyValue+"%'";
//			if ("".equals(serviceStr)) {
//				hql += " order by v.optDate desc";
//			}else{
//				hql += " or v.operationHis.serviceId in ("+serviceStr+") order by v.optDate desc";
//			}
//		}else {
//			hql += " order by v.optDate desc";
//		}
		Page page = versionHisServiceImpl.getPageBy(hql,rowCount);
		page.setPage(pageNo);
		List<VersionHisServiceImpl.VersionHisBean> rows = versionHisServiceImpl.findVersionBeanBy(hql, page);
		result.put("total", page.getResultCount());
		result.put("rows", rows);
		return result;
	}

	//	@RequiresPermissions({"version-get"})
	@RequestMapping("/hisDetailPage")
	public ModelAndView hisDetailPage(String serviceId, String operationId) {
		ModelAndView mv = new ModelAndView("version/versionHisDetail");
		Operation operation = operationService.getOperation(serviceId, operationId);
		if(operation != null){
			mv.addObject("operation", operation);
		}
		Service service = serviceService.getById(serviceId);
		if(null != service){
			mv.addObject("service",service);
		}
		return mv;
	}
}
