/**
 * Created by vincentfxz on 15/7/6.
 */
$(function () {
	$('#user').combobox({
		url:'/user/getAllUser',
		method:'get',
		mode:'remote',
		valueField:'id',
		textField:'name'
	});
    //$("#createTaskBtn").click(function () {
    //    var taskType = "inter";
    //    var user =$('#user').combobox('getValue');
    //    var description = $("#description").val();
    //    var task = {};
    //    task.userId = user;
    //    task.taskType = taskType;
    //    var params = {};
    //    params.commentInput = description;
    //    params.userId = user;
    //    params.approved = false;
    //    taskManager.createTask(task,params,function(){
    //        alert("任务已经提交！");
    //        $("#w").window("close");
    //        $('#taskTable').datagrid('reload');
    //    });
    //});
});