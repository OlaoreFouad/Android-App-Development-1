package com.example.bottomnavigationprojectone.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnavigationprojectone.R;
import com.example.bottomnavigationprojectone.adapters.ItemAdapter;
import com.example.bottomnavigationprojectone.models.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Item> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.itemListId);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        items = new ArrayList<>();
        initialize();

        return view;
    }

    private void initialize() {

        items.add(new Item("Olaore Fouad", "I love android", Color.BLUE));
        items.add(new Item("Ogunneye Ewaoluwa", "I love angular", Color.RED));
        items.add(new Item("Alli Ibraheem", "I love XML", Color.GREEN));
        items.add(new Item("Resident Virus", "I love hacking", Color.BLACK));
        items.add(new Item("Omoruyi Samuel", "I love FIFA", Color.YELLOW));

        ItemAdapter adapter = new ItemAdapter(getContext(), items);

        mRecyclerView.setAdapter(adapter);
    }
}
