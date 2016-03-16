

create or replace function ISYSZ(rydm in varchar2)
return varchar2
is
  rynum integer;
begin
  select count(*) into rynum from qx_user u where u.czry_dm = rydm;
  if (rynum > 0) then
    return '1';
  else
    return '0';
  end if;
end ISYSZ;
/


create or replace function P_GET_JDH(ac_jdh out varchar2)
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--                              存储过程说明
--过 程 名：P_GET_JDH
--功能描述：取节点号
--输入参数：无
--输出参数：节点号
--返 回 值：100成功 其他值 系统错误
--调用来自：
--编写时间：
--编 写 人：
--修改序号：
--修 改 人：Liudq
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
return number is
begin
    select CSNR into ac_jdh from XT_XTCS where CSXH = '10001' and JG_DM='PUBLIC';
    if ac_jdh is null then
       Raise_application_error(-20000, '没有初始化“节点号”[CSXH = ''10001'']系统参数！');
    end if;

    return 100;
end;
/
CREATE OR REPLACE FUNCTION P_GET_JD_DM(
    AC_FULL VARCHAR2, --全路径名称 各级间用~隔开
    AC_FLAG VARCHAR2  --'0'表示查找QX_GNMK_TREE;'1'表示查找QX_GNMB_GNMK(只限于超级用户)
)RETURN VARCHAR2 IS
    LC_FULL     VARCHAR2(1000);--全路径名称
    LN_POS      NUMBER(10);    --~位置
    LC_JD_MC    VARCHAR2(1000);--节点名称
    LC_FJD_DM   VARCHAR2(30);  --父节点代码
BEGIN
    LC_FULL :=AC_FULL;
    LC_FJD_DM:='0';--从根节点0开始
    LOOP
        LN_POS:=INSTR(LC_FULL,'~');
        IF LN_POS=0 THEN
            LC_JD_MC:=LC_FULL;
        ELSE --如果有~，就拆分名称
            LC_JD_MC:=SUBSTR(LC_FULL,1,LN_POS-1);
            LC_FULL:=SUBSTR(LC_FULL,LN_POS+1);
        END IF;
        BEGIN
            --dbms_output.put_line(lc_jd_mc||' '||lc_fjd_dm);
            IF AC_FLAG='0' THEN
              SELECT JD_DM INTO LC_FJD_DM FROM QX_GNMK_TREE WHERE FJD_DM=LC_FJD_DM AND JD_MC=LC_JD_MC;
            ELSE
              SELECT JD_DM INTO LC_FJD_DM FROM QX_GNMB_GNMK WHERE FJD_DM=LC_FJD_DM AND JD_MC=LC_JD_MC AND GNMB_DM='00000000001';
          END IF;
        EXCEPTION
            WHEN OTHERS THEN RAISE_APPLICATION_ERROR(-20000,'查找路径名称错误：'||LC_JD_MC||'？'||SQLERRM);
        END;
        IF LN_POS=0 THEN
            RETURN LC_FJD_DM;--返回节点代码
        END IF;
    END LOOP;
END;
/
CREATE OR REPLACE FUNCTION P_GET_JD_NEW(
    AC_FJD_DM VARCHAR2, --父节点代码
    AC_JD_MC  VARCHAR2,  --节点名称
    AC_FLAG VARCHAR2  --'0'表示查找QX_GNMK_TREE;'1'表示查找QX_GNMB_GNMK(只限于超级用户)
)RETURN VARCHAR2 IS
    LN_ROW    VARCHAR2(30);  --节点代码
    LN_COUNT  NUMBER(10); -- 行数，临时变量
BEGIN
  IF AC_FLAG = '0' THEN
    SELECT COUNT(*) INTO LN_ROW FROM QX_GNMK_TREE WHERE JD_DM=AC_FJD_DM AND (JDLX_DM='01' or jdlx_dm='0'); --add by liuming
    IF LN_ROW<>1 THEN
        RAISE_APPLICATION_ERROR(-20000,'父节点代码：'||AC_FJD_DM||' 不是目录，无法向下扩展');
    END IF;

    SELECT COUNT(*) INTO LN_ROW FROM QX_GNMK_TREE WHERE FJD_DM=AC_FJD_DM AND JD_MC=AC_JD_MC;
    IF LN_ROW>0 THEN
        RAISE_APPLICATION_ERROR(-20000,'节点名称重复：'||AC_JD_MC);
    END IF;

    IF LENGTH(AC_FJD_DM)>18 THEN
      SELECT MAX(JD_DM)+1 INTO LN_ROW FROM QX_GNMK_TREE WHERE JD_DM>='0' AND JD_DM<='9';
      RETURN LN_ROW;
    ELSE
      --通过父节点代码向下扩展三位，生成新节点代码
      SELECT MAX(JD_ORDER) INTO LN_ROW FROM QX_GNMK_TREE WHERE FJD_DM=AC_FJD_DM;
      IF LN_ROW IS NULL THEN
          LN_ROW:=1;
      ELSIF LN_ROW>=999 THEN
          RAISE_APPLICATION_ERROR(-20000,'下属节点超过999，无法扩展！');
      ELSE
          LN_ROW:=LN_ROW+1;
      END IF;

      -- 避免与现有代码重复
      SELECT COUNT(*) INTO LN_COUNT FROM QX_GNMK_TREE WHERE JD_DM=AC_FJD_DM||LPAD(LN_ROW,3,'0');
      WHILE LN_COUNT > 0 LOOP
          LN_ROW:=LN_ROW+1;
          SELECT COUNT(*) INTO LN_COUNT FROM QX_GNMK_TREE WHERE JD_DM=AC_FJD_DM||LPAD(LN_ROW,3,'0');
      END LOOP;

      RETURN AC_FJD_DM||LPAD(LN_ROW,3,'0');
    END IF;

  ELSE
    SELECT COUNT(*) INTO LN_ROW FROM QX_GNMB_GNMK WHERE JD_DM=AC_FJD_DM AND GNMK_DM='FFFFFFFFFFF' AND GNMB_DM='00000000001';
    IF LN_ROW<>1 THEN
        RAISE_APPLICATION_ERROR(-20000,'父节点代码：'||AC_FJD_DM||' 不是目录，无法向下扩展');
    END IF;

    SELECT COUNT(*) INTO LN_ROW FROM QX_GNMB_GNMK WHERE FJD_DM=AC_FJD_DM AND JD_MC=AC_JD_MC AND GNMB_DM='00000000001';
    IF LN_ROW>0 THEN
        RAISE_APPLICATION_ERROR(-20000,'节点名称重复：'||AC_JD_MC);
    END IF;

    IF LENGTH(AC_FJD_DM)>18 THEN
      SELECT MAX(JD_DM)+1 INTO LN_ROW FROM QX_GNMB_GNMK WHERE GNMB_DM='00000000001' AND JD_DM>='0' AND JD_DM<='9';
      RETURN LN_ROW;
    ELSE
      --通过父节点代码向下扩展三位，生成新节点代码
      SELECT MAX(JD_ORDER) INTO LN_ROW FROM QX_GNMB_GNMK WHERE FJD_DM=AC_FJD_DM AND GNMB_DM='00000000001';
      IF LN_ROW IS NULL THEN
          LN_ROW:=1;
      ELSIF LN_ROW>=999 THEN
          RAISE_APPLICATION_ERROR(-20000,'下属节点超过999，无法扩展！');
      ELSE
          LN_ROW:=LN_ROW+1;
      END IF;

      -- 避免与现有代码重复
      SELECT COUNT(*) INTO LN_COUNT FROM QX_GNMB_GNMK WHERE JD_DM=AC_FJD_DM||LPAD(LN_ROW,3,'0');
      WHILE LN_COUNT > 0 LOOP
          LN_ROW:=LN_ROW+1;
          SELECT COUNT(*) INTO LN_COUNT FROM QX_GNMB_GNMK WHERE JD_DM=AC_FJD_DM||LPAD(LN_ROW,3,'0');
      END LOOP;

      RETURN AC_FJD_DM||LPAD(LN_ROW,3,'0');
    END IF;
  END IF;
END;
/
CREATE OR REPLACE FUNCTION P_SEQUENCE_STANDARD(sequenceNo out varchar2, sequenceName in varchar2)

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
--                              存储过程说明
--过 程 名：P_SEQUENCE_STANDARD
--功能描述：标准序列计算函数
--输入参数：序列名称
--输出参数：序列值
--返 回 值：100成功 其他值 系统错误
--调用来自：序列发生器
--编写时间：2007年07月30日
--编 写 人：Liudq
--修改序号：
--修 改 人：
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
return  number is
    jdno    varchar2(4); -- 节点号
    ln_return  number(10); -- 返回值
begin
    execute immediate 'select to_char(' || sequenceName || '.nextval) from dual' into sequenceNo;
    ln_return := P_GET_JDH(jdno);
    if ln_return = 100 then
      sequenceno := lpad(sequenceno,8,'0');
      sequenceNo := jdno||to_char(sysdate,'yy')||'9'||sequenceno||'000';
  end if;

  return ln_return;
end;
/