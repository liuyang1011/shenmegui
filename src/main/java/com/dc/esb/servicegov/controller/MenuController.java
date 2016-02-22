package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.entity.Permission;
import com.dc.esb.servicegov.entity.SGMenu;
import com.dc.esb.servicegov.entity.SGMenuCategory;
import com.dc.esb.servicegov.service.impl.MenuServiceImpl;
import com.dc.esb.servicegov.service.impl.PermissionServiceImpl;
import com.dc.esb.servicegov.service.impl.SGMenuCategoryServiceImpl;
import com.dc.esb.servicegov.service.impl.SGMenuServiceImpl;
import com.dc.esb.servicegov.util.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private SGMenuCategoryServiceImpl sgMenuCategoryService;
    @Autowired
    private SGMenuServiceImpl sgMenuService;
    @Autowired
    private PermissionServiceImpl permissionService;

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
        if(null == rows){
            rows = new ArrayList<TreeNode>();
        }
        return rows;
    }

    //保存添加
    @RequestMapping(method = RequestMethod.POST, value = "/saveAdd", headers = "Accept=application/json")
    @ResponseBody
    public boolean saveAdd(String categoryId, String categoryType, String menuName, String menuType, String permission){
        menuService.saveAdd(categoryId, categoryType, menuName, menuType, permission);
        return true;
    }
    //修改页面
    @RequestMapping(method = RequestMethod.GET, value = "/editPage")
    public ModelAndView editPage(String menuId,String menuType){
        ModelAndView  mv = new ModelAndView("menu/menucategory_edit");
        if("category".equals(menuType)){
            SGMenuCategory entity = sgMenuCategoryService.findUniqueBy("id", menuId);
            mv.addObject("menuName", entity.getChineseName());
            mv.addObject("menuId", entity.getId());
            mv.addObject("type", "category");
            mv.addObject("permission", entity.getTemp());
            mv.addObject("categoryId", entity.getParentId());
            SGMenuCategory parent = sgMenuCategoryService.findUniqueBy("id", entity.getParentId());
            if(null != parent){
                mv.addObject("categoryName", parent.getChineseName());
            }else{
                mv.addObject("categoryName", "");
            }
        }else{
            SGMenu entity = sgMenuService.findUniqueBy("id", menuId);
            mv.addObject("menuName", entity.getName());
            mv.addObject("menuId", entity.getId());
            mv.addObject("type", "menu");
            Permission permission = permissionService.findUniqueBy("id", entity.getPermissionId());
            if(null != permission){
                mv.addObject("permission", permission.getDescription() + "-" + permission.getName());
            }
            SGMenuCategory sgMenuCategory = sgMenuCategoryService.findUniqueBy("id", entity.getSgMenuCategoryId());
            if(null != sgMenuCategory){
                mv.addObject("categoryId", sgMenuCategory.getId());
                mv.addObject("categoryName", sgMenuCategory.getChineseName());
            }
        }
        return mv;
    }
    //保存添加
    @RequestMapping(method = RequestMethod.POST, value = "/saveEdit", headers = "Accept=application/json")
    @ResponseBody
    public boolean saveEdit(String menuId, String categoryId, String categoryType, String menuName, String menuType, String permission){
        menuService.saveEdit(menuId, categoryId, categoryType, menuName, menuType, permission);
        return true;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public @ResponseBody boolean delete(String menuId,String menuType){
        if("category".equals(menuType)){
            sgMenuCategoryService.deleteById(menuId);
        }else{
            sgMenuService.deleteById(menuId);
        }
        return true;
    }
}
