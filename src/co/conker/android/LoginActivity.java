package co.conker.android;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class LoginActivity extends Activity {
	public final static String EXTRA_EMAIL = "co.conker.android.EXTRA_EMAIL";
	public final static String EXTRA_PASSWORD = "co.conker.android.EXTRA_PASSWORD";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_login);
	}
	
	public void login(View view) {
		EditText editTextEmail = (EditText)findViewById(R.id.edit_email);
		EditText editTextPassword = (EditText)findViewById(R.id.edit_password); // TODO: change field from text to email? Is this an option?
		
		// debug defaults
		//editTextEmail.setText("test@example.com");
		//editTextPassword.setText("pass123");
		
		String email = editTextEmail.getText().toString();
		String password = editTextPassword.getText().toString();
		
	    try {
	    	JSONObject requestObject = new JSONObject();
	    	requestObject.put("email", email);
	    	requestObject.put("password", password);
	    	
	    	/*
	    	 * TODO: create a class that acts as the HTTPClient that includes the 
	    	 * url to post to with all the routes and then create the objects in each of the
	    	 * places where a http request needs to be made
	    	 * 
	    	 */
	    	
			AsyncHttpClient client = new AsyncHttpClient();
			client.post(
				this.getApplicationContext(),
				"http://192.168.1.4:8080/login",
				new StringEntity(requestObject.toString()),
				"application/json",
				new AsyncHttpResponseHandler() {

			    @Override
			    public void onStart() {
			        // called before request is started
			    }

			    @Override
			    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
			        // called when response HTTP status is "200 OK"
			    	JSONObject responseObject;
					try {
						responseObject = new JSONObject(new String(response));

						boolean status = responseObject.getBoolean("status");
						if (status) {
							Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
							// TODO: pass variables below - how to access?
							//intent.putExtra(EXTRA_EMAIL, email);
							//intent.putExtra(EXTRA_PASSWORD, password);
							startActivity(intent);
						} else {
							Toast toast = Toast.makeText(getApplicationContext(), responseObject.getString("msg"), Toast.LENGTH_SHORT);
					    	toast.show();
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }

			    @Override
			    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
			        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
			    }

			    @Override
			    public void onRetry(int retryNo) {
			        // called when request is retried
				}
			});
	    } catch (JSONException e) {
	    	// TODO Auto-generated catch block
	    //} catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	}
	
	public void register(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
}
