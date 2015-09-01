<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form class="formui">
    <div class="win-bbar" style="text-align:center"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                                                       onClick="$('#interfaceDlg').dialog('close')">取消</a><a href="#"
                                                                                                             onclick="addInterfaceContent()"
                                                                                                             class="easyui-linkbutton"
                                                                                                             iconCls="icon-save">确定</a></div>
    <fieldset>
        <legend>服务消费者</legend>
        <table border="0" cellspacing="0" cellpadding="0" style="width:auto;">
            <tr>
                <th align="center">已经应用</th>
                <td width="50" align="center">&nbsp;</td>
                <th align="center">应用系统列表</th>
            </tr>

            <tr>
                <th align="center"><select name="select2" id="consumer" size="10" multiple
                                           style="width:155px;height:160px" panelHeight="auto">
                </select></th>
                <td align="center" valign="middle"><a href="#" class="easyui-linkbutton" iconCls="icon-select-add"
                                                      onClick="selectex('consumer','systemList1','1')"></a><br>
                    <br>
                    <br>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-select-remove"
                       onClick="chooseInterface('systemList1','consumer','1')"></a></td>
                <td align="center">
                    <select name="select" id="systemList1" size="10" multiple style="width:155px;height:160px"
                            panelHeight="auto"/>
                    </select>
                </td>
            </tr>
        </table>
    </fieldset>
    <div style="margin-top:10px;"></div>
    <fieldset>
        <legend>服务提供者</legend>
        <table border="0" cellspacing="0" cellpadding="0" style="width:auto;">
            <tr>
                <th align="center">已经应用</th>
                <td width="50" align="center">&nbsp;</td>
                <th align="center">应用系统列表</th>
            </tr>

            <tr>
                <th align="center"><select name="select2" id="provider" size="10" multiple
                                           style="width:155px;height:160px" panelHeight="auto">
                </select></th>
                <td align="center" valign="middle"><a href="#" class="easyui-linkbutton" iconCls="icon-select-add"
                                                      onClick="selectex('provider','systemList2','0')"></a><br>
                    <br>
                    <br>
                    <a href="#" class="easyui-linkbutton" iconCls="icon-select-remove"
                       onClick="chooseInterface('systemList2','provider','0')"></a></td>
                <td align="center"><select name="select" id="systemList2" size="10" multiple
                                           style="width:155px;height:160px" panelHeight="auto">

                </select></td>
            </tr>
        </table>
    </fieldset>
    <!--
    <div style="margin:20px">
        <table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>消费者系统：</th>
                <td><input id="consumerId" class="easyui-combobox"
                           data-options="
                                                panelHeight:'300',
                         						url:'/system/getSystemAll',
                         				 		 method:'get',
                         				 		 valueField: 'id',
                         				 		 textField: 'chineseName',
                         				 		 onChange:function(newValue, oldValue){
                         							this.value=newValue;
                         							$('#consumerInterfaceId').combobox('setValue','');
                         							$('#consumerInterfaceId').combobox('setText','');
                         							$('#consumerInterfaceId').combobox({url:'/interface/getInterfaceJson?systemId='+newValue});
                         						}"/>
                </td>

            </tr>
            <tr>
                <th>消费者接口：</th>
                <td><input id="consumerInterfaceId" class="easyui-combobox"
                           data-options="
                                                panelHeight:'300',
                         				 		 method:'get',
                         				 		 valueField: 'interfaceId',
                         				 		 textField: 'interfaceName',
                         				 		 onChange:function(newValue, oldValue){
                         							this.value=newValue;
                         						 }
                         						"/>
                </td>
            </tr>
            <tr>
                <th>提供者系统：</th>
                <td><input id="providerId" class="easyui-combobox"
                           data-options="
                                                 panelHeight:'300',
                          						url:'/system/getSystemAll',
                          				 		 method:'get',
                          				 		 valueField: 'id',
                          				 		 textField: 'chineseName',
                          				 		 onChange:function(newValue, oldValue){
                          							this.value=newValue;
                          							$('#providerInterfaceId').combobox('setValue','');
                          							$('#providerInterfaceId').combobox('setText','');
                          							$('#providerInterfaceId').combobox({url:'/interface/getInterfaceJson?systemId='+newValue});
                          						}"/>
                </td>

            </tr>
            <tr>
                <th>提供者接口：</th>
                <td><input id="providerInterfaceId" class="easyui-combobox"
                           data-options="
                                                  panelHeight:'300',
                           				 		 method:'get',
                           				 		 valueField: 'interfaceId',
                           				 		 textField: 'interfaceName',
                           				 		 onChange:function(newValue, oldValue){
                           							this.value=newValue;
                           						 }
                           						"/>
                </td>
            </tr>
            <tr>
                <th>描述：</th>
                <td><input id="mappdesc" class="easyui-textbox"/>
                </td>
            </tr>

        </table>
    </div>
    -->
    <div id="opDlg" class="easyui-dialog"
         style="width:400px;height:280px;padding:10px 20px" closed="true"
         resizable="true"></div>
</form>
<script type="text/javascript">
    loadSystem("systemList1", systemList, "systemId", "systemChineseName");
    loadSystem("systemList2", systemList, "systemId", "systemChineseName");

</script>
