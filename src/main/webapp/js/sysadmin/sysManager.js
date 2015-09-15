
/**
报文头管理
**/
var sysManager = {
    add : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/interfaceHead/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            },
            complete:function(responce){
                var resText = responce.responseText;
                if(resText.toString().charAt(0) == "<"){
                    alert("没有权限！");
//                              window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    modify : function(){
    },
    deleteEntity : function(){

    },
    get : function(){

    },
    
    addIDA : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/ida/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            },
            complete:function(responce){
                var resText = responce.responseText;
                if(resText.toString().charAt(0) == "<"){
                    alert("没有权限！");
//                                window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    removeIDA : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/ida/delete",
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            },
            complete:function(responce){
                var resText = responce.responseText;
                if(resText.toString().charAt(0) == "<"){
                    alert("没有权限！");
//                                window.location.href = "/jsp/403.jsp";
                }
            }
        });
    },
    append:function(){
    	uiinit.win({
			w:500,
			iconCls:'icon-add',
			title:"新增报文",
			url : "/jsp/sysadmin/header_add.jsp"
		});	
    },
    
    edit:function(){
    	var node = $('.mxsysadmintree').tree("getSelected");
    	uiinit.win({
			w:500,
			iconCls:'icon-add',
			title:"编辑报文",
			url : "/interfaceHead/edit/"+node.id
		});
    
    },
    
    remove:function(){

        var node = $('.msinterfacetree').tree("getSelected");
    	$.ajax({
            type: "get",
            contentType: "application/json; charset=utf-8",
            url: "/interfaceHead/delete/"+node.id,
            dataType: "json",
            success: function(result) {
                if(result){
                	$('.msinterfacetree').tree("reload");
                	var title = node.text;
                	$('#mainContentTabs').tabs("close",title);
                	
                }
            }
        });
    
    },
    addSystemPage:function(){
        	uiinit.win({
    			w:500,
    			iconCls:'icon-add',
    			title:"新增系统",
    			url : "/jsp/sysadmin/system_add.jsp"
    		});
    },
    querySystemPage:function(){
        if ($('#mainContentTabs').tabs('exists', "系统")){
            $('#mainContentTabs').tabs('select', "系统");
        } else {
            var content = '<iframe scrolling="auto" frameborder="0"  src="/jsp/sysadmin/system_manager.jsp" style="width:100%;height:100%;"></iframe>';
            $('#mainContentTabs').tabs('add',{
                title:"系统",
                content:content,
                closable:true
            });
         }
    },
    editSystemPage:function(){
        var node = $('.msinterfacetree').tree("getSelected");
        uiinit.win({
            w:500,
            iconCls:'icon-add',
            title:"编辑系统",
            url : "/system/edit/"+node.id
        });
    },
    addSystem : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/system/add",
            data: JSON.stringify(data),
            dataType: "json",
            success: function(result) {
                callBack(result);
            },
            complete:function(responce){
                var resText = responce.responseText;
                if(resText.toString().charAt(0) == "<"){
                    alert("没有权限！");
//                              window.location.href = "/jsp/403.jsp";
                }
            }
        });
     },

    deleteSystem:function(){
         var node = $('.msinterfacetree').tree("getSelected");
        if (!confirm("确定要删除该系统吗？")) {
            return;
        }
         $.ajax({
                type: "GET",
                contentType: "application/json; charset=utf-8",
                url: "/system/delete/"+node.id,
                dataType: "json",
                success: function(result) {
                    //$('#w').window('close');
                    $('.msinterfacetree').tree("reload");
                }
            });
     },
     addProtocolPage:function(){
         uiinit.win({
            w:500,
            iconCls:'icon-add',
            title:"新增协议",
            url : "/jsp/sysadmin/protocol_add.jsp"
         });
     },
    addFilePage: function(){
        uiinit.win({
            w:500,
            iconCls:'icon-add',
            title:"新增需求文件",
            url : "/jsp/sysadmin/file_add.jsp"
        });
    },
    addFilePageBySystem: function(systemId){
        uiinit.win({
            w:500,
            iconCls:'icon-add',
            title:"新增需求文件",
            url : "/jsp/sysadmin/file_add.jsp?systemId=" + systemId
        });
    },
    addProtocol : function(data, callBack){
         $.ajax({
             type: "POST",
             contentType: "application/json; charset=utf-8",
             url: "/protocol/add",
             data: JSON.stringify(data),
             dataType: "json",
             success: function(result) {
                 callBack(result);
             },
             complete:function(responce){
                 var resText = responce.responseText;
                 if(resText.toString().charAt(0) == "<"){
                     alert("没有权限！");
//                              window.location.href = "/jsp/403.jsp";
                 }
             }
         });
    }
}
