package com.example.film.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.film.R;
import com.example.film.adapter.HomeAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private String state = "home";
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.view_pager);
        tab.setTabTextColors(Color.parseColor("#1ca2b7"), Color.parseColor("#B71C1C"));

        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabMode(TabLayout.MODE_FIXED);

        if (savedInstanceState != null && state == null) {
            state = savedInstanceState.getString("state");
        }
        homeAdapter = new HomeAdapter(this, getSupportFragmentManager(), state);
        vp.setAdapter(homeAdapter);
        tab.setupWithViewPager(vp);
        getSupportActionBar().setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        if (item.getItemId() == R.id.favorite) {
            state = "fav";
            Intent intent = new Intent(this, FavoriteActivity.class);
            intent.putExtra("stats", state);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("state", state);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        state = savedInstanceState.getString("state");
        homeAdapter = new HomeAdapter(this, getSupportFragmentManager(), state);
    }
}
