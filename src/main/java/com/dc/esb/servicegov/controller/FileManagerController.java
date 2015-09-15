package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.FileManagerService;
import com.dc.esb.servicegov.service.SystemService;
import com.dc.esb.servicegov.service.impl.ProcessContextServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import com.dc.esb.servicegov.util.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/7/12.
 */
@Controller
@RequestMapping("/fileManager")
public class FileManagerController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    FileManagerService fileManagerService;
    @Autowired
    private ProcessContextServiceImpl processContextService;
    @Autowired
    private SystemService systemService;

    @RequiresPermissions({"file-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/getAll", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> getAll(HttpServletRequest req) {
        String starpage = req.getParameter("page");

        String rows = req.getParameter("rows");

        String fileName = req.getParameter("fileName");
        String fileDesc = req.getParameter("fileDesc");
        String systemId = req.getParameter("systemId");

        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();

        StringBuffer hql = new StringBuffer("FROM FileManager t1 WHERE 1=1");
        if (fileName != null && !"".equals(fileName)) {
            SearchCondition searchCond = new SearchCondition();
            hql.append(" and t1.fileName like ?");
            searchCond.setField("fileName");
            searchCond.setFieldValue("%" + fileName + "%");
            searchConds.add(searchCond);
        }

        if (fileDesc != null && !"".equals(fileDesc)) {
            SearchCondition searchCond = new SearchCondition();
            hql.append(" and t1.fileDesc like ?");
            searchCond.setField("fileDesc");
            searchCond.setFieldValue("%" + fileDesc + "%");
            searchConds.add(searchCond);
        }

        if (systemId != null && !"".equals(systemId)) {
            SearchCondition searchCond = new SearchCondition();
            hql.append(" and t1.systemId = ?");
            searchCond.setField("systemId");
            searchCond.setFieldValue(systemId);
            searchConds.add(searchCond);
        }

        Page page = fileManagerService.findPage(hql.toString(), Integer.parseInt(rows), searchConds);
        page.setPage(Integer.parseInt(starpage));


        List<FileManager> fms = fileManagerService.findBy(hql.toString(),page,searchConds);
        for(FileManager f:fms){
            if(null != f.getSystem()){
                f.setSystemName(f.getSystem().getSystemChineseName());
            }
            f.setSystem(null);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getResultCount());
        map.put("rows", fms);
        return map;

    }

    @RequiresPermissions({"file-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/addfile")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("fileDesc") String fileDesc,
                             @RequestParam("systemId") String systemId, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        FileManager fm = new FileManager();
        fm.setFileName(fileName);
        fm.setFileSize(Utils.byte2MGT(fileSize + ""));
        fm.setFileDesc(fileDesc);
        fm.setSystemId(systemId);
        //保存
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/");
            //设置保存路径
            String path = new File(request.getRealPath(request.getRequestURI())).getParentFile().getParent() + "/data/"
                    + dateformat.format(new Date());
            String fileStoreName = "" + new Date().getTime();
            fm.setFilePath(path + "/" + fileStoreName);
            File destFile = new File(path, fileStoreName);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            file.transferTo(destFile);
            fm.setOptDate(DateUtils.format(new Date()));
            fileManagerService.save(fm);
        } catch (Exception e) {
            logger.error("保存文件失败，出现异常：" + e.getMessage());
        }
        return "forward:/jsp/sysadmin/file_list.jsp";
    }

    @RequiresPermissions({"file-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/addfile/{processId}")
    public String uploadFileWithProcess(@RequestParam("file") MultipartFile file,
                                        @RequestParam("fileDesc") String fileDesc,
                                        @RequestParam("systemId") String systemId,
                                        @PathVariable("processId") String processId, HttpServletRequest request) {
        String optUser = (String)SecurityUtils.getSubject().getPrincipal();
        String optDate = DateUtils.format(new Date());
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        FileManager fm = new FileManager();
        fm.setFileName(fileName);
        fm.setFileSize(Utils.byte2MGT(fileSize + ""));
        fm.setFileDesc(fileDesc);
        fm.setSystemId(systemId);
        //保存
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/");
            //设置保存路径
            String path = new File(request.getRealPath(request.getRequestURI())).getParentFile().getParent() + "/data/" + dateformat.format(new Date());
            String fileStoreName = "" + new Date().getTime();
            fm.setFilePath(path + "/" + fileStoreName);
            File destFile = new File(path, fileStoreName);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            file.transferTo(destFile);
            fm.setOptDate(optDate);

            fileManagerService.save(fm);

            com.dc.esb.servicegov.entity.ProcessContext processContext2 = new com.dc.esb.servicegov.entity.ProcessContext();
            processContext2.setName("接口需求文件上传");
            processContext2.setProcessId(processId);
            processContext2.setKey("system");
            com.dc.esb.servicegov.entity.System system = systemService.getById(systemId);
            processContext2.setValue(systemId);
            processContext2.setType("result");
            processContext2.setRemark("修改了系统[" + system.getSystemAb() + "("+ system.getSystemChineseName() +")"
                    + "]进行了需求文件上传");
            processContext2.setOptDate(optDate);
            processContext2.setOptUser(optUser);
            processContextService.save(processContext2);

            com.dc.esb.servicegov.entity.ProcessContext processContext = new com.dc.esb.servicegov.entity.ProcessContext();
            processContext.setName("接口需求文件上传");
            processContext.setProcessId(processId);
            processContext.setKey("file");
            processContext.setValue(fm.getFileId());
            processContext.setType("result");
            processContext.setRemark("在系统[" + system.getSystemAb() + "("+ system.getSystemChineseName() +")"
                    +  "]中上传了需求文件[" + fm.getFileName() + "]");
            processContext.setOptDate(optDate);
            processContext.setOptUser(optUser);
            processContextService.save(processContext);
        } catch (Exception e) {
            logger.error("保存文件失败，出现异常：" + e.getMessage());
        }
        return "forward:/jsp/sysadmin/file_list.jsp";
    }

    @RequiresPermissions({"file-delete"})
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{fileId}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean delete(@PathVariable
                   String fileId) {
        FileManager fm = fileManagerService.getById(fileId);
        if (fm != null && fm.getFilePath() != null) {
            File file = new File(fm.getFilePath());
            if (file != null && file.exists()) {
                file.delete();
            }
        }
        fileManagerService.delete(fm);
        return true;
    }

    @RequiresPermissions({"file-download"})
    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpServletResponse res, String fileId) throws IOException {
        FileManager fm = fileManagerService.findUniqueBy("fileId", fileId);
        if(fm != null){
            File file=new File(fm.getFilePath());
            String fileName = java.net.URLEncoder.encode(fm.getFileName(), "UTF-8");
//            HttpHeaders headers = new HttpHeaders();
//            String fileName=new String(fm.getFileName().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
//            headers.setContentDispositionFormData("attachment", fileName);
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
//                    headers, HttpStatus.CREATED);

            OutputStream os = res.getOutputStream();
            try {
                res.reset();
                res.setHeader("Content-Disposition", "attachment; filename="+ fileName);
                res.setContentType("application/octet-stream");
                os.write(FileUtils.readFileToByteArray(file));
                os.flush();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
        return null;
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }

}
