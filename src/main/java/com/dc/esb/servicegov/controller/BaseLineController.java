package com.dc.esb.servicegov.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.entity.BaseLine;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.OperationHisVO;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.impl.BaseLineServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.util.JSONUtil;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/baseLine")
public class BaseLineController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private BaseLineServiceImpl baseLineServiceImpl;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeServiceImpl;


    @RequiresPermissions({"baseLine-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/release", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean release(HttpServletRequest req, String code, String blDesc,
                    String versionHisIds) {
        OperationLog operationLog = systemLogService.record("基线","基线发布","版本号：" + code + "；描述：" + blDesc);

        boolean result = baseLineServiceImpl.release(req, code, blDesc, versionHisIds);

        systemLogService.updateResult(operationLog);
        return result;

    }

    @RequiresPermissions({"baseLine-get"})
    @RequestMapping("/getBaseLine")
    @ResponseBody
    public Map<String, Object> getBaseLine(String code, String blDesc,HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String hql = "from "+ BaseLine.class.getName() +" where 1=1 ";
        if (StringUtils.isNotEmpty(code)) {
            hql += "and code like '%" + code + "%' ";
        }
        if (StringUtils.isNotEmpty(blDesc)) {
            hql += "and blDesc like '%" + blDesc + "%' ";
        }
        Page page = baseLineServiceImpl.getPageBy(hql,rowCount);
        page.setPage(pageNo);

        Map<String, Object> result = new HashMap<String, Object>();
        hql = " from "+ BaseLine.class.getName() +" where 1=1 ";
        if (StringUtils.isNotEmpty(code)) {
            hql += "and code like '%" + code + "%' ";
        }
        if (StringUtils.isNotEmpty(blDesc)) {
            hql += "and blDesc like '%" + blDesc + "%' ";
        }
        hql+=" order by optDate desc";
        List<?> rows = baseLineServiceImpl.findBy(hql,page);
        result.put("total", page.getResultCount());
        result.put("rows", rows);
        return result;
    }

    @RequiresPermissions({"version-get"})
    @RequestMapping("/getBLOperationHiss")
    @ResponseBody
    public List<?> getBLOperationHiss(String baseId) {
        return baseLineServiceImpl.getBLOperationHiss(baseId);
    }

    @RequiresPermissions({"version-get"})
    @RequestMapping("/getBLInvoke")
    @ResponseBody
    public Map<String, Object> getBLInvoke(String baseId) {
        List<?> rows = serviceInvokeServiceImpl.getBLInvoke(baseId);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    /**
     * 版本公告
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRecentBaseLine")
    @ResponseBody
    public ModelAndView getRecentBaseLine(HttpServletRequest req) {
        String baseId=req.getParameter("baseId");
        ModelAndView model=new ModelAndView();
        model.setViewName("version/releaseHis");
        if("".equals(baseId)||baseId==null){
            String hql="from BaseLine order by optDate desc";
            List<BaseLine> lines=baseLineServiceImpl.findBy(hql);
            model.addObject("baseLine",lines.get(0));
        }else{
            model.addObject("baseLine", baseLineServiceImpl.getById(baseId));
        }
        return model;
    }

    /**
     * 版本公告---服务规范定义列表数据
     * @param baseId
     * @return
     */
    @RequestMapping("/getOneRows")
    @ResponseBody
    public List<?> getOneRows(String baseId) {
        String hql="from BaseLine order by optDate desc";
        List<BaseLine> lines=baseLineServiceImpl.findBy(hql);
        List<OperationHisVO> baseline0=(List<OperationHisVO>)baseLineServiceImpl.getOneRow(lines.get(0).getBaseId(),baseLineServiceImpl);
        List<OperationHisVO> baseLine=(List<OperationHisVO>)baseLineServiceImpl.getOneRow(baseId,baseLineServiceImpl);
        return baseLineServiceImpl.getColorOneRow(baseline0,baseLine);
    }
    @RequestMapping("/getOneRowsNew")
    @ResponseBody
    public List<?> getOneRowsNew() {
        String hql="from BaseLine order by optDate desc";
        List<BaseLine> lines=baseLineServiceImpl.findBy(hql);
        List<OperationHisVO> baseline0=(List<OperationHisVO>)baseLineServiceImpl.getOneRow(lines.get(0).getBaseId(),baseLineServiceImpl);
        List<OperationHisVO> baseline=(List<OperationHisVO>)baseLineServiceImpl.getOneRow(lines.get(0).getBaseId(),baseLineServiceImpl);
        return baseLineServiceImpl.getColorOneRow(baseline0,baseline);
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
