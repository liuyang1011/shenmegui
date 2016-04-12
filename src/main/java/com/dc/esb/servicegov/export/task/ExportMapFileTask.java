package com.dc.esb.servicegov.export.task;

import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.export.impl.MapFileDataFromDB;
import com.dc.esb.servicegov.export.impl.MapFileGenerator;
import com.dc.esb.servicegov.service.support.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ExportMapFileTask implements IExportMapFileTask {
    Log log = LogFactory.getLog(ExportMapFileTask.class);

    public static final String OJECT_PRIFIX = "O";
    public static final String FIELD_PRIFIX_STRING = "F";
    public static final String FIELD_IDENTIFY = "FLD";
    public static final String GRD_IDENTIFY = "GRD";
    public static final String MAPFILE_FORM_LABEL = "WINDOW";
    public static final String MAPFILE_FIELD_LABEL= "PRINTER";

    private OperationPK pk;
    private String path;
    private int fieldCount = 1;
    private CountDownLatch countDown;
    private MapFileDataFromDB db;

    @Override
    public void init(OperationPK pk, String path, MapFileDataFromDB db, CountDownLatch countDown) {
        this.pk = pk;
        this.path = path;
        this.db = db;
        this.countDown = countDown;
        this.fieldCount = 1;
    }

    @Override
    public void run() {
        try {
            Date start = new Date();
            this.generate();
            log.info("=====导出mapfile[" + pk.getServiceId() + pk.getOperationId() + "]耗时：" + (new Date().getTime() - start.getTime()) + "ms"  );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            countDown.countDown();
        }
    }

    /**
     * generate sop mkfiles using interface node from db. The returned files
     * including: 1.one request file 2.several resp files 3.several field files
     * as metadata structures in service gov platform
     * <p/>
     * 通过operationpk来生成接口的MapFiles， 生成的文件包括，
     * 一个或多个请求Object文件，一个或多个返回Object文件，若干Grid文件。
     *
     * @throws IOException
     */
    @Override
    public void generate() {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<ServiceInvoke> sis = db.getInvokeByOperationPK(pk);
        for (ServiceInvoke si : sis) {
            Interface inter = si.getInter();
            if (null != inter && StringUtils.isNotEmpty(inter.getEcode())) {
                String subPath = path + File.separator + inter.getEcode();
                String fileName = subPath + File.separator + inter.getEcode();
                try {
                    File reqFile = new File(fileName + "1");
                    if(!reqFile.getParentFile().exists()){
                        reqFile.getParentFile().mkdirs();
                    }
                    if (!reqFile.exists()) {
                        reqFile.createNewFile();
                    }
                    writeContentToFile(generateSOPObjectMapFileContent(si, Constants.ElementAttributes.REQUEST_NAME, subPath), reqFile);

                    File respFile = new File(fileName + "2");
                    if (!respFile.exists()) {
                        respFile.createNewFile();
                    }
                    writeContentToFile(generateSOPObjectMapFileContent(si,Constants.ElementAttributes.RESPONSE_NAME, subPath), respFile);
                } catch (Exception e) {
                    log.error("根据接口[]" + si.getInterfaceId() + "导出mapfile失败", e);
                }
            }
            fieldCount = 1;
        }
    }

    protected void writeContentToFile(String content, File targetFile) throws IOException {
        if (null != targetFile) {
            FileWriter fWriter = new FileWriter(targetFile);
            fWriter.append(content);
            fWriter.flush();
            fWriter.close();
        }
    }

    /**
     * generate sop object map file 生成Request或者Response请求对象文件的内容
     *
     * @return
     * @throws IOException
     */
    public String generateSOPObjectMapFileContent(ServiceInvoke serviceInvoke,String structName, String path) throws IOException {
        StringBuilder content = new StringBuilder();
        content.append(MAPFILE_FORM_LABEL);
        content.append(System.getProperty("line.separator"));
        String interfaceId = serviceInvoke.getInterfaceId();
        //TODO 服务报文头去除
        content.append(sysHeadToSopMapfileObjec(serviceInvoke, structName, path));//syshead字段
        content.append(interHeadToSopMapfileObjec(serviceInvoke, structName, path));//报文头字段
        //body字段
        Ida ida = db.getIda(interfaceId, structName);
        List<Ida> children = db.getIdas(ida.getId());
        if(null != children && 0 < children.size()){
            for(Ida child : children){
                if (null != child) {
                    content.append(nodeToSopMapfileObject(child, serviceInvoke.getInter().getEcode(), path));
                }
            }
        }
        return content.toString();
    }

    /**
     * 获取场景的服务报文头，生成string
     * @param serviceInvoke
     * @param structName
     * @param path
     * @return
     */
    public String sysHeadToSopMapfileObjec(ServiceInvoke serviceInvoke, String structName, String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        String[] headIds = db.getServiceHeadIds(serviceInvoke.getServiceId(), serviceInvoke.getOperationId());
        for(String headId : headIds){//遍历服务头
            SDA sda = db.getSDA(headId, structName);
            List<SDA> children = db.getSDAs(sda.getId());
            for (SDA child : children) {
                sb.append(nodeToSopMapfileObject(child, serviceInvoke.getInter().getEcode(), path));
            }
        }
        return sb.toString();
    }

    /**
     * 获取接口报文头，生成string
     * @param serviceInvoke
     * @param structName
     * @param path
     * @return
     */
    public String interHeadToSopMapfileObjec(ServiceInvoke serviceInvoke, String structName, String path) throws IOException{
        StringBuilder sb = new StringBuilder();
        List<String> headIds = db.getInterfaceHeadIds(serviceInvoke.getInterfaceId());
        for(String headId : headIds){//遍历所有报文头
            Ida ida = db.getHeadIda(headId, structName);
            List<Ida> children = db.getIdas(ida.getId());
            if(null != children && 0 < children.size()){
                for(Ida child : children){
                    sb.append(nodeToSopMapfileObject(child, serviceInvoke.getInter().getEcode(), path));
                }
            }
        }
        return sb.toString();
    }
    /**
     * 将一个Metadata Node写为一个Field或者Grid的过程
     *
     * @return
     * @throws IOException
     */
    private String nodeToSopMapfileObject(Ida ida,String ecode, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (null != ida) {
            List<Ida> chidren = db.getIdas(ida.getId());
            if (null != chidren && 0 < chidren.size()) {
                String gridName = generateSopGridMapFile(ida, ecode, path);
                sb.append(nodeToMapfileGrid(gridName));
            } else {
                sb.append(nodeToMapfileField(ida));
            }
        }
        return sb.toString();
    }

    /**
     * 生成Grid File
     * @return
     * @throws IOException
     */
    private String generateSopGridMapFile(Ida ida, String ecode, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(MAPFILE_FIELD_LABEL);
        sb.append(System.getProperty("line.separator"));
        String gridFileName = getGridSopMakeFileName(ecode);
        List<Ida> children = db.getIdas(ida.getId());
        for (Ida child : children) {
            List<Ida> subChildren = db.getIdas(child.getId());
            if (null != subChildren && 0 < subChildren.size()) {
                String childGridName = generateSopGridMapFile(child, ecode, path);
                sb.append(nodeToMapfileGrid(childGridName));
            } else {
                sb.append(nodeToMapfileField(child));
            }
        }
        File gridFile = new File(path + File.separator + gridFileName);
        writeContentToFile(sb.toString(), gridFile);
        return gridFileName;
    }

    private String nodeToMapfileField(Ida ida) {
        return nodeToMapFileObj(FIELD_IDENTIFY, ida);
    }

    private String getGridSopMakeFileName(String ecode) {
        String fileName = null;
        if (null != ecode) {
            fileName = FIELD_PRIFIX_STRING + ecode + fieldCount;
            fieldCount++;
        }
        return fileName;
    }

    private String nodeToMapfileGrid(String gridName) {
        return GRD_IDENTIFY + ":" + gridName + System.getProperty("line.separator");
    }

    private String nodeToMapFileObj(String type,Ida ida) {
        StringBuilder mapFieldBuilder = new StringBuilder();
        mapFieldBuilder.append(type);
        mapFieldBuilder.append(":");
        mapFieldBuilder.append(ida.getStructName());
        mapFieldBuilder.append(" ");
        if (StringUtils.isEmpty(ida.getLength())) {
            mapFieldBuilder.append("0");
        } else {
            String length = ida.getLength().trim();
            if(length.startsWith("(")){
                length = length.substring(1, length.length());
            }
            if(length.endsWith(")")){
                length = length.substring(0, length.length()-1);
            }
            if(length.contains(",")){
                String[] strs = length.split("\\,");
                mapFieldBuilder.append(strs[0]);
                ida.setScale(strs[1]);
            }else{
                mapFieldBuilder.append(length);
            }

        }
        mapFieldBuilder.append(" ");
        mapFieldBuilder.append(getSopMapfileType(ida.getType()));
        mapFieldBuilder.append(" ");
        if (StringUtils.isEmpty(ida.getScale())) {
            mapFieldBuilder.append("0");
        } else {
            mapFieldBuilder.append(ida.getScale());
        }
        mapFieldBuilder.append(" ");
        // align to do this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // fillchar this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // turnmode this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // string this is hard coded with NULL
        mapFieldBuilder.append("NULL");
        mapFieldBuilder.append(" ");
        // encrypt this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(System.getProperty("line.separator"));
        return mapFieldBuilder.toString();
    }

    private String getSopMapfileType(String formerType) {
        if(StringUtils.isEmpty(formerType)){
            return "";
        }
        if (formerType.toLowerCase().startsWith("char") || formerType.toLowerCase().startsWith("string") || formerType.toLowerCase().startsWith("varchar")) {
            return "S";
        } else if (formerType.toLowerCase().startsWith("decimal") || formerType.toLowerCase().startsWith("double")) {
            return "D";
        } else if (formerType.toLowerCase().startsWith("date")) {
            return "Q";
        } else if (formerType.toLowerCase().startsWith("integer")  || formerType.toLowerCase().startsWith("number")) {
            return "N";
        } else if (formerType.toLowerCase().startsWith("short")) {
            return "n";
        } else if (formerType.toLowerCase().startsWith("long")) {
            return "L";
        } else if (formerType.toLowerCase().startsWith("time")) {
            return "T";
        } else if (formerType.toLowerCase().startsWith("hex")) {
            return "H";
        } else if (formerType.toLowerCase().startsWith("chn")) {
            return "B";
        }
        return formerType;
    }
    private String nodeToSopMapfileObject(SDA sda,String ecode, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (null != sda) {
            if(StringUtils.isNotEmpty(sda.getType()) && ("array".equalsIgnoreCase(sda.getType()) || "struct".equalsIgnoreCase(sda.getType()))){
                List<SDA> chidren = db.getSDAs(sda.getId());
                if (null != chidren && 0 < chidren.size()) {
                    String gridName = generateSopGridMapFile(sda, ecode, path);
                    sb.append(nodeToMapfileGrid(gridName));
                }
            }
             else {
                sb.append(nodeToMapfileField(sda));
            }
        }
        return sb.toString();
    }

    /**
     * 生成Grid File
     * @return
     * @throws IOException
     */
    private String generateSopGridMapFile(SDA sda, String ecode, String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(MAPFILE_FIELD_LABEL);
        sb.append(System.getProperty("line.separator"));
        String gridFileName = getGridSopMakeFileName(ecode);
        List<SDA> children = db.getSDAs(sda.getId());
        for (SDA child : children) {
            List<Ida> subChildren = db.getIdas(child.getId());
            if (null != subChildren && 0 < subChildren.size()) {
                String childGridName = generateSopGridMapFile(child, ecode, path);
                sb.append(nodeToMapfileGrid(childGridName));
            } else {
                sb.append(nodeToMapfileField(child));
            }
        }
        File gridFile = new File(path + File.separator + gridFileName);
        writeContentToFile(sb.toString(), gridFile);
        return gridFileName;
    }

    private String nodeToMapfileField(SDA sda) {
        return nodeToMapFileObj(FIELD_IDENTIFY, sda);
    }
    private String nodeToMapFileObj(String type, SDA sda) {
        StringBuilder mapFieldBuilder = new StringBuilder();
        mapFieldBuilder.append(type);
        mapFieldBuilder.append(":");
        mapFieldBuilder.append(sda.getStructName());
        mapFieldBuilder.append(" ");
        Metadata metadata = db.getMetadata(sda.getMetadataId());
        if (StringUtils.isEmpty(metadata.getLength())) {
            mapFieldBuilder.append("0");
        } else {
            String length = metadata.getLength().trim();
            if(length.startsWith("(")){
                length = length.substring(1, length.length());
            }
            if(length.endsWith(")")){
                length = length.substring(0, length.length()-1);
            }
            if(length.contains(",")){
                String[] strs = length.split("\\,");
                mapFieldBuilder.append(strs[0]);
                metadata.setScale(strs[1]);
            }else{
                mapFieldBuilder.append(length);
            }
        }
        mapFieldBuilder.append(" ");
        mapFieldBuilder.append(getSopMapfileType(metadata.getType()));
        mapFieldBuilder.append(" ");
        if (StringUtils.isEmpty(metadata.getScale())) {
            mapFieldBuilder.append("0");
        } else {
            mapFieldBuilder.append(metadata.getScale());
        }
        mapFieldBuilder.append(" ");
        // align to do this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // fillchar this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // turnmode this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(" ");
        // string this is hard coded with NULL
        mapFieldBuilder.append("NULL");
        mapFieldBuilder.append(" ");
        // encrypt this is hard coded with 0
        mapFieldBuilder.append("0");
        mapFieldBuilder.append(System.getProperty("line.separator"));
        return mapFieldBuilder.toString();
    }

}
