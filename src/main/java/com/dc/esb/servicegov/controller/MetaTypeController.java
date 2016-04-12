package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.MetaTypeCodeEnumDaoImpl;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.MetaType;
import com.dc.esb.servicegov.entity.MetaTypeCodeEnum;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.service.impl.ExportMetaTypeServiceImpl;
import com.dc.esb.servicegov.service.impl.MetaTypeCodeEnumServiceImpl;
import com.dc.esb.servicegov.service.impl.MetaTypeServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/metaType")
public class MetaTypeController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private MetaTypeServiceImpl metaTypeService;
    @Autowired
    MetaTypeCodeEnumServiceImpl metaTypeCodeEnumService;
    @Autowired
    ExportMetaTypeServiceImpl exportMetaTypeService;

    private Log log = LogFactory.getLog(MetaTypeController.class);

    @RequiresPermissions({"metaType-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean add(@RequestBody MetaType metaType) {
        OperationLog operationLog = systemLogService.record("元数据", "新增", "元数据名称：" + metaType.getChineseName() + "; 英文名称：" + metaType.getName());
        metaTypeService.addMetaType(metaType);
        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"metaType-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/query", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> query(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String name = req.getParameter("name");
        String chineseName = req.getParameter("chineseName");
        String optUser = req.getParameter("optUser");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from MetaType c where 1=1 ");
        if (null != name && !"".equals(name)) {
            hql.append(" and name like ?");
            searchConds.add(new SearchCondition("name", "%" + name + "%"));
        }
        if (null != chineseName && !"".equals(chineseName)) {
            hql.append(" and chineseName like ?");
            try {
                chineseName = URLDecoder.decode(chineseName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            searchConds.add(new SearchCondition("chineseName", "%" + chineseName + "%"));
        }
        if (null != optUser && !"".equals(optUser)) {
            hql.append(" and optUser like ?");
            try {
                optUser = URLDecoder.decode(optUser, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            searchConds.add(new SearchCondition("optUser", "%" + optUser + "%"));
        }
        if (null != startDate && !"".equals(startDate)) {
            hql.append(" and optDate > '"+startDate+"'");
//            searchConds.add(new SearchCondition("optDate", "'" + startDate + "'"));
        }
        if (null != endDate && !"".equals(endDate)) {
            hql.append(" and optDate < '"+endDate+" 23:59:59' ");
//            searchConds.add(new SearchCondition("optDate", "'" + endDate + "23:59:59'"));
        }

        hql.append(" order by name");
//        Page page = categoryWordService.getAll(rowCount);
        Page page = new Page();

        if (searchConds.size() <= 0) {
            page = metaTypeService.getPageBy(hql.toString(), rowCount);
        } else {
            page = metaTypeService.getPageBy(hql.toString(), rowCount, searchConds);
        }

        page.setPage(pageNo);

        List<MetaType> list = metaTypeService.findBy(hql.toString(), page, searchConds);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", list);
        return map;
    }

    /**
     * 验证元数据名称的唯一性
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uniqueValName", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean uniqueValName(String metaType) {
        return metaTypeService.uniqueValName(metaType);
    }

    /**
     * 验证元数据中文名称的唯一性
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uniqueValChName", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean uniqueValChName(String chineseName) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            chineseName = URLDecoder.decode(chineseName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        map.put("chineseName", chineseName);
        return metaTypeService.uniqueValid(map);
    }

    /**
     * 删除元数据
     */
    @RequiresPermissions({"metaType-delete"})
    @RequestMapping("/deletes")
    @ResponseBody
    public boolean deletes(String ids) {
        OperationLog operationLog = systemLogService.record("元数据", "批量删除", "Id：" + ids);
        boolean result = metaTypeService.deleteMetaTypes(ids);
        //加入判断是否删除的判断
//        String metadataId = originalData.getMetadataId();
//        List<Metadata> metadatas = metadataService.findBy("metadataId", metadataId);
//        if (metadatas.size() > 0) {
//            return false;
//        }
        systemLogService.updateResult(operationLog);
        return result;
    }

    /**
     * 修改元数据时显示默认值
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit(String id) {
        ModelAndView model = new ModelAndView("metaType/edit");
        MetaType metaType = metaTypeService.findUniqueBy("id", id);
        model.addObject("metaType", metaType);
        return model;
    }

    /**
     * 代码型获取详细信息
     * @param standardChName 中文名称
     * @return 所有详细信息
     */
    @RequestMapping(method = RequestMethod.GET,value = "{standardChName}/getCodeEnum")
    public
    @ResponseBody
    List<MetaTypeCodeEnum> getCodeValue(@PathVariable("standardChName") String standardChName){
        String chName = null;
        try{
            chName = URLDecoder.decode(standardChName,"UTF-8");
            List<MetaTypeCodeEnum> list =  metaTypeCodeEnumService.getAll(chName);
            return list;

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            log.error("["+chName+"]代码型详细信息转码失败！",e);
        }
        return null;
    }

    /**
     * 导出元数据规范
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/exportExcel")
    public
    @ResponseBody
    boolean exportExcel(HttpServletResponse response){
        String fileName = "元数据规范_" + new Date().getTime();
        XSSFWorkbook workbook = exportMetaTypeService.genderExcel();
        boolean result = export(response, fileName, workbook);
        return result;
    }

    public boolean  export(HttpServletResponse response, String fileName, XSSFWorkbook workbook ){
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/vnd.ms-excel");
            codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
            fOut = response.getOutputStream();
            if(workbook != null){
                workbook.write(fOut);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(fOut != null){
                    fOut.flush();
                    fOut.close();
                }
            }
            catch (IOException e)
            {
                log.error("IO异常");
            }
        }
        return true;

    }






}
















