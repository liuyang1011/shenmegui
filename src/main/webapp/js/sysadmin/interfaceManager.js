
/**
接口管理
**/
var interfaceManager = {
    add : function(data,type, callBack){
        var processId = parent.PROCESS_INFO.processId;
        var url = "/interface/add?type="+type;
        if(processId){
            url = "/interface/add/"+processId+"?type="+type;
        }
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: url,
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
            }
        });
    },
    modifySEQ : function(id,seq,id2,seq2){
        $.ajax({
            type: "get",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "/ida/modifySEQ/"+id+"/"+seq+"/"+id2+"/"+seq2,
            dataType: "json",
            success: function(result) {
            }
        });
    },
     removeIDA : function(data, callBack){
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/ida/delete/"+data,
            dataType: "json",
            success: function(result) {
                callBack(result);
            }
        });
    },
    append:function(systemId){

    	uiinit.win({
			w:500,
			iconCls:'icon-add',
			title:"新增接口",
			url : "/jsp/interface/interface_add.jsp?systemId="+systemId
		});	
    },
    
    edit:function(interfaceId,systemId){
    	
    	var node = $('.msinterfacetree').tree("getSelected");
    	
    	var sId = interfaceId;
    	try{
    		sId=node.id
    	}catch(e){
    		sId = interfaceId;
    	}
    	uiinit.win({
			w:500,
			iconCls:'icon-add',
			title:"编辑报文",
			url : "/interface/edit/"+sId+"?systemId="+systemId
		});
    
    },
    
    remove:function(interfaceId,title){
    	
    	var node = $('.msinterfacetree').tree("getSelected");
    	
    	var sId = interfaceId;
    	var tit = title;
    	var treeObj = $('.msinterfacetree');
    	var tabObj = $('#mainContentTabs');
    	try{
    		sId= node.id
    		tit = node.text;
    	}catch(e){
    		sId = interfaceId;
    		tit = title;
    		treeObj = parent.$('.msinterfacetree');
    		tabObj = parent.$('#mainContentTabs');
    	}
    	
    	$.ajax({
            type: "get",
            contentType: "application/json; charset=utf-8",
            url: "/interface/delete/"+sId,
            dataType: "json",
            success: function(result) {
                if(result){
                    $("#tg").datagrid("reload");
                	treeObj.tree("reload");
                	tabObj.tabs("close",tit);
                }
            }
        });
    
    },

    release:function(){
        var rows = $("#tg").datagrid("getSelections");
        var interfaceIds = "";
        for(var per in rows){
            if(per == rows.length-1){
                interfaceIds += rows[per].interfaceId;
            }else{
                interfaceIds += rows[per].interfaceId + ",";
            }

        }
        $.ajax({
            type: "get",
            contentType: "application/json; charset=utf-8",
            url: "/interface/release/"+interfaceIds,
            dataType: "json",
            success: function(result) {
                if(result){
                    alert("发布成功")
                    $("#tg").datagrid("reload");
                }
            }
        });
    }
 
}
