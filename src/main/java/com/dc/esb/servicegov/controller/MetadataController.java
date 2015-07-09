package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.CategoryWord;
import com.dc.esb.servicegov.entity.Metadata;
import com.dc.esb.servicegov.service.impl.MetadataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/metadata")
public class MetadataController {
    @Autowired
    private MetadataServiceImpl metadataService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getAll() {
        return metadataService.getAllMetadata();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMetadataId/{metadataId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByMetadataId(@PathVariable(value = "metadataId") String metadataId) {
        return metadataService.getByMetadataId(metadataId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMetadataName/{metadataName}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByMetadataName(@PathVariable(value = "metadataName") String metadataName) {
        return metadataService.getByMetadataName(metadataName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByChineseName/{chineseName}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByChineseName(@PathVariable(value = "chineseName") String chineseName) {
        return metadataService.getByChineseName(chineseName);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCategoryWordId/{categoryWordId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByCategoryWordId(@PathVariable(value = "categoryWordId") String categoryWordId) {
        return metadataService.getByCategoryWordId(categoryWordId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByRemark/{remark}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByRemark(@PathVariable(value = "remark") String remark) {
        return metadataService.getByRemark(remark);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByType/{type}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByType(@PathVariable(value = "type") String type) {
        return metadataService.getByType(type);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByLength/{length}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByLength(@PathVariable(value = "length") String length) {
        return metadataService.getByLength(length);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByScale/{scale}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByScale(@PathVariable(value = "scale") String scale) {
        return metadataService.getByScale(scale);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByEnumId/{enumId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByEnumId(@PathVariable(value = "enumId") String enumId) {
        return metadataService.getByEnumId(enumId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMetadataAlias/{metadataAlias}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByMetadataAlias(@PathVariable(value = "metadataAlias") String metadataAlias) {
        return metadataService.getByMetadataAlias(metadataAlias);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByBussDefine/{bussDefine}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByBussDefine(@PathVariable(value = "bussDefine") String bussDefine) {
        return metadataService.getByBussDefine(bussDefine);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByBussRule/{bussRule}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByBussRule(@PathVariable(value = "bussRule") String bussRule) {
        return metadataService.getByBussRule(bussRule);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByDataSource/{dataSource}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByDataSource(@PathVariable(value = "dataSource") String dataSource) {
        return metadataService.getByDataSource(dataSource);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByTemplateId/{templateId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByTemplateId(@PathVariable(value = "templateId") String templateId) {
        return metadataService.getByTemplateId(templateId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByStatus/{status}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByStatus(@PathVariable(value = "status") String status) {
        return metadataService.getByStatus(status);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByVersion/{version}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByVersion(@PathVariable(value = "version") String version) {
        return metadataService.getByVersion(version);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByPotUser/{potUser}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByPotUser(@PathVariable(value = "potUser") String potUser) {
        return metadataService.getByPotUser(potUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByPotDate/{potDate}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByPotDate(@PathVariable(value = "potDate") String potDate) {
        return metadataService.getByPotDate(potDate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByAuditUser/{auditUser}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByAuditUser(@PathVariable(value = "auditUser") String auditUser) {
        return metadataService.getByAuditUser(auditUser);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByAuditDate/{auditDate}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<Metadata> getByAuditDate(@PathVariable(value = "auditDate") String auditDate) {
        return metadataService.getByAuditDate(auditDate);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean add(Metadata metadata) {
        metadataService.addMetadata(metadata);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean modify(Metadata metadata) {
        metadataService.modifyMetadata(metadata);
        return true;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{metadataId}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean delete(@PathVariable String metadataId) {
        metadataService.deleteMetadata(metadataId);
        return true;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public boolean deletes(String metadataIds) {
        metadataService.deleteMetadatas(metadataIds);
        return true;
    }

    @RequestMapping("/query")
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<String, Object>();
        @SuppressWarnings("unchecked")
        List<?> rows = metadataService.queryByCondition(request.getParameterMap());

        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    @RequestMapping("/query/processId/{processId}")
    @ResponseBody
    public Map<String, Object> query(HttpServletRequest request, @PathVariable("processId") String processId) {

        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("processId", processId);
        List<Metadata> rows = metadataService.findBy(params);

        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editPage", headers = "Accept=application/json")
    public ModelAndView editPage(String metadataId) {
        ModelAndView mv = new ModelAndView("../assets/metadata/edit");
        List<Metadata> list = metadataService.getByMetadataId(metadataId);
        if (list != null && list.size() > 0) {
            Metadata entity = list.get(0);
            mv.addObject("entity", entity);
        }

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uniqueValid", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean uniqueValid(String metadataId) {
        return metadataService.uniqueValid(metadataId);
    }

    //获取类别词接口
    @RequestMapping("/categoryWord")
    @ResponseBody
    public List<CategoryWord> categoryWord() {

        List<CategoryWord> rows = metadataService.categoryWord();

        return rows;
    }

    @RequestMapping("/servicePage")
    public ModelAndView servicePage(String serviceId) {
        ModelAndView mv = new ModelAndView("service/serviceIndex");
        return mv;
    }

    @RequestMapping("/audit/process/{processId}")
    public @ResponseBody boolean auditMetadata(@PathVariable("processId") String processId){
        Map<String, String> params = new HashMap<String, String>();
        params.put("processId", processId);
        List<Metadata> metadatas = metadataService.findBy(params);
        for(Metadata metadata : metadatas){
            metadata.setStatus("正式");
            metadataService.save(metadata);
        }
        return true;
    }
}