package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.AttFuncParam;
import com.dc.esb.servicegov.entity.AttFunction;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.AttFunctionServiceImpl;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
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
@RequestMapping("/attFunction")
public class AttFunctionController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    private static final Log log = LogFactory.getLog(MetadataController.class);

    @Autowired
    private AttFunctionServiceImpl attFunctionService;

    @RequestMapping(value = "/query", headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        Page page = new Page(attFunctionService.queryCount(req.getParameterMap()), rowCount);
        page.setPage(pageNo);
//        List<Metadata> rows = metadataService.queryByCondition(req.getParameterMap(), page);
        //关联categoryWord表，显示chineseWord
        List<AttFunction> rows = attFunctionService.queryByCondition(req.getParameterMap(), page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page.getResultCount());
        result.put("rows", rows);
        return result;
    }

    @RequestMapping(value = "/save", headers = "Accept=application/json")
    @ResponseBody
    public boolean save(AttFunction attFunction) {
        OperationLog operationLog = systemLogService.record("字段处理","添加","名称：" + attFunction.getName());

        if(null == attFunction.getId()){
            attFunction.setId(UUID.randomUUID().toString());
        }
        attFunctionService.save(attFunction);

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequestMapping(value = "/editPage", headers = "Accept=application/json")
    @ResponseBody
    public ModelAndView editPage(String id) {
        ModelAndView mv = new ModelAndView("attfunc/func_edit.jsp");
        AttFunction entity = attFunctionService.getById(id);
        mv.addObject("entity", entity);
        return mv;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/deletes", headers = "Accept=application/json")
    @ResponseBody
    public boolean deletes(@RequestBody AttFunction[] functions) {
        OperationLog operationLog = systemLogService.record("字段方法","批量删除","数量：" + functions.length);
        String logParam = "";
        for (int i = 0; i < functions.length; i++) {
            AttFunction attFunction = functions[i];
            attFunctionService.deleteById(attFunction.getId());
            logParam += ", [方法名称：" + attFunction.getName() + "]";
        }
        operationLog.setParams(logParam);
        systemLogService.updateResult(operationLog);
        return true;
    }
}
