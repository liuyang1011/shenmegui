INSERT INTO BASESERVICES ( SERVICEID, PARAMNAME, PARAMVALUE, DESCRIPTION, ID) VALUES ('PrintCtxService', 'truetype', 'inner', NULL,'37'); 
INSERT INTO SERVICEINFO ( SERVICEID, SERVICETYPE, CONTRIBUTION, PREPARED, GROUPNAME, LOCATION, ADAPTERTYPE ) VALUES ('PrintCtxService', 'BASE', NULL, NULL, NULL, 'ESB_PUBLIC_SVC','JOURNAL');

--PADDRESS...
insert into PADDRESS (ADDRESSID, URL, QUEUENAME, LOCATIONDEPEND, LOGICADDRESS)
values ('6c1a4a866e5cb192e56a7ca028acbff3', 'com.dc.messageclient.jndi.MessageServerInitialContextFactory#tcp://127.0.0.1:4141#ConnectionFactory', 'default.in', 'false', 'true');
insert into PADDRESS (ADDRESSID, URL, QUEUENAME, LOCATIONDEPEND, LOGICADDRESS)
values ('70022232c41f240289cac75d5b3e3884', 'com.dc.messageclient.jndi.MessageServerInitialContextFactory#tcp://127.0.0.1:4141#ConnectionFactory', 'default.out', 'false', 'true');
insert into PADDRESS (ADDRESSID, URL, QUEUENAME, LOCATIONDEPEND, LOGICADDRESS)
values ('a31578d478cb9d20de4c67a8aa75f975', 'com.dc.messageclient.jndi.MessageServerInitialContextFactory#tcp://127.0.0.1:4141#ConnectionFactory', 'consumer.in', 'true', 'true');
insert into PADDRESS (ADDRESSID, URL, QUEUENAME, LOCATIONDEPEND, LOGICADDRESS)
values ('4cc95abd8b9cba4705f290b39dee3d6a', 'com.dc.messageclient.jndi.MessageServerInitialContextFactory#tcp://127.0.0.1:4141#ConnectionFactory', 'provider.in', 'false', 'true');
commit;

--CLIENTLOCATION...
insert into CLIENTLOCATION (LOCATION, OUTADDRESSID, INADDRESSID, SESSIONCOUNT, DELIVERYMODE, ID, NODEID)
values ('local_router', '70022232c41f240289cac75d5b3e3884', '6c1a4a866e5cb192e56a7ca028acbff3', 1, '2', null, null);
insert into CLIENTLOCATION (LOCATION, OUTADDRESSID, INADDRESSID, SESSIONCOUNT, DELIVERYMODE, ID, NODEID)
values ('local_in', '70022232c41f240289cac75d5b3e3884', 'a31578d478cb9d20de4c67a8aa75f975', 1, '2', null, null);
insert into CLIENTLOCATION (LOCATION, OUTADDRESSID, INADDRESSID, SESSIONCOUNT, DELIVERYMODE, ID, NODEID)
values ('local_out', '70022232c41f240289cac75d5b3e3884', '4cc95abd8b9cba4705f290b39dee3d6a', 1, '2', null, null);
insert into CLIENTLOCATION (LOCATION, OUTADDRESSID, INADDRESSID, SESSIONCOUNT, DELIVERYMODE, ID, NODEID)
values ('journalSrv', null, null, 1, 'null', null, null);
commit;

--CONFIGURATIONS...
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2394, 'local_in', 'location', 'local_in', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2395, 'local_in', 'mainDataIP', '127.0.0.1', '主流控服务器ip', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2396, 'local_in', 'esb.authentication.data.port', '1001', '安全认证应用服务的的数据处理端口', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2397, 'local_in', 'esb.container.context.poolsize', '100', '容器上下文池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2398, 'local_in', 'java.naming.security.authentication', 'simple', 'MQ身份认证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2399, 'local_in', 'sleepTime', '5000', '重连时间间隔', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2400, 'local_in', 'encrypt', 'false', '安全模块开关', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2401, 'local_in', 'esb.journallog.impl', 'com.dc.esb.journallog.impls.SendjournalLog', '日志流水默认实现', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2402, 'local_in', 'message_buffer_size', '10', '消息缓冲区最大深度', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2403, 'local_in', 'esb.container.metadata.runtime', 'true', '是否使用元数据', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2404, 'local_in', 'esb.authentication.data.maxconnectnum', '50', '数据传输时的最大连接数', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2405, 'local_in', 'esb.container.timeout', '20000', '容器调用的超时时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2406, 'local_in', 'inner.sessioncount', '1', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2407, 'local_in', 'esb.translogflowno', 'false', '是否允许自定义流水号', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2408, 'local_in', 'esb.authentication.user.ssl', 'true', '是否使用ssl进行渠道认证', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2409, 'local_in', 'esb.container.metadata.load', 'true', '是否在启动时加载元数据(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2410, 'local_in', 'interval_time', '10000', '检测连接是否中断的间隔时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2411, 'local_in', 'esb.container.pseudo.patrolsize', '60', '伪异步超时巡查数组大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2412, 'local_in', 'localName', 'L1', '流控客户端名称', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2413, 'local_in', 'esb.container', 'com.dc.esb.container.deployment.impl.lsc.LscServiceFactory', '容器实现工厂', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2414, 'local_in', 'esb.container.error.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultErrorInterceptor', '错误处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2415, 'local_in', 'com.dcfs.messageReceiver.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageReceiverImpl', 'Level0消息监听实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2416, 'local_in', 'subDataPort', '8909', '备份流控服务器port', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2417, 'local_in', 'node.type', '1', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2418, 'local_in', 'esb.authentication.authport', '1000', '渠道认证端口', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2419, 'local_in', 'esb.container.useAdatporValidation', 'false', '是否开启服务适配校验功能(true/false)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2420, 'local_in', 'esb.authentication.ssl.needclientauth', 'false', '是否使用ssl双向认证', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2421, 'local_in', 'data.store.type', 'file', '元数据加载方式(file/db)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2422, 'local_in', 'java.naming.security.principal', 'mqm', 'MQ用户名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2423, 'local_in', 'use_sub', 'false', '是否使用备份流控服务器', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2424, 'local_in', 'esb.authentication.cert.clientpassword', 'password', '渠道ssl认证客户端密钥存储文件的密码', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2425, 'local_in', 'tokenCallbackTime', '5000', '令牌回收时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2426, 'local_in', 'esb.flowcontrol.impl', 'com.dc.esb.flowcontrol.impl.client.ClientDispatch', '默认流控调度实现', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2427, 'local_in', 'inner.node.in', 'a31578d478cb9d20de4c67a8aa75f975', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2428, 'local_in', 'esb.container.sdo.factory', 'com.dc.esb.container.databinding.channel.ChannelSDOFactoryImpl', '元数据工厂实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2429, 'local_in', 'inner.node.ip', '127.0.0.1', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2430, 'local_in', 'inner.node.out', '70022232c41f240289cac75d5b3e3884', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2431, 'local_in', 'thread_pool_size', '10', '默认线程池大小', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2432, 'local_in', 'esb.container.after.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultAfterInterceptor', '后处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2433, 'local_in', 'esb.container.tcpserver.usenio', 'false', 'TCP接入是否使用新IO', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2434, 'local_in', 'esb.authentication.cert.trustfilename', 'tclient.keystore', '渠道ssl认证客户端信任证书的存储文件', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2435, 'local_in', 'esb.authentication.user.password', 'password', '渠道ssl认证客户使用basic认证时的用户密码', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2436, 'local_in', 'esb.authentication.client', 'com.dc.esb.security.EsbAuthClient', '安全认证实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2437, 'local_in', 'esb.container.journal.poolsize', '20', '发送流水线程池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2438, 'local_in', 'subDataIP', '127.0.0.1', '备份流控服务器ip', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2439, 'local_in', 'esb.authentication.cert.clientfilename', 'kclient.keystore', '渠道ssl认证客户端密钥存储文件', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2440, 'local_in', 'java.naming.security.credentials', 'mqm', 'JDNI访问资证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2441, 'local_in', 'esb.logconfig', 'false', '日志功能是否开启', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2442, 'local_in', 'esb.container.before.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultBeforeInterceptor', '前处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2443, 'local_in', 'inner.node.port', '22228', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2444, 'local_in', 'esb.authentication.ssl.authport', '999', '渠道ssl认证端口', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2445, 'local_in', 'mainDataPort', '8908', '主流控服务器port', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2446, 'local_in', 'esb.container.self.detect', 'false', '容器自测功能开关(true/false)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2447, 'local_in', 'esb.container.faultservice', 'true', '是否参与故障隔离', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2448, 'local_in', 'socket_read_timeout', '5000', 'socket读超时时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2393, 'local_in', 'com.dcfs.messageSender.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageSenderImpl', 'Level0消息发送实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (841, 'journalSrv', 'max_try_loopcount', '10', '获取数据库连接池资源失败后最大重试次数', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (842, 'journalSrv', 'inner.node.port', '22226', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (843, 'journalSrv', 'com.dcfs.esb.journallog.savetype', 'DB', '保存类型', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (844, 'journalSrv', 'esb.journal.batchinterval', '20000', 'null', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (845, 'journalSrv', 'inner.node.in', null, null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (847, 'journalSrv', 'com.dcfs.esb.journallog.savepath', 'esbLog', '日志路径', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (848, 'journalSrv', 'com.dcfs.messageReceiver.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageReceiverImpl', 'Level0消息监听实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (849, 'journalSrv', 'esb.journal.taskinterval', '60000', '流水监控任务启动间隔时间(毫秒)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (850, 'journalSrv', 'inner.node.ip', '127.0.0.1', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (851, 'journalSrv', 'location', 'journalSrv', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (852, 'journalSrv', 'inner.deliverymode', 'null', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (853, 'journalSrv', 'SLEEP_TIME', '20000', '监听器最大等待时间(ms)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (854, 'journalSrv', 'inner.node.nodeid', 'null', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (855, 'journalSrv', 'com.dcfs.esb.journallog.maxfilesize', '10k', '日志大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (856, 'journalSrv', 'java.naming.security.principal', 'mqm', 'MQ用户名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (857, 'journalSrv', 'loopwork_period', '1000', '获取数据库连接池资源失败后重试间隔时间(毫秒)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (858, 'journalSrv', 'com.dcfs.esb.client.service.invoker', 'com.dc.esb.monitor.impls.server.MockProviderHandler', '消息分发处理器', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (860, 'journalSrv', 'thread_pool_size', '10', '默认线程池大小', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (861, 'journalSrv', 'loopwork_delay', '0', '错误轮询任务启动延迟时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (862, 'journalSrv', 'com.dcfs.esb.journallog.filename', 'esb.log', '日志文件名称', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (863, 'journalSrv', 'java.naming.security.credentials', 'mqm', 'JDNI访问资证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (864, 'journalSrv', 'inner.sessioncount', '1', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (865, 'journalSrv', 'esb.journal.timeoutinterval', '60000', '流水任务超时时间(毫秒)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (866, 'journalSrv', 'esb.journal.usefilebuffer', 'true', '是否写buffer，默认不写buffer', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (868, 'journalSrv', 'encrypt', 'false', '安全模块开关', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (870, 'journalSrv', 'java.naming.security.authentication', 'simple', 'MQ身份认证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (871, 'journalSrv', 'JournallogThreadNumber', '20', 'null', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (872, 'journalSrv', 'node.type', '16', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (873, 'journalSrv', 'profix', 'com.ibm.ws.naming', 'WAS的消息服务对象JNDI前缀', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (874, 'journalSrv', 'inner.node.out', null, null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (875, 'journalSrv', 'message_buffer_size', '10', '消息缓冲区最大深度', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (876, 'journalSrv', 'databaseconnectionpoolsize', '250', '最大数据库连接池', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (877, 'journalSrv', 'com.dcfs.esb.client.service.name', 'journalSrv', '节点默认服务名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (879, 'journalSrv', 'com.dcfs.messageSender.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageSenderImpl', 'Level0消息发送实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (880, 'journalSrv', 'esb.journal.batchmaxquantity', '1', 'null', 'false');
commit;

insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2449, 'local_in', 'esb.container.pseudo.stacksize', '10000', '伪异步上下文缓存堆栈大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2450, 'local_in', 'esb.authentication.cert.trustpassword', 'password', '渠道ssl认证客户端信任证书的存储文件的密码', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2451, 'local_in', 'inner.node.nodeid', 'null', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2452, 'local_in', 'inner.deliverymode', '2', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2453, 'local_in', 'com.dcfs.esb.client.service.name', 'local_in', '节点默认服务名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2454, 'local_in', 'esb.authentication.ssl.maxconnectnum', '50', '渠道ssl认证模式时的最大连接数', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2455, 'local_in', 'esb.authentication.enabled', 'false', '是否进行安全认证', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2456, 'local_in', 'profix', 'com.ibm.ws.naming', 'WAS的消息服务对象JNDI前缀', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2457, 'local_in', 'esb.container.token', 'false', '是否启用流量控制(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2458, 'local_in', 'esb.container.frame.poolsize', '20', '容器Frame的线程池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2459, 'local_in', 'load_dict_from_db', 'false', '是否从数据库加载数据字典', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2460, 'local_in', 'esb.container.translog', 'true,true', '是否启用日志(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2461, 'local_in', 'esb.authentication.maxconnectnum', '50', '安全认证模式时的最大连接数', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2462, 'local_in', 'esb.container.serviceperiod', 'false', '服务有效期功能是否开启', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2463, 'local_in', 'com.dcfs.esb.client.service.invoker', 'com.dc.esb.container.adaptor.ESBConsumerListener', '消息分发处理器', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2464, 'local_in', 'tcp_long_connect_pool_size', '20', '流控连接池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2465, 'local_in', 'esb.authentication.ip', '127.0.0.1', '安全认证应用服务的IP', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2468, 'local_out', 'esb.container.sdo.factory', 'com.dc.esb.container.databinding.channel.ChannelSDOFactoryImpl', '元数据工厂实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2469, 'local_out', 'inner.node.ip', '127.0.0.1', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2470, 'local_out', 'inner.node.out', '70022232c41f240289cac75d5b3e3884', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2471, 'local_out', 'thread_pool_size', '10', '默认线程池大小', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2472, 'local_out', 'com.dcfs.messageSender.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageSenderImpl', 'Level0消息发送实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2473, 'local_out', 'esb.container.after.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultAfterInterceptor', '后处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2474, 'local_out', 'location', 'local_out', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2475, 'local_out', 'default.busi.impl', 'com.dc.esb.container.service.DefaultBusinessService', '默认业务服务实现类', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2476, 'local_out', 'esb.container.journal.poolsize', '20', '发送流水线程池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2466, 'local_in', 'SLEEP_TIME', '20000', '监听器最大等待时间(ms)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2477, 'local_out', 'esb.container.context.poolsize', '100', '容器上下文池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2478, 'local_out', 'java.naming.security.authentication', 'simple', 'MQ身份认证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2479, 'local_out', 'java.naming.security.credentials', 'mqm', 'JDNI访问资证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2480, 'local_out', 'esb.container.before.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultBeforeInterceptor', '前处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2481, 'local_out', 'esb.logconfig', 'false', '日志功能是否开启', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2482, 'local_out', 'encrypt', 'false', '安全模块开关', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2483, 'local_out', 'esb.journallog.impl', 'com.dc.esb.journallog.impls.SendjournalLog', '日志流水默认实现', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2484, 'local_out', 'inner.node.port', '22228', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2485, 'local_out', 'esb.container.metadata.runtime', 'true', '是否使用元数据', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2486, 'local_out', 'message_buffer_size', '10', '消息缓冲区最大深度', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2487, 'local_out', 'esb.container.timeout', '20000', '容器调用的超时时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2488, 'local_out', 'esb.container.self.detect', 'false', '容器自测功能开关(true/false)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2489, 'local_out', 'inner.sessioncount', '1', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2490, 'local_out', 'esb.translogflowno', 'false', '是否允许自定义流水号', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2491, 'local_out', 'esb.container.metadata.load', 'true', '是否在启动时加载元数据(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2492, 'local_out', 'esb.container.pseudo.patrolsize', '60', '伪异步超时巡查数组大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2493, 'local_out', 'esb.container.pseudo.stacksize', '10000', '伪异步上下文缓存堆栈大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2494, 'local_out', 'esb.container', 'com.dc.esb.container.deployment.impl.lsc.LscServiceFactory', '容器实现工厂', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2495, 'local_out', 'inner.node.nodeid', 'null', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2496, 'local_out', 'inner.deliverymode', '2', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2497, 'local_out', 'esb.container.error.interceptor', 'com.dc.esb.container.adaptor.frame.DefaultErrorInterceptor', '错误处理拦截器实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2498, 'local_out', 'ReversalServiceName', 'reversal', '此容器部署的冲正服务名称', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2499, 'local_out', 'com.dcfs.messageReceiver.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageReceiverImpl', 'Level0消息监听实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2500, 'local_out', 'com.dcfs.esb.client.service.name', 'local_out', '节点默认服务名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2501, 'local_out', 'node.type', '2', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2502, 'local_out', 'profix', 'com.ibm.ws.naming', 'WAS的消息服务对象JNDI前缀', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2503, 'local_out', 'esb.container.token', 'false', '是否启用流量控制(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2504, 'local_out', 'esb.container.frame.poolsize', '20', '容器Frame的线程池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2505, 'local_out', 'esb.container.useAdatporValidation', 'false', '是否开启服务适配校验功能(true/false)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2506, 'local_out', 'load_dict_from_db', 'false', '是否从数据库加载数据字典', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2507, 'local_out', 'esb.container.translog', 'true,true', '是否启用日志(true/false)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2508, 'local_out', 'data.store.type', 'file', '元数据加载方式(file/db)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2509, 'local_out', 'java.naming.security.principal', 'mqm', 'MQ用户名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2510, 'local_out', 'esb.container.serviceperiod', 'false', '服务有效期功能是否开启', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2467, 'local_out', 'inner.node.in', '4cc95abd8b9cba4705f290b39dee3d6a', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (510, 'local_router', 'inner.node.nodeid', 'null', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (511, 'local_router', 'inner.node.port', '22228', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (512, 'local_router', 'java.naming.security.principal', 'mqm', 'MQ用户名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (513, 'local_router', 'inner.node.in', '6c1a4a866e5cb192e56a7ca028acbff3', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (514, 'local_router', 'com.dcfs.esb.client.service.invoker', 'com.dc.esb.monitor.impls.server.MockProviderHandler', '消息分发处理器', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (515, 'local_router', 'thread_pool_size', '10', '默认线程池大小', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (516, 'local_router', 'java.naming.security.credentials', 'mqm', 'JDNI访问资证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (517, 'local_router', 'inner.sessioncount', '1', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (518, 'local_router', 'encrypt', 'false', '安全模块开关', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (519, 'local_router', 'java.naming.security.authentication', 'simple', 'MQ身份认证', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (520, 'local_router', 'com.dcfs.messageReceiver.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageReceiverImpl', 'Level0消息监听实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (521, 'local_router', 'node.type', '4', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (522, 'local_router', 'inner.node.ip', '127.0.0.1', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (523, 'local_router', 'profix', 'com.ibm.ws.naming', 'WAS的消息服务对象JNDI前缀', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (524, 'local_router', 'location', 'local_router', null, 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (525, 'local_router', 'inner.deliverymode', '2', 'null', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (526, 'local_router', 'inner.node.out', '70022232c41f240289cac75d5b3e3884', null, 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (527, 'local_router', 'SLEEP_TIME', '20000', '监听器最大等待时间(ms)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (528, 'local_router', 'message_buffer_size', '10', '消息缓冲区最大深度', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (529, 'local_router', 'com.dcfs.esb.client.service.name', 'local_router', '节点默认服务名', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (530, 'local_router', 'com.dcfs.messageSender.impl', 'com.dcfs.impls.esb.communication.mom.MomMessageSenderImpl', 'Level0消息发送实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2511, 'local_out', 'com.dcfs.esb.client.service.invoker', 'com.dc.esb.container.adaptor.ESBProviderListener', '消息分发处理器', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2512, 'local_out', 'SLEEP_TIME', '20000', '监听器最大等待时间(ms)', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2520, 'local_in', 'defaultAccess', 'true', '默认能否访问服务', 'true');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2513, 'local_in', 'flowWaitTime', '1000', '长连接读取超时时间(ms)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2516, 'local_in', 'longConnectionPoolSize', '50', '负责长连接stop和重连得线程池大小', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2517, 'local_in', 'connectionWaitTime', '60', '交易时连接池里取长连接的超时时间(ms)', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2518, 'local_in', 'esb.loop.detect', 'false', '回路探测开关', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2519, 'local_out', 'esb.loop.detect', 'false', '回路探测开关', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2530, 'local_in', 'serial.type', '2', 'SDO流化类型', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2531, 'local_out', 'serial.type', '2', 'SDO流化类型', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2521, 'local_out', 'monitorTime', '5000', '参数更新周期', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2522, 'local_in', 'monitorTime', '5000', '参数更新周期', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2532, 'local_out', 'esb.container.protocol.asyn.dispatcher', 'com.dc.esb.container.protocol.asynctx.impls.DefaultCtxDispatcher', '本地缓存/MOM缓存', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2533, 'local_out', 'esb.container.multipkg.factory', 'com.dc.esb.test.TestMultiPkgFactory', '异步多包工厂类实现', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2534, 'local_out', 'esb.container.recvctx.timeout', '5000', '异步多报超时时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2535, 'local_out', 'esb.container.protocol.asyn.reqKeyParser', 'com.dc.esb.container.protocol.asynctx.impls.SimpleKeyParser', '请求报文key值解析器', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2536, 'local_out', 'esb.container.protocol.asyn.respKeyParser', 'com.dc.esb.container.protocol.asynctx.impls.SimpleKeyParser', '响应报文key值解析器', 'false');
commit;
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2537, 'local_in', 'esb.container.asyn.timeout', '60000', '异步模式交易超时时间', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2538, 'local_in', 'com.dcfs.esb.memoryrouter.invoker', 'com.dc.esb.container.adaptor.MemoryRouterConsumerListener', '内存路由监听器', 'false');
insert into CONFIGURATIONS (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (2539, 'local_out', 'com.dcfs.esb.memoryrouter.invoker', 'com.dc.esb.container.adaptor.MemoryRouterProviderListener', '内存路由监听器', 'false');
commit;
--ROUTERS...
insert into ROUTERS (ROUTERNAME, LOCATION, DEFAULTPROXYADDRESSID, PROXYID)
values ('local_router', 'local_router', null, null);
commit;

--SERVICES...
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('local_out', '4cc95abd8b9cba4705f290b39dee3d6a', '70022232c41f240289cac75d5b3e3884', null, 1, '2', 'null', 'local_out');
insert into SERVICES (NAME, INADDRESSID, OUTADDRESSID, TYPE, SESSIONCOUNT, DELIVERYMODE, NODEID, LOCATION)
values ('local_in', 'a31578d478cb9d20de4c67a8aa75f975', '70022232c41f240289cac75d5b3e3884', null, 1, '2', 'null', 'local_in');
commit;

--MAILBOX...
insert into MAILBOX (MAILBOXID, ADDRESSID, DELIVERYMODE, SESSIONCOUNT, ROUTERNAME)
values ('9776bc93c81b8a4f3b49a06bb5df609f', '70022232c41f240289cac75d5b3e3884', '2', 1, 'local_router');
commit;

--PADDRESSEXT...
insert into PADDRESSEXT (LOCATION, ROUTERNAME)
values ('local_in', 'local_router');
insert into PADDRESSEXT (LOCATION, ROUTERNAME)
values ('local_out', 'local_router');
commit;
