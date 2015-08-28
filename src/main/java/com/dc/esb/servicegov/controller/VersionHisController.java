package com.dc.esb.servicegov.controller;

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
		int pageNo = Integer.parseInt(req.getParameter("page"));
		int rowCount = Integer.parseInt(req.getParameter("rows"));
		Page page = versionHisServiceImpl.getAll(rowCount);
//		Page page = versionHisServiceImpl.getPageBy(hql);
		page.setPage(pageNo);
		Map<String, Object> result = new HashMap<String, Object>();
		String hql = " from VersionHis";
		if(StringUtils.isNotEmpty(keyValue)){
			hql += " where code like '%"+keyValue+"%' or versionDesc like '%"+keyValue+"%' or remark like '%"+keyValue+"%'";
		}
		List<VersionHisServiceImpl.VersionHisBean> rows = versionHisServiceImpl.findVersionBeanBy(hql, page);
//		List<VersionHis> rows = versionHisServiceImpl.findBy(hql,page);
//		List<VersionHis> rows = versionHisServiceImpl.hisVersionList(keyValue);
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
