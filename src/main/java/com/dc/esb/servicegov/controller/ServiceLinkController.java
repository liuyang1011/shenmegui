package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.vo.ServiceInvokeInfoVO;
import com.dc.esb.servicegov.vo.ServiceLinkNodeVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vincentfxz on 15/7/9.
 */
@Controller
@RequestMapping("/serviceLink")
public class ServiceLinkController {

    private static final Log log = LogFactory.getLog(ServiceLinkController.class);

    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private InvokeConnectionServiceImpl invokeConnectionService;
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private InterfaceServiceImpl interfaceService;
    @Autowired
    private ServiceLinkNodeServiceImpl serviceLinkNodeService;

    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLink/system/{systemId}", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String,Object> getServiceLink(@PathVariable("systemId") String systemId,HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if(null != interfaceName){
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e,e);
            }
        }
        //根据接口名查交易码
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("interfaceName",interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if(i==0){
                interIds = "'"+interfaceList.get(i).getInterfaceId()+"'";
            }else{
                interIds += ",'"+interfaceList.get(i).getInterfaceId()+"'";
            }
        }

        String serviceId = req.getParameter("serviceId");
        String serviceName = req.getParameter("serviceName");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from ServiceInvoke c where systemId='"+systemId+"' ");
        if (null != interfaceId && !"".equals(interfaceId)) {
            hql.append(" and interfaceId like ?");
            searchConds.add(new SearchCondition("interfaceId", "%" + interfaceId + "%"));
        }
        if (null != serviceId && !"".equals(serviceId)) {
            hql.append(" and serviceId like ?");
            searchConds.add(new SearchCondition("serviceId", "%" + serviceId + "%"));
        }
        if(null != interfaceName && !"".equals(interfaceName)){
            hql.append(" and interfaceId in("+interIds+")");
        }

        Page page = serviceInvokeService.findPage(hql.toString(),rowCount,searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page,searchConds);

        List<ServiceInvokeInfoVO> serviceInvokeInfoVOs = new ArrayList<ServiceInvokeInfoVO>();

        for(ServiceInvoke serviceInvoke : serviceInvokes){
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if(serviceInvoke.getOperationId()!=null && serviceInvoke.getServiceId()!=null){
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(),serviceInvoke.getOperationId());
                Service service = serviceService.getById(serviceInvoke.getServiceId());
                serviceInvokeInfoVO.setServiceName(service.getServiceName());
                serviceInvokeInfoVO.setOperationName(operation.getOperationName());
            }
            if (null != serviceName && !"".equals(serviceName)) {
                try {
                    serviceName = URLDecoder.decode(serviceName, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(serviceInvokeInfoVO.getServiceName().indexOf(serviceName) >= 0){
                    serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
                }
            }else{
                serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
            }
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", serviceInvokeInfoVOs);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLinkNode/system/{systemId}", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String, Object> getServiceLinkNodes(@PathVariable("systemId") String systemId, HttpServletRequest req){
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if(null != interfaceName){
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e,e);
            }
        }
        //根据接口名查交易码
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("interfaceName",interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if(i==0){
                interIds = "'"+interfaceList.get(i).getInterfaceId()+"'";
            }else{
                interIds += ",'"+interfaceList.get(i).getInterfaceId()+"'";
            }
        }

        String serviceId = req.getParameter("serviceId");
        String serviceName = req.getParameter("serviceName");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from ServiceInvoke c where systemId='"+systemId+"' ");
        if (null != interfaceId && !"".equals(interfaceId)) {
            hql.append(" and interfaceId like ?");
            searchConds.add(new SearchCondition("interfaceId", "%" + interfaceId + "%"));
        }
        if (null != serviceId && !"".equals(serviceId)) {
            hql.append(" and serviceId like ?");
            searchConds.add(new SearchCondition("serviceId", "%" + serviceId + "%"));
        }
        if(null != interfaceName && !"".equals(interfaceName)){
            hql.append(" and interfaceId in("+interIds+")");
        }

        Page page = serviceInvokeService.findPage(hql.toString(),rowCount,searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page,searchConds);
        List<ServiceLinkNodeVO> serviceLinkNodeVOs = new ArrayList<ServiceLinkNodeVO>();
        List<ServiceLinkNode> serviceLinkNodes = serviceLinkNodeService.getAll();

        for(ServiceInvoke serviceInvoke : serviceInvokes){
            ServiceLinkNodeVO serviceLinkNodeVO = new ServiceLinkNodeVO(serviceInvoke);
            if(serviceInvoke.getOperationId()!=null && serviceInvoke.getServiceId()!=null){
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(),serviceInvoke.getOperationId());
                Service service = serviceService.getById(serviceInvoke.getServiceId());
                serviceLinkNodeVO.setServiceName(service.getServiceName());
                serviceLinkNodeVO.setOperationName(operation.getOperationName());
            }
            for(ServiceLinkNode serviceLinkNode : serviceLinkNodes){
                if(serviceInvoke.getInvokeId().equalsIgnoreCase(serviceLinkNode.getServiceInvokeId())){
                    serviceLinkNodeVO.setCondition(serviceLinkNode.getCondition());
                    serviceLinkNodeVO.setEsbAccessPattern(serviceLinkNode.getEsbAccessPattern());
                }
            }
            if (null != serviceName && !"".equals(serviceName)) {
                try {
                    serviceName = URLDecoder.decode(serviceName, "utf-8");
                } catch (UnsupportedEncodingException e) {
                   log.error(e,e);
                }
                if(serviceLinkNodeVO.getServiceName().indexOf(serviceName) >= 0){
                    serviceLinkNodeVOs.add(serviceLinkNodeVO);
                }
            }else{
                serviceLinkNodeVOs.add(serviceLinkNodeVO);
            }
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", serviceLinkNodeVOs);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLink", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String,Object> getServiceLinks(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if(null != interfaceName){
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e,e);
            }
        }
        //根据接口名查交易码
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("interfaceName",interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if(i==0){
                interIds = "'"+interfaceList.get(i).getInterfaceId()+"'";
            }else{
                interIds += ",'"+interfaceList.get(i).getInterfaceId()+"'";
            }
        }

        String serviceId = req.getParameter("serviceId");
        String serviceName = req.getParameter("serviceName");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from ServiceInvoke c where ");
        if (null != interfaceId && !"".equals(interfaceId)) {
            hql.append(" interfaceId like ?");
            searchConds.add(new SearchCondition("interfaceId", "%" + interfaceId + "%"));
        }
        if (null != serviceId && !"".equals(serviceId)) {
            hql.append(" and serviceId like ?");
            searchConds.add(new SearchCondition("serviceId", "%" + serviceId + "%"));
        }
        if(null != interfaceName && !"".equals(interfaceName)){
            hql.append(" and interfaceId in("+interIds+")");
        }
        Page page = serviceInvokeService.findPage(hql.toString(),rowCount,searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page,searchConds);

        List<ServiceInvokeInfoVO> serviceInvokeInfoVOs = new ArrayList<ServiceInvokeInfoVO>();
        for(ServiceInvoke serviceInvoke : serviceInvokes){
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if(serviceInvoke.getOperationId()!=null && serviceInvoke.getServiceId()!=null){
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(),serviceInvoke.getOperationId());
                Service service = serviceService.getById(serviceInvoke.getServiceId());
                serviceInvokeInfoVO.setServiceName(service.getServiceName());
                serviceInvokeInfoVO.setOperationName(operation.getOperationName());
            }
            if (null != serviceName && !"".equals(serviceName)) {
                try {
                    serviceName = URLDecoder.decode(serviceName, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(serviceInvokeInfoVO.getServiceName().indexOf(serviceName) >= 0){
                    serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
                }
            }else{
                serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
            }
        }

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", serviceInvokeInfoVOs);
        return map;
    }

    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/serviceLinkInfo/system/{systemId}", headers = "Accept=application/json")
    public List<ServiceInvokeInfoVO> serviceLinkInfo(@PathVariable("systemId") String systemId) {
        List<ServiceInvokeInfoVO> serviceInvokeInfoVOs = new ArrayList<ServiceInvokeInfoVO>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("systemId", systemId);
        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(params);
        for(ServiceInvoke serviceInvoke : serviceInvokes){
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if(serviceInvoke.getOperationId()!=null && serviceInvoke.getServiceId()!=null){
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(),serviceInvoke.getOperationId());
                Service service = serviceService.getById(serviceInvoke.getServiceId());
                serviceInvokeInfoVO.setServiceName(service.getServiceName());
                serviceInvokeInfoVO.setOperationName(operation.getOperationName());
            }
            serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
        }
        return serviceInvokeInfoVOs;
    }

    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/invokeConnections/sourceId/{sourceId}", headers = "Accept=application/json")
    public @ResponseBody List<InvokeConnection> getInvokeConnetcion(@PathVariable("sourceId") String sourceId){
        return invokeConnectionService.getConnectionsStartWith(sourceId, new ArrayList<String>());
    }

    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/parentInvokeConnections/sourceId/{sourceId}", headers = "Accept=application/json")
    public @ResponseBody List<InvokeConnection> parentInvokeConnections(@PathVariable("sourceId") String sourceId){
        return invokeConnectionService.getConnectionsEndWith(sourceId,new ArrayList<String>());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", headers = "Accept=application/json")
    public @ResponseBody boolean save(@RequestBody InvokeConnection[] connections) {
        for(InvokeConnection connection : connections){
            String sourceId = connection.getSourceId();
            String targetId = connection.getTargetId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("sourceId", sourceId);
            params.put("targetId", targetId);
            if(sourceId.equals(targetId)) return false;
            List<InvokeConnection> existedConnections = invokeConnectionService.findBy(params);
            if(null == existedConnections){
                invokeConnectionService.save(connection);
            }else if(existedConnections.size() == 0){
                invokeConnectionService.save(connection);
            }
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete", headers = "Accept=application/json")
    public @ResponseBody boolean delete(@RequestBody InvokeConnection[] connections) {
        for(InvokeConnection connection : connections){
            String sourceId = connection.getSourceId();
            String targetId = connection.getTargetId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("sourceId", sourceId);
            params.put("targetId", targetId);
            List<InvokeConnection> existedConnections = invokeConnectionService.findBy(params);
            if(null != existedConnections){
                for(InvokeConnection invokeConnection : existedConnections){
                    invokeConnectionService.delete(invokeConnection);
                }
            }
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify", headers = "Accept=application/json")
    public @ResponseBody boolean modify(@RequestBody ServiceLinkNode serviceLinkNode) {
        serviceLinkNodeService.save(serviceLinkNode);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getExtInfo/{invokeId}", headers = "Accept=application/json")
    public @ResponseBody ServiceLinkNode getExtInfo(@PathVariable("invokeId") String invokeId){
        List<ServiceLinkNode> serviceLinkNodes = serviceLinkNodeService.findBy("serviceInvokeId", invokeId);
        if(serviceLinkNodes.size()> 0){
            return serviceLinkNodes.get(0);
        }else{
            return null;
        }

    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
