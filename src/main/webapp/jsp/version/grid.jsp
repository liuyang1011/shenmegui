<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<fieldset>
  <legend>条件过滤</legend>
  <div>
    <table border="0" cellspacing="0" cellpadding="0">
      <tr>
        <th>基线版本号</th>
        <td><label for="textfield"></label>
          <input class="easyui-textbox" id="code" name="code"type="text" ></td>
      </tr>
      <tr>
        <th>版本描述</th>
        <td><input class="easyui-textbox" id="blDesc" name="blDesc" type="text" name="desc"></td>
        <td><a href="javascript:;" onclick="getBaseLine()" class="easyui-linkbutton" iconcls="icon-search">查询</a>
          <%--&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" plain="true" iconcls="icon-save">版本下载</a>--%>
        </td>
      </tr>

        <td>&nbsp;</td>

    </table>
  </div>

</fieldset>
<table class="easyui-datagrid" title="版本历史" style="height:365px; width:auto;" id="baseLineHis">
  <thead>
  <tr>
    <th data-options="field:'id',checkbox:true"></th>
    <th data-options="field:'code', width:80">基线版本</th>
    <th data-options="field:'blDesc', width:120">版本描述</th>
    <th data-options="field:'optDate', width:100">发布时间</th>
    <th data-options="field:'optUser', width:100">发布用户</th>
  </tr>
  </thead>
</table>
<div class="win-bbar">
  <a href="#" class="easyui-linkbutton"  iconCls="icon-cancel" onClick="$('#w').window('close')">取消</a><a href="javascript:checked()" class="easyui-linkbutton"  iconCls="icon-ok">选择</a>
</div>
<script type="text/javascript">
  $(function(){
    $("#baseLineHis").datagrid({
      rownumbers:true,
      singleSelect:true,
      url:'/baseLine/getBaseLine',
      method:'get',
      pagination:true,
      pageSize:10
    })
  })
  function getBaseLine(){
    var code = $("#code").textbox("getValue");
    var blDesc = $("#blDesc").textbox("getValue");
    var urlPath = "/baseLine/getBaseLine";

    var queryParams = $('#baseLineHis').datagrid('options').queryParams;
    queryParams.code = encodeURI(code);
    queryParams.blDesc = encodeURI(blDesc);
    if(queryParams.keyValue){
      $("#baseLineHis").datagrid('options').queryParams = queryParams;//传递值
      $("#baseLineHis").datagrid('reload');
    }else{
      $("#baseLineHis").datagrid('reload');
    }
  }

  function checked() {
    var row = $("#baseLineHis").datagrid("getSelected");
    var baseId=row.baseId;
    if(row==null){
      alert("请选择一条数据");
      return;
    }
      var tab = parent.$('#mainContentTabs').tabs('getSelected');
      parent.$('#mainContentTabs').tabs('update', {
      tab: tab,
      options: {
        title: '版本公告',
        content:'<iframe scrolling="auto" frameborder="0"  src="/baseLine/getRecentBaseLine?baseId='+baseId+'" style="width:100%;height:98%;"></iframe>'
      }
    });

    $('#w').window('close');


  }
  </script>
</html>