package com.dc.esb.servicegov.controller;

import java.util.*;

import com.dc.esb.servicegov.entity.InterfaceInvoke;
import com.dc.esb.servicegov.entity.jsonObj.ServiceInvokeJson;
import com.dc.esb.servicegov.service.impl.InterfaceInvokeServiceImpl;
import com.dc.esb.servicegov.service.impl.InterfaceServiceImpl;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.JSONUtil;
import com.dc.esb.servicegov.vo.ServiceInvokeInfoVO;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.vo.ServiceInvokeViewBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vincentfxz on 15/7/8.
 */
@Controller
@RequestMapping("/serviceLink")
public class ServiceInvokeController {
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private InterfaceInvokeServiceImpl interfaceInvokeService;

    @RequestMapping(method = RequestMethod.GET, value = "/list", headers = "Accept=application/json")
    public
    @ResponseBody
    List<ServiceInvokeViewBean> getAllTreeBean() {
        List<ServiceInvokeViewBean>  serviceInvokeViewBeans = new ArrayList<ServiceInvokeViewBean>();
        List<ServiceInvoke> serviceInvokes =  serviceInvokeService.getAll();
        for(ServiceInvoke serviceInvoke : serviceInvokes){
            ServiceInvokeViewBean serviceInvokeViewBean = new ServiceInvokeViewBean(serviceInvoke);
            serviceInvokeViewBeans.add(serviceInvokeViewBean);
        }
        return serviceInvokeViewBeans;
    }

//根据系统id查询接口列表

    /**
     * @param systemId
     * @return
     */
    @RequestMapping("/getInterface")
    @ResponseBody
    public Map<String, Object> getInterface(String systemId, String type, String text) throws  Throwable{
        List<ServiceInvokeJson> rows = serviceInvokeService.getDistinctInter(systemId, type,text);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    /**
     * @param serviceId
     * @param operationId
     * @param systemId
     * @return
     */
    @RequestMapping("/getInterfaceByOSS")
    @ResponseBody
    public Map<String, Object> getInterfaceByOSS(String serviceId, String operationId, String systemId) {
        List<?> rows = serviceInvokeService.findJsonBySO(serviceId, operationId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteInvoke", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean deleteInvoke(@RequestBody LinkedHashMap map) {
        String serviceId = map.get("serviceId").toString();
        String operationId = map.get("operationId").toString();
        String[] consumers = map.get("consumers").toString().replaceAll("，", ",").split(",");
        String[] providers = map.get("providers").toString().replaceAll("，",",").split(",");
        String type = map.get("type").toString();
        String interfaceId = map.get("interfaceId").toString();
        String[] consumerIds = map.get("consumerIds").toString().replaceAll("，", ",").split(",");
        String[] providerIds = map.get("providerIds").toString().replaceAll("，",",").split(",");
        return true;
    }
    @RequestMapping(method = RequestMethod.POST, value = "/addServiceLink", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean addServiceLink(@RequestBody List list) {
        List consumers = (ArrayList)list.get(0);
        List providers = (ArrayList)list.get(1);
        List<ServiceInvoke> temp = new ArrayList<ServiceInvoke>();
        for (int i = 0; i < providers.size(); i++) {
            for (int j = 0; j < consumers.size(); j++) {
                LinkedHashMap<String, Object> mapConsumer = (LinkedHashMap)consumers.get(j);
                LinkedHashMap<String, Object> mapProvider = (LinkedHashMap)providers.get(i);
                Long l = (Long)mapConsumer.get("invokeId");
                String invokeId = "" + l;
                String systemId = mapConsumer.get("systemId").toString();
                String systemChineseName = mapConsumer.get("systemChineseName").toString();
                String interfaceId = mapConsumer.get("interfaceId").toString();
                String interfaceName = mapConsumer.get("interfaceName").toString();
                String type = mapConsumer.get("type").toString();
                String isStandard = mapConsumer.get("isStandard").toString();
                String serviceId =mapConsumer.get("serviceId").toString();
                String operationId =mapConsumer.get("operationId").toString();
                ServiceInvoke c;
                ServiceInvoke c2 = serviceInvokeService.getById(invokeId);
                if(null != c2){
                    c = c2;
                }else{
                    //是否已经存在标准消费方
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("isStandard",isStandard);
                    params.put("operationId",operationId);
                    params.put("serviceId",serviceId);
                    params.put("systemId",systemId);
                    List<ServiceInvoke> list1 = serviceInvokeService.findBy(params);
                    if(list1.size()>0){
                        c = list1.get(0);
                    }else{
                        c = new ServiceInvoke();
                        c.setInvokeId(invokeId);
                        c.setSystemId(systemId);
                        c.setInterfaceId(interfaceId);
                        c.setType(type);
                        c.setIsStandard(isStandard);
                        c.setServiceId(serviceId);
                        c.setOperationId(operationId);
                        serviceInvokeService.insert(c);
                    }
                }
                String invokeId2 = mapProvider.get("invokeId").toString();
                String systemId2 = mapProvider.get("systemId").toString();
                String systemChineseName2 = mapProvider.get("systemChineseName").toString();
                String interfaceId2 = mapProvider.get("interfaceId").toString();
                String interfaceName2 = mapProvider.get("interfaceName").toString();
                String type2 = mapProvider.get("type").toString();
                //是否已经存在提供方
                Map<String,String> params = new HashMap<String, String>();
                params.put("operationId",operationId);
                params.put("serviceId",serviceId);
                params.put("systemId",systemId2);
                params.put("interfaceId",interfaceId2);
                List<ServiceInvoke> list1 = serviceInvokeService.findBy(params);
                ServiceInvoke p;
                if(list1.size()>0){
                    p = list1.get(0);
                }else{

                    p = new ServiceInvoke();
                    if("".equals(interfaceId2)){
                       p.setIsStandard("0");
                    }else{
                        p.setIsStandard("1");
                    }
                    p.setInterfaceId(interfaceId2);
                    p.setOperationId(operationId);
                    p.setServiceId(serviceId);
                    p.setSystemId(systemId2);
                    p.setType(Constants.INVOKE_TYPE_PROVIDER);
//                    //判断是否本次重复
//                    int index =  temp.indexOf(p);
//                    if(index >= 0){
//                        p = temp.get(index);
//                    }else{
//                        temp.add(p);
//                        serviceInvokeService.insert(p);
//                    }
                    serviceInvokeService.insert(p);

                }
                //查看是否存在调用关系
                Map<String,String> map = new HashMap<String, String>();
                map.put("providerInvokeId",p.getInvokeId());
                map.put("consumerInvokeId",c.getInvokeId());
                List<InterfaceInvoke> list3 = interfaceInvokeService.findBy(map);
                if(list3.size()>0) continue;
                //配置调用关系
                InterfaceInvoke interfaceInvoke = new InterfaceInvoke();
                interfaceInvoke.setProviderInvokeId(p.getInvokeId());
                interfaceInvoke.setConsumerInvokeId(c.getInvokeId());
                interfaceInvokeService.insert(interfaceInvoke);
            }
        }
        return true;
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
