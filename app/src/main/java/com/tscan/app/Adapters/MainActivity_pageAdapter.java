package com.tscan.app.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.tscan.app.Fragments.Fragment_dashboard;
import com.tscan.app.Fragments.Fragment_performance;
import com.tscan.app.Fragments.Fragment_settings;

public class MainActivity_pageAdapter extends FragmentPagerAdapter {
    private boolean enabled;


    public MainActivity_pageAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.enabled = true;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment_performance();
            case 1:
                return new Fragment_dashboard();
            case 2:
                return new Fragment_settings();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}
