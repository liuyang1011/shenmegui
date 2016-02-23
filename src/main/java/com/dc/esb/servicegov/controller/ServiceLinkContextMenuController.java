package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.Interface;
import com.dc.esb.servicegov.entity.Protocol;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.vo.ServiceInvokeViewBean;
import org.mvel2.ast.Proto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
}
