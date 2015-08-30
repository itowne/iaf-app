package com.newland.test;

import java.io.FileInputStream;

import javax.annotation.Resource;

import newland.iaf.base.model.TransMsg;
import newland.iaf.base.service.HicardPaymentService;
import newland.iaf.utils.service.SerialNoService;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.hicard.Hicard;
import com.newland.BeanTest;

public class HicardPaymentServiceTest extends BeanTest {

	@Resource(name = "hicardPaymentService")
	private HicardPaymentService hicardPaymentService;
	@Resource (name = "serialNoService")
	private SerialNoService serialNoService;
	@Test
	public void test() throws Throwable{
		TransMsg trans = this.hicardPaymentService.queryById("2013040600000802");
		trans.setMerId("000000000000001");
		trans.setOrganNo("0000000001");
		trans.setTransType("0");
		trans.setResultMode("");
		trans.setCallBackUrl("http://192.168.10.47:8080/struts1");
		trans.setOrderNo("2013040600000802");
		trans.setAttachment("测试");
		this.hicardPaymentService.transfer(trans);
	}
	
	public void test1() throws Throwable{
		String encryStr = "MVOe8dajgbfh8GQBDuvwSRo8fjHATRAodpUcN+lCeMudCp3tQ3maCsrKL99aHUtfW9YzueRlyYFc/6FOk9hiL8lLAWGGl9bbf8ZrW8YrYgwejaq/khf3fH3TTHE9MJ5fQfHCLkN5eagtNoz4dva2C29YNWEG2TdlDZJmLJ1qABuqLpFiTTfonnMA/hFJUm/dOjHWJVu8QRDPi+bkU8d0j3OigWdngumCcu+OFCvD4nTWajmNJwI0OjtgWEfo32VUUGxBIu936qEq0g4cMPYj4yzMTcGoIK2vZHd38DGIiqZNOwhPRL4YjB7IapIURz6yFsuSDLZCgvr71b/RibW/CMZ15d8Kp5QbrhfgtHSDDYDozJrtgBUulNyQqDZvz70F3F9Qi0y0kAkGWk+axwKhK+ubQ2YpKUDzAVPcZpAuO8fxhmNGPfcl3G/ADAbJcBqULltTpiT1K4cpgRb4E6ZF2BlPVf9+y0QEQDrd2BbLKn27MPS4EdPs60IElushbKk5hfExUQofW7FmIfc5ksx10zDQNuKpSVL50Hkh/0wQx6nue2xd9m8tL1B2goHkJoHpaSGHp8lOU5wDLc71xV/LHpaNH9qQcODxCI7S7hdC6LtJ7gjVJ5WvHh7gHQj+lpMcBZhwNTszzS9/374KNesR7SmzBmxdKLbJxRetgY4YJS8=";
		String signStr = "Y2e3su60urb4zCYFE0ba2QviZ0gGPsti4lCTZ5zElMPfPeSUNo1fkIYWBXmxWUc4lBWVYuZHb5GlDsxhR/rJtKzOrUMcmzUWqpvPZ1L4eLY8VdyKg9rpW1cbb/jxkWTRs4HYJNMyQkIULawzXhd7CRKPGtLvxMYmxU1nLwGL3wSPmJ6+rIuf4EMmgDtRVCNOdYyYjGhL8Yh1fImw8AEhyV+qogPD4XnrMCcgQnnnv9kR2UvFfrAL0/A1e2oJDjY63QdgrmrPNlaKWT702r4eMAEf48A6/gabmzIilPTpCrKba1Cr6iz1zH/hqmsc4bYpwjortZZcnmol2pqgqFXpXLwxXXhFUX77LDO3Wsy0BQktqKA2NU1LGa8jhaZrOwOi/KVlL2ErulfoA/uQM4FuwUNAunVAUCtcgGWIbl+0oMnfYLQ1V1oguoNea+B7BbHrz2/cMRZgkxusI1QtowWgphOvrS+mYDkbLXL6E5WSWdqlZSmBKA+8g1OjBAlVfo7cOlWX5qRsmm8Q/C22IuyE7Ma+K1IQpwgLrUUm4GZj7pGh44w+7J8ph7pUfSzhYaZoi2lIcLKxdSFSc8qcWVnEtwbo9LJWOJAMa9e4/Db8qqEo/qktXrOabHtZN4zj1geoaw3YnIHo2ezjfQsgzo52llxiru/wJYQyIgBZx3Ghgzc=";
		this.hicardPaymentService.procRet(encryStr, signStr, null, null);
		Hicard h = new Hicard();
	}
	
	public static void main(String[] args) throws Throwable{
		String encryStr = IOUtils.toString(new FileInputStream("test.dat"));
		System.err.println(Hicard.decrypt("e:/tmp/keyfile/privateKeyFile.dat", encryStr));
	}
}
