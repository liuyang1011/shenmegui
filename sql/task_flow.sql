--序列SQL
CREATE SEQUENCE  "ESBSG"."TASK_MANAGE_AUTOADD"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 16 NOCACHE  NOORDER  NOCYCLE ;
--触发器SQL
CREATE trigger TASK  
   before insert on "ESBSG"."TASK_MANAGE" 
   for each row 
begin  
   if inserting then 
      if :NEW."TASKNUM" = 0 then 
         select TASK_MANAGE_AUTOADD.nextval into :NEW."TASKNUM" from dual; 
      end if; 
   end if; 
end;