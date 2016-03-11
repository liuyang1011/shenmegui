
<table id="params" style="width:auto;"
       class="easyui-datagrid"
       data-options="
       rownumbers:true,
       fitColumns:false,
       singleSelect:false,
       url:'/attFuncParam/getByFuncId?id=${param.funcId}',
       method:'get',
       pagination:true,
				pageSize:10">
    <thead>
    <tr>
        <th data-options="field:'id',checkbox:true"></th>
        <th field="name" width="130px" type="text" align="center">参数名称</th>
        <th field="defaultValue" width="130px" align="center">默认值</th>
        <th field="seq" width="130px" align="center">排序</th>
        <th field="des" width="130px" align="center">说明</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">
    $(function(){
        $("#params").datagrid({
            toolbar :[
                {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        var checkedItems = $("#params").datagrid("getChecked");
                        if(checkedItems != null && checkedItems.length > 0){
                            $.messager.confirm('确认', '确定删除吗？', function (r) {
                                        if (r) {
                                            $.ajax({
                                                type: "post",
                                                async: false,
                                                contentType: "application/json; charset=utf-8",
                                                url: "/attFuncParam/deletes",
                                                dataType: "json",
                                                data: JSON.stringify(checkedItems),
                                                success: function (data) {
                                                    if (data) {
                                                        alert("操作成功");
                                                        $("#params").datagrid("reload");
                                                    } else {
                                                        alert("操作失败！");
                                                    }
                                                }
                                            });
                                        }
                                    }
                            );
                        }else{
                            alert("没有选中数据!");
                        }
                    }
                }
            ]
        })
    });
</script>