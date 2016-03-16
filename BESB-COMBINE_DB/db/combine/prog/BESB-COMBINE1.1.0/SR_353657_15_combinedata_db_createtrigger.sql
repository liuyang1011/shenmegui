CREATE OR REPLACE TRIGGER tr_insert_flowlog
   AFTER  INSERT --ָ������ʱ��Ϊ��������󴥷�
   ON ESB2_TRANS_REQ_LOG
   FOR EACH ROW   --˵�����������м�������
   
BEGIN
   --���������ݲ��뵽��־��¼�� ESBFLOWLOG_log ,�Թ�monitorʹ�á�
     INSERT INTO ESBFLOWLOG_log(esbflowno,ESBSERVICEFLOWNO,ESBSTATUS,RESPCODE,TRADESTATUS,BUSINESSRESPCODE,SERVICEID,CHANNELID,LOCATIONID,LOGSTAMP,LOOP,flowstepid,REALCHANNEL,REALSYSTEM,SERVICETYPE)
     VALUES(:new.esbflowno,:new.ESBSERVICEFLOWNO,:new.RespStatus,:new.respcode,:new.BUSINESSRESPSTATUS,:new.businessrespcode,:new.serviceid,:new.logicchannel,:new.locationid,:new.operstamp,:new.loopid,:new.flowstepid,:new.REALCHANNEL,:new.REALSYSTEM,:new.SERVICETYPE);

END;
/
CREATE OR REPLACE TRIGGER tr_insert_flowlogtime
   AFTER  INSERT --ָ������ʱ��Ϊ��������󴥷�
   ON esb2_trans_log_stamp
   FOR EACH ROW   --˵�����������м�������
BEGIN
   --���������ݲ��뵽��־��¼�� ESBFLOWLOG_time ,�Թ�monitorʹ�á�
   INSERT INTO ESBFLOWLOG_time(esbflowno,TIME1,TIME2,TIME3,TIME4,operstamp)
       VALUES( :new.esbflowno,:new.TRANSSTAMP1,:new.TRANSSTAMP2,:new.TRANSSTAMP3,:new.TRANSSTAMP4,sysdate);
END;
/