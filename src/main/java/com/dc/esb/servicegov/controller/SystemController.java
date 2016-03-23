package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.service.ProtocolService;
import com.dc.esb.servicegov.service.SystemProtocolService;
import com.dc.esb.servicegov.service.SystemService;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.util.TreeNode;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by Administrator on 2015/7/2.
 */

@Controller
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemLogServiceImpl systemLogService;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SystemService systemService;

    @Autowired
    ProtocolService protocolService;

    @Autowired
    SystemProtocolService systemProtocolService;

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/getAll", headers = "Accept=application/json")
    public @ResponseBody Map<String,Object> getAll(HttpServletRequest req) {

        String starpage = req.getParameter("page");
        String rows = req.getParameter("rows");

        StringBuffer hql = new StringBuffer("SELECT t1 FROM System t1");

        String systemNo = req.getParameter("systemNo");
        String systemChineseName = req.getParameter("systemChineseName");
        String systemAb = req.getParameter("systemAb");
        String systemPrincipal = req.getParameter("systemPrincipal");
        String systemClassify = req.getParameter("systemClassify");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        hql.append(" where 1=1");
        if(systemNo!=null&&!"".equals(systemNo)){
            hql.append(" and t1.systemNo like ?");
            SearchCondition search = new SearchCondition();
            search.setFieldValue("%" + systemNo + "%");
            searchConds.add(search);
        }
        if(systemChineseName!=null&&!"".equals(systemChineseName)){
            try{
                systemChineseName = URLDecoder.decode(systemChineseName, "utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            hql.append(" and t1.systemChineseName like ?");
            SearchCondition search = new SearchCondition();
            search.setFieldValue("%" + systemChineseName +"%");
            searchConds.add(search);
        }
        if(systemAb!=null&&!"".equals(systemAb)){
            hql.append(" and t1.systemAb like ?");
            SearchCondition search = new SearchCondition();
            search.setFieldValue("%" + systemAb +"%");
            searchConds.add(search);
        }
        if(systemPrincipal!=null&&!"".equals(systemPrincipal)){
            hql.append(" and t1.systemPrincipal like ?");
            SearchCondition search = new SearchCondition();
            search.setFieldValue("%" + systemPrincipal + "%");
            searchConds.add(search);
        }
        if(systemClassify!=null&&!"".equals(systemClassify)){
            hql.append(" and t1.systemClassify like ?");
            SearchCondition search = new SearchCondition();
            search.setFieldValue("%" + systemClassify +"%");
            searchConds.add(search);
        }

        hql.append(" order by t1.systemNo");
        SearchCondition searchCond = new SearchCondition();

        Page page = systemService.findPage(hql.toString(), Integer.parseInt(rows), searchConds);
        page.setPage(Integer.parseInt(starpage));

        List<System> systems = systemService.findBy(hql.toString(),page,searchConds);
        List<System> resList = new ArrayList<System>();
        for (System s:systems) {
            System sys = new System();
            sys.setSystemId(s.getSystemId());
            sys.setSystemNo(s.getSystemNo());
            sys.setSystemAb(s.getSystemAb());
            sys.setSystemChineseName(s.getSystemChineseName());
            sys.setSystemClassify(s.getSystemClassify());
            sys.setSystemPrincipal(s.getSystemPrincipal());
            sys.setPrincipalTel(s.getPrincipalTel());
            sys.setCreateUser(s.getCreateUser());
            sys.setUpdateDate(s.getUpdateDate());
            sys.setUpdateUser(s.getUpdateUser());
            //add by

            List<SystemProtocol> systemProtocols =  s.getSystemProtocols();
            String protocolName = "";
            for(SystemProtocol p : systemProtocols){
                if(!"".equals(protocolName)){
                    protocolName =  protocolName + ",";
                }
                protocolName += p.getProtocol().getProtocolName();
            }
            sys.setProtocolName(protocolName);
            resList.add(sys);

        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total", page.getResultCount());
        map.put("rows", resList);
        return map;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{systemId}", headers = "Accept=application/json")
    public @ResponseBody
    System getById(@PathVariable String systemId) {
        return systemService.getById(systemId);

    }

    @RequiresPermissions({"system-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
    public @ResponseBody
    boolean save(@RequestBody
                 System entity) {
        OperationLog operationLog = systemLogService.record("系统","保存","系统名称：" + entity.getSystemChineseName());
        systemService.save(systemService.createTime(entity));

        systemLogService.updateResult(operationLog);
        return true;

    }
    @RequiresPermissions({"system-update"})
    @RequestMapping(method = RequestMethod.GET, value = "/edit/{systemId}", headers = "Accept=application/json")
    public ModelAndView getSystem(@PathVariable
                                     String systemId) {

        System system = systemService.getById(systemId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("system", system);
        modelAndView.setViewName("sysadmin/system_edit");
        return modelAndView;
    }

    @RequiresPermissions({"system-update"})
    @RequestMapping(method = RequestMethod.POST, value = "/update", headers = "Accept=application/json")
    public @ResponseBody
    boolean update(@RequestBody
                   System entity) {
        OperationLog operationLog = systemLogService.record("系统", "修改", "系统编号:" + entity.getSystemNo());
        System entity1=systemService.getById(entity.getSystemId());
        systemService.replaceSystem(entity1,entity);
        systemService.update(entity1);
        systemLogService.updateResult(operationLog);
        return true;

    }

    @RequiresPermissions({"system-delete"})
    @RequestMapping(method = RequestMethod.POST, value = "/delete2", headers = "Accept=application/json")
    public @ResponseBody
    boolean delete2(@RequestBody String systemId) {
        //去掉""
        systemId = systemId.substring(1,systemId.length()-1);
        OperationLog operationLog = systemLogService.record("系统","删除","系统编号:" + systemService.getById(systemId).getSystemNo());
        boolean result =  systemService.deleteSystemById(systemId);

        systemLogService.updateResult(operationLog);
        return result;

    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getProtocolAll", headers = "Accept=application/json")
    public @ResponseBody List<Map<String,Object>> getProtocolAll(HttpServletRequest request) {
        List<Map<String,Object>> resList = new ArrayList<Map<String, Object>>();
        List<Protocol> protocols =  null;
        Map<String,Object> map = new HashMap<String, Object>();
        if(request.getParameter("query")!=null && !"".equals(request.getParameter("query"))) {
            map.put("id", "");
            map.put("text", "全部");
            resList.add(map);
        }else{
            map.put("id", "");
            map.put("text", "不关联");
            resList.add(map);
        }
        String systemId = request.getParameter("systemId");
        if(systemId!=null&&!"".equals(systemId)){
            String hql = "SELECT t1 From Protocol t1,SystemProtocol t2 WHERE t1.protocolId = t2.protocolId and t2.systemId = ?";
            ArrayList<SearchCondition> searchConditions = new ArrayList<SearchCondition>();
            SearchCondition search = new SearchCondition();
            search.setFieldValue(systemId);
            searchConditions.add(search);
            protocols = protocolService.findBy(hql,searchConditions);

        }else {
            protocols = protocolService.getAll();
        }
        for (Protocol p : protocols) {
            map = new HashMap<String, Object>();
            map.put("id", p.getProtocolId());
            map.put("text", p.getProtocolName());
            resList.add(map);
        }
        return resList;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/protocolRelate/{systemId}", headers = "Accept=application/json")
//    public void protocolRelate(@PathVariable String systemId){
//        List<Map<String,Object>> resList = new ArrayList<Map<String, Object>>();
//        List<Protocol> protocols =  protocolService.getAll();
//        Map<String,Object> map = null;
//        for (Protocol p :protocols){
//            map = new HashMap<String, Object>();
//            map.put("id",p.getProtocolId());
//            map.put("text",p.getProtocolName());
//            resList.add(map);
//        }
//    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getChecked/{systemId}", headers = "Accept=application/json")
    public @ResponseBody List<String> getChecked(@PathVariable String systemId,HttpServletRequest request) {
       List<String> resList = new ArrayList<String>();
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("systemId",systemId);
        List<SystemProtocol> protocols =  systemProtocolService.findBy(paramMap);
        for (SystemProtocol p : protocols){
            resList.add(p.getProtocolId());
        }
        return resList;
    }

    @RequiresPermissions({"protocol-update"})
    @RequestMapping(method = RequestMethod.GET, value = "/protocolRelate/{systemId}/{protocols}", headers = "Accept=application/json")
    public @ResponseBody boolean protocolRelate(@PathVariable String systemId,@PathVariable String protocols) {
        OperationLog operationLog = systemLogService.record("系统","关联协议","系统ID:" + systemId + "; 协议ID:" + protocols);

        if(protocols.equals("none")){
            systemProtocolService.deleteSystemProtocol(systemId);
            return true;
        }
        if(protocols!=null && systemId!=null){
//            try {
//                systemProtocolService.deleteSystemProtocol(systemId);
//
//            }catch (ObjectNotFoundException e){
//                logger.info("该系统中["+systemId+"]没发现关联的协议可删除");
//            }
            StringTokenizer tokenizer = new StringTokenizer(protocols,",");
            while (tokenizer.hasMoreElements()){
                String protocol = tokenizer.nextElement().toString();
                SystemProtocol systemProtocol = new SystemProtocol();
                systemProtocol.setProtocolId(protocol);
                systemProtocol.setSystemId(systemId);
                systemProtocolService.save(systemProtocol);
            }
        }

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getProtocol/{systemId}", headers = "Accept=application/json")
    public @ResponseBody List<Protocol> getChecked(@PathVariable String systemId) {
        List<String> resList = new ArrayList<String>();
        Map<String,String> paramMap = new HashMap<String, String>();
        paramMap.put("systemId",systemId);
        List<SystemProtocol> ps =   systemProtocolService.findBy(paramMap);
        List<Protocol> protocols = new ArrayList<Protocol>();
        for(SystemProtocol s:ps){
            protocols.add(protocolService.getById(s.getProtocolId()));
        }
        return protocols;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/check/{interfaceId}", headers = "Accept=application/json")
    public @ResponseBody
    boolean check(@PathVariable String interfaceId) {
        System system =  systemService.findUniqueBy("systemId",interfaceId);
        if(system!=null) {
            return true;
        }
        return  false;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getSystemAll", headers = "Accept=application/json")
    public @ResponseBody List<Map<String,Object>> getSystemAll(HttpServletRequest request) {
        List<Map<String,Object>> resList = new ArrayList<Map<String, Object>>();
        List<System> systems =  systemService.getAll();
        Map<String,Object> map = new HashMap<String, Object>();
        if(request.getParameter("query")!=null && !"".equals(request.getParameter("query"))) {
            map.put("id", "");
            map.put("text", "全部");
            resList.add(map);
        }

        for (System system :systems){
            map = new HashMap<String, Object>();
            map.put("id",system.getSystemId());
            map.put("systemNo",system.getSystemNo());
            map.put("text",system.getSystemAb());
            map.put("chineseName",system.getSystemChineseName());
            resList.add(map);
        }
        return resList;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/systemNoCheck/{systemNo}", headers = "Accept=application/json")
    public @ResponseBody
    boolean systemId(@PathVariable String systemNo) {
        System system =  systemService.findUniqueBy("systemNo",systemNo);
        if(system!=null) {
            return true;
        }
        return  false;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/systemAbcheck/{systemAb}/{systemNo}", headers = "Accept=application/json")
    public @ResponseBody
    boolean systemAb(@PathVariable String systemAb,@PathVariable String systemNo) {
        System system =  systemService.findUniqueBy("systemAb",systemAb);

        if(system!=null && !system.getSystemNo().equals(systemNo)) {
            return true;
        }
        return  false;
    }

    @RequiresPermissions({"system-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getTree", headers = "Accept=application/json")
    public
    @ResponseBody
    List<TreeNode> getAllTreeBean() {
        List<TreeNode> systemTree = new ArrayList<TreeNode>();
        List<com.dc.esb.servicegov.entity.System> systems = systemService.getAll();
        for(com.dc.esb.servicegov.entity.System system : systems){
            TreeNode systemTreeNode = new TreeNode();
            systemTreeNode.setId(system.getSystemId());
            systemTreeNode.setText(system.getSystemChineseName());
            systemTree.add(systemTreeNode);
        }
        return systemTree;
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
