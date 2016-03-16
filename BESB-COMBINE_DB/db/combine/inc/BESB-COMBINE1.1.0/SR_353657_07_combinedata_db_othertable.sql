	declare
		var_IpPort VARCHAR2(30):='http://localhost:9080';
	begin	
	
	P_ADD_GNMK('ESB控制台~配置管理~渠道管理','esbconsole.configMng.channelMng.channelPrioMng','渠道系统有效期管理','../esb/esbconsole/serviceperiod/first.iface?servicetype=true','090000');
	
	p_del_gnmk('esbconsole.configMng.flowCtlMng.flowMonitor');
	p_del_gnmk('systemmanage.security.resource.limitation');
	commit;
	
	P_ADD_ML('ESB控制台~配置管理','服务测试');
	P_ADD_GNMK('ESB控制台~配置管理~服务测试','esbconsole.configMng.servicetest.servProvider','服务提供方','../esbtest/provider.jsp','090000');
	P_ADD_GNMK('ESB控制台~配置管理~服务测试','esbconsole.configMng.servicetest.servConsumer','服务消费方','../esbtest/consumer.jsp','090000');
	commit;
	
	
	P_ADD_ML('ESB控制台','消息服务器');
	P_ADD_ML('ESB控制台~消息服务器','MOM队列管理');
	P_ADD_GNMK('ESB控制台~消息服务器~MOM队列管理','esbconsole.msgServerMng.queueMng.queueMng','MOM队列管理','../esb/esbconsole/mom/queueMng/index.iface','090000');
	commit;

	P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcFaultMan','服务故障管理','../esb/esbconsole/monitor/flowControl/faultmaintenance/first.iface?servicetype=true','090000');
  commit;
  
	p_del_gnmk('esbconsole.configMng.flowCtlMng.faultMaintenance');
  P_ADD_GNMK('ESB控制台~配置管理~渠道管理','esbconsole.configMng.flowCtlMng.faultMaintenance','渠道系统故障管理','../esb/esbconsole/monitor/flowControl/faultmaintenance/first.iface','090000');
	commit;

	p_del_gnmk('esbconsole.configMng.flowMng.flowConfig');
  P_ADD_GNMK('ESB控制台~配置管理~流水管理','esbconsole.configMng.flowMng.flowConfig','流水策略配置','../esb/esbconsole/monitor/trans/transconfig/first.iface','090000');
  
  p_del_gnmk('esbconsole.configMng.flowMng.flowNoConfig');
  P_ADD_GNMK('ESB控制台~配置管理~流水管理','esbconsole.configMng.flowMng.flowNoConfig','流水号配置','../esb/esbconsole/translogflowno/main.iface','090000');
  
  p_del_gnmk('esbconsole.configMng.flowMng.flowQuery');
  P_ADD_GNMK('ESB控制台~配置管理~流水管理','esbconsole.configMng.flowMng.flowQuery','流水信息查询','../esb/esbconsole/monitor/trans/transquery/first.iface','090000');
	commit;
	
  p_del_gnmk('esbconsole.configMng.svcMng.svcSystemMng');
  P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcSystemMng','服务系统管理','../esb/esbconsole/servicesystem/first.iface','090000');

  p_del_gnmk('esbconsole.configMng.svcMng.svcPeriodMng');
  P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcPeriodMng','服务有效期管理','../esb/esbconsole/serviceperiod/first.iface','090000');

  p_del_gnmk('esbconsole.configMng.svcMng.svcPeriodMng');
  P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcPeriodMng','服务有效期管理','../esb/esbconsole/serviceperiod/first.iface','090000');
  
  p_del_gnmk('esbconsole.configMng.svcMng.svcFaultMan');
  P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcFaultMan','服务故障管理','../esb/esbconsole/monitor/flowControl/faultmaintenance/first.iface?servicetype=true','090000');
  commit;
  
	update qx_gnmk_tree set jd_order=2 where jd_dm='esbconsole002001';
	update qx_gnmk_tree set jd_order=1 where jd_dm='esbconsole002002';

	update qx_gnmk_tree set jd_order=2 where jd_dm='esbconsole002002001';
	update qx_gnmk_tree set jd_order=1 where jd_dm='esbconsole002002002';
	commit;
		
	update qx_system  set sessionkeep ='N' where systemname='系统管理';
	commit;

	UPDATE QX_GNMK SET CFDK='N';
	commit;
	
    p_del_gnmk('esbconsole.configMng.rescMng.msgPrcMng');
	commit;    
	
   
	
    p_del_gnmk('esbconsole.configMng.svcMng.svcControlMng');
	P_ADD_GNMK('ESB控制台~配置管理~服务管理','esbconsole.configMng.svcMng.svcControlMng','服务访问控制','../esb/esbconsole/servicecontrol/first.iface','090000');
	commit;
	
	
	P_ADD_ML('ESB控制台~配置管理','数据源管理');
	P_ADD_GNMK('ESB控制台~配置管理~数据源管理','esbconsole.configMng.datasource.datasource','数据源管理','../esb/esbconsole/dataSourceMng/first.iface','090000');
	P_ADD_GNMK('ESB控制台~配置管理~数据源管理','esbconsole.configMng.datasource.datasource2','流水数据源重置','../esb/esbconsole/dataSourceMng/reset.iface','090000');
        commit;
	
    P_ADD_ML('ESB控制台~配置管理','多路管理');
	P_ADD_GNMK('ESB控制台~配置管理~多路管理','esbconsole.configMng.clusterMng.clusterResult','多路通知查询','../esb/esbconsole/clusternotify/first.iface','090000');
	commit;

	P_ADD_ML('ESB控制台~配置管理','渠道管理');
	P_ADD_GNMK('ESB控制台~配置管理~渠道管理','esbconsole.configMng.channelMng.realchannel','渠道系统','../esb/esbconsole/realchannel/first.iface?ischannel=true','090000');
	
	p_del_gnmk('esbconsole.configMng.rescMng.formatMng');
	p_del_gnmk('esbconsole.configMng.routeMng.ptServiceMng');
	commit;
	
	p_del_gnmk('esbconsole.configMng.nodeMng.f5controlMng');
	P_ADD_GNMK('ESB控制台~配置管理~多路管理','esbconsole.configMng.nodeMng.f5controlMng','集群分路状态管控','../esb/esbconsole/f5controlMng/first.iface','090000');
	commit;
		
	p_del_gnmk('esbconsole.configMng.flowCtrl.multiFlowCtrl');
	p_del_gnmk('esbconsole.configMng.flowCtrl.multiLocalFlowCtrl');
  	P_ADD_GNMK('ESB控制台~配置管理~流量控制管理','esbconsole.configMng.flowCtrl.multiFlowCtrl','流控维度管理','../esb/esbconsole/monitor/flowControl/multiFlowCtrl/first.iface?servicetype=true','090000');
	P_ADD_GNMK('ESB控制台~配置管理~流量控制管理','esbconsole.configMng.flowCtrl.multiLocalFlowCtrl','本地流控','../esb/esbconsole/monitor/flowControl/multiLocalFlowCtrl/first.iface?servicetype=true','090000');
  	commit;
  	
	end;
/