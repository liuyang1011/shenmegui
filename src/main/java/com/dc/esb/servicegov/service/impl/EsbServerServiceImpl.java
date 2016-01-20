package com.dc.esb.servicegov.service.impl;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.dc.esb.servicegov.dao.impl.EsbServerDAOImpl;
import com.dc.esb.servicegov.dao.support.HibernateDAO;
import com.dc.esb.servicegov.entity.EsbServer;
import com.dc.esb.servicegov.entity.ServiceInvoke;
import com.dc.esb.servicegov.export.impl.MetadataConfigGenerator;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.service.support.AbstractBaseService;
import com.dc.esb.servicegov.vo.ConfigListVO;
import com.dc.esb.servicegov.vo.ConfigVO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/1/20.
 */
@Service
@Transactional
public class EsbServerServiceImpl extends AbstractBaseService<EsbServer, String> {
    private static final Log log = LogFactory.getLog(EsbServerServiceImpl.class);
    @Autowired
    private EsbServerDAOImpl esbServerDAO;
    @Autowired
    private MetadataConfigGenerator metadataConfigGenerator;

    public void tranConfig(String path,String optionFlag, String dicSync, String serverStr){
        List<EsbServer> esbServers;
        if("false".equals(optionFlag)){
            esbServers = esbServerDAO.getAll();
        }else {
            if("all".equals(serverStr)){
                esbServers = esbServerDAO.getAll();
            }else{
                String hql = " from EsbServer where serverId in (?)";
                esbServers = esbServerDAO.find(hql, serverStr);
            }
        }
        String inPath = path + File.separator + "in_config";
        String outPath =  path + File.separator + "out_config";
        try {
            //TODO 选择更新数据字典
            File metadataFile = metadataConfigGenerator.generate();
            File inMetadataFile = new File(inPath + File.separator + metadataFile.getName());
            File outMetadataFile = new File(outPath + File.separator + metadataFile.getName());
            FileUtils.copyFile(metadataFile, inMetadataFile);
            FileUtils.copyFile(metadataFile, outMetadataFile);
        }catch (IOException e){
            log.error("复制数据字典据失败！", e);
        }
        File[] inFiles = new File(inPath).listFiles();
        File[] outFiles = new File(outPath).listFiles();

        for(EsbServer esbServer : esbServers){
            try {
                tranConfigToServer(inFiles, outFiles, esbServer);
            }catch (Exception e){
                log.error("文件传入[" + esbServer.getServerIp()+"]失败!", e);
            }
        }

    }
    public void tranConfigToServer(File[] inFiles, File[] outFiles,  EsbServer esbServer) throws Exception{
        ;doSCP(esbServer.getServerIp(), esbServer.getUserName(), esbServer.getUserPsw(), inFiles, esbServer.getInConfigPath() );
        ;doSCP(esbServer.getServerIp(), esbServer.getUserName(), esbServer.getUserPsw(), outFiles, esbServer.getOutConfigPath() );
    }
    private void doSCP(String hostname, String user, String psw,File[] files, String remotePath) throws Exception {
        //1, 创建一个连接connection对象
        Connection conn = new Connection(hostname);
        //2, 进行连接操作
        conn.connect();
        //3, 进行连接访问授权验证
        boolean isAuth = conn.authenticateWithPassword(user, psw);
        if(!isAuth)
            throw new Exception("Authentication failed");
        //4, 创建一个SCPClient对象
        SCPClient client = new SCPClient(conn);
        try {
            for(int i = files.length - 1; i >= 0 ; i --) {
                File cur = files[i];
                if((cur != null) && cur.exists()) {
//                    String scplocal = localPath + File.separatorChar + cur.getName();
                    //5, 进行文件scp远程拷贝
                    long length = cur.length();
                    client.put(cur.getAbsolutePath(), remotePath);
                }
            }
        }
        finally {
            //6, 使用完关闭连接
            conn.close();
        }
    }

    @Override
    public HibernateDAO<EsbServer, String> getDAO() {
        return esbServerDAO;
    }


}
