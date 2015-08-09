package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.service.impl.OperationServiceImpl;
import com.dc.esb.servicegov.service.impl.VersionServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
//        int pageNo = Integer.parseInt(req.getParameter("page"));
//        int rowCount = Integer.parseInt(req.getParameter("rows"));

        Map<String, Object> result = new HashMap<String, Object>();
        List<?> list = operationService.getReleased();
        result.put("total", list.size());
        result.put("rows", list);
        return result;
    }

}