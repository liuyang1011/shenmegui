package com.dc.esb.servicegov.inport.task;

import com.dc.esb.servicegov.entity.OperationPK;
import com.dc.esb.servicegov.service.impl.MappingFileImportSeviceImpl;
import com.dc.esb.servicegov.vo.MappingImportIndexRowVO;
import com.dc.esb.servicegov.wsdl.impl.SpdbWSDLGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MappingFileImportTask implements Callable<Boolean>{
    protected Log logger = LogFactory.getLog(getClass());

    private Workbook workbook;
    private MappingImportIndexRowVO indexVO;
    private Set<String> allReqHeadMetadatas;
    private Set<String> allResHeadMetadatas;
    private Set<String> allLocalHeadMetadatas;
    private CountDownLatch countDown;
    private MappingFileImportSeviceImpl mappingFileImportSevice;

    public void init(Workbook workbook, MappingImportIndexRowVO indexVO, Set<String> allReqHeadMetadatas, Set<String> allResHeadMetadatas, CountDownLatch countDown,
                     MappingFileImportSeviceImpl mappingFileImportSevice,Set<String> allLocalHeadMetadatas) {
        this.workbook = workbook;
        this.indexVO = indexVO;
        this.allReqHeadMetadatas = allReqHeadMetadatas;
        this.allResHeadMetadatas = allResHeadMetadatas;
        this.allLocalHeadMetadatas = allLocalHeadMetadatas;
        this.countDown = countDown;
        this.mappingFileImportSevice = mappingFileImportSevice;
    }

//    public void run() {
//        try {
//            logger.info("===========开始导入INDEX页第" + indexVO.getIndexNum() + "条记录,接口代码[" + indexVO.getInterfaceId() + "]=============");
//            Date start = new Date();
////            mappingFileImportSevice.imoportIndexRecordWithMetadatas(workbook, indexVO, allReqHeadMetadatas, allResHeadMetadatas);
//            logger.info("===================导入结束，耗时" + (new Date().getTime() - start.getTime()) + "ms==================");
//        }catch (Exception e){
//            mappingFileImportSevice.logMsg("index页第" + indexVO.getIndexNum() + "条记录导入失败,导入时发生异常！异常信息："+e.getMessage());
//            logger.error(e,e);
//        }finally {
//            countDown.countDown();
//        }
//    }


    public Boolean call() throws Exception{
        boolean boo = mappingFileImportSevice.imoportIndexRecordWithMetadatas(workbook,indexVO,allReqHeadMetadatas,allResHeadMetadatas,allLocalHeadMetadatas);
        countDown.countDown();
        return boo;
    }




}


















