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


    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_title1,
            R.string.tab_title2
    };

    public HomeAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MoviesFragment();
        } else {
            return new TvShowFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }
}
