package com.dc.esb.servicegov.export.impl;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
@Component
public class HsBankISO8583ConfigExportGenerator extends ConfigExportGenerator{
    private Log log = LogFactory.getLog(HsBankISO8583ConfigExportGenerator.class);
    //缓存文件
    private  String CUPSPath = "template/config_export/hs/iso8583/CUPS8583.xml";//默认
    private  String POSPath = "template/config_export/hs/iso8583/POS8583.xml";
    //模板文件
    private  String reqPath = "template/config_export/hs/iso8583/channel_service_template.xml";
    private  String resPath = "template/config_export/hs/iso8583/service_system_template.xml";
    /**
     * 生成in_config文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInRequest(ServiceInvoke serviceInvoke, String path){
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(reqPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //判断是否超过64域，如果超出，添加<IB2 metadataid="IB2" type="bitmap" length="8"/>

            //填充模板外元素
            fillElement(rootElement, serviceInvoke, Constants.ElementAttributes.REQUEST_NAME);
            //生成文件
            String fileName = getReqFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成request文件失败！", e);
        }
    }
    /**
     * 生成esb响应文件
     * @param serviceInvoke
     * @param path
     */
    @Override
    public void  generateInResponse(ServiceInvoke serviceInvoke, String path){
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            //模板路径
            String templatePath = loader.getResource(resPath).getPath();
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(templatePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            //填充模板外元素
            fillElement(rootElement, serviceInvoke, Constants.ElementAttributes.RESPONSE_NAME);
            //生成文件
            String fileName = getResFilePath(serviceInvoke, path);
            createFile(doc, fileName);
        }catch (Exception e){
            log.error("生成response文件失败！", e);
        }
    }
    @Override
    public void  generateOutRequest(ServiceInvoke serviceInvoke, String path){
        generateInRequest(serviceInvoke, path);
    }

    @Override
    public void  generateOutResponse(ServiceInvoke serviceInvoke, String path){
        generateInResponse(serviceInvoke, path);
    }
    public void fillElement(Element rootElement, ServiceInvoke serviceInvoke, String structName){
        String serviceId = serviceInvoke.getServiceId();
        String operationId = serviceInvoke.getOperationId();
        com.dc.esb.servicegov.entity.System system = serviceInvoke.getSystem();

        //获取ida列表（一个场景下，某个系统所有接口的request或response，ida的并集)
        List<Ida> idaList = new ArrayList<Ida>();
        //查询req,res节点
        Ida reIda = idaService.getByInterfaceIdStructName(serviceInvoke.getInterfaceId(), structName);
        //查询req,res下的子节点
        List<Ida> reChildren = idaService.getNotEmptyByParentId(reIda.getId());
        idaList.addAll(reChildren);
        //填充
        for(Ida ida : idaList){
            fillDocElement(rootElement, system.getSystemAb(), ida);
        }
    }
    public Element fillDocElement(Element parentElement, String systemAb, Ida ida){
        //从缓存中查询元素
        Element element = getCacheElement(getCatchPath(systemAb), ida.getStructName());
        if(null != element){
            addAttribute(element, "metadataid", ida.getMetadataId());
            parentElement.add(element);
            //查询子元素
            List<Ida> children = idaService.getNotEmptyByParentId(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    fillDocElement(element, systemAb, child);
                }
            }
        }else{
            element = parentElement.addElement("ErrorNode") ;
            addAttribute(element, "msg", "不是ISO8583节点");
        }
        return element;
    }
    public String getCatchPath(String systemId){
        ClassLoader classLoader =  this.getClass().getClassLoader();
        String path = classLoader.getResource(CUPSPath).getPath();
//        if("CORE".equalsIgnoreCase(systemId)){
//        }
        if("POS".equalsIgnoreCase(systemId)){
            return classLoader.getResource(POSPath).getPath();
        }
        return path;
    }
    //从缓存模板中读取对应节点
    public Element getCacheElement(String cacheFilePath, String elementName){
        try {
            SAXReader sr = new SAXReader();//获取读取xml的对象。
            Document doc = sr.read(cacheFilePath);//得到xml所在位置。然后开始读取。并将数据放入doc中
            Element rootElement = doc.getRootElement();
            Element childElement =rootElement.element(elementName);
            if(null == childElement){
                return null;
            }
            return childElement.createCopy();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
