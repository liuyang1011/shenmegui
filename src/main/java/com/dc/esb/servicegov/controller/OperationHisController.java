package com.dc.esb.servicegov.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.Version;
import com.dc.esb.servicegov.entity.VersionHis;
import com.dc.esb.servicegov.service.impl.VersionHisServiceImpl;
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
	@Autowired
	private VersionHisServiceImpl versionHisService;

	@RequiresPermissions({"service-get"})
	@RequestMapping("/hisPage")
	public ModelAndView hisPage(HttpServletRequest req, String operationId, String serviceId){
		return operationHisServiceImpl.hisPage(req, operationId, serviceId);
	}
	
	//根据服务和场景id
//	@RequiresPermissions({"service-get"})
	@RequiresPermissions({"version-get"})
	@RequestMapping("/getByOS/{serviceId}/{operationId}")
	@ResponseBody
	public Map<String, Object> getByOS(@PathVariable(value="serviceId") String serviceId, @PathVariable(value="operationId") String operationId) {
		List<?> rows = operationHisServiceImpl.getByOS(operationId, serviceId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", rows.size());
		result.put("rows", rows);
		return result;
	}

	@RequiresPermissions({"version-get"})
	@RequestMapping("/getVersionsByOS/{serviceId}/{operationId}")
	@ResponseBody
	public List<VersionVO> getVersions(@PathVariable(value="serviceId") String serviceId, @PathVariable(value="operationId") String operationId) {
		List<OperationHis> operationHises = operationHisServiceImpl.getByOS(operationId, serviceId);
		List<VersionVO> versions = new ArrayList<VersionVO>();

		for(int i = 0; i < operationHises.size(); i ++){
			VersionHis version = operationHises.get(i).getVersionHis();
			version.setAutoId(operationHises.get(i).getAutoId());
			VersionVO versionVO = new VersionVO();
			versionVO.setVersionId("V" + (i+1));
			versionVO.setVersionCode(version.getCode());
			versions.add(versionVO);
		}
		return versions;
	}

	class VersionVO{
		private String versionCode;
		private String versionId;

		public String getVersionCode() {
			return versionCode;
		}

		public void setVersionCode(String versionCode) {
			this.versionCode = versionCode;
		}

		public String getVersionId() {
			return versionId;
		}

		public void setVersionId(String versionId) {
			this.versionId = versionId;
		}
	}

	@RequiresPermissions({"service-get"})
	@RequestMapping("/getByAutoId")
	@ResponseBody
	public OperationHis getByAutoId(String autoId){
		return operationHisServiceImpl.getByAutoId(autoId);
	}

	@RequiresPermissions({"baseLine-get"})
	@RequestMapping("/operationHisList")
	@ResponseBody
	public Map<String, Object> operationHisList(HttpServletRequest req) {
		int pageNo = Integer.parseInt(req.getParameter("page"));
		int rowCount = Integer.parseInt(req.getParameter("rows"));

		String serviceId = req.getParameter("serviceId");
		if(null == serviceId) serviceId = "";
		String serviceName = req.getParameter("serviceName");
		if(null != serviceName && !"".equals(serviceName)){
			try{
				serviceName = URLDecoder.decode(req.getParameter("serviceName"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			serviceName = "";
		}
		String operationId = req.getParameter("operationId");
		if(null == operationId) operationId = "";
		String operationName = req.getParameter("operationName");
		if(null != operationName && !"".equals(operationName)){
			try{
				operationName = URLDecoder.decode(req.getParameter("operationName"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else{
			operationName = "";
		}



		String hql = "from OperationHis a where a.state = '"+Constants.Operation.LIFE_CYCLE_STATE_PUBLISHED+"'";
		hql += " and a.service.serviceId like ? and a.service.serviceName like ?";
		hql += " and a.operationId like ? and a.operationName like ?";
		Page page = operationHisServiceImpl.getPageBy(hql, rowCount,"%"+serviceId+"%","%"+serviceName+"%","%"+operationId+"%","%"+operationName+"%");
		page.setPage(pageNo);
		Map<String, Object> result = new HashMap<String, Object>();
		hql = "from OperationHis a where a.state = '"+Constants.Operation.LIFE_CYCLE_STATE_PUBLISHED+"'" +
				" and a.service.serviceId like ? and a.service.serviceName like ?"+
				" and a.operationId like ? and a.operationName like ?"+
				" order by a.versionHis.optDate desc";
		List<?> rows = operationHisServiceImpl.findBy(hql,page,"%"+serviceId+"%","%"+serviceName+"%","%"+operationId+"%","%"+operationName+"%");

		result.put("total", page.getResultCount());
		result.put("rows", rows);
		return result;
	}
	@RequestMapping("/hisJudge")
	@ResponseBody
	public boolean hisJudge(String serviceId, String operationId){
		return operationHisServiceImpl.hisJudge(serviceId, operationId);
	}

	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
