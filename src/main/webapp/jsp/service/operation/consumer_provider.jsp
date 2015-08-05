<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<form class="formui">
<div style="margin:20px">
<table border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th>消费者系统</th>
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
                <th>消费者接口</th>
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
                <th>提供者系统</th>
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
                <th>提供者接口</th>
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

        </table>
 </div>
 <div class="win-bbar" style="text-align:center"><a href="#" class="easyui-linkbutton" iconCls="icon-cancel"
                                                            onClick="closeDlg()">取消</a><a href="#"
                                                                                       onclick="selectCP()"
                                                                                       class="easyui-linkbutton"
                                                                                       iconCls="icon-save">保存</a></div>
        </form>
