package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.MetadataVersion;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import com.dc.esb.servicegov.service.impl.MetadataVersionServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/1/4.
 */
@Controller
@RequestMapping("/metadataVersion")
public class MetadataVersionController {
    @Autowired
    MetadataVersionServiceImpl metadataVersionService;
    @Autowired
    private SystemLogServiceImpl systemLogService;

    @RequestMapping(value = "/releasePage")
    public ModelAndView releasePage(){//数据字典发布页面
        ModelAndView mv = new ModelAndView("metadata/metadata_release");
        String lastVersionNo = metadataVersionService.getLastVersion();
        mv.addObject("lastVersionNo", lastVersionNo);
        return mv;
    }

    @RequestMapping(value = "/release")
    public @ResponseBody
    boolean release(String versionNo, String versionDesc) {//保存发布版本
        OperationLog operationLog = systemLogService.record("数据字典","发布","版本号：" + versionNo);

        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        MetadataVersion metadataVersion = new MetadataVersion();
        metadataVersion.setId(UUID.randomUUID().toString());
        metadataVersion.setVersionNo(versionNo);
        metadataVersion.setVersionDesc(versionDesc);
        metadataVersion.setOptUser(userName);
        metadataVersion.setOptDate(DateUtils.format(new Date()));

        metadataVersionService.save(metadataVersion);

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"metadata-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/query", headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        Page page = new Page(metadataVersionService.queryCount(req.getParameterMap()), rowCount);
        page.setPage(pageNo);
//        List<Metadata> rows = metadataService.queryByCondition(req.getParameterMap(), page);
        //关联categoryWord表，显示chineseWord
        List<MetadataVersion> rows = metadataVersionService.queryByCondition(req.getParameterMap(), page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page.getResultCount());
        result.put("rows", rows);
        return result;
    }

    /**
     * 获取修订记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/showRecord", headers = "Accept=application/json")
    @ResponseBody
    public List<OperationLog> showRecord(String id) {
        MetadataVersion metadataVersion = metadataVersionService.findUniqueBy("id", id);//当前版本
        MetadataVersion metadataVersion1 = metadataVersionService.getBefore(metadataVersion.getOptDate());//之前最近的一个版本
        if(null == metadataVersion1){
            return null;
        }
        String startDate = metadataVersion1.getOptDate();
        String endDate = metadataVersion.getOptDate();
        List<OperationLog> result = systemLogService.getDataDectionaryRecord(startDate, endDate);
        return result;
    }

}
