package core.download.twitter;

public class tweets {
	
	private boolean has_more_items;
	
	private String items_html;
	
	private String min_position;
	
	private int new_latent_count;

	public boolean isHas_more_items() {
		return has_more_items;
	}

	public void setHas_more_items(boolean has_more_items) {
		this.has_more_items = has_more_items;
	}

	public String getItems_html() {
		return items_html;
	}

	public void setItems_html(String items_html) {
		this.items_html = items_html;
	}

	public String getMin_position() {
		return min_position;
	}

	public void setMin_position(String min_position) {
		this.min_position = min_position;
	}

	public int getNew_latent_count() {
		return new_latent_count;
	}

	public void setNew_latent_count(int new_latent_count) {
		this.new_latent_count = new_latent_count;
	}

}
