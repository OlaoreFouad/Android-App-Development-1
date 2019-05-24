package com.example.contactsapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.contactsapp.adapters.GroupsAdapter;
import com.example.contactsapp.listeners.OnGroupSelectedListener;
import com.example.contactsapp.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class SelectGroupsActivity extends AppCompatActivity {

    private RecyclerView groupsRecyclerView;
    private GroupsAdapter adapter;
    private List<String> groups;

    private List<String> finalGroups;

    private OnGroupSelectedListener mOnGroupSelectedListener;
    private Button leftArrowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(Util.TAG, "onCreate: hey fam!");
        setContentView(R.layout.activity_select_groups);

        groupsRecyclerView = findViewById(R.id.groupsRecyclerViewId);
        leftArrowButton = findViewById(R.id.leftArrowId);
        groups = new ArrayList<>();
        finalGroups = new ArrayList<>();

        initialize();

        mOnGroupSelectedListener = new OnGroupSelectedListener() {
            @Override
            public void groupSelected(String group) {
                Log.i(Util.TAG, "groupSelected: " + group);
                finalGroups.add(group);
            }

            @Override
            public void groupRemoved(String group) {
                Log.i(Util.TAG, "groupRemoved: " + group);
                finalGroups.remove(group);
            }
        };

        final Intent intent = getIntent();

        leftArrowButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Log.i(Util.TAG, "onClick: it is getting here!");
                String[] s = new String[finalGroups.size()];
                intent.putExtra("groups", Util.join(", ", finalGroups.toArray(s)));
                String s1 = Util.join(", ", finalGroups.toArray(s));
                Log.i(Util.TAG, "onClick: Joined String :" + s1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        groupsRecyclerView.setHasFixedSize(true);
        groupsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new GroupsAdapter(this, groups, this.mOnGroupSelectedListener);
        groupsRecyclerView.setAdapter(adapter);

    }

    private void initialize() {
        groups.add("ICE - emergency contacts");
        groups.add("Co-workers");
        groups.add("Family");
        groups.add("Friends");
        groups.add("My contacts");
        groups.add("Special");
        groups.add("Team mates");
        groups.add("Enemies");
    }
}
