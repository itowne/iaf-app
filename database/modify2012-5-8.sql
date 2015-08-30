drop table if EXISTS t_hc_trans_log_mouth;
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

drop table if EXISTS t_hc_trans;
CREATE TABLE t_hc_trans (
  i_hc_trans bigint(20) NOT NULL,
  export_time datetime DEFAULT NULL,
  remote_file varchar(50) NOT NULL,
  local_file varchar(50),
  gen_time datetime NOT NULL,
  insert_num int(11) DEFAULT NULL,
  realRecNum int(11) DEFAULT NULL,
  rec_num int(11) DEFAULT NULL,
  sha1 varchar(28) NOT NULL,
  size bigint(20) NOT NULL,
  status varchar(10) NOT NULL,
  trans_type varchar(255) NOT NULL,
  upd_time datetime DEFAULT NULL,
  memo varchar(100),
  PRIMARY KEY (i_hc_trans)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if EXISTS t_hc_install_log;
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
  uninstall_reason varchar(100) DEFAULT NULL,
  PRIMARY KEY (i_hc_install_log),
  KEY idx_hc_install_log1 (hc_merch_no),
  KEY idx_hc_install_log4 (term_stat)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE `iaf`.`t_inst_busi_info` ADD COLUMN `bank_code` VARCHAR(20) NOT NULL  AFTER `repayment_no` ;
