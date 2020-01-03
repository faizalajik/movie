package com.example.film;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.view_pager);
        tab.setTabTextColors(Color.parseColor("#1ca2b7"), Color.parseColor("#B71C1C"));

        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabMode(TabLayout.MODE_FIXED);

        HomeAdapter homeAdapter = new HomeAdapter(this, getSupportFragmentManager());
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
        return super.onOptionsItemSelected(item);
    }
}
