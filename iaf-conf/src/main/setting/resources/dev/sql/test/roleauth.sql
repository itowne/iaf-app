

insert into t_inst_role_auth(i_inst_role, i_inst_auth) SELECT a.i_inst_role, b.i_inst_auth FROM t_inst_role a, t_inst_auth b;