package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.impl.MapFileGenerator;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.OperationPKVO;
import com.dc.esb.servicegov.wsdl.impl.SpdbWSDLGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

/**
 * Created by Administrator on 2016/1/14.
 */
@Controller
@RequestMapping("/mapFile")
public class MapFileExportController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;

    @Autowired
    private MapFileGenerator mapFileGenerator;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    /*根据场景列表导出mapping*/
    @RequestMapping(method = RequestMethod.POST, value = "/exportOperation", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportOperation(HttpServletRequest request, HttpServletResponse response, OperationPKVO pkvo) {
        OperationLog operationLog = systemLogService.record("mapfile", "导出", "根据场景导出mapfile");

        String path = MapFileExportController.class.getResource("/").getPath() + "/generator" + new Date().getTime();
        mapFileGenerator.generate(pkvo, path);;
        if(StringUtils.isNotEmpty(path)){

            ZipUtil.compressZip(path, path + "/mapfile.zip", "mapfile.zip");
            InputStream in = null;
            OutputStream out = null;
            try {
                File file = new File(path + "/mapfile.zip");

                response.setContentType("application/zip");
                response.addHeader("Content-Disposition",
                        "attachment;filename=mapfile.zip");
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
                    logger.error("导出mapfile文件，关闭流异常," + e.getMessage(),e);
                }

            }
        }
        systemLogService.updateResult(operationLog);
        return true;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/exportOperationAll")
    public
    @ResponseBody
    boolean exportOperationAll(HttpServletRequest request, HttpServletResponse response) {
        String hql = " from ServiceInvoke where serviceId is not null and operationId is not null";
        List<ServiceInvoke> serviceInvokes = serviceInvokeService.find(hql);
        OperationPKVO pkvo = new OperationPKVO();
        List<OperationPK> pks = new ArrayList<OperationPK>();
        for(ServiceInvoke serviceInvoke : serviceInvokes){
            OperationPK pk = new OperationPK();
            pk.setServiceId(serviceInvoke.getServiceId());
            pk.setOperationId(serviceInvoke.getOperationId());
            if(!pks.contains(pk)){
                pks.add(pk);
            }
        }
        pkvo.setPks(pks);
        if(0 == pks.size()){
            return false;
        }
        return exportOperation(request, response, pkvo);
    }

}
