package com.example.bottomnavigationprojectone;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bottomnavigationprojectone.fragments.ExploreFragment;
import com.example.bottomnavigationprojectone.fragments.HomeFragment;
import com.example.bottomnavigationprojectone.fragments.ProfileFragment;
import com.example.bottomnavigationprojectone.listeners.OnColorSelectedListener;
import com.example.bottomnavigationprojectone.util.Utils;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, OnColorSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Fragment selectedFragment = null;

        switch (id) {
            case R.id.homeId: {
                selectedFragment = new HomeFragment();
            }
            break;
            case R.id.exploreId: {
                selectedFragment = new ExploreFragment();
            }
            break;
            case R.id.profileId: {
                selectedFragment = new ProfileFragment();
            }
            break;
        }

        setFragment(selectedFragment);

        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_id, fragment);

        transaction.commit();
    }

    private void setFragment(Fragment fragment, int colorcode) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_id, fragment);

        Bundle bundle = new Bundle();
        bundle.putInt("colorcode", colorcode);

        fragment.setArguments(bundle);

        transaction.commit();
    }

    @Override
    public void colorSelected(int colorcode) {
        Log.i(Utils.TAG, "colorSelected: " + colorcode);

        setFragment(new ProfileFragment(), colorcode);
    }
}
