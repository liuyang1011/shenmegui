package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.export.impl.ConfigBathGenerator;
import com.dc.esb.servicegov.vo.ConfigVO;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xhx113 on 2016/3/24.
 */
public class ExportConfigTask implements Runnable{
    private ConfigBathGenerator configBathGenerator;
    private HttpServletRequest request;
    private ConfigVO configVO;
    private String path;
    private CountDownLatch countDown;

    public void init(ConfigBathGenerator configBathGenerator,HttpServletRequest request,ConfigVO configVO,String path,CountDownLatch countDown){
        this.configBathGenerator = configBathGenerator;
        this.request = request;
        this.configVO = configVO;
        this.path = path;
        this.countDown = countDown;
    }
    public void run() {
        configBathGenerator.generate(request, configVO, path);
        countDown.countDown();
    }
}
