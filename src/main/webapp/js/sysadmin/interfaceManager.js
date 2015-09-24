
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
                if(resText.toString().indexOf("没有操作权限") > 0){
                    alert("没有权限！");
                    //window.location.href = "/jsp/403.jsp";
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
            top:"50px",
            left: (2*$(document).width() - $(top).width() -500)/2 +"px",
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
            top:"20px",
            left:"150px",
			iconCls:'icon-add',
			title:"编辑报文",
			url : "/interface/edit/"+sId+"?systemId="+systemId
		});
    
    },
    
    remove:function(interfaceId,title){
        if (!confirm("确定要删除该接口吗？")) {
            return;
        }
    	
    	var node = $('.msinterfacetree').tree("getSelected");
        console.log(node);
    	var sId = interfaceId;
    	var tit = title;
    	var treeObj = $('.msinterfacetree');
    	var tabObj = $('#mainContentTabs');
    	try{
    		sId= node.id;
    		tit = node.text;
    	}catch(e){
    		sId = interfaceId;
    		tit = title;
    		treeObj = parent.$('.msinterfacetree');
    		tabObj = parent.$('#mainContentTabs');
    	}

    	$.ajax({
            type: "post",
            contentType: "application/json; charset=utf-8",
            //测试中出现#&等特殊符号，没法删掉
            //url: "/interface/delete/"+sId,
            url: "/interface/delete2",
            dataType: "json",
            data:JSON.stringify(sId),
            success: function(result) {
                if(result){
//                    $("#tg").datagrid("reload");
//                	treeObj.tree("reload");
                    var parent = $('.msinterfacetree').tree("getParent", node.target);
                    $('.msinterfacetree').tree("remove", node.target);
                    $('.msinterfacetree').tree('options').url = "/interface/getLeftTree/subInterfaceTree/system/" + parent.id;
                    $('.msinterfacetree').tree("reload", parent.target);

                    tabObj.tabs("close",tit);
                }
            }
        });
    
    },

    release:function(interfaceIds,versionDesc ){
        $.ajax({
            type: "get",
            contentType: "application/json; charset=utf-8",
            url: "/interface/release",
            dataType: "json",
            data:{
                "interfaceIds":interfaceIds,
                "versionDesc":encodeURI(versionDesc)
                    },
            success: function(result) {
                if(result){
                    $('#releaseDlg').dialog('close');
                    alert("发布成功");
                    try{
                        $("#tg").datagrid("reload");
                    }catch (exception) {
                    }
                    try{
                        $("#interfacetg").datagrid("reload");
                    }catch (exception) {
                    }
                }
            }
        });
    }
 
}
