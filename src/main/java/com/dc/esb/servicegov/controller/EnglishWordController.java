package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.EnglishWordDAOImpl;
import com.dc.esb.servicegov.dao.support.Page;
import com.dc.esb.servicegov.dao.support.SearchCondition;
import com.dc.esb.servicegov.entity.*;
import com.dc.esb.servicegov.service.impl.EnglishWordServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import com.dc.esb.servicegov.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.System;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/englishWord")
public class EnglishWordController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    EnglishWordDAOImpl englishWordDAO ;
    @Autowired
    private EnglishWordServiceImpl englishWordService;

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, Object> getAll(HttpServletRequest req) {
        int pageNo = Integer.parseInt(req.getParameter("page"));
        int rowCount = Integer.parseInt(req.getParameter("rows"));
        String englishWord = req.getParameter("englishWord");
        String chineseWord = req.getParameter("chineseWord");
        String firstWord = req.getParameter("firstWord");

        String hql = "FROM EnglishWord t WHERE 1=1 ";
        List<SearchCondition> searchConds = new ArrayList<SearchCondition>();
        if(englishWord!=null && !"".equals(englishWord)){
            hql += " and englishWord like ?";
            SearchCondition search = new SearchCondition("englishWord","%"+englishWord+"%");
            searchConds.add(search);
        }
        if(chineseWord!=null && !"".equals(chineseWord)){
            hql += " and chineseWord like ?";
            try {
                chineseWord = URLDecoder.decode(chineseWord, "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SearchCondition search = new SearchCondition("chineseWord","%"+chineseWord+"%");
            searchConds.add(search);
        }
        if(firstWord!=null && !"".equals(firstWord)){
            hql += " and firstWord like ? ";
            SearchCondition search = new SearchCondition("firstWord","%"+firstWord.toUpperCase()+"%");
            searchConds.add(search);
        }
        hql +="order by firstWord asc";
        Page page = englishWordService.findPage(hql, rowCount, searchConds);
        page.setPage(pageNo);
        List<EnglishWord> rows = englishWordService.findBy(hql,page,searchConds);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page.getResultCount());
        result.put("rows", rows);
        return result;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getById/{Id}", headers = "Accept=application/json")
    public
    @ResponseBody
    EnglishWord getEnglishWordById(@PathVariable(value = "Id") String Id) {
        return englishWordService.getById(Id);
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByEnglishWord/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByEnglishWord(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("englishWord", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }
    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getAllFirstWord", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getAll() {
        String hql="select distinct firstWord from EnglishWord order by firstWord asc";
        List<Objects> list= englishWordService.getDAO().findBy(hql);
        List<EnglishWord> lists=new ArrayList<EnglishWord>();
       for(int i=0;i<list.size();i++){
           EnglishWord ew=new EnglishWord();
           ew.setFirstWord(String.valueOf(list.get(i)));
           lists.add(ew);
       }
        return lists;
    }
    @RequiresPermissions({"englishWord-get"})
    @RequestMapping("/getByFirstWord")
    @ResponseBody
    public List<EnglishWord> getByFirstWord(String firstWord) {
        List<EnglishWord> rows;
        rows = englishWordService.findBy("firstWord", firstWord);
        return rows;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByWordAb/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByWordAb(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("wordAb", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }



    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByChineseWord/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByChineseWord(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("chineseWord", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByPotUser/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByPotUser(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("potUser", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByPotDate/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByPotDate(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("potDate", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.GET, value = "/getByRemark/{value}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<EnglishWord> getEnglishWordByRemark(@PathVariable String value) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("remark", value);
        List<EnglishWord> words = englishWordService.findBy(params);
        return words;
    }

    @RequiresPermissions({"englishWord-get"})
    @RequestMapping(method = RequestMethod.POST, value = "/query", headers = "Accept=application/json" )
    @ResponseBody
    public Map<String, Object> query(@RequestBody Map<String, String> params) {
        List<EnglishWord> rows = englishWordService.findLikeAnyWhere(params);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", rows.size());
        result.put("rows", rows);
        return result;
    }

    @RequiresPermissions({"englishWord-add"})
    @RequestMapping(method = RequestMethod.POST, value = "/add", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean save(@RequestBody EnglishWord word) {
        OperationLog operationLog = systemLogService.record("英文单词", "添加", "中文名称：" + word.getChineseWord() + ";英文名称：" + word.getEnglishWord());
        String firstWord = word.getWordAb().substring(0, 1);
        word.setFirstWord(firstWord.toUpperCase());
        word.setOptDate(DateUtils.format(new Date()));
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        word.setOptUser(userName);
        String createUserName = (String) SecurityUtils.getSubject().getPrincipal();
        word.setCreateUser(createUserName);
        englishWordService.save(word);

        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"englishWord-update"})
    @RequestMapping(method = RequestMethod.POST, value = "/modify/{oldEnglishWord}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean modify(@RequestBody EnglishWord word,@PathVariable String oldEnglishWord) {
        OperationLog operationLog = systemLogService.record("英文单词","修改","中文名称：" + word.getChineseWord() + ";英文名称：" + word.getEnglishWord());

        if(!word.getEnglishWord().equals(oldEnglishWord)){
            String hql = "select englishWord from EnglishWord where englishWord ='"+word.getEnglishWord()+"'";
            List<EnglishWord>  list = englishWordDAO.findBy(hql);
            if(list.size()!=0){
                return false;
            }
        }
        word.setFirstWord(word.getWordAb().substring(0, 1));
        word.setOptDate(DateUtils.format(new Date()));
        String userName = (String) SecurityUtils.getSubject().getPrincipal();
        word.setOptUser(userName);
        englishWordService.update(word);

        systemLogService.updateResult(operationLog);
        return true;
    }
    @RequiresPermissions({"englishWord-update"})
    @RequestMapping(method = RequestMethod.GET, value = "/editPage", headers = "Accept=application/json")
    public ModelAndView editPage(String id) {
        ModelAndView mv = new ModelAndView("../jsp/englishWord/edit");
        List<EnglishWord> list = englishWordService.getId(id);
        if (list != null && list.size() > 0) {
            EnglishWord entity = list.get(0);
            mv.addObject("entity", entity);
        }

        return mv;
    }

    @RequiresPermissions({"englishWord-delete"})
    @RequestMapping(method = RequestMethod.POST, value = "/deleteWord", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean deleteWord(@RequestBody EnglishWord englishWord) throws Exception {
        OperationLog operationLog = systemLogService.record("英文单词", "删除", "名称：" + englishWord.getChineseWord());
        englishWordService.delete(englishWord);
        systemLogService.updateResult(operationLog);
        return true;
    }

    @RequiresPermissions({"englishWord-delete"})
    @RequestMapping(method = RequestMethod.POST, value = "/delete", headers = "Accept=application/json")
    public @ResponseBody
    boolean delete(String ids) {
        OperationLog operationLog = systemLogService.record("英文单词", "批量删除", "ID：" + ids);
        for (String id : ids.split(",")) {
            englishWordService.deleteById(id);
        }
        systemLogService.updateResult(operationLog);
        return true;
    }
    /**
     * 英文单词esglisgAb唯一性验证
     *
     * @param englishWord
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/uniqueValid", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean uniqueValid(String englishWord) {
        return englishWordService.uniqueValid(englishWord);
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
