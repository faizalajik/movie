package com.example.film.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.film.R;
import com.example.film.adapter.HomeAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        state = getIntent().getStringExtra("stats");
        tab = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.view_pager);
        tab.setTabTextColors(Color.parseColor("#1ca2b7"), Color.parseColor("#B71C1C"));

        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabMode(TabLayout.MODE_FIXED);

        HomeAdapter homeAdapter = new HomeAdapter(this, getSupportFragmentManager(), state);
        vp.setAdapter(homeAdapter);
        tab.setupWithViewPager(vp);
        getSupportActionBar().setElevation(0);
    }
}
