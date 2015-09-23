package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.entity.System;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.util.GraphColumn;
import com.dc.esb.servicegov.util.GraphConnection;
import com.dc.esb.servicegov.util.GraphNode;
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

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

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

    private int initX = 50;
    private int initY = 100;

    /**
     * 构造交易链路节点
     *
     * @param serviceInvoke
     * @return
     */
    private GraphNode constructGraphNode(ServiceInvoke serviceInvoke) {
        GraphNode sourceGraphNode = new GraphNode();
        sourceGraphNode.setId(serviceInvoke.getInvokeId());
        String name = "";
        String interfaceId = serviceInvoke.getInterfaceId();
        String interfaceName = null;
        Interface inter = serviceInvoke.getInter();
        if (null != inter) {
            interfaceName = inter.getInterfaceName();
        }
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        if (null != interfaceId) {
            name = interfaceId + "(" + interfaceName + ")";
        } else {
            name = serviceId + operationId;
        }
        sourceGraphNode.setName(name);
        sourceGraphNode.setLeft(String.valueOf(initX += 250));
        sourceGraphNode.setTop(String.valueOf(initY));
        sourceGraphNode.setType("table");
        return sourceGraphNode;
    }

    /**
     * gou
     *
     * @param connections
     * @return
     */
    private Collection<GraphConnection> constructGraphConnection(List<InvokeConnection> connections) {
        Collection<GraphConnection> graphConnections = new ArrayList<GraphConnection>();
        for (InvokeConnection invokeConnection : connections) {
            GraphConnection graphConnection = new GraphConnection();
            graphConnection.setSource(invokeConnection.getSourceId());
            graphConnection.setTarget(invokeConnection.getTargetId());
            graphConnections.add(graphConnection);
        }
        return graphConnections;
    }

    /**
     * 构建交易链路节点中的属性
     *
     * @param serviceInvoke
     * @return
     */
    private List<GraphColumn> constructGraphColumns(ServiceInvoke serviceInvoke) {
        List<GraphColumn> graphColumns = new ArrayList<GraphColumn>();
        Interface inter = serviceInvoke.getInter();
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        if (null != serviceId && null != operationId && null != inter) {
            Service service = serviceService.getById(serviceId);
            Operation operation = operationService.getOperation(serviceId, operationId);
            if (null != service && null != operation) {
                String serviceName = service.getServiceName();
                String operationName = operation.getOperationName();
                GraphColumn serviceGraphColumn = new GraphColumn();
                serviceGraphColumn.setId("服务场景:" + serviceId + operationId + "(" + serviceName + ":" + operationName + ")");
                graphColumns.add(serviceGraphColumn);
            }
        }

        GraphColumn iconColumn = new GraphColumn();
        iconColumn.setId("");
        List<GraphColumn.Icon> icons = new ArrayList<GraphColumn.Icon>();
        if (null != inter) {
            GraphColumn.Icon interIcon = new GraphColumn.Icon();
            interIcon.setIcon("puzzle-piece");
            icons.add(interIcon);
        }
        if (null != serviceId && null != operationId) {
            GraphColumn.Icon serviceIcon = new GraphColumn.Icon();
            serviceIcon.setIcon("flag");
            icons.add(serviceIcon);
        }
        System system = serviceInvoke.getSystem();
        if (null != system) {
            String systemAB = system.getSystemAb();
            String systemName = system.getSystemChineseName();
            GraphColumn systemGraphColumn = new GraphColumn();
            systemGraphColumn.setId("系统:" + systemAB + "(" + systemName + ")");
            graphColumns.add(systemGraphColumn);
        }
        if (icons.size() > 0) {
            iconColumn.setIcons(icons);
        }
        graphColumns.add(iconColumn);
        return graphColumns;
    }

    /**
     * 添加获取交易链路的数据节点的方法
     *
     * @param nodeId
     * @return
     */
    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLink/start/node/{nodeId}", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Collection<?>> getServiceLinkStartWith(@PathVariable("nodeId") String nodeId) {
        initX = 0;
        initY = 100;
        List<InvokeConnection> invokeConnections = invokeConnectionService.getConnectionsStartWith(nodeId, new ArrayList<String>());
        Map<String, Collection<?>> renderObj = new HashMap<String, Collection<?>>();
        Map<String, GraphNode> nodes = new HashMap<String, GraphNode>();
        for (InvokeConnection invokeConnection : invokeConnections) {
            String sourceId = invokeConnection.getSourceId();
            String targetId = invokeConnection.getTargetId();
            if (null != sourceId && !nodes.containsKey(sourceId)) {
                ServiceInvoke sourceServiceInvoke = serviceInvokeService.getById(invokeConnection.getSourceId());
                GraphNode sourceGraphNode = constructGraphNode(sourceServiceInvoke);
                List<GraphColumn> columns = constructGraphColumns(sourceServiceInvoke);
                sourceGraphNode.setColumns(columns);
                nodes.put(sourceId, sourceGraphNode);
            }
            if (null != targetId && !nodes.containsKey(targetId)) {
                ServiceInvoke targetServiceInvoke = serviceInvokeService.getById(invokeConnection.getTargetId());
                GraphNode targetGraphNode = constructGraphNode(targetServiceInvoke);
                List<GraphColumn> columns = constructGraphColumns(targetServiceInvoke);
                targetGraphNode.setColumns(columns);
                nodes.put(targetId, targetGraphNode);
            }
        }
        Collection<GraphConnection> graphConnections = constructGraphConnection(invokeConnections);
        Collection<GraphNode> nodeList = nodes.values();
        renderObj.put("nodes", nodeList);
        renderObj.put("edges", graphConnections);
        return renderObj;
    }


    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLink/system/{systemId}", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String, Object> getServiceLink(@PathVariable("systemId") String systemId, HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if (null != interfaceName) {
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e, e);
            }
        }
        //根据接口名查交易码
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("interfaceName", interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if (i == 0) {
                interIds = "'" + interfaceList.get(i).getInterfaceId() + "'";
            } else {
                interIds += ",'" + interfaceList.get(i).getInterfaceId() + "'";
            }
        }

        String serviceId = req.getParameter("serviceId");
        String serviceName = req.getParameter("serviceName");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from ServiceInvoke c where systemId='" + systemId + "' ");
        if (null != interfaceId && !"".equals(interfaceId)) {
            hql.append(" and interfaceId like ?");
            searchConds.add(new SearchCondition("interfaceId", "%" + interfaceId + "%"));
        }
        if (null != serviceId && !"".equals(serviceId)) {
            hql.append(" and serviceId like ?");
            searchConds.add(new SearchCondition("serviceId", "%" + serviceId + "%"));
        }
        if (null != interfaceName && !"".equals(interfaceName)) {
            hql.append(" and interfaceId in(" + interIds + ")");
        }

        Page page = serviceInvokeService.findPage(hql.toString(), rowCount, searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page, searchConds);

        List<ServiceInvokeInfoVO> serviceInvokeInfoVOs = new ArrayList<ServiceInvokeInfoVO>();

        for (ServiceInvoke serviceInvoke : serviceInvokes) {
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if (serviceInvoke.getOperationId() != null && serviceInvoke.getServiceId() != null) {
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
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
                if (serviceInvokeInfoVO.getServiceName().indexOf(serviceName) >= 0) {
                    serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
                }
            } else {
                serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", serviceInvokeInfoVOs);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLinkNode/system/{systemId}", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String, Object> getServiceLinkNodes(@PathVariable("systemId") String systemId, HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if (null != interfaceName) {
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e, e);
            }
        }
        //根据接口名查交易码
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("interfaceName", interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if (i == 0) {
                interIds = "'" + interfaceList.get(i).getInterfaceId() + "'";
            } else {
                interIds += ",'" + interfaceList.get(i).getInterfaceId() + "'";
            }
        }

        String serviceId = req.getParameter("serviceId");
        String serviceName = req.getParameter("serviceName");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        StringBuffer hql = new StringBuffer("select c from ServiceInvoke c where systemId='" + systemId + "' ");
        if (null != interfaceId && !"".equals(interfaceId)) {
            hql.append(" and interfaceId like ?");
            searchConds.add(new SearchCondition("interfaceId", "%" + interfaceId + "%"));
        }
        if (null != serviceId && !"".equals(serviceId)) {
            hql.append(" and serviceId like ?");
            searchConds.add(new SearchCondition("serviceId", "%" + serviceId + "%"));
        }
        if (null != interfaceName && !"".equals(interfaceName)) {
            hql.append(" and interfaceId in(" + interIds + ")");
        }

        Page page = serviceInvokeService.findPage(hql.toString(), rowCount, searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page, searchConds);
        List<ServiceLinkNodeVO> serviceLinkNodeVOs = new ArrayList<ServiceLinkNodeVO>();
        List<ServiceLinkNode> serviceLinkNodes = serviceLinkNodeService.getAll();

        for (ServiceInvoke serviceInvoke : serviceInvokes) {
            ServiceLinkNodeVO serviceLinkNodeVO = new ServiceLinkNodeVO(serviceInvoke);
            if (serviceInvoke.getOperationId() != null && serviceInvoke.getServiceId() != null) {
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
                Service service = serviceService.getById(serviceInvoke.getServiceId());
                serviceLinkNodeVO.setServiceName(service.getServiceName());
                serviceLinkNodeVO.setOperationName(operation.getOperationName());
            }
            for (ServiceLinkNode serviceLinkNode : serviceLinkNodes) {
                if (serviceInvoke.getInvokeId().equalsIgnoreCase(serviceLinkNode.getServiceInvokeId())) {
                    serviceLinkNodeVO.setCondition(serviceLinkNode.getCondition());
                    serviceLinkNodeVO.setEsbAccessPattern(serviceLinkNode.getEsbAccessPattern());
                }
            }
            if (null != serviceName && !"".equals(serviceName)) {
                try {
                    serviceName = URLDecoder.decode(serviceName, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    log.error(e, e);
                }
                if (serviceLinkNodeVO.getServiceName().indexOf(serviceName) >= 0) {
                    serviceLinkNodeVOs.add(serviceLinkNodeVO);
                }
            } else {
                serviceLinkNodeVOs.add(serviceLinkNodeVO);
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", serviceLinkNodeVOs);
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getServiceLink", headers = "Accept=application/json")
    public
    @ResponseBody
    HashMap<String, Object> getServiceLinks(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String interfaceId = req.getParameter("interfaceId");
        String interfaceName = req.getParameter("interfaceName");
        if (null != interfaceName) {
            try {
                interfaceName = URLDecoder.decode(interfaceName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error(e, e);
            }
        }
        //根据接口名查交易码
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("interfaceName", interfaceName);
        List<Interface> interfaceList = interfaceService.findLikeAnyWhere(map1);
        String interIds = "";
        for (int i = 0; i < interfaceList.size(); i++) {
            if (i == 0) {
                interIds = "'" + interfaceList.get(i).getInterfaceId() + "'";
            } else {
                interIds += ",'" + interfaceList.get(i).getInterfaceId() + "'";
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
        if (null != interfaceName && !"".equals(interfaceName)) {
            hql.append(" and interfaceId in(" + interIds + ")");
        }
        Page page = serviceInvokeService.findPage(hql.toString(), rowCount, searchConds);
        page.setPage(pageNo);

        List<ServiceInvoke> serviceInvokes = serviceInvokeService.findBy(hql.toString(), page, searchConds);

        List<ServiceInvokeInfoVO> serviceInvokeInfoVOs = new ArrayList<ServiceInvokeInfoVO>();
        for (ServiceInvoke serviceInvoke : serviceInvokes) {
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if (serviceInvoke.getOperationId() != null && serviceInvoke.getServiceId() != null) {
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
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
                if (serviceInvokeInfoVO.getServiceName().indexOf(serviceName) >= 0) {
                    serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
                }
            } else {
                serviceInvokeInfoVOs.add(serviceInvokeInfoVO);
            }
        }

        HashMap<String, Object> map = new HashMap<String, Object>();
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
        for (ServiceInvoke serviceInvoke : serviceInvokes) {
            ServiceInvokeInfoVO serviceInvokeInfoVO = new ServiceInvokeInfoVO(serviceInvoke);
            if (serviceInvoke.getOperationId() != null && serviceInvoke.getServiceId() != null) {
                Operation operation = operationService.getOperation(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
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
    public
    @ResponseBody
    List<InvokeConnection> getInvokeConnetcion(@PathVariable("sourceId") String sourceId) {
        return invokeConnectionService.getConnectionsStartWith(sourceId, new ArrayList<String>());
    }

    @RequiresPermissions({"link-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/parentInvokeConnections/sourceId/{sourceId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<InvokeConnection> parentInvokeConnections(@PathVariable("sourceId") String sourceId) {
        return invokeConnectionService.getConnectionsEndWith(sourceId, new ArrayList<String>());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean save(@RequestBody InvokeConnection[] connections) {
        for (InvokeConnection connection : connections) {
            String sourceId = connection.getSourceId();
            String targetId = connection.getTargetId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("sourceId", sourceId);
            params.put("targetId", targetId);
            if (sourceId.equals(targetId)) return false;
            List<InvokeConnection> existedConnections = invokeConnectionService.findBy(params);
            if (null == existedConnections) {
                invokeConnectionService.save(connection);
            } else if (existedConnections.size() == 0) {
                invokeConnectionService.save(connection);
            }
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean delete(@RequestBody InvokeConnection[] connections) {
        for (InvokeConnection connection : connections) {
            String sourceId = connection.getSourceId();
            String targetId = connection.getTargetId();
            Map<String, String> params = new HashMap<String, String>();
            params.put("sourceId", sourceId);
            params.put("targetId", targetId);
            List<InvokeConnection> existedConnections = invokeConnectionService.findBy(params);
            if (null != existedConnections) {
                for (InvokeConnection invokeConnection : existedConnections) {
                    invokeConnectionService.delete(invokeConnection);
                }
            }
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean modify(@RequestBody ServiceLinkNode serviceLinkNode) {
        serviceLinkNodeService.save(serviceLinkNode);
        return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getExtInfo/{invokeId}", headers = "Accept=application/json")
    public
    @ResponseBody
    ServiceLinkNode getExtInfo(@PathVariable("invokeId") String invokeId) {
        List<ServiceLinkNode> serviceLinkNodes = serviceLinkNodeService.findBy("serviceInvokeId", invokeId);
        if (serviceLinkNodes.size() > 0) {
            return serviceLinkNodes.get(0);
        } else {
            return null;
        }

    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
