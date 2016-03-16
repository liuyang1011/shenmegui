create or replace view esbflowlog as
select  a.ESBSERVICEFLOWNO,
        cast(a.ESBSTATUS as INTEGER)  ESBStatus,
        a.respcode,
        cast(a.TRADESTATUS as INTEGER) TradeStatus,
        a.businessrespcode,
        b.time1 time1,
        b.time2 time2,
        b.time3 time3,
        b.time4 time4,
        a.serviceid,
         cast((select servicetype from ESBFLOWLOG_log where esbflowno=a.esbflowno and flowstepid='2') as VARCHAR(1)) servicetype,
        NVL(s.name,'other') SERVICESYSTEM,
        NVL(t.name,'other') CHANNELSYSTEM,
        NVL(a.realchannel,'other') channelid,
        a.locationid,
        a.logstamp LogStamp,
        a.loop LOOP
  from ESBFLOWLOG_log a, ESBFLOWLOG_time b,servicesystemmap s,servicesystemmap t
  where a.esbflowno=b.esbflowno and  a.flowstepid='4' and s.serviceid(+)=a.serviceid and t.serviceid(+)=a.channelid

/