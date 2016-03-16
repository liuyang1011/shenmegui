create or replace view v_rs_ryxx as
	select
	"RY_DM",
	"RY_XM",
	"DW_JG_DM",
	"BM_JG_DM",
	"ADDRESS",
	"ZJHM",
	"DHHM",
	"SJHM",
	"EMAIL"
	from RS_RYXX
/

CREATE OR REPLACE VIEW V_DM_DWLSGX AS
	SELECT "DWLSGX_DM","DWLSGX_MC","XYBZ","YXBZ"
	FROM DM_DWLSGX
	WHERE YXBZ='Y' AND XYBZ = 'Y'
/

create or replace view v_dm_jg as
	select
	"JG_DM",
	"JG_MC",
	"JG_JC",
	"JG_BZ",
	"SJ_JG_DM",
	"DWLSGX_DM",
	"JG_JG",
	"JGYB",
	"JGDZ",
	"JGDH",
	"CZDH",
	"DYDZ",
	"XZQH_DM",
	"JGFZR_DM",
	"JBDM",
	"JCDM",
	"XYBZ",
	"YXBZ"
	from DM_JG
	where YXBZ = 'Y'
	and XYBZ = 'Y'
	order by JG_DM
/

CREATE OR REPLACE VIEW V_DM_BM AS
	SELECT "JG_DM",
	"JG_MC",
	"JG_JC","JG_BZ","SJ_JG_DM","DWLSGX_DM","JG_JG","JGYB","JGDZ","JGDH","CZDH","DYDZ","XZQH_DM","JGFZR_DM","JBDM","JCDM","XYBZ","YXBZ"
	  FROM DM_JG
	 WHERE YXBZ='Y'
	   AND XYBZ = 'Y'
	   AND JG_BZ='B'
/


create or replace view v_dm_czry as
	select dm_czry.czry_dm,czry_mc,jg_dm,zjhm,address,dhhm,sjhm,email,isysz(dm_czry.czry_dm)as isysz,qx_user.userid as userid
	from dm_czry left join qx_user on dm_czry.czry_dm=qx_user.czry_dm
/

create or replace view V_QX_GNMK_TREE as
select JD_DM, FJD_DM, JD_MC, JDLX_DM, JD_ORDER, QX_GNMK.GNMK_DM, GNMK_HZMC, GNMK_LJMC, MKLX_DM, YWHJ_DM, CYBJ, GZL_BZ, CFDK, DKWZ, SHOWLEFT, SHOWTOP, SHOWINTREE, SYSTEMNAME, YXBZ
   from QX_GNMK_TREE inner join QX_GNMK on QX_GNMK_TREE.GNMK_DM=QX_GNMK.GNMK_DM;