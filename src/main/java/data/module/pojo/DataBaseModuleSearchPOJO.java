package data.module.pojo;

import java.math.BigInteger;

/**
 * 这个POJO用来接收sql 返回的字段唯一！！！！
 * @author hutao
 *
 */
public class DataBaseModuleSearchPOJO {
	
	/**
	 * 主键Id
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public BigInteger createDate;
	
	/**
	 * 标题
	 */
	public String title;
	
	/**
	 * 内容
	 */
	public String content;
	
	/**
	 * 搜索
	 */
	public String search;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public BigInteger getCreateDate() {
		return createDate;
	}

	public void setCreateDate(BigInteger createDate) {
		this.createDate = createDate;
	}

}