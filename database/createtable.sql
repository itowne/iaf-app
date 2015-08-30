drop schema `iaf`;
CREATE SCHEMA `iaf` ;
use iaf;


CREATE TABLE hibernate_sequences (
  sequence_name varchar(200) NOT NULL,
  sequence_next_hi_value int(11) DEFAULT NULL,
  PRIMARY KEY (sequence_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_province (
  prov_code varchar(6) NOT NULL,
  close_flag tinyint(1) NOT NULL,
  `name` varchar(30) NOT NULL,
  pre_prov_code varchar(6) NOT NULL,
  PRIMARY KEY (prov_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_rpt_file (
  i_report bigint(20) DEFAULT NULL,
  i_rpt_file bigint(20) NOT NULL,
  file_path varchar(200) DEFAULT NULL,
  gen_time datetime DEFAULT NULL,
  position int(11) DEFAULT NULL,
  file_type int(11) DEFAULT NULL,
  PRIMARY KEY (i_rpt_file),
  KEY FKE9093190D3E5995E (i_report)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rpt_temp (
  ireportTemplate bigint(20) NOT NULL,
  file_name varchar(200) DEFAULT NULL,
  gen_time datetime NOT NULL,
  position int(11) DEFAULT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (ireportTemplate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_apply_req (
  i_apply_req bigint(20) NOT NULL,
  amount decimal(19,2) DEFAULT NULL,
  apply_type int(11) DEFAULT NULL,
  batch_id varchar(16) NOT NULL,
  gen_time datetime DEFAULT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_loan_ord bigint(20) DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  inst_name varchar(100) DEFAULT NULL,
  loan_ord_id varchar(12) DEFAULT NULL,
  merch_name varchar(100) DEFAULT NULL,
  periods varchar(20) DEFAULT NULL,
  reason varchar(100) DEFAULT NULL,
  result varchar(200) DEFAULT NULL,
  status int(11) DEFAULT NULL,
  upd_time datetime DEFAULT NULL,
  version int(11) DEFAULT NULL,
  PRIMARY KEY (i_apply_req)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_bs_role (
  i_bs_role bigint(20) NOT NULL,
  gen_time datetime DEFAULT NULL,
  role_desc varchar(200) DEFAULT NULL,
  role_name varchar(50) DEFAULT NULL,
  status int(11) DEFAULT NULL,
  upd_time datetime DEFAULT NULL,
  PRIMARY KEY (i_bs_role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_bs_user (
  i_bs_user bigint(20) NOT NULL,
  address varchar(200) DEFAULT NULL,
  email varchar(200) DEFAULT NULL,
  gen_time datetime DEFAULT NULL,
  login_name varchar(50) DEFAULT NULL,
  mobile varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  qq_num varchar(50) DEFAULT NULL,
  sex_type int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  upd_time datetime DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (i_bs_user),
  UNIQUE KEY login_name (login_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_bs_user_role (
  i_bs_user bigint(20) NOT NULL,
  i_bs_role bigint(20) NOT NULL,
  KEY FK6B594FE718BB97DC (i_bs_role),
  KEY FK6B594FE718BE6E86 (i_bs_user),
  CONSTRAINT FK6B594FE718BE6E86 FOREIGN KEY (i_bs_user) REFERENCES t_bs_user (i_bs_user),
  CONSTRAINT FK6B594FE718BB97DC FOREIGN KEY (i_bs_role) REFERENCES t_bs_role (i_bs_role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_debit_bid (
  i_debit_bid bigint(20) NOT NULL,
  accept_total bigint(20) DEFAULT NULL,
  bid_stat int(11) NOT NULL,
  debit_bid_id varchar(8) NOT NULL,
  exp_date datetime NOT NULL,
  gen_time datetime NOT NULL,
  i_merch_user bigint(20) NOT NULL,
  i_merch bigint(20) NOT NULL,
  merch_name varchar(50) DEFAULT NULL,
  merch_type int(11) NOT NULL,
  purpose varchar(200) NOT NULL,
  quota decimal(19,2) NOT NULL,
  refund_type int(11) DEFAULT NULL,
  region varchar(6) NOT NULL,
  term int(11) NOT NULL,
  upd_time datetime NOT NULL,
  version varchar(255) DEFAULT NULL,
  year_rate decimal(19,2) NOT NULL,
  PRIMARY KEY (i_debit_bid),
  KEY idx_debit_bid1 (term),
  KEY idx_debit_bid2 (year_rate),
  KEY FK_debit_bid1 (region),
  CONSTRAINT FK_debit_bid1 FOREIGN KEY (region) REFERENCES t_province (prov_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_field_survy (
i_field_survy  bigint(20) NOT NULL ,
i_merch  bigint(20) NULL DEFAULT NULL ,
inspect_busi  int(11) NULL DEFAULT NULL ,
inspect_know  int(11) NULL DEFAULT NULL ,
inspect_knowdesc  varchar(100) NULL DEFAULT NULL ,
inspect_lic  int(11) NULL DEFAULT NULL ,
inspect_licdesc  varchar(100) NULL DEFAULT NULL ,
inspect_loan  int(11) NULL DEFAULT NULL ,
inspect_onsite  int(11) NULL DEFAULT NULL ,
inspect_onsitedesc  varchar(100) NULL DEFAULT NULL ,
inspect_opin  int(11) NULL DEFAULT NULL ,
inspect_range  int(11) NULL DEFAULT NULL ,
inspect_rangedesc  varchar(100) NULL DEFAULT NULL ,
inspect_report  int(11) NULL DEFAULT NULL ,
inspect_reportdesc  varchar(100) NULL DEFAULT NULL ,
manage_area  int(11) NULL DEFAULT NULL ,
manage_branch_count  varchar(20) NULL DEFAULT NULL ,
manage_branch_range  varchar(30) NULL DEFAULT NULL ,
manage_bussiness_hours  varchar(20) NULL DEFAULT NULL ,
manage_district  int(11) NULL DEFAULT NULL ,
manage_field_nature  int(11) NULL DEFAULT NULL ,
manage_field_square  varchar(50) NULL DEFAULT NULL ,
manage_range_avocation  varchar(50) NULL DEFAULT NULL ,
manage_range_major  varchar(50) NULL DEFAULT NULL ,
manage_scale  varchar(20) NULL DEFAULT NULL ,
PRIMARY KEY (i_field_survy)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_file (
  i_file bigint(20) NOT NULL,
  ext_name varchar(255) DEFAULT NULL,
  file_type varchar(255) DEFAULT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_inst_user bigint(20) DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  i_merch_user bigint(20) DEFAULT NULL,
  meta_type varchar(255) DEFAULT NULL,
  path varchar(255) DEFAULT NULL,
  sha1 varchar(255) DEFAULT NULL,
  upload_name varchar(255) DEFAULT NULL,
  uuid_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (i_file),
  UNIQUE KEY uuid_name (uuid_name),
  KEY idx_file1 (uuid_name),
  KEY idx_file2 (i_merch),
  KEY idx_file3 (i_merch_user),
  KEY idx_file4 (i_inst_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_fund_flow (
  i_fund_flow bigint(20) NOT NULL,
  amount decimal(19,2) NOT NULL,
  capital decimal(19,2) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_loan_ord bigint(20) NOT NULL,
  i_loanord_plan bigint(20) DEFAULT NULL,
  i_merch bigint(20) NOT NULL,
  inst_id varchar(20) DEFAULT NULL,
  inst_name varchar(100) DEFAULT NULL,
  loan_ord_id varchar(12) NOT NULL,
  memo varchar(50) DEFAULT NULL,
  merch_id varchar(20) DEFAULT NULL,
  merch_name varchar(100) DEFAULT NULL,
  oth_sys_no varchar(32) DEFAULT NULL,
  period int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  trace_no varchar(16) NOT NULL,
  trans_type int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  upd_time datetime NOT NULL,
  version int(11) DEFAULT NULL,
  PRIMARY KEY (i_fund_flow),
  UNIQUE KEY trace_no (trace_no),
  KEY idx_fund_flow1 (i_fund_flow),
  KEY idx_fund_flow2 (`type`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_hc_inspect_log (
  i_hc_inspect_log bigint(20) NOT NULL,
  hc_merch_no varchar(45) DEFAULT NULL,
  hc_term_No varchar(45) DEFAULT NULL,
  i_hc_trans bigint(20) DEFAULT NULL,
  inspect_case varchar(45) DEFAULT NULL,
  inspect_date datetime DEFAULT NULL,
  inspect_stat varchar(45) DEFAULT NULL,
  inspector varchar(45) DEFAULT NULL,
  memo varchar(255) DEFAULT NULL,
  serial_no bigint(20) DEFAULT NULL,
  PRIMARY KEY (i_hc_inspect_log),
  KEY idx_hc_inspect_log1 (hc_merch_no,hc_term_No)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_hc_install_log (
  i_hc_install_log bigint(20) NOT NULL,
  hc_merch_no varchar(45) DEFAULT NULL,
  hc_term_no varchar(45) DEFAULT NULL,
  i_hc_trans bigint(20) DEFAULT NULL,
  install_addr varchar(255) DEFAULT NULL,
  install_date datetime DEFAULT NULL,
  install_oper varchar(45) DEFAULT NULL,
  serial_no bigint(20) DEFAULT NULL,
  term_stat varchar(45) DEFAULT NULL,
  uninstall_date datetime DEFAULT NULL,
  uninstall_oper varchar(45) DEFAULT NULL,
  uninstall_reason varchar(45) DEFAULT NULL,
  PRIMARY KEY (i_hc_install_log),
  KEY idx_hc_install_log1 (hc_merch_no),
  KEY idx_hc_install_log4 (term_stat)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_hc_merch_base_info (
  i_hc_merch_base_info bigint(20) NOT NULL,
  businlic varchar(255) DEFAULT NULL,
  contract varchar(255) DEFAULT NULL,
  contract_tel varchar(255) DEFAULT NULL,
  hc_merch_no varchar(255) DEFAULT NULL,
  legal_per_cerd_id varchar(255) DEFAULT NULL,
  legal_per_name varchar(255) DEFAULT NULL,
  merch_login_name varchar(255) DEFAULT NULL,
  merch_name varchar(255) DEFAULT NULL,
  merch_status varchar(255) DEFAULT NULL,
  merch_type varchar(255) DEFAULT NULL,
  nature varchar(255) DEFAULT NULL,
  open_time datetime DEFAULT NULL,
  pos_addr varchar(255) DEFAULT NULL,
  reg_addr varchar(255) DEFAULT NULL,
  reg_cap varchar(255) DEFAULT NULL,
  reg_time datetime DEFAULT NULL,
  risk_class varchar(255) DEFAULT NULL,
  serial_no bigint(20) DEFAULT NULL,
  settle_acct_name varchar(255) DEFAULT NULL,
  settle_acct_no varchar(255) DEFAULT NULL,
  settle_bank varchar(255) DEFAULT NULL,
  tax_reg_no varchar(255) DEFAULT NULL,
  original_time varchar(20) DEFAULT NULL,
  PRIMARY KEY (i_hc_merch_base_info),
  KEY idx_hc_merch_base_info1 (hc_merch_no),
  KEY idx_hc_merch_base_info4 (merch_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_hc_trans (
  i_hc_trans bigint(20) NOT NULL,
  export_time datetime DEFAULT NULL,
  file_name varchar(50) NOT NULL,
  gen_time datetime NOT NULL,
  insert_num int(11) DEFAULT NULL,
  realRecNum int(11) DEFAULT NULL,
  rec_num int(11) DEFAULT NULL,
  sha1 varchar(28) NOT NULL,
  size bigint(20) NOT NULL,
  `status` varchar(10) NOT NULL,
  trans_type varchar(255) NOT NULL,
  upd_time datetime DEFAULT NULL,
  memo varchar(100),
  PRIMARY KEY (i_hc_trans)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_hc_trans_log (
  i_hc_trans_log bigint(20) NOT NULL,
  abroad_card_flag varchar(1) DEFAULT NULL,
  amt decimal(19,2) DEFAULT NULL,
  charge decimal(19,2) DEFAULT NULL,
  golden_express_flag varchar(1) DEFAULT NULL,
  hicard_no bigint(20) DEFAULT NULL,
  i_hc_trans bigint(20) NOT NULL,
  merch_no varchar(15) DEFAULT NULL,
  order_no varchar(20) DEFAULT NULL,
  settle_date datetime DEFAULT NULL,
  trans_date datetime DEFAULT NULL,
  trans_time varchar(255) DEFAULT NULL,
  trans_type varchar(1) DEFAULT NULL,
  PRIMARY KEY (i_hc_trans_log),
  UNIQUE KEY order_no (order_no),
  KEY idx_hc_trans_log1 (settle_date),
  KEY idx_hc_trans_log2 (trans_date),
  KEY idx_hc_trans_log3 (merch_no),
  KEY idx_hc_trans_log4 (trans_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst (
  i_inst bigint(20) NOT NULL,
  busi_license varchar(255) DEFAULT NULL,
  busi_region varchar(255) DEFAULT NULL,
  catchword varchar(255) DEFAULT NULL,
  contact varchar(255) DEFAULT NULL,
  contact_phone varchar(255) DEFAULT NULL,
  english_name varchar(255) DEFAULT NULL,
  gen_time datetime NOT NULL,
  inst_id varchar(255) DEFAULT NULL,
  inst_name varchar(255) NOT NULL,
  inst_nature varchar(255) DEFAULT NULL,
  inst_stat int(11) NOT NULL,
  introduce varchar(500) DEFAULT NULL,
  loan_permit varchar(255) NOT NULL,
  logo_i_file bigint(20) DEFAULT NULL,
  official_website varchar(255) DEFAULT NULL,
  open_time datetime DEFAULT NULL,
  pdt_count bigint(20) DEFAULT NULL,
  people_count varchar(255) DEFAULT NULL,
  reg_addr varchar(255) DEFAULT NULL,
  reg_capital decimal(19,2) NOT NULL,
  reg_time datetime NOT NULL,
  short_phone varchar(255) DEFAULT NULL,
  succ_rate decimal(19,2) DEFAULT NULL,
  tax_reg_no varchar(255) DEFAULT NULL,
  total_capital decimal(19,2) NOT NULL,
  qq_uid varchar(20) NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_inst),
  KEY idx_inst1 (inst_name),
  KEY idx_inst2 (inst_nature),
  KEY idx_inst3 (reg_capital)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE T_INST_APPLY_REQ(
  i_inst_apply_req bigint(20) NOT NULL,
  city varchar(32) DEFAULT NULL,
  city_code varchar(6) DEFAULT NULL,
  cont_name varchar(50) DEFAULT NULL,
  gen_time datetime DEFAULT NULL,
  gender int(11) DEFAULT NULL,
  inst_name varchar(50) DEFAULT NULL,
  mobile_phone varchar(12) DEFAULT NULL,
  province varchar(32) DEFAULT NULL,
  province_code varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  upt_time datetime DEFAULT NULL,
  PRIMARY KEY (i_inst_apply_req)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_auth (
  i_inst_auth bigint(20) NOT NULL,
  auth_code varchar(255) NOT NULL,
  auth_name varchar(255) NOT NULL,
  auth_type varchar(255) NOT NULL,
  memo varchar(255) NOT NULL,
  menu_name varchar(255) DEFAULT NULL,
  p_i_inst_auth bigint(20) DEFAULT NULL,
  url varchar(255) NOT NULL,
  PRIMARY KEY (i_inst_auth)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_busi_info (
  i_inst_busi_info bigint(20) NOT NULL,
  accept_region varchar(255) DEFAULT NULL,
  bank varchar(255) DEFAULT NULL,
  bank_code varchar(20) NOT NULL,
  i_inst bigint(20) NOT NULL,
  loan_account_no varchar(255) DEFAULT NULL,
  loan_name varchar(255) DEFAULT NULL,
  repayment_bank varchar(255) DEFAULT NULL,
  repayment_name varchar(255) DEFAULT NULL,
  repayment_no varchar(255) DEFAULT NULL,
  PRIMARY KEY (i_inst_busi_info),
  KEY idx_inst_busi_info1 (accept_region)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_coll_merch (
  i_inst_coll_merch bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_inst_usr bigint(20) NOT NULL,
  i_merch bigint(20) NOT NULL,
  PRIMARY KEY (i_inst_coll_merch),
  KEY idx_inst_coll_merch1 (i_inst),
  KEY idx_inst_coll_merch2 (i_merch),
  KEY idx_inst_coll_merch3 (i_inst_usr)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_inst_coll_ord(
  i_inst_coll_ord bigint(20) NOT NULL,
  gen_time datetime,
  i_inst bigint(20) DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  i_debit_bid bigint(20) DEFAULT NULL,
  PRIMARY KEY(i_inst_coll_ord),
  KEY idx_inst_coll_ord1 (i_inst),
  KEY idx_inst_coll_ord2 (i_merch)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_inst_legal_pers (
  i_inst_legal_pers bigint(20) NOT NULL,
  age int(11) DEFAULT NULL,
  cerd_no varchar(255) DEFAULT NULL,
  education int(11) DEFAULT NULL,
  family_addr varchar(255) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  legal_pers_name varchar(255) DEFAULT NULL,
  marital_status int(11) DEFAULT NULL,
  sex int(11) DEFAULT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_inst_legal_pers)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_notice (
  i_inst_notice bigint(20) NOT NULL,
  content varchar(512) DEFAULT NULL,
  gen_time datetime NOT NULL,
  `status` int(11) NOT NULL,
  title varchar(50) NOT NULL,
  upd_time datetime DEFAULT NULL,
  PRIMARY KEY (i_inst_notice)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_role (
  i_inst_role bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  memo varchar(255) DEFAULT NULL,
  role_name varchar(255) NOT NULL,
  role_stat int(11) NOT NULL,
  PRIMARY KEY (i_inst_role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_role_auth (
  i_inst_role bigint(20) NOT NULL,
  i_inst_auth bigint(20) NOT NULL,
  PRIMARY KEY (i_inst_role,i_inst_auth),
  KEY FK_inst_role_auth1 (i_inst_auth),
  KEY FK_inst_role_auth2 (i_inst_role),
  CONSTRAINT FK_inst_role_auth1 FOREIGN KEY (i_inst_auth) REFERENCES t_inst_auth (i_inst_auth),
  CONSTRAINT FK_inst_role_auth2 FOREIGN KEY (i_inst_role) REFERENCES t_inst_role (i_inst_role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_user (
  i_inst_user bigint(20) NOT NULL,
  mail varchar(255) DEFAULT NULL,
  qqnum varchar(255) DEFAULT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  last_login_time datetime NOT NULL,
  login_name varchar(255) NOT NULL,
  passwd varchar(255) NOT NULL,
  phone varchar(255) DEFAULT NULL,
  position varchar(255) NOT NULL,
  upd_time datetime NOT NULL,
  user_name varchar(255) NOT NULL,
  usr_stat int(11) NOT NULL,
  PRIMARY KEY (i_inst_user),
  UNIQUE KEY login_name (login_name),
  KEY idx_merch_user1 (login_name),
  KEY idx_merch_user2 (user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_inst_user_role (
  i_inst_user bigint(20) NOT NULL,
  i_inst_role bigint(20) NOT NULL,
  PRIMARY KEY (i_inst_user,i_inst_role),
  KEY FK_inst_user_role1 (i_inst_role),
  KEY FK_inst_user_role2 (i_inst_user),
  CONSTRAINT FK_inst_user_role1 FOREIGN KEY (i_inst_user) REFERENCES t_inst_user (i_inst_user),
  CONSTRAINT FK_inst_user_role2 FOREIGN KEY (i_inst_role) REFERENCES t_inst_role (i_inst_role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_ord (
  i_loan_ord bigint(20) NOT NULL,
  accept_date datetime DEFAULT NULL,
  check_date datetime DEFAULT NULL,
  credit_date datetime DEFAULT NULL,
  curtPayment decimal(19,2) DEFAULT NULL,
  expiry_date datetime NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_loan_pdt bigint(20) DEFAULT NULL,
  i_merch bigint(20) NOT NULL,
  inst_name varchar(50) DEFAULT NULL,
  last_refund_date datetime DEFAULT NULL,
  loan_ord_id varchar(12) DEFAULT NULL,
  loan_pdt_id varchar(12) DEFAULT NULL,
  merch_id varchar(20) DEFAULT NULL,
  merch_name varchar(50) DEFAULT NULL,
  merch_type int(11) DEFAULT NULL,
  ord_date datetime NOT NULL,
  ord_stat int(11) NOT NULL,
  organ_id varchar(20) DEFAULT NULL,
  pdt_name varchar(50) DEFAULT NULL,
  pdt_type int(11) NOT NULL,
  purpose varchar(200) DEFAULT NULL,
  quota decimal(19,2) NOT NULL,
  rate decimal(19,2) NOT NULL,
  recive_period int(11) DEFAULT NULL,
  remain_payment decimal(19,2) DEFAULT NULL,
  remain_period int(11) DEFAULT NULL,
  repayment decimal(19,2) DEFAULT NULL,
  shield int(11) NOT NULL,
  term int(11) NOT NULL,
  upd_time datetime NOT NULL,
  version bigint(20) DEFAULT NULL,
  PRIMARY KEY (i_loan_ord),
  UNIQUE KEY loan_ord_id (loan_ord_id),
  KEY idx_loan_ord1 (i_merch),
  KEY idx_loan_ord2 (i_inst),
  KEY idx_loan_ord3 (i_merch,ord_stat),
  KEY idx_loan_ord4 (i_inst,ord_stat),
  KEY idx_loan_ord5 (ord_stat),
  KEY idx_loan_ord6 (i_merch,ord_date),
  KEY idx_loan_ord7 (i_inst,ord_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_pdt (
  i_loan_pdt bigint(20) NOT NULL,
  rereq varchar(200) DEFAULT NULL,
  req_cond varchar(200) DEFAULT NULL,
  credit_term int(11) NOT NULL,
  delete_flag int(11) DEFAULT NULL,
  feature varchar(200) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_loan_pdt_his bigint(20) NOT NULL,
  introduce varchar(200) DEFAULT NULL,
  load_pdt_id varchar(8) DEFAULT NULL,
  logo_i_file bigint(20) DEFAULT NULL,
  max_quota decimal(19,2) NOT NULL,
  max_term int(11) NOT NULL,
  min_quota decimal(19,2) NOT NULL,
  min_term int(11) NOT NULL,
  pdt_name varchar(255) NOT NULL,
  pdt_stat int(11) NOT NULL,
  rate decimal(19,2) NOT NULL,
  region varchar(6) NOT NULL,
  area varchar(50) NOT NULL,
  province_code varchar(20) NOT NULL,
  repayment int(11) DEFAULT NULL,
  req_total bigint(20) DEFAULT NULL,
  upd_time datetime NOT NULL,
  version bigint(20) DEFAULT NULL,
  PRIMARY KEY (i_loan_pdt),
  KEY idx_load_pdt1 (min_quota),
  KEY idx_load_pdt2 (max_quota),
  KEY idx_load_pdt3 (min_term),
  KEY idx_load_pdt4 (max_term),
  KEY idx_load_pdt5 (upd_time),
  KEY FK_load_pdt1 (region),
  CONSTRAINT FK_load_pdt1 FOREIGN KEY (region) REFERENCES t_province (prov_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_pdt_his (
  i_loan_pdt_his bigint(20) NOT NULL,
  rereq varchar(255) NOT NULL,
  req_cond varchar(255) NOT NULL,
  credit_term int(11) NOT NULL,
  feature varchar(255) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_loan_pdt bigint(20) NOT NULL,
  introduce varchar(255) DEFAULT NULL,
  loan_pdt_id varchar(255) DEFAULT NULL,
  max_quota decimal(19,2) NOT NULL,
  max_term int(11) NOT NULL,
  min_quota decimal(19,2) NOT NULL,
  min_term int(11) NOT NULL,
  pdt_name varchar(255) NOT NULL,
  pdt_stat int(11) NOT NULL,
  rate decimal(19,2) NOT NULL,
  region varchar(255) NOT NULL,
  area varchar(50) NOT NULL,
  province_code varchar(20) NOT NULL,
  repayment int(11) DEFAULT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_loan_pdt_his),
  KEY idx_load_pdt_his1 (i_loan_pdt),
  KEY FK_load_pdt_his1 (region),
  CONSTRAINT FK_load_pdt_his1 FOREIGN KEY (region) REFERENCES t_province (prov_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_rpt (
  i_report bigint(20) NOT NULL,
  begin_date datetime DEFAULT NULL,
  end_date datetime DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  merch_name varchar(50) DEFAULT NULL,
  pdf_filename varchar(100) DEFAULT NULL,
  rpt_date datetime DEFAULT NULL,
  rpt_id varchar(10) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (i_report),
  KEY idx_rpt_1 (rpt_id),
  KEY idx_rpt_2 (i_merch,rpt_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_statistics (
  i_loan_statis bigint(20) NOT NULL,
  cur_acce_count bigint(20) DEFAULT 0,
  cur_audit_count bigint(20) DEFAULT 0,
  cur_credit_amount decimal(19,2) DEFAULT 0,
  cur_credit_count bigint(20) DEFAULT 0,
  cur_period_ref_amount decimal(19,2) DEFAULT 0,
  cur_refund_amount decimal(19,2) DEFAULT 0,
  cur_refunding_count bigint(20) DEFAULT 0,
  debt_amount decimal(19,2) DEFAULT 0,
  expire_refund bigint(20) DEFAULT 0,
  freeze_amount decimal(19,2) DEFAULT 0,
  i_inst bigint(20) DEFAULT 0,
  inst_type int(11) DEFAULT 0,
  loan_acce_count bigint(20) DEFAULT 0,
  loan_amount decimal(19,2) DEFAULT 0,
  loan_apl_count bigint(20) DEFAULT 0,
  loan_aud_count bigint(20) DEFAULT 0,
  loan_count bigint(20) DEFAULT 0,
  mer_deb_count bigint(20) DEFAULT 0,
  overdue_amount decimal(19,2) DEFAULT 0,
  overdue_count bigint(20) DEFAULT 0,
  prod_count bigint(20) DEFAULT 0,
  refund_amount decimal(19,2) DEFAULT 0,
  refuse_accept_count bigint(20) DEFAULT 0,
  refuse_audit_count bigint(20) DEFAULT 0,
  PRIMARY KEY (i_loan_statis),
  UNIQUE KEY i_inst (i_inst,inst_type),
  KEY idx_loan_statistics1 (i_inst,inst_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loan_statistics_his (
  i_loan_statis_his bigint(20) NOT NULL AUTO_INCREMENT,
  cur_acce_count bigint(20) DEFAULT NULL,
  cur_audit_count bigint(20) DEFAULT NULL,
  cur_credit_amount decimal(19,2) DEFAULT NULL,
  cur_credit_count bigint(20) DEFAULT NULL,
  cur_period_ref_amount decimal(19,2) DEFAULT NULL,
  cur_refund_amount decimal(19,2) DEFAULT NULL,
  debt_amount decimal(19,2) DEFAULT NULL,
  expire_refund bigint(20) DEFAULT NULL,
  freeze_amount decimal(19,2) DEFAULT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_loan_statis bigint(20) DEFAULT NULL,
  inst_type int(11) DEFAULT NULL,
  loan_acce_count bigint(20) DEFAULT NULL,
  loan_amount decimal(19,2) DEFAULT NULL,
  loan_apl_count bigint(20) DEFAULT NULL,
  loan_aud_count bigint(20) DEFAULT NULL,
  loan_count bigint(20) DEFAULT NULL,
  mer_deb_count bigint(20) DEFAULT NULL,
  overdue_amount decimal(19,2) DEFAULT NULL,
  overdue_count bigint(20) DEFAULT 0,
  prod_count bigint(20) DEFAULT NULL,
  refund_amount decimal(19,2) DEFAULT NULL,
  refuse_accept_count bigint(20) DEFAULT NULL,
  refuse_audit_count bigint(20) DEFAULT NULL,
   time_stamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (i_loan_statis_his),
  KEY idx_loan_statistics1 (i_inst,inst_type, time_stamp)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_loanord_plan (
  i_loanord_plan bigint(20) NOT NULL,
  capital decimal(19,2) DEFAULT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_loan_ord bigint(20) NOT NULL,
  i_merch bigint(20) DEFAULT NULL,
  interest decimal(19,2) DEFAULT NULL,
  loan_ord_id varchar(12) NOT NULL,
  memo varchar(100) DEFAULT NULL,
  period int(11) NOT NULL,
  pre_stat int(11) NOT NULL,
  refund_date datetime NOT NULL,
  remain_amount decimal(19,2) DEFAULT NULL,
  repayment decimal(19,2) NOT NULL,
  plan_stat int(11) NOT NULL,
  upd_time datetime NOT NULL,
  version bigint(20) DEFAULT NULL,
  PRIMARY KEY (i_loanord_plan),
  KEY idx_loanord_plan1 (i_loan_ord)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_loanord_plan_his (
  i_loanord_plan bigint(20) NOT NULL,
  capital decimal(19,2) DEFAULT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_loan_ord bigint(20) NOT NULL,
  i_merch bigint(20) DEFAULT NULL,
  interest decimal(19,2) DEFAULT NULL,
  loan_ord_id varchar(12) NOT NULL,
  memo varchar(100) DEFAULT NULL,
  period int(11) NOT NULL,
  pre_stat int(11) NOT NULL,
  refund_date datetime NOT NULL,
  remain_amount decimal(19,2) DEFAULT NULL,
  repayment decimal(19,2) NOT NULL,
  plan_stat int(11) NOT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_loanord_plan)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_mcc (
  id varchar(8) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(5) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch (
  i_merch bigint(20) NOT NULL,
  audir_businlic varchar(200) DEFAULT NULL,
  audit_addr varchar(200) DEFAULT NULL,
  businlic varchar(200) DEFAULT NULL,
  company_size varchar(50) DEFAULT NULL,
  contract varchar(20) DEFAULT NULL,
  contract_tel varchar(50) DEFAULT NULL,
  credit varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  industry varchar(255) DEFAULT NULL,
  logo_i_file bigint(20) DEFAULT NULL,
  merch_name varchar(255) DEFAULT NULL,
  merch_no varchar(255) DEFAULT NULL,
  merch_type int(11) NOT NULL,
  open_time datetime DEFAULT NULL,
  pos_addr varchar(255) DEFAULT NULL,
  province varchar(255) DEFAULT NULL,
  reg_addr varchar(255) DEFAULT NULL,
  reg_cap varchar(255) DEFAULT NULL,
  reg_no varchar(255) DEFAULT NULL,
  reg_time datetime DEFAULT NULL,
  tax_reg varchar(255) DEFAULT NULL,
  qq_uid varchar(20) NULL,
  original_time varchar(20) DEFAULT NULL,
  PRIMARY KEY (i_merch),
  KEY idx_merch1 (merch_name),
  KEY idx_merch2 (merch_no,merch_type),
  KEY idx_merch3 (reg_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_audithistory (
  i_merch_audithistory bigint(20) NOT NULL,
  audit_status varchar(255) DEFAULT NULL,
  update_content varchar(255) DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  update_user varchar(255) DEFAULT NULL,
  PRIMARY KEY (i_merch_audithistory),
  KEY idx_merch_audithistory1 (update_time),
  KEY idx_merch_audithistory2 (update_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_busi_info (
  i_merch_busi_info bigint(20) NOT NULL,
  account_no varchar(255) DEFAULT NULL,
  bank varchar(255) DEFAULT NULL,
  i_merch bigint(20) NOT NULL,
  merch_natrue varchar(255) DEFAULT NULL,
  bank_code varchar(255) NOT NULL,
  account_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (i_merch_busi_info),
  KEY idx_merch_busi_info1 (bank),
  KEY idx_merch_busi_info2 (account_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_coll_inst (
  i_merch_coll_inst bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_merch_usr bigint(20) NOT NULL,
  i_merch bigint(20) NOT NULL,
  PRIMARY KEY (i_merch_coll_inst),
  KEY idx_merch_coll_inst1 (i_inst),
  KEY idx_merch_coll_inst2 (i_merch_usr)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_coll_pdt (
  i_merch_coll_pdt bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) NOT NULL,
  i_loan_pdt bigint(20) NOT NULL,
  i_merch_usr bigint(20) NOT NULL,
  i_merch bigint(20) NOT NULL,
  PRIMARY KEY (i_merch_coll_pdt),
  KEY idx_merch_coll_pdt1 (i_merch_usr),
  KEY idx_merch_coll_pdt2 (i_loan_pdt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_info_appeal (
  i_merch_info_appeal bigint(20) NOT NULL,
  appeal_content varchar(255) NOT NULL,
  appeal_man varchar(255) NOT NULL,
  appeal_phone varchar(255) NOT NULL,
  appeal_reason varchar(255) NOT NULL,
  PRIMARY KEY (i_merch_info_appeal)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_info_perm (
  i_minf_perm bigint(20) NOT NULL,
  accept_type int(11) DEFAULT NULL,
  gen_time datetime DEFAULT NULL,
  imerch bigint(20) DEFAULT NULL,
  position int(11) DEFAULT NULL,
  PRIMARY KEY (i_minf_perm),
  KEY idx_minf_perm1 (imerch,position)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_legal_pers (
  i_merch_legal_pers bigint(20) NOT NULL,
  age int(11) DEFAULT NULL,
  cerd_no varchar(255) DEFAULT NULL,
  edu_background int(11) DEFAULT NULL,
  fa_addr varchar(255) DEFAULT NULL,
  i_merch bigint(20) NOT NULL,
  legal_pers_name varchar(255) DEFAULT NULL,
  marital_status int(11) DEFAULT NULL,
  mobi_phone varchar(255) DEFAULT NULL,
  office_phone varchar(255) DEFAULT NULL,
  sex int(11) DEFAULT NULL,
  PRIMARY KEY (i_merch_legal_pers),
  KEY idx_merch_legal_pers1 (legal_pers_name),
  KEY idx_merch_legal_pers2 (cerd_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_used_payer (
  i_merch_used_payer bigint(20) NOT NULL,
  i_merch bigint(20) NOT NULL,
  payer_bank_name varchar(255) NOT NULL,
  payer_card_no varchar(255) NOT NULL,
  payer_name varchar(255) NOT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_merch_used_payer),
  KEY idx_merch_used_payer (i_merch)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_merch_user (
  i_merch_user bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  i_merch bigint(20) NOT NULL,
  last_login_time datetime DEFAULT NULL,
  login_name varchar(20) NOT NULL,
  merch_no varchar(30) DEFAULT NULL,
  merch_stat int(11) NOT NULL,
  passwd varchar(50) DEFAULT NULL,
  upd_time datetime NOT NULL,
  user_name varchar(50) DEFAULT NULL,
  user_type varchar(50) NOT NULL,
  PRIMARY KEY (i_merch_user),
  UNIQUE KEY user_type (user_type,login_name),
  KEY idx_merch_user1 (merch_no),
  KEY idx_merch_user2 (login_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_oper_log (
  trace_no varchar(16) NOT NULL,
  gen_time datetime NOT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  inst_id varchar(50) DEFAULT NULL,
  inst_type int(11) NOT NULL,
  ip_addr varchar(255) DEFAULT NULL,
  i_roles varchar(100) DEFAULT NULL,
  i_user bigint(20) DEFAULT NULL,
  login_name varchar(50) NOT NULL,
  memo varchar(200) DEFAULT NULL,
  merch_id varchar(50) DEFAULT NULL,
  oper_result varchar(200) DEFAULT NULL,
  oper_stat int(11) NOT NULL,
  oper_type int(11) NOT NULL,
  role_names varchar(200) DEFAULT NULL,
  user_name varchar(200) DEFAULT NULL,
  PRIMARY KEY (trace_no),
  KEY idx_oper_log1 (i_inst),
  KEY idx_oper_log2 (i_inst,gen_time),
  KEY idx_oper_log3 (i_merch),
  KEY idx_oper_log4 (i_merch,gen_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_ord_operlog (
  amount decimal(19,2) NOT NULL,
  i_loan_ord bigint(20) NOT NULL,
  i_loan_pdt bigint(20) NOT NULL,
  loan_ord_id varchar(12) NOT NULL,
  loan_pdt_id varchar(8) NOT NULL,
  oth_trace_no varchar(32) DEFAULT NULL,
  pdt_type int(11) NOT NULL,
  period varchar(200) DEFAULT NULL,
  term int(11) DEFAULT NULL,
  year_rate decimal(19,2) DEFAULT NULL,
  trace_no varchar(16) NOT NULL,
  PRIMARY KEY (trace_no),
  KEY idx_ord_operlog1 (i_loan_ord),
  KEY FK_ord_operlog1 (trace_no),
  CONSTRAINT FK_ord_operlog1 FOREIGN KEY (trace_no) REFERENCES t_oper_log (trace_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_pdt_operlog (
  i_pdt bigint(20) NOT NULL,
  max_amount decimal(19,2) DEFAULT NULL,
  min_amount decimal(19,2) DEFAULT NULL,
  pdt_id varchar(8) NOT NULL,
  pdt_name varchar(30) DEFAULT NULL,
  pdt_type int(11) DEFAULT NULL,
  purpose varchar(200) DEFAULT NULL,
  term int(11) DEFAULT NULL,
  year_rate decimal(19,2) DEFAULT NULL,
  trace_no varchar(16) NOT NULL,
  PRIMARY KEY (trace_no),
  KEY FK_pdt_operlog1 (trace_no),
  CONSTRAINT FK_pdt_operlog1 FOREIGN KEY (trace_no) REFERENCES t_oper_log (trace_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






CREATE TABLE t_schedule (
  i_sch bigint(20) NOT NULL,
  end_time datetime DEFAULT NULL,
  err_msg varchar(200) DEFAULT NULL,
  ip_addr varchar(32) DEFAULT NULL,
  run_stat int(11) DEFAULT NULL,
  run_date datetime DEFAULT NULL,
  schedule_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (i_sch)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_schedule_his (
  i_sch_his bigint(20) NOT NULL,
  end_time datetime DEFAULT NULL,
  err_msg varchar(200) DEFAULT NULL,
  gen_time datetime DEFAULT NULL,
  ip_addr varchar(32) DEFAULT NULL,
  run_stat int(11) DEFAULT NULL,
  run_date datetime DEFAULT NULL,
  schedule_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (i_sch_his)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_sms_log (
  i_sms_log bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  msg varchar(100) DEFAULT NULL,
  msg_type int(11) NOT NULL,
  phone varchar(50) NOT NULL,
  sms_url varchar(200) DEFAULT NULL,
  src_i_bs_user bigint(20) DEFAULT NULL,
  src_i_inst bigint(20) DEFAULT NULL,
  src_i_inst_usr bigint(20) DEFAULT NULL,
  src_i_merch bigint(20) DEFAULT NULL,
  src_i_merch_usr bigint(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  targ_i_bs_user bigint(20) DEFAULT NULL,
  targ_i_inst bigint(20) DEFAULT NULL,
  targ_i_inst_usr bigint(20) DEFAULT NULL,
  targ_i_merch bigint(20) DEFAULT NULL,
  targ_i_merch_usr bigint(20) DEFAULT NULL,
  upd_time datetime NOT NULL,
  PRIMARY KEY (i_sms_log)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_sno_cfg (
  sno_id int(11) NOT NULL AUTO_INCREMENT,
  max_val int(11) DEFAULT NULL,
  sno_type varchar(200) DEFAULT NULL,
  PRIMARY KEY (sno_id),
  KEY idx_sno_cfg1 (sno_type)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


CREATE TABLE t_sys_param (
  i_sys_param bigint(20) NOT NULL,
  gen_time datetime NOT NULL,
  param_name varchar(30) NOT NULL,
  param_type varchar(30) NOT NULL,
  upd_time datetime NOT NULL,
  `value` varchar(100) NOT NULL,
  version bigint(20) DEFAULT NULL,
  PRIMARY KEY (i_sys_param),
  UNIQUE KEY param_type (param_type,param_name),
  KEY idx_sys_param1 (param_type),
  KEY idx_sys_param2 (param_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_trans_log (
  order_no varchar(16) NOT NULL,
  bg_ret_url varchar(200) DEFAULT NULL,
  call_back_url varchar(200) DEFAULT NULL,
  charge varchar(1) NOT NULL,
  curr_code varchar(3) NOT NULL,
  error_msg varchar(200) DEFAULT NULL,
  gate_id varchar(1) NOT NULL,
  gate_type varchar(200) DEFAULT NULL,
  i_inst bigint(20) DEFAULT NULL,
  i_merch bigint(20) DEFAULT NULL,
  mer_id varchar(15) NOT NULL,
  order_amount decimal(19,2) NOT NULL,
  organ_no varchar(10) NOT NULL,
  other_sys_no varchar(50) DEFAULT NULL,
  priv1 varchar(1) NOT NULL,
  resp_code varchar(5) DEFAULT NULL,
  result_mode varchar(1) NOT NULL,
  settle_no varchar(15) NOT NULL,
  trans_count int(11) NOT NULL,
  trans_date datetime NOT NULL,
  trans_stat int(11) NOT NULL,
  trans_type varchar(2) NOT NULL,
  flow_type int(11) NOT NULL,
  user_id varchar(2) NOT NULL,
  version varchar(10) NOT NULL,
  PRIMARY KEY (order_no),
  KEY idx_trans_log1 (flow_type,trans_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE t_hc_trans_log_mouth (
  mouth char(6) NOT NULL,
  merch_no varchar(20) NOT NULL,
  trans_type varchar(2),
  trans_num bigint(20),
  amt decimal(19,2),
  avg_amt decimal(19,2),
  stddev_amt decimal(19,2),
  charge decimal(19,2),
  PRIMARY KEY (mouth, merch_no, trans_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE t_fund_flow ADD COLUMN recive_acct_no VARCHAR(32) NOT NULL , 
ADD COLUMN recive_acct_name VARCHAR(100) NOT NULL , 
ADD COLUMN recive_bank_name VARCHAR(200) NOT NULL , 
ADD COLUMN recive_bank_code VARCHAR(20) NOT NULL;
ALTER TABLE t_fund_flow 
DROP INDEX idx_fund_flow1 
, ADD INDEX idx_fund_flow1 (oth_sys_no ASC) ;
ALTER TABLE t_trans_log ADD COLUMN attachment VARCHAR(255) NULL;
ALTER TABLE t_trans_log 
ADD INDEX idx_trans_log2 (trans_date ASC, trans_stat ASC) ;

