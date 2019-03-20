package com.example.bottomnavigationprojectone.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bottomnavigationprojectone.R;
import com.example.bottomnavigationprojectone.adapters.ProfileTabsAdapter;
import com.example.bottomnavigationprojectone.dialogs.ColorDialog;
import com.example.bottomnavigationprojectone.listeners.OnColorSelectedListener;
import com.example.bottomnavigationprojectone.util.Utils;

public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProfileTabsAdapter adapter;

    private Button selectColorButton;

    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tabLayout = view.findViewById(R.id.tabsId);
        viewPager = view.findViewById(R.id.profile_fragment_container);
        selectColorButton = view.findViewById(R.id.selectColorButtonId);

        adapter = new ProfileTabsAdapter(getFragmentManager());

        adapter.add("Posts", new PostsFragment());
        adapter.add("Following", new FollowingFragment());
        adapter.add("Followers", new FollowersFragment());

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        Bundle arguments = this.getArguments();

        if (arguments != null) {
            int colorcode = arguments.getInt("colorcode");
            toolbar = view.findViewById(R.id.toolbarId);


            switch (colorcode) {
                case 1: {
                    configure(Color.RED);
                }
                break;
                case 2: {
                    configure(Color.BLUE);
                }
                break;
                case 3: {
                    configure(Color.GREEN);
                }
                break;
            }

        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        selectColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display();
            }
        });
    }

    private void display() {
        DialogFragment dialog = new ColorDialog();
        dialog.show(getFragmentManager(), Utils.TAG);
    }

    private void configure(int color) {
        toolbar.setBackgroundColor(color);
        tabLayout.setBackgroundColor(color);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
    }
}
