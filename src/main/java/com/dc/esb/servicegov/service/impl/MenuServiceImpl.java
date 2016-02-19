package com.dc.esb.servicegov.service.impl;

import com.dc.esb.servicegov.dao.impl.PermissionDAOImpl;
import com.dc.esb.servicegov.dao.impl.SGMenuCategoryDAOImpl;
import com.dc.esb.servicegov.dao.impl.SGMenuDAOImpl;
import com.dc.esb.servicegov.entity.SGMenu;
import com.dc.esb.servicegov.entity.SGMenuCategory;
import com.dc.esb.servicegov.util.EasyUiTreeUtil;
import com.dc.esb.servicegov.util.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        fields.put("id", "id");
        fields.put("text", "name");
        fields.put("append1", "type");

        List<TreeNode> treeNodes = EasyUiTreeUtil.getInstance().convertTree(sgMenus, fields);
        return treeNodes;
    }
}
