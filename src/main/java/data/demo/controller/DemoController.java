package data.demo.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.demo.entity.Demo;
import data.demo.manager.DemoManager;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Resource
	private DemoManager demoManager;
	
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(){
		try {
			Demo demo=new Demo();
			demo.setCreateDate(new Date());
			demo.setDeleted(0);
			demo.setContent("Conetent");
			demo.setContent1("Conetent1");
			demo.setTitle1("title1");
			demo.setTitle2("title2");
			demo.setName("hello word!");
			demoManager.save(demo);
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update(){
		Demo demo=new Demo();
		demo.setId("402881e15f9a2c9d015f9a2cd6590000");
		demo.setVersion(0L);
		demo.setDeleted(0);
		demo.setName("hello word!");
		demoManager.update(demo);
	}
	
	@RequestMapping("/deleted")
	@ResponseBody
	public void deleted(){
		Demo demo=demoManager.get(Demo.class, "402881e15f9a2c9d015f9a2cd6590000");
		demoManager.delete(demo);
	}
}
