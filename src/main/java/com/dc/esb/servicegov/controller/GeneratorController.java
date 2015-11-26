package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.Generator;
import com.dc.esb.servicegov.service.impl.GeneratorServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {
    @Autowired
    GeneratorServiceImpl generatorService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<Generator> getAll(){
        return generatorService.getAll();
    }
}
