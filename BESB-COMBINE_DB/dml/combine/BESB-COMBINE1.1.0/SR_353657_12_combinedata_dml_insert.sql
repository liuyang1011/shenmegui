-- PROTOCOLBIND...
insert into PROTOCOLBIND (PROTOCOLID, BINDTYPE, BINDURI,REQUESTADAPTER,RESPONSEADAPTER)
values ('TcpChannel_4545', 'TCPChannelConnector', '<protocol.tcp protocolName="TCPChannelConnector" id="TcpChannel_4545" mode="synchronous" ioDirection="DataIn" side="server"><common connectMode="short" threadsCount="20" port="4545" /><request policy="length:unknown" encoding="UTF-8" readTimeout="10000" action="toString" /><response policy="length:unknown" encoding="UTF-8" /></protocol.tcp>','default_protocolAdapter_req_in','default_protocolAdapter_res_in');
insert into PROTOCOLBIND (PROTOCOLID, BINDTYPE, BINDURI)
values ('TCPService_10013', 'TCPServiceConnector', '<protocol.tcp protocolName="TCPServiceConnector" id="TCPService_10013" mode="synchronous" ioDirection="DataIn/DataOut" side="client"><common connectMode="short" threadsCount="1" host="localhost" port="10013" /><request policy="length:unknown" encoding="UTF-8" connectTimeout="10000" /><response policy="length:unknown" encoding="UTF-8" readTimeout="10000" action="toString" /></protocol.tcp>');
insert into PROTOCOLBIND (PROTOCOLID, BINDTYPE, BINDURI, REQUESTADAPTER, RESPONSEADAPTER, THREADPOOL)
values ('JMS_StoreForward', 'JMSChannelConnector', '<protocol.jms protocolName="JMSChannelConnector" id="JMS_StoreForward" mode="synchronous" ioDirection="DataIn/DataOut" side="server"><common /><request JNDIContextFactory="com.dc.messageclient.jndi.MessageServerInitialContextFactory" ProviderURL="tcp://127.0.0.1:4141" ConnectionFactory="ConnectionFactory" QueueName="in.out" SessionCount="1" /><response JNDIContextFactory="com.dc.messageclient.jndi.MessageServerInitialContextFactory" ProviderURL="tcp://127.0.0.1:4141" ConnectionFactory="ConnectionFactory" QueueName="in.in" SessionCount="1" /><advanced /></protocol.jms>', 'default_protocolAdapter_req_in', 'default_protocolAdapter_res_in', '');
commit;

--SERVICEINFO...
insert into SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, DESCRIPTION, ADAPTERTYPE, ISCREATE)
values ('S_business_001', 'BUSS', 'S_business_001', 'false', null, 'local_out', null, null, 'true');
insert into SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, DESCRIPTION, ADAPTERTYPE, ISCREATE)
values ('Channel_1', 'CHANNEL', null, null, null, 'local_in', null, null, null);
insert into SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, DESCRIPTION, ADAPTERTYPE, ISCREATE)
values ('StoreForwardChannel', 'CHANNEL', null, null, null, 'local_in', null, null, null);
insert into SERVICEINFO (SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, DESCRIPTION, ADAPTERTYPE, ISCREATE)
values ('StoreForwardService', 'BUSS', 'StoreForwardService', 'false', null, 'local_out', null, null, 'true');
commit;

--deployments...
insert into deployments(id,location,name,version) values('1','local_out','S_business_001','0');

--SERVICES...
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('S_business_001', '4cc95abd8b9cba4705f290b39dee3d6a', '70022232c41f240289cac75d5b3e3884', 'SERVICE', 1, '2', 'null', 'local_out');
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('Channel_1', 'a31578d478cb9d20de4c67a8aa75f975', '70022232c41f240289cac75d5b3e3884', 'CHANNEL', 1, '2', 'null', 'local_in');
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('StoreForwardService', '4cc95abd8b9cba4705f290b39dee3d6a', '70022232c41f240289cac75d5b3e3884', 'SERVICE', 1, '2', 'null', 'local_out');
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('StoreForwardChannel', 'a31578d478cb9d20de4c67a8aa75f975', '70022232c41f240289cac75d5b3e3884', 'CHANNEL', 1, '2', 'null', 'local_in');
commit;

--BUSSSERVICES...
insert into BUSSSERVICES (SERVICEID, CATEGORY, METHODNAME, ISARG, DESCRIPTION)
values ('S_business_001', null, 'null', 'false', null);
insert into BUSSSERVICES (SERVICEID, CATEGORY, METHODNAME, ISARG, DESCRIPTION)
values ('StoreForwardService', null, 'null', 'false', null);
commit;

--DATAADAPTER...
insert into DATAADAPTER (DATAADAPTERID, DATAADAPTER, LOCATION, ADAPTERTYPE)values ('S_business_001', 'default_service', 'local_out', 'OUT');
insert into DATAADAPTER (DATAADAPTERID, DATAADAPTER, LOCATION, ADAPTERTYPE)values ('Channel_1', 'default_channel', 'local_in', 'IN');
insert into DATAADAPTER (DATAADAPTERID, DATAADAPTER, LOCATION, ADAPTERTYPE)values ('StoreForwardChannel', 'adaptor_forward_channel', 'local_in', 'IN');
insert into DATAADAPTER (DATAADAPTERID, DATAADAPTER, LOCATION, ADAPTERTYPE)values ('StoreForwardService', 'default_service', 'local_out', 'OUT');

insert into dataadapter_param (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)values ('adaptor_forward_channel', '', 'through', 'true', 'common', 'false');
insert into dataadapter_param (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)values ('adaptor_forward_service', '', 'through', 'true', 'common', 'false');
insert into dataadapter_param (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)values ('adaptor_store_channel', '', 'through', 'true', 'common', 'false');
insert into dataadapter_param (DATAADAPTERID, LOCATION, PARAMNAME, PARAMVALUE, PARAMDESC, ISGROUP)values ('default_channel', '', 'through', 'true', 'common', 'false');

insert into esb_adapter_template (NAME, ADAPTERS, TYPE, REMARK, PROTOCOLADAPTER) values ('default_protocolAdapter_req_in', 'ProtocolReadService|true|PROTOCOL,FlowNoGenerateService|false|PROTOCOL', 'IN', '', '1');
insert into esb_adapter_template (NAME, ADAPTERS, TYPE, REMARK, PROTOCOLADAPTER) values ('default_protocolAdapter_res_in', 'ExceptionHandlerService|true|PROTOCOL,ProtocolWriteService|true|PROTOCOL', 'IN', '', '1');
insert into esb_adapter_template (NAME, ADAPTERS, TYPE, REMARK, PROTOCOLADAPTER) values ('adaptor_forward_channel', 'StoreForwardServiceIdentifyService|false|SYSTEMIDENTIFY,ConsumerClientService|false|ROUTER', 'IN', '', '0');
insert into esb_adapter_template (NAME, ADAPTERS, TYPE, REMARK, PROTOCOLADAPTER) values ('adaptor_forward_service', 'StoreForwardSystemIdentifyService|false|SYSTEMIDENTIFY,default|false|OTHER', 'OUT', '', '0');
insert into esb_adapter_template (NAME, ADAPTERS, TYPE, REMARK, PROTOCOLADAPTER) values ('adaptor_store_channel', 'ServiceIdentify|false|IDENTIFY,StoreForwardIdentifyService|false|SYSTEMIDENTIFY,ConsumerClientService|false|ROUTER,GeneratePkgService|false|PACKUNPACK', 'IN', '', '0');



commit;


--BINDMAP...
insert into BINDMAP (SERVICEID, STYPE, LOCATION, VERSION, PROTOCOLID, MAPTYPE)
values ('S_business_001', 'SERVICE', 'local_out', '0', 'TCPService_10013', 'request');
insert into BINDMAP (SERVICEID, STYPE, LOCATION, VERSION, PROTOCOLID, MAPTYPE)
values ('Channel_1', 'CHANNEL', 'local_in', null, 'TcpChannel_4545', 'request');
insert into BINDMAP (SERVICEID, STYPE, LOCATION, VERSION, PROTOCOLID, MAPTYPE)
values ('StoreForwardChannel', 'CHANNEL', 'local_in', null, 'JMS_StoreForward', 'request');
commit;

insert into ESB_F5_CONTROL (CLUSTERNAME, STATUS) values ('cluster1', 'running');
commit;



---FlowCtrl...
insert into ESB2_FLOW_CTRL_DIMENSION (DIMENSION, PRIORITY, TYPE,USED) values ('realChannel', '1', 'default','true');
insert into ESB2_FLOW_CTRL_DIMENSION (DIMENSION, PRIORITY, TYPE,USED) values ('immuneChannelid', '2', 'default','true');
insert into ESB2_FLOW_CTRL_DIMENSION (DIMENSION, PRIORITY, TYPE,USED) values ('immuneServiceid', '3', 'default','true');
commit;

---3?ˉ?±?????
insert into ESB_IDENDIFY_RULES (NAME, TYPE, RULENAME, RULETYPE, MESSAGETYPE, IMPCLASS, EXPRESSION, NAMESPACE, MAPPINGTABLE, MERGERULE, SERVICEID)
values ('defaultIdentifyRule', 'IN', 'default', 'static', 'xml', null, null, null, null, null, 'S_business_001');
commit;