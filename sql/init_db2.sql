delete from system;
insert into system(system_id, system_chinese_name, system_ab)values('000001', '综合前端系统', 'befosystem');
insert into system(system_id, system_chinese_name, system_ab)values('000002', '财管系统', 'FTM');
insert into system(system_id, system_chinese_name, system_ab)values('000003', '核心账务系统', 'CORE');
insert into system(system_id, system_chinese_name, system_ab)values('000004', '机构柜员管理系统', 'ITS');
insert into system(system_id, system_chinese_name, system_ab)values('000005', 'BSMP', 'BSM');
insert into system(system_id, system_chinese_name, system_ab)values('000006', '联网核查系统', 'idcheck');
insert into system(system_id, system_chinese_name, system_ab)values('000009', '自助发卡系统', 'ACM');
insert into system(system_id, system_chinese_name, system_ab)values('000010', '自动柜员系统', 'ATM');
commit;


INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('1001','渠道运维',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('1002','流程服务',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('1003','内部渠道服务',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('1004','合作方服务',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('2001','客户信息管理',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('2002','客户服务',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3001','存款',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3002','贷款',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3003','银行卡',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3004','支付结算',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3005','投资理财',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3006','中间业务',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3007','金融市场',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3008','投行',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('3009','客户资产管理',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('4001','风险管理',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('5001','银行业务支持',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('5002','企业管理支持',null);
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('6001','技术支持',null);


INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10011','金融类','1001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10012','非金融类','1001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10013','查询类','1001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10014','文件类','1001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10015','冲正类','1001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10021','金融类','1002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10022','非金融类','1002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10023','查询类','1002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10024','文件类','1002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10025','冲正类','1002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10031','金融类','1003'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10032','非金融类','1003'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10033','查询类','1003');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10034','文件类','1003');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10035','冲正类','1003');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10041','金融类','1004');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10042','非金融类','1004'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10043','查询类','1004');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10044','文件类','1004'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('10045','冲正类','1004');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20011','金融类','2001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20012','非金融类','2001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20013','查询类','2001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20014','文件类','2001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20015','冲正类','2001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20021','金融类','2002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20022','非金融类','2002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20023','查询类','2002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20024','文件类','2002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('20025','冲正类','2002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30011','金融类','3001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30012','非金融类','3001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30013','查询类','3001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30014','文件类','3001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30015','冲正类','3001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30021','金融类','3002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30022','非金融类','3002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30023','查询类','3002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30024','文件类','3002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30025','冲正类','3002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30031','金融类','3003'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30032','非金融类','3003');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30033','查询类','3003'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30034','文件类','3003'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30035','冲正类','3003');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30041','金融类','3004'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30042','非金融类','3004'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30043','查询类','3004');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30044','文件类','3004'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30045','冲正类','3004');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30051','金融类','3005'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30052','非金融类','3005'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30053','查询类','3005'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30054','文件类','3005'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30055','冲正类','3005');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30061','金融类','3006'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30062','非金融类','3006');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30063','查询类','3006');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30064','文件类','3006'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30065','冲正类','3006');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30071','金融类','3007');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30072','非金融类','3007'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30073','查询类','3007');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30074','文件类','3007');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30075','冲正类','3007');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30081','金融类','3008');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30082','非金融类','3008'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30083','查询类','3008'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30084','文件类','3008'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30085','冲正类','3008');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30091','金融类','3009');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30092','非金融类','3009'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30093','查询类','3009');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30094','文件类','3009'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('30095','冲正类','3009');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('40011','金融类','4001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('40012','非金融类','4001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('40013','查询类','4001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('40014','文件类','4001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('40015','冲正类','4001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50011','金融类','5001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50012','非金融类','5001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50013','查询类','5001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50014','文件类','5001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50015','冲正类','5001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50021','金融类','5002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50022','非金融类','5002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50023','查询类','5002'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50024','文件类','5002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('50025','冲正类','5002');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('60011','金融类','6001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('60012','非金融类','6001');
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('60013','查询类','6001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('60014','文件类','6001'); 
INSERT INTO SERVICE_CATEGORY (CATEGORY_ID, CATEGORY_NAME, PARENT_ID) VALUES ('60015','冲正类','6001');


COMMIT;

insert into SG_ORG (ORG_ID, ORG_AB, ORG_NAME, ORG_STATUS)
values ('1', '1', '信息科技部', '1');
insert into SG_ORG (ORG_ID, ORG_AB, ORG_NAME, ORG_STATUS)
values ('2', '2', '其他部门', '1');

insert into SG_ROLE (role_id, role_name, role_remark)
values ('developer', '服务开发人员', null);
insert into SG_ROLE (role_id, role_name, role_remark)
values ('systemfunctionary', '系统负责人员', null);
insert into SG_ROLE (role_id, role_name, role_remark)
values ('admin', 'admin', '2015/7/10');
insert into SG_ROLE (role_id, role_name, role_remark)
values ('server', '服务治理人员', null);
insert into SG_ROLE (role_id, role_name, role_remark)
values ('serverheadman', '服务治理组长', null);
commit;


insert into SG_USER (USER_ID, USER_LASTDATE, USER_NAME, USER_ORG_ID, USER_PASSWORD, USER_REMARK, USER_STARTDATE, USER_MOBILE, USER_TEL) values ('admin', '2025/10/28', '管理员', '1', '123456', '', '2015/7/22', '', '');

insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('admin','admin');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1749','admin');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1891','admin');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6939','admin');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0229','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0271','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0272','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0376','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0407','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0772','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('0847','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1106','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1748','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1890','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('1892','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('2010','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('2011','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('2013','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('2687','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('2688','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('3035','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('3036','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4132','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4134','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4375','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4376','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4599','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4700','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4823','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4933','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('4934','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('5504','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('5676','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('5716','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('5822','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6138','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6305','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6793','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6796','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6841','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('6935','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('7076','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('7520','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('7635','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('7925','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('7926','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8127','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8128','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8129','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8379','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8380','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8564','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8806','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8807','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('8906','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('9380','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('9382','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('9944','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('9945','systemfunctionary');
insert into USER_ROLE_RELATION (USER_ID,ROLE_ID) VALUES ('9946','systemfunctionary');

commit;

insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('1','公共信息',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('2','系统管理',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('3','服务管理-服务信息管理',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('4','服务管理-服务发布管理',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('10','服务管理-服务报文头管理',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('5','服务管理',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('6','交易链路',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('7','统计报表',null,null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('8','平台管理',null,null);

insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('81','用户维护','8',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('82','角色维护','8',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('83','系统日志','8',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('84','生成类','8',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('71','复用率统计','7',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('72','发布统计','7',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('61','交易链路信息','6',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('31','左侧右击菜单','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('32','服务基本信息','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('33','服务场景','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('34','服务sda','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('35','服务SLA','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('36','服务接口映射','3',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('41','版本发布','4',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('42','版本历史','4',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('43','基线制作','4',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('44','基线历史','4',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('51','服务检索','5',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('21','左侧右击菜单','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('22','系统管理','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('23','接口','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('24','接口定义信息','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('25','报文头ida','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('26','协议信息','2',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('11','类别词管理','1',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('12','元数据','1',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('13','公共代码','1',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('14','资源导入','1',null);
insert into SG_MENU_CATEGORY (id,chinese_name,parent_id,temp) values ('15','英文单词管理','1',null);



insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('101','查询','101','81');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('102','新增','102','81');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('103','修改','103','81');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('104','删除','104','81');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('105','重置密码','105','81');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('106','新增','106','82');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('107','修改','107','82');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('108','删除','108','82');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('109','权限分配','109','82');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('110','查询','110','83');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('111','查询','111','71');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('112','导出','112','71');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('113','查询','111','72');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('114','导出','112','72');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('115','预览','113','61');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('116','血缘分析','113','61');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('117','节点维护','114','61');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('118','服务大类新增','115','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('119','服务大类修改','116','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('120','服务大类删除','117','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('121','服务新增','118','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('122','服务编辑','119','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('123','服务查询','120','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('124','服务删除','121','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('125','导出白皮书PDF','122','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('126','导出字段映射Excel','123','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('127','导出服务视图Excel','124','31');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('128','场景新增','125','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('129','场景修改','126','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('130','场景删除','127','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('131','场景查询','128','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('132','历史版本','129','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('133','发布版本','130','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('134','提交审核','131','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('135','审核','132','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('136','修订','133','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('137','导出EXCEL','123','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('138','导出配置文件','134','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('139','消费者提供者关系-新增','135','33');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('140','消费者提供者关系-删除','136','33');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('141','保存','126','33');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('142','新增','137','34');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('143','编辑','138','34');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('144','删除','139','34');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('145','查询','140','34');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('146','新增','141','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('147','删除','142','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('148','保存','143','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('149','SLA模板-查询','144','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('150','SLA模板-新增','145','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('151','SLA模板-删除','146','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('152','SLA模板-修改','147','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('153','查询','148','35');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('154','映射元数据-查询','149','36');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('155','映射元数据-删除','150','36');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('156','映射元数据-修改','151','36');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('157','查询','129','41');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('158','发布','130','41');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('159','查询','152','42');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('160','发布','153','43');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('161','查询','154','43');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('162','查询','154','44');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('163','查询','128','51');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('164','导出excel','123','51');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('165','导出PDF','122','51');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('166','详细信息','128','51');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('167','新增系统','155','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('168','查看系统','156','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('169','新增接口','157','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('170','编辑接口','158','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('171','删除接口','159','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('172','新增报文头','160','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('173','删除报文头','161','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('174','新增协议','162','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('175','删除协议','163','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('176','删除文件','164','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('177','新增','155','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('178','修改','165','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('179','删除','166','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('180','查询','156','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('181','文件管理-新增','167','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('182','文件管理-删除','164','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('183','文件管理-查询','168','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('184','文件管理-下载','169','22');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('185','新增','157','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('186','修改','158','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('187','删除','159','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('188','关联报文头','170','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('189','导出','171','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('190','发布','172','23');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('191','ida删除','150','24');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('192','ida修改','151','24');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('193','ida新增','173','24');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('194','ida查询','149','24');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('195','删除','150','25');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('196','修改','151','25');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('197','新增','173','25');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('198','查询','149','25');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('199','修改','174','26');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('200','查询','175','26');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('201','删除','163','26');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('202','新增','176','11');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('203','删除','177','11');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('204','修改','178','11');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('205','查询','179','11');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('206','新增','180','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('207','修改','181','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('208','删除','182','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('209','关联服务场景','183','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('210','导入','184','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('211','导出XML','185','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('212','导出excel','186','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('213','查询','183','12');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('214','查询','187','13');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('215','修改','188','13');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('216','删除','189','13');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('217','新增','190','13');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('21801','数据字典导入','184','14');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('218','字段映射导入','191','14');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('219','接口导入','192','14');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('220','导入日志删除','193','14');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('221','导入日志查看','194','14');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('222','编辑报文头','195','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('223','编辑协议','196','21');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('224','下线','197','32');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('225','查看','198','10');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('226','添加','199','10');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('227','修改','200','10');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('228','删除','201','10');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('229','查看','202','84');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('230','添加','203','84');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('231','修改','204','84');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('232','删除','205','84');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('233','查看','206','15');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('234','添加','207','15');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('235','修改','208','15');
insert into SG_MENU (id,name,permission_id,sg_menu_category_id) values ('236','删除','209','15');



insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('101', null, null, 'user', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('102', null, null, 'user', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('103', null, null, 'user', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('104', null, null, 'user', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('105', null, null, 'password', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('106', null, null, 'role', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('107', null, null, 'role', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('108', null, null, 'role', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('109', null, null, 'role', 'fp', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('110', null, null, 'systemLog', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('111', null, null, 'statistics', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('112', null, null, 'exportStatistics', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('113', null, null, 'link', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('114', null, null, 'link', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('115', null, null, 'serviceCategory', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('116', null, null, 'serviceCategory', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('117', null, null, 'serviceCategory', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('118', null, null, 'service', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('119', null, null, 'service', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('120', null, null, 'service', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('121', null, null, 'service', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('122', null, null, 'exportPdf', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('123', null, null, 'excelExport', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('124', null, null, 'viewExcelExport', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('125', null, null, 'operation', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('126', null, null, 'operation', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('127', null, null, 'operation', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('128', null, null, 'operation', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('129', null, null, 'version', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('130', null, null, 'version', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('131', null, null, 'operation', 'commit', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('132', null, null, 'version', 'check', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('133', null, null, 'operation', 'revise', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('134', null, null, 'exportConfig', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('135', null, null, 'invoke', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('136', null, null, 'invoke', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('137', null, null, 'sda', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('138', null, null, 'sda', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('139', null, null, 'sda', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('140', null, null, 'sda', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('141', null, null, 'sla', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('142', null, null, 'sla', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('143', null, null, 'sla', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('144', null, null, 'slaTemp', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('145', null, null, 'slaTemp', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('146', null, null, 'slaTemp', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('147', null, null, 'slaTemp', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('148', null, null, 'sla', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('149', null, null, 'ida', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('150', null, null, 'ida', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('151', null, null, 'ida', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('152', null, null, 'versionHis', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('153', null, null, 'baseLine', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('154', null, null, 'baseLine', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('155', null, null, 'system', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('156', null, null, 'system', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('157', null, null, 'interface', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('158', null, null, 'interface', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('159', null, null, 'interface', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('160', null, null, 'interfaceHead', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('161', null, null, 'interfaceHead', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('162', null, null, 'protocol', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('163', null, null, 'protocol', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('164', null, null, 'file', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('165', null, null, 'system', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('166', null, null, 'system', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('167', null, null, 'file', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('168', null, null, 'file', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('169', null, null, 'file', 'download', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('170', null, null, 'interface', 'headRelation', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('171', null, null, 'exportInterface', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('172', null, null, 'interface', 'release', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('173', null, null, 'ida', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('174', null, null, 'protocol', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('175', null, null, 'protocol', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('176', null, null, 'categoryWord', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('177', null, null, 'categoryWord', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('178', null, null, 'categoryWord', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('179', null, null, 'categoryWord', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('180', null, null, 'metadata', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('181', null, null, 'metadata', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('182', null, null, 'metadata', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('183', null, null, 'metadata', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('184', null, null, 'importMetadata', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('185', null, null, 'exportXML', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('186', null, null, 'exportMetadataExcel', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('187', null, null, 'enum', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('188', null, null, 'enum', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('189', null, null, 'enum', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('190', null, null, 'enum', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('191', null, null, 'importExcel', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('192', null, null, 'importInterface', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('193', null, null, 'importlog', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('194', null, null, 'importlog', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('195', null, null, 'interfaceHead', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('196', null, null, 'protocol', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('197', null, null, 'operation', 'quit', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('198', null, null, 'serviceHead', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('199', null, null, 'serviceHead', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('200', null, null, 'serviceHead', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('201', null, null, 'serviceHead', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('202', null, null, 'generator', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('203', null, null, 'generator', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('204', null, null, 'generator', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('205', null, null, 'generator', 'delete', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('206', null, null, 'englishWord', 'get', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('207', null, null, 'englishWord', 'add', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('208', null, null, 'englishWord', 'update', null, null);
insert into PERMISSION (id, chinese_description, chinese_name, description, name, temp, category_id) values ('209', null, null, 'englishWord', 'delete', null, null);
commit;


delete from ROLE_PERMISSION_RELATION;
commit;

delete from ROLE_MENU_RELATION;
commit;

insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d89d00ee', 'serverheadman', '126');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8a300ef', 'serverheadman', '127');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8b600f0', 'serverheadman', '128');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8bb00f1', 'serverheadman', '129');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8c000f2', 'serverheadman', '130');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8c500f3', 'serverheadman', '131');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8cb00f4', 'serverheadman', '132');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8de00f5', 'serverheadman', '133');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec01506934d729010a', 'admin', '118');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de729012e', 'admin', '126');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de7640130', 'admin', '128');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069597f7d0006', 'admin', '131');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de76e0134', 'admin', '130');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069597fd90008', 'admin', '163');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de77a0138', 'admin', '133');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de77f013a', 'admin', '134');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de784013c', 'admin', '135');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de788013e', 'admin', '136');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de78e0140', 'admin', '137');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de7920141', 'admin', '138');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069597ff60009', 'admin', '166');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de7e10145', 'admin', '158');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693de7f00146', 'admin', '164');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506948570150694c21fd0001', 'admin', '149');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069708fd1000c', 'admin', '190');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749bf3000e', 'admin', '167');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c3b0010', 'admin', '169');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c400012', 'admin', '170');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c440014', 'admin', '171');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c490016', 'admin', '172');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c4d0018', 'admin', '173');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c52001a', 'admin', '174');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506a6e7e01506a774ed8000b', 'tt', '105');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c5a001e', 'admin', '176');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c5f0020', 'admin', '177');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c630021', 'admin', '178');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c680023', 'admin', '179');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c6e0025', 'admin', '181');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c730027', 'admin', '182');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c780028', 'admin', '184');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c7d002a', 'admin', '185');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c81002b', 'admin', '186');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c86002c', 'admin', '187');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c8a002d', 'admin', '188');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749c8f002f', 'admin', '189');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506a6e7e01506a774e070009', 'tt', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749ccd0037', 'admin', '194');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749cf8003c', 'admin', '198');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d1a003e', 'admin', '129');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d240040', 'admin', '132');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d350042', 'admin', '139');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d3a0044', 'admin', '140');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d400046', 'admin', '141');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d450047', 'admin', '142');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d4a0049', 'admin', '143');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d4f004b', 'admin', '144');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d57004d', 'admin', '146');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d5c004f', 'admin', '147');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d630051', 'admin', '148');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d6b0053', 'admin', '150');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d720055', 'admin', '151');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d770057', 'admin', '152');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d7f0059', 'admin', '154');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d90005c', 'admin', '157');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d98005d', 'admin', '159');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f950694857015069749d98216d', 'admin', '224');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d73500bc', 'serverheadman', '174');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d73c00be', 'serverheadman', '175');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d74a00c0', 'serverheadman', '176');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f6ca720007', 'admin', '205');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d76200c2', 'serverheadman', '177');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d76d00c3', 'serverheadman', '178');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d77800c5', 'serverheadman', '179');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f743c5000f', 'admin', '209');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d78400c7', 'serverheadman', '180');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d78c00c8', 'serverheadman', '181');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d79300ca', 'serverheadman', '182');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f743d80017', 'admin', '213');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f743dc0018', 'admin', '214');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7a000cb', 'serverheadman', '183');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7a800cd', 'serverheadman', '184');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7b000cf', 'serverheadman', '185');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7b700d0', 'serverheadman', '186');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7ca00d1', 'serverheadman', '187');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7ce00d2', 'serverheadman', '188');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f743fc0026', 'admin', '221');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7d200d4', 'serverheadman', '189');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f74435002a', 'admin', '168');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7d700d6', 'serverheadman', '190');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d7dc00d8', 'serverheadman', '191');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d82b00d9', 'serverheadman', '192');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d83100da', 'serverheadman', '193');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d83900dc', 'serverheadman', '194');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d83e00dd', 'serverheadman', '195');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d84200de', 'serverheadman', '196');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d84700df', 'serverheadman', '197');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d84d00e0', 'serverheadman', '198');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d85a00e1', 'serverheadman', '199');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d85f00e3', 'serverheadman', '200');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f744da0041', 'admin', '180');
commit;

insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d86400e5', 'serverheadman', '201');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d86900e6', 'serverheadman', '118');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f744ea0045', 'admin', '183');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d86f00e7', 'serverheadman', '119');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d88000e8', 'serverheadman', '120');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d88500e9', 'serverheadman', '121');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d88a00ea', 'serverheadman', '122');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d88f00eb', 'serverheadman', '123');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d89300ec', 'serverheadman', '124');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d89800ed', 'serverheadman', '125');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8e500f6', 'serverheadman', '134');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7455f0060', 'admin', '200');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8ea00f7', 'serverheadman', '135');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8f000f8', 'serverheadman', '136');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f745700065', 'admin', '119');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f745760067', 'admin', '120');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8f500f9', 'serverheadman', '137');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d8fb00fa', 'serverheadman', '138');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f74588006d', 'admin', '123');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d90b00fb', 'serverheadman', '139');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d91200fc', 'serverheadman', '140');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d91d00fd', 'serverheadman', '141');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d92500fe', 'serverheadman', '142');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d92f00ff', 'serverheadman', '143');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d93c0100', 'serverheadman', '144');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9550101', 'serverheadman', '145');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d96f0102', 'serverheadman', '146');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9740103', 'serverheadman', '147');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9790104', 'serverheadman', '148');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9800105', 'serverheadman', '149');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9850106', 'serverheadman', '150');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9a10107', 'serverheadman', '151');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9a70108', 'serverheadman', '152');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9ad0109', 'serverheadman', '153');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9b2010a', 'serverheadman', '154');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9b9010b', 'serverheadman', '155');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9c5010c', 'serverheadman', '156');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9ca010d', 'serverheadman', '157');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9cf010e', 'serverheadman', '158');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e9d002a', 'server', '219');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9d7010f', 'serverheadman', '159');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ea2002c', 'server', '221');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6d7011f', 'serverheadman', '224');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f746c80097', 'admin', '145');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9dd0110', 'serverheadman', '160');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9e40111', 'serverheadman', '161');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9ef0112', 'serverheadman', '162');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d9f60113', 'serverheadman', '163');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da030114', 'serverheadman', '164');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da0b0115', 'serverheadman', '165');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da130116', 'serverheadman', '166');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f746fe00a7', 'admin', '153');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da1a0117', 'serverheadman', '115');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da220118', 'serverheadman', '116');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da2c0119', 'serverheadman', '117');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da37011a', 'serverheadman', '111');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da44011b', 'serverheadman', '112');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da4e011c', 'serverheadman', '113');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7476000b0', 'admin', '160');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7476700b2', 'admin', '161');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7476e00b4', 'admin', '162');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da57011d', 'serverheadman', '114');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2da7e011e', 'serverheadman', '110');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7479100b9', 'admin', '115');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7479900bb', 'admin', '116');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747a000bc', 'admin', '117');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747bf00c3', 'admin', '114');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747c700c4', 'admin', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747ce00c6', 'admin', '102');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747d500c8', 'admin', '103');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747dd00ca', 'admin', '104');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747ed00ce', 'admin', '106');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747f400d0', 'admin', '107');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f747fc00d2', 'admin', '108');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7480400d4', 'admin', '109');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec015068f7482900d6', 'admin', '110');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150692fd3810104', 'admin', '111');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150692fd3900106', 'admin', '112');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150692fd3a40108', 'admin', '113');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693bb7010111', 'admin', '122');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693c0f360113', 'admin', '121');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693c0f3e0115', 'admin', '124');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693c0f670117', 'admin', '125');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506e040201506e06ec780001', 'admin', '202');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693c0f70011b', 'admin', '127');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506e040201506e06ed160003', 'admin', '203');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9506e040201506e06ed1c0005', 'admin', '204');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01d4d0009', 'server', '202');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e5c000b', 'server', '203');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e60000d', 'server', '204');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e64000f', 'server', '205');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e680011', 'server', '206');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e6c0013', 'server', '207');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95068d5ec0150693c0fd40129', 'admin', '165');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e700015', 'server', '208');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e730017', 'server', '209');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e770019', 'server', '210');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e7b001b', 'server', '211');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e7f001d', 'server', '212');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e84001f', 'server', '213');
commit;

insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e880020', 'server', '214');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e8c0022', 'server', '215');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e900024', 'server', '216');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e940026', 'server', '217');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01e990028', 'server', '218');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ea6002e', 'server', '167');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01eaa002f', 'server', '168');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01eae0030', 'server', '169');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01eb20031', 'server', '170');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01eb60032', 'server', '171');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ebb0033', 'server', '172');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ee00034', 'server', '173');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ee40035', 'server', '174');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01ee90037', 'server', '175');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f080039', 'server', '177');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f0c003a', 'server', '178');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f11003c', 'server', '179');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f15003e', 'server', '180');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f1e003f', 'server', '185');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f230040', 'server', '186');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f280041', 'server', '187');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f2c0042', 'server', '188');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f310044', 'server', '189');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f360046', 'server', '191');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f3b0047', 'server', '192');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f7b0048', 'server', '193');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f80004a', 'server', '194');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f85004b', 'server', '195');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f8a004c', 'server', '196');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f8e004d', 'server', '197');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f92004e', 'server', '198');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f96004f', 'server', '199');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01f9b0051', 'server', '200');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fa00053', 'server', '201');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fa90054', 'server', '121');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fae0055', 'server', '122');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fb30056', 'server', '123');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fb80058', 'server', '124');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fbd005a', 'server', '125');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fc1005b', 'server', '126');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fc6005c', 'server', '127');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fca005d', 'server', '128');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fcf005e', 'server', '129');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fd30060', 'server', '130');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fd70061', 'server', '131');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fdc0063', 'server', '132');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fe20064', 'server', '134');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fe90065', 'server', '137');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa01fee0066', 'server', '138');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020100067', 'server', '139');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020a20068', 'server', '140');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020a90069', 'server', '141');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020ae006a', 'server', '142');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020b2006b', 'server', '143');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020b7006c', 'server', '144');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020bc006d', 'server', '145');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020c2006e', 'server', '146');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020c7006f', 'server', '147');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020cc0070', 'server', '148');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020d00071', 'server', '149');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020d50072', 'server', '150');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020da0073', 'server', '151');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020df0074', 'server', '152');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020e40075', 'server', '153');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020e80076', 'server', '154');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020ed0077', 'server', '155');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020f10078', 'server', '156');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020f60079', 'server', '157');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa020fc007a', 'server', '159');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa02103007b', 'server', '161');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa02107007c', 'server', '162');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0210c007d', 'server', '163');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa02111007e', 'server', '164');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa02115007f', 'server', '165');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0211a0080', 'server', '166');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0211f0081', 'server', '115');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa021240083', 'server', '116');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa021290084', 'server', '117');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0212f0086', 'server', '111');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa021350087', 'server', '112');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0213a0088', 'server', '113');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa0213f0089', 'server', '114');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa02155008a', 'server', '110');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d3f4008e', 'serverheadman', '202');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d40a0090', 'serverheadman', '203');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d46c0092', 'serverheadman', '204');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6370098', 'serverheadman', '207');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d642009a', 'serverheadman', '208');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d649009c', 'serverheadman', '209');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d64e009e', 'serverheadman', '210');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d65300a0', 'serverheadman', '211');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d65700a2', 'serverheadman', '212');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d65f00a4', 'serverheadman', '213');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d66400a5', 'serverheadman', '214');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d66a00a7', 'serverheadman', '215');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d66f00a9', 'serverheadman', '216');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d67500ab', 'serverheadman', '217');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d67b00ad', 'serverheadman', '218');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d68000af', 'serverheadman', '219');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6cb00b1', 'serverheadman', '220');
commit;

insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6cf00b3', 'serverheadman', '221');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6d300b5', 'serverheadman', '167');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6da00b6', 'serverheadman', '168');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d6fe00b7', 'serverheadman', '169');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d70700b8', 'serverheadman', '170');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d71c00b9', 'serverheadman', '171');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d72200ba', 'serverheadman', '172');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d72e00bb', 'serverheadman', '173');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa95f320123', 'systemfunctionary', '205');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa95fb80125', 'systemfunctionary', '209');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987e10127', 'systemfunctionary', '213');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987e40128', 'systemfunctionary', '214');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987ec012a', 'systemfunctionary', '219');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987f1012c', 'systemfunctionary', '221');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987f5012e', 'systemfunctionary', '168');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f9507d972701507db312cc0017', 'admin', '105');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987fc0130', 'systemfunctionary', '170');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987f8012f', 'systemfunctionary', '169');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1280067', 'admin', '206');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b12c0069', 'admin', '207');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b130006b', 'admin', '208');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b150006d', 'admin', '210');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b154006f', 'admin', '211');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1580071', 'admin', '212');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b15f0073', 'admin', '215');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1630075', 'admin', '216');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1830077', 'admin', '217');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1870079', 'admin', '218');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b18c007b', 'admin', '219');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b190007d', 'admin', '220');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1a1007f', 'admin', '175');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1bd0081', 'admin', '191');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1c10083', 'admin', '192');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1c60085', 'admin', '193');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1f40087', 'admin', '195');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1f80088', 'admin', '196');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b1fd0089', 'admin', '197');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b203008a', 'admin', '199');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b209008c', 'admin', '201');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b24e008d', 'admin', '155');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('4028e9f95069485701506980b253008e', 'admin', '156');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa987ff0131', 'systemfunctionary', '171');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988050133', 'systemfunctionary', '175');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988090135', 'systemfunctionary', '176');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9880f0137', 'systemfunctionary', '180');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988130138', 'systemfunctionary', '181');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98817013a', 'systemfunctionary', '182');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9881a013b', 'systemfunctionary', '183');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9881e013d', 'systemfunctionary', '184');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98821013f', 'systemfunctionary', '185');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988250140', 'systemfunctionary', '186');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988280141', 'systemfunctionary', '187');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9882b0142', 'systemfunctionary', '188');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9882f0144', 'systemfunctionary', '189');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988330146', 'systemfunctionary', '190');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9883a0148', 'systemfunctionary', '193');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9883d014a', 'systemfunctionary', '194');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98843014c', 'systemfunctionary', '197');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98847014d', 'systemfunctionary', '198');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9884a014e', 'systemfunctionary', '199');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9884e0150', 'systemfunctionary', '200');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988510152', 'systemfunctionary', '201');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9885d0153', 'systemfunctionary', '123');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9887e0155', 'systemfunctionary', '125');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988830156', 'systemfunctionary', '126');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988880157', 'systemfunctionary', '127');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988900158', 'systemfunctionary', '131');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98894015a', 'systemfunctionary', '132');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9889e015b', 'systemfunctionary', '137');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988a2015c', 'systemfunctionary', '138');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988ae015d', 'systemfunctionary', '145');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988ba015e', 'systemfunctionary', '153');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988be0160', 'systemfunctionary', '154');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988d70161', 'systemfunctionary', '157');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988dc0162', 'systemfunctionary', '159');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988e20164', 'systemfunctionary', '161');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988e50165', 'systemfunctionary', '162');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988e90166', 'systemfunctionary', '163');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988ee0167', 'systemfunctionary', '164');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988f20168', 'systemfunctionary', '165');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988f60169', 'systemfunctionary', '166');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988fa016a', 'systemfunctionary', '115');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa988fe016c', 'systemfunctionary', '116');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98904016d', 'systemfunctionary', '111');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa98908016f', 'systemfunctionary', '112');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa9890d0171', 'systemfunctionary', '113');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa989110172', 'systemfunctionary', '114');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507faa18840175', 'systemfunctionary', '211');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507faa188d0177', 'systemfunctionary', '212');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22bf0179', 'developer', '205');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22db017b', 'developer', '209');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22df017d', 'developer', '211');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22e2017f', 'developer', '212');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22e50181', 'developer', '213');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22e80182', 'developer', '214');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22f30184', 'developer', '168');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac22fe0185', 'developer', '180');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac230b0186', 'developer', '194');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d4e80094', 'serverheadman', '205');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fa2d51c0096', 'serverheadman', '206');
commit;

insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23110188', 'developer', '198');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23150189', 'developer', '200');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac231d018b', 'developer', '123');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2390018d', 'developer', '125');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2393018e', 'developer', '126');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2396018f', 'developer', '127');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac239d0190', 'developer', '131');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23c50192', 'developer', '132');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23cc0193', 'developer', '137');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23cf0194', 'developer', '138');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23d70195', 'developer', '145');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23fc0197', 'developer', '153');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac23ff0199', 'developer', '154');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2404019a', 'developer', '157');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2408019b', 'developer', '159');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2410019e', 'developer', '162');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac2413019f', 'developer', '163');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac241701a0', 'developer', '164');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac241a01a1', 'developer', '165');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac241e01a2', 'developer', '166');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff808081507f900101507fac240d019d', 'developer', '161');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b228f06a0024', 'systemfunctionary', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b228f0e90026', 'systemfunctionary', '103');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b228f0f10028', 'systemfunctionary', '105');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2292f70004f', 'developer', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2292f770051', 'developer', '103');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2292f7d0053', 'developer', '105');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2295acc007a', 'server', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2295ad4007c', 'server', '103');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b2295adb007e', 'server', '105');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b229839200a5', 'serverheadman', '101');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b229839a00a7', 'serverheadman', '103');
insert into ROLE_MENU_RELATION (id, role_id, sg_menu_id)
values ('ff80808150b225410150b22983a100a9', 'serverheadman', '105');
commit;

insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0aa40077', '179', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0bcb007a', '183', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0bd2007c', '185', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0bd6007e', '186', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0be10082', '187', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0bf50087', '156', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c1e008d', '149', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c460092', '175', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c7a0096', '120', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c800098', '122', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c85009a', '123', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c8a009c', '124', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c95009f', '128', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0c9a00a1', '129', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0ca900a4', '134', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0cbb00a8', '140', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0ccb00ab', '148', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0ce800b3', '152', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150925f0cf200b6', '154', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5c300f2', '179', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5cc00f5', '183', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5d200f7', '185', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5d600f9', '186', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5e000fd', '187', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5ea0100', '192', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5f00102', '194', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5f90106', '156', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f5fe0108', '157', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f602010a', '158', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f607010c', '159', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f60e010e', '163', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6130110', '164', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6220114', '167', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f62b0117', '168', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6300119', '169', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f644011f', '170', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6480121', '171', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f64d0123', '172', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6560126', '173', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f65b0128', '149', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f689012e', '174', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f68e0130', '175', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6a30135', '120', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6aa0137', '122', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6af0139', '123', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6b4013b', '124', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6bf013e', '128', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6c40140', '129', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6d30143', '134', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6e50147', '140', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f6f6014a', '148', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f7110152', '152', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f71a0155', '154', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f7460161', '113', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f7580166', '111', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509263f75e0168', '112', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647245019a', '176', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647265017a', '197', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647249019c', '177', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264724d019e', '178', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264725201a0', '179', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264725801a3', '180', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264725c01a5', '181', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264726101a7', '182', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264726501a9', '183', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264726901ab', '184', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264726d01ad', '185', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264727201af', '186', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264727c01b3', '187', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264728001b5', '188', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264728401b7', '189', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264728901b9', '190', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264729001bc', '191', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264729501be', '192', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264729901c0', '193', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264729f01c2', '194', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472a801c6', '155', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472ac01c8', '156', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472b101ca', '157', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472b601cc', '158', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472bb01ce', '159', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472bf01d0', '160', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472c401d2', '161', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472c901d4', '162', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472ce01d6', '163', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472d301d8', '164', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472d801da', '195', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472dd01dc', '196', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472e801e0', '165', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472ed01e2', '166', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926472f701e5', '167', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264730201e8', '168', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264732601ea', '169', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264733d01f0', '170', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264734201f2', '171', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264734701f4', '172', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264736601f7', '150', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264736b01f9', '151', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264737101fb', '173', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264737601fd', '149', 'admin');
commit;

insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473960205', '174', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264739c0207', '175', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473ac020c', '115', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473b2020e', '116', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473b80210', '117', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473bd0212', '118', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473c30214', '119', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473dc0216', '120', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473e20218', '121', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473e8021a', '122', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473ee021c', '123', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473f5021e', '124', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926473fd0221', '125', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474040223', '126', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264740a0225', '127', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474110227', '128', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474170229', '129', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264741d022b', '130', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647423022d', '131', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264742a022f', '132', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474300231', '133', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264743d0234', '134', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264744d0237', '135', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474530239', '136', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647462023d', '137', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647468023f', '138', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264746e0241', '139', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474750243', '140', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264747d0246', '141', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474840248', '142', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264748a024a', '143', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474a9024c', '144', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474b0024e', '145', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474b70250', '146', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474bd0252', '147', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926474c40254', '148', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264751e025f', '152', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926475280262', '153', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264757d0264', '154', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926475b10270', '113', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5990339', '128', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926475bf0273', '114', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c59f033b', '129', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926475cb0277', '111', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926475d20279', '112', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926476130280', '101', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264761a0282', '102', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926476220284', '103', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926476290286', '104', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c0150926476300288', '105', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264763a028b', '106', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647660028d', '107', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c015092647668028f', '108', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264766f0291', '109', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509264767a0294', '110', 'admin');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3aa02bf', '176', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3ae02c1', '177', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3b202c3', '178', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3b602c5', '179', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3bc02c8', '180', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3c002ca', '181', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3c402cc', '182', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3c802ce', '183', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3cc02d0', '184', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3d002d2', '185', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3d402d4', '186', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3de02d8', '187', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3e202da', '188', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3e602dc', '189', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3eb02de', '190', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3f102e1', '191', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3f602e3', '192', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c3fb02e5', '194', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c41702e9', '155', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c41b02eb', '156', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c42002ed', '157', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c42502ef', '158', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c42902f1', '159', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c42e02f3', '160', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c43302f5', '161', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c44b02f7', '162', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c45002f9', '163', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c45602fb', '195', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c45b02fd', '196', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c4660301', '165', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c46b0303', '166', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c48a030a', '170', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c48f030c', '171', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c498030f', '150', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c49d0311', '151', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c4df0313', '173', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c4e50315', '149', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c503031d', '174', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c508031f', '175', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c51c0324', '118', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5210326', '119', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5270328', '120', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c52c032a', '121', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c532032c', '122', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c556032e', '123', 'server');
commit;

insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c55c0330', '124', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5870333', '125', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c58d0335', '126', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5930337', '127', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5a6033d', '131', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5b50340', '134', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5bd0343', '135', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5c30345', '136', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5d10349', '137', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5d7034b', '138', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5dd034d', '139', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5e4034f', '140', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5ec0352', '141', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5f20354', '142', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5f80356', '143', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c5ff0358', '144', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c605035a', '145', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c62a035c', '146', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c631035e', '147', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c6380360', '148', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c664036a', '152', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c66f036d', '154', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c6a20379', '113', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c6af037c', '114', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c6d00380', '111', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c6d70382', '112', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509267c70f038b', '110', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c12403b6', '176', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c12803b8', '177', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c12c03ba', '178', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c12f03bc', '179', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c13503bf', '180', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c13903c1', '181', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c13d03c3', '182', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c14103c5', '183', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c14503c7', '184', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c14903c9', '185', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c14d03cb', '186', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c15803cf', '187', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c15c03d1', '188', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c16003d3', '189', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c16503d5', '190', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c16b03d8', '191', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c17003da', '192', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c17403dc', '193', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c17803de', '194', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c18103e2', '155', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c18503e4', '156', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c18a03e6', '157', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c18e03e8', '158', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c19303ea', '159', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c19803ec', '160', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c19c03ee', '161', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1a103f0', '162', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1a603f2', '163', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1aa03f4', '164', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1af03f6', '195', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1b403f8', '196', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1c003fc', '165', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1c403fe', '166', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1ce0401', '167', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1f50404', '168', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c1fa0406', '169', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c210040c', '170', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c215040e', '171', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c21a0410', '172', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2220413', '150', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2270415', '151', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c22d0417', '173', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2320419', '149', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2500421', '174', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2560423', '175', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2650428', '115', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c26b042a', '116', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c271042c', '117', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c276042e', '118', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c27c0430', '119', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2820432', '120', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2870434', '121', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2b70436', '122', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2cd043d', '125', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2d4043f', '126', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2e00443', '128', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2e60445', '129', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2ec0447', '130', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2f20449', '131', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2f8044b', '132', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2ff044d', '133', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c30b0450', '134', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3140453', '135', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c31a0455', '136', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3290459', '137', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c34c045b', '138', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c353045d', '139', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c35a045f', '140', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3630462', '141', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c36a0464', '142', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3710466', '143', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3780468', '144', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c37e046a', '145', 'serverheadman');
commit;

insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c385046c', '146', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c38c046e', '147', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3920470', '148', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3c7047b', '152', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3d7047e', '153', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c3de0480', '154', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c412048c', '113', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c420048f', '114', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c42c0493', '111', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c4330495', '112', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c465049e', '110', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2be0438', '123', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2c4043a', '124', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150921f4c01509269c2da0441', '127', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b228f0bb0025', '101', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b228f0ea0027', '103', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b228f0f40029', '105', 'systemfunctionary');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2292f710050', '101', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2292f780052', '103', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2292f7e0054', '105', 'developer');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2295acd007b', '101', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2295ad5007d', '103', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b2295add007f', '105', 'server');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b229839400a6', '101', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b229839b00a8', '103', 'serverheadman');
insert into ROLE_PERMISSION_RELATION (id, permission_id, role_id)
values ('ff80808150b225410150b22983a300aa', '105', 'serverheadman');
commit;



delete from ENUM_ELEMENT_MAP;
commit;

delete from ENUM_ELEMENTS;
commit;


delete from ENUM;
commit;

delete from MASTER_SLAVE_ENUM_MAP;
commit;
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac5d7e000056', '统一支付平台', '1', '1', 'EDUCATION', '2015-09-08 17:50:52', 'admin', null, '学历', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('4028ead54fab95fe014faba29dc00014', '统一支付平台', '1', '1', 'GLOBAL_TYPE', '2015-09-08 14:26:45', 'admin', null, '证件类型', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac71bf8100a3', '统一支付平台', '1', '1', 'CCY', '2015-09-08 18:12:59', 'admin', null, '币种', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac7bc7cc00a6', '统一支付平台', '1', '1', 'SOURCE_TYPE', '2015-09-08 18:23:57', 'admin', null, '渠道类型', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac7c167500a7', '统一支付平台', '1', '1', 'ACCT_TYPE', '2015-09-08 18:24:17', 'admin', null, '账户类型', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac4890850023', '核心业务系统', '0', '1', '证件类型', '2015-09-08 17:28:00', 'admin', null, null, '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac585a390046', '统一支付平台', '1', '1', 'GENDER', '2015-09-08 17:45:15', 'admin', null, '性别', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac5b5003004d', '统一支付平台', '1', '1', 'MARITAL_STATUS', '2015-09-08 17:48:29', 'admin', null, '婚姻状况', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('ff8080814fac41c3014fac7b6fcc00a5', '统一支付平台', '1', '1', 'COUNTRY_CODE', '2015-09-08 18:23:34', 'admin', null, '国家代码', '1', null);
insert into ENUM (id, data_source, is_master, is_standard, name, opt_date, opt_user, process_id, remark, status, version)
values ('4028e9f94faf75ea014faf94116b001b', '核心业务系统', '0', '1', '币种', '2015-09-09 08:49:20', 'admin', null, null, '1', null);
commit;


insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X174', null, 'NIC', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '尼加拉瓜');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X175', null, 'NIU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '纽埃');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X176', null, 'NLD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '荷兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X177', null, 'NOR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '挪威');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X178', null, 'NPL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '尼泊尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X179', null, 'NRU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瑙鲁');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X180', null, 'NZL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '新西兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X181', null, 'OMN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿曼');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X182', null, 'PAK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴基斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X183', null, 'PAN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴拿马');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X184', null, 'PCN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '皮特凯恩群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X185', null, 'PER', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '秘鲁');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X186', null, 'PHL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '菲律宾');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X187', null, 'PLW', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '贝劳');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X188', null, 'PNG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴布亚新几内亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X189', null, 'POL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '波兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X190', null, 'PRI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '波多黎各');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X191', null, 'PRK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '朝鲜');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X192', null, 'PRT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '葡萄牙');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X193', null, 'PRY', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴拉圭');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X194', null, 'PSE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴基斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X195', null, 'PYF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '法属波利尼西亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X196', null, 'QAT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '卡塔尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X197', null, 'REU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '留尼汪');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X198', null, 'ROM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '罗马尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X199', null, 'RUS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '俄罗斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X200', null, 'RWA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '卢旺达');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X201', null, 'SAU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '沙竺阿拉伯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X202', null, 'SCG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塞尔维亚和黑山');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X203', null, 'SDN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '苏丹');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X204', null, 'SEN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塞内加尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X205', null, 'SGS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '南乔治亚岛和南桑德韦奇岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X206', null, 'SHN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣赫勒拿');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X207', null, 'SJM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斯瓦尔巴群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X208', null, 'SLB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '所罗门群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X209', null, 'SLE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塞拉利昂');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X210', null, 'SLV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '萨尔瓦多');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X211', null, 'SMR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣马力诺');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X212', null, 'SOM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '索马里');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X213', null, 'SPM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣皮埃尔和密克隆');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X214', null, 'STP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣多美和普林西比');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X215', null, 'SUR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '苏里南');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X216', null, 'SVK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斯洛伐克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X217', null, 'SVN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斯洛文尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X218', null, 'SWE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瑞典');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X219', null, 'SWZ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斯威士兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X220', null, 'SYC', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塞舌尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X221', null, 'SYR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '叙利亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X222', null, 'TCA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '特克斯科斯群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X223', null, 'TCD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '乍得');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X224', null, 'TGO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '多哥');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X225', null, 'THA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '泰国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X226', null, 'TJK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塔吉克斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X227', null, 'TKL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '托克劳');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X228', null, 'TKM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '土库曼斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X229', null, 'TMP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '东帝汶');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X230', null, 'TON', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '汤加');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X231', null, 'TTO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '特立尼达和多巴哥');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X232', null, 'TUN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '突尼斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X233', null, 'TUR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '土耳其');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X234', null, 'TUV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '图瓦卢');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X235', null, 'TWN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '中国台湾');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X236', null, 'TZA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '坦桑尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X237', null, 'UGA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '乌干达');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X238', null, 'UKR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '乌克兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5ddc2c0057', null, '12', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:51:16', 'admin', null, '博士研究生');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4d4d550027', null, 'S', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:33:11', 'admin', null, '士兵证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4d91730028', null, 'ST', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:33:28', 'admin', null, '股东代码证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4ddf0f0029', null, 'HI', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:33:48', 'admin', null, '港澳身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4e2154002a', null, 'TI', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:34:05', 'admin', null, '台湾居民身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4e8ea5002b', null, 'TT', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:34:33', 'admin', null, '台胞通行证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4f013f002c', null, 'GT', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:35:02', 'admin', null, '港澳台通行证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4f3e46002d', null, 'F', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:35:18', 'admin', null, '户口簿');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4f6f74002e', null, 'FP', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:35:31', 'admin', null, '外国护照');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4fbdba002f', null, 'PO', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:35:51', 'admin', null, '警官证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4ffd430030', null, 'T', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:36:07', 'admin', null, '临时证件');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5036d80031', null, 'TEI', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:36:22', 'admin', null, '临时身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5062d90032', null, 'H', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:36:33', 'admin', null, '回乡证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac509a940033', null, 'CP', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:36:47', 'admin', null, '文职证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac50e3800034', null, 'FL', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:37:06', 'admin', null, '外国人居留证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5121d80035', null, 'PRO', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:37:22', 'admin', null, '证明');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac52532b0036', null, 'A', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:38:40', 'admin', null, '技术监督局');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac52fdba0037', null, 'L', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:39:24', 'admin', null, '营业执照');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac53af130038', null, 'C', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:40:09', 'admin', null, '行政机关');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac542d970039', null, 'Z', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:40:42', 'admin', null, '社团登记证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5481da003a', null, 'BR', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:41:03', 'admin', null, '工商登记证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac54db88003b', null, 'TR', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:41:26', 'admin', null, '税务登记证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5529c0003c', null, 'ON', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:41:46', 'admin', null, '组织机构代码');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac556dea003d', null, 'LP', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:42:04', 'admin', null, '法人代码证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6a9c510067', null, '31', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:05:12', 'admin', null, '大专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6ac7450068', null, '38', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:05:23', 'admin', null, '相当大专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6b06360069', null, '39', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:05:39', 'admin', null, '大专肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6b2799006a', null, '41', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:05:47', 'admin', null, '中专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6b4e44006b', null, '42', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:05:57', 'admin', null, '中技');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6b7977006c', null, '43', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:06:08', 'admin', null, '高中专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6bcbca006d', null, '44', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:06:29', 'admin', null, '初中专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6bfed1006e', null, '45', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:06:42', 'admin', null, '四年制中专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6c30b0006f', null, '48', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:06:55', 'admin', null, '相当中专');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6c61400070', null, '49', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:07:08', 'admin', null, '中专肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6c9ac10071', null, '51', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:07:22', 'admin', null, '技工学校毕业');
commit;


insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6cc6a20072', null, '59', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:07:34', 'admin', null, '技工学校肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6ce6460074', null, '61', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:07:42', 'admin', null, '高中');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6d07b90075', null, '62', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:07:50', 'admin', null, '职高');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6d2de10076', null, '63', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:00', 'admin', null, '农高');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6d57b10077', null, '68', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:11', 'admin', null, '相当高中');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6d7e360078', null, '69', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:21', 'admin', null, '高中肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6da55d0079', null, '71', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:31', 'admin', null, '初中');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6dd496007a', null, '72', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:43', 'admin', null, '职业初中毕业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6e04be007b', null, '73', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:08:55', 'admin', null, '农业初中毕业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6e3c08007c', null, '78', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:09:09', 'admin', null, '相当初中');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6e6640007d', null, '79', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:09:20', 'admin', null, '初中肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6e8c44007e', null, '81', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:09:30', 'admin', null, '小学');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6eb085007f', null, '88', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:09:39', 'admin', null, '相当小学毕业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6ee3650086', null, '89', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:09:52', 'admin', null, '小学肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6f2dde0087', null, '90', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:10:11', 'admin', null, '文盲或半文盲');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac6f50260088', null, '99', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 18:10:20', 'admin', null, '未知');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac617cfd0060', null, '29', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:55:14', 'admin', null, '大学肄业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028ead54fab95fe014faba39f280015', null, 'I', '4028ead54fab95fe014faba29dc00014', '2015-09-08 14:27:51', 'admin', null, '身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac72a75700a4', null, 'CNY', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 18:13:59', 'admin', null, '人民币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5e128b0058', null, '13', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:51:30', 'admin', null, '硕士研究生');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5e35c50059', null, '14', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:51:39', 'admin', null, '研究生毕业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5e6efc005a', null, '19', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:51:54', 'admin', null, '研究生结业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5e9720005b', null, '21', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:52:04', 'admin', null, '大学');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5eec14005c', null, '22', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:52:26', 'admin', null, '六年制大学毕业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5f24aa005d', null, '23', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:52:40', 'admin', null, '六年制大学结业');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5f64d3005e', null, '24', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:52:57', 'admin', null, '双学历本科');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5ff6bb005f', null, '28', 'ff8080814fac41c3014fac5d7e000056', '2015-09-08 17:53:34', 'admin', null, '相当大学');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac48e28a0024', null, '0', 'ff8080814fac41c3014fac4890850023', '2015-09-08 17:28:21', 'admin', null, '身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4cc0260025', null, 'P', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:32:35', 'admin', null, '护照');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac4cf5ad0026', null, 'J', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:32:48', 'admin', null, '军官证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X042', null, 'AZE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿塞拜疆');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X043', null, 'BDI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '布隆迪');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac55adb3003e', null, 'W', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:42:20', 'admin', null, '军队开户证明');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac55f835003f', null, 'D', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:42:39', 'admin', null, '武警');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5633e90040', null, 'B', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:42:54', 'admin', null, '下属机构');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac56896f0041', null, 'B', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:43:16', 'admin', null, '下属机构');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac56b54e0042', null, 'K', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:43:27', 'admin', null, '基金会');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5712350043', null, 'X', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:43:51', 'admin', null, '开户许可证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac577e6f0044', null, 'Y', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:44:19', 'admin', null, '机关编辑批文');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac57a7ce0045', null, 'OT', '4028ead54fab95fe014faba29dc00014', '2015-09-08 17:44:29', 'admin', null, '其他证件');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5948d90047', null, '0', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:46:16', 'admin', null, '未知的性别');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5967230048', null, '1', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:46:24', 'admin', null, '男');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac598b090049', null, '2', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:46:33', 'admin', null, '女');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac59ced4004a', null, '3', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:46:50', 'admin', null, '女性改（变）为男性');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5a1f9d004b', null, '4', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:47:11', 'admin', null, '男性改（变）为女性');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5a63a0004c', null, '9', 'ff8080814fac41c3014fac585a390046', '2015-09-08 17:47:29', 'admin', null, '未说明的性别');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5ba2ea004e', null, '10', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:48:50', 'admin', null, '未婚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5bd77e004f', null, '20', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:49:04', 'admin', null, '有配偶');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5c00b60050', null, '21', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:49:14', 'admin', null, '初婚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5c33700051', null, '22', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:49:27', 'admin', null, '再婚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5c5b130052', null, '23', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:49:37', 'admin', null, '复婚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5cb2590053', null, '30', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:50:00', 'admin', null, '丧偶');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5cd85e0054', null, '40', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:50:10', 'admin', null, '离婚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5cffbe0055', null, '90', 'ff8080814fac41c3014fac5b5003004d', '2015-09-08 17:50:20', 'admin', null, '其它');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X001', null, 'CNY', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '人民币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X002', null, 'USD', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '美元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X003', null, 'HKD', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '港币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X004', null, 'EUR', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '欧元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X005', null, 'JPY', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '日元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X006', null, 'GBP', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '英镑');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X007', null, 'AUD', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '澳大利亚元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X008', null, 'CAD', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '加拿大元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X009', null, 'CHF', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '瑞士法郎');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X010', null, 'DEM', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '德国马克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X011', null, 'SGD', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '新加坡元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X012', null, 'BEF', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '比利时法郎');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X013', null, 'MOP', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '澳门币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X014', null, 'NLG', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '荷兰盾');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X015', null, 'DKK', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '丹麦克郎');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X016', null, 'SEK', 'ff8080814fac41c3014fac71bf8100a3', '2015-09-08 17:51:16', 'admin', null, '瑞典克朗');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X017', null, 'CHN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '中国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X018', null, 'DEU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '德国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X019', null, 'FRA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '法国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X020', null, 'GBR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '英国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X021', null, 'JPN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '日本');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X022', null, 'USA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '美国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X023', null, 'CAN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '加拿大');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X024', null, 'AUS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '澳大利亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X025', null, 'CHE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瑞士');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X026', null, 'SGP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '新加坡');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X027', null, 'ABW', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿鲁巴');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X028', null, 'AFG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿富汗');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X029', null, 'AGO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '安哥拉');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X030', null, 'AIA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '安圭拉');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X031', null, 'ALB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿尔巴尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X032', null, 'AND', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '安道尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X033', null, 'ANT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '荷属安的列斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X034', null, 'ARE ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿闻酋');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X035', null, 'ARG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿根廷');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X036', null, 'ARM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '亚美尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X037', null, 'ASM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '美属萨摩亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X038', null, 'ATA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '南极洲');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X039', null, 'ATF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '法属南部领土');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X040', null, 'ATG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '安提瓜和巴布达');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X041', null, 'AUT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '奥地利');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X101', null, 'GLP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瓜德罗普');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X102', null, 'GMB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '冈比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X103', null, 'GNB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '几内亚比绍');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X104', null, 'GNQ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '赤道几内亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X105', null, 'GRC', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '希腊');
commit;


insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X106', null, 'GRD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '格林纳达');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X107', null, 'GRL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '格陵兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X108', null, 'GTM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '危地马拉');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X044', null, 'BEL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '比利时');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X045', null, 'BEN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '贝宁');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X046', null, 'BFA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '布基纳法索');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X047', null, 'BGD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '孟加拉国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X048', null, 'BGR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '保加利亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X049', null, 'BHR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴林');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X050', null, 'BHS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴哈马');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X051', null, 'BIH', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '波斯尼亚和黑塞哥维那');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X052', null, 'BLR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '白俄罗斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X053', null, 'BLZ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '伯利兹');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X054', null, 'BMU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '百慕大');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X055', null, 'BOL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '玻利维亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X056', null, 'BRA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴西');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X057', null, 'BRB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '巴巴多斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X058', null, 'BRN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '文莱');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X059', null, 'BTN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '不丹');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X060', null, 'BVT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '布维岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X061', null, 'BWA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '博茨瓦纳');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X062', null, 'CAF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '中非');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X063', null, 'CCK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '科科斯(基林)群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X064', null, 'CHL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '智利');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X065', null, 'CIV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '科特迪瓦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X066', null, 'CMR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '喀麦隆');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X067', null, 'COD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '刚果(金)');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X068', null, 'COG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '刚果(布)');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X069', null, 'COK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '库克群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X070', null, 'COL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '哥伦比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X071', null, 'COM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '科摩罗');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X072', null, 'CPV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '佛得角');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X073', null, 'CRI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '哥斯达黎加');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X074', null, 'CSR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣诞岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X075', null, 'CUB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '古巴');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X076', null, 'CYM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '开曼群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X077', null, 'CYP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '塞浦路斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X078', null, 'CZE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '捷克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X079', null, 'DJI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '吉布提');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X080', null, 'DMA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '多米尼克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X081', null, 'DNK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '丹麦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X082', null, 'DOM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '多米尼加共和国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X083', null, 'DZA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '阿尔及利亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X084', null, 'ECU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '厄瓜多尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X085', null, 'EGY', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '埃及');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X086', null, 'ERI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '厄立特里亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X087', null, 'ESH', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '西撒哈拉');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X088', null, 'ESP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '西班牙');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X089', null, 'EST', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '爱沙尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X090', null, 'ETH', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '埃塞俄比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X091', null, 'FIN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '芬兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X092', null, 'FJI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斐济');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X093', null, 'FLK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马尔维纳斯群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X094', null, 'FRO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '法罗群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X095', null, 'FSM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '密克罗尼西亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X096', null, 'GAB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '加蓬');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X097', null, 'GEO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '格鲁吉亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X098', null, 'GHA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '加纳');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X099', null, 'GIB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '直布罗陀');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X100', null, 'GIN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '几内亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X126', null, 'JAM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '牙买加');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X127', null, 'JOR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '约旦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X128', null, 'KAZ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '哈萨克斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X129', null, 'KEN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '肯尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X130', null, 'KGZ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '吉尔吉斯斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X131', null, 'KHM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '柬埔寨');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X132', null, 'KIR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '基里巴斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X133', null, 'KNA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣基茨和尼维斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X134', null, 'KOR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '韩国');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X135', null, 'KWT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '科威特');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X136', null, 'LAO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '老挝');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X137', null, 'LBN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '黎巴嫩');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X138', null, 'LBR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '利比里亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X139', null, 'LBY', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '利比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X140', null, 'LCA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣卢西亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X141', null, 'LIE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '列支敦士登');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X142', null, 'LKA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '斯里兰卡');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X143', null, 'LSO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '莱索托');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X144', null, 'LTU', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '立陶宛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X145', null, 'LUX', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '卢森堡');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X146', null, 'LVA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '拉脱维亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X147', null, 'MAC', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '澳门');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X148', null, 'MAR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '摩洛哥');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X149', null, 'MCO', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '摩纳哥');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X150', null, 'MDA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '摩尔多瓦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X151', null, 'MDG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马达加斯加');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X152', null, 'MDV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马尔代夫');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X153', null, 'MEX', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '墨西哥');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X154', null, 'MHL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马绍尔群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X155', null, 'MKD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马斯顿');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X156', null, 'MLI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马里');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X157', null, 'MLT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马耳他');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X158', null, 'MMR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '缅甸');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X159', null, 'MNG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '蒙古');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X160', null, 'MNP', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '北马里亚纳');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X161', null, 'MOZ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '莫桑比克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X162', null, 'MRT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '毛里塔尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X163', null, 'MSR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '蒙特塞拉特');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X164', null, 'MTQ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马提尼克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X165', null, 'MUS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '毛里求斯');
commit;


insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X166', null, 'MWI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马拉维');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X167', null, 'MYS', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马来西亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X168', null, 'MYT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '马约特');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X169', null, 'NAM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '纳米比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X170', null, 'NCL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '新喀里多尼亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X171', null, 'NER', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '尼日尔');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X172', null, 'NFK', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '诺福克岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X173', null, 'NGA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '尼日利亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X109', null, 'GUF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '法属圭亚那');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X110', null, 'GUM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '关岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X111', null, 'GUY', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圭亚那');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X112', null, 'HKG', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '香港');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X113', null, 'HMD', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '赫德岛和麦克唐纳岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X114', null, 'HND', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '洪都拉斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X115', null, 'HRV', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '克罗地亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X116', null, 'HTI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '海地');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X117', null, 'HUN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '匈牙利');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X118', null, 'IDN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '印度尼西亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X119', null, 'IOT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '英属印度洋领土');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X120', null, 'IRL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '爱尔兰');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X121', null, 'IRN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '伊朗');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X122', null, 'IRQ', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '伊拉克');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X123', null, 'ISL', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '冰岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X124', null, 'ISR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '以色列');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X125', null, 'ITA', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '意大利');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7b4e700003', null, '1', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:22:17', 'admin', null, '户口簿');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X239', null, 'UMI', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '美属太平洋各群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X240', null, 'URY', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '乌拉圭');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X241', null, 'UZB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '乌兹别克斯坦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X242', null, 'VAT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '梵蒂冈');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X243', null, 'VCT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '圣文森特和格林纳丁斯');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X244', null, 'VEN', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '委内瑞拉');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X245', null, 'VGB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '英属维尔京群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X246', null, 'VIR', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '美属维尔京群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X247', null, 'VNM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '越南');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X248', null, 'VUT', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瓦努阿图');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X249', null, 'WLF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '瓦利斯和富图纳群岛');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X250', null, 'WSM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '西萨摩亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X251', null, 'YEM', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '也门');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X252', null, 'ZAF', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '南非');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X253', null, 'ZMB', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '赞比亚');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X254', null, 'ZWE', 'ff8080814fac41c3014fac7b6fcc00a5', '2015-09-08 17:51:16', 'admin', null, '津巴布韦');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X255', null, 'MT', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '柜面交易');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X256', null, 'PB', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '个人网银');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X257', null, 'CB', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '企业网银');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X258', null, 'MB', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '手机银行');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X259', null, 'TB', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '电话银行');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X260', null, 'AM', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '自动终端 ');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X261', null, 'AP', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '移动终端');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X262', null, 'FP', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '网关接收来账');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X263', null, 'AC', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '自动发卡机');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X264', null, 'WB', 'ff8080814fac41c3014fac7bc7cc00a6', '2015-09-08 17:51:16', 'admin', null, '微信银行');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X265', null, '1', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '基本户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X266', null, '2', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '一般户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X267', null, '3', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '专用户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X268', null, '4', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '临时户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X269', null, '5', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '普通户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X270', null, '6', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '内部户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X271', null, '7', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '贷款户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('ff8080814fac41c3014fac5d7e00X272', null, '8', 'ff8080814fac41c3014fac7c167500a7', '2015-09-08 17:51:16', 'admin', null, '协议户');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf94fc230020', null, '27', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:50:20', 'admin', null, '日元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf9528a60021', null, '38', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:50:32', 'admin', null, '欧元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7c131b0007', null, '4', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:23:08', 'admin', null, '士兵证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7b958c0005', null, '2', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:22:36', 'admin', null, '护照');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7bd08b0006', null, '3', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:22:51', 'admin', null, '军官证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7c4c2f0008', null, '5', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:23:22', 'admin', null, '港澳通行证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7caad40009', null, '6', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:23:47', 'admin', null, '台胞通行证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7ce89c000a', null, '7', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:24:02', 'admin', null, '临时身份证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7dbe64000b', null, '8', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:24:57', 'admin', null, '外国人居留证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7e0ae0000c', null, '9', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:25:17', 'admin', null, '警官证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7e3d19000d', null, '10', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:25:30', 'admin', null, '无身份证1');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7e6d60000e', null, '11', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:25:42', 'admin', null, '其他证件');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7ea85b000f', null, '12', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:25:57', 'admin', null, '组织机构代码证');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7ed9880010', null, '13', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:26:10', 'admin', null, '营业执照');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7f01100011', null, '14', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:26:20', 'admin', null, '批文');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7f33820012', null, '15', 'ff8080814fac41c3014fac4890850023', '2015-09-09 08:26:33', 'admin', null, '主管证明');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7fd7100014', null, '1', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:27:15', 'admin', null, '人民币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf7ffebf0015', null, '12', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:27:25', 'admin', null, '英镑');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf802eac0016', null, '13', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:27:37', 'admin', null, '港币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf80551c0017', null, '14', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:27:47', 'admin', null, '美元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf80b26b0018', null, '27', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:28:11', 'admin', null, '日元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf80dd6a0019', null, '38', '4028e9f94faf75ea014faf7f73f80013', '2015-09-09 08:28:22', 'admin', null, '欧元');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf9456cf001c', null, '1', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:49:38', 'admin', null, '人民币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf9489dc001d', null, '12', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:49:51', 'admin', null, '英镑');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf94be16001e', null, '13', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:50:04', 'admin', null, '港币');
insert into ENUM_ELEMENTS (element_id, buss_define, element_name, enum_id, opt_date, opt_user, process_id, remark)
values ('4028e9f94faf75ea014faf94dd2e001f', null, '14', '4028e9f94faf75ea014faf94116b001b', '2015-09-09 08:50:12', 'admin', null, '美元');
commit;


insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('ff8080814fac41c3014fac48e28a0024', '4028ead54fab95fe014faba39f280015', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7c4c2f0008', 'ff8080814fac41c3014fac4f013f002c', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7b4e700003', 'ff8080814fac41c3014fac4f3e46002d', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7caad40009', 'ff8080814fac41c3014fac4e8ea5002b', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7ce89c000a', 'ff8080814fac41c3014fac5036d80031', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7dbe64000b', 'ff8080814fac41c3014fac50e3800034', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7e0ae0000c', 'ff8080814fac41c3014fac4fbdba002f', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7e3d19000d', 'ff8080814fac41c3014fac53af130038', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7e6d60000e', 'ff8080814fac41c3014fac57a7ce0045', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7ea85b000f', 'ff8080814fac41c3014fac5529c0003c', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7ed9880010', 'ff8080814fac41c3014fac52fdba0037', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7f01100011', 'ff8080814fac41c3014fac577e6f0044', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7f33820012', 'ff8080814fac41c3014fac5121d80035', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf9456cf001c', 'ff8080814fac41c3014fac5d7e00X001', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf94dd2e001f', 'ff8080814fac41c3014fac5d7e00X002', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf94be16001e', 'ff8080814fac41c3014fac5d7e00X003', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf9528a60021', 'ff8080814fac41c3014fac5d7e00X004', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf94fc230020', 'ff8080814fac41c3014fac5d7e00X005', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf9489dc001d', 'ff8080814fac41c3014fac5d7e00X006', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7b958c0005', 'ff8080814fac41c3014fac4cc0260025', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7bd08b0006', 'ff8080814fac41c3014fac4cf5ad0026', null, null);
insert into ENUM_ELEMENT_MAP (slave_element_id, master_element_id, direction, mapping_relation)
values ('4028e9f94faf75ea014faf7c131b0007', 'ff8080814fac41c3014fac4d4d550027', null, null);
commit;


insert into MASTER_SLAVE_ENUM_MAP (slave_id, master_id)
values ('4028e9f94faf75ea014faf7f73f80013', '4028ead54fab95fe014faba29dc00014');
insert into MASTER_SLAVE_ENUM_MAP (slave_id, master_id)
values ('4028e9f94faf75ea014faf94116b001b', 'ff8080814fac41c3014fac71bf8100a3');
insert into MASTER_SLAVE_ENUM_MAP (slave_id, master_id)
values ('ff8080814fac41c3014fac4890850023', '4028ead54fab95fe014faba29dc00014');
commit;

insert into SLA (sla_id, operation_id, service_id, sla_desc, sla_name, sla_remark, sla_template_id, sla_value, version)
values ('ff8080814fd4f5c8014fd4f79e7b0007', null, null, '服务总线上处理所有业务的成功数', '业务成功率', null, '4028eabb4fd419c7014fd41ab1290001', '99.99%', null);
insert into SLA (sla_id, operation_id, service_id, sla_desc, sla_name, sla_remark, sla_template_id, sla_value, version)
values ('ff8080814fd4f5c8014fd4f79e7d0008', null, null, '服务消费者发起请求调用ESB服务到接收消息响应的平均处理时间', '平均响应时间', null, '4028eabb4fd419c7014fd41ab1290001', '300ms', null);
insert into SLA (sla_id, operation_id, service_id, sla_desc, sla_name, sla_remark, sla_template_id, sla_value, version)
values ('ff8080814fd4f5c8014fd4f79e650005', null, null, '同一时刻服务请求的数量', '并发数', null, '4028eabb4fd419c7014fd41ab1290001', '260', null);
insert into SLA (sla_id, operation_id, service_id, sla_desc, sla_name, sla_remark, sla_template_id, sla_value, version)
values ('ff8080814fd4f5c8014fd4f79e680006', null, null, '与ESB存在调用关系的前后端系统能够接受的交易处理时间范围', '超时时间', null, '4028eabb4fd419c7014fd41ab1290001', '60-120', null);
insert into SLA (sla_id, operation_id, service_id, sla_desc, sla_name, sla_remark, sla_template_id, sla_value, version)
values ('ff8080814fd4f5c8014fd4f79e680007', null, null, 'ESB内部各容器服务处理时间总和', 'ESB内部处理时间', null, '4028eabb4fd419c7014fd41ab1290001', '60-120', null);
commit;


insert into SLA_TEMPLATE (sla_template_id, description, template_name)
values ('4028eabb4fd419c7014fd41ab1290001', '模版一', '1');


INSERT INTO GENERATOR (ID, DESCRIPTION, IMPLEMENTS, NAME) VALUES ('0', 'standardXml', 'com.dc.esb.servicegov.export.impl.StandardXMLConfigExportGender', 'standardXml');
INSERT INTO GENERATOR (ID, DESCRIPTION, IMPLEMENTS, NAME) VALUES ('1', 'xml', 'com.dc.esb.servicegov.export.impl.UnStandardXMLConfigExportGender', 'xml');
INSERT INTO GENERATOR (ID, DESCRIPTION, IMPLEMENTS, NAME) VALUES ('30f792df-61e1-47e6-b947-9f0b57c7730b', 'fix', 'com.dc.esb.servicegov.export.impl.UnStandardFixConfigExportGenerator', 'fix');
INSERT INTO GENERATOR (ID, DESCRIPTION, IMPLEMENTS, NAME) VALUES ('71d8dbe5-916e-448f-a916-d9b98d72cf8b', 'coreXml', 'com.dc.esb.servicegov.export.impl.CoreXMLConfigExportGender', 'coreXml');


insert into service_head(head_id, head_desc, head_name, head_remark, type) values('SYS_HEAD','默认SYS_HEAD','SYS_HEAD','数据库初始化时必须','SYS_HEAD');

insert into service_head(head_id, head_desc, head_name, head_remark, type) values('APP_HEAD','默认APP_HEAD','APP_HEAD','数据库初始化时必须','APP_HEAD');
commit;

insert into SDA(SDA_ID,METADATAID, PARENT_ID, REMARK, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('sys_head_root', '', '', '', '', 0, 'SYS_HEAD', '根节点', 'root', '', '/root');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REMARK, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('sys_head_request', '', 'sys_head_root', '', '', 0, 'SYS_HEAD', '请求报文体', 'request', '', '/root/request');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REMARK, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('sys_head_response', '', 'sys_head_root', '', '', 1, 'SYS_HEAD', '响应报文体 ', 'response', '', '/root/response');

insert into sda(sda_id, structname, structalias, parent_id, service_head_id, seq) values('app_head_root', 'root', '根节点', null, 'APP_HEAD', 0);
insert into sda(sda_id, structname, structalias, parent_id, service_head_id, seq) values('app_head_request', 'request', '请求报文体', 'app_head_root', 'APP_HEAD', 0);
insert into sda(sda_id, structname, structalias, parent_id, service_head_id, seq) values('app_head_response', 'response', '响应报文体 ','app_head_root', 'APP_HEAD', 1);

commit;

insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1002', 'ReqSeq', 'sys_head_request', '是',1002, 'SYS_HEAD', '请求系统流水号', 'ReqSeq', 'String(22)', '/root/request/ReqSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1003', 'ServiceID', 'sys_head_request', '是',1003, 'SYS_HEAD', '服务代码', 'ServiceID', 'String(11)', '/root/request/ServiceID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1004', 'ChannelID', 'sys_head_request', '是',1004, 'SYS_HEAD', '发起方渠道标识', 'ChannelID', 'String(7)', '/root/request/ChannelID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1005', 'LegOrgID', 'sys_head_request', '是',1005, 'SYS_HEAD', '发起方法人机构编码', 'LegOrgID', 'String(12)', '/root/request/LegOrgID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1006', 'ReqDate', 'sys_head_request', '是',1006, 'SYS_HEAD', '请求系统日期', 'ReqDate', 'String(8)', '/root/request/ReqDate');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1007', 'ReqTime', 'sys_head_request', '是',1007, 'SYS_HEAD', '请求系统时间', 'ReqTime', 'String(6)', '/root/request/ReqTime');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1008', 'MAC ', 'sys_head_request', '否',1008, 'SYS_HEAD', 'MAC校验值', 'MAC ', 'String(16)', '/root/request/MAC ');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1009', 'Version', 'sys_head_request', '是',1009, 'SYS_HEAD', '服务版本号', 'Version', 'String(6)', '/root/request/Version');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1010', 'Priority ', 'sys_head_request', '否',1010, 'SYS_HEAD', '运行优先级', 'Priority ', 'String(2)', '/root/request/Priority ');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1011', 'ReqSysID', 'sys_head_request', '是',1011, 'SYS_HEAD', '请求系统编码', 'ReqSysID', 'String(3)', '/root/request/ReqSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1012', 'DomainRef', 'sys_head_request', '是',1012, 'SYS_HEAD', '请求者域名地址', 'DomainRef', 'String(32)', '/root/request/DomainRef');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1013', 'AcceptLang', 'sys_head_request', '是',1013, 'SYS_HEAD', '接受语言', 'AcceptLang', 'String(7)', '/root/request/AcceptLang');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1014', 'GlobalSeq', 'sys_head_request', '是',1014, 'SYS_HEAD', '全局流水号', 'GlobalSeq', 'String(24)', '/root/request/GlobalSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1015', 'OrgSysID', 'sys_head_request', '是',1015, 'SYS_HEAD', '发起方系统编码', 'OrgSysID', 'String(3)', '/root/request/OrgSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1016', 'RespSeq', 'sys_head_request', '是',1016, 'SYS_HEAD', '应答系统流水号', 'RespSeq', 'String(22)', '/root/request/RespSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1017', 'RespDate', 'sys_head_request', '是',1017, 'SYS_HEAD', '应答系统日期', 'RespDate', 'String(8)', '/root/request/RespDate');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1018', 'RespTime', 'sys_head_request', '是',1018, 'SYS_HEAD', '应答系统时间', 'RespTime', 'String(6)', '/root/request/RespTime');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1019', 'TransStatus', 'sys_head_request', '是',1019, 'SYS_HEAD', '应答交易状态', 'TransStatus', 'String(1)', '/root/request/TransStatus');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1020', 'RetCode', 'sys_head_request', '是',1020, 'SYS_HEAD', '应答信息码', 'RetCode', 'String(10)', '/root/request/RetCode');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1021', 'RetMsg', 'sys_head_request', '是',1021, 'SYS_HEAD', '应答信息码描述', 'RetMsg', 'String(64)', '/root/request/RetMsg');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1022', 'RespSysID', 'sys_head_request', '是',1022, 'SYS_HEAD', '应答系统编码', 'RespSysID', 'String(3)', '/root/request/RespSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1023', 'TrackCode', 'sys_head_request', '否',1023, 'SYS_HEAD', '交易技术跟踪码', 'TrackCode', 'String(10)', '/root/request/TrackCode');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1024', 'ReseFlg', 'sys_head_request', '否',1024, 'SYS_HEAD', '预留标识', 'ReseFlg', 'String(1)', '/root/request/ReseFlg');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1025', 'ReseFld', 'sys_head_request', '否',1025, 'SYS_HEAD', '预留字段', 'ReseFld', 'String(28)', '/root/request/ReseFld');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1026', 'SvcScn', 'sys_head_request', '是',1026, 'SYS_HEAD', '服务场景', 'SvcScn', 'String(50)', '/root/request/SvcScn');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1027', 'TmlCd', 'sys_head_request', '否',1027, 'SYS_HEAD', '终端号', 'TmlCd', 'String(30)', '/root/request/TmlCd');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1028', 'TlrNo', 'app_head_request', '否',1028, 'APP_HEAD', '操作员', 'TlrNo', 'String(30)', '/root/request/TlrNo');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1029', 'BrId', 'app_head_request', '否',1029, 'APP_HEAD', '机构代码', 'BrId', 'String(30)', '/root/request/BrId');



insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1030', 'PkgLength', 'sys_head_response', '否',1030, 'SYS_HEAD', '报文长度', 'PkgLength', 'String(8)', '/root/response/PkgLength');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1031', 'ReqSeq', 'sys_head_response', '是',1031, 'SYS_HEAD', '请求系统流水号', 'ReqSeq', 'String(22)', '/root/response/ReqSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1032', 'ServiceID', 'sys_head_response', '是',1032, 'SYS_HEAD', '服务代码', 'ServiceID', 'String(11)', '/root/response/ServiceID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1033', 'ChannelID', 'sys_head_response', '是',1033, 'SYS_HEAD', '发起方渠道标识', 'ChannelID', 'String(7)', '/root/response/ChannelID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1034', 'LegOrgID', 'sys_head_response', '是',1034, 'SYS_HEAD', '发起方法人机构编码', 'LegOrgID', 'String(12)', '/root/response/LegOrgID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1035', 'ReqDate', 'sys_head_response', '是',1035, 'SYS_HEAD', '请求系统日期', 'ReqDate', 'String(8)', '/root/response/ReqDate');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1036', 'ReqTime', 'sys_head_response', '是',1036, 'SYS_HEAD', '请求系统时间', 'ReqTime', 'String(6)', '/root/response/ReqTime');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1037', 'MAC ', 'sys_head_response', '否',1037, 'SYS_HEAD', 'MAC校验值', 'MAC ', 'String(16)', '/root/response/MAC ');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1038', 'Version', 'sys_head_response', '是',1038, 'SYS_HEAD', '服务版本号', 'Version', 'String(6)', '/root/response/Version');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1039', 'Priority ', 'sys_head_response', '否',1039, 'SYS_HEAD', '运行优先级', 'Priority ', 'String(2)', '/root/response/Priority ');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1040', 'ReqSysID', 'sys_head_response', '是',1040, 'SYS_HEAD', '请求系统编码', 'ReqSysID', 'String(3)', '/root/response/ReqSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1041', 'DomainRef', 'sys_head_response', '是',1041, 'SYS_HEAD', '请求者域名地址', 'DomainRef', 'String(32)', '/root/response/DomainRef');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1042', 'AcceptLang', 'sys_head_response', '是',1042, 'SYS_HEAD', '接受语言', 'AcceptLang', 'String(7)', '/root/response/AcceptLang');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1043', 'GlobalSeq', 'sys_head_response', '是',1043, 'SYS_HEAD', '全局流水号', 'GlobalSeq', 'String(24)', '/root/response/GlobalSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1044', 'OrgSysID', 'sys_head_response', '是',1044, 'SYS_HEAD', '发起方系统编码', 'OrgSysID', 'String(3)', '/root/response/OrgSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1045', 'RespSeq', 'sys_head_response', '是',1045, 'SYS_HEAD', '应答系统流水号', 'RespSeq', 'String(22)', '/root/response/RespSeq');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1046', 'RespDate', 'sys_head_response', '是',1046, 'SYS_HEAD', '应答系统日期', 'RespDate', 'String(8)', '/root/response/RespDate');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1047', 'RespTime', 'sys_head_response', '是',1047, 'SYS_HEAD', '应答系统时间', 'RespTime', 'String(6)', '/root/response/RespTime');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1048', 'TransStatus', 'sys_head_response', '是',1048, 'SYS_HEAD', '应答交易状态', 'TransStatus', 'String(1)', '/root/request/TransStatus');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1049', 'RetCode', 'sys_head_response', '是',1049, 'SYS_HEAD', '应答信息码', 'RetCode', 'String(10)', '/root/response/RetCode');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1050', 'RetMsg', 'sys_head_response', '是',1050, 'SYS_HEAD', '应答信息码描述', 'RetMsg', 'String(64)', '/root/response/RetMsg');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1051', 'RespSysID', 'sys_head_response', '是',1051, 'SYS_HEAD', '应答系统编码', 'RespSysID', 'String(3)', '/root/response/RespSysID');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1052', 'TrackCode', 'sys_head_response', '否',1052, 'SYS_HEAD', '交易技术跟踪码', 'TrackCode', 'String(10)', '/root/response/TrackCode');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1053', 'ReseFlg', 'sys_head_response', '否',1053, 'SYS_HEAD', '预留标识', 'ReseFlg', 'String(1)', '/root/response/ReseFlg');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1054', 'ReseFld', 'sys_head_response', '否',1054, 'SYS_HEAD', '预留字段', 'ReseFld', 'String(28)', '/root/response/ReseFld');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1055', 'SvcScn', 'sys_head_response', '是',1055, 'SYS_HEAD', '服务场景', 'SvcScn', 'String(50)', '/root/response/SvcScn');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1056', 'TmlCd', 'sys_head_response', '否',1056, 'SYS_HEAD', '终端号', 'TmlCd', 'String(30)', '/root/response/TmlCd');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1057', 'TlrNo', 'app_head_response', '否',1057, 'APP_HEAD', '操作员', 'TlrNo', 'String(30)', '/root/response/TlrNo');
insert into SDA(SDA_ID,METADATAID, PARENT_ID, REQUIRED, SEQ, SERVICE_HEAD_ID, STRUCTALIAS, STRUCTNAME, TYPE, XPATH) values('1058', 'BrId', 'app_head_response', '否',1058, 'APP_HEAD', '机构代码', 'BrId', 'String(30)', '/root/response/BrId');
commit;