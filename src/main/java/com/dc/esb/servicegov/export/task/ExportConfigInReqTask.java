package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.impl.ConfigExportGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xhx113 on 2016/3/26.
 */
public class ExportConfigInReqTask implements Runnable{
    private static final Log logger = LogFactory.getLog(ExportConfigInReqTask.class);
    private ConfigExportGenerator configExportGenerator;
    private ServiceInvoke serviceInvokesIn;
    private String path;
    private CountDownLatch countDownIn;

    public void init(ConfigExportGenerator configExportGenerator,ServiceInvoke serviceInvokesIn,String path,CountDownLatch countDownIn){
        this.configExportGenerator = configExportGenerator;
        this.serviceInvokesIn = serviceInvokesIn;
        this.path = path;
        this.countDownIn = countDownIn;
    }

    public void run(){
        configExportGenerator.generateInRequest(serviceInvokesIn,path);
        countDownIn.countDown();
    }
}
