var userManager = {
    "compare" : function(){
        var autoId1 =  $("#version1").combobox("getValue");
        var autoId2 =  $("#version2").combobox("getValue");
        if(autoId1 == autoId2){
            alert("两个版本相同！");
        }else{
            var type=0;
            if(autoId1 != "" && autoId2 != ""){
                type = 1;
            }
            if(autoId1 != "" && autoId2 == ""){
                type = 2;
            }
            if(type == 0){
                autoId1 = "";
            }
            if(type == 2){
                autoId1 = autoId2;
                autoId2 = "";
            }
            var versionId = $("#versionId").attr("value");
            var urlPath = "/version/getOperationDiff?type=" + type +"&versionId=" + versionId + "&autoId1="+autoId1+"&autoId2="+autoId2 + "&_t=" + new Date().getTime();
            $("#sdaHisTree").treegrid({url:urlPath});
        }
    }
}