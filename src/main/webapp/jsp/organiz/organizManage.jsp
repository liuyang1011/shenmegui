<%@ page contentType="text/html; charset=utf-8" language="java"
         errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>机构管理</title>
  <link rel="stylesheet" type="text/css"
        href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css"
        href="/resources/themes/icon.css">
  <link href="/resources/css/css.css" rel="stylesheet" type="text/css">
</head>

<body>
<form id="searchForm">
  <fieldset>
    <table border="0" cellspacing="0" cellpadding="0">
        <th><nobr>机构代码</nobr></th>
        <td><input class="easyui-textbox" type="text" name="Id" id="orgId">
        </td>
        <th><nobr>机构名称</nobr></th>
        <td><input class="easyui-textbox" type="text" name="Name" id="orgName">
        </td>
        <td align="right"><a href="#" class="easyui-linkbutton" iconCls="icon-search" id="search">查询</a>
        </td>
    </table>
  </fieldset>
</form>

<table id="orgTab" style="height:500px; width:auto;" title="所有机构">
  <thead>
  <tr>
    <th data-options="field:'org',checkbox:true"></th>
    <th field="orgId" width="130px" type="text" align="center">机构代码</th>
    <th field="orgName" width="130px" align="center">机构名称</th>
    <th field="orgAB" width="130px" align="center">机构分类上级</th>
    <th field="orgStatus" width="130px" align="center">机构状态</th>
    <th field="orgResponsibility" width="130px" align="center">机构责任人</th>
    <th field="orgRemark" width="130px" align="center">机构备注</th>
    <th field="orgUpDate" width="130px" align="center">更新时间</th>
  </tr>
  </thead>
</table>

<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width:500px;height:200px;padding:10px;">
</div>

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/resources/js/ui.js"></script>
<script type="text/javascript" src="/plugin/json/json2.js"></script>
<script type="text/javascript" src="/js/org/organizationManager.js"></script>

<script type="text/javascript">
  var globalData = {};
  var toolbar = [{
    text: '新增',
    iconCls: 'icon-add',
    handler: function () {
      uiinit.win({
        w: 370,
        iconCls: 'icon-add',
        title: "新增机构",
        url: "organizationAdd.jsp"
      })
    }
  },{
    text : '修改',
    iconCls : 'icon-edit',
    handler : function() {
      var row = $('#orgTab').edatagrid('getSelected');
      var checkedItems = $('#orgTab').edatagrid('getChecked');
      if (checkedItems != null && checkedItems.length > 0) {
        globalData.orgId = row.orgId;
        uiinit.win({
          w: 370,
          iconCls: 'icon-edit',
          title: "修改机构",
          url: "/org/getById/" + row.orgId
        })
      }else {
        alert("请选中要修改的数据！");
      }
    }
  },
    {
      text: '删除',
      iconCls: 'icon-remove',
      handler: function () {
        var hasOrg;
        var userData;
        $.ajax({
          type:"GET",
          url:"/user/getAllUser",
          async:false,
          success:function(msg){
            userData = msg;
          }
        });
        var row = $('#orgTab').edatagrid('getSelected');
        var rowIndex = $('#orgTab').edatagrid('getRowIndex', row);
        var checkedItems = $('#orgTab').edatagrid('getChecked');
        for(var i = 0,len = userData.length; i < len; i++){
          if(userData[i].orgId == row.orgName){
            hasOrg = true;
            break;
          }else{
            hasOrg = false;
          }
        }
        if (checkedItems != null && checkedItems.length > 0) {
          if (hasOrg) {
            alert("无法删除还有用户的机构！");
          } else {
          organizationManager.deleteById(row.orgId, function (result) {
            if (result) {
              $('#orgTab').edatagrid('deleteRow', rowIndex);
              alert("删除成功");
            } else {
              alert("删除失败");
            }
          });
        }
        } else {
          alert("请选中要删除的数据！");
        }

      }
    }

  ];

  $(function () {
    $('#orgTab').datagrid({
      rownumbers: true,
      singleSelect: true,
      url: '/org/getAll',
      method: 'get',
      toolbar: toolbar,
      pagination: true,
      pageSize: 10,
      onLoadError: function (responce) {
        var resText = responce.responseText;
        if(resText.toString().indexOf("没有操作权限") > 0){
//                    alert("没有权限！");
          window.location.href = "/jsp/403.jsp";
        }
      }
    });
    $('#search').click(function () {
      var id=$('#orgId').val();
      var name= $('#orgName').val();
      if(id=="" && name=="")
      {window.location.reload();}
      else{
        var param = {};
        param.orgId = $('#orgId').val();
        param.orgName = $('#orgName').val();
        organizationManager.query(param, function(result){
          $('#orgTab').edatagrid('loadData', result);
        });
      }
    });
  });
</script>

</body>
</html>