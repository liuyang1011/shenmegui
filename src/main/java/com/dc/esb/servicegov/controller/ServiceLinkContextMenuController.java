package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.service.SystemService;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.ServiceInvokeViewBean;
import org.apache.commons.lang.StringUtils;
import org.mvel2.ast.Proto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/23.
 */
@Controller
@RequestMapping("/serviceLinkContextMenu")
public class ServiceLinkContextMenuController {
    @Autowired
    private SystemLogServiceImpl systemLogService;

    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private InterfaceInvokeServiceImpl interfaceInvokeService;
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private InterfaceServiceImpl interfaceService;
    @Autowired
    private ProtocolServiceImpl protocolService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private SystemServiceImpl systemService;
    /**
     * 基本信息界面
     * @param invokeId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/baseInfo", headers = "Accept=application/json")
    public ModelAndView baseInfo(String invokeId) {
        ModelAndView mv = new ModelAndView("serviceLink/contextMenu/baseInfo");
        ServiceInvoke serviceInvoke = serviceInvokeService.findUniqueBy("invokeId", invokeId);
        mv.addObject("serviceInvoke", serviceInvoke);
        String interfaceName = "";
        String interfaceEcode = "";
        if(null != serviceInvoke.getInter()){
            interfaceName = serviceInvoke.getInter().getInterfaceName();
            interfaceEcode = serviceInvoke.getInter().getEcode();
        }
        mv.addObject("interfaceName", interfaceName);
        mv.addObject("interfaceEcode", interfaceEcode);
        String type = "";
        if(Constants.INVOKE_TYPE_CONSUMER.equals(serviceInvoke.getType())){
            type = "调用方";
        }
        if(Constants.INVOKE_TYPE_PROVIDER.equals(serviceInvoke.getType())){
            type = "提供方";
        }
        mv.addObject("type", type);
        return mv;
    }

    /**
     * 判断接口是否关联了协议
     * @param invokeId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/checkProtocol", headers = "Accept=application/json")
    public @ResponseBody boolean checkProtocol(String invokeId) {
        ServiceInvoke serviceInvoke = serviceInvokeService.findUniqueBy("invokeId", invokeId);
        if(null != serviceInvoke){
            Protocol protocol = protocolService.findUniqueBy("protocolId", serviceInvoke.getProtocolId());
            if(null != protocol){
               return true;
            }else{
               return false;
            }
        }else{
            return false;
        }
    }
    /**
     *消息协议界面
     * @param invokeId serviceInvoke id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/messageProtocolPage", headers = "Accept=application/json")
    public ModelAndView messageProtocolPage(String invokeId) {
        ModelAndView mv = new ModelAndView("serviceLink/contextMenu/messageProtocol");
        ServiceInvoke serviceInvoke = serviceInvokeService.findUniqueBy("invokeId", invokeId);
        Protocol protocol = protocolService.findUniqueBy("protocolId", serviceInvoke.getProtocolId());
        mv.addObject("protocol", protocol);
        return mv;
    }

    /**
     * 服务信息界面
     * @param invokeId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/serviceInfo", headers = "Accept=application/json")
    public ModelAndView serviceInfo(String invokeId) {
        ModelAndView mv = new ModelAndView("serviceLink/contextMenu/serviceInfo");
        ServiceInvoke serviceInvoke = serviceInvokeService.findUniqueBy("invokeId", invokeId);
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        // 根据serviceId获取service信息
        if (StringUtils.isNotEmpty(serviceId)) {
            Service service = serviceService.getById(serviceId);
            if (service != null) {
                mv.addObject("service", service);
            }
            if (StringUtils.isNotEmpty(operationId)) {
                // 根据serviceId,operationId获取operation信息
                Operation operation = operationService.getOperation(serviceId, operationId);
                if (operation != null) {
                    mv.addObject("operation", operation);
                    List<?> systemList = systemService.getAll();
                    mv.addObject("systemList", systemList);
                }
            }
        }
        return mv;
    }
    /**
     * 根据invokeId查询interfaceId
     * @param invokeId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getInterfaceId", headers = "Accept=application/json")
    public @ResponseBody
    Map<String, String> getInterfaceId(String invokeId) {
        Map<String, String> map = new HashMap<String, String>();
        String interfaceId = "";
        ServiceInvoke serviceInvoke = serviceInvokeService.findUniqueBy("invokeId", invokeId);
        if(null != serviceInvoke){
            interfaceId = serviceInvoke.getInterfaceId();
        }
        map.put("interfaceId", interfaceId);
        return map;
    }
}
