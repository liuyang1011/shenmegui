variable JOBNO number;
/
begin
	sys.dbms_job.submit(
		job => :JOBNO,
		what => 'pr_delete_flowlog;pr_delete_time;',
		next_date => to_date(to_char(sysdate,'yyyy-MM-dd') || ' 10:35:00', 'yyyy-MM-dd hh24:mi:ss'),
		interval => 'sysdate+1/1440'   --每天1440分钟，即5分钟运行存储过程一次
	);
	commit;
end;
/
PRINT JOBNO; 