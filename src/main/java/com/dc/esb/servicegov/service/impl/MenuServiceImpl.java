package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.PermissionDAOImpl;
import com.dc.esb.servicegov.dao.impl.SGMenuCategoryDAOImpl;
import com.dc.esb.servicegov.dao.impl.SGMenuDAOImpl;
import com.dc.esb.servicegov.entity.Permission;
import com.dc.esb.servicegov.entity.SGMenu;
import com.dc.esb.servicegov.entity.SGMenuCategory;
import com.dc.esb.servicegov.util.EasyUiTreeUtil;
import com.dc.esb.servicegov.util.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2016/2/18.
 */
@Service
@Transactional
public class MenuServiceImpl  {
    @Autowired
    private SGMenuCategoryDAOImpl sgMenuCategoryDAO;
    @Autowired
    private SGMenuDAOImpl sgMenuDAO;
    @Autowired
    private PermissionCategoryServiceImpl permissionCategoryService;
    @Autowired
    private PermissionDAOImpl permissionDAO;
    /**
     * @return 菜单树
     */
    public List<TreeNode> menuTree(){
        //获取SGMenuCategory
        List<SGMenuCategory> sgMenuCategories = sgMenuCategoryDAO.getAll();
        for(SGMenuCategory sgMenuCategory : sgMenuCategories){
            sgMenuCategory.setType("category");
            sgMenuCategory.setState("closed");
        }
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "Id");
        fields.put("text", "chineseName");
        fields.put("state", "state");
        fields.put("append1", "type");

        List<TreeNode> treeNodes = EasyUiTreeUtil.getInstance().convertTree(sgMenuCategories, fields);

        //根节点
        TreeNode root = new TreeNode();
        root.setId("root");
        root.setText("菜单权限树");
        root.setChildren(treeNodes);

        List<TreeNode> result = new ArrayList<TreeNode>();
        result.add(root);
        return result;
    }

    public List<TreeNode> subMenuTree(String id){
        //获取SGMenu
        List<SGMenu> sgMenus = sgMenuDAO.findBy("sgMenuCategoryId", id);
        for(SGMenu sgMenu : sgMenus){
            sgMenu.setType("menu");
        }
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("id", "Id");
        fields.put("text", "name");
        fields.put("append1", "type");

        List<TreeNode> treeNodes = EasyUiTreeUtil.getInstance().convertTree(sgMenus, fields);
        return treeNodes;
    }

    public void saveAdd(String categoryId, String categoryType, String menuName, String menuType, String permissionStr){
        String permissionId = null;
        if(StringUtils.isNotEmpty(permissionStr)){
            String[] per = permissionStr.split("-");
            String hql = " from Permission where description = ? and name = ?";
            Permission permission = permissionDAO.findUnique(hql, per[0], per[1]);
            if(null != permission){
                permissionId = permission.getId();
            }else{
                permission = new Permission();
                permission.setId(new Date().getTime() + "");
                permission.setDescription(per[0]);
                permission.setName(per[1]);
                permissionDAO.save(permission);
                permissionId = permission.getId();
            }
        }
        if("category".equals(menuType)){//导航菜单
            SGMenuCategory sgMenuCategory = new SGMenuCategory();
            sgMenuCategory.setId(new Date().getTime() + "");
            sgMenuCategory.setChineseName(menuName);
            if(StringUtils.isEmpty(categoryId)){
                categoryId = null;
            }
            sgMenuCategory.setParentId(categoryId);
            sgMenuCategory.setTemp(permissionStr);
            sgMenuCategoryDAO.save(sgMenuCategory);
        }
        if("menu".equals(menuType)){//按钮
            SGMenu sgMenu = new SGMenu();
            sgMenu.setId(new Date().getTime() + "");
            sgMenu.setName(menuName);
            if(StringUtils.isEmpty(categoryId)){
                categoryId = null;
            }
            sgMenu.setSgMenuCategoryId(categoryId);
            sgMenu.setPermissionId(permissionId);
            sgMenuDAO.save(sgMenu);
        }
    }
    public void saveEdit(String menuId, String categoryId, String categoryType, String menuName, String menuType, String permissionStr){
        String permissionId = null;
        if(StringUtils.isNotEmpty(permissionStr)){
            String[] per = permissionStr.split("-");
            String hql = " from Permission where description = ? and name = ?";
            Permission permission = permissionDAO.findUnique(hql, per[0], per[1]);
            if(null != permission){
                permissionId = permission.getId();
            }else{
                permission = new Permission();
                permission.setId(new Date().getTime() + "");
                permission.setDescription(per[0]);
                permission.setName(per[1]);
                permissionDAO.save(permission);
                permissionId = permission.getId();
            }
        }
        if("category".equals(menuType)){//导航菜单
            SGMenuCategory sgMenuCategory = sgMenuCategoryDAO.findUniqueBy("id", menuId);
            if(null != sgMenuCategory){
                sgMenuCategory.setChineseName(menuName);
                if(StringUtils.isEmpty(categoryId)){
                    categoryId = null;
                }
                sgMenuCategory.setParentId(categoryId);
                sgMenuCategory.setTemp(permissionStr);
                sgMenuCategoryDAO.save(sgMenuCategory);
            }
        }
        if("menu".equals(menuType)){//按钮
            SGMenu sgMenu = sgMenuDAO.findUniqueBy("Id", menuId);
            sgMenu.setName(menuName);
            if(StringUtils.isEmpty(categoryId)){
                categoryId = null;
            }
            sgMenu.setSgMenuCategoryId(categoryId);
            sgMenu.setPermissionId(permissionId);
            sgMenuDAO.save(sgMenu);
        }
    }
}
