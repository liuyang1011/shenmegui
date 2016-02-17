package com.dc.esb.servicegov.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dc.esb.servicegov.dao.impl.TaskManageServiceImpl;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.ProcessContext;
import com.dc.esb.servicegov.entity.TaskManage;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.TaskVO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private TaskManageServiceImpl taskManageService;
    @RequiresRoles({"admin"})
    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
    public @ResponseBody
    boolean add(@RequestBody TaskManage task) {
        OperationLog operationLog = systemLogService.record("任务","添加","任务名称：" + task.getTaskNum());
        taskManageService.save(task);
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
        StringBuffer hql = new StringBuffer("select task from TaskManage task");
        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        Page page = taskManageService.findPage(hql.toString(), Integer.parseInt(rows), searchConds);
        page.setPage(Integer.parseInt(starpage));
        List<TaskManage> task = taskManageService.findBy(hql.toString(), page, searchConds);
        Map<String,Object> resMap = new HashMap<String,Object>();
        resMap.put("total", page.getResultCount());
        resMap.put("rows", task);
        return resMap;
    }

    @RequiresRoles({"admin"})
    @RequestMapping(method = RequestMethod.POST, value = "/query", headers = "Accept=application/json")
    public
    @ResponseBody
    List<TaskVO> getByName( @RequestBody Map<String, String> params) {
        List<TaskVO> TaskVOs = new ArrayList<TaskVO>();
        List<TaskManage> tasks = taskManageService.findLikeAnyWhere(params);
        for(TaskManage task : tasks){
            TaskVOs.add(new TaskVO(task));
        }
        return TaskVOs;
    }
    @RequiresRoles({"admin"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean delete(@PathVariable String id) {
        OperationLog operationLog = systemLogService.record("任务","删除","任务ID:" + id);
        taskManageService.deleteById(id);
        systemLogService.updateResult(operationLog);
        return true;
    }
    /*
    @RequiresRoles({"admin"})
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{id}", headers = "Accept=application/json")
    public
    @ResponseBody
    ModelAndView getById(@PathVariable String id) {
        Organization org= orgServiceImpl.getById(id);
        ModelAndView model = new ModelAndView();
        model.addObject("org", org);
        model.setViewName("org/orgEdit");
        return model;
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
    */


    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
