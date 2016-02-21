/**
 * Created by lmd on 2016/1/28.
 */
var taskManage = {
    "createTask": function createTask(params, callBack) {
        $.ajax({
            "type": "POST",
            "contentType": "application/json;charset=utf-8",
            "url": "/task/add",
            "data": JSON.stringify(params),
            "dataType": "json",
            "success": function (result) {
                callBack(result);
            },
            "complete": function (responce) {
                var resText = responce.responseText;
                if (resText.toString().indexOf("没有操作权限") > 0) {
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    "query" : function(params, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/task/query",
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
            "url" : "/task/delete/"+id,
            "dataType" : "json",
            "success" : function(result) {
                callBack(result);
            },
            "complete":function(responce){
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    }
}