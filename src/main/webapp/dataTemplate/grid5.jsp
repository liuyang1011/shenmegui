<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>列表页</title>
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>
<fieldset>
    <form id="searchForm">
    <legend>条件搜索</legend>
    <table border="0" cellspacing="0" cellpadding="0">
        <tr>
            <th>词汇英文名称</th>
            <td><input class="easyui-textbox" type="text" name="englishWord_" id="englishWord_">
            </td>
            <th>词汇中文名称</th>
            <td><input class="easyui-textbox" type="text" name="chineseWord" id="chineseWord">
            </td>
            <th>首写字母</th>
            <td><input class="easyui-textbox" type="text" name="firstWord_" id="firstWord_">
            </td>
            <td align="right"><a href="#" class="easyui-linkbutton"
                                 iconCls="icon-search" id="search">搜索单词</a>
                <a href="#" id="clean" onclick="$('#searchForm').form('clear');" class="easyui-linkbutton" iconCls="icon-clear" style="margin-left:1em" >清空</a>
            </td>
        </tr>
    </table>
        </form>
</fieldset>

<table id="tt" style="height:370px; width:auto;" title="词汇列表">
    <thead>
    <tr>
        <th data-options="field:'',checkbox:true"></th>
        <th field="id" width="100" hidden="true">ID</th>
        <th field="firstWord" width="100" align="center" >首写字母</th>
        <th field="englishWord" width="100" align="center" >词汇英文名称</th>
        <th field="wordAb" width="100" align="center" >词汇英文缩写</th>
        <th field="chineseWord" width="100" align="center" >词汇中文名称</th>
        <th field="optDate" width="200" align="center" >更新时间</th>
        <th field="optUser" width="100" align="center" >更新用户</th>
        <th field="createUser" width="100" align="center" >创建用户</th>
    </tr>
    </thead>
</table>

<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;"></div>
<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>
<script type="text/javascript"
        src="/assets/englishWord/js/englishWordManager.js"></script>
<script type="text/javascript">
    $(function () {
        var editedRows = [];
        var toolbar = [
            {
                text: '新增',
                iconCls: 'icon-add',
                handler: function () {
//                    $('#tt').edatagrid('addRow');
                    uiinit.win({
                        top:"20px",
                        left:"150px",
                        w: 500,
                        iconCls: 'icon-add',
                        title: "新增词汇",
                        url: "/jsp/englishWord/add.jsp"
                    });
                }
            },
            {
                text: '修改',
                iconCls: 'icon-edit',
                handler: function () {
                   var checkedItems = $('#tt').datagrid('getChecked');
                   var checkedItem;
                    if (checkedItems != null && checkedItems.length > 0) {
                        if (checkedItems.length > 1) {
                            alert("请只选中一行要修改的数据！");
                            return false;
                        }
                        else {
                            checkedItem = checkedItems[0];
                            uiinit.win({
                                w: 500,
                                top:"20px",
                                left:"150px",
                                iconCls: 'icon-edit',
                                title: "修改词汇",
                              //  url: "/englishWord/getById/" + checkedItem.id
                                url: "/englishWord/editPage?id=" + checkedItem.id

                            });
                        }
                    }
                    else {
                        alert("请选中要修改的数据！");
                    }
                }
            },
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    deleteObj();
                }
            }
        ]
        $('#tt').edatagrid({

            rownumbers: true,
            singleSelect: false,
            url: '/englishWord/getAll',
            method: 'get',
            toolbar: toolbar,
            pagination: true,
            pageSize: 10,
            onBeginEdit: function (index, row) {
                editedRows.push(index);
            }
        });

        $('#tt').edatagrid({
            autoSave: false
        });
        $('#search').click(function () {

            var queryParams = $('#tt').datagrid('options').queryParams;
            queryParams.englishWord = $("#englishWord_").textbox("getValue");
            queryParams.chineseWord = encodeURI($("#chineseWord").textbox("getValue"));
            queryParams.firstWord = $("#firstWord_").textbox("getValue");
            if (queryParams.englishWord || queryParams.chineseWord || queryParams.firstWord ) {
                $("#tt").datagrid('options').queryParams = queryParams;//传递值
                $("#tt").datagrid('reload');//重新加载table
            } else {
                $("#tt").datagrid('reload');
            }
        });
    });
    var deleteRow = function (data, callBack) {
        $.ajax({
            "type": "POST",
            "contentType": "application/json; charset=utf-8",
            "url": "/englishWord/deleteWord",
            "dataType": "json",
            "data": JSON.stringify(data),
            "success": function (result) {
                callBack(result);
            }
        });
    }
    function deleteObj(){
        var checkedItems = $('#tt').datagrid('getChecked');
        if(checkedItems != null && checkedItems.length > 0){
            if(confirm("确定要删除已选中的"+checkedItems.length+"项吗？一旦删除无法恢复！")){
                var idss = [];
                $.each(checkedItems, function(index, item) {
                    idss.push(item.id);
                });
                $.ajax({
                    type: "post",
                    async: false,
                    url: "/englishWord/delete",
                    dataType: "json",
                    data: {"ids":idss.join(",")},
                    success: function(result){
                        if(result){
                            alert("操作成功");
                            $('#tt').datagrid('reload');
                        }else{
                            alert("操作失败");
                        }

                    }
                });
            }
        }else{
            alert("没有选中项！");
        }
    }
</script>

</body>
</html>