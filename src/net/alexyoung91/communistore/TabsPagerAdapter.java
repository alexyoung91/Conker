package net.alexyoung91.communistore;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
        switch (index) {
	        case 0:
	            // Post fragment activity
	            return new PostFragment();
	        case 1:
	            // View Projects fragment activity
	            return new ViewProjectsFragment();
	        case 2:
	            // My Projects fragment activity
	            return new MyProjectsFragment();
        }
        return null;
    }
 
    @Override
    public int getCount() {
        return 3;	// 3 Tabs
    }
 
}