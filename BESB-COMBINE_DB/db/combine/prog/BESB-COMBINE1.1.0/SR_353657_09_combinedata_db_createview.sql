create or replace view esbflowlog as
select  a.ESBSERVICEFLOWNO,
        cast(a.RespStatus as INTEGER)  ESBStatus,
        a.respcode,
        cast(a.BUSINESSRESPSTATUS as INTEGER) TradeStatus,
        a.businessrespcode,
        a.serviceid,
         cast((select servicetype from esb2_trans_req_log where esbflowno=a.esbflowno and flowstepid='2') as VARCHAR(1)) servicetype,
          a.locationid,
           a.operstamp LogStamp,
          b.transstamp1 time1,
        b.transstamp2 time2,
        b.transstamp3 time3,
        b.transstamp4 time4,
        NVL(s.name,'other') SERVICESYSTEM,
        NVL(t.name,'other') CHANNELSYSTEM,
        NVL(a.realchannel,'other') channelid,
        a.loopid LOOP
  from esb2_trans_req_log a, esb2_trans_log_stamp b,servicesystemmap s,servicesystemmap t
  where a.esbflowno=b.esbflowno and  a.flowstepid='4' and s.serviceid(+)=a.serviceid and t.serviceid(+)=a.realchannel
/