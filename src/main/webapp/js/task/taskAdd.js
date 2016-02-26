/**
 * Created by lmd on 2016/1/29.
 */
var taskFormatter = {
    "formatStatus": function (data) {
        if (data) {
            if (data == "Reserved") {
                return "待分派";
            }
            if (data == "Ready") {
                return "已分派";
            }
            if (data == "InProgress") {
                return "处理中";
            }
            if (data == "Finished") {
                return "已完成";
            }
        }
    },
    "formatPriority": function (data) {
        if (data != undefined) {
            if ("0" == data) {
                return "低";
            }
        }
    },
    "formatStartDate":function(data){
        if(data == "0"){
            var myDate = new Date();
            var stringDate = myDate.getFullYear()+"/"+(myDate.getMonth()+1)+"/"+myDate.getDate();
            return stringDate.toString();
        }
    },
    "formatEndDate":function(data){
        if(data == "0"){
            var myDate = new Date();
            var stringDate = myDate.getFullYear()+"/"+(myDate.getMonth()+1)+"/"+(myDate.getDate()+3);
            return stringDate.toString();
        }
    }
};
$(function () {
    $('#responsibility').combobox({
        url:'/user/getAllUser',
        method:'get',
        mode:'remote',
        valueField:'id',
        textField:'name'
    });
    $("#createTaskBtn").click(function () {
        var data={};
        /*使用数据库触发器来控制主键为0的时候自增1*/
        data.taskNum = 0;
        data.workFlow = $("#workFlow2").val();
        data.taskPoint = $("#taskType2").val();
        data.responsibility =$('#responsibility').combobox('getValue');
        data.taskDescribe = $("#taskDescribe2").val();
        data.urgencyDegree = taskFormatter.formatPriority("0");
        data.status = taskFormatter.formatStatus("Reserved");
        data.startDate = taskFormatter.formatStartDate("0");
        data.endDate = taskFormatter.formatEndDate("0");
        if(data.workFlow == "" || data.taskPoint==""){
            alert("请输入必输项！");
        }else {
            taskManage.createTask(data, function () {
                $("#w").window("close");
                $('#taskTab').datagrid('reload');
            });
        }
    });
});