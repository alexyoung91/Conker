package net.alexyoung91.communistore;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProjectsAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;
	
	public MyProjectsAdapter(Context c, ArrayList<HashMap<String, String>> d) {
		context = c;
		data = d;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(context);
	}
	
	public int getCount() {
		return data.size();
	}
	
	public Object getItem(int position) {
		return position;
	}
	
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		
		if (convertView == null)
			vi = inflater.inflate(R.layout.mp_list_row, parent, false);
		
		TextView title = (TextView)vi.findViewById(R.id.project_title);
		//TextView author = (TextView)vi.findViewById(R.id.project_author);
		TextView description = (TextView)vi.findViewById(R.id.project_description);
		TextView location = (TextView)vi.findViewById(R.id.project_location);
		ImageView thumbnail = (ImageView)vi.findViewById(R.id.thumbnail_image);
		
		HashMap<String, String> project = new HashMap<String, String>();
		project = data.get(position);
		
		// Setting all values in listview
		title.setText(project.get(ViewProjectsFragment.KEY_TITLE));
		//author.setText(project.get(ViewProjectsFragment.KEY_AUTHOR));
		description.setText(project.get(ViewProjectsFragment.KEY_DESCRIPTION));
		location.setText(project.get(ViewProjectsFragment.KEY_LOCATION));
		imageLoader.DisplayImage(project.get(ViewProjectsFragment.KEY_THUMB_URL), thumbnail);
		
		return vi;
	}
}
