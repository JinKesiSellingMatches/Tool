package Junit;


import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

		Demo demo=new Demo();
		demo.setCreateDate(new Date());
		demo.setDeleted(0);
		demo.setName("hello word!");
		DemoManager manager = appCtx.getBean("demoManager", DemoManager.class);
		manager.save(demo);
	}

} 
