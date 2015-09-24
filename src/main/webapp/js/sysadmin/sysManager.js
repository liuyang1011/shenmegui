/**
 报文头管理
 **/
var sysManager = {
    add: function (data, callBack) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/interfaceHead/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                callBack(result);
            },
            complete: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    modify: function () {
    },
    deleteEntity: function () {

    },
    get: function () {

    },

    addIDA: function (data, callBack) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/ida/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                callBack(result);
            },
            complete: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    removeIDA: function (data, callBack) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/ida/delete",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                callBack(result);
            },
            complete: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    append: function () {
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "新增报文",
            url: "/jsp/sysadmin/header_add.jsp"
        });
    },

    edit: function () {
        var node = $('.mxsysadmintree').tree("getSelected");
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "编辑报文",
            url: "/interfaceHead/edit/" + node.id
        });

    },

    remove: function () {
        if (!confirm("确定要删除该报文头吗？")) {
            return;
        }
        var node = $('.msinterfacetree').tree("getSelected");
        $.ajax({
            type: "get",
            contentType: "application/json; charset=utf-8",
            url: "/interfaceHead/delete/" + node.id,
            dataType: "json",
            success: function (result) {
                if (result) {
                    alert("删除成功！");
                    var parent = $('.msinterfacetree').tree("getParent", node.target);
                    $('.msinterfacetree').tree("remove", node.target);
                    $('.msinterfacetree').tree('options').url = "/interface/getLeftTree/subHeadTree/system/" + parent.id;
                    $('.msinterfacetree').tree("reload", parent.target);
                    var title = node.text;
                    $('#mainContentTabs').tabs("close", title);
                }
            }
        });

    },
    addSystemPage: function () {
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "新增系统",
            url: "/jsp/sysadmin/system_add.jsp"
        });
    },
    querySystemPage: function () {
        if ($('#mainContentTabs').tabs('exists', "系统")) {
            $('#mainContentTabs').tabs('select', "系统");
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/sysadmin/system_manager.jsp" style="width:100%;height:100%;"></iframe>';
            $('#mainContentTabs').tabs('add', {
                title: "系统",
                content: content,
                closable: true
            });
        }
    },
    editSystemPage: function () {
        var node = $('.msinterfacetree').tree("getSelected");
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "编辑系统",
            url: "/system/edit/" + node.id
        });
    },
    addSystem: function (data, callBack) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/system/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                callBack(result);
            },
            complete: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },

    deleteSystem: function () {
        var node = $('.msinterfacetree').tree("getSelected");
        if (!confirm("确定要删除该系统吗？")) {
            return;
        }
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            //如果systemId包含#等特殊符号就会有问题
            //url: "/system/delete/" + systemId,
            url: "/system/delete2",
            dataType: "json",
            data: JSON.stringify(node.id),
            success: function (result) {
                if(result){
                    $('.msinterfacetree').tree("reload");
                }else{
                    alert("包含接口的系统不予删除，请先删除系统下的接口")
                }
            }
        });
    },
    addProtocolPage: function () {
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "新增协议",
            url: "/jsp/sysadmin/protocol_add.jsp"
        });
    },
    addFilePage: function () {
        uiinit.win({
            w: 500,
            iconCls: 'icon-add',
            title: "新增需求文件",
            url: "/jsp/sysadmin/file_add.jsp"
        });
    },
    addFilePageBySystem: function () {
        var node = $('.msinterfacetree').tree("getSelected");
        uiinit.win({
            w: 500,
            top:100,
            iconCls: 'icon-add',
            title: "新增需求文件",
            url: "/jsp/sysadmin/file_add.jsp?systemId=" + node.id
        });
    },
    addProtocol: function (data, callBack) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/protocol/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                callBack(result);
            },
            complete: function (responce) {
                var resText = responce.responseText;
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    "deleteProtocol": function () {
        var node = $('.msinterfacetree').tree("getSelected");
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/protocol/delete/" + node.id,
            dataType: "json",
            success: function (result) {
                var parent = $('.msinterfacetree').tree("getParent", node.target);
                $('.msinterfacetree').tree("remove", node.target);
                $('.msinterfacetree').tree('options').url = "/interface/getLeftTree/subProtocolTree/system/" + parent.id;
                $('.msinterfacetree').tree("reload", parent.target);
            }
        });
    },
    "deleteFile": function (){
        var node = $('.msinterfacetree').tree("getSelected");
        if (!confirm("确定要删除选中的记录吗？")) {
            return;
        }
        $.ajax({
            type: "GET",
            contentType: "application/json; charset=utf-8",
            url: "/fileManager/delete/" + node.id,
            dataType: "json",
            success: function (result) {
                var parent = $('.msinterfacetree').tree("getParent", node.target);
                $('.msinterfacetree').tree("remove", node.target);
                $('.msinterfacetree').tree('options').url = "/interface/getLeftTree/subFileTree/system/" + parent.id;
                $('.msinterfacetree').tree("reload", parent.target);
                $('#tg').datagrid("reload");

            }
        });
    },
    "refreshFile" : function(){
        var node = $('.msinterfacetree').tree("getSelected");
        $('.msinterfacetree').tree('append', {
            parent: (node?node.target:null),
            data: [{
                text: 'new item1'
            }]
        });
        var urlPath = $('.msinterfacetree').tree('options').url;
        $('.msinterfacetree').tree('options').url = "/interface/getLeftTree/subFileTree/system/" + node.id;
        $('.msinterfacetree').tree("reload", node.target);
        $('.msinterfacetree').tree('options').url = urlPath;
    }
}
