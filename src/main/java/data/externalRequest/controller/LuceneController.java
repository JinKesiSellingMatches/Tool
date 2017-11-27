package data.externalRequest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import core.utils.JacksonUtil;
import data.common.factory.DataSourceContext;
import data.common.manager.BaseGaiaDao;
import data.common.manager.LuceneDao;
import data.demo.entity.Demo;
import data.demo.manager.DemoManager;
import data.externalRequest.pojo.ReceivePOJO;
import data.lucene.manager.LuceneNodeManager;
import data.lucene.pojo.LuceneSerachPOJO;

@Controller
@RequestMapping("/external")
public class LuceneController {
	
	@Resource
	private DemoManager demoManager;
	
	@Resource
	private BaseGaiaDao baseGaiaDao;
	
	@Resource
	private LuceneNodeManager luceneNodeManager;
	
	/**
	 * 唯一接收数据接口（用来保存索引）
	 * @param pojo
	 */
	@RequestMapping("/send")
	@ResponseBody
	public void save(String send){
		try {
			ReceivePOJO pojo=(ReceivePOJO) JacksonUtil.jsonToBean(send, ReceivePOJO.class);
			DataSourceContext dataSourceContext=new DataSourceContext();
			//dataSourceContext.dataSource(pojo);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 唯一接收数据接口（用来保存索引）
	 * @param pojo
	 */
	@RequestMapping("/search")
	@ResponseBody
	public List<LuceneSerachPOJO> search(String search){
		
		List<LuceneSerachPOJO> pojos=new ArrayList<>();
		try {
			pojos= luceneNodeManager.search(search);
		} catch (Exception e) {
			System.out.println(e);
		}
		return pojos;
	}
}
