package com.dc.esb.servicegov.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.rsimport.impl.MetadataArrayParserImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.dc.esb.servicegov.rsimport.impl.CategoryWordParserImpl;
import com.dc.esb.servicegov.rsimport.impl.EnglishWordXlsxParserImpl;
import com.dc.esb.servicegov.rsimport.impl.MetadataXlsxParserImpl;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/resourceImport")
public class ResourceImportController {
    @Autowired
    private SystemLogServiceImpl systemLogService;

    private Log log = LogFactory.getLog(ResourceImportController.class);
    @Autowired
    private CategoryWordParserImpl categoryWordParserImpl;
    @Autowired
    private EnglishWordXlsxParserImpl englishWordXlsxParserImpl;
    @Autowired
    private MetadataXlsxParserImpl metadataXlsxParserImpl;
    @Autowired
    private MetadataArrayParserImpl metadataArrayParserImpl;

    @RequiresPermissions({"importMetadata-update"})
    @RequestMapping(method = RequestMethod.POST, value = "/import")
    public ModelAndView importMetadata(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("file") MultipartFile file
    ) throws Exception {
        OperationLog operationLog = systemLogService.record("数据字典","导入","文件名称：" + file.getName());

        ModelAndView mv = new org.springframework.web.servlet.ModelAndView("message");
        String url= "/dataTemplate/grid7.jsp";
        String msg = "";
        response.setContentType("text/html");
        response.setCharacterEncoding("GB2312");
        log.info("import fileName is: " + file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        //判断上传文件是否为xlsx或xls类型
        if (fileName.toLowerCase().endsWith("xlsx")
                || fileName.toLowerCase().endsWith("xls")) {
            synchronized (ResourceImportController.class) {//同步，防止有多个请求
                Workbook workbook = null;
                try {
                    if (fileName.toLowerCase().endsWith("xls")) {
                        workbook = new HSSFWorkbook(file.getInputStream());
                    } else if (fileName.toLowerCase().endsWith("xlsx")) {
                        workbook = new XSSFWorkbook(file.getInputStream());
                    }
                } catch (IOException e) {
                    log.error(e, e);
                    msg = "文件上传过程中出现错误！";
                }
                if (null != workbook) {
                    try {
                        englishWordXlsxParserImpl.parse(workbook);
                        categoryWordParserImpl.parse(workbook);
                        metadataXlsxParserImpl.parse(workbook);
                        metadataArrayParserImpl.parse(workbook);
                    }catch (Exception e){
                        log.error(e, e);
                        msg =  "数据转换过程中出现错误！";
                    }

                }

            }
        } else {
            msg = "请上传EXCEL文件！";
        }
        if(StringUtils.isEmpty(msg)){
            msg = "上传成功！";
        }
        mv.addObject("msg", msg);
        mv.addObject("url", url);

        systemLogService.updateResult(operationLog);
        return mv;
    }

    public void printMsg(HttpServletResponse response, String message){
        PrintWriter pw = null;
        try {
            response.setContentType("text/html; charset=utf-8");
            pw = response.getWriter();
            pw.print("<script language='javascript'>alert('" + message + "')</script>");
        }catch (Exception e){
            log.error(e, e);
        }finally {
            if(pw != null){
                pw.close();
            }
        }
    }
    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}