package com.example.tabsprojectone;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerTabAdapter viewPagerTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);

        viewPagerTabAdapter = new ViewPagerTabAdapter(getSupportFragmentManager());

        viewPagerTabAdapter.add("Tabs Quiz", new TabsFragment());
        viewPagerTabAdapter.add("Explore", new TabsFragment());
        viewPagerTabAdapter.add("Store", new TabsFragment());

        viewPager.setAdapter(viewPagerTabAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
