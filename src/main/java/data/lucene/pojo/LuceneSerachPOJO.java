package data.lucene.pojo;


public class LuceneSerachPOJO {
	
    /**
     * 搜索,唯一ID
     */
    private String serach,luceneId;
    
    /**
     *所属模块                                                             
     */
    private String moduleCode;
    
    private String tableId;

	public String getSerach() {
		return serach;
	}

	public void setSerach(String serach) {
		this.serach = serach;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getLuceneId() {
		return luceneId;
	}

	public void setLuceneId(String luceneId) {
		this.luceneId = luceneId;
	}

}
