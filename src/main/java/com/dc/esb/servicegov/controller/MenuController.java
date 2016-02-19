package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.service.impl.MenuServiceImpl;
import com.dc.esb.servicegov.util.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;

    //构建菜单树
    @RequestMapping(method = RequestMethod.GET, value = "/menuTree", headers = "Accept=application/json")
    @ResponseBody
    public List<TreeNode> menuTree(HttpServletRequest req){
        List<TreeNode> rows = menuService.menuTree();

        return rows;
    }

    //构建菜单树
    @RequestMapping(method = RequestMethod.GET, value = "/nodeContent", headers = "Accept=application/json")
    @ResponseBody
    public List<TreeNode> nodeContent(String id){
        List<TreeNode> rows = menuService.subMenuTree(id);

        return rows;
    }
}
