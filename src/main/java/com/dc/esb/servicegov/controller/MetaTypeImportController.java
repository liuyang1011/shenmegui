package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.rsimport.impl.CodeEnumXlsxParserImpl;
import com.dc.esb.servicegov.rsimport.impl.MetaTypeXlsxParserImpl;
import com.dc.esb.servicegov.rsimport.impl.MetadataXlsxParserImpl;
import com.dc.esb.servicegov.service.impl.MetaTypeServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhx113 on 2016/3/9.
 */
@Controller
@RequestMapping("/metaTypeImport")
public class MetaTypeImportController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private MetaTypeXlsxParserImpl metaTypeXlsxParser;
    @Autowired
    private CodeEnumXlsxParserImpl codeEnumXlsxParser;

    private Log log = LogFactory.getLog(MetaTypeImportController.class);

    public static List<String> metaTypeList = new ArrayList<String>();// 元数据缓存，用于处理重复元素


    /**
     * 导入元数据
     * @param request
     * @param response
     * @param file
     * @return
     * @throws Exception
     */
    @RequiresPermissions({"metaType-import"})
    @RequestMapping(method = RequestMethod.POST, value = "/import")
    public ModelAndView importMetaType(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam("file") MultipartFile file
    )throws Exception {
        OperationLog operationLog = systemLogService.record("元数据","导入","文件名称：" + file.getOriginalFilename());
        ModelAndView mv = new ModelAndView("message");
        String url = "/jsp/metaType/import_metaType.jsp";
        String msg="";
        response.setContentType("text/html");
        response.setCharacterEncoding("GB2312");
        log.info("import fileName is: " + file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        //判断上传文件是否为xlsx或xls类型
        if (fileName.toLowerCase().endsWith("xlsx") || fileName.toLowerCase().endsWith("xls")) {
            synchronized (MetaTypeImportController.class) {//同步，防止有多个请求
                Workbook workbook = null;
                try{
                    if (fileName.toLowerCase().endsWith("xls")) {
                        workbook = new HSSFWorkbook(file.getInputStream());
                    } else if (fileName.toLowerCase().endsWith("xlsx")) {
                        workbook = new XSSFWorkbook(file.getInputStream());
                    }
                }catch (IOException e){
                    log.error(e, e);
                    msg = "文件上传过程中出现错误！";
                }
                if(null!=workbook){
                    //主要处理区域
                    try{
                        metaTypeXlsxParser.parse(workbook);
                        codeEnumXlsxParser.parse(workbook);
                    }catch(Exception e){
                        log.error(e, e);
                        msg =  "数据转换过程中出现错误！";
                    }finally {
                        metaTypeList.clear();
                    }

                }
            }
        }else{
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
}
