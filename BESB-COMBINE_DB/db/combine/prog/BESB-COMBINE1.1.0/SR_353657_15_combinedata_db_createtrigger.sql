CREATE OR REPLACE TRIGGER tr_insert_flowlog
   AFTER  INSERT --指定触发时机为插入操作后触发
   ON ESB2_TRANS_REQ_LOG
   FOR EACH ROW   --说明创建的是行级触发器
   
BEGIN
   --将新增数据插入到日志记录表 ESBFLOWLOG_log ,以供monitor使用。
     INSERT INTO ESBFLOWLOG_log(esbflowno,ESBSERVICEFLOWNO,ESBSTATUS,RESPCODE,TRADESTATUS,BUSINESSRESPCODE,SERVICEID,CHANNELID,LOCATIONID,LOGSTAMP,LOOP,flowstepid,REALCHANNEL,REALSYSTEM,SERVICETYPE)
     VALUES(:new.esbflowno,:new.ESBSERVICEFLOWNO,:new.RespStatus,:new.respcode,:new.BUSINESSRESPSTATUS,:new.businessrespcode,:new.serviceid,:new.logicchannel,:new.locationid,:new.operstamp,:new.loopid,:new.flowstepid,:new.REALCHANNEL,:new.REALSYSTEM,:new.SERVICETYPE);

END;
/
CREATE OR REPLACE TRIGGER tr_insert_flowlogtime
   AFTER  INSERT --指定触发时机为插入操作后触发
   ON esb2_trans_log_stamp
   FOR EACH ROW   --说明创建的是行级触发器
BEGIN
   --将新增数据插入到日志记录表 ESBFLOWLOG_time ,以供monitor使用。
   INSERT INTO ESBFLOWLOG_time(esbflowno,TIME1,TIME2,TIME3,TIME4,operstamp)
       VALUES( :new.esbflowno,:new.TRANSSTAMP1,:new.TRANSSTAMP2,:new.TRANSSTAMP3,:new.TRANSSTAMP4,sysdate);
END;
/