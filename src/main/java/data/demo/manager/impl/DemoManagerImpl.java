package data.demo.manager.impl;

import org.springframework.stereotype.Service;

import data.common.manager.impl.BaseDaoImpl;
import data.demo.manager.DemoManager;

@Service(value="demoManager")
public class DemoManagerImpl extends BaseDaoImpl implements DemoManager {

}
