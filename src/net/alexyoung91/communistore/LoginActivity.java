package net.alexyoung91.communistore;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	public final static String EXTRA_EMAIL = "net.alexyoung91.communistore.EXTRA_EMAIL";
	public final static String EXTRA_PASSWORD = "net.alexyoung91.communistore.EXTRA_PASSWORD";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.hide();
		setContentView(R.layout.activity_login);
	}
	
	public void login(View view) {
		Intent intent = new Intent(this, HomeScreenActivity.class);
		EditText editTextEmail = (EditText)findViewById(R.id.edit_email);
		EditText editTextPassword = (EditText)findViewById(R.id.edit_password);
		String username = editTextEmail.getText().toString();
		String password = editTextPassword.getText().toString();
		intent.putExtra(EXTRA_EMAIL, username);
		intent.putExtra(EXTRA_PASSWORD, password);
		startActivity(intent);
	}
	
	public void register(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
}
