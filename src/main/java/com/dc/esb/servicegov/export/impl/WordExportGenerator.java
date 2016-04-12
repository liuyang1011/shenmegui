package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.export.bean.WordTemplate;
import com.dc.esb.servicegov.service.impl.*;
import com.dc.esb.servicegov.service.support.Constants;
import com.dc.esb.servicegov.util.PdfUtils;
import com.dc.esb.servicegov.vo.OperationPKVO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2016/2/3.
 */
@Component
public class WordExportGenerator {
    private static Log logger = LogFactory.getLog(WordExportGenerator.class);
    @Autowired
    private OperationServiceImpl operationService;
    @Autowired
    private ServiceInvokeServiceImpl serviceInvokeService;
    @Autowired
    private SDAServiceImpl sdaService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private ServiceCategoryServiceImpl serviceCategoryService;

    private static String WordTemplatePath = "template/word/word_template.xml";

    private static final String serviceType = "service";
    private static final String serviceCategoryType0 = "root";
    private static final String serviceCategoryType1 = "serviceCategory";


    public File generatorByNode(String id, String type){
        try {
            //读取模板
            ClassLoader loader = this.getClass().getClassLoader();
            String templatePath = loader.getResource(WordTemplatePath).getPath();
            File templateFile = new File(templatePath);
            String destPath = loader.getResource("").getPath() + File.separator + "银行服务_" + new Date().getTime() + ".doc";
            File destFile = new File(destPath);
            //复制模板
            FileUtils.copyFile(templateFile, destFile);
            //填充内容
            if (serviceCategoryType0.equals(type)) {
                generateByServiceCategoryRoot(id, destFile);
            }
            if (serviceCategoryType1.equals(type)) {
                generateByServiceCategory(id, destFile, "1");
            }
            if (serviceType.equals(type)) {
                gennerateByService(id, destFile, "1");
            }
            //添加尾部信息
            appendFile(destFile, WordTemplate.foot);
            return destFile;
        }catch (Exception e){
            logger.error("生成word文件出错！", e);
        }
        return null;
    }
    /**
     * 根节点操作，导出所有
     * @param serviceCategoryId
     * @return
     */
    public void generateByServiceCategoryRoot(String serviceCategoryId, File file) throws Exception {
        String hql = " from " + ServiceCategory.class.getName() +" where parentId is null order by categoryId asc";
        List<ServiceCategory> children = serviceCategoryService.find(hql);
        if(children.size() > 0){
            for(int i = 0; i< children.size(); i++){
                ServiceCategory child = children.get(i);
                generateByServiceCategory(child.getCategoryId(), file, String.valueOf(i + 1));
            }
        }
    }

    /**
     * 分类节点操作
     * @param serviceCategoryId
     * @param file
     * @throws Exception
     */
    public void generateByServiceCategory(String serviceCategoryId, File file, String tab) throws Exception{
        ServiceCategory sc = serviceCategoryService.findUniqueBy("categoryId", serviceCategoryId);
        //填充分类头
        appendFile(file, getCategoryStr(sc, tab));
        String hql = " from " + ServiceCategory.class.getName() +" where parentId = ? order by categoryId asc";
        List<ServiceCategory> children = serviceCategoryService.find(hql, serviceCategoryId);
        if(children.size() > 0){
            for(int i = 0; i< children.size(); i++){
                ServiceCategory child = children.get(i);
                generateByServiceCategory(child.getCategoryId(), file, tab + "." + (i + 1));
            }
        }
        else{
            String hql2 = " from " + Service.class.getName() +" where categoryId = ? order by serviceId asc";
            List<Service> services = serviceService.find(hql2, serviceCategoryId);
            if(services.size() > 0){
                for(int i = 0; i < services.size(); i++){
                    Service service = services.get(i);
                    gennerateByService(service.getServiceId(), file, tab + "." + (i + 1));
                }
            }
        }
    }

    public void gennerateByService(String serviceId, File file, String tab) throws Exception{
        Service service = serviceService.findUniqueBy("serviceId", serviceId);
        appendFile(file, getServiceStr(service, tab));
        String hql = " from " + Operation.class.getName() +" where serviceId = ? order by operationId asc";
        List<Operation> operations = operationService.find(hql, serviceId);
        //填充模板
        generateOperation(file, operations);
    }

    //根据选中服务场景生成word文档
    public File generatorByOperations(OperationPKVO pkvo){
        //将场景按服务整理
        Map<Service, List<Operation>> map = new HashMap<Service, List<Operation>>();
        for(OperationPK pk : pkvo.getPks()){
            Operation operation = operationService.getOperation(pk.getServiceId(), pk.getOperationId());
            List<Operation> operations = map.get(operation.getService());
            if(operations != null){
                operations.add(operation);
            }
            else{
                operations = new ArrayList<Operation>();
                operations.add(operation);
                map.put(operation.getService(), operations);
            }
        }
        try {
            //读取模板
            ClassLoader loader = this.getClass().getClassLoader();
            String templatePath = loader.getResource(WordTemplatePath).getPath();
            File templateFile = new File(templatePath);
            String destPath = loader.getResource("").getPath() + File.separator + "银行服务_" + new Date().getTime() + ".doc";
            File destFile = new File(destPath);
            //复制模板
            FileUtils.copyFile(templateFile, destFile);
            //服务编号
            int i = 0;
            //填充模板
            for (Map.Entry<Service, List<Operation>> entry : map.entrySet()) {
                Service service = entry.getKey();
                //填充服务信息
                appendFile(destFile, getServiceStr(service, String.valueOf(++i)));
                List<Operation> operations = entry.getValue();
                generateOperation(destFile, operations);
            }
            //添加尾部信息
            appendFile(destFile, WordTemplate.foot);
            return  destFile;
        } catch (Exception e) {
            logger.error("生成word文件出错！", e);
        }
        return null;
    }
    public void generateOperation(File destFile, List<Operation> operations) throws Exception{
        for (Operation operation : operations) {
            //填充场景信息
            appendFile(destFile, getOperationStr(operation));
            //添加请求sda
            Counter counter = new Counter();
            SDA reqSda = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), Constants.ElementAttributes.REQUEST_NAME);
            List<SDA> reqSdas = sdaService.findBy("parentId", reqSda.getId());
            for(SDA sda : reqSdas){
                StringBuffer buffer = new StringBuffer("");
                appendReqSda(buffer, sda, counter);
                appendFile(destFile, buffer.toString());
            }
            //添加输出行
            appendFile(destFile, WordTemplate.sdaSeperator);
            //添加响应sda
            counter.i = 1;
            SDA rspSda = sdaService.getByStructName(operation.getServiceId(), operation.getOperationId(), Constants.ElementAttributes.RESPONSE_NAME);
            List<SDA> rspSdas = sdaService.findBy("parentId", rspSda.getId());
            for(SDA sda : rspSdas){
                StringBuffer buffer = new StringBuffer("");
                appendRspSda(buffer, sda, counter);
                appendFile(destFile, buffer.toString());
            }
            //添加场景结束
            appendFile(destFile, WordTemplate.operationFoot);
        }
    }

    //写入文件尾部
    public void appendFile(File file, String appendContent) throws IOException{
        FileWriter writer = new FileWriter(file, true);
        writer.write(appendContent);
        writer.close();
    }
    public String strReplace(String str, String oldStr, String newStr ){
        if(StringUtils.isNotEmpty(oldStr)){
            if(StringUtils.isNotEmpty(newStr)){
                newStr = newStr.replaceAll("<","&lt;");
                newStr = newStr.replaceAll(">","&gt;");
                newStr = newStr.replaceAll("\"","&quot;");
                newStr = newStr.replaceAll("&","&amp;");
                newStr = newStr.replaceAll("'","&apos;");
                return str.replace(oldStr, newStr);
            }else {
                return str.replace(oldStr, "");
            }
        }
        return "";
    }
    //分类务信息
    public String getCategoryStr(ServiceCategory serviceCategory, String tab){
        //头
        String categoryTitle = tab + " " + serviceCategory.getCategoryName() + "(" + serviceCategory.getCategoryId() + ")";
        String str = WordTemplate.categoryInfo;
        str = strReplace(str, WordTemplate.categoryTitle, categoryTitle);
        return str;
    }
    //生成服务信息
    public String getServiceStr(Service service, String tab){
        //服务头
        String serviceTitle = tab + " " + service.getServiceName() + "(" + service.getServiceId() + ")";
        //功能描述
        String serviceDesc = service.getDesc();
        String str = WordTemplate.serviceInfo;
        str = strReplace(str, WordTemplate.serviceTitle, serviceTitle);
        str = strReplace(str, WordTemplate.serviceDesc, serviceDesc);
        return str;
    }
    //生成场景信息
    public String getOperationStr(Operation operation){
        String operationTitle = operation.getOperationId() + ":" + operation.getOperationName();
        String operationDesc = operation.getOperationDesc();
        String providers = getSystemStr(operation, Constants.INVOKE_TYPE_PROVIDER);
        String consumers = getSystemStr(operation, Constants.INVOKE_TYPE_CONSUMER);
        String str = WordTemplate.operationInfo;
        str = strReplace(str, WordTemplate.operationTitle, operationTitle);
        str = strReplace(str, WordTemplate.operationDesc, operationDesc);
        str = strReplace(str, WordTemplate.providers, providers);
        str = strReplace(str, WordTemplate.consumers, consumers);
        return str;
    }
    //获取请求sda 信息
    public String appendReqSda(StringBuffer buffer, SDA sda, Counter counter){
        String str = WordTemplate.sdaReq;
        if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
            str = WordTemplate.sdaArray;
        }
        str = strReplace(str, WordTemplate.reqSeq, String.valueOf(counter.i));
        str = strReplace(str, WordTemplate.reqStructName, sda.getStructName());
        str = strReplace(str, WordTemplate.reqType, sda.getType());
        str = strReplace(str, WordTemplate.reqStructAlias, sda.getStructAlias());
        str = strReplace(str, WordTemplate.reqRequired, sda.getRequired());
        str = strReplace(str, WordTemplate.reqConstraint, sda.getConstraint());
        str = strReplace(str, WordTemplate.reqRemark, sda.getRemark());
        counter.i++;
        buffer.append(str);
        List<SDA> children = sdaService.findBy("parentId", sda.getId());
        for(SDA child : children){
            appendReqSda(buffer, child, counter);
        }
        //添加end节点
        if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
            str = WordTemplate.sdaArray;
            str = strReplace(str, WordTemplate.reqSeq, String.valueOf(counter.i));
            str = strReplace(str, WordTemplate.reqStructName, sda.getStructName());
            str = strReplace(str, WordTemplate.reqType, sda.getType());
            str = strReplace(str, WordTemplate.reqStructAlias, sda.getStructAlias());
            str = strReplace(str, WordTemplate.reqRequired, sda.getRequired());
            str = strReplace(str, WordTemplate.reqConstraint, sda.getConstraint());
            str = strReplace(str, WordTemplate.reqRemark, "end");
            counter.i++;
            buffer.append(str);
        }
        return buffer.toString();
    }
    //获取响应sda 信息
    public String appendRspSda(StringBuffer buffer, SDA sda, Counter counter){
        String str = WordTemplate.sdaRsp;
        if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
            str = WordTemplate.sdaArray;
            str = strReplace(str, WordTemplate.reqSeq, String.valueOf(counter.i));
            str = strReplace(str, WordTemplate.reqStructName, sda.getStructName());
            str = strReplace(str, WordTemplate.reqType, sda.getType());
            str = strReplace(str, WordTemplate.reqStructAlias, sda.getStructAlias());
            str = strReplace(str, WordTemplate.reqRequired, sda.getRequired());
            str = strReplace(str, WordTemplate.reqConstraint, sda.getConstraint());
            str = strReplace(str, WordTemplate.reqRemark, sda.getRemark());
        }else{
            str = strReplace(str, WordTemplate.rspSeq, String.valueOf(counter.i));
            str = strReplace(str, WordTemplate.rspStructName, sda.getStructName());
            str = strReplace(str, WordTemplate.rspType, sda.getType());
            str = strReplace(str, WordTemplate.rspStructAlias, sda.getStructAlias());
            str = strReplace(str, WordTemplate.rspRequired, sda.getRequired());
            str = strReplace(str, WordTemplate.rspConstraint, sda.getConstraint());
            str = strReplace(str, WordTemplate.rspRemark, sda.getRemark());
        }
        counter.i++;
        buffer.append(str);
        List<SDA> children = sdaService.findBy("parentId", sda.getId());
        for(SDA child : children){
            appendRspSda(buffer, child, counter);
        }
        //添加end节点
        if("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType())){
            str = WordTemplate.sdaArray;
            str = strReplace(str, WordTemplate.reqSeq, String.valueOf(counter.i));
            str = strReplace(str, WordTemplate.reqStructName, sda.getStructName());
            str = strReplace(str, WordTemplate.reqType, sda.getType());
            str = strReplace(str, WordTemplate.reqStructAlias, sda.getStructAlias());
            str = strReplace(str, WordTemplate.reqRequired, sda.getRequired());
            str = strReplace(str, WordTemplate.reqConstraint, sda.getConstraint());
            str = strReplace(str, WordTemplate.reqRemark, "end");
            counter.i++;
            buffer.append(str);
        }
        return buffer.toString();
    }
    //根据类型获取场景消费者、提供者字符串
    public String  getSystemStr(Operation operation, String invokeType){
        List<ServiceInvoke> invokes = serviceInvokeService.getByOperationAndType(operation, invokeType);
        StringBuffer buffer = new StringBuffer("");
        for(int i = 0 ; i < invokes.size(); i++){
            ServiceInvoke serviceInvoke = invokes.get(i);
            com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();
            if(null != system){
                if(i != invokes.size()-1){
                    buffer.append(system.getSystemChineseName() + ",");
                }else{
                    buffer.append(system.getSystemChineseName());
                }
            }
        }
        return buffer.toString();
    }
    class Counter{
        int i = 1;
    }
}
