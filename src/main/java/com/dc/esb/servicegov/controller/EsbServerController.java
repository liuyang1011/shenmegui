package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.EsbServer;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.Service;
import com.dc.esb.servicegov.export.impl.ConfigBathGenerator;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.service.ServiceService;
import com.dc.esb.servicegov.service.impl.EsbServerServiceImpl;
import com.dc.esb.servicegov.service.impl.ServiceServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import com.dc.esc.jmx.register.PublishServiceProxy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/1/19.
 */
@Controller
@RequestMapping("/esbServer")
public class EsbServerController {
    protected Log logger = LogFactory.getLog(getClass());
    @Autowired
    private EsbServerServiceImpl esbServerService;
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    ConfigBathGenerator configBathGenerator;


    @RequestMapping(value = "/getServerList")
    public
    @ResponseBody
    List<EsbServer> getServerList() {
        List<EsbServer> list = esbServerService.getAll();
        return list;
    }

    @RequestMapping(value = "/saveAdd")
    public
    @ResponseBody
    boolean saveAdd(EsbServer esbServer) {
        esbServer.setServerId(UUID.randomUUID().toString());
        esbServerService.save(esbServer);
        return true;
    }

    @RequestMapping(value = "/editPage")
    public ModelAndView editPage(String serverId) {
        ModelAndView mv = new ModelAndView("esb/server_edit");

        EsbServer esbServer = esbServerService.findUniqueBy("serverId", serverId);
        mv.addObject("esbServer", esbServer);

        return mv;
    }

    @RequestMapping(value = "/saveEdit")
    public
    @ResponseBody
    boolean saveEdit(EsbServer esbServer) {
        esbServerService.save(esbServer);
        return true;
    }

    @RequestMapping(value = "/deleteById")
    public
    @ResponseBody
    boolean deleteById(String serverId) {
        esbServerService.deleteById(serverId);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/configSync/{optionFlag}/{dicSync}/{serverStr}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean configSync(HttpServletRequest request, HttpServletResponse response, @RequestBody ConfigVO[] list, @PathVariable(value = "optionFlag") String optionFlag, @PathVariable(value = "dicSync") String dicSync, @PathVariable(value = "serverStr") String serverStr) {
        OperationLog operationLog = systemLogService.record("配置文件", "ESB同步", "");

        try {
            String serviceDef = "";
            String serviceDefContent = "";
            String toFile = "";
            String toFileContent = "";
            String fromFile = "";
            String fromFileContent = "";
            ConfigListVO configListVO = new ConfigListVO();
            List<ConfigVO> configVOs = Arrays.asList(list);
            configListVO.setList(configVOs);
            String path = configBathGenerator.generate(request, configListVO);//一条
            ConfigVO configVo = configVOs.get(0);
            PublishServiceProxy proxy = new PublishServiceProxy();
            File dir = new File(path);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File file : files) {
                    if (file.getName().startsWith("out")) {
                        File[] configFiles = file.listFiles();
                        for (File configFile : configFiles) {
                            String fileName = configFile.getName();
                            if (fileName.startsWith("service")) {
                                serviceDef = configFile.getName();
                                serviceDefContent = new String(readFile(configFile));
                            } else if (fileName.contains("to")) {
                                toFile = fileName;
                                toFileContent = new String(readFile(configFile));
                            } else if (fileName.contains("from")) {
                                fromFile = fileName;
                                fromFileContent = new String(readFile(configFile));
                            }
                        }
                        Service service = serviceService.findUniqueBy("serviceId", configVo.getServiceId());
                        proxy.pubProvider(configVo.getServiceId() + configVo.getOperationId(), service.getServiceName(), configVo.getProviderName(), configVo.getVersionId(),
                                configVo.getVersionAutoId(), configVo.getProInterfaceName(), configVo.getProGeneratorName(), serviceDef,
                                serviceDefContent, toFile, toFileContent, fromFile, fromFileContent, "userId");
                    } else if (file.getName().startsWith("in")) {
                        File[] configFiles = file.listFiles();
                        for (File configFile : configFiles) {
                            String fileName = configFile.getName();
                            if (fileName.contains("to")) {
                                toFile = fileName;
                                toFileContent = new String(readFile(configFile));
                            } else if (fileName.contains("from")) {
                                fromFile = fileName;
                                fromFileContent = new String(readFile(configFile));
                            }
                        }
                        proxy.pubConsumer(configVo.getServiceId() + configVo.getOperationId(), configVo.getVersionId(),configVo.getVersionAutoId(),
                                configVo.getConsumerName(), configVo.getConInterfaceName(), configVo.getConGeneratorName(), toFile,
                                toFileContent, fromFile, fromFileContent, "userId");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("配置文件同步错误", e);
        }
        systemLogService.updateResult(operationLog);
        return true;
    }

    private byte[] readFile(File configFile) throws IOException {
        int length = (int) configFile.length();
        byte[] contentBytes = new byte[length];
        DataInputStream in = new DataInputStream(new FileInputStream(configFile));
        in.readFully(contentBytes);
        return contentBytes;
    }


}
