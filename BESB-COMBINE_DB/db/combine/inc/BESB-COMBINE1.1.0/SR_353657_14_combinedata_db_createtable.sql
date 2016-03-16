create table esb_runtime_service
(
  serviceid   varchar2(255) not null,
  servicetype varchar2(40)
)
;

-- Create table
create table ESB_RUNTIME_FILE
(
  FILEID    VARCHAR2(40) not null,
  FILENAME  VARCHAR2(255) not null,
  FILEEXT   VARCHAR2(40),
  FILETYPE  VARCHAR2(40),
  FILEBLOB  BLOB,
  INOUTSIDE VARCHAR2(4),
  SERVICEID VARCHAR2(255),
  OPERATETIME TIMESTAMP
)
;

create table ESB_RUNTIME_FILE_CONTENT
(
  CID        VARCHAR2(40) not null,
  CONTENTXML VARCHAR2(4000),
  ROWINDEX   INTEGER,
  FILEID     VARCHAR2(40)
)
;



CREATE TABLE ESB_MASTERKEY_INFO
   (	
	 CHANNEL_UID VARCHAR2(50) NOT NULL, 
	 MASTERKEY VARCHAR2(24) NOT NULL, 
	 REMARK  VARCHAR2(1000)
   )
;

CREATE TABLE ESB_WORKKEY_INFO 
   (	
	CHANNEL_UID VARCHAR2(50) NOT NULL, 
	WORKDATE DATE NOT NULL, 
	WORKKEY VARCHAR2(24) NOT NULL, 
	REMARK VARCHAR2(1000)
   ) 
;


alter table DEPLOYMENTS add (SERVICEIMPL VARCHAR2(100));


------------------------------------------------------------------
--log 5分钟数据表
CREATE TABLE ESBFLOWLOG_log
(
   esbflowno,
   ESBSERVICEFLOWNO,
   ESBSTATUS,
   RESPCODE,
   TRADESTATUS,
   BUSINESSRESPCODE,
   SERVICEID,
   CHANNELID,
   LOCATIONID,
   LOGSTAMP,
   LOOP,
   flowstepid,
   REALCHANNEL,
   REALSYSTEM,
   SERVICETYPE
)
AS
   SELECT a.esbflowno,a.ESBSERVICEFLOWNO,
          a.RespStatus ESBStatus,
          a.respcode,
          a.BUSINESSRESPSTATUS TradeStatus,
          a.businessrespcode,
          a.serviceid,
          a.LOGICCHANNEL channelid,
          a.locationid,
          a.operstamp LogStamp,
          a.loopid LOOP,
          a.flowstepid,
          a.REALCHANNEL,
          a.REALSYSTEM,
	  a.SERVICETYPE
     FROM ESB2_TRANS_REQ_LOG a;
    
------------------------------------------------------------------
/* 5分钟log执行时间表*/
CREATE table ESBFLOWLOG_time
(
   esbflowno,
   TIME1,
   TIME2,
   TIME3,
   TIME4,
   operstamp
)
AS
   SELECT b.esbflowno,
          b.transstamp1 time1,
          b.transstamp2 time2,
          b.transstamp3 time3,
          b.transstamp4 time4,
          to_timestamp(sysdate) operstamp
     FROM esb2_trans_log_stamp b;
commit;