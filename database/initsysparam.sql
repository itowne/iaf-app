delete from t_inst_auth where 1=1;

#我的首页
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1000, null, 'menu', 'WDSY_MENU_ALL', '我的首页', '我的首页', '我的首页', '/inst/welcome');


#申请受理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2000, null, 'menu', 'SQSL_MENU_ALL', '申请受理', '申请受理', '申请受理', '#');

#申请受理-产品受理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2100, 2000, 'menu', 'SQSL_CPSL_MENU', '产品受理', '产品受理', '申请受理-产品受理', '/inst/loanord/prodAccept');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2110, 2100, 'operation', 'SQSL_CPSL_CX', '查询', '查询', '申请受理-产品受理-查询', '/inst/loanord/prodAccept');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2120, 2100, 'operation', 'SQSL_CPSL_SL', '受理', '受理', '申请受理-产品受理-受理', '/inst/loanord/prodAccept');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2130, 2100, 'operation', 'SQSL_CPSL_JJ', '拒绝', '拒绝', '申请受理-产品受理-拒绝 ', '/inst/loanord/prodAccept');


#申请受理-竞投受理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2200, 2000, 'menu', 'SQSL_JTSL_MENU', '竞投受理', '竞投受理', '申请受理-竞投受理', '/inst/loanord/bidAccept');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2210, 2200, 'operation', 'SQSL_JTSL_CX', '查询', '查询', '申请受理-竞投受理-查询', '/inst/loanord/bidAccept');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2220, 2200, 'operation', 'SQSL_JTSL_SL', '受理', '受理', '申请受理-竞投受理-受理', '/inst/loanord/bidAccept');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2230, 2200, 'operation', 'SQSL_JTSL_JJ', '拒绝', '拒绝', '申请受理-竞投受理-拒绝', '/inst/loanord/bidAccept');


#审批处理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3000, null, 'menu', 'SPCL_MENU_ALL', '审批处理', '审批处理', '审批处理', '/inst/loanord/prodAudit');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3100, 3000, 'operation', 'SPCL_CX', '查询', '查询', '审批处理-查询', '/inst/loanord/prodAudit');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3200, 3000, 'operation', 'SPCL_SH', '审核 ', '审核 ', '审批处理-审核', '/inst/loanord/prodAudit');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3300, 3000, 'operation', 'SPCL_SCHKJH', '上传还款计划', '上传还款计划', '审批处理-上传还款计划', '/inst/loanord/prodAudit');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3400, 3000, 'operation', 'SPCL_XGJH', '修改计划 ', '修改计划 ', '审批处理-修改计划 ', '/inst/loanord/prodAudit');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3500, 3000, 'operation', 'SPCL_ZFJH', '作废计划', '作废计划', '审批处理-作废计划', '/inst/loanord/prodAudit');


#放款处理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4000, null, 'menu', 'FKGL_MENU_ALL', '放款处理', '放款处理', '放款处理', '/inst/loanord/prodCredit');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4100, 4000, 'operation', 'FKGL_CX', '查询', '查询', '放款处理-查询', '/inst/loanord/prodCredit');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4200, 4000, 'operation', 'FKGL_FK', '放款', '放款', '放款处理-放款', '/inst/loanord/prodCredit');


#贷后管理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5000, null, 'menu', 'DHGL_MENU_ALL', '贷后管理', '贷后管理', '贷后管理', '#');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5010, 5000, 'operation', 'DHGL_CX', '查询', '查询', '贷后管理-查询', '#');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5020, 5000, 'operation', 'DHGL_DJ', '冻结', '冻结', '贷后管理-冻结', '#');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5030, 5000, 'operation', 'DHGL_JD', '解冻', '解冻', '贷后管理-解冻', '#');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5040, 5000, 'operation', 'DHGL_FXCX', '风险查询', '风险查询', '贷后管理-风险查询', '#');


insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5100, 5000, 'menu', 'DHGL_ZDHKJH_MENU', '制定还款计划', '制定还款计划', '贷后管理-制定还款计划', '/inst/loanord/planUpload');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5200, 5000, 'menu', 'DHGL_FXKZ_MENU', '风险监控', '风险监控', '贷后管理-风险监控', '#');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5300, 5000, 'menu', 'DHGL_HKJH_MENU', '还款管理', '还款管理', '贷后管理-还款管理', '/inst/loanord/refundMgr');

#我的产品
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6000, null, 'menu', 'WDCP_MENU_ALL', '我的产品', '我的产品', '我的产品', '/inst/productPublish');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6010, 6000, 'operation', 'WDCP_CX', '查询', '查询', '我的产品-查询', '/inst/productPublish');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6020, 6000, 'operation', 'WDCP_XG', '修改', '修改', '我的产品-修改', '/inst/productPublish');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6030, 6000, 'operation', 'WDCP_XZ', '新增', '新增', '我的产品-新增', '/inst/productPublish');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6040, 6000, 'operation', 'WDCP_SJ', '上架', '上架', '我的产品-上架', '/inst/productPublish');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6050, 6000, 'operation', 'WDCP_XJ', '下架', '下架', '我的产品-下架', '/inst/productPublish');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6060, 6000, 'operation', 'WDCP_SC', '删除 ', '删除 ', '我的产品-删除 ', '/inst/productPublish');


#我的关注
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7000, null, 'menu', 'WDGZ_MENU_ALL', '我的关注', '我的关注', '我的关注', '#');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7100, 7000, 'menu', 'WDGZ_GZKH_MENU', '关注的客户', '关注的客户', '我的关注-关注的客户', 'inst/concerned-customer.jsp');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7110, 7100, 'operation', 'WDGZ_GZKH_CX', '查询', '查询', '我的关注-关注的客户-查询', '/inst/loanord/refundMgr');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7120, 7100, 'operation', 'WDGZ_GZKH_TJ', '添加', '添加', '我的关注-关注的客户-添加', '/inst/loanord/refundMgr');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7130, 7100, 'operation', 'WDGZ_GZKH_SC', '删除', '删除', '我的关注-关注的客户-删除', '/inst/loanord/refundMgr');


insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7200, 7000, 'menu', 'WDGZ_GZDD_MENU', '关注的订单', '关注的订单', '我的关注-关注的订单', '/inst/orders-of-concern.jsp');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7210, 7200, 'operation', 'WDGZ_GZDD_CX', '查询', '查询', '我的关注-关注的订单-查询', '/inst/loanord/refundMgr');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7220, 7200, 'operation', 'WDGZ_GZDD_TJ', '添加', '添加', '我的关注-关注的订单-添加', '/inst/loanord/refundMgr');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7230, 7200, 'operation', 'WDGZ_GZDD_SC', '修改', '修改', '我的关注-关注的订单-修改', '/inst/loanord/refundMgr');

#我的资料
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8000, null, 'menu', 'WDZL_MENU_ALL', '我的资料', '我的资料', '我的资料', '#');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8100, 8000, 'menu', 'WDZL_ZLGL_MENU', '资料管理', '资料管理', '我的资料-资料管理', 'inst/information/instInformation');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8110, 8100, 'operation', 'WDZL_CX', '查询', '查询', '我的资料-资料管理-查询', '/inst/information/instInformation');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8120, 8100, 'operation', 'WDZL_XG', '修改', '修改', '我的资料-资料管理-修改', '/inst/information/instInformation');


insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8200, 8000, 'menu', 'WDZL_MMXG_MENU', '修改密码', '修改密码', '我的关注-修改密码', '/inst/password.jsp');

#系统管理
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9000, null, 'menu', 'XTGL_MENU_ALL', '系统管理', '系统管理', '系统管理', '#');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9100, 9000, 'menu', 'XTGL_JSQX_MENU', '角色权限', '角色权限', '系统管理-角色权限', '/inst/instRole');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9110, 9100, 'operation', 'XTGL_JSQX_XZJS', '新增角色', '新增', '系统管理-角色权限-新增', '/inst/instRole');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9120, 9100, 'operation', 'XTGL_JSQX_XGJS', '修改角色', '修改', '系统管理-角色权限-修改', '/inst/instRole');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9130, 9100, 'operation', 'XTGL_JSQX_XZJS', '暂停角色', '暂停', '系统管理-角色权限-暂停', '/inst/instRole');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9140, 9100, 'operation', 'XTGL_JSQX_XGMM', '修改密码', '修改密码', '系统管理-角色权限-修改密码', '/inst/instRole');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9200, 9000, 'menu', 'XTGL_ZHGL_MENU', '帐号管理', '帐号管理', '系统管理-帐号管理', '/inst/instUser');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9210, 9200, 'operation', 'XTGL_ZHQX_XGZH', '新增帐号', '新增帐号', '系统管理-帐号管理-新增帐号', '/inst/instUser');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9220, 9200, 'operation', 'XTGL_ZHQX_XGJS', '修改帐号', '修改帐号', '系统管理-帐号管理-修改帐号', '/inst/instUser');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9230, 9200, 'operation', 'XTGL_ZHQX_ZTZH', '暂停帐号', '暂停帐号', '系统管理-帐号管理-暂停帐号', '/inst/instUser');
insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9240, 9200, 'operation', 'XTGL_ZHQX_XGMM', '修改密码', '修改密码', '系统管理-帐号管理-修改密码', '/inst/instUser');

insert into t_inst_auth (i_inst_auth, p_i_inst_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9300, 9000, 'menu', 'XTGL_XTRZ_MENU', '系统日志', '系统日志', '系统管理-系统日志', '/inst/operLog');



