package com.dc.esb.servicegov.controller;

import com.dc.esb.servicegov.dao.impl.PermissionDAOImpl;
import com.dc.esb.servicegov.entity.OperationLog;
import com.dc.esb.servicegov.entity.Permission;
import com.dc.esb.servicegov.entity.PermissionCategory;
import com.dc.esb.servicegov.entity.RolePermissionRelation;
import com.dc.esb.servicegov.service.impl.PermissionCategoryServiceImpl;
import com.dc.esb.servicegov.service.impl.PermissionServiceImpl;
import com.dc.esb.servicegov.service.impl.RolePermissionRelationServiceImpl;
import com.dc.esb.servicegov.service.impl.SystemLogServiceImpl;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by lenovo on 2015/7/13.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private SystemLogServiceImpl systemLogService;
    @Autowired
    private PermissionDAOImpl permissionDAOImpl;
    @Autowired
    private PermissionServiceImpl permissionService;
    @Autowired
    private PermissionCategoryServiceImpl permissionCategoryService;
    @Autowired
    private RolePermissionRelationServiceImpl rolePermissionRelationService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll", headers = "Accept=application/json")
    public
    @ResponseBody
    Map<String, String> getAll() {
        Map<String, String> resMap = new HashMap<String, String>();
        List<Permission> permissions = permissionDAOImpl.getAll();
        for (Permission p : permissions) {
            String key = p.getChineseDescription();
            String val= p.getId()+"&"+ p.getChineseName();
            if (resMap.containsKey(p.getChineseDescription())) {
                val= p.getId()+"&"+ p.getChineseName();
                resMap.put(key, resMap.get(key) + "," + val);
            } else {
                resMap.put(key,val);
            }
        }
        return resMap;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPermissionTree/{roleId}", headers = "Accept=application/json")
    public
    @ResponseBody
    List<PermissionServiceImpl.PermissionTreeBean> getPermissionTree(@PathVariable("roleId") String roleId) {
        List<PermissionCategory> permissionCategoryList = permissionCategoryService.getAll();
        List<Permission> permissionList = permissionService.getAll();
        return permissionService.getTreeJson(permissionCategoryList, permissionList,roleId);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/savePermission/{roleId}", headers = "Accept=application/json")
    public
    @ResponseBody
    boolean savePermission(@RequestBody ArrayList list,@PathVariable("roleId") String roleId) {
        OperationLog operationLog = systemLogService.record("权限","保存权限","角色ID：" + roleId + "； 权限数量：" + list.size());
        parsePermissionData(list,roleId);

        systemLogService.updateResult(operationLog);
        return true;

    }
    //权限递归
    private void parsePermissionData(ArrayList list,String roleId){
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<String,Object> map = (LinkedHashMap<String,Object>)list.get(i);
            if (map.get("type").equals("permission")){
                //权限数据
                String permissionId = (String)map.get("id");//权限id
                String permissionState = (String) map.get("permissionState");
                Map<String,String> params = new HashMap<String, String>();
                params.put("roleId",roleId);
                params.put("permissionId",permissionId);
                RolePermissionRelation relation = rolePermissionRelationService.findUniqueBy(params);
                if(null == relation){//添加
                    if(permissionState.equals("1")){
                        relation = new RolePermissionRelation();
                        relation.setPermissionId(permissionId);
                        relation.setRoleId(roleId);
                        rolePermissionRelationService.insert(relation);
                    }
                }else{//删除
                    if(permissionState.equals("0")){
                        rolePermissionRelationService.delete(relation);
                    }
                }
            }else{
                ArrayList children = (ArrayList)map.get("children");
                if(children.size()>0){
                    parsePermissionData(children,roleId);
                }
            }
        }
    }

    @ExceptionHandler({UnauthenticatedException.class, UnauthorizedException.class})
    public String processUnauthorizedException() {
        return "403";
    }
}
