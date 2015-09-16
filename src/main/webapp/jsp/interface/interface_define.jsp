<%@ page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv ="X-UA-Compatible" content ="IE=edge" >
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="/resources/themes/icon.css">
    <link href="/resources/css/ui.css" rel="stylesheet" type="text/css">
    <link href="/plugin/aehlke-tag-it/css/jquery.tagit.css" rel="stylesheet" type="text/css">
    <link href="/plugin/aehlke-tag-it/css/tagit.ui-zendesk.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script src="/newui/plugins/jQueryUI/jquery-ui.js" type="text/javascript" charset="utf-8"></script>
    <script src="/plugin/aehlke-tag-it/js/tag-it.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript"
            src="/resources/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/resources/js/ui.js"></script>
    <script type="text/javascript" src="/resources/js/treegrid-dnd.js"></script>
    <script type="text/javascript" src="/js/sysadmin/sysManager.js"></script>
    <script type="text/javascript" src="/js/sysadmin/interfaceManager.js"></script>
    <script type="text/javascript" src="/assets/tag/tagManager.js"></script>


    <script type="text/javascript">

        var editingId;
        var parentId;
        var isupdate;
        var addArray = new Array();
        var editArray = new Array();
        var parentIdAry = new Array();
        var versionCode="";
        var toolbar = [{
            text: '刪除',
            iconCls: 'icon-remove',
            handler: function () {
                if (!confirm("确定要删除选中的记录吗？")) {
                    return;
                }
                var nodes = $('#tg').treegrid('getSelections');
                if (nodes) {
                    var delAry = new Array();
                    for (var i = 0; i < nodes.length; i++) {
                        if (nodes[i].structName != 'request' && nodes[i].structName != 'response') {
                            delAry.push(nodes[i].id);
                        }
                    }
                    sysManager.removeIDA(delAry, function (result) {
                        if (result) {
                            //array.
                            //$('#tg').treegrid('reload');
                            for (var j = 0; j < delAry.length; j++) {
                                $('#tg').treegrid('remove', delAry[j]);
                            }
                            $("#interfacetg").datagrid("reload");
                        } else {
                            alert("删除失败");
                        }

                    });
                }

            }
        }, {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {

                var reqAry = [];
                for (var i = 0; i < addArray.length; i++) {

                    $('#tg').treegrid('endEdit', addArray[i]);
                    var row = $('#tg').treegrid('find', addArray[i]);
                    if (row) {
                        var structName = row.structName;
                        var structAlias = row.structAlias;
                        var type = row.type;
                        var length = row.length;
                        var metadataId = row.metadataId;
                        var scale = row.scale;
                        var required = row.required;
                        var remark = row.remark;
                        var seq = row.seq;
                        var data = {};
                        data.structName = structName;
                        data.structAlias = structAlias;
                        data.type = type;
                        data.length = length;
                        data.metadataId = metadataId;
                        data.scale = scale;
                        data._parentId = parentIdAry[i];
                        data.required = required;
                        data.remark = remark;
                        data.headId = "${param.headId}";
                        data.interfaceId = "${param.interfaceId}";
                        data.seq = seq;
                        reqAry.push(data);
                    }
                }

                for (var i = 0; i < editArray.length; i++) {

                    $('#tg').treegrid('endEdit', editArray[i]);
                    var row = $('#tg').treegrid('find', editArray[i]);
                    if (row) {
                        var structName = row.structName;
                        var structAlias = row.structAlias;
                        var type = row.type;
                        var length = row.length;
                        var metadataId = row.metadataId;
                        var scale = row.scale;
                        var required = row.required;
                        var remark = row.remark;
                        var seq = row.seq;
                        var data = {};
                        data.structName = structName;
                        data.structAlias = structAlias;
                        data.type = type;
                        data.length = length;
                        data.metadataId = metadataId;
                        data.scale = scale;
                        //data._parentId = parentId;
                        data.required = required;
                        data.remark = remark;
                        data.headId = "${param.headId}";
                        data.interfaceId = "${param.interfaceId}";
                        data.seq = seq;
                        data.id = row.id;
                        reqAry.push(data);
                    }
                }

                sysManager.addIDA(reqAry, function (result) {
                    if (result) {
                        alert("保存成功");
                        $('#tg').treegrid({url: '/ida/getInterfaces/${param.interfaceId}?_t=' + new Date().getTime()});
                        $("#interfacetg").datagrid({url:'/interface/getInterById/${param.interfaceId}?_t=' + new Date().getTime()});
                       // $('#tg').treegrid('reload');
                    } else {
                        alert("保存失败");
                    }

                });
            }
        }, {
            text: '上移',
            iconCls: 'icon-up',
            handler: function () {
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
                        url: "/ida/moveUp?_t=" + new Date().getTime(),
                        dataType: "json",
                        data: {"id": node.id},
                        success: function(data){
                            if(data){
                                $('#tg').treegrid({url:'/ida/getInterfaces/${param.interfaceId}?_t='+ new Date().getTime()});
                                $("#interfacetg").datagrid("reload");
                            }
                        }
                    });
                }
            }
        }, {
            text: '下移',
            iconCls: 'icon-down',
            handler: function () {
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
                        url: "/ida/moveDown?_t="+new Date().getTime(),
                        dataType: "json",
                        data: {"id": node.id},
                        success: function(data){
                            if(data){
                                $('#tg').treegrid({url:'/ida/getInterfaces/${param.interfaceId}?_t='+ new Date().getTime()});
                                $("#interfacetg").datagrid("reload");
                            }
                        }
                    });
                }
            }
        },
            {
                text: '发布',
                iconCls: 'icon-save',
                handler: function () {
                    var rows = $("#interfacetg").datagrid("getRows");
                    var interfaceName = rows[0].interfaceName;
                    var interfaceName = rows[0].interfaceName;
                    var urlPath = "/jsp/interface/interface_release.jsp?interfaceId=${param.interfaceId}&interfaceName="+encodeURI(encodeURI(interfaceName))+
                            "&versionCode="+versionCode;
                    $('#releaseDlg').dialog({
                        title: '版本发布',
                        width: 500,
                        left:150,
                        top:50,
                        closed: false,
                        cache: false,
                        href: urlPath,
                        modal: true
                    });

                }
            }       /*,{
            text: '提交任务',
            iconCls: 'icon-ok',
            handler: function () {
                uiinit.win({
                    w: 500,
                    iconCls: 'icon-cfp',
                    title: "完成任务",
                    url: "/jsp/task/completeTask.jsp"
                });
            }
        }*/
        ]

        function onContextMenu(e, row) {

            e.preventDefault();


            $(this).treegrid('unselectAll');

            $(this).treegrid('select', row.id);

            if (row.structName == 'root') {
                $('#tg').treegrid('unselect', row.id);
                return;
            }


            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        }

        function removeIt() {
            if (!confirm("确定要删除选中的记录吗？")) {
                return;
            }
            var nodes = $('#tg').treegrid('getSelections');
            if (nodes) {
                var delAry = new Array();
                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].structName != 'request' && nodes[i].structName != 'response') {
                        delAry.push(nodes[i].id);
                    }
                }
                sysManager.removeIDA(delAry, function (result) {
                    if (result) {
                        //array.
                        //$('#tg').treegrid('reload');
                        for (var j = 0; j < delAry.length; j++) {
                            $('#tg').treegrid('remove', delAry[j]);
                        }
                        $("#interfacetg").datagrid("reload");
                    } else {
                        alert("删除失败");
                    }

                });
            }
        }
        function editIt() {
            var row = $('#tg').treegrid('getSelected');

            if (row) {
                editingId = row.id
                $('#tg').treegrid('beginEdit', editingId);
                isupdate = true;
                $("#upbtn" + editingId).hide();
                $("#downbtn" + editingId).hide();
                if (jQuery.inArray(editingId, editArray) == -1) {
                    editArray.push(editingId);
                }
            }
        }
        var idIndex = 999;
        function append() {

            idIndex++;

            var node = $('#tg').treegrid('getSelected');
            var nodes = $('#tg').treegrid("getChildren", node.id);
            var seq = 0;
            if (nodes.length > 0) {
                seq = nodes[nodes.length - 1].seq + 1;
            }
            $('#tg').treegrid('append', {
                parent: node.id,
                data: [{id: idIndex, structName: "", seq: seq}]

            })
            editingId = idIndex;
            parentId = node.id;

            $('#tg').treegrid('reloadFooter');
            $('#tg').treegrid('beginEdit', idIndex);

            addArray.push(idIndex);
            parentIdAry.push(parentId);

        }

        function loadData() {
            var interfaceId = "${param.interfaceId}"
            $('#tg').treegrid({
                url: '/ida/getInterfaces/' + interfaceId + '?_t=' + new Date().getTime(),
                singleSelect: true,//是否单选
                onAfterEdit: function (row, changes) {
                    //alert(row.name);
                }
            });

            $('#interfacetg').datagrid({
                iconCls: 'icon-edit',//图标
                width: 'auto',
                height: '80px',
                collapsible: false,
                fitColumns: true,
                method: 'get',
                url: '/interface/getInterById/${param.interfaceId}',
                singleSelect: false,//是否单选
                rownumbers: false

            });
        }


        function onClickRow(row) {
            if (row.structName == 'root' || row.structName == 'request' || row.structName == 'response') {
                $('#tg').treegrid('unselect', row.id);
            }
        }
        var formatter = {
            interfaceState: function (value, row, index) {
                if (value == 0) {
                    return "<font color='green'>投产</font>";
                }
                if (value == 1) {
                    return "<font color='red'>废弃</font>";
                }
            },
            version:function(value, row, index){
                try {
                    versionCode = row.version.code;
                    return row.version.code
                } catch (exception) {
                }
            }
        };
        $(function () {
            var interfaceId = "${param.interfaceId}";
            /**
            *  初始化接口标签
            * @param result
             */
            var initTags = function initTags(result){
                result.forEach(function(tag){
                    $("#tags").append("<li>" + tag.tagName + "</li>");
                });
                $("#tags").tagit();

            };
            tagManager.getTagForInterface(interfaceId,initTags);


            $("#saveTagBtn").click(function () {
                var tagNames = $("#tags").tagit("assignedTags");
                var tags = [];
                tagNames.forEach(function (tagName){
                    var tagToAdd = {};
                    tagToAdd.tagName = tagName;
                    tags.push(tagToAdd);
                });
                tagManager.addTagForInterface(interfaceId, tags, function (){
                    alert("标签保存成功");
                });
            });

            $("#historyBtn").click(function () {
                var urlPath =  '/jsp/interface/interface_history.jsp?interfaceId=${param.interfaceId}';
                var hisContent = ' <iframe scrolling="auto" frameborder="0"  src="' + urlPath + '" style="width:100%;height:100%;"></iframe>'

                parent.parent.$('#mainContentTabs').tabs('add', {
                    title: '接口发布历史',
                    content: hisContent,
                    closable: true
                });
            });

        });
    </script>
</head>

<body onload="loadData();">
<div>
    <table>
        <tr>
            <td>
                <label style="font-size: 12px">
                    接口标签:
                </label>
            </td>
            <td>
                <ul id="tags">
                </ul>
            </td>
            <td>
                <a href="#" id="saveTagBtn" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:1em">保存</a>
            </td>
            <td>
                <a href="#" id="historyBtn" class="easyui-linkbutton" iconCls="icon-save" style="margin-left:1em">发布历史</a>
            </td>
        </tr>
    </table>


</div>
<table id="interfacetg" style="height: 370px; width: auto;">
    <thead>
    <tr>
        <th data-options="field:'ecode',width:'10%'">
            交易码
        </th>
        <th data-options="field:'interfaceName',width:'18%'">
            交易名称
        </th>

        <th data-options="field:'status',width:'8%',align:'right'" formatter='formatter.interfaceState'>
            交易状态
        </th>
        <th data-options="field:'headName',width:'15%'">
            报文头
        </th>
        <th data-options="field:'desc',align:'right',width:'20%'">
            功能描述
        </th>
        <th data-options="field:'versionId',width:'10%'" formatter='formatter.version'>
            版本号
        </th>
        <th data-options="field:'optDate',width:'10%',align:'center'">
            更新时间
        </th>
        <th data-options="field:'optUser',width:'10%'">
            更新用户
        </th>

    </tr>
    </thead>
</table>
<div id="w" class="easyui-window" title=""
     data-options="modal:true,closed:true,iconCls:'icon-add'"
     style="width: 500px; height: 200px; padding: 10px;">

</div>

<div id="mm" class="easyui-menu" style="width: 120px;">
    <div onclick="append()" data-options="iconCls:'icon-add'">
        新增
    </div>
    <div onclick="editIt()" data-options="iconCls:'icon-edit'">
        编辑
    </div>
    <div onclick="removeIt()" data-options="iconCls:'icon-remove'">
        删除
    </div>
</div>
<table title="接口定义信息" id="tg"
       style="height: 440px; width: auto;"
       data-options="
				iconCls:'icon-edit',
				rownumbers: false,
				animate: true,
				collapsible: true,
				fitColumns: true,
				method: 'get',
				idField: 'id',
				treeField: 'structName',
                toolbar:toolbar,
                onContextMenu:onContextMenu,
                singleSelect:true,
                onClickRow:onClickRow
               
			">
    <thead>
    <tr>
        <th
                data-options="field:'structName',width:120,align:'left',editor:'text'">
            字段名称
        </th>
        <th
                data-options="field:'structAlias',width:90,align:'left',editor:'text'">
            字段别名
        </th>
        <th data-options="field:'type',width:80,editor:'text'">
            类型
        </th>
        <th data-options="field:'length',width:80,editor:'text'">
            长度
        </th>
        <%--<th data-options="field:'metadataId',width:100,editor:'text'">
            元数据ID
        </th>--%>
        <%--<th data-options="field:'scale',width:100,editor:'text'">
            精度
        </th>--%>
        <th data-options="field:'required',width:50,editor:'text'">
            是否必须
        </th>
        <th data-options="field:'remark',width:100,editor:'text'">
            备注
        </th>
        <th data-options="field:'seq',width:50,hidden:true">
            排序
        </th>
    </tr>
    </thead>
</table>
<div id="releaseDlg" class="easyui-dialog" closed="true" resizable="true"></div>
<script type="text/javascript">
</html>