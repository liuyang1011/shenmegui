<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript">
       //关系保存按钮
        function selectCP(){
            alert(0);
            var consumerId = $("#consumerId").combobox("getValue");
            var consumer = $("#consumerId").combobox("getText");
            var consumerInterfaceId = $("#consumerInterfaceId").combobox("getValue");
            var consumerInterface = $("#consumerInterfaceId").combobox("getText");
            var providerId = $("#providerId").combobox("getValue");
            var provider = $("#providerId").combobox("getText");
            var providerInterfaceId = $("#providerInterfaceId").combobox("getValue");
            var providerInterface = $("#providerInterfaceId").combobox("getText");

            var row = {"consumerId": consumerId,
              "consumer": consumer,
             "consumerInterfaceId":consumerInterfaceId,
             "consumerInterface":consumerInterface,
             "providerId":providerId,
             "provider":provider,
             "providerInterfaceId":providerInterfaceId,
             "providerInterface":providerInterface};

            $("#consumerProvicerList").datagrid("appendRow", row);
            closeDlg();
        }

</script>
<form class="formui">
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
 <div class="win-bbar" style="text-align:center"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                                                            onClick="closeDlg()">取消</a><a href="#"
                                                                                       onclick="selectCP()"
                                                                                       class="easyui-linkbutton"
                                                                                       iconCls="icon-save">保存</a></div>
        </form>
