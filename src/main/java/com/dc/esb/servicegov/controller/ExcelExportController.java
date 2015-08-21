package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.service.impl.ExcelExportInterfaceImpl;
import com.dc.esb.servicegov.service.impl.ExcelExportServiceImpl;
import com.dc.esb.servicegov.vo.OperationPKVO;
import com.dc.esb.servicegov.vo.ReleaseListVO;
import com.dc.esb.servicegov.vo.ReuseRateListVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/14.
 */
@Controller
@RequestMapping("/excelExporter")
public class ExcelExportController {

    private static Log log = LogFactory.getLog(ExcelExportController.class);
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
    @Autowired
    private ExcelExportServiceImpl excelExportServiceImpl;

    @Autowired
    private ExcelExportInterfaceImpl excelExportInterfaceImpl;


    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportService", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportService(HttpServletRequest request, HttpServletResponse response,
                          String id, String type) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode(type+"_"+id, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportServiceImpl.genderExcel(id, type);
            fOut = response.getOutputStream();
            if(workbook != null){
                workbook.write(fOut);
            }
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        catch (Exception e)
        {
            //TODO catch后没做任何处理，也没print，抛错后找不到地方
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

    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportOperation", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportOperation(HttpServletRequest request, HttpServletResponse response, OperationPKVO pkvo) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode("operation_" + new Date().getTime(), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportServiceImpl.genderExcelByOperation(pkvo);
            fOut = response.getOutputStream();
            if(workbook != null){
                workbook.write(fOut);
            }
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
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

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
    /**
     * @param request
     * @param response
     * @param categoryId
     * @return
     */
    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportServiceView", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportServiceView(HttpServletRequest request, HttpServletResponse response,
            String type, String categoryId) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode("serviceview_"+categoryId, "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportServiceImpl.genderServiceView(type, categoryId);

            fOut = response.getOutputStream();
            workbook.write(fOut);
        }
        catch (UnsupportedEncodingException e1)
        {
            log.error(e1, e1);
        }
        catch (Exception e)
        {
            log.error(e, e);
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

    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportInterface", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportInterface(HttpServletRequest request, HttpServletResponse response,
                          String ids, String type,String systemId) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode(type, "UTF-8");
            String[] interfaceIds = ids.split(",");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportInterfaceImpl.genderExcel(interfaceIds, type, systemId);
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

    /**
     * 导出复用率统计execl
     * */
    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportReuserate", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportReuserate(HttpServletRequest request, HttpServletResponse response,
                            ReuseRateListVO listVO) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode("reuse_rate_"+ new Date().getTime(), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportServiceImpl.genderRuserate(listVO);
            fOut = response.getOutputStream();
            if(workbook != null){
                workbook.write(fOut);
            }
        }
        catch (UnsupportedEncodingException e1)
        {}
        catch (Exception e)
        {}
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
    /**
     * 导出发布统计
     * */
    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportRelease", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportRelease(HttpServletRequest request, HttpServletResponse response,
                            ReleaseListVO listVO) {
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            response.setContentType("application/zip");
            codedFileName = java.net.URLEncoder.encode("release_"+ new Date().getTime(), "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = excelExportServiceImpl.genderRelease(listVO);
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
