/**
 * Created by vincentfxz on 15/7/6.
 */
var taskManagers = {
    "createTask": function createTask(task, params, callBack) {
        //{user}/create/{type}
        var url = "/serviceIdentify/" + task.userId + "/create/" + task.taskType+"/"+task.interfaceId+"/"+task.serviceId+"/"+task.operationId;
        $.ajax({
            "type": "POST",
            "contentType": "application/json;charset=utf-8",
            "url": url,
            "data": JSON.stringify(params),
            "dataType": "json",
            "success": function (result) {
                alert("创建人物ID:["+result.processId+"],流程ID:["+result.taskId+"]");
                callBack(result);

            },
            "complete": function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    }

};