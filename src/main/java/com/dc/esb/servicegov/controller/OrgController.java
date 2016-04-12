package com.dc.esb.servicegov.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.OrgVO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.dc.esb.servicegov.entity.Organization;
import com.dc.esb.servicegov.service.impl.OrgServiceImpl;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/org")
public class OrgController {
	@Autowired
	private SystemLogServiceImpl systemLogService;
	@Autowired
	private OrgServiceImpl orgServiceImpl;
	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
	public @ResponseBody
	boolean add(@RequestBody Organization org) {
		OperationLog operationLog = systemLogService.record("组织","添加","组织名称：" + org.getOrgName());
		orgServiceImpl.save(org);
		systemLogService.updateResult(operationLog);
		return true;
	}
	@RequiresRoles({"admin"})
    @RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
    public
    @ResponseBody
	Map<String,Object> getAll(String operationId,HttpServletRequest req) {
		String starpage = req.getParameter("page");
		String rows = req.getParameter("rows");
		StringBuffer hql = new StringBuffer("select org from Organization org");
		List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
		Page page = orgServiceImpl.findPage(hql.toString(), Integer.parseInt(rows), searchConds);
		page.setPage(Integer.parseInt(starpage));
		List<Organization> orgs = orgServiceImpl.findBy(hql.toString(), page, searchConds);
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("total", page.getResultCount());
		resMap.put("rows", orgs);
		return resMap;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllOrg", headers = "Accept=application/json")
	 public
	 @ResponseBody
	 List<Organization> getAllOrg() {
		return orgServiceImpl.getAll();
	}

	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.GET, value = "/getById/{id}", headers = "Accept=application/json")
	public
		 @ResponseBody
		 ModelAndView getById(@PathVariable String id) {
		Organization org= orgServiceImpl.getById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("org", org);
		model.setViewName("organiz/orgEdit");
		return model;
	}
	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean delete(@PathVariable String id) {
		OperationLog operationLog = systemLogService.record("机构","删除","机构ID:" + id);
		orgServiceImpl.deleteById(id);
		systemLogService.updateResult(operationLog);
		return true;
	}

	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.POST, value = "/query", headers = "Accept=application/json")
	public
	@ResponseBody
	List<OrgVO> getByName( @RequestBody Map<String, String> params) {
		List<OrgVO> OrgVOs = new ArrayList<OrgVO>();
		List<Organization> orgs = orgServiceImpl.findLikeAnyWhere(params);
		for(Organization org : orgs){
			OrgVOs.add(new OrgVO(org));
		}
		return OrgVOs;
	}
	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.GET, value = "/checkUnique/orgId/{orgId}", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean passWord(@PathVariable("orgId") String orgId) {
		Organization org = orgServiceImpl.getById(orgId);
		if(null != org){
			return false;
		}
		return true;
	}

	@RequiresRoles({"admin"})
	@RequestMapping(method = RequestMethod.GET, value = "/checkOrgNameUnique/name/{name}", headers = "Accept=application/json")
	public
	@ResponseBody
	boolean checkOrgNameUnique(@PathVariable("orgName") String name) {
		List<Organization> orgs = orgServiceImpl.findBy("orgName", name);
		if(orgs.size() > 0){
			return false;
		}
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findById")
	public @ResponseBody Organization modifyUser(@RequestParam String OrgId) {
		Organization org = orgServiceImpl.getById(OrgId);
		return org;
	}



	@ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
	public String processUnauthorizedException() {
		return "403";
	}
}
