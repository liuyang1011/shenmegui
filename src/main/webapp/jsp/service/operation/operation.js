var ff = {
    interfaceName: function (value, row, index) {
        try {
            return row.inter.interfaceName;
        } catch (exception) {
        }
    },
    systemChineseName: function (value, row, index) {
        try {
            return row.system.systemChineseName;
        } catch (exception) {
        }
    },
    isStandardText: function (value, row, index) {
        if ("0" == value) {
            return "是";
        } else {
            return "否";
        }
    },
    typeText: function (value, row, index) {
        if ("1" == value) {
            return "消费者";
        }
        if ("0" == value) {
            return "提供者";
        }
    },
    version:function(value, row, index){
        try {
            return row.version.code
        } catch (exception) {
        }
    }
}

function save(formId, operation) {
    if (!$("#" + formId).form('validate')) {
        return false;
    }
    var params = $("#" + formId).serialize();
    params = decodeURIComponent(params, true);
    var processId = parent.parent.PROCESS_INFO.processId;
    params = params + "&processId=" + processId;
    var urlPath;
    if (operation == 0) {
        urlPath = "/operation/add";
    }
    if (operation == 1) {
        urlPath = "/operation/edit";
    }
    $.ajax({
        type: "post",
        async: false,
        url: urlPath,
        dataType: "json",
        data: params,
        success: function (data) {
            if (data == true) {
                var serviceId = $("#serviceId").attr("value");
                var operationId = $("#operationId").textbox("getValue");
                var serviceInvokeList = new Array();
                for(var i = 0; i < invokeList.length; i++){
                    var si = invokeList[i];
                    var serviceInvoke = {};
                    serviceInvoke.serviceId = serviceId;
                    serviceInvoke.operationId = operationId;
                    serviceInvoke.systemId = si.systemId;
                    serviceInvoke.interfaceId = si.interfaceId;
                    serviceInvoke.type = si.type;
                    serviceInvokeList.push(serviceInvoke);
                }
                $.ajax({
                    type: "post",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    url: "/operation/addInvokeMapping",
                    dataType: "json",
                    data: JSON.stringify(serviceInvokeList),
                    success: function (data) {
                        alert("操作成功 ！");
                        //清空页面数据
                        clean();
                        //刷新查询列表
                        parent.serviceInfo.reloadData();
                    }
                });

            } else {
                alert("保存出现异常 ，操作失败！");
            }

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
function clean() {
    $("#operationId").textbox("setValue", "");
    $("#operationName").textbox("setValue", "");
    $("#operationDesc").textbox("setValue", "");
    $("#operationRemark").textbox("setValue", "");
    $("#state").combobox("setValue", "");

    //标签切换
    parent.$('#subtab').tabs('select', '服务基本信息');
}

function choseService(id) {
    $('#' + id).dialog({
        title: '选择服务',
        width: 500,
        closed: false,
        cache: false,
        href: '/jsp/service/serviceTreePage.jsp',
        modal: true
    });
}

/*
 * 弹出服务窗口后选中某一个服务，确认方法
 * 获取选中数据的serviceId
 * 关闭对话框
 * 改变table的url
 * 刷新table
 */
function selectService() {
    var node = $("#serviceTree").tree("getSelected");
    if(node.type != "service"){
        alert("请选择服务！");
        return false;
    }
    $('#dlg').dialog('close');
    $("#serviceId").textbox("setValue", node.service.serviceId);
    $("#serviceId").textbox("setText", node.service.serviceName);
    $("#operationAuditList").datagrid({
        url:'/operation/getAudits/'+ node.service.serviceId
    });
}

//审核通过方法
function auditPass(listId) {
    var checkedItems = $('#' + listId).datagrid('getChecked');
    if (checkedItems != null && checkedItems.length > 0) {
        var ids = [];
        $.each(checkedItems, function (index, item) {
            ids.push(""+item.operationId+","+item.serviceId);
        });
        $.ajax({
            type: "post",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "/operation/auditPass",
            dataType: "json",
            data: JSON.stringify(ids),
            success: function (data) {
                alert("操作成功");
                $('#' + listId).datagrid('reload');
            }
        });
    }
}

//审核不通过方法
function auditUnPass(listId) {
    var checkedItems = $('#' + listId).datagrid('getChecked');
    if (checkedItems != null && checkedItems.length > 0) {
        var ids = [];
        $.each(checkedItems, function (index, item) {
            ids.push(item.operationId);
        });
        $.ajax({
            type: "post",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "/operation/auditUnPass",
            dataType: "json",
            data: JSON.stringify(ids),
            success: function (data) {
                alert("操作成功");
                $('#' + listId).datagrid('reload');
            }
        });
    }
    //如果有任务在执行，则更新任务的状态
    parent.PROCESS_INFO.approved = false;
}
//加载系统列表
function loadSystem(id, items, valueField, textField) {
    if (items.length > 0) {
        $.each(items, function (index, item) {
            $("#" + id).append("<option value='" + item[valueField] + "'>" + item[textField] + "</option>");
        });
    }
}
//加载已选系统-接口列表
function loadSelect(id, items) {
    if (items.length > 0) {
        $.each(items, function (index, item) {
            var text = item["system"]["systemChineseName"];
            if (null != item["inter"]) {
                text = text + "::" + item["inter"]["interfaceName"];
            }
            $("#" + id).append("<option value='__invoke__" + item["invokeId"] + "'>" + text + "</option>");
        });
    }
}

//从系统列表中选中系统点击向左移按钮，如果没有接口直接移动，如果有弹出接口选择界面
function chooseInterface(oldListId, newListId, type) {
    $('#' + oldListId + ' option:selected').each(function () {
        var value = $(this).val();
        var text = this.text;
        $.ajax({
            type: "get",
            async: false,
            contentType: "application/json; charset=utf-8",
            url: "/operation/judgeInterface",
            dataType: "json",
            data: {"systemId": $(this).val()},
            success: function (data) {
                //如果系统没有接口，直接转移
                if (!data) {
//		     		$("#"+oldListId+" option[value="+value+"]").remove();
                    var exsit = $("#" + newListId + " option[value='" + value + "']");
                    if (exsit.length > 0) {
                        alert("应经被选中！");
                    } else {
                        var invoekId = new Date().getTime();
                        $("#" + newListId).append("<option value='" + invoekId + "'>" + text + "</option>");
                        var si = genderServiceInvoke(invoekId, value,text, "","", type );
                        if(type == "1"){
                            consumerList.push(si);
                        }
                        if(type =="0"){
                            providerList.push(si);
                        }
                    }

                } else {
                    //如果有接口弹出接口选择页面
                    $('#opDlg').dialog({
                        title: '接口选择',
                        width: 700,
                        closed: false,
                        cache: false,
                        href: '/jsp/service/operation/interfaceList.jsp?systemId=' + value + "&newListId=" + newListId+"&type=" + type,
                        modal: true
                    });
                }

            }
        });
    });

}

function selectex(oldDataUi, newDataUi, type) {
    $('#' + oldDataUi + ' option:selected').each(function () {
        var value = $(this).val();
        $("#" + oldDataUi + " option[value=" + value + "]").remove();
        if(type == "1"){
            for(var i = 0; i < consumerList.length ; i ++){
                var si = consumerList[i];
                if(si.invokeId == value){
                    consumerList.splice(i,1);
                }
            }
        }
        if(type == "0"){
            for(var i = 0; i < providerList.length ; i ++){
                var si = providerList[i];
                if(si.invokeId == value){
                    providerList.splice(i,1);
                }
            }
        }
//			$("#"+newDataUi).append("<option value='"+$(this).val()+"'>"+this.text+"</option>");
    });
}

function selectInterface(listId, type) {
    var checkedItems = $('#intefaceList').datagrid('getChecked');
    if (checkedItems != null && checkedItems.length > 0) {
        $.each(checkedItems, function (index, item) {
            var exsit = $("#" + listId + " option[value='" + item.invokeId + "']");
            if (exsit.length > 0) {
                alert("该接口已经被选中！");
            } else {
                $("#" + listId).append("<option value='" + item.invokeId + "'>" + item.systemChineseName + "::" + item.interfaceName + "</option>");
                var si = genderServiceInvoke(item.invokeId, item.systemId, item.systemChineseName, item.interfaceId, item.interfaceName, type);
                if(type == "1"){
                    consumerList.push(si);
                }
                if(type =="0"){
                    providerList.push(si);
                }
            }

        });
    }
    $('#opDlg').dialog('close');
}
function genderServiceInvoke(invokeId, systemId,systemChineseName, interfaceId, interfaceName, type){
    var serviceInvoke = {};
    serviceInvoke.invokeId = invokeId;
    serviceInvoke.systemId = systemId;
    serviceInvoke.systemChineseName = systemChineseName;
    serviceInvoke.interfaceId = interfaceId;
    serviceInvoke.interfaceName = interfaceName;
    serviceInvoke.type = type;
    return serviceInvoke;

}
