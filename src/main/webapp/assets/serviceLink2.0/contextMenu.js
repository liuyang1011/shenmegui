//交易节点基本信息
function baseInfo(node){
    $("#resultList").treegrid("select", id);
    $('#opDialog').dialog({
        title: '新增菜单',
        width: 500,
        closed: false,
        cache: false,
        href: '/jsp/menu/menucategory_add.jsp?categoryName=' + encodeURI(encodeURI(text)) + '&categoryId=' + id ,
        modal: true
    });
}