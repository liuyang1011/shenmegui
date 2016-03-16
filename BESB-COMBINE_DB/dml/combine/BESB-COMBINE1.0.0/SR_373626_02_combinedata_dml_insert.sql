INSERT INTO COMB_BUSS_JOB_RULE (RULEID,MAXNUM,INTERVAL,PRIORITYLEVEL,RUNTASK,FINISHTASK) 
VALUES ('JOBCa1800200000101',10,30,1,'com.branch.base.impl.service.BaseCallBusi1300100000607','com.branch.base.impl.service.BaseUpdateFinishStatus');

INSERT INTO COMB_BUSS_JOB_RULE (RULEID,MAXNUM,INTERVAL,PRIORITYLEVEL,RUNTASK,FINISHTASK) 
VALUES ('JOBCa1800200000102',10,30,1,'com.branch.base.impl.service.BaseCallBusi1300100000608','com.branch.base.impl.service.BaseUpdateFinishStatus');

insert into configurations (id, location, paramname, paramvalue, description, paramtype)
values (5333, '', 'job.interval', '5', 'job定时器扫描任务表间隔时间', '');
  
insert into configurations (id, location, paramname, paramvalue, description, paramtype)
values (5334, '', 'job.pool.limit.num', '10', 'job处理任务线程池大小', '');
  
insert into configurations (id, location, paramname, paramvalue, description, paramtype)
values (5335, '', 'job.max.scan.num', '10', 'job定时器每次扫描的记录数', '');

insert into ESB_F5_CONTROL (CLUSTERNAME, STATUS) values ('cluster2', 'running');


insert into configurations (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (6666, 'journalSrv', 'cluster.identify', '10.8.64.114:cluster1#10.8.64.115:cluster2# ', '分路标识', 'true');

insert into configurations (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (6667, 'local_router', 'cluster.identify', '10.8.64.114:cluster1#10.8.64.115:cluster2# ', '分路标识', 'true');

insert into configurations (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (6668, 'local_out', 'cluster.identify', '10.8.64.114:cluster1#10.8.64.115:cluster2# ', '分路标识', 'true');

insert into configurations (ID, LOCATION, PARAMNAME, PARAMVALUE, DESCRIPTION, PARAMTYPE)
values (6669, 'local_in', 'cluster.identify', '10.8.64.114:cluster1#10.8.64.115:cluster2# ', '分路标识', 'true');

