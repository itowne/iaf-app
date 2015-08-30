package com.newland.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import newland.iaf.base.model.dict.InstUserStatType;
import newland.iaf.inst.model.InstUser;
import newland.iaf.inst.service.InstUserManagerService;

import org.junit.Test;

import com.newland.BeanTest;



public class IdGerTest extends BeanTest{
	
	/**机构用户信息操作DAO接口**/
	@Resource(name = "instUserManagerService")
	private InstUserManagerService instUserManagerService;
	
	@Test
	public void testInstUser() throws Exception{
		try {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			InstUser user = new InstUser();
			user.setIinst(1L);
			user.setGenTime(new Date());
			user.setLastLoginTime(new Date());
			user.setLoginName("aaaaa");
			user.setPasswd("111111");
			user.setUpdTime(new Date());
			user.setUserName("测试用户");
			user.setUsrStat(InstUserStatType.USED);
			instUserManagerService.addInstUser(user);
			//InstUser user2 = instUserManagerService.findInstUser(new Long(1));
			
			//System.out.println("##################################"+user2.getLoginName());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public static void main(String[] args){
		List<Object> obj = new ArrayList<Object>(20);
		List<Object> list = new ArrayList<Object>();
		list.add(new Object());
		list.add(new Object());
		obj.addAll(list);
		System.err.println(obj.size());
		obj.set(10, new Object());
		System.err.println(obj.size());
	}
}
