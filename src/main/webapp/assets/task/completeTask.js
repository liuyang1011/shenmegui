/**
 * Created by vincentfxz on 15/7/7.
 */
$(function () {
	$('#nextUser').combobox({
        url:'/user/getAllUser',
		method:'get',
		mode:'remote',
		valueField:'id',
		textField:'name'
});
    $("#completeTaskBtn").click(function () {
        var task = {};

        task.taskId = parent.PROCESS_INFO.taskId;

        var nextUser = $("#nextUser").combobox('getValue');
        task.userId = "admin";
        var params = {};
        params.userId = nextUser;
        params.approved = true;
        taskManager.completeTask(task,params,function(){
            alert("任务已经处理！");
            $("#w").window("close");
            console.log($('#taskTable'));
            $('#taskTable').datagrid('reload');

            var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/task/mytask.jsp" style="width:100%;height:100%;"></iframe>';
            var title = "我的任务"
            var tab = $('#mainContentTabs').tabs('getSelected');  // get selected panel
            $('#mainContentTabs').tabs('update', {
                tab: tab,
                options: {
                    title: title,
                    content: content  // the new content URL
                }
            });
        });
    });
});