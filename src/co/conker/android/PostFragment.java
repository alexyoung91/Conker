package co.conker.android;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class PostFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_post, container, false);
		
		final View button = view.findViewById(R.id.button_loc_auto);
	    button.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	DialogFragment newFragment = new DatePickerFragment();
	                newFragment.show(getFragmentManager(), "datePicker");
	            }
	        }
	    );
        
        return view;
    }
}
