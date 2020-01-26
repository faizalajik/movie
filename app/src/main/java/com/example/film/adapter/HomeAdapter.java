package com.example.film.adapter;


import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.film.R;
import com.example.film.view.MoviesFragment;
import com.example.film.view.TvShowFragment;

public class HomeAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final String state;


    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_title1,
            R.string.tab_title2
    };
    @StringRes
    private final int[] TAB_TITLES2 = new int[]{
            R.string.tab_title3,
            R.string.tab_title4
    };

    public HomeAdapter(Context context, FragmentManager fm, String state) {
        super(fm);
        this.context = context;
        this.state = state;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MoviesFragment(state);
        } else {
            return new TvShowFragment(state);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (state.equals("home")) {
            return context.getResources().getString(TAB_TITLES[position]);
        } else {
            return context.getResources().getString(TAB_TITLES2[position]);
        }
    }
}
