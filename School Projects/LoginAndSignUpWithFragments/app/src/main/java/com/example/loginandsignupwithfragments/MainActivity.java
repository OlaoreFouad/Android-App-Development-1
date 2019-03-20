package com.example.loginandsignupwithfragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.loginandsignupwithfragments.fragments.LoginFragment;
import com.example.loginandsignupwithfragments.listeners.OnFragmentChangeListener;

public class MainActivity extends AppCompatActivity implements OnFragmentChangeListener {
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    public void initialize() {
        setFragment(new LoginFragment(), "loginFragment", false);
    }

    public void setFragment(Fragment fragment, String tag, boolean addToBackStack) {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragmentContainerId, fragment);

        if (addToBackStack) {
            mFragmentTransaction.addToBackStack(tag);
        }

        mFragmentTransaction.commit();
    }

    @Override
    public void change(Fragment fragment, String tag, boolean addToBackStack) {
        setFragment(fragment, tag, addToBackStack);
    }
}
