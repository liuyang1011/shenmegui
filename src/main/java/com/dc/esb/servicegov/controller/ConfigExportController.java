package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.export.IExportableNode;
import com.dc.esb.servicegov.export.IMetadataConfigGenerator;
import com.dc.esb.servicegov.export.IPackerParserConfigGenerator;
import com.dc.esb.servicegov.export.bean.ExportBean;
import com.dc.esb.servicegov.export.exception.ExportException;
import com.dc.esb.servicegov.export.impl.StandardSOAPConfigGenerator;
import com.dc.esb.servicegov.export.impl.StandardXMLConfigGenerator;
import com.dc.esb.servicegov.export.util.ExportUtil;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.*;
import com.dc.esb.servicegov.service.impl.*;

import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/14.
 */
@Controller
@RequestMapping("/export")
public class ConfigExportController {

    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    SystemService systemService;
    @Autowired
    ServiceInvokeService serviceInvokeService;
    @Autowired
    SDAService sdaService;
    @Autowired
    OperationServiceImpl operationService;
    @Autowired
    IdaService idaService;
    @Autowired
    StandardXMLConfigGenerator standardXMLConfigGenerator;
    @Autowired
    StandardSOAPConfigGenerator standardSOAPConfigGenerator;
    @Autowired
    InterfaceService interfaceService;
    @Autowired
    ProtocolService protocolService;

    @Autowired
    LogInfoServiceImpl logInfoService;
    @Autowired
    private InterfaceHeadServiceImpl interfaceHeadService;

    /**
     * 导出配置文件
     *
     * @param serviceId            服务ID
     * @param operationId          操作ID
     * @param providerSystemId     提供系统ID
     * @param providerInterfaceId  提供接口ID
     * @param providerIsStandard   提供方是否为标准
     * @param consumerInterfaceId  消费方接口
     * @param consumerSystemId     消费方系统ID
     * @param consumerIsStandard   消费方是否为标准
     * @param providerStandardType
     * @param consumerStandardType
     * @param response
     * @return
     */
    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/exportHandle/{serviceId}/{operationId}/{providerSystemId}/{providerInterfaceId}/{providerIsStandard}/{consumerSystemId}/{consumerInterfaceId}/{consumerIsStandard}/{providerStandardType}/{consumerStandardType}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> export(@PathVariable String serviceId, @PathVariable String operationId,
                        @PathVariable String providerSystemId, @PathVariable String providerInterfaceId,
                        @PathVariable boolean providerIsStandard, @PathVariable String consumerInterfaceId,
                        @PathVariable String consumerSystemId, @PathVariable boolean consumerIsStandard,
                        @PathVariable String providerStandardType, @PathVariable String consumerStandardType,
                        HttpServletResponse response) {
        logger.info("开始导出配置,服务ID:[" + serviceId + "],操作ID:[" + operationId + "],提供者ID:[" + providerSystemId + "" +
                "j],消费者ID:[" + consumerSystemId + "],提供接口ID:[" + providerInterfaceId + "],消费接口ID:[" + consumerInterfaceId + "]");
        OperationLog operationLog = systemLogService.record("服务", "配置导出", "服务ID:" + serviceId + ";操作ID：" + operationId
                + ";提供者ID" + providerSystemId + ";消费者ID:" + consumerSystemId + ", 提供者接口ID"
                + providerInterfaceId + ", consumerSystemId 消费者接口ID:");
        File in_file = null;
        //构造导出的条件Bean
        ExportBean export = new ExportBean(serviceId, operationId, providerSystemId, providerInterfaceId,
                providerIsStandard, consumerSystemId, consumerInterfaceId, consumerIsStandard);
        //sdaMap
        Map<String, String> sdaMap = new HashMap<String, String>();
        Map<String, String> idaMap = new HashMap<String, String>();
        sdaMap.put("serviceId", export.getServiceId());
        sdaMap.put("operationId", export.getOperationId());
        sdaMap.put("interfaceId", export.getConsumerInterfaceId());
        List<SDA> sdas = sdaService.findBy(sdaMap);
        //这个又是什么鬼东西
        List<Ida> idas = idaService.findBy(idaMap);
        SDA SDARequest = null;
        SDA SDAResponse = null;
        for (SDA sda : sdas) {
            if (sda.getStructName().equalsIgnoreCase("request")) {
                SDARequest = sda;
                continue;
            }
            if (sda.getStructName().equalsIgnoreCase("response")) {
                SDAResponse = sda;
            }
            if (SDARequest != null && SDAResponse != null) {
                break;
            }
        }
        String requestText = "";
        String responseText = "";
        String requestSOAPText = "";
        String responseSOAPText = "";

        //消费方是否标准接口
        if (export.isConsumerIsStandard()) {
            requestText = ExportUtil.generatorServiceDefineXML(sdas, SDARequest);
            responseText = ExportUtil.generatorServiceDefineXML(sdas, SDAResponse);
            if (consumerStandardType.equalsIgnoreCase("xml")) {
                standardXMLConfigGenerator.init(requestText, responseText);
                in_file = standardXMLConfigGenerator.generatorIn(idas, sdas, export);
            } else if (consumerStandardType.equalsIgnoreCase("soap")) {
                requestSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDARequest);
                responseSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDAResponse);
                standardSOAPConfigGenerator.init(requestText, responseText, requestSOAPText, responseSOAPText);
                in_file = standardSOAPConfigGenerator.generatorIn(idas, sdas, export);
            }
        } else {
            //非标准接口导出,我不得不改了
            try {
                IPackerParserConfigGenerator generator = getGenerator(export);
                Map<String, String> param = new HashMap<String, String>();
                param.put("interfaceId", export.getConsumerInterfaceId());
                List<Ida> bodyNodes = idaService.findBy(param, "seq");
                List<List<? extends IExportableNode>> headers = new ArrayList<List<? extends IExportableNode>>();
                List<List<Ida>> headIdas = interfaceHeadService.getHeadersByInterfaceId(export.getConsumerInterfaceId());
                for(List<Ida> headIda : headIdas){
                    headers.add(headIda);
                }
                List<File> resultFiles = generator.generate(headers, bodyNodes, export.getServiceId(), export.getOperationId(),
                        export.getConsumerSystemId(), "consumer");
                in_file = resultFiles.get(0);
            } catch (Exception e) {
                printMsg(response, e.getMessage());
            }
        }
        //提供方是否标准接口
        if (export.isProviderIsStandard()) {
            if (providerStandardType.equalsIgnoreCase("xml")) {
                standardXMLConfigGenerator.generatorOut(null, sdas, export);
            } else if (providerStandardType.equalsIgnoreCase("soap")) {
                standardSOAPConfigGenerator.generatorOut(idas, sdas, export);
            }
        } else {
            //非标准接口导出,我不得不改了
            try {
                IPackerParserConfigGenerator generator = getGenerator(export);
                Map<String, String> param = new HashMap<String, String>();
                param.put("interfaceId", export.getConsumerInterfaceId());
                List<Ida> bodyNodes = idaService.findBy(param, "seq");
                List<List<? extends IExportableNode>> headers = new ArrayList<List<? extends IExportableNode>>();
                List<List<Ida>> headIdas = interfaceHeadService.getHeadersByInterfaceId(export.getConsumerInterfaceId());
                for(List<Ida> headIda : headIdas){
                    headers.add(headIda);
                }
                List<File> resultFiles = generator.generate(headers, bodyNodes, export.getServiceId(), export.getOperationId(),
                        export.getConsumerSystemId(), "provider");
                in_file = resultFiles.get(0);
            } catch (Exception e) {
                printMsg(response, e.getMessage());
            }
        }
        String path = in_file.getPath();
        ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");
        InputStream in = null;
        OutputStream out = null;
        try {
            File metadata = new File(path + "/metadata.zip");

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

            systemLogService.updateResult(operationLog);

        } catch (Exception e) {


        } finally {
            try {
                in.close();
                out.close();
                FileUtil.deleteDirectory(new File(path).getParent());
            } catch (Exception e) {
                logger.error("导出文件，关闭流异常," + e.getMessage());
            }
        }

        return null;
    }


    private IPackerParserConfigGenerator getGenerator(ExportBean export) throws Exception {
        IPackerParserConfigGenerator generator = null;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", export.getServiceId());
        paramMap.put("operationId", export.getOperationId());
        paramMap.put("interfaceId", export.getConsumerInterfaceId());
        paramMap.put("systemId", export.getConsumerSystemId());
        ServiceInvoke invoke = serviceInvokeService.findUniqueBy(paramMap);
        if (invoke != null) {
            String protocolId = invoke.getProtocolId();
            if (protocolId == null || "".equals(protocolId)) {
                logger.error("消费方接口未关联协议，导出失败");
                logInfoService.saveLog("消费方接口未关联协议，导出失败", "导出");
                return null;
            } else {
                Protocol protocol = protocolService.getById(protocolId);
                String generatorClass = protocol.getGeneratorId();
                try {
                    Class c = Class.forName(generatorClass);
                    generator = (IPackerParserConfigGenerator) c.newInstance();
                } catch (ReflectiveOperationException e) {
                    logger.error("消费方接口协议报文生成类反射失败，导出失败");
                    logInfoService.saveLog("消费方接口协议报文生成类反射失败，导出失败", "导出");
                    throw new ExportException("消费方接口协议报文生成类反射失败，导出失败");
                }

            }
        }
        return generator;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getSystem/{serviceId}/{operationId}/{type}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Map<String, String>> getSystemAll(@PathVariable String serviceId, @PathVariable String operationId, @PathVariable String type) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", serviceId);
        paramMap.put("operationId", operationId);
        paramMap.put("type", type);
        List<ServiceInvoke> invokes = serviceInvokeService.findBy(paramMap);

        Map<String, String> map = new HashMap<String, String>();


        for (ServiceInvoke system : invokes) {
            if (!contains(resList, system.getSystemId())) {
                map = new HashMap<String, String>();
                map.put("id", system.getSystemId());
                map.put("text", system.getSystem().getSystemAb());
                resList.add(map);
            }
        }
        return resList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/getInterface/{serviceId}/{operationId}/{type}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Map<String, String>> getInterfaceAll(@PathVariable String serviceId, @PathVariable String operationId, @PathVariable String type) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("serviceId", serviceId);
        paramMap.put("operationId", operationId);
        paramMap.put("type", type);
        List<ServiceInvoke> invokes = serviceInvokeService.findBy(paramMap);

        Map<String, String> map = new HashMap<String, String>();


        for (ServiceInvoke system : invokes) {
            map = new HashMap<String, String>();
            map.put("id", system.getInterfaceId());
            Interface inter = new Interface();
            inter = system.getInter();
            if (null == inter) continue;
            map.put("text", inter.getInterfaceName());
            resList.add(map);
        }
        return resList;
    }

    private boolean contains(List<Map<String, String>> mapList, String systemId) {
        for (Map<String, String> map : mapList) {
            java.util.Iterator iter = map.keySet().iterator();
            while (iter.hasNext()) {
                String value = map.get(iter.next());
                if (systemId.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printMsg(HttpServletResponse response, String message) {
        PrintWriter pw = null;
        try {
            response.setContentType("text/html; charset=utf-8");
            pw = response.getWriter();
            pw.print("<script language='javascript'>alert('" + message + "')</script>");
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }

    /*根据场景列表导出字段映射*/
    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/getConfigVo", headers = "Accept=application/json")
    public
    @ResponseBody
    List<ConfigVO> getConfigVo(@RequestBody List list) {
        List<ConfigVO> result = operationService.getConfigVo(list);
        return result;
    }

    @RequiresPermissions({"exportConfig-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/exportBatch", headers = "Accept=application/json")
    public
    @ResponseBody
    List<String> exportBatch(HttpServletRequest request, HttpServletResponse response, ConfigListVO list) {
        String path = "";
        InputStream in = null;
        OutputStream out = null;
        try {
            if (list != null) {
                List<ConfigVO> voList = list.getList();
                if (voList != null && voList.size() > 0) {
                    for (ConfigVO configVO : voList) {
                        String serviceId = configVO.getServiceId();
                        String operationId = configVO.getOperationId();
                        String providerSystemId = configVO.getProviderId();
                        String isStandardPro = configVO.getIsStandardPro();
                        boolean providerIsStandard = true;
                        String providerInterfaceId = "no";
                        String consumerInterfaceId = "no";
                        String consumerSystemId = configVO.getCustomerId();
                        String isStandardCon = configVO.getIsStandardCon();
                        boolean consumerIsStandard = true;
                        String providerStandardType = configVO.getInterfaceOrProtocolPro();
                        String consumerStandardType = configVO.getInterfaceOrProtocolCon();

                        if ("1".equals(isStandardPro)) {
                            providerIsStandard = false;
                            providerInterfaceId = configVO.getInterfaceIdOrProtocolIdPro();
                        }
                        if ("1".equalsIgnoreCase(isStandardCon)) {
                            consumerIsStandard = false;
                            consumerInterfaceId = configVO.getInterfaceIdOrProtocolIdCon();
                        }
                        File in_file = null;
                        ExportBean export = new ExportBean(serviceId, operationId, providerSystemId, providerInterfaceId, providerIsStandard, consumerSystemId, consumerInterfaceId, consumerIsStandard);
                        Map<String, String> sdaMap = new HashMap<String, String>();
                        sdaMap.put("serviceId", export.getServiceId());
                        sdaMap.put("operationId", export.getOperationId());
                        List<SDA> sdas = sdaService.findBy(sdaMap);

                        Map<String, String> idaMap = new HashMap<String, String>();
                        sdaMap.put("interfaceId", export.getConsumerInterfaceId());
                        List<Ida> idas = idaService.findBy(idaMap);
                        SDA SDARequest = null;
                        SDA SDAResponse = null;
                        for (SDA sda : sdas) {
                            if (sda.getStructName().equalsIgnoreCase("request")) {
                                SDARequest = sda;
                                continue;
                            }
                            if (sda.getStructName().equalsIgnoreCase("response")) {
                                SDAResponse = sda;
                            }
                            if (SDARequest != null && SDAResponse != null) {
                                break;
                            }
                        }
                        String requestText = "";
                        String responseText = "";
                        String requestSOAPText = "";
                        String responseSOAPText = "";

                        //消费方是否标准接口
                        if (export.isConsumerIsStandard()) {

                            requestText = ExportUtil.generatorServiceDefineXML(sdas, SDARequest);
                            responseText = ExportUtil.generatorServiceDefineXML(sdas, SDAResponse);
                            if (consumerStandardType.equalsIgnoreCase("xml")) {
                                standardXMLConfigGenerator.init(requestText, responseText);
                                in_file = standardXMLConfigGenerator.generatorIn(idas, sdas, export);
                            } else if (consumerStandardType.equalsIgnoreCase("soap")) {
                                requestSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDARequest);
                                responseSOAPText = ExportUtil.generatorServiceDefineSOAP(sdas, SDAResponse);
                                standardSOAPConfigGenerator.init(requestText, responseText, requestSOAPText, responseSOAPText);
                                in_file = standardSOAPConfigGenerator.generatorIn(idas, sdas, export);
                            }
                        } else {
                            Map<String, String> paramMap = new HashMap<String, String>();
                            paramMap.put("serviceId", export.getServiceId());
                            paramMap.put("operationId", export.getOperationId());
                            paramMap.put("interfaceId", export.getConsumerInterfaceId());
                            paramMap.put("systemId", export.getConsumerSystemId());
                            ServiceInvoke invoke = serviceInvokeService.findUniqueBy(paramMap);
                            if (invoke != null) {
                                String protocolId = invoke.getProtocolId();
                                if (protocolId == null || "".equals(protocolId)) {
                                    logger.error("消费方接口未关联协议，导出失败");
                                    //                    return "消费方提供方接口未关联协议，导出失败";
                                    logInfoService.saveLog("消费方接口未关联协议，导出失败", "导出");
                                    printMsg(response, "消费方接口未关联协议，导出失败");
                                    return null;
                                } else {
                                    Protocol protocol = protocolService.getById(protocolId);
                                    String generatorClass = protocol.getGeneratorId();

                                    try {
                                        Class c = Class.forName(generatorClass);
                                        try {
                                            IMetadataConfigGenerator generator = (IMetadataConfigGenerator) c.newInstance();
                                            generator.setSystemService(systemService);
                                            generator.setInterfaceService(interfaceService);
                                            generator.setSdaService(sdaService);
                                            generator.setOperationService(operationService);
                                            generator.setInterfaceHeadService(interfaceHeadService);
                                            generator.setIdaService(idaService);
                                            in_file = generator.generatorIn(idas, sdas, export);
                                        } catch (InstantiationException e) {
                                            logger.error("消费方接口协议报文生成类实例化失败,导出失败,错误信息：" + e.getMessage());
                                            //                            return "消费方接口协议报文生成类构造方法是不可访问,导出失败，请查看日志!";
                                            logInfoService.saveLog("消费方接口协议报文生成类实例化失败,导出失败", "导出");
                                            printMsg(response, "消费方接口协议报文生成类实例化失败,导出失败");
                                            return null;
                                        } catch (IllegalAccessException e) {

                                            logger.error("消费方接口协议报文生成类构造方法是不可访问,导出失败,错误信息：" + e.getMessage());
                                            logInfoService.saveLog("消费方接口协议报文生成类构造方法是不可访问,导出失败", "导出");
                                            //                            return "消费方接口协议报文生成类构造方法是不可访问,导出失败";
                                            printMsg(response, "消费方接口协议报文生成类构造方法是不可访问,导出失败");
                                            return null;
                                        }
                                    } catch (ClassNotFoundException e) {
                                        logger.error("消费方接口协议报文生成类未找到，导出失败");
                                        logInfoService.saveLog("消费方接口协议报文生成类未找到，导出失败", "导出");
                                        //                        return "消费方接口协议报文生成类未找到，导出失败";
                                        printMsg(response, "消费方接口协议报文生成类未找到，导出失败");
                                        return null;
                                    }

                                }

                            }

                        }

                        Map<String, String> providerIDAMap = new HashMap<String, String>();
                        providerIDAMap.put("interfaceId", export.getProviderInterfaceId());
                        List<Ida> provideridas = idaService.findBy(providerIDAMap);
                        //提供方是否标准接口
                        if (export.isProviderIsStandard()) {
                            if (providerStandardType.equalsIgnoreCase("xml")) {
                                standardXMLConfigGenerator.generatorOut(provideridas, sdas, export);
                            } else if (providerStandardType.equalsIgnoreCase("soap")) {
                                standardSOAPConfigGenerator.generatorOut(idas, sdas, export);
                            }

                        } else {
                            Map<String, String> paramMap = new HashMap<String, String>();
                            paramMap.put("serviceId", export.getServiceId());
                            paramMap.put("operationId", export.getOperationId());
                            paramMap.put("interfaceId", export.getProviderInterfaceId());
                            paramMap.put("systemId", export.getProviderSystemId());
                            ServiceInvoke invoke = serviceInvokeService.findUniqueBy(paramMap);
                            if (invoke != null) {

                                String protocolId = invoke.getProtocolId();
                                if (protocolId == null || "".equals(protocolId)) {
                                    logger.error("提供方接口未关联协议，导出失败");
                                    logInfoService.saveLog("提供方接口未关联协议，导出失败", "导出");
                                    //                    return "提供方接口未关联协议，导出失败";
                                    printMsg(response, "提供方接口未关联协议，导出失败");
                                    return null;
                                } else {


                                    Protocol protocol = protocolService.getById(protocolId);
                                    String generatorClass = protocol.getGeneratorId();

                                    try {
                                        Class c = Class.forName(generatorClass);
                                        try {
                                            IMetadataConfigGenerator generator = (IMetadataConfigGenerator) c.newInstance();
                                            generator.setSystemService(systemService);
                                            generator.setInterfaceService(interfaceService);
                                            generator.setSdaService(sdaService);
                                            generator.setOperationService(operationService);
                                            generator.setInterfaceHeadService(interfaceHeadService);
                                            generator.setIdaService(idaService);
                                            generator.generatorOut(provideridas, sdas, export);
                                        } catch (InstantiationException e) {
                                            logger.error("提供方接口协议报文生成类实例化失败,导出失败,错误信息：" + e.getMessage());
                                            //                            return "提供方接口协议报文生成类构造方法是不可访问,导出失败，请查看日志!";
                                            logInfoService.saveLog("提供方接口协议报文生成类实例化失败,导出失败", "导出");
                                            printMsg(response, "提供方接口协议报文生成类实例化失败,导出失败");
                                            return null;
                                        } catch (IllegalAccessException e) {
                                            logger.error("提供方接口协议报文生成类构造方法是不可访问,导出失败,错误信息：" + e.getMessage());
                                            //                            return "提供方接口协议报文生成类构造方法是不可访问,导出失败";
                                            logInfoService.saveLog("提供方接口协议报文生成类构造方法是不可访问,导出失败", "导出");
                                            printMsg(response, "提供方接口协议报文生成类构造方法是不可访问,导出失败");
                                            return null;
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                        logger.error("提供方接口协议报文生成类未找到，导出失败");
                                        logInfoService.saveLog("提供方接口协议报文生成类未找到，导出失败", "导出");
                                        //                        return "提供方接口协议报文生成类未找到，导出失败";
                                        printMsg(response, "提供方接口协议报文生成类未找到，导出失败");
                                        return null;
                                    }

                                }
                            }
                        }
                        path = in_file.getParentFile().getPath();
                    }

                }
            }
            ZipUtil.compressZip(path, path + "/metadata.zip", "metadata.zip");

            File metadata = new File(path + "/metadata.zip");

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

//            systemLogService.updateResult(operationLog);

        } catch (Exception e) {
            logger.error(e, e);
            printMsg(response, "数据错误，导出失败!");

        } finally {
            try {
                in.close();
                out.close();
                FileUtil.deleteDirectory(path);
            } catch (Exception e) {
                logger.error("导出文件，关闭流异常," + e.getMessage());
            }
        }

        return null;
    }

}
