package net.alexyoung91.communistore;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MyProjectsFragment extends ListFragment {
	
	static final String URL = "http://brunellms.com/communistore/projects.json";
	
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title"; // PARENT NODE ???
	static final String KEY_DESCRIPTION = "desc";
	static final String KEY_AUTHOR = "author";
	static final String KEY_LOCATION = "location";
	static final String KEY_THUMB_URL = "thumb";
	
	static final String NAME = "Alex Young";
	
	ListView list;
	ViewProjectsAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ArrayList<HashMap<String, String>> projectsList = new ArrayList<HashMap<String, String>>();
		
		try {
			// Networking needs to be in a separate thread - maybe JSONFetcher can use a callback and display a loading image until then
			JSONFetcher fetcher = new JSONFetcher(getActivity());
			//String json = fetcher.getJSONFromURL(URL);
			String json = fetcher.getJSONFromAssets();
						
			JSONObject jsonObject = new JSONObject(json);
			JSONArray jsonArray = jsonObject.getJSONArray("projects");
			
			for (int i = 0; i < jsonArray.length(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				JSONObject o = jsonArray.getJSONObject(i);
				
				map.put(KEY_ID, o.getString(KEY_ID));
				map.put(KEY_TITLE, o.getString(KEY_TITLE));
				map.put(KEY_DESCRIPTION, o.getString(KEY_DESCRIPTION));
				map.put(KEY_AUTHOR, o.getString(KEY_AUTHOR));
				map.put(KEY_LOCATION, o.getString(KEY_LOCATION));
				map.put(KEY_THUMB_URL, o.getString(KEY_THUMB_URL));
				
				if (o.getString(KEY_AUTHOR).equals(NAME))
					projectsList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		adapter = new ViewProjectsAdapter(getActivity(), projectsList);
		setListAdapter(adapter);
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Toast toast = Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT);
		toast.show();
	}
}