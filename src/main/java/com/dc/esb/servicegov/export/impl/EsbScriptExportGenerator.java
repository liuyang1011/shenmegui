package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.excel.support.Constants;
import com.dc.esb.servicegov.export.util.FileUtil;
import com.dc.esb.servicegov.service.impl.ServiceInvokeServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by xhx109 on 2016/1/30.
 */
@Component
public class EsbScriptExportGenerator {
    private Log log = LogFactory.getLog(EsbScriptExportGenerator.class);
    private static String SERVICE_INADDRESSID = "'4cc95abd8b9cba4705f290b39dee3d6a'";
    private static String SERVICE_OUTADDRESSID = "'70022232c41f240289cac75d5b3e3884'";
    private static String SERVICE_TYPE = "'SERVICE'";
    private static String SERVICE_SESSIONCOUNT = "1";
    private static String SERVICE_DELIVERYMODE = "'2'";
    private static String SERVICE_NODEID = "null";
    private static String SERVICE_LOCATION = "'local_out'";
    private static String SERVICE_ROUTERABLE = "'1'";

    private static String BUSSSERVICES_SERVICEID = "";

    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;

    public void checkedExport(String  delScript, String protocolTypeScript, String protocolScript, String adapterFrameScript, String serviceSystemScript,
                                 String chinalScript, String serviceScript, String baseServiceScript, String operationIds, String path){
        if(StringUtils.isNotEmpty(operationIds)){
            //删除脚本
            if("checked".equalsIgnoreCase(delScript)){
                fillDelScript(operationIds, path);
            }
            if("checked".equalsIgnoreCase(protocolTypeScript)){

            }
            if("checked".equalsIgnoreCase(protocolScript)){

            }
            if("checked".equalsIgnoreCase(adapterFrameScript)){

            }
            if("checked".equalsIgnoreCase(serviceSystemScript)){

            }
            if("checked".equalsIgnoreCase(chinalScript)){

            }
            //服务脚本
            if("checked".equalsIgnoreCase(serviceScript)){
                fillServiceScript(operationIds, path);
            }
            if("checked".equalsIgnoreCase(baseServiceScript)){

            }
        }
    }
    //生成删除脚本
    public void fillDelScript(String operationIds, String path){
        String fileName = path + File.separator + "delete.sql";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try{
            file.createNewFile();
            String content = delContent(operationIds);
            FileUtil.writeFile(content, fileName);
        }catch (IOException e){
            log.error(e);
        }
    }
    //生成删除脚本内容
    public String delContent(String operationIds){
        StringBuffer operationsBuffer = new StringBuffer("");
        String[] operationIdArray = operationIds.split("\\,");
        for(int i = 0; i < operationIdArray.length; i++){
            if(i != operationIdArray.length -1){
                operationsBuffer.append("'" + operationIdArray[i] + "',\n");
            }else{
                operationsBuffer.append("'" + operationIdArray[i] + "'\n");
            }
        }
        StringBuffer content = new StringBuffer("");
        String ids = operationsBuffer.toString();
        content.append("delete from DEPLOYMENTS where NAME in(\n" + ids + ");\n");
        content.append("delete from SERVICESYSTEMMAP where SERVICEID in(\n" + ids + ");\n");
        content.append("delete from DATAADAPTER where DATAADAPTERID in(\n" + ids + ");\n");
        content.append("delete from BINDMAP where SERVICEID in(\n" + ids + ");\n");
        content.append("delete from SERVICEINFO where SERVICEID in(\n" + ids + ");\n");
        content.append("delete from BUSSSERVICES where SERVICEID in(\n" + ids + ");\n");
        content.append("delete from SERVICES where NAME in(\n" + ids + ");\n");

        return content.toString();
    }
    //生成服务脚本
    public void fillServiceScript(String operationIds, String path){
        String fileName = path + File.separator + "service.sql";
        File file = new File(fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try{
            file.createNewFile();
            String content = serviceContent(operationIds);
            FileUtil.writeFile(content, fileName);
        }catch (IOException e){
            log.error(e);
        }
    }
    //服务脚本内容
    public String serviceContent(String operationIds){
        String[] operationIdArray = operationIds.split("\\,");
        StringBuffer content = new StringBuffer("");
        for(int i = 0; i < operationIdArray.length; i++){
            String operationId = operationIdArray[i];
            content.append("----------" + operationId + "\n");
            content.append("insert into SERVICES(NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION, ROUTERABLE)" +
                    " VALUES('"+ operationId + "', " + SERVICE_INADDRESSID + ", " + SERVICE_OUTADDRESSID + ", " + SERVICE_TYPE + ", " + SERVICE_SESSIONCOUNT +
                    ", " + SERVICE_DELIVERYMODE + ", " + SERVICE_NODEID + " , " + SERVICE_LOCATION + ", " + SERVICE_ROUTERABLE + ");\n");
            content.append("insert into BUSSSERVICES(SERVICEID, CATEGORY, METHODNAME, ISARG, DESCRIPTION) VALUES('" + operationId + "', null, null, 'false', null);\n");
            content.append("insert into SERVICEINFO(SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, DESCRIPTION, MODIFYTIME, ADAPTERTYPE, ISCREATE) " +
                    "VALUES('" + operationId + "', 'BUSS', '" + operationId + "', 'false', null, 'local_out', null, '" + DateUtils.format(new Date()) + "', null, 'true');\n");
            content.append("insert into BINDMAP(SERVICEID, STYPE, LOCATION, VERSION, PROTOCOLID, MAPTYPE) " +
                    "VALUES('" + operationId + "', 'SERVICE', 'local_out', '0', '" + getProtocolID(operationId) + "', 'request');\n");
            content.append("insert into DATAADAPTER(DATAADAPTERID, DATAADAPTER, LOCATION, ADAPTERTYPE) " +
                    "VALUES('" + operationId + "', 'default_service', 'local_out', 'OUT');\n");
            content.append("insert into SERVICESYSTEMMAP(NAME, SERVICEID, ADAPTER) " +
                    "VALUES('" + getSystem(operationId) + "', '" + operationId + "', '" + getProtocolID(operationId) + "');\n");
            content.append("insert into DEPLOYMENTS(ID, LOCATION, FILEPATH, DEPLOYDATE, DESCRIPTION, NAME, FILECONTENT, USERNAME, VERSION) " +
                    "VALUES('" + new Date().getTime() + "', 'local_out', null, '" + DateUtils.format(new Date()) + "', null, '" + operationId + "', '6c6f63616c5f6f7574', null, '0');\n");
        }
        return content.toString();
    }
    public String getProtocolID(String operationId){
        String systemAb = getSystem(operationId);
        if(StringUtils.isNotEmpty(systemAb)){
            //适配器命名规则，系统简称+Adapter
            return systemAb + "Adapter";
        }
        return null;
    }
    //获取服务系统简称
    public String getSystem(String operationId){
        if(StringUtils.isNotEmpty(operationId)){
            String serviceId = operationId.substring(0, operationId.length() -2);
            String opId = operationId.substring(operationId.length()-2, operationId.length());
            String hql = "select distinct s.system from ServiceInvoke s where s.serviceId = ? and s.operationId = ? and s.type = ?";
            List  systems = serviceInvokeService.find(hql, serviceId, opId, com.dc.esb.servicegov.service.support.Constants.INVOKE_TYPE_PROVIDER);
            if(0 < systems.size()){
//                log.warn("一个场景中存在多个不同的服务方！");
                com.dc.esb.servicegov.entity.System system = (com.dc.esb.servicegov.entity.System)systems.get(0);
                return system.getSystemAb();
            }
        }
        return null;
    }
}
