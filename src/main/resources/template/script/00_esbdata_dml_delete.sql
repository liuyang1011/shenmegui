-- protocol
delete from BINDMAP where PROTOCOLID in('');
delete from PROTOCOLBIND where PROTOCOLID in('');
--protocoltype
delete from BINDTYPEDEFINE where TYPENAME='';
-- adapter
delete from ESB_ADAPTER_TEMPLATE where name in('');
-- serviceSystem
delete from SERVICESYSTEM where NAME='';
-- channel
delete from DATAADAPTER where DATAADAPTERID = '';
delete from BINDMAP where SERVICEID = '';
delete from SERVICES where NAME = '';
delete from SERVICEINFO where SERVICEID= '';
-- service
delete from DEPLOYMENTS where NAME in('');
delete from SERVICESYSTEMMAP where SERVICEID in('');
delete from DATAADAPTER where DATAADAPTERID in('');
delete from BINDMAP where SERVICEID in('');
delete from SERVICEINFO where SERVICEID in('');
delete from BUSSSERVICES where SERVICEID in('');
delete from SERVICES where NAME in('');
-- base service
delete from BASESERVICES where SERVICEID in('');
-- F5ctrl
delete from  ESB_F5_CONTROL;
-- flowctrl
delete from ESB2_IN_FLOW_CTRL;
delete from ESB2_FLOW_CTRL;