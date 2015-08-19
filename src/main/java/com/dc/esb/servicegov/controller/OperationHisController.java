package com.dc.esb.servicegov.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dc.esb.servicegov.entity.OperationHis;
import com.dc.esb.servicegov.service.impl.OperationHisServiceImpl;

@Controller
@RequestMapping("/operationHis")
public class OperationHisController {
	@Autowired
	private OperationHisServiceImpl operationHisServiceImpl;

	@RequiresPermissions({"service-get"})
	@RequestMapping("/hisPage")
	public ModelAndView hisPage(HttpServletRequest req, String operationId, String serviceId){
		return operationHisServiceImpl.hisPage(req, operationId, serviceId);
	}
	
	//根据服务和场景id
	@RequiresPermissions({"service-get"})
	@RequestMapping("/getByOS/{serviceId}/{operationId}")
	@ResponseBody
	public Map<String, Object> getByOS(@PathVariable(value="serviceId") String serviceId, @PathVariable(value="operationId") String operationId) {
		List<?> rows = operationHisServiceImpl.getByOS(operationId, serviceId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", rows.size());
		result.put("rows", rows);
		return result;
	}

	@RequiresPermissions({"service-get"})
	@RequestMapping("/getByAutoId")
	@ResponseBody
	public OperationHis getByAutoId(String autoId){
		return operationHisServiceImpl.getByAutoId(autoId);
	}

	@RequiresPermissions({"service-get"})
	@RequestMapping("/operationHisList")
	@ResponseBody
	public Map<String, Object> operationHisList(HttpServletRequest req) {
		int pageNo = Integer.parseInt(req.getParameter("page"));
		int rowCount = Integer.parseInt(req.getParameter("rows"));
		String hql = "select count(*) from OperationHis t where t.state = '"+Constants.Operation.OPT_STATE_PASS+"'";
		Page page = operationHisServiceImpl.getPageBy(hql, rowCount);
		page.setPage(pageNo);
		Map<String, Object> result = new HashMap<String, Object>();
		hql = "from OperationHis t where t.state = '"+Constants.Operation.OPT_STATE_PASS+"'";
		List<?> rows = operationHisServiceImpl.findBy(hql,page);

		result.put("total", page.getResultCount());
		result.put("rows", rows);
		return result;
	}

	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
