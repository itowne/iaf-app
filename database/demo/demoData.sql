--机构

insert into t_inst(inst_id, i_inst,inst_name,loan_permit,reg_time,reg_capital,busi_license,tax_reg_no,open_time,inst_nature,contact,contact_phone,reg_addr,inst_stat,gen_time,upd_time,total_capital,logo_i_file) 
values('0000000001',1,'中国工商银行广州支行','A123456','2001-01-01',5000,'A12345678','A12345678','2001-1-2','银行','张三','8888 9999','中国广东省广州市东风中路123号','1','2013-3-9','2013-3-9',1000000,1);

insert into t_inst(inst_id,i_inst,inst_name,loan_permit,reg_time,reg_capital,busi_license,tax_reg_no,open_time,inst_nature,contact,contact_phone,reg_addr,inst_stat,gen_time,upd_time,total_capital,logo_i_file) 
values('0000000002',5,'中国银行广州支行','A11212','2001-01-01',5000,'A12345678','A12345678','2001-1-2','银行','李四','8888 9999','中国广东省广州市东风中路124号','1','2013-3-9','2013-3-9',1000000,2);


--机构法人资料表
insert into t_inst_legal_pers (i_inst_legal_pers,i_inst,legal_pers_name,cerd_no,sex,age,education,marital_status,family_addr,gen_time,upd_time) 
values (1,1,'李四','440123456789123456','1',30,'1','1','中国广东省广州市天河区中山大道20号2003房','2013-3-9','2013-3-9');

--机构业务资料
insert into t_inst_busi_info(i_inst_busi_info,i_inst,accept_region,loan_account_no,bank,loan_name,repayment_no,repayment_bank,repayment_name,bank_code) 
values(1,1,'广东省','95588123456789333','中国工商银行广州支行','赵武','95588123456789333','中国工商银行广州支行','赵武','000000');

--机构角色
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (1,1,'老板','老板',1,'2013-3-9');
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (2,1,'业务员','业务员',1,'2013-3-9');
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (3,1,'财务经理','财务经理',1,'2013-3-9');
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (4,1,'借贷经理','借贷经理',1,'2013-3-9');
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (5,1,'系统管理员','系统管理员',1,'2013-3-9');
insert into t_inst_role(i_inst_role,i_inst,role_name,memo,role_stat,gen_time) values (6,1,'业务助理','业务助理',1,'2013-3-9');

--机构用户表
insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(1,1,'hzf','hzf','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','13570007843','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(2,1,'lyq','lyq','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','15602213246','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(3,1,'tjd','tjd','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','18922157843','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(4,1,'ldq','ldq','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','13645054858','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(5,1,'szn','szn','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','13960902343','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(6,1,'wm','wm','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','18605919561','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(7,1,'tzy','tzy','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','18250168694','123@newland.com');

insert into t_inst_user(i_inst_user,i_inst,login_name,user_name,passwd,usr_stat,gen_time,upd_time,last_login_time,position,qqnum,phone,mail) 
values(8,1,'wwa','wwa','H4LJQr79optu1IelHaGZ94/OfwU=',1,'2013-3-9','2013-3-9','2013-3-9','职位','1234567','18759178933','123@newland.com');


--机构用户角色
insert into t_inst_user_role(i_inst_user,i_inst_role) values(1,1);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(2,2);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(3,3);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(4,4);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(5,5);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(6,6);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(7,5);
insert into t_inst_user_role(i_inst_user,i_inst_role) values(8,6);



--机构角色权限表
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(1,1);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(1,2);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(1,3);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(1,4);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(1,5);
--
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(2,6);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(2,7);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(2,8);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(2,9);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(2,10);
--
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(3,6);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(3,7);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(3,8);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(3,9);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(3,10);
--
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(4,11);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(4,12);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(4,13);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(4,14);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(4,15);
--
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(5,1);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(5,2);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(5,3);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(5,4);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(5,5);

--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,1000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2110);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2120);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2130);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2200);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2210);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2220);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,2230);


--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3200);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3300);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3400);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,3500);

--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,4000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,4100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,4200);




--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5010);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5020);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5030);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5040);

--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5200);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,5300);


--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6010);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6020);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6030);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6040);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6050);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,6060);



--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7110);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7120);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7130);

--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7200);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7210);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7220);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,7230);




--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,8000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,8100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,8110);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,8120);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,8200);


--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9000);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9100);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9110);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9120);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9200);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9210);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9220);
--insert into t_inst_role_auth(i_inst_role,i_inst_auth) values(6,9300);

--商户
insert into t_merch(merch_no,i_merch,merch_name,reg_no,reg_addr,reg_time,pos_addr,businlic,tax_reg,open_time,reg_cap,contract,contract_tel,email,merch_type,credit,industry,company_size,audit_addr,audir_businlic,province) 
values('000000000000001',1,'王老吉','注册编号','注册地址','2013-3-9','机具装机地址','营业执照','税务登记证','2013-3-9','10000','联系人','联系电话','邮件地址','1','信用情况','所属行业','经营规模','1','1','所属省份');

insert into t_merch(merch_no,i_merch,merch_name,reg_no,reg_addr,reg_time,pos_addr,businlic,tax_reg,open_time,reg_cap,contract,contract_tel,email,merch_type,credit,industry,company_size,audit_addr,audir_businlic,province) 
values('000000000000002',2,'七匹狼','注册编号','注册地址','2013-3-9','机具装机地址','营业执照','税务登记证','2013-3-9','10000','联系人','联系电话','邮件地址','1','信用情况','所属行业','经营规模','1','1','所属省份');

insert into t_merch(merch_no,i_merch,merch_name,reg_no,reg_addr,reg_time,pos_addr,businlic,tax_reg,open_time,reg_cap,contract,contract_tel,email,merch_type,credit,industry,company_size,audit_addr,audir_businlic,province) 
values('000000000000003',3,'intel','注册编号','注册地址','2013-3-9','机具装机地址','营业执照','税务登记证','2013-3-9','10000','联系人','联系电话','邮件地址','1','信用情况','所属行业','经营规模','1','1','所属省份');


--商户法人
insert into t_merch_legal_pers(i_merch_legal_pers,i_merch,legal_pers_name,sex,age,cerd_no,marital_status,office_phone,mobi_phone,fa_addr,edu_background) 
values(1,1,'奥巴马',1,20,'身份证号',1,'办公电话','手机','家庭地址','1');

insert into t_merch_legal_pers(i_merch_legal_pers,i_merch,legal_pers_name,sex,age,cerd_no,marital_status,office_phone,mobi_phone,fa_addr,edu_background) 
values(2,2,'布什',1,20,'身份证号',1,'办公电话','手机','家庭地址','1');

insert into t_merch_legal_pers(i_merch_legal_pers,i_merch,legal_pers_name,sex,age,cerd_no,marital_status,office_phone,mobi_phone,fa_addr,edu_background) 
values(3,3,'华盛顿',1,20,'身份证号',1,'办公电话','手机','家庭地址','1');


--商户业务资料
insert into t_merch_busi_info(i_merch_busi_info,i_merch,bank,account_no,merch_natrue,bank_code,account_name) values(1,1,'银行','贷款资金划入账户','商户性质','000000','贷款账户名');
insert into t_merch_busi_info(i_merch_busi_info,i_merch,bank,account_no,merch_natrue,bank_code,account_name) values(2,2,'银行','贷款资金划入账户','商户性质','000000','贷款账户名');
insert into t_merch_busi_info(i_merch_busi_info,i_merch,bank,account_no,merch_natrue,bank_code,account_name) values(3,3,'银行','贷款资金划入账户','商户性质','000000','贷款账户名');

--商户用户表
insert into t_merch_user(i_merch_user,i_merch,login_name,passwd,user_name,gen_time,upd_time,last_login_time ,merch_stat,user_type) 
values(1,1,'merch1','H4LJQr79optu1IelHaGZ94/OfwU=','商户1','2013-3-9','2013-3-9','2013-3-9',0,'common');

insert into t_merch_user(i_merch_user,i_merch,login_name,passwd,user_name,gen_time,upd_time,last_login_time ,merch_stat,user_type) 
values(2,1,'merch2','H4LJQr79optu1IelHaGZ94/OfwU=','商户2','2013-3-9','2013-3-9','2013-3-9',0,'common');

insert into t_merch_user(i_merch_user,i_merch,login_name,passwd,user_name,gen_time,upd_time,last_login_time ,merch_stat,user_type) 
values(3,1,'merch3','H4LJQr79optu1IelHaGZ94/OfwU=','商户3','2013-3-9','2013-3-9','2013-3-9',0,'common');

--产品
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(1,1,1,'红棉贷',1,10,1,1,3,5,'全国',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000001');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(2,2,1,'快速贷',1,10,1,1,3,5,'福州',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000002');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(3,3,1,'批发贷',1,10,1,1,3,5,'广东',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000003');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(4,4,1,'服装贷',1,10,1,1,3,5,'上海',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000004');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(5,5,1,'低息贷',1,10,1,1,3,5,'北京',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000005');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(6,1,1,'红棉贷',1,10,1,1,3,5,'全国',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000001');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(7,2,1,'快速贷',1,10,1,1,3,5,'福州',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000002');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(8,3,1,'批发贷',1,10,1,1,3,5,'广东',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000003');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(9,4,1,'服装贷',1,10,1,1,3,5,'上海',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000004');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(10,5,1,'低息贷',1,10,1,1,3,5,'北京',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000005');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(11,1,1,'红棉贷',1,10,1,1,3,5,'全国',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000001');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(12,2,1,'快速贷',1,10,1,1,3,5,'福州',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000002');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(13,3,1,'批发贷',1,10,1,1,3,5,'广东',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000003');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(14,4,1,'服装贷',1,10,1,1,3,5,'上海',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000004');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(15,5,1,'低息贷',1,10,1,1,3,5,'北京',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000005');
--
--insert into t_loan_pdt(i_loan_pdt,I_LOAN_PDT_HIS,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,version,upd_time,pdt_stat,shelf,introduce,load_pdt_id) 
--values(16,5,1,'低息贷',1,10,1,1,3,5,'北京',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9',1,'2013-3-9',1,true,'产品特点简述','P0000005');

--产品历史
----insert into t_loan_pdt_his(i_loan_pdt_his,i_loan_pdt,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,upd_time,shelf,introduce) 
----values(1,1,1,'红棉贷',1,10,1,1,3,5,'全国',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9','2013-3-9',true,'产品特点简述');
----
----insert into t_loan_pdt_his(i_loan_pdt_his,i_loan_pdt,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,upd_time,shelf,introduce) 
----values(2,2,1,'快速贷',1,10,1,1,3,5,'福州',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9','2013-3-9',true,'产品特点简述');
----
----insert into t_loan_pdt_his(i_loan_pdt_his,i_loan_pdt,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,upd_time,shelf,introduce) 
----values(3,3,1,'批发贷',1,10,1,1,3,5,'广东',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9','2013-3-9',true,'产品特点简述');
----
----insert into t_loan_pdt_his(i_loan_pdt_his,i_loan_pdt,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,upd_time,shelf,introduce) 
----values(4,4,1,'服装贷',1,10,1,1,3,5,'上海',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9','2013-3-9',true,'产品特点简述');
----
----insert into t_loan_pdt_his(i_loan_pdt_his,i_loan_pdt,i_inst,pdt_name,min_quota,max_quota,min_term,max_term,rate,credit_term,region,repayment,feature,req_cond,rereq,gen_time,upd_time,shelf,introduce) 
----values(5,5,1,'低息贷',1,10,1,1,3,5,'北京',1,'无抵押、无担保、可提前还款','申请条件','申请所需材料','2013-3-9','2013-3-9',true,'产品特点简述');
--
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type ,merch_name,merch_type)   
--values (1,1,1,'2013-04-08','2013-03-08',1,1,'1资金周转',30000,1,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (2,1,1,'2013-04-08','2013-03-08',1,1,'2资金周转',30000,2,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (3,1,1,'2013-04-08','2013-03-08',1,1,'3资金周转',30000,3,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (4,1,1,'2013-04-08','2013-03-08',1,1,'4资金周转',30000,4,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (5,1,1,'2013-04-08','2013-03-08',1,1,'5资金周转',30000,5,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type ,merch_name,merch_type)   
--values (6,1,1,'2013-04-08','2013-03-08',1,1,'6资金周转',30000,1,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (7,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,2,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (8,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,3,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (9,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,4,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (10,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,5,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type ,merch_name,merch_type)   
--values (11,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,1,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (12,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,2,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (13,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,3,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (14,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,4,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (15,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,5,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type ,merch_name,merch_type)   
--values (16,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,1,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (17,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,2,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (18,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,3,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (19,1,1,'2013-04-08','2013-03-08',1,1,'资金周转',30000,4,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (20,1,1,'2013-04-08','2013-03-08',1,1,'资金周转0',30000,5,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (21,1,1,'2013-04-08','2013-03-08',1,1,'资金周转1',30000,5,'2013-03-08',1,10,1,'王老吉',1);
--insert into  T_DEBIT_BID  (  i_debit_bid ,bid_stat, debit_bid_id  , exp_date , gen_time , i_merch , i_merch_user ,purpose , quota, term , upd_time , version , year_rate ,refund_type,merch_name,merch_type)   
--values (23,1,1,'2013-04-08','2013-03-08',1,1,'资金周转3',30000,5,'2013-03-08',1,10,1,'王老吉',1);

insert into t_inst_notice(i_inst_notice,title,gen_time,status) values(1,'中国人民银行关于借款规定2012','2013-03-08', 1);
insert into t_inst_notice(i_inst_notice,title,gen_time,status) values(2,'从事放贷业务公司守则','2013-03-08', 1);
insert into t_inst_notice(i_inst_notice,title,gen_time,status) values(3,'中国人民银行金融法','2013-03-08', 1);

update t_loan_pdt set delete_flag=false;

--insert into t_file ( ext_name , file_type , i_file , i_inst , i_inst_user , i_merch , i_merch_user , meta_type ,path , upload_name , uuid_name ) values ('gif','instLogo',1,1,1,null,null,'GIF','E:\\huika\\iaf-app\\iaf-war\\src\\main\\webapp\\resources\\images\\logo\\','icbc','121321312312312');

--insert into t_file ( ext_name , file_type , i_file , i_inst , i_inst_user , i_merch , i_merch_user , meta_type ,path , upload_name , uuid_name ) values ('gif','instLogo',2,5,2,null,null,'GIF','E:\\huika\\iaf-app\\iaf-war\\src\\main\\webapp\\resources\\images\\logo\\','boc','121321312312313');
