package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.export.impl.MapFileDataFromDB;
import com.dc.esb.servicegov.export.util.ZipUtil;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.wsdl.impl.SpdbWSDLGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExportWSDLTask implements Runnable{
    private String serviceId;
    private List<OperationPK> operationPKs;
    private String path;
    private CountDownLatch countDown;
    private SpdbWSDLGenerator spdbWSDLGenerator;

    public void init(String serviceId, List<OperationPK> operationPKs, String path, SpdbWSDLGenerator spdbWSDLGenerator, CountDownLatch countDown) {
        this.serviceId = serviceId;
        this.operationPKs = operationPKs;
        this.path = path;
        this.spdbWSDLGenerator = spdbWSDLGenerator;
        this.countDown = countDown;
    }

    public void run() {
        try {
            spdbWSDLGenerator.generateByService(serviceId, operationPKs, path);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countDown.countDown();
        }
    }
}
