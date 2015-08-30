package com.newland.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.backstage.model.BackStageRole;
import newland.iaf.backstage.model.BackStageUser;
import newland.iaf.backstage.service.BackStageRoleService;
import newland.iaf.backstage.service.BackStageUserService;
import newland.iaf.base.model.dict.InstUserStatType;

import org.junit.Test;

import com.newland.BeanTest;


public class BackStageUserServiceTest extends BeanTest {
	@Resource (name = "backStageUserService")
	private BackStageUserService bsuService;
	@Resource (name = "backStageRoleService")
	private BackStageRoleService bsrService;
	@Test
	public void test() throws Exception{
		BackStageUser user = new BackStageUser();
		user.setGenTime(new Date());
		user.setLoginName("shizhenning");
		user.setPassword("shizhenning");
		user.setStat(InstUserStatType.USED);
		user.setUpdTime(new Date());
		user.setUserName("test");
		
		BackStageRole role = new BackStageRole();
		role.setGenTime(new Date());
		role.setRoleName("accept");
		role.setStat(1);
		role.setUpdTime(new Date());
		
		this.bsrService.save(role);
		role = this.bsrService.queryByName("accept");
		List<BackStageRole> roles = new ArrayList<BackStageRole>();
		roles.add(role);
		//user.setRoles(roles);
		this.bsuService.save(user);
		this.bsuService.login("shizhenning", "shizhenning");
	}

}
