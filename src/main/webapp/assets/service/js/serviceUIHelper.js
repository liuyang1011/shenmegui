/**
 * Created by vincentfxz on 15/6/30.
 */
var serviceUIHelper = {
    "appendServiceForm": function appendServiceForm() {
        var node = $('.mxservicetree').tree('getSelected');
        if (node.type == "service") {
            uiinit.win({
                w: 500,
                iconCls: 'icon-add',
                title: "新增服务",
                url: "/jsp/service/serviceAppandForm.jsp"
            });
        } else if (node.type == "root" || node.serviceCategory.parentId == null) {//根节点 或 父节点
            uiinit.win({
                w: 500,
                iconCls: 'icon-add',
                title: "新增服务类",
                url: "/dataTemplate/formTemplate/serviceCategoryForm/serviceCategoryAppandForm.jsp"
            });
        } else if (node.type == "serviceCategory" && node.serviceCategory.parentId != null) {//服务类子节点
            uiinit.win({
                w: 500,
                iconCls: 'icon-add',
                title: "新增服务",
                url: "/jsp/service/serviceAppandForm.jsp"
            });
        }
    },
    "editServiceForm": function editServiceForm() {
        var node = $('.mxservicetree').tree('getSelected');
        if (node.type == "service") {
            uiinit.win({
                w: 500,
                iconCls: 'icon-add',
                title: "编辑服务",
                url: "/pages/service/form/serviceEditForm.jsp"
            });
        } else if (node.type == "serviceCategory") {
            uiinit.win({
                w: 500,
                iconCls: 'icon-add',
                title: "编辑服务类",
                url: "/dataTemplate/formTemplate/serviceCategoryForm/serviceCategoryEditForm.jsp"
            });
        }
    },
    "deleteServiceFormTree" : function removeService(){
        var node = $('.mxservicetree').tree('getSelected');
        var id = node.id;
        if(node.type=="service"){
            $('.mxservicetree').tree('remove', node.target);
            serviceManager.deleteById(id);
        }else if(node.type=="serviceCategory"){
            $('.mxservicetree').tree('remove', node.target);
            serviceManager.deleteCategoryById(id);
        }
    },
    "searchService" : function searchService(){
        var serviceName = $('#servicetreefilter').val();
        if(serviceName==""){
            $('.mxservicetree').tree({
                url:"/service/getTree"
            });
        }else{
            $('.mxservicetree').tree({
                url:"/service/searchService/"+serviceName
            });
        }
    },
    "refreshTree" : function refreshTree(){
        $('.mxservicetree').tree({
            url:"/service/searchService/processId"+ PROCESS_INFO.processId
        });
    },
    "exportPdf" : function exportPdf(){
                var node = $('.mxservicetree').tree('getSelected');
                var id = node.id;
                var type = node.type;
                if(node.type == "serviceCategory"){
                    if(node.children != null && node.children.length == 0){
                         alert("该服务分类下没有服务数据！");
                                        return false;
                    }
                    if(node.children != null && node.children.length > 0){
                        type = "serviceCategory";
                    }
                }
                var form=$("<form>");//定义一个form表单
                form.attr("style","display:none");
                form.attr("target","");
                form.attr("method","post");
                form.attr("action","/pdfExporter/exportService");
                var input1=$("<input>");
                input1.attr("type","hidden");
                input1.attr("name","id");
                input1.attr("value",id);

                 var input2=$("<input>");
                 input2.attr("type","hidden");
                 input2.attr("name","type");
                 input2.attr("value",type);

                $("body").append(form);//将表单放置在web中
                form.append(input1);
                form.append(input2);
                form.submit();//表单提交
        },
        "exportExcel" : function exportExcel(){
                var node = $('.mxservicetree').tree('getSelected');
                var id = node.id;
                var type = node.type;
                if(node.type == "serviceCategory"){
                    if(node.children != null && node.children.length == 0){
                         alert("该服务分类下没有服务数据！");
                                        return false;
                    }
                    else if(node.serviceCategory.parentId == null){
                         type = "serviceCategory1";
                    }
                    else{
                          type = "serviceCategory2";
                    }
                }
                var form=$("<form>");//定义一个form表单
                form.attr("style","display:none");
                form.attr("target","");
                form.attr("method","post");
                form.attr("action","/excelExporter/exportService");
                var input1=$("<input>");
                input1.attr("type","hidden");
                input1.attr("name","id");
                input1.attr("value",id);

                 var input2=$("<input>");
                 input2.attr("type","hidden");
                 input2.attr("name","type");
                 input2.attr("value",type);

                $("body").append(form);//将表单放置在web中
                form.append(input1);
                form.append(input2);
                form.submit();//表单提交
        },
    "exportView" : function exportView(){
        //var node = $('.mxservicetree').tree('getSelected');
        var t = $('.mxservicetree');	// get the tree object
        var node = t.tree('getSelected');		// get selected node
        if(node == null || node == ''){
            alert("请选择服务！");
            return false;
        }
        var id = node.id;
        if(node.domId == '_easyui_tree_1'){
            type = "root";
        }
        if(node.domId == '_easyui_tree_2'){
            type = "serviceCategory1";
        }
        if(node.domId == '_easyui_tree_3'){
            type = "serviceCategory1";
            var parent = t.tree('getParent', node.target);
            id = parent.id;
        }
        if(node.domId == '_easyui_tree_4'){
            type = "serviceCategory1";
            parent = t.tree('getParent', node.target);
            id = t.tree('getParent', parent.target).id;
        }

        var form=$("<form>");//定义一个form表单
        form.attr("style","display:none");
        form.attr("target","");
        form.attr("method","post");
        form.attr("action","/excelExporter/exportServiceView");
        var input1=$("<input>");
        input1.attr("type","hidden");
        input1.attr("name","categoryId");
        input1.attr("value",id);

        var input2=$("<input>");
        input2.attr("type","hidden");
        input2.attr("name","type");
        input2.attr("value",type);

        $("body").append(form);//将表单放置在web中
        form.append(input1);
        form.append(input2);

        form.submit();//表单提交

    }

};
