 
create or replace trigger ESB2_TRANS_EXTEND_LOG_tb_tri
before insert on ESB2_TRANS_EXTEND_LOG 
for each row
begin
select ESB2_TRANS_EXTEND_LOG_seq.nextval into :new.EXTID from dual;  
end;
/

