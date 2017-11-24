package Junit;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.common.manager.BaseGaiaDao;
import data.demo.entity.Demo;
import data.demo.manager.DemoManager;



public class JunitTest {
	private ClassPathXmlApplicationContext appCtx;

	@Before
	public void init() {
		appCtx = new ClassPathXmlApplicationContext(
				new String[] { "conf/spring.xml","conf/spring-mvc.xml", "conf/spring-shiro.xml", "conf/spring-hibernate.xml" });
	}

	@Test
	public void testGetMapByHql() throws Exception {

		BaseGaiaDao manager = appCtx.getBean("baseGaiaDao", BaseGaiaDao.class);
		System.out.println(manager.find("select * from tb_sys_user"));
	}

} 
