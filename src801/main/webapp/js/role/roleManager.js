
/**
用户管理
**/
var roleManager = {
    "add" : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/role/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    "getAll" : function(callBack){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/role/getAll",
            data: JSON.stringify(),
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    "query" : function(params, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/role/query",
            data: JSON.stringify(params),
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    "deleteById" : function(id,callBack) {
        $.ajax({
            "type" : "DELETE",
            "contentType" : "application/json; charset=utf-8",
            "url" : "/role/delete/"+id,
            "dataType" : "json",
            "success" : function(result) {
                callBack(result);
            }
        });
    },
    
    "getById": function(data, callBack){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/role/getById/"+data,
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    "checkUnique" : function(roleId, callBack){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/role/checkUnique/roleId/"+roleId,
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    "relation" : function(roleId, callBack){
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/role/getRelation/"+roleId,
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    }
};