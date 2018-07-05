package data.demo.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import data.common.manager.BaseGaiaDao;
import data.demo.entity.Demo;
import data.demo.manager.DemoManager;

@Controller
@RequestMapping("/demo")
public class DemoController {
	
	@Resource
	private DemoManager demoManager;
	
	@Resource
	private BaseGaiaDao baseGaiaDao;
	
	
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(){
		try {
			Demo demo=new Demo();
			demo.setCreateDate(new Date());
			demo.setDeleted(0);
			demo.setContent("liu");
			demo.setContent1("jun");
			demo.setTitle1("liu");
			demo.setTitle2("jun");
			demo.setName("liujiun");
			demoManager.save(demo);
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void update(){
		Demo demo=new Demo();
		demo.setId("402881e15fb4cfc1015fb4cfec210000");
		demo.setVersion(0L);
		demo.setDeleted(0);
		
		demo.setContent("ni");
		demo.setContent1("hao");
		demo.setTitle1("ni");
		demo.setTitle2("hao");
		demo.setName("nihao");
		demoManager.update(demo);
	}
	
	@RequestMapping("/deleted")
	@ResponseBody
	public void deleted(String id){
		Demo demo=demoManager.get(Demo.class, id);
		demoManager.delete(demo);
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public void find(){
		System.out.println(baseGaiaDao.findBySql("select * from tb_sys_user"));
	}
}
