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
    <div style="width:100%">
        <table id="choosedList" class="easyui-datagrid"
               data-options="
                rownumbers:true,
                singleSelect:true,
                fitColumns:false,
                toolbar:[{
                    iconCls: 'icon-excel-export',
                    text:'导出配置',
                     handler: exportBath
                }],
                method:'get',
                onBeforeSelect:function(index,row){
                    var row2 = $(this).datagrid('getSelected');
                    if(row2){
                        var index2 = $(this).datagrid('getRowIndex', row2);
                        $(this).datagrid('endEdit', index2);
                    }
                },
                onDblClickCell: function(index,field){
                     $(this).datagrid('beginEdit', index);
                    var ed = $(this).datagrid('getEditor', {index:index,field:'isStandardPro'});
                    $(ed.target).focus();
                    //$(this).datagrid('selectRow', index);

                }
                    "
               style="width:100%;">
            <thead>
            <tr>
                <th data-options="field:'serviceId',width:100" rowspan="2">服务代码</th>
                <th data-options="field:'operationId',width:80" rowspan="2">场景代码</th>
                <th data-options="field:'operationName',width:100" rowspan="2">场景名称</th>
                <th colspan="5">提供者</th>
                <th colspan="5">消费者</th>
            </tr>
            <tr>
                <th data-options="field:'providerId',width:100, hidden:true">提供者Id</th>
                <th data-options="field:'providerName',width:100">提供者</th>
                <th data-options="field:'isStandardPro',width:50,
                formatter:function (value, row, index) {
                    var text = row.isStandardPro;
                    if(text == '0'){
                          return '是';
                    }
                    if(text == '1'){
                          return '否';
                    }
                },
                editor:{type:'combobox',
                    options:{
                        required:true,
                        valueField:'id',
                        textField:'text',
                        data:[{
                                id: '1',
                                text: '否'
                            },{
                                id: '0',
                                text: '是'
                            }],
                         onSelect:function(record){
                            var row = $('#choosedList').datagrid('getSelected');
                            var index =  $('#choosedList').datagrid('getRowIndex', row);
                            var ed = $('#choosedList').datagrid('getEditor', {index:index,field:'interfaceOrProtocolPro'});
                            $(ed.target).combobox('setValue',null);
                            if(record.id == '1'){
                                var url = '/export/getInterface/'+row.serviceId +'/' + row.operationId +'/0';
                                $(ed.target).combobox({
                                    valueField: 'text',
                                    textField: 'text',
                                    url: url,
                                    onSelect:function(record){
                                        row.interfaceIdOrProtocolIdPro = record.id;
                                    }
                                })
                            }
                            if(record.id == '0'){
                                var data = [{
                                            id: 'xml',
                                            text: 'xml'
                                        },{
                                            id: 'soap',
                                            text: 'soap'
                                        }];
                                $(ed.target).combobox('loadData',data);
                            }
                         }
                        }
                    }
                ">标准</th>
                <th data-options="field:'interfaceIdOrProtocolIdPro',width:100, hidden:true">接口/协议ID</th>
                <th data-options="field:'interfaceOrProtocolPro',width:100,
                 editor:{
                    type:'combobox',
                    options:{
                        required:true,
                         valueField: 'id',
                          textField: 'text',
                         data:[{
                              id: 'xml',
                              text: 'xml'
                            },{
                               id: 'soap',
                               text: 'soap'
                          }]
                    }
                 }
                ">接口/协议</th >
                <th data-options="field:'customerId',width:100, hidden:true">消费者Id</th>
                <th data-options="field:'customerName',width:100">消费者</th>
                <th data-options="field:'isStandardCon',width:50,
                formatter:function (value, row, index) {
                    var text = row.isStandardPro;
                    if(text == '0'){
                          return '是';
                    }
                    if(text == '1'){
                          return '否';
                    }
                },
                editor:{type:'combobox',
                    options:{
                        required:true,
                        valueField:'id',
                        textField:'text',
                        data:[{
                                id: '1',
                                text: '否'
                            },{
                                id: '0',
                                text: '是'
                            }],
                         onSelect:function(record){
                            var row = $('#choosedList').datagrid('getSelected');
                            var index =  $('#choosedList').datagrid('getRowIndex', row);
                            var ed = $('#choosedList').datagrid('getEditor', {index:index,field:'interfaceOrProtocolCon'});
                            $(ed.target).combobox('setValue',null);
                            if(record.id == '1'){
                                var url = '/export/getInterface/'+row.serviceId +'/' + row.operationId +'/1';
                                $(ed.target).combobox({
                                    valueField: 'text',
                                    textField: 'text',
                                    url: url,
                                    onSelect:function(record){
                                        row.interfaceIdOrProtocolIdPro = record.id;
                                    }
                                })
                            }
                            if(record.id == '0'){
                                var data = [{
                                            id: 'xml',
                                            text: 'xml'
                                        },{
                                            id: 'soap',
                                            text: 'soap'
                                        }];
                                $(ed.target).combobox('loadData',data);
                            }
                         }
                        }
                    }
                ">标准</th>
                <th data-options="field:'interfaceIdOrProtocolIdCon',width:100, hidden:true">接口/协议ID</th>
                <th data-options="field:'interfaceOrProtocolCon',width:100,
                 editor:{
                    type:'combobox',
                    options:{
                         required:true,
                         valueField: 'id',
                          textField: 'text',
                         data:[{
                              id: 'xml',
                              text: 'xml'
                            },{
                               id: 'soap',
                               text: 'soap'
                          }]
                    }
                 }
                ">接口/协议</th>
            </tr>
            </thead>
        </table>
    </div>
</form>
<script type="text/javascript">

    function exportBath(){
        if(!$("#exportForm").form('validate')){
            alert("请完善数据！");
            return false;
        }
        var row2 = $("#choosedList").datagrid('getSelected');
        if(row2){
            var index2 = $("#choosedList").datagrid('getRowIndex', row2);
            $("#choosedList").datagrid('endEdit', index2);
        }
        var data = $("#choosedList").datagrid("getData");
        var form=$("<form>");//定义一个form表单
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");
        form.attr("action","/export/exportBatch");
        var fields = ["serviceId", "operationId","providerId", "customerId", "isStandardPro",  "isStandardCon", "interfaceIdOrProtocolIdPro", "interfaceIdOrProtocolIdCon", "interfaceOrProtocolPro", "interfaceOrProtocolCon"];
        for(var i=0; i < data.rows.length; i++){
            for(var j=0; j < fields.length; j++){
                var input1=$("<input>");
                input1.attr("type","hidden");
                input1.attr("name","list["+i+"]."+fields[j]);
                input1.attr("value",data.rows[i][fields[j]]);
                form.append(input1);
            }
        }

        $("body").append(form);//将表单放置在web中
        form.submit();//表单提交
    }


</script>
</body>
</html>