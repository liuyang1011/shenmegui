update configurations t set t.paramvalue ='10000' where t.paramname = 'message_buffer_size';
update configurations t set t.paramvalue ='500' where t.paramname = 'thread_pool_size';
update configurations t set t.paramvalue ='500' where t.paramname = 'esb.container.frame.poolsize';
update configurations t set t.paramvalue ='500' where t.paramname = 'esb.container.context.poolsize';
update configurations t set t.paramvalue ='60000' where t.paramname = 'esb.container.timeout' and t.location ='local_in';
update configurations t set t.paramvalue ='55000' where t.paramname = 'esb.container.timeout' and t.location ='local_out';

update protocolbind set binduri = '<?xml version="1.0" encoding="UTF-8"?><protocol.http protocolName="HTTPChannelConnector" id="HTTP_IN" mode="synchronous" ioDirection="DataIn/DataOut" side="server"><common uri="http://127.0.0.1:48008/smartbrach"/><request encoding="UTF-8" action="toString" requestAdapter="Adaptor_HTTP_IN" method="post"/><response contentType="text/html" encoding="UTF-8" responseAdaprer="Adaptor_Resp"/><security protocol="SSL" bidirectional="false"><keyStore/><trustStore/></security><advanced threadCount="200" connPerHostCount="200" readTimeout="120000" maxConnCount="2000" soLinger="0" writeBufferSize="2048" reuseAddress="true" connectionTimeout="120000" readBufferSize="2048" tcpNoDelay="true"/></protocol.http>' where protocolid = 'HTTP_IN';

update protocolbind set binduri = '<?xml version="1.0" encoding="UTF-8"?><protocol.jms protocolName="JMSServiceConnector" id="MQ_OUT" mode="synchronous" ioDirection="DataIn/DataOut" side="client"><request JNDIContextFactory="com.ibm.mq.jms.context.WMQInitialContextFactory" ProviderURL="127.0.0.1:38895/BRANCH.CHL" ConnectionFactory="esb.branch.qm" QueueName="BRANCH.IN" SessionCount="200" locationDepend="false"/><response readTimeout="30000" JNDIContextFactory="com.ibm.mq.jms.context.WMQInitialContextFactory" ProviderURL="127.0.0.1:38895/BRANCH.CHL" ConnectionFactory="esb.branch.qm" QueueName="BRANCH.OUT" SessionCount="200" locationDepend="false"/><advanced/></protocol.jms>' where protocolid = 'MQ_OUT';
commit;