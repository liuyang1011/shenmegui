package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.OLADAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.OLA;
import com.dc.esb.servicegov.entity.Operation;
import com.dc.esb.servicegov.service.OLAService;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OLAServiceImpl extends AbstractBaseService<OLA, String> implements OLAService {

    @Autowired
    private OLADAOImpl olaDAO;

    @Override
    public HibernateDAO<OLA, String> getDAO() {
        return olaDAO;
    }

    public List<OLA> getTemplateOLA(Map<String, String> params) {
        return olaDAO.findTemplateBy(params);
    }

    public ModelAndView olaPage(String operationId, String serviceId,
                                HttpServletRequest req) {
        ModelAndView mv = new ModelAndView("service/ola/olaPage");
        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getSession().getServletContext());
        // 根据serviceId获取service信息
        if (StringUtils.isNotEmpty(serviceId)) {
            ServiceServiceImpl ss = context.getBean(ServiceServiceImpl.class);
            com.dc.esb.servicegov.entity.Service service = ss
                    .getUniqueByServiceId(serviceId);
            if (service != null) {
                mv.addObject("service", service);
            }
            if (StringUtils.isNotEmpty(operationId)) {
                // 根据serviceId,operationId获取operation信息
                OperationServiceImpl os = context
                        .getBean(OperationServiceImpl.class);
                Operation operation = os.getOperation(
                        operationId, serviceId);
                if (operation != null) {
                    mv.addObject("operation", operation);
                }
            }
        }

        return mv;
    }
}

