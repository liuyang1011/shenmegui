/**
 * Created by lmd on 2016/2/18.
 */
$(function () {
    for(var i=1;i<6;i++){
        var nextUser = '#nextUser'+i;
        $(nextUser).combobox({
            url:'/user/getAllUser',
            method:'get',
            mode:'remote',
            valueField:'id',
            textField:'name'
        });
    };
    $("#completeTaskBtn").click(function () {
        var length = parent.PROCESS_INFO.length;
        var url = new Array();
        var params = new Array();
        for(var i=0;i<5;i++){params[i] = {};}

        url[0] = "/process/" + $("#userId").text() + "/complete/" + parent.PROCESS_INFO.taskId1;
        url[1] = "/process/" + $("#userId").text() + "/complete/" + parent.PROCESS_INFO.taskId2;
        url[2] = "/process/" + $("#userId").text() + "/complete/" + parent.PROCESS_INFO.taskId3;
        url[3] = "/process/" + $("#userId").text() + "/complete/" + parent.PROCESS_INFO.taskId4;
        url[4] = "/process/" + $("#userId").text() + "/complete/" + parent.PROCESS_INFO.taskId5;

        for(var i=0;i<length;i++){
            nextUser = "#nextUser"+(i+1);
            params[i].userId = $(nextUser).combobox('getValue');
            params[i].approved = parent.PROCESS_INFO.approved;
        }
            taskManager.completeAllTask(length,url,params,function(){
                alert("任务已经处理！");
                $("#w").window("close");
                $('#taskTable').datagrid('reload');
                var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/myWorkbench/mytask.jsp" style="width:100%;height:100%;"></iframe>';
                var title = "待办事宜";
                $('#mainContentTabs').tabs('select', '待办事宜');
                var tab = $('#mainContentTabs').tabs('getSelected');  // get selected panel
                $('#mainContentTabs').tabs('update', {
                    tab: tab,
                    options: {
                        title: title,
                        content: content  // the new content URL
                    }
                });
                parent.PROCESS_INFO.approved = true;
            });
    });
});