package imageaicreator;

public class ImageRequest {
	
	private String model;
    private String prompt;
    private Integer n;
    private String quality;
    private String size;
    private String responseFormat;
    
    
    
	public ImageRequest(String model, String prompt, Integer n, String quality, String size, String responseFormat) {
		super();
		this.model = model;
		this.prompt = prompt;
		this.n = n;
		this.quality = quality;
		this.size = size;
		this.responseFormat = responseFormat;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public Integer getN() {
		return n;
	}
	public void setN(Integer n) {
		this.n = n;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getResponseFormat() {
		return responseFormat;
	}
	public void setResponseFormat(String responseFormat) {
		this.responseFormat = responseFormat;
	} 

}
