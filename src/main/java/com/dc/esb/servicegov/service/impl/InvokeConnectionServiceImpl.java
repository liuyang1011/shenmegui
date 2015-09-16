package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.InvokeConnectionDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.InterfaceInvoke;
import com.dc.esb.servicegov.entity.InvokeConnection;
import com.dc.esb.servicegov.service.InterfaceInvokeService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentfxz on 15/7/13.
 */
@Service
@Transactional
public class InvokeConnectionServiceImpl extends AbstractBaseService<InvokeConnection, String> {

    @Autowired
    private InvokeConnectionDAOImpl invokeConnectionDAO;
    @Autowired
    private InterfaceInvokeService interfaceInvokeService;

    @Override
    public HibernateDAO<InvokeConnection, String> getDAO() {
        return invokeConnectionDAO;
    }

    /**
     * TODO 注意环形递归
     * 根据交易链路的某个起点 查询交易链路
     *
     * @param sourceId
     * @return
     */
    //TODO 调用自己死循环
    public List<InvokeConnection> getConnectionsStartWith(String sourceId) {
        List<InvokeConnection> connections = new ArrayList<InvokeConnection>();
        List<InterfaceInvoke> invokeRelations = interfaceInvokeService.findBy("consumerInvokeId", sourceId);

        List<InvokeConnection> startConnections = getDAO().findBy("sourceId", sourceId);
        connections.addAll(startConnections);
        for (InvokeConnection invokeConnection : startConnections) {
            String targetId = invokeConnection.getTargetId();
            if (null != targetId) {
                connections.addAll(getConnectionsStartWith(targetId));
            }
        }
        for (InterfaceInvoke interfaceInvoke : invokeRelations) {
            boolean add = true;
            for (InvokeConnection connection : connections) {
                if (connection.getSourceId().equalsIgnoreCase(interfaceInvoke.getConsumerInvokeId()) && connection.getTargetId().equalsIgnoreCase(interfaceInvoke.getProviderInvokeId())) {
                    add = false;
                    break;
                }
            }
            if (add) {
                InvokeConnection invokeConnection = new InvokeConnection();
                invokeConnection.setSourceId(interfaceInvoke.getConsumerInvokeId());
                invokeConnection.setTargetId(interfaceInvoke.getProviderInvokeId());
                connections.add(invokeConnection);
            }
        }
        return connections;
    }

    /**
     * TODO 注意环形递归
     * 根据交易链路的某个子节点 查询交易链路血缘分析
     *
     * @param sourceId
     * @return
     */
    public List<InvokeConnection> getConnectionsEndWith(String targetId) {
        List<InvokeConnection> connections = new ArrayList<InvokeConnection>();
        List<InvokeConnection> endConnections = getDAO().findBy("targetId", targetId);
        connections.addAll(endConnections);
        for (InvokeConnection invokeConnection : endConnections) {
            String sourceId = invokeConnection.getSourceId();
            if (null != sourceId) {
                connections.addAll(getConnectionsEndWith(sourceId));
            }
        }
        return connections;
    }
}
