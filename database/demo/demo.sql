DROP TABLE IF EXISTS t_loan_statistics_his;
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
  prod_count bigint(20) DEFAULT NULL,
  refund_amount decimal(19,2) DEFAULT NULL,
  refuse_accept_count bigint(20) DEFAULT NULL,
  refuse_audit_count bigint(20) DEFAULT NULL,
   time_stamp datetime ,
  PRIMARY KEY (i_loan_statis_his)
);

-- 新增贷款申请--
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001', 1, '201203150001',0,  '2013-04-20', '2013-03-15', 1, 1, 1, 'P0000001', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '工商银行', '美容店', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',2, '201203150002',0,  '2013-04-30', '2013-03-15', 1, 1, 1, 'P0000002', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '工商银行', '九**服装店', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',3, '201203150003',0,  '2013-04-30', '2013-03-15', 1, 1, 1, 'P0000003', '2013-03-15', 1, 100000, 0.1, 1, 24, '2013-03-15', 1, 0, '人人贷', '九**服装店', 0);


insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',4, '201203150004',0,  '2013-04-20', '2013-03-15', 1, 1, 2, 'P0000001', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '人人贷', '松**电器', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',5, '201203150005',0,  '2013-04-30', '2013-03-15', 1, 1, 2, 'P0000002', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '广州银行', '西餐厅', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',6, '201203150006',0,  '2013-04-30', '2013-03-15', 1, 1, 2, 'P0000003', '2013-03-15', 1, 100000, 0.1, 1, 24, '2013-03-15', 1, 0, '测试商户', '测试机构', 0);

insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',7, '201203150007',0,  '2013-04-20', '2013-03-15', 1, 1, 3, 'P0000001', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '测试商户', '测试机构', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',8, '201203150008',0,  '2013-04-30', '2013-03-15', 1, 1, 3, 'P0000002', '2013-03-15', 0, 100000, 0.1, 0, 24, '2013-03-15', 1, 0, '测试商户', '测试机构', 0);
insert into t_loan_ord (merch_id, organ_id, i_loan_ord , loan_ord_id , pdt_type , expiry_date , gen_time , i_inst , i_loan_pdt , i_merch , loan_pdt_id , ord_date , ord_stat , quota , rate , recive_period , term , upd_time , version  , merch_type, merch_name, inst_name,shield)
 values ('000000000000001','0000000001',9, '201203150009',0,  '2013-04-30', '2013-03-15', 1, 1, 3, 'P0000003', '2013-03-15', 1, 100000, 0.1, 1, 24, '2013-03-15', 1, 0, '测试商户', '测试机构', 0); 
 
 
 

 
