package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.service.impl.ServiceHeadSdaServiceImpl;
import com.dc.esb.servicegov.util.TreeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
@Controller
@RequestMapping("serviceHeadSda")
public class ServiceHeadSdaController {
    @Autowired
    private ServiceHeadSdaServiceImpl serviceHeadSdaService;
    @RequiresPermissions({"sda-get"})
    //根据serviceId，operationId获取sda树
    @RequestMapping("/sdaTree")
    @ResponseBody
    public List<TreeNode> getSDATree(String serviceHeadId){
        return serviceHeadSdaService.genderSDATree(serviceHeadId);
    }
}
