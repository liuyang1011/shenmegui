<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>批量导出配置</title>

    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

</head>


<body>
<form class="easyui-form" id="exportForm" >
    <div id="exportTB">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel-export" plain="true" onclick="exportBath()">导出配置文件</a>
        （默认导出标准XML配置，鼠标右键选择其他选项)&nbsp;&nbsp;&nbsp;
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-excel-export" plain="true" onclick="tranToEsb()">发布到ESB</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-config" plain="true" onclick="tranConfig()">发布选项</a>
    </div>
    <div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="chooseUnStandard()" data-options="iconCls:'icon-edit'">选择非标导出</div>
    </div>
    <div id="tranConfigDialog" class="easyui-dialog"
         style="width:400px;height:280px;padding:10px 20px" closed="true"
         resizable="true"></div>
    <%--部署选项开关标志， 默认false--%>
    <input type="hidden" id="optionFlag" value="false" />
    <%--数据字典同步标志， 默认不同步--%>
    <input type="hidden" id="dicSync" value="false"/>
    <%--esb服务器id字符串--%>
    <input type="hidden" id="serverStr" value=""/>
    <table id="choosedList" class="easyui-datagrid"
           data-options="
                rownumbers:true,
			      singleSelect:false,
			      fitColumns:true,
                toolbar:'#exportTB',
                method:'get',
                onLoadSuccess:function(data){//当表格成功加载时执行
                    $.each(data.rows, function(i, row){
                        var rowIndex = $('#choosedList').datagrid('getRowIndex', row);
                        $('#choosedList').datagrid('beginEdit', rowIndex);
                    });
                    $(this).datagrid('selectAll');
                },
                onRowContextMenu:onRowContextMenu,
                onDblClickCell:onDblClickCell
                "
           style="width:100%;">
        <thead>
        <tr>
            <th data-options="field:'', checkbox:true" rowspan="2"></th>
            <th data-options="field:'serviceId',width:100" rowspan="2">服务代码</th>
            <th data-options="field:'operationId',width:80" rowspan="2">场景代码</th>
            <th data-options="field:'operationName',width:100" rowspan="2">场景名称</th>
            <th data-options="hidden:true" colspan="4"></th>
            <th colspan="3">调用方</th>
            <th colspan="3">提供方</th>

        </tr>
        <tr>
            <th data-options="field:'providerServiceInvokeId',hidden:true">提供者关系Id</th>
            <th data-options="field:'consumerServiceInvokeId',hidden:true">消费者关系ID</th>
            <th data-options="field:'conGeneratorId',hidden:true">生成器ID</th>
            <th data-options="field:'proGeneratorId',hidden:true">生成器ID</th>

            <th data-options="field:'consumerName',width:100">调用方</th>
            <th data-options="field:'conInterfaceName',width:100">接口</th>
            <%--<th data-options="field:'conIsStandard',width:50,formatter:formatter.standard, editor:standardEditor">标准</th>--%>
            <th data-options="field:'conGeneratorName',width:100,editor:conGeneratorEditor">生成器</th>

            <th data-options="field:'providerName',width:100">提供方</th>
            <th data-options="field:'proInterfaceName',width:100">接口</th>
            <%--<th data-options="field:'proIsStandard',width:50,formatter:formatter.standard">标准</th>--%>
            <th data-options="field:'proGeneratorName',width:100,editor:proGeneratorEditor">生成器</th>


    </table>
</form>
<script type="text/javascript">
    var formatter={
        standard : function (value, row, index) {
            if(value == '0'){
                return '是';
            }
            if(value == '1'){
                return '否';
            }
        }
    }
    var conGeneratorEditor = {
        type:'combobox',
        options: {
            required:true,
            valueField: 'id',
            textField: 'name',
            method : 'GET',
            url: "/generator/getAll",
            onSelect:function(record){
//                var row = $("#choosedList").datagrid("getSelected");
//                var index = $("#choosedList").datagrid("getRowIndex", row);
//                $("#choosedList").datagrid("endEdit", index);
//                $("#choosedList").datagrid("updateRow",{
//                    index : index,
//                    row:{
//                        conGeneratorId:record.id
//                    }
//                })
            }
        }
    }
    var proGeneratorEditor = {
        type:'combobox',
        options: {
            required:true,
            valueField: 'id',
            textField: 'name',
            method : 'GET',
            url: "/generator/getAll",
            onSelect:function(record){
//                var row = $("#choosedList").datagrid("getSelected");
//                var index = $("#choosedList").datagrid("getRowIndex", row);
//                $("#choosedList").datagrid("endEdit", index);
//                $("#choosedList").datagrid("updateRow",{
//                    index : index,
//                    row:{
//                        proGeneratorId:record.id
//                    }
//                })
            }
        }
    }
    function onRowContextMenu(e, rowIndex, rowData) {//表格鼠标右击事件
        e.preventDefault(); //阻止浏览器捕获右键事件
        $(this).datagrid("clearSelections"); //取消所有选中项
        $(this).datagrid("selectRow", rowIndex); //根据索引选中该行
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    }
    function chooseUnStandard(){//选择非标导出方法
        var row = $("#choosedList").datagrid("getSelected");
        var index = $("#choosedList").datagrid("getRowIndex", row);
        $("#choosedList").datagrid("beginEdit", index);
    }
    function exportBath(){
        if(!$("#exportForm").form('validate')){
            alert("请完善数据！");
            return false;
        }
        var rows = $("#choosedList").datagrid('getChecked');
        if(null != rows && 0 < rows.length){
            var form=$("<form>");//定义一个form表单
            form.attr("style","display:none");
            form.attr("target","");
            form.attr("method","post");
            form.attr("action","/export/exportBatch");
            var fields = ["consumerServiceInvokeId", "providerServiceInvokeId", "conGeneratorId", "proGeneratorId"];
            for(var i=0; i < rows.length; i++){
                var index = $("#choosedList").datagrid('getRowIndex', rows[i]);
                var conEditor = $("#choosedList").treegrid('getEditor', {index:index,field:'conGeneratorName'});
                rows[i]["conGeneratorId"] = $(conEditor.target).combobox("getValue");
                var proEditor = $("#choosedList").treegrid('getEditor', {index:index,field:'proGeneratorName'});
                rows[i]["proGeneratorId"] = $(proEditor.target).combobox("getValue");
                for(var j=0; j < fields.length; j++){
                    var input1=$("<input>");
                    input1.attr("type","hidden");
                    input1.attr("name","list["+i+"]."+fields[j]);
                    input1.attr("value",rows[i][fields[j]]);
                    form.append(input1);
                }
            }

            $("body").append(form);//将表单放置在web中
            form.submit();//表单提交
        }else{
            alert("没有选中数据！");
        }

    }

    function tranToEsb(){
        if(!$("#exportForm").form('validate')){
            alert("请完善数据！");
            return false;
        }
        var rows = $("#choosedList").datagrid('getChecked');
        var configVOs = [];
        if(null != rows && 0 < rows.length){
            var configVO = {};
            for(var i = 0; i < rows.length; i++){
                configVO.consumerServiceInvokeId = rows[i].consumerServiceInvokeId;
                configVO.providerServiceInvokeId = rows[i].providerServiceInvokeId;
                configVO.conGeneratorId = rows[i].conGeneratorId;
                configVO.proGeneratorId = rows[i].proGeneratorId;
                configVOs.push(configVO)
            }
            var serverIdStr = $("#serverStr").attr("value");
            if(null == serverIdStr || "" == serverIdStr);
            serverIdStr="all";
            var urlPath = "/esbServer/configSync/" + $("#optionFlag").attr("value") + "/" + $("#dicSync").attr("value") + "/" + serverIdStr;
            $.messager.progress({
                title:'请稍后',
                msg:'正在发布...'
            });
            $.ajax({
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: urlPath,
                dataType: "json",
                data: JSON.stringify(configVOs),
                success: function (data) {
                    $.messager.progress("close");
                    if(data){
                        alert("操作成功");
                    }else{
                        alert("配置文件发布到ESB服务器失败");
                    }
                    $("#opDialog").dialog("close");
                }
            });
        }else{
            alert("没有选中数据!");
        }
    }
    //设置部署选项
    function tranConfig(){
        $('#tranConfigDialog').dialog({
            title: '发布配置',
            width: 600,
            height: 300,
            top:$(document).scrollTop() + ($(window).height()-250) * 0.5,
            closed: false,
            closable:false,
            cache: false,
            href: "/jsp/esb/server_config_option.jsp",
            modal: true
        });
    }

    function tranConfigClose(){
        $("#optionFlag").attr("value", "false");
        $("#tranConfigDialog").dialog("close");
    }
    function tranConfigSave(){
        $("#optionFlag").attr("value", "true");
        var rows = $("#esbServerList").datagrid("getChecked");
        if(null != rows && 0 < rows.length){
            var serviceIds = new Array();
            for(var i = 0; i < rows.length; i ++){
                serviceIds.push(rows[i].serverId);
            }
            var serviceStr = serviceIds.join(",");
            $("#serverStr").attr("value", serviceStr);
            $("#tranConfigDialog").dialog("close");
        }else{
            alert("请至少选择一个服务器！");
        }
    }
    //更新数据字典值
    function changeDicSyncValue(){
        var dicSyncValue = $("#dicSync").attr("value");
        if(dicSyncValue == "true"){
            $("#dicSync").attr("value", "false");
        }else{
            $("#dicSync").attr("value", "true");
        }
    }
</script>
</body>
</html>