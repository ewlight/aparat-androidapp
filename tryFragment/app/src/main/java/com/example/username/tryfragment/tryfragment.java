package com.example.username.tryfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

class UsefulInfoFragment extends Fragment {

    public UsefulInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        return rootView;
    }

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        // This determines the fragment for each tab
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new UsefulInfoFragment();
            } else if (position == 1) {
                return new groupFrag();
            } else if (position == 2) {
                return new PengumumanFrag();
            } else {
                return new PengaturanFrag();
            }
        }

        // This determines the number of tabs
        @Override
        public int getCount() {
            return 4;
        }

        // This determines the title for each tab
        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            switch (position) {
                case 0:
                    return mContext.getString(R.string.category_usefulinfo);
                case 1:
                    return mContext.getString(R.string.category_places);
                case 2:
                    return mContext.getString(R.string.category_food);
                case 3:
                    return mContext.getString(R.string.category_nature);
                default:
                    return null;
            }
        }

    }

}