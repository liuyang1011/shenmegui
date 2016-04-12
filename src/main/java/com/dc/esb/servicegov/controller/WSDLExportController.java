package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.task.ExportWSDLTask;
import com.dc.esb.servicegov.export.task.IExportMapFileTask;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.ServiceService;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
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
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    /*根据场景列表导出字段映射*/
    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportOperation", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportOperation(HttpServletRequest request, HttpServletResponse response, OperationPKVO pkvo) {
        OperationLog operationLog = systemLogService.record("WSDL", "导出", "根据服务导出WSDL");
        String path = WSDLExportController.class.getResource("/").getPath() + "/generator" + new Date().getTime();
        Map<String, List<OperationPK>> map = new HashMap<String, List<OperationPK>>();
        //将场景按服务分类
        for(OperationPK operationPK : pkvo.getPks()){
            String serviceId = operationPK.getServiceId();
            if(null == map.get(serviceId)){
                List<OperationPK> pks = new ArrayList<OperationPK>();
                map.put(serviceId, pks);
            }
            List<OperationPK> pks = map.get(serviceId);
            pks.add(operationPK);
        }
        List<ExportWSDLTask> taskLst = new ArrayList<ExportWSDLTask>();
        CountDownLatch countDown = new CountDownLatch(map.size());
        //每个服务生成一个文件
        for(String serviceId : map.keySet()){
            String childPath = path + File.separator + serviceId;
//            spdbWSDLGenerator.generateByService(serviceId, map.get(serviceId), childPath);
            ExportWSDLTask task = new ExportWSDLTask();
            task.init(serviceId, map.get(serviceId), childPath, spdbWSDLGenerator,countDown);
            taskLst.add(task);
//
//            String zipName = serviceId + "wsdl.zip";
//            ZipUtil.compressZip(childPath, path + File.separator + zipName, zipName);
        }
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (ExportWSDLTask t : taskLst) {
            executor.execute(t);
        }
        try {
            countDown.await(60 * 5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("WSDL export : " + countDown.getCount());
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
//        for(OperationPK operationPK : pkvo.getPks()){
//           String childPath = path + File.separator + operationPK.getServiceId() + operationPK.getOperationId();
//            spdbWSDLGenerator.generate(operationPK, childPath);
//            if(StringUtils.isNotEmpty(childPath)){
//                String zipName = operationPK.getServiceId() + operationPK.getOperationId() + "wsdl.zip";
//                ZipUtil.compressZip(childPath, path + File.separator + zipName, zipName);
//            }
//        }
        ZipUtil.compressZip(path, path + File.separator + "wsdl.zip", "wsdl.zip");
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

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"excelExport-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/exportOperationAll", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean exportOperationBySystemId(HttpServletRequest request, HttpServletResponse response) {
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
