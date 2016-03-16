set define off;insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('c7c421dacbe043028295b8809495239c','channel_ESB_service_Busi1300100000608.xml','xml','interface','out','Busi1300100000608',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('158a8d118bcd45a3a6a6d2564c731e0d','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''08''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''13001000006''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>  <body></body></service>','1','c7c421dacbe043028295b8809495239c');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('32fa0c6ebce9490e96a833a1a53f389a','service_Busi1300100000608_system_ESB.xml','xml','interface','out','Busi1300100000608',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('d3a3b25ca1ea4f889e07b03053d67254','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE"  expression="''13001000006''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''08''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="FILE_PATH">					<FILE_PATH metadataid="FILE_PATH" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">		<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>				</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">		</data>	</local-header>		<body>	<data name="BODY">	<struct>		<data name="ORDER_NO">			<ORDER_NO metadataid="ORDER_NO" />		</data>		<data name="REASON">			<REASON metadataid="REASON" />		</data>		<data name="ORG_SEQ_ID">			<ORG_SEQ_ID metadataid="ORG_SEQ_ID" />		</data>	</struct>	</data>	</body></service>','1','32fa0c6ebce9490e96a833a1a53f389a');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('4c0b02f0c9f64e39b5b912ff452b4512','channel_ESB_service_Busi0400200000227.xml','xml','interface','out','Busi0400200000227',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('9c91f7edb9b34903b784f1b48cfda3c6','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''27''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''04002000002''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<app-header>		<data name="APP_HEAD">		</data>	</app-header>  <body></body></service>','1','4c0b02f0c9f64e39b5b912ff452b4512');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('3c604192102141779a5ca133dc334615','service_Busi0400200000227_system_ESB.xml','xml','interface','out','Busi0400200000227',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('8542bcb647824ad18947d7fd9aa4b79f','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''04002000002''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''27''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">				<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>				</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="CARD_NO">					<CARD_NO metadataid="CARD_NO" />				</data>				<data name="SEQ_NO">					<SEQ_NO metadataid="SEQ_NO" expression="${/sdoroot/body/org_seq_id}"/>				</data>				<data name="APPLICANT_NAME">					<APPLICANT_NAME metadataid="APPLICANT_NAME" />				</data>				<data name="SEX">					<SEX metadataid="SEX" />				</data>				<data name="GLOBAL_TYPE">					<GLOBAL_TYPE metadataid="GLOBAL_TYPE" />				</data>				<data name="GLOBAL_ID">					<GLOBAL_ID metadataid="GLOBAL_ID" />				</data>				<data name="BIRTH_DATE">					<BIRTH_DATE metadataid="BIRTH_DATE" />				</data>				<data name="REASON">					<REASON metadataid="REASON" />				</data>				<data name="ACCEPT_SEQ_NO">					<ACCEPT_SEQ_NO metadataid="ACCEPT_SEQ_NO" />				</data>			</struct>		</data>	</body></service>','1','3c604192102141779a5ca133dc334615');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('6b3ebf99a6c4421ab22ca77ab13d5f6a','channel_CH_HTTPS_service_Ca1800200000101.xml','xml','interface','in','Ca1800200000101',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('acc1404bb64646b29c6abe71dc9a0ddb','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''18002000001''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''01''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="FILE_PATH">					<FILE_PATH metadataid="FILE_PATH" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">			<struct>			</struct>		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="CARD_NO">					<CARD_NO metadataid="CARD_NO" />				</data>				<data name="CARD_TYPE">					<CARD_TYPE metadataid="CARD_TYPE" />				</data>				<data name="APPLICANT_NAME">					<APPLICANT_NAME metadataid="APPLICANT_NAME" />				</data>				<data name="GLOBAL_TYPE">					<GLOBAL_TYPE metadataid="GLOBAL_TYPE" />				</data>				<data name="GLOBAL_ID">					<GLOBAL_ID metadataid="GLOBAL_ID" />				</data>				<data name="BIRTH_DATE">					<BIRTH_DATE metadataid="BIRTH_DATE" />				</data>				<data name="SEX">					<SEX metadataid="SEX" />				</data>				<data name="APPLY_DATE">					<APPLY_DATE metadataid="APPLY_DATE" />				</data>				<data name="TYPE">					<TYPE metadataid="TYPE" />				</data>				<data name="PHONE_NO">					<PHONE_NO metadataid="PHONE_NO" />				</data>				<data name="SUB_BRANCH_ID">					<SUB_BRANCH_ID metadataid="SUB_BRANCH_ID" />				</data>				<data name="SUB_BRANCH_NAME">					<SUB_BRANCH_NAME metadataid="SUB_BRANCH_NAME" />				</data>				<data name="BRANCH_NO">					<BRANCH_NO metadataid="BRANCH_NO" />				</data>				<data name="AUTH_TELLER_NO">					<AUTH_TELLER_NO metadataid="AUTH_TELLER_NO" />				</data>				<data name="REASON">					<REASON metadataid="REASON" />				</data>				<data name="IMAGE_NO">					<IMAGE_NO metadataid="IMAGE_NO" />				</data>								<data name="CERT_ID">					<CERT_ID metadataid="CERT_ID" />				</data>				<data name="APPLY_NUM">					<APPLY_NUM metadataid="APPLY_NUM" />				</data>				<data name="APPLY_DESC">					<APPLY_DESC metadataid="APPLY_DESC" />				</data>				<data name="DATE">					<DATE metadataid="DATE" />				</data>				<data name="START_NO">					<START_NO metadataid="START_NO" />				</data>				<data name="END_NO">					<END_NO metadataid="END_NO" />				</data>				<data name="ORDER_TYPE">					<ORDER_TYPE metadataid="ORDER_TYPE" />				</data>			</struct>		</data>	</body></service>','1','6b3ebf99a6c4421ab22ca77ab13d5f6a');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('c87e02bfd7e841dfb81a0b3739ec5719','service_Ca1800200000101_system_CH_HTTPS.xml','xml','interface','in','Ca1800200000101',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('b4c6ebb581dd4eaba959f5f99fac225d','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''01''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''18002000001''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">			<struct>			</struct>		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="ORDER_NO">				<ORDER_NO metadataid="ORDER_NO" />				</data>				<data name="CARD_TYPE">				<CARD_TYPE metadataid="CARD_TYPE"/>				</data>				<data name="CERT_ID">				<CERT_ID metadataid="CERT_ID"/>				</data>			</struct>		</data>	</body></service>','1','c87e02bfd7e841dfb81a0b3739ec5719');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('a756085e056c4bea94dac0ae04a0d395','channel_ESB_service_Busi1300100000607.xml','xml','interface','out','Busi1300100000607',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('ba05f125f5ef44059d97a59f4ec0998f','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''07''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''13001000006''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<body>		<data>			<struct>				<data name="ORDER_NO">					<ORDER_NO metadataid="ORDER_NO" />				</data>			</struct>		</data>	</body></service>','1','a756085e056c4bea94dac0ae04a0d395');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('b0924f8d9e4a40a3b3da2a690ae6edc7','service_Busi1300100000607_system_ESB.xml','xml','interface','out','Busi1300100000607',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('346ef492c93d42ceb44c01cc0bdc39e0','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''13001000006''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''07''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="FILE_PATH">					<FILE_PATH metadataid="FILE_PATH" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>				</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="CERT_ID">					<CERT_ID metadataid="CERT_ID" />				</data>				<data name="APPLY_NUM">					<APPLY_NUM metadataid="APPLY_NUM" />				</data>				<data name="APPLY_DESC">					<APPLY_DESC metadataid="APPLY_DESC" />				</data>				<data name="DATE">					<DATE metadataid="DATE" />				</data>				<data name="START_NO">					<START_NO metadataid="START_NO" />				</data>				<data name="END_NO">					<END_NO metadataid="END_NO" />				</data>				<data name="SUB_BRANCH_ID">					<SUB_BRANCH_ID metadataid="SUB_BRANCH_ID" />				</data>				<data name="BRANCH_NO">					<BRANCH_NO metadataid="BRANCH_NO" />				</data>				<data name="ORDER_TYPE">					<ORDER_TYPE metadataid="ORDER_TYPE" />				</data>			</struct>		</data>	</body></service>','1','b0924f8d9e4a40a3b3da2a690ae6edc7');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('012f8c2dbbb940adaac808969b4b396a','channel_ESB_service_Busi0400200000226.xml','xml','interface','out','Busi0400200000226',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('e5bfb965d7c146dbb4d93045765c69f2','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''26''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''04002000002''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<body>		<data name="BODY">			<struct>				<data name="CARD_TYPE">					<CARD_TYPE metadataid="CARD_TYPE" />				</data>				<data name="CERT_ID">					<CERT_ID metadataid="CERT_ID" />				</data>			</struct>		</data>	</body></service>','1','012f8c2dbbb940adaac808969b4b396a');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('d43b1defe71f4c41955a287c036fd536','service_Busi0400200000226_system_ESB.xml','xml','interface','out','Busi0400200000226',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('498d762899994e19be1233de57f13194','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''04002000002''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''26''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="FILE_PATH">					<FILE_PATH metadataid="FILE_PATH" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>				</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="CARD_NO">					<CARD_NO metadataid="CARD_NO" />				</data>				<data name="CARD_TYPE">					<CARD_TYPE metadataid="CARD_TYPE" />				</data>				<data name="APPLICANT_NAME">					<APPLICANT_NAME metadataid="APPLICANT_NAME" />				</data>				<data name="GLOBAL_TYPE">					<GLOBAL_TYPE metadataid="GLOBAL_TYPE" />				</data>				<data name="GLOBAL_ID">					<GLOBAL_ID metadataid="GLOBAL_ID" />				</data>				<data name="BIRTH_DATE">					<BIRTH_DATE metadataid="BIRTH_DATE" />				</data>				<data name="SEX">					<SEX metadataid="SEX" />				</data>				<data name="APPLY_DATE">					<APPLY_DATE metadataid="APPLY_DATE" />				</data>				<data name="TYPE">					<TYPE metadataid="TYPE" />				</data>				<data name="PHONE_NO">					<PHONE_NO metadataid="PHONE_NO" />				</data>				<data name="SUB_BRANCH_ID">					<SUB_BRANCH_ID metadataid="SUB_BRANCH_ID" />				</data>				<data name="SUB_BRANCH_NAME">					<SUB_BRANCH_NAME metadataid="SUB_BRANCH_NAME" />				</data>				<data name="BRANCH_NO">					<BRANCH_NO metadataid="BRANCH_NO" />				</data>				<data name="AUTH_TELLER_NO">					<AUTH_TELLER_NO metadataid="AUTH_TELLER_NO" />				</data>				<data name="REASON">					<REASON metadataid="REASON" />				</data>				<data name="IMAGE_NO">					<IMAGE_NO metadataid="IMAGE_NO" />				</data>			</struct>		</data>	</body></service>','1','d43b1defe71f4c41955a287c036fd536');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('1dbaa59141894b22aa916860a1b7edc6','channel_CH_HTTPS_service_Ca1800200000102.xml','xml','interface','in','Ca1800200000102',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('87387d0787024198ab63420b6642aa18','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''18002000001''" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''02''" />				</data>				<data name="MODULE_ID">					<MODULE_ID metadataid="MODULE_ID" />				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="PROGRAM_ID">					<PROGRAM_ID metadataid="PROGRAM_ID" />				</data>				<data name="USER_LANG">					<USER_LANG metadataid="USER_LANG" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="CONSUMER_SVR_ID">					<CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID" />				</data>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="WS_ID">					<WS_ID metadataid="WS_ID" />				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">			<struct>			</struct>		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="CARD_NO">					<CARD_NO metadataid="CARD_NO" />				</data>				<data name="ACCEPT_SEQ_NO">					<ACCEPT_SEQ_NO metadataid="ACCEPT_SEQ_NO" />				</data>				<data name="APPLICANT_NAME">					<APPLICANT_NAME metadataid="APPLICANT_NAME" />				</data>				<data name="SEX">					<SEX metadataid="SEX" />				</data>				<data name="GLOBAL_TYPE">					<GLOBAL_TYPE metadataid="GLOBAL_TYPE" />				</data>				<data name="GLOBAL_ID">					<GLOBAL_ID metadataid="GLOBAL_ID" />				</data>				<data name="BIRTH_DATE">					<BIRTH_DATE metadataid="BIRTH_DATE" />				</data>				<data name="REASON">					<REASON metadataid="REASON" />				</data>				<data name="ORDER_NO">					<ORDER_NO metadataid="ORDER_NO" />				</data>				<data name="ORG_SEQ_ID">					<ORG_SEQ_ID metadataid="ORG_SEQ_ID" />				</data>			</struct>		</data>	</body></service>','1','1dbaa59141894b22aa916860a1b7edc6');

insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('1a4e297013a04312b9ff80cd9efaf455','service_Ca1800200000102_system_CH_HTTPS.xml','xml','interface','in','Ca1800200000102',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('0e0c9243aa034154bbc4b07c2f330c2e','<?xml version="1.0" encoding="UTF-8"?><service package_type="CD">	<sys-header>		<data name="SYS_HEAD">			<struct>				<data name="ESB_SEQ_NO">					<ESB_SEQ_NO metadataid="ESB_SEQ_NO" />				</data>				<data name="RET">					<RET metadataid="ret" type="array" is_struct="false">						<data>						<struct>							<data name="RET_CODE">								<RET_CODE metadataid="RET_CODE" />							</data>							<data name="RET_MSG">								<RET_MSG metadataid="RET_MSG" />							</data>						</struct>						</data>					</RET>				</data>				<data name="TRAN_TIMESTAMP">					<TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP" />				</data>				<data name="API_CODE">					<API_CODE metadataid="API_CODE" />				</data>				<data name="CONSUMER_SEQ_NO">					<CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO" />				</data>				<data name="RET_STATUS">					<RET_STATUS metadataid="RET_STATUS" />				</data>				<data name="SERVICE_SCENE">					<SERVICE_SCENE metadataid="SERVICE_SCENE" expression="''02''"/>				</data>				<data name="CONSUMER_ID">					<CONSUMER_ID metadataid="CONSUMER_ID" />				</data>				<data name="SERVICE_CODE">					<SERVICE_CODE metadataid="SERVICE_CODE" expression="''18002000001''" />				</data>				<data name="TRAN_DATE">					<TRAN_DATE metadataid="TRAN_DATE" />				</data>				<data name="ORG_SYS_ID">					<ORG_SYS_ID metadataid="ORG_SYS_ID" />				</data>			</struct>		</data>	</sys-header>	<app-header>		<data name="APP_HEAD">			<struct>				<data name="BIZ_SEQ_NO">					<BIZ_SEQ_NO metadataid="BIZ_SEQ_NO" />				</data>				<data name="PGUP_OR_PGDN">					<PGUP_OR_PGDN metadataid="PGUP_OR_PGDN" />				</data>				<data name="USER_ID">					<USER_ID metadataid="USER_ID" />				</data>				<data name="PER_PAGE_NUM">					<PER_PAGE_NUM metadataid="PER_PAGE_NUM" />				</data>				<data name="BRANCH_ID">					<BRANCH_ID metadataid="BRANCH_ID" />				</data>			</struct>		</data>	</app-header>	<local-header>		<data name="LOCAL_HEAD">			<struct>			</struct>		</data>	</local-header>	<body>		<data name="BODY">			<struct>				<data name="FLAG">					<FLAG metadataid="FLAG" />				</data>				<data name="RET_MSG">					<RET_MSG metadataid="RET_MSG" />				</data>			</struct>		</data>	</body></service>','1','1a4e297013a04312b9ff80cd9efaf455');

insert into esb_runtime_service (serviceid,servicetype) values('esb_runtime_metadata','metadata');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('3594cda3a7454fc18e5f4b96ded3e312','metadata_ESB.xml','xml','metadata','all','esb_runtime_metadata',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('b55cc3d8523a4c8b9aec58b923401106','<?xml version="1.0" encoding="UTF-8"?>

<metadata>
	<!-- SYS_HEAD -->

	<SERVICE_CODE name="SERVICE_CODE" type="string" length="30" chinese_name="服务代码" metadataid="SERVICE_CODE" scale="0"/>
	<CONSUMER_ID name="CONSUMER_ID" type="string" length="6" chinese_name="请求系统编号" metadataid="CONSUMER_ID" scale="0"/>
	<SERVICE_SCENE name="SERVICE_SCENE" type="string" length="2" chinese_name="服务应用场景" metadataid="SERVICE_SCENE" scale="0"/>
	<ESB_SEQ_NO name="ESB_SEQ_NO" type="string" length="52" chinese_name="ESB产生的流水号" metadataid="ESB_SEQ_NO" scale="0"/>
	<MODULE_ID name="MODULE_ID" type="string" length="4" chinese_name="请求系统模块标识" metadataid="MODULE_ID" scale="0"/>
	<PROGRAM_ID name="PROGRAM_ID" type="string" length="8" chinese_name="请求系统应用程序模块" metadataid="PROGRAM_ID" scale="0"/>
	<CONSUMER_SEQ_NO name="CONSUMER_SEQ_NO" type="string" length="52" chinese_name="服务请求发送方流水号" metadataid="CONSUMER_SEQ_NO" scale="0"/>
	<ORG_SYS_ID name="ORG_SYS_ID" type="string" length="6" chinese_name="发起方系统编号" metadataid="ORG_SYS_ID" scale="0"/>
	<CONSUMER_SVR_ID name="CONSUMER_SVR_ID" type="string" length="30" chinese_name="发起方服务器标识" metadataid="CONSUMER_SVR_ID" scale="0"/>
	<TM_NO name="TM_NO" type="string" length="4" chinese_name="终端号" metadataid="TM_NO" scale="0"/>
	<WS_ID name="WS_ID" type="string" length="30" chinese_name="终端标识" metadataid="WS_ID" scale="0"/>
	<AFT_TRAN_CODE name="AFT_TRAN_CODE" type="string" length="4" chinese_name="后继交易码" metadataid="AFT_TRAN_CODE" scale="0"/>
	<AFT_APP_CODE name="AFT_APP_CODE" type="string" length="2" chinese_name="后继应用码" metadataid="AFT_APP_CODE" scale="0"/>
	<SUBS_SERV_CODE name="SUBS_SERV_CODE" type="string" length="11" chinese_name="后继服务代码" metadataid="SUBS_SERV_CODE" scale="0"/>
	<TR_FLAG name="TR_FLAG" type="string" length="3" chinese_name="交易符" metadataid="TR_FLAG" scale="0"/>
	<TRAN_DATE name="TRAN_DATE" type="string" length="8" chinese_name="交易日期" metadataid="TRAN_DATE" scale="0"/>
	<TRAN_TIMESTAMP name="TRAN_TIMESTAMP" type="string" length="9" chinese_name="交易时间" metadataid="TRAN_TIMESTAMP" scale="0"/>
	<USER_LANG name="USER_LANG" type="string" length="20" chinese_name="用户语言" metadataid="USER_LANG" scale="0"/>
	<FILE_PATH name="FILE_PATH" type="string" length="512" chinese_name="文件路径" metadataid="FILE_PATH" scale="0"/>
	<RET_STATUS name="RET_STATUS" type="string" length="1" chinese_name="交易状态" metadataid="RET_STATUS" scale="0"/>
	<RET name="RET" type="array" length="" chinese_name="交易返回代码数组" metadataid="RET" scale="0"/>
	<API_CODE name="API_CODE" type="string" length="10" chinese_name="API码" metadataid="API_CODE" scale="0"/>


	<!-- APP_HEAD -->

	<AUTH_LVL name="AUTH_LVL" type="string" length="2" chinese_name="交易需要的授权级别" metadataid="AUTH_LVL" scale="0"/>
	<AUTH_REASON name="AUTH_REASON" type="string" length="60" chinese_name="授权原因" metadataid="AUTH_REASON" scale="0"/>
	<MULTI_OUTPUT_FLAG name="MULTI_OUTPUT_FLAG" type="string" length="1" chinese_name="多页输出标志" metadataid="MULTI_OUTPUT_FLAG" scale="0"/>
	<PER_PAGE_NUM name="PER_PAGE_NUM" type="string" length="5" chinese_name="要求每页返回记录总数" metadataid="PER_PAGE_NUM" scale="0"/>
	<TRAN_REQ_TYPE name="TRAN_REQ_TYPE" type="string" length="1" chinese_name="交易请求类型" metadataid="TRAN_REQ_TYPE" scale="0"/>
	<USER_ID name="USER_ID" type="string" length="50" chinese_name="服务请求者身份" metadataid="USER_ID" scale="0"/>
	<USER_PASSWORD name="USER_PASSWORD" type="string" length="30" chinese_name="服务请求者密码" metadataid="USER_PASSWORD" scale="0"/>
	<USER_LEVEL name="USER_LEVEL" type="string" length="2" chinese_name="服务请求者级别" metadataid="USER_LEVEL" scale="0"/>
	<USER_TYPE name="USER_TYPE" type="string" length="2" chinese_name="服务请求','1','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('857cc984d3dd4544acd9f05b68436633','者类别" metadataid="USER_TYPE" scale="0"/>
	<APPR_FLAG name="APPR_FLAG" type="string" length="1" chinese_name="复核标志" metadataid="APPR_FLAG" scale="0"/>
	<APPR_USER_ID_ARRAY name="APPR_USER_ID_ARRAY" type="array" length="null" chinese_name="录入柜员数组" metadataid="APPR_USER_ID_ARRAY" scale="0"/>
	<APPR_USER_ID name="APPR_USER_ID" type="string" length="30" chinese_name="交易录入柜员标识" metadataid="APPR_USER_ID" scale="0"/>
	<APPR_USER_LEVEL name="APPR_USER_LEVEL" type="string" length="2" chinese_name="交易录入柜员级别" metadataid="APPR_USER_LEVEL" scale="0"/>
	<APPR_USER_TYPE name="APPR_USER_TYPE" type="string" length="2" chinese_name="交易录入柜员类别" metadataid="APPR_USER_TYPE" scale="0"/>
	<AUTH_FLAG name="AUTH_FLAG" type="string" length="1" chinese_name="授权标志" metadataid="AUTH_FLAG" scale="0"/>
	<AUTH_USER_ID_ARRAY name="AUTH_USER_ID_ARRAY" type="array" length="null" chinese_name="授权柜员数组" metadataid="AUTH_USER_ID_ARRAY" scale="0"/>
	<AUTH_USER_ID name="AUTH_USER_ID" type="string" length="30" chinese_name="授权柜员标识" metadataid="AUTH_USER_ID" scale="0"/>
	<AUTH_PASSWORD name="AUTH_PASSWORD" type="string" length="30" chinese_name="授权柜员密码" metadataid="AUTH_PASSWORD" scale="0"/>
	<AUTH_LEVEL name="AUTH_LEVEL" type="string" length="2" chinese_name="授权柜员级别" metadataid="AUTH_LEVEL" scale="0"/>
	<AUTH_TYPE name="AUTH_TYPE" type="string" length="2" chinese_name="授权柜员类别" metadataid="AUTH_TYPE" scale="0"/>
	<AUTH_DEV name="AUTH_DEV" type="string" length="1" chinese_name="主管密码输入设备" metadataid="AUTH_DEV" scale="0"/>
	<BIZ_SEQ_NO name="BIZ_SEQ_NO" type="string" length="52" chinese_name="发起方业务流水号" metadataid="BIZ_SEQ_NO" scale="0"/>
	<REVERSAL_DATE name="REVERSAL_DATE" type="string" length="8" chinese_name="需冲正的原流水交易日前" metadataid="REVERSAL_DATE" scale="0"/>
	<REVERSAL_SEQ_NO name="REVERSAL_SEQ_NO" type="string" length="52" chinese_name="需冲正的原交易流水号" metadataid="REVERSAL_SEQ_NO" scale="0"/>
	<REVERSAL_BIZ_SEQ_NO name="REVERSAL_BIZ_SEQ_NO" type="string" length="52" chinese_name="需冲正的原业务流水号" metadataid="REVERSAL_BIZ_SEQ_NO" scale="0"/>
	<SERV_SEQ_NO name="SERV_SEQ_NO" type="string" length="52" chinese_name="服务处理者返回流水号" metadataid="SERV_SEQ_NO" scale="0"/>
	<PGUP_OR_PGDN name="PGUP_OR_PGDN" type="string" length="1" chinese_name="上翻/下翻标志" metadataid="PGUP_OR_PGDN" scale="0"/>
	<RESULT_NUM name="RESULT_NUM" type="string" length="5" chinese_name="本次交易返回查询结果记录数" metadataid="RESULT_NUM" scale="0"/>
	<TOTAL_NUM name="TOTAL_NUM" type="string" length="10" chinese_name="符合业务查询条件的记录总数" metadataid="TOTAL_NUM" scale="0"/>
	<QUERY_KEY_ARRAY name="QUERY_KEY_ARRAY" type="array" length="null" chinese_name="KEY数组" metadataid="QUERY_KEY_ARRAY" scale="0"/>
	<QUERY_KEY name="QUERY_KEY" type="string" length="256" chinese_name="查询结果定位串" metadataid="QUERY_KEY" scale="0"/>
	<END_FLAG name="END_FLAG" type="string" length="2" chinese_name="是否结束" metadataid="END_FLAG" scale="0"/>
	<PAGE_NUM name="PAGE_NUM" type="string" length="5" chinese_name="页码" metadataid="PAGE_NUM" scale="0"/>
	<ACCOUNT_DATE name="ACCOUNT_DATE" type="string" length="8" chinese_name="记账日期" metadataid="ACCOUNT_DATE" scale="0"/>
	<DEPT_ID name="DEPT_ID" type="string" length="4" chinese_name="部门编号" metadataid="DEPT_ID" scale="0"/>


	<!-- LOCAL_HEAD -->

	<CICS_CODE name="CICS_CODE" type="string" length="4" chinese_name="CICS交易码" metadataid="CICS_CODE" scale="0"/>
	<WS_TYPE name="WS_TYPE" type="string" length="10" chinese_name="终端类型" metadataid="WS_TYPE" scale="0"/>
	<MAIN_PRODUCT_CODE name="MAIN_PRODUCT_CODE" type="string" length="5" chinese_name="主产品码" metadataid="MAIN_PRODUCT_CODE" scale="0"/>
	<SUB_PRODUCT_CODE name="SUB_PRODUCT_CODE" type="string"','2','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('cad48ff331a54722ac1688feb6cefbaf',' length="5" chinese_name="子产品码" metadataid="SUB_PRODUCT_CODE" scale="0"/>
	<ORIGINAL_PRODUCT_CODE name="ORIGINAL_PRODUCT_CODE" type="string" length="5" chinese_name="原产品码" metadataid="ORIGINAL_PRODUCT_CODE" scale="0"/>
	<AUTHORIZE_FLAG name="AUTHORIZE_FLAG" type="string" length="2" chinese_name="授权标志" metadataid="AUTHORIZE_FLAG" scale="0"/>
	<EXTENDED_REPLY_ARRAY name="EXTENDED_REPLY_ARRAY" type="array" length="null" chinese_name="扩展返回信息" metadataid="EXTENDED_REPLY_ARRAY" scale="0"/>
	<RET_CODE name="RET_CODE" type="string" length="20" chinese_name="返回码" metadataid="RET_CODE" scale="0"/>
	<VALIDATION_ERROR_ARRAY name="VALIDATION_ERROR_ARRAY" type="array" length="null" chinese_name="验证错误信息" metadataid="VALIDATION_ERROR_ARRAY" scale="0"/>
	<ERROR_CODE name="ERROR_CODE" type="string" length="20" chinese_name="错误码" metadataid="ERROR_CODE" scale="0"/>
	<ERROR_MSG name="ERROR_MSG" type="string" length="512" chinese_name="错误信息" metadataid="ERROR_MSG" scale="0"/>
	<OBJECT_NAME name="OBJECT_NAME" type="string" length="150" chinese_name="对象名称" metadataid="OBJECT_NAME" scale="0"/>
	<ATTR_NAME name="ATTR_NAME" type="string" length="150" chinese_name="属性名称" metadataid="ATTR_NAME" scale="0"/>
	<ATTR_VALUE name="ATTR_VALUE" type="string" length="150" chinese_name="属性值" metadataid="ATTR_VALUE" scale="0"/>
	<METHOD_NAME name="METHOD_NAME" type="string" length="150" chinese_name="方法名称" metadataid="METHOD_NAME" scale="0"/>
	<ERROR_NAME name="ERROR_NAME" type="string" length="150" chinese_name="错误名称" metadataid="ERROR_NAME" scale="0"/>
	<TRACE_SEQ_NO name="TRACE_SEQ_NO" type="string" length="52" chinese_name="跟踪流水号" metadataid="TRACE_SEQ_NO" scale="0"/>
	<ACCOUNTING_NO name="ACCOUNTING_NO" type="string" length="30" chinese_name="传票号" metadataid="ACCOUNTING_NO" scale="0"/>
	<INFO_INDEX name="INFO_INDEX" type="string" length="4" chinese_name="信息下标" metadataid="INFO_INDEX" scale="0"/>
	<DATA_COMP_FLAG name="DATA_COMP_FLAG" type="string" length="1" chinese_name="数据压缩标志" metadataid="DATA_COMP_FLAG" scale="0"/>
	<DATA_ENCR_FLAG name="DATA_ENCR_FLAG" type="string" length="1" chinese_name="数据加密标志" metadataid="DATA_ENCR_FLAG" scale="0"/>
	<VERSION_NO name="VERSION_NO" type="string" length="6" chinese_name="版本号" metadataid="VERSION_NO" scale="0"/>


	<!-- 银行卡信息查询 04003000003 26 服务 -->

	<SYSTEM_NO name="SYSTEM_NO" type="string" length="10" chinese_name="系统号" metadataid="SYSTEM_NO" scale="0"/>
	<MSG name="MSG" type="string" length="2048" chinese_name="信息" metadataid="MSG" scale="0"/>
	<CARD_EXPR_DATE name="CARD_EXPR_DATE" type="string" length="8" chinese_name="卡有效日期" metadataid="CARD_EXPR_DATE" scale="0"/>
	<CARD_PROP_CODE name="CARD_PROP_CODE" type="string" length="2" chinese_name="卡片属性代码" metadataid="CARD_PROP_CODE" scale="0"/>
	<STATUS name="STATUS" type="string" length="10" chinese_name="状态" metadataid="STATUS" scale="0"/>
	<CARD_STATUS_CODE name="CARD_STATUS_CODE" type="string" length="40" chinese_name="卡片状态代码" metadataid="CARD_STATUS_CODE" scale="0"/>

	<!-- 借记卡信息查询(04003000003) 03-->

	<SUB_SYSTEM_NO name="SUB_SYSTEM_NO" type="string" length="10" chinese_name="子系统ID" metadataid="SUB_SYSTEM_NO" scale="0"/>
	<TRAN_PASSWORD name="TRAN_PASSWORD" type="string" length="50" chinese_name="交易密码" metadataid="TRAN_PASSWORD" scale="0"/>
	<PWD_CHECK_FLAG name="PWD_CHECK_FLAG" type="string" length="1" chinese_name="密码校验标志" metadataid="PWD_CHECK_FLAG" scale="0"/>
	<CARD_STATUS name="CARD_STATUS" type="string" length="20" chinese_name="卡状态" metadataid="CARD_STATUS" scale="0"/>
	<ECIF_CLIENT_NO name="ECIF_CLIENT_NO" type="string" length="20" chinese_name="ECIF客户号" metadataid="ECIF_CLIENT_NO" scale="0"/>

	<!-- 帐户密码服务(01002000001 26) -->

	<OP_RESULT_FLAG name="OP_RESULT_FLAG" type="','3','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('4fe6824c24dc4f9bb80ad1f0337a2d56','string" length="1" chinese_name="操作结果返回标志" metadataid="OP_RESULT_FLAG" scale="0"/>
	<CARD_SERIAL_NO name="CARD_SERIAL_NO" type="string" length="32" chinese_name="卡序号" metadataid="CARD_SERIAL_NO" scale="0"/>
	<LENGTH name="LENGTH" type="string" length="10" chinese_name="长度" metadataid="LENGTH" scale="0"/>
	<IC_SYS_DATA_MSG name="IC_SYS_DATA_MSG" type="string" length="512" chinese_name="IC卡系统数据信息" metadataid="IC_SYS_DATA_MSG" scale="0"/>


	<!-- 信用卡汇总信息查询(05003000004 04) -->

	<CREDIT_CARD_ACCT_NO name="CREDIT_CARD_ACCT_NO" type="string" length="50" chinese_name="信用卡账号" metadataid="CREDIT_CARD_ACCT_NO" scale="0"/>
	<MSG_TYPE name="MSG_TYPE" type="string" length="10" chinese_name="报文类型" metadataid="MSG_TYPE" scale="0"/>
	<BIT_MAP1 name="BIT_MAP1" type="string" length="8" chinese_name="位图1" metadataid="BIT_MAP1" scale="0"/>
	<BIT_MAP2 name="BIT_MAP2" type="string" length="8" chinese_name="位图2" metadataid="BIT_MAP2" scale="0"/>
	<CREDIT_CARD_NO name="CREDIT_CARD_NO" type="string" length="19" chinese_name="信用卡号" metadataid="CREDIT_CARD_NO" scale="0"/>
	<TRAN_CODE name="TRAN_CODE" type="string" length="30" chinese_name="交易码" metadataid="TRAN_CODE" scale="0"/>
	<TRAN_TIME name="TRAN_TIME" type="string" length="14" chinese_name="交易时间" metadataid="TRAN_TIME" scale="0"/>
	<SYS_TRACE_SEQ_NO name="SYS_TRACE_SEQ_NO" type="string" length="52" chinese_name="系统跟踪号" metadataid="SYS_TRACE_SEQ_NO" scale="0"/>
	<LOCAL_TIME name="LOCAL_TIME" type="string" length="6" chinese_name="所在地时间" metadataid="LOCAL_TIME" scale="0"/>
	<LOCAL_DATE name="LOCAL_DATE" type="string" length="8" chinese_name="所在地日期" metadataid="LOCAL_DATE" scale="0"/>
	<EXPIRE_DATE name="EXPIRE_DATE" type="string" length="8" chinese_name="有效日期" metadataid="EXPIRE_DATE" scale="0"/>
	<SETTLMT_DATE name="SETTLMT_DATE" type="string" length="8" chinese_name="清算日期" metadataid="SETTLMT_DATE" scale="0"/>
	<TRADER_TYPE name="TRADER_TYPE" type="string" length="4" chinese_name="商户类型" metadataid="TRADER_TYPE" scale="0"/>
	<SERVICE_MODE_CODE name="SERVICE_MODE_CODE" type="string" length="3" chinese_name="服务点输入方式码" metadataid="SERVICE_MODE_CODE" scale="0"/>
	<SERVICE_REQ_CODE name="SERVICE_REQ_CODE" type="string" length="3" chinese_name="服务点条件码" metadataid="SERVICE_REQ_CODE" scale="0"/>
	<RECEV_BRANCH_ID name="RECEV_BRANCH_ID" type="string" length="20" chinese_name="受理机构标识码" metadataid="RECEV_BRANCH_ID" scale="0"/>
	<SEND_BRANCH_ID name="SEND_BRANCH_ID" type="string" length="20" chinese_name="发送机构标识码" metadataid="SEND_BRANCH_ID" scale="0"/>
	<REF_SEQ_NO name="REF_SEQ_NO" type="string" length="52" chinese_name="检索参考号" metadataid="REF_SEQ_NO" scale="0"/>
	<RET_STATUS_CODE name="RET_STATUS_CODE" type="string" length="20" chinese_name="应答码" metadataid="RET_STATUS_CODE" scale="0"/>
	<TERM_NO name="TERM_NO" type="string" length="10" chinese_name="终端标识码" metadataid="TERM_NO" scale="0"/>
	<BRANCH_ID name="BRANCH_ID" type="string" length="20" chinese_name="受卡方标识码" metadataid="BRANCH_ID" scale="0"/>
	<CARD_ACPT_ADDRESS name="CARD_ACPT_ADDRESS" type="string" length="200" chinese_name="受卡方名称地址" metadataid="CARD_ACPT_ADDRESS" scale="0"/>
	<PIN name="PIN" type="string" length="50" chinese_name="PIN码" metadataid="PIN" scale="0"/>
	<TRANS_MSG name="TRANS_MSG" type="string" length="300" chinese_name="交易描述" metadataid="TRANS_MSG" scale="0"/>

	<!-- 同行活期转账(01001000001) -->

	<VALIDATION_FLAG name="VALIDATION_FLAG" type="string" length="300" chinese_name="验证标志" metadataid="VALIDATION_FLAG" scale="0"/>
	<BACK_VALUE_DATE name="BACK_VALUE_DATE" type="string" length="8" chinese_name="倒起息日" metadataid="BACK_VALUE_DATE" scale="0"/>
	<OUT_ACCT_NO name="OUT_ACCT_NO" type="string" length="50" chinese_name="转出账号" metad','4','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('aabdffa57bd54bc79df06b47a49e24ba','ataid="OUT_ACCT_NO" scale="0"/>
	<OUT_ACCT_BUSS_TYPE name="OUT_ACCT_BUSS_TYPE" type="string" length="50" chinese_name="转出账户业务类型" metadataid="OUT_ACCT_BUSS_TYPE" scale="0"/>
	<FROM_ACCT_NAME name="FROM_ACCT_NAME" type="string" length="150" chinese_name="资金划出方户名" metadataid="FROM_ACCT_NAME" scale="0"/>
	<OUT_DEP_BOOK_ID name="OUT_DEP_BOOK_ID" type="string" length="30" chinese_name="转出存折号码" metadataid="OUT_DEP_BOOK_ID" scale="0"/>
	<OUT_ACCT_USAGE name="OUT_ACCT_USAGE" type="string" length="200" chinese_name="转出账户用途" metadataid="OUT_ACCT_USAGE" scale="0"/>
	<OUT_ACC_CASH_USAGE_CODE name="OUT_ACC_CASH_USAGE_CODE" type="string" length="15" chinese_name="转出账户现金用途码" metadataid="OUT_ACC_CASH_USAGE_CODE" scale="0"/>
	<TRAN_FLAG name="TRAN_FLAG" type="string" length="6" chinese_name="交易标示" metadataid="TRAN_FLAG" scale="0"/>
	<IN_ACCT_NO name="IN_ACCT_NO" type="string" length="50" chinese_name="转入账号" metadataid="IN_ACCT_NO" scale="0"/>
	<IN_ACCT_BUSS_TYPE name="IN_ACCT_BUSS_TYPE" type="string" length="50" chinese_name="转入账户业务类型" metadataid="IN_ACCT_BUSS_TYPE" scale="0"/>
	<IN_ACCT_NAME name="IN_ACCT_NAME" type="string" length="150" chinese_name="入账账号户名" metadataid="IN_ACCT_NAME" scale="0"/>
	<IN_DEP_BOOK_ID name="IN_DEP_BOOK_ID" type="string" length="30" chinese_name="转入存折号码" metadataid="IN_DEP_BOOK_ID" scale="0"/>
	<IN_ACCT_USAGE name="IN_ACCT_USAGE" type="string" length="200" chinese_name="转入账户用途" metadataid="IN_ACCT_USAGE" scale="0"/>
	<IN_ACC_CASH_USAGE_CODE name="IN_ACC_CASH_USAGE_CODE" type="string" length="15" chinese_name="转入账户现金用途码" metadataid="IN_ACC_CASH_USAGE_CODE" scale="0"/>
	<CERT_TYPE name="CERT_TYPE" type="string" length="3" chinese_name="凭证类型" metadataid="CERT_TYPE" scale="0"/>
	<CERT_NO name="CERT_NO" type="string" length="30" chinese_name="凭证号码" metadataid="CERT_NO" scale="0"/>
	<CANCEL_FLAG name="CANCEL_FLAG" type="string" length="1" chinese_name="撤销标志" metadataid="CANCEL_FLAG" scale="0"/>
	<AMT name="AMT" type="double" length="20" chinese_name="交易金额" metadataid="AMT" scale="0"/>
	<CCY name="CCY" type="string" length="3" chinese_name="币种" metadataid="CCY" scale="0"/>
	<REMARK name="REMARK" type="string" length="300" chinese_name="备注" metadataid="REMARK" scale="0"/>
	<CHECK_FLAG name="CHECK_FLAG" type="string" length="1" chinese_name="检查标志" metadataid="CHECK_FLAG" scale="0"/>
	<SAFE_AUTH_NO name="SAFE_AUTH_NO" type="string" length="30" chinese_name="准证号" metadataid="SAFE_AUTH_NO" scale="0"/>
	<FLAG name="FLAG" type="string" length="2" chinese_name="标志" metadataid="FLAG" scale="0"/>
	<COMMISSION_ACCT_NO name="COMMISSION_ACCT_NO" type="string" length="50" chinese_name="手续费账号" metadataid="COMMISSION_ACCT_NO" scale="0"/>
	<COMMISSION_ACCT_NAME name="COMMISSION_ACCT_NAME" type="string" length="150" chinese_name="手续费账户名" metadataid="COMMISSION_ACCT_NAME" scale="0"/>
	<BUSS_TYPE name="BUSS_TYPE" type="string" length="30" chinese_name="业务类型" metadataid="BUSS_TYPE" scale="0"/>
	<PWD_SET_FLAG name="PWD_SET_FLAG" type="string" length="1" chinese_name="密码设置标志" metadataid="PWD_SET_FLAG" scale="0"/>
	<PASSWORD name="PASSWORD" type="string" length="50" chinese_name="卡\折密码" metadataid="PASSWORD" scale="0"/>
	<TRACK_CHECK_FLAG name="TRACK_CHECK_FLAG" type="string" length="2" chinese_name="磁道校验标志" metadataid="TRACK_CHECK_FLAG" scale="0"/>
	<TRACK2 name="TRACK2" type="string" length="37" chinese_name="二磁道信息" metadataid="TRACK2" scale="0"/>
	<TRACK3 name="TRACK3" type="string" length="104" chinese_name="三磁道信息" metadataid="TRACK3" scale="0"/>
	<DEP_BOOK_ID name="DEP_BOOK_ID" type="string" length="30" chinese_name="存折号" metadataid="DEP_BOOK_ID" scale="0"/>
	<USAGE name="USAGE" type="string" length="200" chinese_name="用途" metadataid="USAG','5','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('c057129f24b942da890ef2fc04981c11','E" scale="0"/>
	<CASH_USAGE_CODE name="CASH_USAGE_CODE" type="string" length="15" chinese_name="现金用途码" metadataid="CASH_USAGE_CODE" scale="0"/>
	<OUT_COUNTER_CLIENT_NAME name="OUT_COUNTER_CLIENT_NAME" type="string" length="150" chinese_name="转出对方行客户名称" metadataid="OUT_COUNTER_CLIENT_NAME" scale="0"/>
	<OUT_COUNTER_BANK_NAME name="OUT_COUNTER_BANK_NAME" type="string" length="150" chinese_name="转出对方行银行名称" metadataid="OUT_COUNTER_BANK_NAME" scale="0"/>
	<OUT_COUNTER_BRANCH_NAME name="OUT_COUNTER_BRANCH_NAME" type="string" length="150" chinese_name="转出对方行行名" metadataid="OUT_COUNTER_BRANCH_NAME" scale="0"/>
	<OUT_COUNTER_PROVINCE_NAME name="OUT_COUNTER_PROVINCE_NAME" type="string" length="60" chinese_name="转出对方行省名称" metadataid="OUT_COUNTER_PROVINCE_NAME" scale="0"/>
	<OUT_COUNTER_ACCT_NO name="OUT_COUNTER_ACCT_NO" type="string" length="50" chinese_name="转出对方行账号" metadataid="OUT_COUNTER_ACCT_NO" scale="0"/>
	<REMARK1 name="REMARK1" type="string" length="300" chinese_name="备注1" metadataid="REMARK1" scale="0"/>
	<OUT_COUNTER_CERT_TYPE name="OUT_COUNTER_CERT_TYPE" type="string" length="3" chinese_name="转出对方行凭证类型" metadataid="OUT_COUNTER_CERT_TYPE" scale="0"/>
	<OUT_COUNTER_CERT_NO name="OUT_COUNTER_CERT_NO" type="string" length="30" chinese_name="转出对方行凭证号" metadataid="OUT_COUNTER_CERT_NO" scale="0"/>
	<OUT_COUNTER_BANK_ID name="OUT_COUNTER_BANK_ID" type="string" length="20" chinese_name="转出对方行银行代码" metadataid="OUT_COUNTER_BANK_ID" scale="0"/>
	<OUT_COUNTER_COUNTRY_CODE name="OUT_COUNTER_COUNTRY_CODE" type="string" length="20" chinese_name="转出对方行国别代码" metadataid="OUT_COUNTER_COUNTRY_CODE" scale="0"/>
	<OUT_COUNTER_DISTRICT_CODE name="OUT_COUNTER_DISTRICT_CODE" type="string" length="20" chinese_name="转出对方行国内地区码" metadataid="OUT_COUNTER_DISTRICT_CODE" scale="0"/>
	<IN_COUNTER_CLIENT_NAME name="IN_COUNTER_CLIENT_NAME" type="string" length="150" chinese_name="转入对方行客户名称" metadataid="IN_COUNTER_CLIENT_NAME" scale="0"/>
	<IN_COUNTER_BANK_NAME name="IN_COUNTER_BANK_NAME" type="string" length="150" chinese_name="转入对方行银行名称" metadataid="IN_COUNTER_BANK_NAME" scale="0"/>
	<IN_COUNTER_BRANCH_NAME name="IN_COUNTER_BRANCH_NAME" type="string" length="150" chinese_name="转入对方行分行名称" metadataid="IN_COUNTER_BRANCH_NAME" scale="0"/>
	<IN_COUNTER_PROVINCE_NAME name="IN_COUNTER_PROVINCE_NAME" type="string" length="150" chinese_name="转入对方行省名称" metadataid="IN_COUNTER_PROVINCE_NAME" scale="0"/>
	<IN_COUNTER_ACCT_NO name="IN_COUNTER_ACCT_NO" type="string" length="50" chinese_name="转入对方行账号" metadataid="IN_COUNTER_ACCT_NO" scale="0"/>
	<REMARK2 name="REMARK2" type="string" length="300" chinese_name="备注2" metadataid="REMARK2" scale="0"/>
	<IN_COUNTER_CERT_TYPE name="IN_COUNTER_CERT_TYPE" type="string" length="3" chinese_name="转入对方行凭证类型" metadataid="IN_COUNTER_CERT_TYPE" scale="0"/>
	<IN_COUNTER_CERT_NO name="IN_COUNTER_CERT_NO" type="string" length="30" chinese_name="转入对方行凭证号" metadataid="IN_COUNTER_CERT_NO" scale="0"/>
	<IN_COUNTER_BANK_ID name="IN_COUNTER_BANK_ID" type="string" length="20" chinese_name="转入对方行银行代码" metadataid="IN_COUNTER_BANK_ID" scale="0"/>
	<IN_COUNTER_COUNTRY_CODE name="IN_COUNTER_COUNTRY_CODE" type="string" length="20" chinese_name="转入对方行国别代码" metadataid="IN_COUNTER_COUNTRY_CODE" scale="0"/>
	<IN_COUNTER_DISTRICT_CODE name="IN_COUNTER_DISTRICT_CODE" type="string" length="20" chinese_name="转入对方行国内地区码" metadataid="IN_COUNTER_DISTRICT_CODE" scale="0"/>
	<OUT_ACCT_FREEZE_TYPE name="OUT_ACCT_FREEZE_TYPE" type="string" length="10" chinese_name="待解除的资金冻结类型" metadataid="OUT_ACCT_FREEZE_TYPE" scale="0"/>
	<OUT_ACCT_FREEZE_AMT name="OUT_ACCT_FREEZE_AMT" type="double" length="20" c','6','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('718a4c749f8c479592092d23e3e84883','hinese_name="冻结金额" metadataid="OUT_ACCT_FREEZE_AMT" scale="0"/>
	<IN_ACCT_FREEZE_TYPE name="IN_ACCT_FREEZE_TYPE" type="string" length="10" chinese_name="待添加的资金冻结类型" metadataid="IN_ACCT_FREEZE_TYPE" scale="0"/>
	<REMARK3 name="REMARK3" type="string" length="300" chinese_name="备注3" metadataid="REMARK3" scale="0"/>
	<REMARK4 name="REMARK4" type="string" length="300" chinese_name="备注4" metadataid="REMARK4" scale="0"/>
	<IN_ACCT_FREEZE_AMT name="IN_ACCT_FREEZE_AMT" type="double" length="20" chinese_name="冻结金额" metadataid="IN_ACCT_FREEZE_AMT" scale="0"/>
	<EXPIRY_DATE name="EXPIRY_DATE" type="string" length="8" chinese_name="到期日" metadataid="EXPIRY_DATE" scale="0"/>
	<STATEMENT_NO name="STATEMENT_NO" type="string" length="30" chinese_name="对账单号" metadataid="STATEMENT_NO" scale="0"/>
	<CASH_LOT_NO name="CASH_LOT_NO" type="string" length="50" chinese_name="现金LOT号" metadataid="CASH_LOT_NO" scale="0"/>
	<TRAN_PROPERTY_TYPE name="TRAN_PROPERTY_TYPE" type="string" length="20" chinese_name="交易性质类型" metadataid="TRAN_PROPERTY_TYPE" scale="0"/>
	<OUT_SUSPEND_NO name="OUT_SUSPEND_NO" type="string" length="32" chinese_name="转出挂帐单编号" metadataid="OUT_SUSPEND_NO" scale="0"/>
	<IN_SUSPEND_NO name="IN_SUSPEND_NO" type="string" length="32" chinese_name="转入挂帐单编号" metadataid="IN_SUSPEND_NO" scale="0"/>
	<FREEZE_NO name="FREEZE_NO" type="string" length="20" chinese_name="冻结编号" metadataid="FREEZE_NO" scale="0"/>
	<IN_ACCT_BALANCE name="IN_ACCT_BALANCE" type="double" length="20" chinese_name="转入账户余额" metadataid="IN_ACCT_BALANCE" scale="0"/>
	<IN_BRANCH_ID name="IN_BRANCH_ID" type="string" length="20" chinese_name="转入行号" metadataid="IN_BRANCH_ID" scale="0"/>
	<OUT_ACCT_BALANCE name="OUT_ACCT_BALANCE" type="double" length="20" chinese_name="转出账户余额" metadataid="OUT_ACCT_BALANCE" scale="0"/>
	<OUT_BRANCH_ID name="OUT_BRANCH_ID" type="string" length="20" chinese_name="转出行号" metadataid="OUT_BRANCH_ID" scale="0"/>
	<COMMISSION_ARRAY name="COMMISSION_ARRAY" type="array" length="null" chinese_name="手续费数组" metadataid="COMMISSION_ARRAY" scale="0"/>
	<FEE_CODE name="FEE_CODE" type="string" length="20" chinese_name="费项编号" metadataid="FEE_CODE" scale="0"/>
	<COMMISSION_CCY name="COMMISSION_CCY" type="string" length="3" chinese_name="手续费币种" metadataid="COMMISSION_CCY" scale="0"/>
	<COMMISSION name="COMMISSION" type="double" length="20" chinese_name="手续费金额" metadataid="COMMISSION" scale="0"/>

	<!-- BCARD93017 同卡号换卡申请 -->
	<APPLY_DATE name="APPLY_DATE" type="string" length="8" chinese_name="申请日期" metadataid="APPLY_DATE" scale="0"/>
	<TYPE name="TYPE" type="string" length="20" chinese_name="类型" metadataid="TYPE" scale="0"/>
	<PHONE_NO name="PHONE_NO" type="string" length="30" chinese_name="联系电话" metadataid="PHONE_NO" scale="0"/>
	<SUB_BRANCH_NAME name="SUB_BRANCH_NAME" type="string" length="150" chinese_name="网点名称" metadataid="SUB_BRANCH_NAME" scale="0"/>
	<AUTH_TELLER_NO name="AUTH_TELLER_NO" type="string" length="30" chinese_name="授权柜员" metadataid="AUTH_TELLER_NO" scale="0"/>
	<IMAGE_NO name="IMAGE_NO" type="string" length="150" chinese_name="影像条形号码" metadataid="IMAGE_NO" scale="0"/>
	<CARD_TYPE name="CARD_TYPE" type="string" length="3" chinese_name="卡类型" metadataid="CARD_TYPE" scale="0"/>
	
	
	<!-- BCARD93018 同卡号换卡申请撤销 -->
	<CARD_NO name="CARD_NO" type="string" length="20" chinese_name="卡号" metadataid="CARD_NO" scale="0"/>
	<SEQ_NO name="SEQ_NO" type="string" length="52" chinese_name="流水号" metadataid="SEQ_NO" scale="0"/>
	<APPLICANT_NAME name="APPLICANT_NAME" type="string" length="150" chinese_name="申请人名称" metadataid="APPLICANT_NAME" scale="0"/>
	<SEX name="SEX" type="string" length="1" chinese_name="性别" metadataid="SEX" scale="0"/>
	<GLOBAL_TYPE name="GLOBAL_TYPE" type="str','7','3594cda3a7454fc18e5f4b96ded3e312');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('a7f4d2d5af454d4fade9678b2f75524a','ing" length="5" chinese_name="证件类型" metadataid="GLOBAL_TYPE" scale="0"/>
	<GLOBAL_ID name="GLOBAL_ID" type="string" length="25" chinese_name="证件号码" metadataid="GLOBAL_ID" scale="0"/>
	<BIRTH_DATE name="BIRTH_DATE" type="string" length="8" chinese_name="出生日期" metadataid="BIRTH_DATE" scale="0"/>
	<ACCEPT_SEQ_NO name="ACCEPT_SEQ_NO" type="string" length="100" chinese_name="受理号" metadataid="ACCEPT_SEQ_NO" scale="0"/>


	<!-- VB5001 增加凭证订单申请接口 -->
	<CERT_ID name="CERT_ID" type="string" length="30" chinese_name="凭证代码" metadataid="CERT_ID" scale="0"/>
	<APPLY_NUM name="APPLY_NUM" type="string" length="10" chinese_name="申请数量" metadataid="APPLY_NUM" scale="0"/>
	<APPLY_DESC name="APPLY_DESC" type="string" length="500" chinese_name="申请说明" metadataid="APPLY_DESC" scale="0"/>
	<DATE name="DATE" type="string" length="8" chinese_name="日期" metadataid="DATE" scale="0"/>
	<START_NO name="START_NO" type="string" length="30" chinese_name="开始号码" metadataid="START_NO" scale="0"/>
	<END_NO name="END_NO" type="string" length="30" chinese_name="结束号码" metadataid="END_NO" scale="0"/>
	<SUB_BRANCH_ID name="SUB_BRANCH_ID" type="string" length="20" chinese_name="网点号" metadataid="SUB_BRANCH_ID" scale="0"/>
	<BRANCH_NO name="BRANCH_NO" type="string" length="8" chinese_name="分行号" metadataid="BRANCH_NO" scale="0"/>
	<ORDER_TYPE name="ORDER_TYPE" type="string" length="3" chinese_name="订货方式" metadataid="ORDER_TYPE" scale="0"/>
	<RET_MSG name="RET_MSG" type="string" length="512" chinese_name="返回信息" metadataid="RET_MSG" scale="0"/>


	<!-- VB5002 增加凭证订单作废接口 -->
	<ORDER_NO name="ORDER_NO" type="string" length="40" chinese_name="订单编号" metadataid="ORDER_NO" scale="0"/>
	<REASON name="REASON" type="string" length="300" chinese_name="原因" metadataid="REASON" scale="0"/>
	<ORG_SEQ_ID name="ORG_SEQ_ID" type="string" length="52" chinese_name="原交易流水号" metadataid="ORG_SEQ_ID" scale="0"/>

</metadata>
','8','3594cda3a7454fc18e5f4b96ded3e312');

delete from serviceinfo where serviceid ='BaseChaChlName' and location='local_in';insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseChaChlName','BASE','BaseChaChlName','local_in','2015-04-16 21:38:03');delete from deployments where name='BaseChaChlName' and location='local_in';insert into deployments(location,name,deploydate,filepath,description,id,serviceImpl) values('local_in','BaseChaChlName','2015-04-16 21:38:03','','获取渠道ID','8.','com.dc.esb.base.impls.service.BaseChaChlName');delete from baseServices where serviceid='BaseChaChlName';insert into baseservices (serviceid,paramname,paramvalue,description,id,parentid) values('BaseChaChlName','','','','47fddb4567',null);delete from serviceinfo where serviceid ='BaseReadService' and location='local_in';insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseReadService','BASE','BaseReadService','local_in','2015-04-16 21:38:03');delete from deployments where name='BaseReadService' and location='local_in';insert into deployments(location,name,deploydate,filepath,description,id,serviceImpl) values('local_in','BaseReadService','2015-04-16 21:38:03','','读取请求数据(同步workkey)','68','com.dc.esb.base.impls.service.BaseReadService');delete from baseServices where serviceid='BaseReadService';insert into baseservices (serviceid,paramname,paramvalue,description,id,parentid) values('BaseReadService','','','','eb1569c7bc',null);delete from serviceinfo where serviceid ='BaseSaveBussLog' and location='local_out';insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseSaveBussLog','BASE','BaseSaveBussLog','local_out','2015-04-16 21:38:03');delete from deployments where name='BaseSaveBussLog' and location='local_out';insert into deployments(location,name,deploydate,filepath,description,id,serviceImpl) values('local_out','BaseSaveBussLog','2015-04-16 21:38:03','','','57','com.dc.esb.base.impls.service.BaseSaveBussLog');delete from baseServices where serviceid='BaseSaveBussLog';insert into baseservices (serviceid,paramname,paramvalue,description,id,parentid) values('BaseSaveBussLog','','','','4e6e561047',null);delete from serviceinfo where serviceid ='BaseUpdateBussStatus' and location='local_out';insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseUpdateBussStatus','BASE','BaseUpdateBussStatus','local_out','2015-04-16 21:38:03');delete from deployments where name='BaseUpdateBussStatus' and location='local_out';insert into deployments(location,name,deploydate,filepath,description,id,serviceImpl) values('local_out','BaseUpdateBussStatus','2015-04-16 21:38:03','','','03','com.dc.esb.base.impls.service.BaseUpdateBussStatus');delete from baseServices where serviceid='BaseUpdateBussStatus';insert into baseservices (serviceid,paramname,paramvalue,description,id,parentid) values('BaseUpdateBussStatus','','','','b36e81cf5a',null);delete from serviceinfo where serviceid ='BaseWriteService' and location='DEFAULT';insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseWriteService','BASE','BaseWriteService','local_in','2015-04-16 21:38:03');insert into serviceinfo (serviceid,servicetype,contribution,location,modifytime) values('BaseWriteService','BASE','BaseWriteService','local_out','2015-04-16 21:38:03');delete from deployments where name='BaseWriteService' and location='DEFAULT';insert into deployments(location,name,deploydate,filepath,description,id,serviceImpl) values('DEFAULT','BaseWriteService','2015-04-16 21:38:03','','写响应数据','15','com.dc.esb.base.impls.service.BaseWriteService');delete from baseServices where serviceid='BaseWriteService';insert into baseservices (serviceid,paramname,paramvalue,description,id,parentid) values('BaseWriteService','','','','9375abd3d6',null);insert into protocolbind(protocolid,bindtype,binduri,requestadapter,responseadapter,threadpool) values('HTTP_IN','HTTPChannelConnector','<?xml version="1.0" encoding="UTF-8"?>
<protocol.http protocolName="HTTPChannelConnector" id="HTTP_IN" mode="synchronous" ioDirection="DataIn/DataOut" side="server"><common uri="http://127.0.0.1:48008/smartbrach"/><request encoding="UTF-8" action="toString" requestAdapter="Adaptor_HTTP_IN" method="post"/><response contentType="text/html" encoding="UTF-8" responseAdaprer="Adaptor_Resp"/><security protocol="SSL" bidirectional="false"><keyStore/><trustStore/></security><advanced threadCount="50" connPerHostCount="200" readTimeout="30000" maxConnCount="2000" soLinger="0" writeBufferSize="2048" reuseAddress="true" connectionTimeout="30000" readBufferSize="2048" tcpNoDelay="true"/></protocol.http>','Adaptor_HTTP_IN','Adaptor_Resp','');

insert into protocolbind(protocolid,bindtype,binduri,requestadapter,responseadapter,threadpool) values('MQ_OUT','JMSServiceConnector','<?xml version="1.0" encoding="UTF-8"?>
<protocol.jms protocolName="JMSServiceConnector" id="MQ_OUT" mode="synchronous" ioDirection="DataIn/DataOut" side="client"><request JNDIContextFactory="com.ibm.mq.jms.context.WMQInitialContextFactory" ProviderURL="127.0.0.1:38895/BRANCH.CHL" ConnectionFactory="esb.branch.qm" QueueName="BRANCH.IN" SessionCount="1" locationDepend="false"/><response readTimeout="60000" JNDIContextFactory="com.ibm.mq.jms.context.WMQInitialContextFactory" ProviderURL="127.0.0.1:38895/BRANCH.CHL" ConnectionFactory="esb.branch.qm" QueueName="BRANCH.OUT" SessionCount="1" locationDepend="false"/><advanced/></protocol.jms>','','','');

insert into  ESB_IDENDIFY_RULES(name,type,rulename,ruletype,messagetype,impclass,expression,namespace,mappingtable,mergerule,serviceid) values('CH_HS','IN','CH_HTTPS','dynamic','xml','com.dc.esb.container.service.serviceIdentify.impl.CDMessageParser','/service/sys-header/data/struct/data/SERVICE_CODE&;&/service/sys-header/data/struct/data/SERVICE_SCENE','','18002000001+02=Ca1800200000102'||'&'||';'||'&'||'18002000001+01=Ca1800200000101','%2B','') ;

insert into  ESB_IDENDIFY_RULES(name,type,rulename,ruletype,messagetype,impclass,expression,namespace,mappingtable,mergerule,serviceid) values('SERV','OUT','Busi0400200000226','static','','','','','','','ESB') ;
insert into  ESB_IDENDIFY_RULES(name,type,rulename,ruletype,messagetype,impclass,expression,namespace,mappingtable,mergerule,serviceid) values('SERV','OUT','Busi1300100000607','static','','','','','','','ESB') ;
insert into  ESB_IDENDIFY_RULES(name,type,rulename,ruletype,messagetype,impclass,expression,namespace,mappingtable,mergerule,serviceid) values('SERV','OUT','Busi0400200000227','static','','','','','','','ESB') ;
insert into  ESB_IDENDIFY_RULES(name,type,rulename,ruletype,messagetype,impclass,expression,namespace,mappingtable,mergerule,serviceid) values('SERV','OUT','Busi1300100000608','static','','','','','','','ESB') ;

INSERT INTO ESB_ADAPTER_TEMPLATE(name,adapters,remark,type,protocoladapter,identifyruleid,jorunalClassifyid)VALUES('Adaptor_CH_HTTPS','ServiceIdentify|false|OTHER,BaseChaChlName|false|OTHER,AccessCheckService|false|OTHER,JournalLogService|true|OTHER,UnpackService|false|OTHER,ConsumerClientService|false|OTHER,PackService|false|OTHER,JournalLogService|true|OTHER,FlowRecycleService|true|OTHER','','IN','0','CH_HS','');

INSERT INTO ESB_ADAPTER_TEMPLATE(name,adapters,remark,type,protocoladapter,identifyruleid,jorunalClassifyid)VALUES('Adaptor_HTTP_IN','BaseReadService|false|OTHER','','IN','1','','');

INSERT INTO ESB_ADAPTER_TEMPLATE(name,adapters,remark,type,protocoladapter,identifyruleid,jorunalClassifyid)VALUES('Adaptor_Resp','ExceptionHandlerService|true|OTHER,BaseWriteService|true|OTHER','','IN','1','','');

INSERT INTO ESB_ADAPTER_TEMPLATE(name,adapters,remark,type,protocoladapter,identifyruleid,jorunalClassifyid)VALUES('Adaptor_SERV','SystemIdentify|false|OTHER,PackService|false|OTHER,JournalLogService|true|OTHER,default|false|OTHER,JournalLogService|true|OTHER,UnpackService|false|OTHER','','OUT','0','SERV','');

INSERT INTO ESB_ADAPTER_TEMPLATE(name,adapters,remark,type,protocoladapter,identifyruleid,jorunalClassifyid)VALUES('Adaptor_proxy','JournalLogService|false|OTHER,SimpleProxyService|false|OTHER,JournalLogService|false|OTHER','','OUT','0','','');

insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('CH_HTTPS','Adaptor_CH_HTTPS','IN','local_in');
insert into serviceinfo(serviceid,servicetype,location ,description,modifytime) values('CH_HTTPS','CHANNEL','local_in' ,'HTTP渠道接入(加密)','2015-04-16 21:38:05 847');
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('CH_HTTPS','a31578d478cb9d20de4c67a8aa75f975','70022232c41f240289cac75d5b3e3884','1','2','','local_in','CHANNEL') ;
update services set location ='local_in', outaddressid= '70022232c41f240289cac75d5b3e3884',inaddressid='a31578d478cb9d20de4c67a8aa75f975',sessioncount='1',deliverymode='2',type='CHANNEL' where name='CH_HTTPS' and location='local_in';
delete from bindmap where serviceid='CH_HTTPS' and location='local_in'  and stype='CHANNEL' ;
insert into bindmap(serviceid,location,version,stype,protocolId,MAPTYPE) values('CH_HTTPS','local_in','','CHANNEL','HTTP_IN','request');

insert into esb_runtime_service (serviceid,servicetype) values('Busi0400200000226','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('9680d626f327446cb57997d77bd21f8c','service_Busi0400200000226.xml','xml','service','all','Busi0400200000226',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('703ee3e445cc4c58861028e2b8bf7330','<?xml version="1.0" encoding="UTF-8"?>

<Busi0400200000226>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <SEX metadataid="SEX"/>
                <APPLY_DATE metadataid="APPLY_DATE"/>
                <TYPE metadataid="TYPE"/>
                <PHONE_NO metadataid="PHONE_NO"/>
                <SUB_BRANCH_ID metadataid="SUB_BRANCH_ID"/>
                <SUB_BRANCH_NAME metadataid="SUB_BRANCH_NAME"/>
                <BRANCH_NO metadataid="BRANCH_NO"/>
                <AUTH_TELLER_NO metadataid="AUTH_TELLER_NO"/>
                <REASON metadataid="REASON"/>
                <IMAGE_NO metadataid="IMAGE_NO"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <RET length="1" type="array" metadataid="RET">
                    <sdo array="RET">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </RET>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <CERT_ID metadataid="CERT_ID"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Busi0400200000226>
','1','9680d626f327446cb57997d77bd21f8c');
insert into serviceinfo(serviceid,servicetype,CONTRIBUTION,PREPARED,LOCATION,isCreate,modifytime) values('Busi0400200000226','BUSS','Busi0400200000226','true','local_out','true','2015-04-16 21:38:05');
insert into deployments(location,name,deploydate,filepath,description,username,version,id,serviceImpl) values('local_out','Busi0400200000226','15-4-16 下午9:38','','BCARD93017 同卡号换卡申请','','0','06ce563d878467483a7c','com.dc.esb.container.service.DefaultBusinessService');
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Busi0400200000226','Adaptor_SERV','OUT','local_out');
update services set location ='local_out', outaddressid= '70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',deliverymode='2',type='SERVICE' where name='Busi0400200000226' and location='local_out';
insert into bussservices(serviceid,category,methodname,isarg) values('Busi0400200000226','service','','false');
update serviceinfo set groupname=null where serviceid='Busi0400200000226' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Busi0400200000226','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
delete from bindmap where serviceid='Busi0400200000226' and location='local_out'  and stype='SERVICE' ;
insert into bindmap(serviceid,location,version,stype,protocolId,MAPTYPE) values('Busi0400200000226','local_out','','SERVICE','MQ_OUT','request');
delete  from ServiceSystemMap where name='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';

insert into esb_runtime_service (serviceid,servicetype) values('Busi0400200000227','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('07db1efe3c9847d9bab605e9665462e1','service_Busi0400200000227.xml','xml','service','all','Busi0400200000227',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('38e6ccdaa8094269b427260fe754910c','<?xml version="1.0" encoding="UTF-8"?>

<Busi0400200000227>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <SEQ_NO metadataid="SEQ_NO"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <SEX metadataid="SEX"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <REASON metadataid="REASON"/>
                <ACCEPT_SEQ_NO metadataid="ACCEPT_SEQ_NO"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <RET length="1" type="array" metadataid="RET">
                    <sdo array="RET">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </RET>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Busi0400200000227>
','1','07db1efe3c9847d9bab605e9665462e1');
insert into serviceinfo(serviceid,servicetype,CONTRIBUTION,PREPARED,LOCATION,isCreate,modifytime) values('Busi0400200000227','BUSS','Busi0400200000227','true','local_out','true','2015-04-16 21:38:05');
insert into deployments(location,name,deploydate,filepath,description,username,version,id,serviceImpl) values('local_out','Busi0400200000227','15-4-16 下午9:38','','BCARD93018 同卡号换卡申请撤销','','0','3e87f325aa7490ba1fbe','com.dc.esb.container.service.DefaultBusinessService');
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Busi0400200000227','Adaptor_SERV','OUT','local_out');
update services set location ='local_out', outaddressid= '70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',deliverymode='2',type='SERVICE' where name='Busi0400200000227' and location='local_out';
insert into bussservices(serviceid,category,methodname,isarg) values('Busi0400200000227','service','','false');
update serviceinfo set groupname=null where serviceid='Busi0400200000227' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Busi0400200000227','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
delete from bindmap where serviceid='Busi0400200000227' and location='local_out'  and stype='SERVICE' ;
insert into bindmap(serviceid,location,version,stype,protocolId,MAPTYPE) values('Busi0400200000227','local_out','','SERVICE','MQ_OUT','request');
delete  from ServiceSystemMap where name='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';

insert into esb_runtime_service (serviceid,servicetype) values('Busi1300100000607','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('b05a38c1c6454a03bafe8365560cd287','service_Busi1300100000607.xml','xml','service','all','Busi1300100000607',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('38bc800f394e431c92ce51b4ca6300c8','<?xml version="1.0" encoding="UTF-8"?>

<Busi1300100000607>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CERT_ID metadataid="CERT_ID"/>
                <APPLY_NUM metadataid="APPLY_NUM"/>
                <APPLY_DESC metadataid="APPLY_DESC"/>
                <DATE metadataid="DATE"/>
                <START_NO metadataid="START_NO"/>
                <END_NO metadataid="END_NO"/>
                <SUB_BRANCH_ID metadataid="SUB_BRANCH_ID"/>
                <BRANCH_NO metadataid="BRANCH_NO"/>
                <ORDER_TYPE metadataid="ORDER_TYPE"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <RET length="1" type="array" metadataid="RET">
                    <sdo array="RET">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </RET>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <ORDER_NO metadataid="ORDER_NO"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Busi1300100000607>
','1','b05a38c1c6454a03bafe8365560cd287');
insert into serviceinfo(serviceid,servicetype,CONTRIBUTION,PREPARED,LOCATION,isCreate,modifytime) values('Busi1300100000607','BUSS','Busi1300100000607','true','local_out','true','2015-04-16 21:38:06');
insert into deployments(location,name,deploydate,filepath,description,username,version,id,serviceImpl) values('local_out','Busi1300100000607','15-4-16 下午9:38','','增加凭证订单申请接口','','0','5d80c86e1c0453899f88','com.dc.esb.container.service.DefaultBusinessService');
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Busi1300100000607','Adaptor_SERV','OUT','local_out');
update services set location ='local_out', outaddressid= '70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',deliverymode='2',type='SERVICE' where name='Busi1300100000607' and location='local_out';
insert into bussservices(serviceid,category,methodname,isarg) values('Busi1300100000607','service','','false');
update serviceinfo set groupname=null where serviceid='Busi1300100000607' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Busi1300100000607','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
delete from bindmap where serviceid='Busi1300100000607' and location='local_out'  and stype='SERVICE' ;
insert into bindmap(serviceid,location,version,stype,protocolId,MAPTYPE) values('Busi1300100000607','local_out','','SERVICE','MQ_OUT','request');
delete  from ServiceSystemMap where name='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';

insert into esb_runtime_service (serviceid,servicetype) values('Busi1300100000608','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('44b9416b27034ed283e3f706b6ba539e','service_Busi1300100000608.xml','xml','service','all','Busi1300100000608',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('86672da1be5e41ef9542c360797fe489','<?xml version="1.0" encoding="UTF-8"?>

<Busi1300100000608>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <ORDER_NO metadataid="ORDER_NO"/>
                <REASON metadataid="REASON"/>
                <ORG_SEQ_ID metadataid="ORG_SEQ_ID"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <FILE_PATH metadataid="FILE_PATH"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <RET length="1" type="array" metadataid="RET">
                    <sdo array="RET">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </RET>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body"/>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Busi1300100000608>
','1','44b9416b27034ed283e3f706b6ba539e');
insert into serviceinfo(serviceid,servicetype,CONTRIBUTION,PREPARED,LOCATION,isCreate,modifytime) values('Busi1300100000608','BUSS','Busi1300100000608','true','local_out','true','2015-04-16 21:38:06');
insert into deployments(location,name,deploydate,filepath,description,username,version,id,serviceImpl) values('local_out','Busi1300100000608','15-4-16 下午9:38','','增加凭证订单作废接口','','0','1e1dd8767d542f780203','com.dc.esb.container.service.DefaultBusinessService');
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Busi1300100000608','Adaptor_SERV','OUT','local_out');
update services set location ='local_out', outaddressid= '70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',deliverymode='2',type='SERVICE' where name='Busi1300100000608' and location='local_out';
insert into bussservices(serviceid,category,methodname,isarg) values('Busi1300100000608','service','','false');
update serviceinfo set groupname=null where serviceid='Busi1300100000608' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Busi1300100000608','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
delete from bindmap where serviceid='Busi1300100000608' and location='local_out'  and stype='SERVICE' ;
insert into bindmap(serviceid,location,version,stype,protocolId,MAPTYPE) values('Busi1300100000608','local_out','','SERVICE','MQ_OUT','request');
delete  from ServiceSystemMap where name='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';
delete from ServiceSystemAttr  where ServiceSystemName='ESB';

insert into serviceinfo(Serviceid,servicetype,location) values('Ca1800200000101','PROXY','local_out');
update  proxyservices set xpdl=empty_clob(),contents=empty_clob() where serviceid ='Ca1800200000101';
update proxyservices set xpdl='',contents='<?xml version="1.0" encoding="UTF-8"?>

<proxyEngine>
    <route>
        <from id="ProxyStart1" uri="nmd:Ca1800200000101"/>
        <process id="ProxyStart1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SetVariable" vars="[{proxyKey:ProxyFirstAutoSave,from:sdo,type:RGLOBALVAR}]"/>
        <to id="ProxyBaseSaveBussLog1" uri="nmd:BaseSaveBussLog" serviceId="BaseSaveBussLog" params="ProxyFirstAutoSave" returns=""/>
        <process id="ProxyBaseSaveBussLog1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
        <choice id="ProxyChoice2">
            <when>
                <myHeader paramsIndexes="sdo?path=/sdoroot/SYS_HEAD/RET[0]/SDO/RET_CODE">"000000".equals(${a})</myHeader>
                <pipeline id="ProxyPipeline3">
                    <to id="ProxyBusi04002000002262" uri="nmd:Busi0400200000226" serviceId="Busi0400200000226" params="ProxyFirstAutoSave" returns=""/>
                    <process id="ProxyBusi04002000002262_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
                    <to id="ProxyBaseUpdateBussStatus3" uri="nmd:BaseUpdateBussStatus" serviceId="BaseUpdateBussStatus" params="ProxyFirstAutoSave" returns=""/>
                    <process id="ProxyBaseUpdateBussStatus3_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
                </pipeline>
            </when>
        </choice>
    </route>
</proxyEngine>
'where serviceid='Ca1800200000101';
update services set outaddressid='70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',type='SERVICE' ,deliverymode='2'  where name='Ca1800200000101' and location='local_out';
update serviceinfo set groupname=null where serviceid='Ca1800200000101' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Ca1800200000101','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Ca1800200000101','Adaptor_proxy','OUT','local_out');
insert into esb_runtime_service (serviceid,servicetype) values('Ca1800200000101','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('871d5b6a7707413ab88e90ba4baed720','service_Ca1800200000101.xml','xml','service','all','Ca1800200000101',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('e7a7fe9642294f4f8a83b4e08859064a','<?xml version="1.0" encoding="UTF-8"?>

<Ca1800200000101>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <SEX metadataid="SEX"/>
                <APPLY_DATE metadataid="APPLY_DATE"/>
                <TYPE metadataid="TYPE"/>
                <PHONE_NO metadataid="PHONE_NO"/>
                <SUB_BRANCH_ID metadataid="SUB_BRANCH_ID"/>
                <SUB_BRANCH_NAME metadataid="SUB_BRANCH_NAME"/>
                <BRANCH_NO metadataid="BRANCH_NO"/>
                <AUTH_TELLER_NO metadataid="AUTH_TELLER_NO"/>
                <REASON metadataid="REASON"/>
                <IMAGE_NO metadataid="IMAGE_NO"/>
                <CERT_ID metadataid="CERT_ID"/>
                <APPLY_NUM metadataid="APPLY_NUM"/>
                <APPLY_DESC metadataid="APPLY_DESC"/>
                <DATE metadataid="DATE"/>
                <START_NO metadataid="START_NO"/>
                <END_NO metadataid="END_NO"/>
                <ORDER_TYPE metadataid="ORDER_TYPE"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <sys_head metadataid="sys_head">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <ret length="10" type="array" metadataid="ret">
                    <sdo array="ret">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </ret>
            </sys_head>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="bod','1','871d5b6a7707413ab88e90ba4baed720');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('4e70f4f128664919a3339714420820c8','y">
                <ORDER_NO metadataid="ORDER_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <CERT_ID metadataid="CERT_ID"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Ca1800200000101>
','2','871d5b6a7707413ab88e90ba4baed720');

insert into serviceinfo(Serviceid,servicetype,location) values('Ca1800200000102','PROXY','local_out');
insert into proxyservices (serviceid,xpdl,contents,proxytype,subservices) values ('Ca1800200000102',empty_clob(),empty_clob(),'out','installer');
update  proxyservices set xpdl=empty_clob(),contents=empty_clob() where serviceid ='Ca1800200000102';
update proxyservices set xpdl='',contents='<?xml version="1.0" encoding="UTF-8"?>

<proxyEngine>
    <route>
        <from id="ProxyStart1" uri="nmd:Ca1800200000102"/>
        <process id="ProxyStart1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SetVariable" vars="[{proxyKey:ProxyFirstAutoSave,from:sdo,type:RGLOBALVAR}]"/>
        <to id="ProxyBaseSaveBussLog1" uri="nmd:BaseSaveBussLog" serviceId="BaseSaveBussLog" params="ProxyFirstAutoSave" returns=""/>
        <process id="ProxyBaseSaveBussLog1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
        <choice id="ProxyChoice2">
            <when>
                <myHeader paramsIndexes="sdo?path=/sdoroot/SYS_HEAD/RET[0]/SDO/RET_CODE">"000000".equals(${a})</myHeader>
                <pipeline id="ProxyPipeline3">
                    <to id="ProxyBusi04002000002262" uri="nmd:Busi0400200000227" serviceId="Busi0400200000227" params="ProxyFirstAutoSave" returns=""/>
                    <process id="ProxyBusi04002000002262_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
                    <to id="ProxyBaseUpdateBussStatus3" uri="nmd:BaseUpdateBussStatus" serviceId="BaseUpdateBussStatus" params="ProxyFirstAutoSave" returns=""/>
                    <process id="ProxyBaseUpdateBussStatus3_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>
                </pipeline>
            </when>
        </choice>
    </route>
</proxyEngine>
'where serviceid='Ca1800200000102';
update services set outaddressid='70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',type='SERVICE' ,deliverymode='2'  where name='Ca1800200000102' and location='local_out';
update serviceinfo set groupname=null where serviceid='Ca1800200000102' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Ca1800200000102','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;
insert into DataAdapter(DATAADAPTERID,DATAADAPTER,ADAPTERTYPE,LOCATION) values('Ca1800200000102','Adaptor_proxy','OUT','local_out');
insert into esb_runtime_service (serviceid,servicetype) values('Ca1800200000102','busi');
insert into esb_runtime_file (fileid,filename,fileext,filetype,inoutside,serviceid,operatetime,fileblob) values('2c354ed81f4644ae9b8f736d5429373f','service_Ca1800200000102.xml','xml','service','all','Ca1800200000102',sysdate,empty_blob());
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('9193b2a6d9544a92b5803a04a5738c2f','<?xml version="1.0" encoding="UTF-8"?>

<Ca1800200000102>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <SEX metadataid="SEX"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <REASON metadataid="REASON"/>
                <ACCEPT_SEQ_NO metadataid="ACCEPT_SEQ_NO"/>
                <ORDER_NO metadataid="ORDER_NO"/>
                <ORG_SEQ_ID metadataid="ORG_SEQ_ID"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <sys_head metadataid="sys_head">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <ret length="10" type="array" metadataid="ret">
                    <sdo array="ret">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </ret>
            </sys_head>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <ORDER_NO metadataid="ORDER_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <CERT_ID metadataid="CERT_ID"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Ca1800200000102>
','1','2c354ed81f4644ae9b8f736d5429373f');


insert into ServiceSystem (name,description,realchannel,adapter) values('ESB','','false','MQ_OUT' );
insert into ServiceSystemMap (name,ServiceId,adapter)  values('ESB','Busi0400200000226','MQ_OUT');
insert into ServiceSystemMap (name,ServiceId,adapter)  values('ESB','Busi0400200000227','MQ_OUT');
insert into ServiceSystemMap (name,ServiceId,adapter)  values('ESB','Busi1300100000607','MQ_OUT');
insert into ServiceSystemMap (name,ServiceId,adapter)  values('ESB','Busi1300100000608','MQ_OUT');

insert into serviceinfo(Serviceid,servicetype,location) values('Ca1800200000101','PROXY','local_out');
insert into proxyservices (serviceid,xpdl,contents,proxytype,subservices) values ('Ca1800200000101',empty_clob(),empty_clob(),'out','installer');update  proxyservices set xpdl=empty_clob(),contents=empty_clob() where serviceid ='Ca1800200000101';update proxyservices set xpdl='',contents='<?xml version="1.0" encoding="UTF-8"?><proxyEngine>    <route>        <from id="ProxyStart1" uri="nmd:Ca1800200000101"/>        <process id="ProxyStart1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SetVariable" vars="[{proxyKey:ProxyFirstAutoSave,from:sdo,type:RGLOBALVAR}]"/>        <to id="ProxyBaseSaveBussLog1" uri="nmd:BaseSaveBussLog" serviceId="BaseSaveBussLog" params="ProxyFirstAutoSave" returns=""/>        <process id="ProxyBaseSaveBussLog1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>        <choice id="ProxyChoice2">            <when>                <myHeader paramsIndexes="sdo?path=/sdoroot/SYS_HEAD/RET[0]/SDO/RET_CODE">"000000".equals(${a})</myHeader>                <pipeline id="ProxyPipeline3">                    <to id="ProxyBusi04002000002262" uri="nmd:Busi0400200000226" serviceId="Busi0400200000226" params="ProxyFirstAutoSave" returns=""/>                    <process id="ProxyBusi04002000002262_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>                    <to id="ProxyBaseUpdateBussStatus3" uri="nmd:BaseUpdateBussStatus" serviceId="BaseUpdateBussStatus" params="ProxyFirstAutoSave" returns=""/>                    <process id="ProxyBaseUpdateBussStatus3_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>                </pipeline>            </when>        </choice>    </route></proxyEngine>'where serviceid='Ca1800200000101';update services set outaddressid='70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',type='SERVICE' ,deliverymode='2'  where name='Ca1800200000101' and location='local_out';
update serviceinfo set groupname=null where serviceid='Ca1800200000101' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Ca1800200000101','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;


insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('7885239241004fe5b46182bde8d0abe7','<?xml version="1.0" encoding="UTF-8"?>

<Ca1800200000101>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <SEX metadataid="SEX"/>
                <APPLY_DATE metadataid="APPLY_DATE"/>
                <TYPE metadataid="TYPE"/>
                <PHONE_NO metadataid="PHONE_NO"/>
                <SUB_BRANCH_ID metadataid="SUB_BRANCH_ID"/>
                <SUB_BRANCH_NAME metadataid="SUB_BRANCH_NAME"/>
                <BRANCH_NO metadataid="BRANCH_NO"/>
                <AUTH_TELLER_NO metadataid="AUTH_TELLER_NO"/>
                <REASON metadataid="REASON"/>
                <IMAGE_NO metadataid="IMAGE_NO"/>
                <CERT_ID metadataid="CERT_ID"/>
                <APPLY_NUM metadataid="APPLY_NUM"/>
                <APPLY_DESC metadataid="APPLY_DESC"/>
                <DATE metadataid="DATE"/>
                <START_NO metadataid="START_NO"/>
                <END_NO metadataid="END_NO"/>
                <ORDER_TYPE metadataid="ORDER_TYPE"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <sys_head metadataid="sys_head">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <ret length="10" type="array" metadataid="ret">
                    <sdo array="ret">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </ret>
            </sys_head>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="bod','1','4a6908222e134fde8a26084d27165649');
insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('7dcf8eb71ae84b75a916d753b91f8ffd','y">
                <ORDER_NO metadataid="ORDER_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <CERT_ID metadataid="CERT_ID"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Ca1800200000101>
','2','4a6908222e134fde8a26084d27165649');

insert into serviceinfo(Serviceid,servicetype,location) values('Ca1800200000102','PROXY','local_out');
update  proxyservices set xpdl=empty_clob(),contents=empty_clob() where serviceid ='Ca1800200000102';update proxyservices set xpdl='',contents='<?xml version="1.0" encoding="UTF-8"?><proxyEngine>    <route>        <from id="ProxyStart1" uri="nmd:Ca1800200000102"/>        <process id="ProxyStart1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SetVariable" vars="[{proxyKey:ProxyFirstAutoSave,from:sdo,type:RGLOBALVAR}]"/>        <to id="ProxyBaseSaveBussLog1" uri="nmd:BaseSaveBussLog" serviceId="BaseSaveBussLog" params="ProxyFirstAutoSave" returns=""/>        <process id="ProxyBaseSaveBussLog1_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>        <choice id="ProxyChoice2">            <when>                <myHeader paramsIndexes="sdo?path=/sdoroot/SYS_HEAD/RET[0]/SDO/RET_CODE">"000000".equals(${a})</myHeader>                <pipeline id="ProxyPipeline3">                    <to id="ProxyBusi04002000002262" uri="nmd:Busi0400200000227" serviceId="Busi0400200000227" params="ProxyFirstAutoSave" returns=""/>                    <process id="ProxyBusi04002000002262_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>                    <to id="ProxyBaseUpdateBussStatus3" uri="nmd:BaseUpdateBussStatus" serviceId="BaseUpdateBussStatus" params="ProxyFirstAutoSave" returns=""/>                    <process id="ProxyBaseUpdateBussStatus3_ProxyProcess" ref="com.dc.esb.container.proxyservice.SDOComposeProcess" vars="ProxyFirstAutoSave;current"/>                </pipeline>            </when>        </choice>    </route></proxyEngine>'where serviceid='Ca1800200000102';update services set outaddressid='70022232c41f240289cac75d5b3e3884',inaddressid='4cc95abd8b9cba4705f290b39dee3d6a',sessioncount='1',type='SERVICE' ,deliverymode='2'  where name='Ca1800200000102' and location='local_out';
update serviceinfo set groupname=null where serviceid='Ca1800200000102' and location='local_out';
insert into services(name,inaddressid,outaddressid,sessioncount,deliverymode,nodeid,location,type) values('Ca1800200000102','4cc95abd8b9cba4705f290b39dee3d6a','70022232c41f240289cac75d5b3e3884','1','2','','local_out','SERVICE') ;


insert into esb_runtime_file_content(cid,contentxml,rowindex,fileid) values('3b3f9ec86a0945408e58ad8988b78a3a','<?xml version="1.0" encoding="UTF-8"?>

<Ca1800200000102>
    <request>
        <sdoroot>
            <SYS_HEAD metadataid="SYS_HEAD">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
            </SYS_HEAD>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <CARD_NO metadataid="CARD_NO"/>
                <APPLICANT_NAME metadataid="APPLICANT_NAME"/>
                <SEX metadataid="SEX"/>
                <GLOBAL_TYPE metadataid="GLOBAL_TYPE"/>
                <GLOBAL_ID metadataid="GLOBAL_ID"/>
                <BIRTH_DATE metadataid="BIRTH_DATE"/>
                <REASON metadataid="REASON"/>
                <ACCEPT_SEQ_NO metadataid="ACCEPT_SEQ_NO"/>
                <ORDER_NO metadataid="ORDER_NO"/>
                <ORG_SEQ_ID metadataid="ORG_SEQ_ID"/>
            </body>
        </sdoroot>
    </request>
    <response>
        <sdoroot>
            <sys_head metadataid="sys_head">
                <SERVICE_CODE metadataid="SERVICE_CODE"/>
                <SERVICE_SCENE metadataid="SERVICE_SCENE"/>
                <MODULE_ID metadataid="MODULE_ID"/>
                <TRAN_TIMESTAMP metadataid="TRAN_TIMESTAMP"/>
                <USER_LANG metadataid="USER_LANG"/>
                <TRAN_DATE metadataid="TRAN_DATE"/>
                <CONSUMER_SVR_ID metadataid="CONSUMER_SVR_ID"/>
                <ESB_SEQ_NO metadataid="ESB_SEQ_NO"/>
                <CONSUMER_SEQ_NO metadataid="CONSUMER_SEQ_NO"/>
                <WS_ID metadataid="WS_ID"/>
                <CONSUMER_ID metadataid="CONSUMER_ID"/>
                <ORG_SYS_ID metadataid="ORG_SYS_ID"/>
                <RET_STATUS metadataid="RET_STATUS"/>
                <ret length="10" type="array" metadataid="ret">
                    <sdo array="ret">
                        <RET_CODE metadataid="RET_CODE"/>
                        <RET_MSG metadataid="RET_MSG"/>
                    </sdo>
                </ret>
            </sys_head>
            <APP_HEAD metadataid="APP_HEAD">
                <BIZ_SEQ_NO metadataid="BIZ_SEQ_NO"/>
                <PGUP_OR_PGDN metadataid="PGUP_OR_PGDN"/>
                <USER_ID metadataid="USER_ID"/>
                <PER_PAGE_NUM metadataid="PER_PAGE_NUM"/>
                <BRANCH_ID metadataid="BRANCH_ID"/>
            </APP_HEAD>
            <body metadataid="body">
                <ORDER_NO metadataid="ORDER_NO"/>
                <CARD_TYPE metadataid="CARD_TYPE"/>
                <CERT_ID metadataid="CERT_ID"/>
            </body>
        </sdoroot>
    </response>
    <error>
        <sdoroot/>
    </error>
</Ca1800200000102>
','1','c4f9fd8eb04041c7847e3707efb3961e');


commit;