<%
  String nodeId=request.getParameter("nodeId");
%>
<input type="hidden" value="<%=nodeId%>" id="nodeId"/>
        <fieldset style="height: 180px">
          <legend>节点依赖信息</legend>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>
                <NOBR>ESB调用方式</NOBR>
              </th>
              <td><select name="esbAccessPattern" id="esbAccessPattern"panelHeight="auto" style="width:180px" editable="false"/>
              </td>
            </tr>
            <tr>
              <th>
                <NOBR>条件位</NOBR>
              </th>
              <td><input class="easyui-textbox" type="text" name="condition" id="condition" style="width:180px"/></td>
            </tr>
            <tr>
              <th>
                <NOBR>条件信息</NOBR>
              </th>
              <td><input class="easyui-textbox" type="text" name="connectionDesc" id="connectionDesc" style="width:180px"/></td>
            </tr>
            <tr>
              <th>
                <NOBR>组合位</NOBR>
              </th>
              <td><input class="easyui-textbox" type="text" name="connectionDesc" id="linkCondition" style="width:180px"/></td>
            </tr>
            <tr>
              <th>
                <a href="javascript:;" id="saveNodeInfoBtn" class="easyui-linkbutton" iconCls="icon-save"
                   style="margin-left:1em">保存</a>
              </th>
            </tr>
          </table>
        </fieldset>

      <script type="text/javascript">

        $('#esbAccessPattern').combobox({
          valueField: 'value',
          textField: 'label',
          data: [{
            label: '0-穿透模式',
            value: '0'
          },{
            label: '1-代理模式',
            value: '1'
          },{
            label: '2-寻址模式',
            value: '2'
          },{
            label: '4-组合模式',
            value: '4'
          }]
        });
        $(function () {
          var nodeId=$("#nodeId").val();
          $("#saveNodeInfoBtn").click(function () {
            var invokeId=nodeId;
            var nodeProperties = [];
            var esbAccessPattern = {
              "invokeId": invokeId,
              "propertyName": "esbAccessPattern",
              "propertyValue": $('#esbAccessPattern').combobox('getValue')
            };
            var condition = {
              "invokeId": invokeId,
              "propertyName": "condition",
              "propertyValue": $("#condition").val()
            };
            var conditionDesc = {
              "invokeId": invokeId,
              "propertyName": "connectionDesc",
              "propertyValue": $("#connectionDesc").val()
            };
            var linkCondition = {
              "invokeId": invokeId,
              "propertyName": "linkCondition",
              "propertyValue": $('#linkCondition').val()
            };
            nodeProperties.push(esbAccessPattern);
            nodeProperties.push(condition);
            nodeProperties.push(conditionDesc);
            nodeProperties.push(linkCondition);
            var flag=confirm("确定修改吗？");
            if(!flag){
              return;
            }
            serviceLinkManager.saveNodeProperties(nodeProperties, function () {
              $("#systemDlg").window('close');
              $("#invokeLinkeTable").datagrid("reload");
              alert("修改成功！");
            });
          });

          $.ajax({
            type: "GET",
            async: true,
            url: "/serviceLink/nodeInfoProperties/" + nodeId,
            success: function (data) {
              var esbAccessPattern =data.esbAccessPattern;
              if(esbAccessPattern != ""){
                $('#esbAccessPattern').combobox('setValue',esbAccessPattern);
              }
              $("#condition").textbox('setValue',data.condition);
              $("#connectionDesc").textbox('setValue',data.connectionDesc);
              $("#linkCondition").textbox('setValue',data.linkCondition);
            }
          });

        });
        </script>
