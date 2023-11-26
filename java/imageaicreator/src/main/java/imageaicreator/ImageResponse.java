package imageaicreator;

import java.util.List;

public class ImageResponse {

	private Long created;
    private List<Image> data;
    
	public Long getCreated() {
		return created;
	}
	public void setCreated(Long created) {
		this.created = created;
	}
	public List<Image> getData() {
		return data;
	}
	public void setData(List<Image> data) {
		this.data = data;
	}
	
}
