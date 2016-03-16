INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'ConsumerClientService', 'truetype', 'inner', NULL, '1'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'ConsumerClientService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC', 'ROUTER'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'JournalLogService', 'truetype', 'inner', NULL, '2');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'JournalLogService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','JOURNAL'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('PackService', 'truetype', 'inner', NULL,'5'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('PackService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PACKUNPACK');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('UnpackService', 'truetype', 'inner', NULL,'6'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('UnpackService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PACKUNPACK');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('MappingService', 'truetype', 'inner', NULL,'7'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('MappingService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','TRANSFORMER');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) VALUES ('ValidateService', 'truetype', 'inner', NULL, '8'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('ValidateService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','VALIDATE');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) VALUES ('EncryptService', 'truetype', 'inner', NULL, '9'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('EncryptService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','ENCRYPT');


INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) VALUES ('DecryptService', 'truetype', 'inner', NULL, '10'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('DecryptService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','ENCRYPT');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'FlowControlService', 'truetype', 'inner', NULL, '11');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'FlowControlService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','FLOW'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'FlowRecycleService', 'truetype', 'inner', NULL, '12');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'FlowRecycleService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','FLOW'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'ServiceIdentify', 'truetype', 'inner', NULL, '13');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'ServiceIdentify', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','IDENTIFY'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'SystemIdentify', 'truetype', 'inner', NULL, '14');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'SystemIdentify', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','SYSTEMIDENTIFY'); 
commit;
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES ( 
'NoReturnService', 'truetype', 'inner', NULL, '15');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES ( 
'NoReturnService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','EXFUNC');

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION,
ID ) VALUES (
'AccessCheckService', 'truetype', 'inner', NULL, '16');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,
LOCATION, ADAPTERTYPE ) VALUES (
'AccessCheckService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','FLOW'); 

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('FTPProcessService', 'truetype', 'inner', NULL,'28'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('FTPProcessService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PACKUNPACK'); 


INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
VALUES ('FtpForwardService', 'truetype', 'inner', NULL, '17');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,LOCATION, ADAPTERTYPE ) 
VALUES ('FtpForwardService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','FLOW'); 


INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
VALUES ('BaseIPChecker', 'truetype', 'inner', NULL, '18');
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME,LOCATION, ADAPTERTYPE ) 
VALUES ('BaseIPChecker', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','VALIDATE'); 

commit;

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
    VALUES ('StoreForwardServiceIdentifyService', 'truetype', 'inner', NULL, '29');
INSERT INTO SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) 
	VALUES ('StoreForwardServiceIdentifyService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','SYSTEMIDENTIFY'); 
	
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
    VALUES ('StoreForwardSystemIdentifyService', 'truetype', 'inner', NULL, '30');
INSERT INTO SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) 
	VALUES ('StoreForwardSystemIdentifyService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','SYSTEMIDENTIFY'); 
	
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
    VALUES ('StoreForwardIdentifyService', 'truetype', 'inner', NULL, '31');
INSERT INTO SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) 
	VALUES ('StoreForwardIdentifyService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','SYSTEMIDENTIFY'); 
	
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID ) 
    VALUES ('GeneratePkgService', 'truetype', 'inner', NULL, '32');
INSERT INTO SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) 
	VALUES ('GeneratePkgService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PACKUNPACK'); 
commit;

INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('ProtocolReadService', 'truetype', 'inner', NULL,'35'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('ProtocolReadService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PROTOCOL');
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('ProtocolWriteService', 'truetype', 'inner', NULL,'36'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('ProtocolWriteService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PROTOCOL');
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('FlowNoGenerateService', 'truetype', 'inner', NULL,'33'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('FlowNoGenerateService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PROTOCOL');
INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('ExceptionHandlerService', 'truetype', 'inner', NULL,'34'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('ExceptionHandlerService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','PROTOCOL');
commit;

INSERT INTO esb2_trans_ctrl(FlowType,ChannelId,ServiceId,FlowLevel,FlowLocation) 
		VALUES ('1','all','all',2,3); 


INSERT INTO ESB_USERS ( ID, USERNAME, PASSWORD, ROLES, LASTDATE, DESCRIPTION,
STATUS ) VALUES ( 
'1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 1, '1241090029812', '1240998905341'
, '1'); 

INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'TCPServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.tcp.TCPProtocolConfig', 'com.dc.esb.container.protocol.tcp.TCPProtocolProcessor'
, 'com.dc.esb.container.protocol.tcp.client.TCPClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'HTTPServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.http.HTTPProtocolConfig', 'com.dc.esb.container.protocol.http.HTTPProtocolProcessor'
, 'com.dc.esb.container.protocol.http.client.HTTPClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'JMSServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.jms.JMSProtocolConfig', 'com.dc.esb.container.protocol.jms.JMSProtocolProcessor'
, 'com.dc.esb.container.protocol.jms.client.JMSClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'EJBServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.ejb.EJBProtocolConfig', 'com.dc.esb.container.protocol.ejb.EJBProtocolProcessor'
, 'com.dc.esb.container.protocol.ejb.client.EJBClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'TCPChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.tcp.TCPProtocolConfig', 'com.dc.esb.container.protocol.tcp.TCPProtocolProcessor'
, 'com.dc.esb.container.protocol.tcp.server.TCPServerConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'HTTPChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.http.HTTPProtocolConfig', 'com.dc.esb.container.protocol.http.HTTPProtocolProcessor'
, 'com.dc.esb.container.protocol.http.server.HTTPServerConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'JMSChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.jms.JMSProtocolConfig', 'com.dc.esb.container.protocol.jms.JMSProtocolProcessor'
, 'com.dc.esb.container.protocol.jms.server.JMSServerConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'WSServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.ws.WSProtocolConfig'
, 'com.dc.esb.container.protocol.ws.WSProtocolProcessor', 'com.dc.esb.container.protocol.ws.client.WSClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'WSChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.ws.WSProtocolConfig'
, 'com.dc.esb.container.protocol.ws.WSProtocolProcessor', 'com.dc.esb.container.protocol.ws.server.WSServerConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,
INVOKERIMPL ) VALUES ( 
'EJBChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.ejb.EJBProtocolConfig', 'com.dc.esb.container.protocol.ejb.EJBProtocolProcessor', 'com.dc.esb.container.protocol.ejb.server.EJBServerConnector');
--CASE ID 2513 modified by lihangyu add Rest & CICS protocol
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'RESTServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.rest.RESTProtocolConfig', 'com.dc.esb.container.protocol.rest.RESTProtocolProcessor', 'com.dc.esb.container.protocol.rest.client.RESTClientConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'RESTChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.rest.RESTProtocolConfig', 'com.dc.esb.container.protocol.rest.RESTProtocolProcessor', 'com.dc.esb.container.protocol.rest.server.RESTServerConnector'); 
insert into bindtypedefine ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) values ( 'CICSConnector', 2, 'ip,1.1.1.1|port,11111|properties,user=CICSUSER;servername=hrbrun;password=;beginIndex=49;endIndex=57;separateIndex=58', 'com.dc.esb.container.protocol.cics.CICSProtocolConfig', 'com.dc.esb.container.protocol.cics.CICSProtocolProcessor', 'com.dc.esb.container.protocol.cics.client.CICSClientConnector');

--CASE ID 3171 modified by xucf add MQ & UDP protocol
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'IbmQChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.ibmq.IbmQProtocolConfig', 'com.dc.esb.container.protocol.ibmq.IbmQProtocolProcessor', 'com.dc.esb.container.protocol.ibmq.connector.IbmQLoopRSConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'IbmQServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.ibmq.IbmQProtocolConfig', 'com.dc.esb.container.protocol.ibmq.IbmQProtocolProcessor', 'com.dc.esb.container.protocol.ibmq.connector.IbmQSRConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'UDPChannelConnector', 1, NULL, 'com.dc.esb.container.protocol.udp.UDPProtocolConfig', 'com.dc.esb.container.protocol.udp.UDPProtocolProcessor', 'com.dc.esb.container.protocol.udp.connector.UDPServerConnector'); 
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'UDPServiceConnector', 2, NULL, 'com.dc.esb.container.protocol.udp.UDPProtocolConfig', 'com.dc.esb.container.protocol.udp.UDPProtocolProcessor', 'com.dc.esb.container.protocol.udp.connector.UDPClientConnector'); 
--CASE ID 5643 modified by lijh add MQ jdbc protocol
INSERT INTO BINDTYPEDEFINE ( TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL,INVOKERIMPL ) VALUES ( 'JDBCClient', 2, NULL, 'com.dc.esb.container.protocol.jdbc.JDBCProtocolConfig', 'com.dc.esb.container.protocol.jdbc.JDBCProtocolProcessor', 'com.dc.esb.container.protocol.jdbc.client.JDBCClientConnector'); 

--CASE ID 5634 modified by likun add ftp protocol
insert into bindtypedefine (TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL, INVOKERIMPL)
values ('FTPChannelConnector', 1, '', 'com.dc.esb.container.protocol.ftp.FTPProtocolConfig', 'com.dc.esb.container.protocol.ftp.FTPProtocolProcessor', 'com.dc.esb.container.protocol.ftp.server.FTPServerConnector');
insert into bindtypedefine (TYPENAME, CONTAINERTYPE, VMPATH, CONFIGIMPL, PARSERIMPL, INVOKERIMPL)
values ('FTPServiceConnector', 2, '', 'com.dc.esb.container.protocol.ftp.FTPProtocolConfig', 'com.dc.esb.container.protocol.ftp.FTPProtocolProcessor', 'com.dc.esb.container.protocol.ftp.client.FTPClientConnector');



commit;

insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('baseservices_id_sq', 1, 151);
insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('conf_id_sq', 1, 5000);
insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('deployment_sq', 1, 100);
insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('mappingconfig_sq', 1, 100);
insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('mappingid_sq', 1, 100);
insert into ESB_SEQUENCE (NAME, STEP, VALUE)
values ('template_id_sq', 1, 100);
commit;


insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('OTHER', '通用适配', 1);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('MESSAGE', '消息处理', 11);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('SYSTEMIDENTIFY', '系统识别', 2);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('IDENTIFY', '服务识别', 3);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('PACKUNPACK', '打包拆包', 4);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('ROUTER', '路由服务', 5);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('TRANSFORMER', '格式映射', 6);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('ENCRYPT', '加密解密', 7);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('FLOW', '流量控制', 8);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('JOURNAL', '流水日志', 9);
insert into ESB_ADAPTER_TYPE (TYPENAME, TYPEDESC, SEQUEUE)
values ('VALIDATE', '合法性校验', 10);
commit;


insert into ESB_ADAPTER_TEMPLATE (NAME, ADAPTERS, TYPE, REMARK,PROTOCOLADAPTER)
values ('default_service', 'default|false|OTHER', 'OUT', null,'0');
insert into ESB_ADAPTER_TEMPLATE (NAME, ADAPTERS, TYPE, REMARK,PROTOCOLADAPTER,IDENTIFYRULEID)
values ('default_channel', 'ServiceIdentify|false|IDENTIFY,ConsumerClientService|false|ROUTER', 'IN', null,'0','defaultIdentifyRule');
commit;

insert into DATAADAPTER_PARAM (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)
values ('default_channel', null, 'through', 'true', 'common', 'false');
insert into DATAADAPTER_PARAM (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)
values ('default_service', null, 'through', 'true', 'common', 'false');
commit;

INSERT INTO TEMPLATES ( ID, MARK, TEMPLATES ) VALUES ( 
'1', '1239865142499',EMPTY_CLOB()); 
UPDATE TEMPLATES SET TEMPLATES= '%3C%3Fxml+version%3D%221.0%22+encoding%3D%22UTF-8%22%3F%3E%3Cbeans+xmlns%3D%22http%3A%2F%2Fwww.springframework.org%2Fschema%2Fbeans%22%09xmlns%3Axsi%3D%22http%3A%2F%2Fwww.w3.org%2F2001%2FXMLSchema-instance%22%09xsi%3AschemaLocation%3D%22+++++++http%3A%2F%2Fwww.springframework.org%2Fschema%2Fbeans+http%3A%2F%2Fwww.springframework.org%2Fschema%2Fbeans%2Fspring-beans-2.5.xsd+++++++http%3A%2F%2Factivemq.apache.org%2Fcamel%2Fschema%2Fspring+http%3A%2F%2Factivemq.apache.org%2Fcamel%2Fschema%2Fspring%2Fcamel-spring.xsd%22%3E%09%3CcamelContext+id%3D%22context1%22%09%09xmlns%3D%22http%3A%2F%2Factivemq.apache.org%2Fcamel%2Fschema%2Fspring%22%3E%09%09%09%3C%2FcamelContext%3E%09%3Cbean+id%3D%22pipelineprocess%22+class%3D%22com.dc.esb.complexrouter.nmrouter.PiplineProcessor%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22multicastprocess%22+class%3D%22com.dc.esb.complexrouter.nmrouter.MulticastProcessor%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22end%22';
UPDATE TEMPLATES SET TEMPLATES= TEMPLATES || '+class%3D%22com.dc.esb.complexrouter.nmrouter.EndProcessor%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22stop%22+class%3D%22com.dc.esb.complexrouter.nmrouter.StopProcessor%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22nmd%22%09%09class%3D%22com.dc.esb.complexrouter.extcamelcomponent.directcomponent.TComponent%22+%2F%3E%09%3Cbean+id%3D%22sca%22%09%09class%3D%22com.dc.esb.complexrouter.extcamelcomponent.scacomponent.SCAComponent%22+%2F%3E%09%3Cbean+id%3D%22multicastAggregatorStrategy%22%09%09class%3D%22com.dc.esb.complexrouter.extcamelcomponent.processor.aggregate.MulticastAggregationStrategy%22+%2F%3E%09%3Cbean+id%3D%22mySingleThreadExcutor%22+class%3D%22com.dc.esb.complexrouter.extcamelcomponent.processor.MySingleThreadExecutor%22%2F%3E%09%3Cbean+id%3D%22noErrorHandler%22+class%3D%22com.dc.esb.complexrouter.bam.process.NoErrorHandlerBuilder%22%2F%3E%09%3Cbean+id%3D%22setVars%22+class%3D%22com.dc.esb.complexrouter.nmrouter.SetVariableProcessor%22+scope%3D%22prototype%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22setConstansVar%22+class%3D%22com.dc.esb.complexrouter.nmrouter.SetConstansVarProcessor%22+scope%3D%22prototype%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22setGlobalVar%22+class%3D%22com.dc.esb.complexrouter.nmrouter.SetGlobalVarProcessor%22++scope%3D%22prototype%22%3E%09%09%3Cproperty+name%3D%22process%22+value%3D%22true%22%2F%3E%09%3C%2Fbean%3E%09%3Cbean+id%3D%22vars%22+class%3D%22com.dc.esb.complexrouter.nmrouter.WholeVariable%22%3E%09%3C%2Fbean%3E%3C%2Fbeans%3E';