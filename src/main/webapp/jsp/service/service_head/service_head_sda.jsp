<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
  <base href="<%=basePath%>">

  <title>服务报文头内容信息</title>

  <link rel="stylesheet" type="text/css" href="/resources/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/resources/themes/icon.css">
  <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.easyui.min.js"></script>
  <script type="text/javascript">
    var editingId;
    var newIds = [];
    var delIds = [];
    var metadataJson;
    function onContextMenu(e,row){
      e.preventDefault();
      $(this).treegrid('select', row.id);
      $('#mm').menu('show',{
        left: e.pageX,
        top: e.pageY
      });
    }
    function removeIt(){
      var node = $('#tg').treegrid('getSelected');
      if(node.text == "root" || node.text == "response" || node.text == "request"){
        alert("请选择其他节点！");
        return false;
      }
      if (node){

        delIds.push(node.id);
        $('#tg').treegrid('remove', node.id);
      }
    }
    function editIt(){
      var row = $('#tg').treegrid('getSelected');
      if(row.text == "root" || row.text == "response" || row.text == "request"){
        alert("请选择其他节点！");
        return false;
      }

      if (row){
        editingId = row.id
        var contains = false;//判断之前是否已经将此节点加入newIds
        for(var i=0; i < newIds.length; i++){
          if(editingId == newIds[i]){
            contains = true;
            break;
          }
        }
        if(!contains){
          newIds.push(editingId);
        }
        $('#tg').treegrid('beginEdit', editingId);
        var ed = $('#tg').treegrid('getEditor',
                {id:editingId,field:'append4'});

        $(ed.target).combobox({"onSelect":function(record) {
          comboboxSelect(record);
        }});
        $(ed.target).combobox('setValue', row.append4);
//					$("#cancelbtn"+editingId).show();
//					$("#okbtn"+editingId).show();
      }
    }

    function append(){
      var uuid = "" + new Date().getTime();
      var node = $('#tg').treegrid('getSelected');
      if(node.text == "root" && node.parentId == null){
        alert("请选择其他节点！");
        return false;
      }
      $('#tg').treegrid('append',{
        parent: node.id,
        data: [{
          id: uuid,
          parentId:node.id,
          append3:node.append3  //初始路径为父路径
        }]
      });
      editingId = uuid;
      newIds.push(uuid);
      $('#tg').treegrid('reloadFooter');
      $('#tg').treegrid('select',uuid);
      $('#tg').treegrid('beginEdit', uuid);
      var ed = $('#tg').treegrid('getEditor',
              {id:uuid,field:'append4'});
      $(ed.target).combobox({"onSelect":function(record) {
        $('#tg').treegrid('select',uuid);
        comboboxSelect(record);
      }});
    }
    function saveSDA(){
      if (!confirm("确定保存吗？")) {
        return;
      }
      if (!$("#sdaForm").form('validate')) {
        return false;
      }
      var t = $('#tg');
      if (editingId != undefined){
        var editNodes = [];
        for(var i=0; i<newIds.length; i++){
          var editNode = t.treegrid('find', newIds[i]);
          if(editNode != null){
            t.treegrid('endEdit', editNode.id);
            var node = {};
            node.sdaId = editNode.id;
            node.structName = editNode.text;
            node.parentId = editNode.parentId;

            node.serviceId = "${service.serviceId }";
            node.operationId = "${operation.operationId }";

            node.structAlias = editNode.append1;
            node.type = editNode.append2;
            node.length = editNode.append3;
            node.metadataId = editNode.append4;
            node.required = editNode.append5;
            node.remark = editNode.append6;
            node.constraint = editNode.append7;
            node.seq = editNode.attributes;
            node.xpath = editNode.append3;

            editNodes.push(node);
          }
        }

        editingId = undefined;
        var result = false;
        $.ajax({
          type: "post",
          async: false,
          contentType:"application/json; charset=utf-8",
          url: "/sda/saveSDA",
          dataType: "json",
          data: JSON.stringify(editNodes),
          success: function(data){
            if(data){
              newIds = [];
              result = true;
              //t.treegrid('reload');
            }else{
              result = false;
              alert("只有服务定义状态和修订状态能进行修改");
              t.treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});
              return false;
            }
          }
        });
      }
      if(delIds.length > 0){
        $.ajax({
          type: "post",
          async: false,
          contentType:"application/json; charset=utf-8",
          url: "/sda/deleteSDA",
          dataType: "json",
          data: JSON.stringify(delIds),
          success: function(data){
            if(data){
              delIds = [];
              result = true
            }
          }
        });
      }
      if(result){
        alert("保存成功");
        t.treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});
      }
    }

    //节点上移
    function moveUp(){
      var node = $('#tg').treegrid('getSelected');

      if(node != null){
        //判断是否是第一个节点
        var parentNode = $('#tg').treegrid('getParent', node.id);
        var brothers =  $('#tg').treegrid('getChildren', parentNode.id);
        if(node.id == brothers[0].id){
          alert("已经在顶部！");
          return false;
        }
        $.ajax({
          type:"get",
          url: "/sda/moveUp?_t=" + new Date().getTime(),
          dataType: "json",
          data: {"sdaId": node.id},
          success: function(data){
            if(data){
              $('#tg').treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});
            }
          }
        });
      }

    }

    function moveDown(){
      var node = $('#tg').treegrid('getSelected');
      if(node != null){
        //判断是否是第一个节点
        var parentNode = $('#tg').treegrid('getParent', node.id);
        var brothers =  $('#tg').treegrid('getChildren', parentNode.id);
        if(node.id == brothers[brothers.length -1 ].id){
          alert("已经在底部！");
          return false;
        }
        $.ajax({
          type:"get",
          url: "/sda/moveDown?_t="+new Date().getTime(),
          dataType: "json",
          data: {"sdaId": node.id},
          success: function(data){
            if(data){
              $('#tg').treegrid({url:'/sda/sdaTree?serviceId=${service.serviceId }&operationId=${operation.operationId }&t='+ new Date().getTime()});

            }
          }
        });
      }

    }

    $.extend($.fn.validatebox.defaults.rules, {
      unique: {
        validator: function (value, param) {
          if(value != 'root' && value != 'request' && value != 'response'){
            return true;
          }
          return false;
        },
        message: '新建节点名称不能为“root、request、response”'
      }
    });
    //选择元数据自动更新其他数据
    function comboboxSelect(record){
      var node = $('#tg').treegrid('getSelected');
      $('#tg').treegrid('endEdit', node.id);
      var node2 = $('#tg').treegrid('getSelected');
      node2.text =  record.metadataId;
      node2.append1 = record.chineseName;
      node2.append2 = record.formula;
      node2.append3 = node.append3+"/"+record.metadataId;
      node2.append4 = record.metadataId;
      $('#tg').treegrid('refreshRow',node2.id);
    }
    //弹出元数据选择界面
    function appendByMetadata(){
      var node = $('#tg').treegrid('getSelected');
      if(node.text == "root" && node.parentId == null){
        alert("请选择其他节点！");
        return false;
      }
      var urlPath ="/jsp/metadata/metadata_choose.jsp"
      $('#dlg').dialog({
        title: '元数据',
        width: 770,
        left:100,
        closed: false,
        href: urlPath,
        modal: true
      });
    }
    function getMetadataJson(){
      if(!metadataJson){
        $.ajax({
          type: "get",
          async: false,
          contentType: "application/json; charset=utf-8",
          url: "/metadata/getAll",
          dataType: "json",
          success: function(data){
            metadataJson = data;
          }
        });
      }
      return metadataJson;
    }
  </script>
</head>
<body >
<div id="mm" class="easyui-menu" style="width:120px;">
  <shiro:hasPermission name="sda-add">
    <div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
    <div onclick="appendByMetadata()" data-options="iconCls:'icon-add'">根据元数据新增</div>
  </shiro:hasPermission>
  <shiro:hasPermission name="sda-update">
    <div onclick="editIt()" data-options="iconCls:'icon-edit'">编辑</div>
  </shiro:hasPermission>
  <shiro:hasPermission name="sda-delete">
    <div onclick="removeIt()" data-options="iconCls:'icon-remove'">删除</div>
  </shiro:hasPermission>
</div>
<fieldset>
  <legend>条件搜索</legend>
  <table border="0" cellspacing="0" cellpadding="0">
    <tr style="width:100%;">
      <th><nobr>服务代码</nobr></th>
      <td><input class="easyui-textbox" disabled type="text" name="serviceId" value="${service.serviceId }" style="width:100px"></td>
      <th><nobr>服务名称</nobr></th>
      <td><input class="easyui-textbox" disabled type="text" name="serviceName" value="${service.serviceName }"  style="width:250px"></td>
      <th><nobr>场景号</nobr></th>
      <td> <input class="easyui-textbox"disabled  type="text" name="operationId" value="${operation.operationId }"   style="width:50px"></td>
      <th><nobr>场景名称</nobr></th>
      <td><input class="easyui-textbox" disabled type="text" name="operationName" value="${operation.operationName }"  style="width:250px"></td>
      <th><nobr>版本</nobr></th>
      <td><input class="easyui-textbox" disabled type="text" name="operationName" value="${operation.version.code }"  style="width:50px"></td>
    </tr>

  </table>


</fieldset>
<form id="sdaForm">
  <table title="sda" class="easyui-treegrid" id="tg" style=" width:auto;"
         data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: '/serviceHeadSda/sdaTree?serviceHeadId=${param.headId}&t='+ new Date().getTime(),
				method: 'get',
				idField: 'id',
				treeField: 'text',
                toolbar:'#tb',
                onContextMenu:onContextMenu"
          >
    <thead>
    <tr>
      <th data-options="field:'text',width:140" editor="{type:'textbox',options:{editable:false, validType:['englishB']}}">字段名</th>
      <th data-options="field:'append1',width:60,align:'left'" editor="{type:'textbox', options:{editable:false}}">字段别名</th>
      <th data-options="field:'append2',width:50" editor="{type:'textbox', options:{editable:false}}">类型/长度</th>
      <th data-options="field:'append3',width:60,editor:'text', hidden:true">xpath</th>
      <th field="append4" width="80" editor="{type:'combobox', options:{required:true, method:'get', data: getMetadataJson(), valueField:'metadataId',textField:'metadataId'}}">元数据</th>
      <th field ="append5" width="40" editor="{type:'combobox',options:{url:'/jsp/service/sda/combobox_data.json',valueField:'id',textField:'text'}}">是否必输</th>
      <!--
         <th data-options="field:'append6',width:80,formatter:formatConsole">备注</th>
         -->
      <th field ="append7" width="80" editor="{type:'combobox',options:{url:'/jsp/service/sda/combobox_data2.json',valueField:'id',textField:'text'}}">约束条件</th>
      <th data-options="field:'append6',width:80,editor:'text'">备注</th>
    </tr>
    </thead>
  </table>
  <div id="tb" style="padding:5px;height:auto">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td>
          <shiro:hasPermission name="sda-update">
            <a href="javascript:void(0)" onclick="moveUp()" class="easyui-linkbutton" iconCls="icon-up" plain="true">上移</a>&nbsp;&nbsp;
            <a href="javascript:void(0)" onclick="moveDown()" class="easyui-linkbutton" iconCls="icon-down" plain="true">下移</a>&nbsp;&nbsp;
            <!--
            <a href="javascript:void(0)" onclick="addNode()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>&nbsp;&nbsp;
            -->
            <a href="javascript:void(0)" onclick="saveSDA()" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
          </shiro:hasPermission>
          <shiro:hasPermission name="sda-get">
            <a href="javascript:void(0)" onclick="comparePage()" class="easyui-linkbutton" iconCls="icon-save" plain="true">版本对比</a>
          </shiro:hasPermission>
        </td>
        <td align="right"></td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript" src="/plugin/validate.js"></script>
<div id="dlg" class="easyui-dialog"
     style="width:400px;height:280px;padding:10px 20px" closed="true"
     resizable="true"></div>

</body>
</html>
