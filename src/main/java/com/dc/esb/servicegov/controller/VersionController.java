package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.VersionServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/1.
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    @Autowired
    private VersionServiceImpl versionService;
    @Autowired
    private OperationServiceImpl operationService;

    @RequiresPermissions({"version-get"})
    @RequestMapping("/operationList")
    @ResponseBody
    public Map<String, Object> operationList(HttpServletRequest req) {
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
//        Page page = operationService.getAll(rowCount);
        String hql = "from Operation a where a.state=? and a.version.optType !=? and a.service.serviceId like ? and a.service.serviceName like ?";
        hql += " and a.operationId like ? and a.operationName like ?";
        Page page = operationService.getPageBy(hql,rowCount, Constants.Operation.OPT_STATE_PASS, Constants.Version.OPT_TYPE_RELEASE,"%"+serviceId+"%","%"+serviceName+"%",
                                                "%"+operationId+"%","%"+operationName+"%");
        page.setPage(pageNo);

        Map<String, Object> result = new HashMap<String, Object>();
        List<Operation> list = operationService.getReleased(page,serviceId,serviceName,operationId,operationName);
        result.put("total", page.getResultCount());
        result.put("rows", list);
        return result;
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }

}
