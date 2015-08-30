#商户管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1000, null, 'menu', 'SHGL_MENU_ALL', '商户管理', '商户管理', '商户管理', '#');

#商户管理-商户资料管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1100, 1000, 'menu', 'SHGL_SHZLGL_MENU', '商户资料管理', '商户资料管理', '商户资料管理', '/backstage/merchmanager/merchManager');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1110, 1000, 'operation', 'SHGL_SHZLGL_CX', '查询', '查询', '商户资料管理-查询', '/backstage/merchmanager/merchManager!list');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1120, 1000, 'operation', 'SHGL_SHZLGL_JY', '禁用', '禁用', '商户资料管理-禁用', '/backstage/merchmanager/merchUser!disablemerchUser');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1130, 1000, 'operation', 'SHGL_SHZLGL_XZ', '新增', '新增', '商户资料管理-新增', '/backstage/merchmanager/merchUser!saveMerchUser');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1140, 1000, 'operation', 'SHGL_SHZLGL_XG', '修改', '修改', '商户资料管理-修改', '/backstage/merchmanager/merchUser!updateMerchUser');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1150, 1000, 'operation', 'SHGL_SHZLGL_XCDC', '现场调查', '现场调查', '商户资料管理-现场调查', '/backstage/merchmanager/merchManager!savefieldSurvey');

#商户管理-信用报告管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1200, 1000, 'menu', 'SHGL_XYBGGL_MENU', '信用报告管理', '信用报告管理', '信用报告管理', '/backstage/merchcreditreport/backMerchCredit');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1210, 1000, 'operation', 'SHGL_XYBGGL_CX', '查询', '查询', '信用报告管理-查询', '/backstage/merchcreditreport/backMerchCredit!list');

#商户管理-行用报告申诉
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1300, 1000, 'menu', 'SHGL_XYBGSS_MENU', '信用报告申诉', '信用报告申诉', '信用报告申诉', '/backstage/backMerchAppeal');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1310, 1000, 'operation', 'SHGL_XYBGSS_CX', '查询', '查询', '信用报告申诉-查询', '/backstage/backMerchAppeal!list');


#商户管理-被冻结商户
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1400, 1000, 'menu', 'SHGL_BDJSH_MENU', '被冻结商户', '被冻结商户', '被冻结商户', '/backstage/merchmanager/frozenMerch');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (1410, 1000, 'operation', 'SHGL_BDJSH_CX', '查询', '查询', '被冻结商户-查询', '/backstage/merchmanager/frozenMerch!list');


#机构管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2000, null, 'menu', 'JGGL_MENU_ALL', '机构管理', '机构管理', '机构管理', '#');

#机构管理-机构申请
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2100, 2000, 'menu', 'JGGL_JGSQ_MENU', '机构申请', '机构申请', '机构申请', '/backstage/instmanager/instApplyRequest');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2110, 2000, 'operation', 'JGGL_JGSQ_TG', '通过', '通过', '机构管理-通过', '/backstage/instmanager/instApplyRequest!check');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2120, 2000, 'operation', 'JGGL_JGSQ_TH', '退回', '退回', '机构管理-退回', '/backstage/instmanager/instApplyRequest!cancel');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2130, 2000, 'operation', 'JGGL_JGSQ_CX', '查询', '查询', '机构管理-查看', '/backstage/instmanager/instApplyRequest!detail');

#机构管理-机构管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2200, 2000, 'menu', 'JGGL_JGGL_MENU', '机构管理', '机构管理', '机构管理', '/backstage/instmanager/instManager');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2210, 2000, 'operation', 'JGGL_JGGL_XZ', '新增', '新增', '机构管理-新增', '/backstage/instmanager/instManager!saveInstInfo');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2220, 2000, 'operation', 'JGGL_JGGL_XG', '修改', '修改', '机构管理-修改', '/backstage/instmanager/instManager!updateInstInfo');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2230, 2000, 'operation', 'JGGL_JGGL_JY', '禁用', '禁用', '机构管理-禁用', '/backstage/instmanager/instManager!disableInst');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2240, 2000, 'operation', 'JGGL_JGGL_CX', '查询', '查询', '机构管理-查询', '/backstage/instmanager/instManager!list');

#机构管理-机构操作员
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2300, 2000, 'menu', 'JGGL_JGCZY_MENU', '机构操作员', '机构操作员', '机构操作员', '/backstage/instmanager/backstageInstUser!instManagerIndexUser');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2310, 2000, 'operation', 'JGGL_JGCZY_XZ', '新增', '新增', '机构操作员-新增', '/backstage/instmanager/backstageInstUser!instUserAdd');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2320, 2000, 'operation', 'JGGL_JGCZY_XG', '修改', '修改', '机构操作员-修改', '/backstage/instmanager/backstageInstUser!instUserUpdate');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2330, 2000, 'operation', 'JGGL_JGCZY_JY', '禁用', '禁用', '机构操作员-禁用', '/backstage/instmanager/backstageInstUser!disableAcct');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2340, 2000, 'operation', 'JGGL_JGCZY_CX', '查询', '查询', '机构操作员-查询', '/backstage/instmanager/backstageInstUser!list');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2350, 2000, 'operation', 'JGGL_JGCZY_SC', '删除', '删除', '机构操作员-删除', '/backstage/instmanager/backstageInstUser!deleteInstUser');


#机构管理-机构角色
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2400, 2000, 'menu', 'JGGL_JGJS_MENU', '机构角色', '机构角色', '机构角色', '/backstage/instmanager/instRole!instRoleIndex');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2410, 2000, 'operation', 'JGGL_JGJS_XZ', '新增', '新增', '机构角色-新增', '/backstage/instmanager/instRole!instRoleAdd');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2420, 2000, 'operation', 'JGGL_JGJS_XG', '修改', '修改', '机构角色-修改', '/backstage/instmanager/instRole!instRoleUpdate');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2430, 2000, 'operation', 'JGGL_JGJS_JY', '禁用', '禁用', '机构角色-禁用', '/backstage/instmanager/instRole!cancelRole');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2440, 2000, 'operation', 'JGGL_JGJS_CX', '查询', '查询', '机构角色-查询', '/backstage/instmanager/instRole!list');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (2450, 2000, 'operation', 'JGGL_JGJS_SC', '删除', '删除', '机构角色-删除', '/backstage/instmanager/instRole!deleteInstRole');


#贷款产品
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3000, null, 'menu', 'HDCP_MENU_ALL', '贷款产品', '贷款产品', '贷款产品', '/backstage/product/instProduce');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3110, 3000, 'operation', 'HDCP_XZ', '新增', '新增', '贷款产品-新增', '/backstage/product/instProduce!saveProduct');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3120, 3000, 'operation', 'HDCP_SC', '删除', '删除', '贷款产品-删除', '/backstage/product/instProduce!deleteProduct');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3130, 3000, 'operation', 'HDCP_XG', '修改', '修改', '贷款产品-修改', '/backstage/product/instProduce!updateProduct');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3150, 3000, 'operation', 'HDCP_SJ', '上架', '上架', '贷款产品-上架', '/backstage/product/instProduce!upStore');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (3160, 3000, 'operation', 'HDCP_XJ', '下架', '下架', '贷款产品-下架', '/backstage/product/instProduce!downStore');

#放款对账
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4000, null, 'menu', 'FKDZ_MENU_ALL', '放款对账', '放款对账', '放款对账', '/backstage/creditCheck');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4110, 4000, 'operation', 'FKDZ_XZ', '下载', '下载', '放款对账-下载', '/backstage/creditCheck!download');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4120, 4000, 'operation', 'FKDZ_TH', '退回', '退回', '放款对账-退回', '/backstage/creditCheck!cancel');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (4130, 4000, 'operation', 'FKDZ_YDD', '已到达', '已到达', '放款对账-已到达', '/backstage/creditCheck!audit');

#还款对账
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5000, null, 'menu', 'HKDZ_MENU_ALL', '还款对账', '还款对账', '还款对账', '/backstage/refundCheck');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5110, 5000, 'operation', 'HKDZ_XZ', '下载', '下载', '还款对账-下载', '/backstage/refundCheck!download');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5120, 5000, 'operation', 'HKDZ_TH', '退回', '退回', '还款对账-退回', '/backstage/refundCheck!cancel');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (5130, 5000, 'operation', 'HKDZ_YDD', '已到达', '已到达', '还款对账-已到达', '/backstage/refundCheck!audit');


#冻结管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6000, null, 'menu', 'DJGL_MENU_ALL', '冻结管理', '冻结管理', '冻结管理', '/backstage/freezeCheck');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6110, 6000, 'operation', 'DJGL_DJ', '冻结', '冻结', '冻结管理-冻结', '/backstage/freezeCheck!proc');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6120, 6000, 'operation', 'DJGL_JJ', '拒绝', '拒绝', '冻结管理-拒绝', '/backstage/freezeCheck!refuse');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6130, 6000, 'operation', 'DJGL_SHYHK', '商户已还款', '商户已还款', '冻结管理-商户已还款', '/backstage/freezeCheck!unFreezenPayed');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6140, 6000, 'operation', 'DJGL_SHWHK', '商户未还款', '商户未还款', '冻结管理-商户未还款', '/backstage/freezeCheck!unFreezenUnPayed');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (6150, 6000, 'operation', 'DJGL_XZ', '下载', '下载', '冻结管理-下载', '/backstage/freezeCheck!download');

#借贷管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7000, null, 'menu', 'JDDD_MENU_ALL', '借贷订单', '借贷订单', '借贷订单', '/backstage/loanOrdMgr');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7110, 7000, 'operation', 'JDDD_YB', '隐蔽', '隐蔽', '借贷订单-隐蔽', '/backstage/loanOrdMgr!shield');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (7120, 7000, 'operation', 'JDDD_XQ', '详情', '详情', '借贷订单-详情', '/backstage/loanOrdMgr!viewLoanOrd');


#放款记录
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8000, null, 'menu', 'HKJL_MENU_ALL', '放款记录', '放款记录', '放款记录', '/backstage/creditMgr');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (8110, 8000, 'operation', 'HKJL_XQ', '详情', '详情', '放款记录-详情', '/backstage/creditMgr!viewFundFlowDetail');

#还款记录
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9000, null, 'menu', 'FKJL_MENU_ALL', '还款记录', '还款记录', '还款记录', '/backstage/refundMgr');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (9110, 9000, 'operation', 'FKJL_XQ', '详情', '详情', '还款记录-详情', '/backstage/refundMgr!viewFundFlowDetail');


#公告
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (10000, null, 'menu', 'GGGL_MENU_ALL', '公告管理', '公告管理', '公告管理', '/backstage/notifymanager/notifyManager');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (10110, 10000, 'operation', 'GGGL_XZ', '新增', '新增', '公告管理-新增', '/backstage/notifymanager/notifyManager!saveNotify');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (10120, 10000, 'operation', 'GGGL_XG', '修改', '修改', '公告管理-修改', '/backstage/notifymanager/notifyManager!updateNotify');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (10130, 10000, 'operation', 'GGGL_SC', '删除', '删除', '公告管理-删除', '/backstage/notifymanager/notifyManager!deleteNotify');

#系统参数设置
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (11000, null, 'menu', 'XTCSSZ_MENU_ALL', '系统参数设置', '系统参数设置', '系统参数设置', '#');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (12000, 11000, 'menu', 'XTCSSZ_JYJL', '交易记录', '交易记录', '交易记录', '/backstage/sysparam/sysParam!transLog');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (13000, 11000, 'menu', 'XTCSSZ_SHJBXX', '商户基本信息', '商户基本信息', '商户基本信息', '/backstage/sysparam/sysParam!merchBaseInfo');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (14000, 11000, 'menu', 'XTCSSZ_AZJL', '安装记录', '安装记录', '安装记录', '/backstage/sysparam/sysParam!installLog');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (15000, 11000, 'menu', 'XTCSSZ_XJJL', '巡检记录', '巡检记录', '巡检记录', '/backstage/sysparam/sysParam!serviceLog');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (16000, 11000, 'menu', 'XTCSSZ_ZTMY', '转跳密钥', '转跳密钥', '转跳密钥', '/backstage/sysparam/sysParam!jumpTicket');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (17000, 11000, 'menu', 'XTCSSZ_JZGMMJY', '金掌柜密码校验', '金掌柜密码校验', '金掌柜密码校验', '/backstage/sysparam/sysParam!pwdCheck');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (18000, 11000, 'menu', 'XTCSSZ_DXWG', '短信网关', '短信网关', '短信网关', '/backstage/sysparam/sysParam!smsEdit');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (19000, 11000, 'menu', 'XTCSSZ_FXCS', '风险参数', '风险参数', '风险参数', '/backstage/sysparam/sysParam!riskControl');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (20000, 11000, 'menu', 'XTCSSZ_TPSCSZ', '图片上传设置', '图片上传设置', '图片上传设置', '/backstage/sysparam/sysParam!imageUrl');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (21000, 11000, 'menu', 'XTCSSZ_SXRQSZ', '失效日期设置', '失效日期设置', '失效日期设置', '/backstage/sysparam/sysParam!expireDate');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (21100, 11000, 'menu', 'XTCSSZ_JZGZFWG', '金掌柜支付网关', '金掌柜支付网关', '金掌柜支付网关', '/backstage/sysparam/sysParam!payUrl');

#系统维护
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (22000, null, 'menu', 'XTWH_MENU_ALL', '系统维护', '系统维护', '系统维护', '/backstage/transmanage/transmanage');


#系统管理
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23000, null, 'menu', 'XTGL_MENU_ALL', '系统管理', '系统管理', '系统管理', '#');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23100, 23000, 'menu', 'XTGL_WDZHXX_MENU', '我的帐号信息', '我的帐号信息', '我的帐号信息', '/backstage/backuser/backUserManager!userInfo');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23110, 23000, 'operation', 'WDZHXX_BC', '保存', '保存', '保存', '/backstage/backuser/backUserManager!updateUserInfo');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23200, 23000, 'menu', 'XTGL_CZZHGL_MENU', '操作账号管理', '操作账号管理', '操作账号管理', '/backstage/backuser/backUserManager');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23210, 23000, 'operation', 'CZZHGL_XZ', '新增', '新增', '新增', '/backstage/backuser/backUserManager!saveBackstageUser');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23220, 23000, 'operation', 'CZZHGL_XG', '修改', '修改', '修改', '/backstage/backuser/backUserManager!updateBackstageUser');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23230, 23000, 'operation', 'CZZHGL_TY', '停用', '停用', '停用', '/backstage/backuser/backUserManager!disableBackstageUser');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23300, 23000, 'menu', 'XTGL_JSGL_MENU', '角色管理', '角色管理', '角色管理', '/backstage/backstagerole/backstageRole');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23310, 23000, 'operation', 'JSGL_XZ', '新增', '新增', '新增', '/backstage/backstagerole!saveRole');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23320, 23000, 'operation', 'JSGL_XG', '修改', '修改', '修改', '/backstage/backstagerole!updateRole');
insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23330, 23000, 'operation', 'JSGL_TY', '停用', '停用', '停用', '/backstage/backstagerole!deleteRole');

insert into t_bs_auth (i_bs_auth, p_i_bs_auth, auth_type, auth_code, auth_name, menu_name, memo, url) values (23400, 23000, 'menu', 'XTGL_CZRZ_MENU', '操作日志', '操作日志', '操作日志', '/backstage/backstagelog/backstageLog');




