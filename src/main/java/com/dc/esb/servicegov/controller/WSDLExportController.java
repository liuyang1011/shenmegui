package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.ServiceService;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.OperationPKVO;
import com.dc.esb.servicegov.wsdl.impl.ESBServiceDescriptorGenerator;
import com.dc.esb.servicegov.wsdl.impl.MetadataSchemaGenerator;
import com.dc.esb.servicegov.wsdl.impl.SpdbServiceSchemaGenerator;
import com.dc.esb.servicegov.wsdl.impl.SpdbWSDLGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/1/14.
 */
@Controller
@RequestMapping("/wsdlExport")
public class WSDLExportController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;

    @Autowired
    private SpdbWSDLGenerator spdbWSDLGenerator;
    @Autowired
    private ServiceServiceImpl serviceService;
    /*根据场景列表导出字段映射*/
    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportOperation", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportOperation(HttpServletRequest request, HttpServletResponse response, OperationPKVO pkvo) {
        OperationLog operationLog = systemLogService.record("WSDL", "导出", "根据服务导出WSDL");
        List<Service> services = new ArrayList<Service>();
        for(OperationPK operationPK : pkvo.getPks()){
            Service service = serviceService.getUniqueByServiceId(operationPK.getServiceId());
            if(null != service){
                if(!services.contains(service)){
                    services.add(service);
                }
            }
        }
        String path = WSDLExportController.class.getResource("/").getPath() + "/generator" + new Date().getTime();
        spdbWSDLGenerator.generate(services, path);;
        if(StringUtils.isNotEmpty(path)){

            ZipUtil.compressZip(path, path + "/wsdl.zip", "wsdl.zip");
            InputStream in = null;
            OutputStream out = null;
            try {
                File file = new File(path + "/wsdl.zip");

                response.setContentType("application/zip");
                response.addHeader("Content-Disposition",
                        "attachment;filename=wsdl.zip");
                in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream(response.getOutputStream());
                long fileLength = file.length();
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
                logger.error(e, e);
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
        return true;
    }

}
