package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.EsbServer;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.export.impl.ConfigBathGenerator;
import com.dc.esb.servicegov.export.impl.EsbScriptExportGenerator;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.impl.EsbServerServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/1/19.
 */
@Controller
@RequestMapping("/esbScriptExport")
public class EsbScriptExportController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private EsbScriptExportGenerator esbScriptExportGenerator;
    @RequiresPermissions({"scriptExport-get"})
    @RequestMapping("/checkedExport")
    @ResponseBody
    public boolean checkedExport(HttpServletRequest request, HttpServletResponse response, String  delScript, String protocolTypeScript, String protocolScript, String adapterFrameScript, String serviceSystemScript,
                                 String chinalScript, String serviceScript, String baseServiceScript, String operationIds){
        OperationLog operationLog = systemLogService.record("导出", "脚本导出","");
        String path = EsbScriptExportController.class.getResource("/").getPath() + "/script" + new Date().getTime();
        esbScriptExportGenerator.checkedExport(delScript, protocolTypeScript, protocolScript, adapterFrameScript, serviceSystemScript, chinalScript, serviceScript, baseServiceScript, operationIds, path);
        ZipUtil.compressZip(path, path + "/dbscript.zip", "dbscript.zip");
        InputStream in = null;
        OutputStream out = null;
        try {
            File metadata = new File(path + "/dbscript.zip");
            response.setContentType("application/zip");
            response.addHeader("Content-Disposition",
                    "attachment;filename=dbscript.zip");
            in = new BufferedInputStream(new FileInputStream(metadata));
            out = new BufferedOutputStream(response.getOutputStream());
            long fileLength = metadata.length();
            byte[] cache = null;
            if (fileLength > Integer.MAX_VALUE) {
                cache = new byte[Integer.MAX_VALUE];
            } else {
                cache = new byte[(int) fileLength];
            }
            int i = 0;
            while ((i = in.read(cache)) > 0) {
                out.write(cache, 0, i);
            }
            out.flush();
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            try {
                in.close();
                out.close();
                FileUtil.deleteDirectory(new File(path));
            } catch (Exception e) {
                logger.error("导出配置文件，关闭流异常," + e.getMessage(),e);
            }

        }
        systemLogService.updateResult(operationLog);
        return true;
    }
    @RequestMapping(value="/preview")
    public @ResponseBody Map<String, String> preview(String type, String operationIds){
        String result = "";
        if("del".equalsIgnoreCase(type)){
            result = esbScriptExportGenerator.delContent(operationIds);
        }
        if("service".equalsIgnoreCase(type)){
            result = esbScriptExportGenerator.serviceContent(operationIds);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", result);
        return map;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/downloadAll", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> exportBatch(HttpServletRequest request, HttpServletResponse response, ConfigListVO list) {
        OperationLog operationLog = systemLogService.record("导出", "脚本全量导出","");

        String path  = "";
        if(StringUtils.isNotEmpty(path)){
            ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");
            InputStream in = null;
            OutputStream out = null;
            try {
                File metadata = new File(path + "/dbscript.zip");

                response.setContentType("application/zip");
                response.addHeader("Content-Disposition",
                        "attachment;filename=metadata.zip");
                in = new BufferedInputStream(new FileInputStream(metadata));
                out = new BufferedOutputStream(response.getOutputStream());
                long fileLength = metadata.length();
                byte[] cache = null;
                if (fileLength > Integer.MAX_VALUE) {
                    cache = new byte[Integer.MAX_VALUE];
                } else {
                    cache = new byte[(int) fileLength];
                }
                int i = 0;
                while ((i = in.read(cache)) > 0) {
                    out.write(cache, 0, i);
                }
                out.flush();
            } catch (Exception e) {
                logger.error(e,e);
            } finally {
                try {
                    in.close();
                    out.close();
                    FileUtil.deleteDirectory(new File(path));
                } catch (Exception e) {
                    logger.error("导出配置文件，关闭流异常," + e.getMessage(),e);
                }

            }
        }
        systemLogService.updateResult(operationLog);
        return null;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/configSync/{optionFlag}/{dicSync}/{serverStr}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean configSync (HttpServletRequest request, HttpServletResponse response,@RequestBody ConfigVO[] list,@PathVariable(value = "optionFlag")  String optionFlag,@PathVariable(value = "dicSync") String dicSync,@PathVariable(value = "serverStr") String serverStr) {
        OperationLog operationLog = systemLogService.record("配置文件", "ESB同步", "");

        try {

        }catch (Exception e){
            logger.error("配置文件同步错误");
        }
        systemLogService.updateResult(operationLog);
        return true;
    }


}
