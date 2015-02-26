package co.conker.android;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class HomeScreenActivity extends FragmentActivity implements ActionBar.TabListener {

	private ViewPager viewPager;
    private TabsPagerAdapter adapter;
    private ActionBar actionBar;
    private String[] tabs = { "Post", "Projects", "My Projects" };
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        
        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar = getActionBar();
        adapter = new TabsPagerAdapter(getSupportFragmentManager());
        
        viewPager.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Adding Tabs
        for (String tabName : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tabName).setTabListener(this));
        }
        
        /**
         * on swiping the viewpager make respective tab selected
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            	//
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            	//
            }
        });
        
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.EXTRA_EMAIL);
        String password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD);
        
        // Projects as the default page
        viewPager.setCurrentItem(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	Log.d("conker", "test1");
    	
        switch (item.getItemId()) {
        	/*
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
            */
	        case R.id.action_search:
	        	//Search for a Project
	        	break;
            case R.id.action_refresh:
            	// Refresh Project List
            	break;
            case R.id.action_share:
            	// Share your project or somebody elses
            	break;
            case R.id.action_settings:
            	// Show settings (e.g. accessibility etc)
            	break;
            case R.id.action_logout: {
            	
            	AsyncHttpClient client = new AsyncHttpClient();
    			client.post(this.getApplicationContext(), "http://192.168.0.10:8080/logout", null, "application/json", new AsyncHttpResponseHandler() {

    				@Override
    			    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
    			        // called when response HTTP status is "200 OK"
    			    	JSONObject responseObject;
    					try {
    						responseObject = new JSONObject(new String(response));
    						
    						

    						boolean status = responseObject.getBoolean("status");
    						
    						Log.d("conker", "test2: " + status);
    						
    						if (status) {
    							//Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
    							//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    							//startActivity(intent);
    							finish();
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
    			    	Toast toast = Toast.makeText(getApplicationContext(), "Error logging in", Toast.LENGTH_SHORT);
    			    	toast.show();
    			    }
    			});
            	
            	break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

	@Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	//
    }
    
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    	//
    }
}
