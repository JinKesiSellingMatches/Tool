package core.download.pixiv;

import java.util.List;

public class AttentionPOJO {
	
	//作者
	private String attentionName;
	
	//请求地址
	private String attentionUrl;
	
	//图片请求地址
	private List<String> pageUrl;
	
	private List<String> imgPageUrl;
	
	//真实图片下载地址
	private List<String> imgUrl;

	public String getAttentionName() {
		return attentionName;
	}

	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}

	public String getAttentionUrl() {
		return attentionUrl;
	}

	public void setAttentionUrl(String attentionUrl) {
		this.attentionUrl = attentionUrl;
	}

	public List<String> getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(List<String> pageUrl) {
		this.pageUrl = pageUrl;
	}

	public List<String> getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<String> getImgPageUrl() {
		return imgPageUrl;
	}

	public void setImgPageUrl(List<String> imgPageUrl) {
		this.imgPageUrl = imgPageUrl;
	}

}
