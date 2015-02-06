package co.conker.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;

public class JSONFetcher {
	private Context context;
	
	public JSONFetcher(Context a_context) {
		context = a_context;
	}
	
	public String getJSONFromURL(String url) {
		String jsonString = null;
		
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			jsonString = EntityUtils.toString(httpEntity);
		} catch (UnsupportedEncodingException e) {
			
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}
		
		return jsonString;
	}
	
	public String getJSONFromAssets() {
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open("projects.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonString;
    }
	
	
}
