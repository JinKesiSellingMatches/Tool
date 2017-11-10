package data.demo.manager.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import data.common.manager.impl.BaseDaoImpl;
import data.demo.entity.Demo;
import data.demo.manager.DemoManager;

@Service(value="demoManager")
public class DemoManagerImpl extends BaseDaoImpl implements DemoManager {

	@Override
	public void doSave() {
		Demo demo=new Demo();
		demo.setCreateDate(new Date());
		demo.setName("nihao");
		this.save(demo);
	}

}
