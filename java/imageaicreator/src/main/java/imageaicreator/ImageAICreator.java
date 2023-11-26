package imageaicreator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;


public class ImageAICreator {
	private static final String URL = "https://api.vyro.ai/v1/imagine/api/generations";
	public static final String IMAGE_RESOURCES_PATH = "C:\\resources";

	public static void main(String[] args) {
		ImageAICreator imageCreator = new ImageAICreator();
		writeImageToFile(imageCreator.sendImagePrompt("A ragdool cat playing football"), "C:\\resources\\image.png");
	}
	
	public Boolean createAIImage(String prompt, String filePath) {
		Boolean result = true;
		
		HttpResponse response = sendImagePrompt(prompt);

		writeImageToFile(response, filePath);
		
		return result;
	}

	public HttpResponse sendImagePrompt(String prompt) {
		
		HttpResponse result = null;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(URL);
		post.setHeader("Authorization", "Bearer <YOUR API KEY HERE>");
		
		final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addTextBody("prompt", prompt, ContentType.TEXT_PLAIN);
        builder.addTextBody("style_id", "29", ContentType.TEXT_PLAIN);
        
        HttpEntity entity = builder.build();
        post.setEntity(entity);
        
		try {
			result = client.execute(post); 
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

	public static boolean writeImageToFile(HttpResponse imageData, String destination) {
		Boolean result = false;
		File outputFile = new File(destination);
		try {
			FileUtils.copyInputStreamToFile(imageData.getEntity().getContent(), outputFile);
			result = true;
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}

	

}
