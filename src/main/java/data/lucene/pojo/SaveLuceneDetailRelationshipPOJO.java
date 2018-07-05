package data.lucene.pojo;

import java.util.List;

public class SaveLuceneDetailRelationshipPOJO {
	
	
	/**
	 * 编码，名称，添加第一次查询，添加时第二次查询字段，等级
	 */
	public String code,name,addFirstSelect,addSecondSelect,levelNode;
	
	/**
	 * 详情
	 */
	public List<SaveLuceneRelationshipPOJO> details;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddFirstSelect() {
		return addFirstSelect;
	}

	public void setAddFirstSelect(String addFirstSelect) {
		this.addFirstSelect = addFirstSelect;
	}

	public String getAddSecondSelect() {
		return addSecondSelect;
	}

	public void setAddSecondSelect(String addSecondSelect) {
		this.addSecondSelect = addSecondSelect;
	}

	public String getLevelNode() {
		return levelNode;
	}

	public void setLevelNode(String levelNode) {
		this.levelNode = levelNode;
	}

	public List<SaveLuceneRelationshipPOJO> getDetails() {
		return details;
	}

	public void setDetails(List<SaveLuceneRelationshipPOJO> details) {
		this.details = details;
	}
	
	

}
