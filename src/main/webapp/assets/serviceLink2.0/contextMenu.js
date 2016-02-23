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
        $('#opDialog').dialog({
            title: '报文协议',
            width: 500,
            closed: false,
            cache: false,
            href: '/jsp/serviceLink/contextMenu/message_protocol.jsp' ,
            modal: true
        });
    },
    interfaceInfo : function(){
        $('#opDialog').dialog({
            title: '报文协议',
            width: 500,
            closed: false,
            cache: false,
            href: '/jsp/serviceLink/contextMenu/message_protocol.jsp' ,
            modal: true
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
