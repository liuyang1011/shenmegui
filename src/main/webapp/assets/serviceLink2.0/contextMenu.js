//交易节点基本信息
var seelectedNode;
var contextMenuManager = {
    baseInfo : function (){
        $('#opDialog').dialog({
            title: '基本信息',
            width: 500,
            closed: false,
            cache: false,
            href: '/serviceLinkContextMenu/baseInfo?invokeId=' + seelectedNode.id ,
            modal: true
        });
    },
    messageProtocol : function(){
        $.ajax({
            type: "get",
            async: false,
            url: "/serviceLinkContextMenu/checkProtocol?invokeId=" + seelectedNode.id,
            dataType: "json",
            success: function (data) {
                if (data == true) {
                    $('#opDialog').dialog({
                        title: '报文协议',
                        width: 500,
                        closed: false,
                        cache: false,
                        href: '/serviceLinkContextMenu/messageProtocolPage?invokeId='+ seelectedNode.id ,
                        modal: true
                    });
                }else{
                    alert("该接口未关联任何协议！");
                }
            }
        });

    },
    serviceInfo : function(){
        var urlPath = '/serviceLinkContextMenu/serviceInfo?invokeId=' + seelectedNode.id;
        parent.parent.$('#mainContentTabs').tabs('add', {
            title: '服务信息',
            content: ' <iframe scrolling="auto" frameborder="0"  src="' + urlPath + '"  style="width:100%;height:99%;"></iframe>',
            closable: true
        });
    },
    interfaceInfo : function(){
        $.ajax({
            type: "get",
            async: false,
            url: "/serviceLinkContextMenu/getInterfaceId?invokeId=" + seelectedNode.id,
            dataType: "json",
            success: function (data) {
                if (""  != data.interfaceId ) {
                    var urlPath = '/jsp/serviceLink/contextMenu/interfaceInfo.jsp?interfaceId=' + data.interfaceId;
                    parent.parent.$('#mainContentTabs').tabs('add', {
                        title: '接口信息',
                        content: ' <iframe scrolling="auto" frameborder="0"  src="' + urlPath + '"  style="width:100%;height:99%;"></iframe>',
                        closable: true
                    });
                }else{
                    alert("接口信息为空！");
                }
            }
        });
    },
    openNode : function(){
        $('#opDialog').dialog({
            title: '报文协议',
            width: 500,
            closed: false,
            cache: false,
            href: '/jsp/serviceLink/contextMenu/message_protocol.jsp' ,
            modal: true
        });
    },
    closeNode : function(){
        $('#opDialog').dialog({
            title: '报文协议',
            width: 500,
            closed: false,
            cache: false,
            href: '/jsp/serviceLink/contextMenu/message_protocol.jsp' ,
            modal: true
        });
    }

}
