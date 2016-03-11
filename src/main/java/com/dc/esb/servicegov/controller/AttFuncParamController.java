package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.AttFuncParamServiceImpl;
import com.dc.esb.servicegov.service.impl.AttFunctionServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/3/10.
 */
@Controller
@RequestMapping("/attFuncParam")
public class AttFuncParamController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    private static final Log log = LogFactory.getLog(MetadataController.class);

    @Autowired
    private AttFuncParamServiceImpl attFuncParamService;

    @RequestMapping(value = "/getByFuncId", headers = "Accept=application/json")
    @ResponseBody
    public List<AttFuncParam> getByFuncId(String id){
        List<AttFuncParam> list = attFuncParamService.findBy("funcId", id);
        return list;
    }

    @RequestMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    public boolean save(AttFuncParam attFuncParam) {
        OperationLog operationLog = systemLogService.record("方法参数", "保存", "名称：" + attFuncParam.getName());

        if(null == attFuncParam.getId()){
            attFuncParam.setId(UUID.randomUUID().toString());
        }
        attFuncParamService.save(attFuncParam);

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deletes", headers = "Accept=application/json")
    @ResponseBody
    public boolean deletes(@RequestBody AttFuncParam[] params) {
        OperationLog operationLog = systemLogService.record("方法参数","批量删除","数量：" + params.length);
        String logParam = "";
        for (int i = 0; i < params.length; i++) {
            AttFuncParam attFuncParam = params[i];
            attFuncParamService.deleteById(attFuncParam.getId());
            logParam += ", [参数名称：" + attFuncParam.getName() + "]";
        }
        operationLog.setParams(logParam);
        systemLogService.updateResult(operationLog);
        return true;
    }
}
