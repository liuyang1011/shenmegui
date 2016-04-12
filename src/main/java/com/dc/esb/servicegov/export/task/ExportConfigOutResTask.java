package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.impl.ConfigExportGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xhx113 on 2016/3/26.
 */
public class ExportConfigOutResTask implements Runnable{
    private static final Log logger = LogFactory.getLog(ExportConfigOutResTask.class);
    private ConfigExportGenerator configExportGenerator;
    private ServiceInvoke serviceInvokesIn;
    private String path;
    private CountDownLatch countDown;
    public void init(ConfigExportGenerator configExportGenerator,ServiceInvoke serviceInvokesIn,String path,CountDownLatch countDown){
        this.configExportGenerator = configExportGenerator;
        this.serviceInvokesIn = serviceInvokesIn;
        this.path = path;
        this.countDown = countDown;
    }
    public void run(){
        configExportGenerator.generateOutResponse(serviceInvokesIn, path);
        countDown.countDown();
    }
}
