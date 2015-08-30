/**
 * 
 */
package com.newland;

import javax.annotation.Resource;

import newland.iaf.other.service.InitService;

import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * @author lindaqun
 * 
 */
@ContextConfiguration(locations = { "classpath:/conf/beans.xml" })
public class BeanTest extends AbstractJUnit4SpringContextTests {

	//@Resource(name = "initDemoService")
	//protected InitService initDemoService;

}
