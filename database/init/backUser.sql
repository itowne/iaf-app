delete from t_bs_role where 1=1;
delete from t_bs_user where 1=1;
delete from t_bs_user_role where 1=1;
insert into t_bs_role  (i_bs_role , gen_time , role_desc , role_name , status , upd_time )
 values (10, '2013-03-21', ' 管理员', 'administrators', 1, '2013-03-21');
 
insert into t_bs_user ( gen_time , i_bs_user , login_name , password , status , upd_time , user_name )
 values ('2013-03-21', 10, 'admin', 'H4LJQr79optu1IelHaGZ94/OfwU=', 1, '2013-03-21', 'test');
 
insert into t_bs_user_role ( i_bs_role , i_bs_user )
 values (10, 10);
 