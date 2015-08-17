<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">

        var taskFormatter = {
            "formatStatus": function (data, row) {
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
                }
            },
            "formatPriority": function (data, row) {
                if (data != undefined) {
                    if ("0" == data) {
                        return "低";
                    }
                }
            },
            "formatCreateBy": function (data, row) {
                if (row) {
                    if (row.createdBy)
                        return row.createdBy.id;
                }
            },
            "formatActualOwner": function (data, row) {
                if (data) {
                    return data.id;
                }
            }
        }
    </script>
</head>

<body>
<div id="userId" style="display: none"><shiro:principal/></div>
<div class="easyui-tabs" style="width:100%;height:auto">
    <div title="未完成任务" style="padding:0px">
        <table class="easyui-datagrid" id="taskTable"
               data-options="rownumbers:true,border:false,toolbar:toolbar,singleSelect:false,url:'/process/admin/list',method:'get',pagination:true,
				pageSize:10" style="height:370px; width:auto;">
            <thead>
            <tr>
                <th data-options="field:'productid',checkbox:true"></th>
                <th data-options="field:'id'">任务ID</th>
                <th data-options="field:'name'">任务名称</th>
                <th data-options="field:'subject'">主题</th>
                <th data-options="field:'description'">描述</th>
                <th data-options="field:'status'" formatter="taskFormatter.formatStatus">状态</th>
                <th data-options="field:'priority'" formatter="taskFormatter.formatPriority">优先级</th>
                <th data-options="field:'createBy'" formatter="taskFormatter.formatCreateBy">创建人</th>
                <th data-options="field:'actualOwner'" formatter="taskFormatter.formatActualOwner">受理人</th>
            </tr>
            </thead>
        </table>
    </div>
    <%--<div title="已完成任务" style="padding:10px">
        <fieldset>
            <legend>条件搜索</legend>
            &lt;%&ndash;<table border="0" cellspacing="0" cellpadding="0">&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<th>工作流程</th>&ndash;%&gt;
            &lt;%&ndash;<td>&ndash;%&gt;
            &lt;%&ndash;<select class="easyui-combobox" panelHeight="auto" style="width:155px">&ndash;%&gt;
            &lt;%&ndash;<option value="java">流程1</option>&ndash;%&gt;
            &lt;%&ndash;<option value="c">流程2</option>&ndash;%&gt;
            &lt;%&ndash;<option value="basic">流程3</option>&ndash;%&gt;
            &lt;%&ndash;<option value="perl">流程4</option>&ndash;%&gt;
            &lt;%&ndash;</select></td>&ndash;%&gt;

            &lt;%&ndash;<th>任务节点</th>&ndash;%&gt;
            &lt;%&ndash;<td>&ndash;%&gt;
            &lt;%&ndash;<select class="easyui-combobox" panelHeight="auto" style="width:155px">&ndash;%&gt;
            &lt;%&ndash;<option value="java">节点1</option>&ndash;%&gt;
            &lt;%&ndash;<option value="c">节点2</option>&ndash;%&gt;
            &lt;%&ndash;<option value="basic">节点3</option>&ndash;%&gt;
            &lt;%&ndash;<option value="perl">节点4</option>&ndash;%&gt;
            &lt;%&ndash;</select></td>&ndash;%&gt;
            &lt;%&ndash;<th>紧急程度</th>&ndash;%&gt;
            &lt;%&ndash;<td><input class="easyui-textbox" type="text" name="name"></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<th>计划起始日期</th>&ndash;%&gt;
            &lt;%&ndash;<td><input class="easyui-datebox" type="text" name="name"></td>&ndash;%&gt;
            &lt;%&ndash;<th>计划结束日期</th>&ndash;%&gt;
            &lt;%&ndash;<td><input class="easyui-datebox" type="text" name="name"></td>&ndash;%&gt;
            &lt;%&ndash;<th>责任人</th>&ndash;%&gt;
            &lt;%&ndash;<td><input class="easyui-textbox" type="text" name="name"></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td>&nbsp;</td>&ndash;%&gt;
            &lt;%&ndash;<td>&nbsp;</td>&ndash;%&gt;
            &lt;%&ndash;<td>&nbsp;</td>&ndash;%&gt;
            &lt;%&ndash;<td>&nbsp;</td>&ndash;%&gt;
            &lt;%&ndash;<td>&nbsp;</td>&ndash;%&gt;
            &lt;%&ndash;<td align="right"><a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索任务</a></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;</table>&ndash;%&gt;


        </fieldset>
        &lt;%&ndash;<table class="easyui-datagrid" title="已完成任务"&ndash;%&gt;
        &lt;%&ndash;data-options="rownumbers:true,singleSelect:false,url:'../datagrid_data1.json',method:'get',pagination:true,&ndash;%&gt;
        &lt;%&ndash;pageSize:10" style="height:370px; width:auto;">&ndash;%&gt;
        &lt;%&ndash;<thead>&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'productid',checkbox:true"></th>&ndash;%&gt;


        &lt;%&ndash;<th data-options="field:'itemid'">字段1</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'status'">字段2</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'listprice',align:'right'">字段3</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'unitcost',width:80,align:'right'">字段4</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'attr1'">消费方</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'status',width:60,align:'center'">字段5</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'attr1'"> 字段6</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'attr1'"> 字段7</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'attr1'">字段8</th>&ndash;%&gt;
        &lt;%&ndash;<th data-options="field:'attr1'">字段9</th>&ndash;%&gt;
        &lt;%&ndash;</tr>&ndash;%&gt;
        &lt;%&ndash;</thead>&ndash;%&gt;
        &lt;%&ndash;</table>&ndash;%&gt;
    </div>--%>
    <script type="text/javascript">
        var Global = {};
        var toolbar = [
            {
                text: '创建',
                iconCls: 'icon-edit',
                handler: function () {
                    uiinit.win({
                        w: 500,
                        iconCls: 'icon-add',
                        title: "创建任务",
                        url: "/jsp/task/addTask.jsp"
                    });
                }
            },
            {
                text: '分配',
                iconCls: 'icon-edit',
                handler: function () {
                    var checkedItems = $('#taskTable').datagrid('getChecked');
                    var checkedItem;
                    if (checkedItems != null && checkedItems.length > 0) {
                        if (checkedItems.length > 1) {
                            alert("请选择一个任务进行分配！");
                            return false;
                        }
                        else {
                            checkedItem = checkedItems[0];
                            Global.taskId = checkedItem.id;
                            uiinit.win({
                                w: 500,
                                iconCls: 'icon-edit',
                                title: "任务分配",
                                url: "/jsp/task/assignTask.jsp"
                            });
                        }
                    }
                    else {
                        alert("请选中要修改的数据！");
                    }
                }
            },
            {
                text: '处理',
                iconCls: 'icon-edit',
                handler: function () {
                    var checkedItems = $('#taskTable').datagrid('getChecked');
                    var checkedItem;
                    if (checkedItems != null && checkedItems.length > 0) {
                        if (checkedItems.length > 1) {
                            alert("请选择一个任务进行分配！");
                            return false;
                        }
                        else {
                            checkedItem = checkedItems[0];
                            Global.taskId = checkedItem.id;
                            Global.processInstanceId = checkedItem.processInstanceId;
                            Global.taskName = checkedItem.name;
                            var task = {};
                            task.processInstanceId = Global.processInstanceId;
                            task.taskId = Global.taskId;
                            task.userId = $("#userId").text();
                            ;
                            task.name = Global.taskName;
                            task.name = task.name.replace(/(^\s*)|(\s$)/g, '');
                            parent.PROCESS_INFO.processId = checkedItem.id;
                            parent.PROCESS_INFO.taskName = task.name;
                            parent.PROCESS_INFO.taskId = task.taskId;
                            parent.changeTaskName();
                            taskManager.processTask(task, function (result) {
                                if (task.name == '创建元数据') {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/process/metadataByTask/process/' + task.processInstanceId + '/task/' + task.taskId + '" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("创建元数据", content);
                                }
                                if (task.name == "元数据审核") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/process/metadataAuditByTask/process/' + task.processInstanceId + '/task/' + task.taskId + '" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("创建元数据", content);
                                }
                                if (task.name == "服务定义") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(4);
                                    alert("请在左侧服务目录菜单中新增服务。");
                                }
                                if (task.name == "创建公共代码") {
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/SGEnum/task/common.jsp?processId=' + task.processInstanceId + '&taskId=' + task.taskId + '" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("创建公共代码", content);
                                }
                                if (task.name == "公共代码审核") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/process/sgenum/sgenumAuditByTask/process/' + task.processInstanceId + '/task/' + task.taskId + '" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("创建公共代码", content);
                                }
                                if (task.name == "接口需求上传") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(6);
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/sysadmin/file_list.jsp" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("接口需求文件上传", content);
                                    alert("请上传接口需求文档。");
                                }
                                if (task.name == "接口定义") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(6);
                                    alert("请在左侧系统菜单中右键新增接口。");
                                }
                                if (task.name == "服务审核") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(4);
                                    parent.SYSMENU.reloadTreeByValue('mxservicetree', "1");
                                }
                                if(task.name == "服务发布"){
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(6);
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/version/versionRelease.jsp" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("服务发布", content);
                                    alert("请选择服务并发布。");
                                }
                                if (task.name == "服务开发") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    parent.SYSMENU.changeLeftMenu(4);
                                    alert("请在右侧服务选择服务，进行开发。");
                                }
                                if (task.name == "服务上线") {
                                    $("#w").window("close");
                                    $('#taskTable').datagrid('reload');
                                    var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/version/baseLineMake.jsp" style="width:100%;height:100%;"></iframe>';
                                    parent.addTab("服务上线基线制作", content);
                                    alert("请在上线基线中发布上线内容。");
                                }
                            });
                        }
                    }
                    else {
                        alert("请选中要修改的数据！");
                    }
                }
            }/*,
             {
             text: '挂起',
             iconCls: 'icon-guaqi',
             handler: function () {
             alert('挂起')
             }
             },{
             text: '完成',
             iconCls: 'icon-guaqi',
             handler: function () {
             alert('挂起')
             }
             }*/
        ];
    </script>
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/assets/task/taskManager.js"></script>

</body>
</html>