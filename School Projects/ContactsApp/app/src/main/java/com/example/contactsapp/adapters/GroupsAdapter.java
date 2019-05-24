package com.example.contactsapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.contactsapp.R;
import com.example.contactsapp.listeners.OnGroupSelectedListener;
import com.example.contactsapp.utils.Util;

import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private Context context;
    private List<String> groups;
    private OnGroupSelectedListener mOnGroupSelectedListener;

    public GroupsAdapter(Context context, List<String> groups, OnGroupSelectedListener mOnGroupSelectedListener) {
        this.context = context;
        this.groups = groups;
        this.mOnGroupSelectedListener = mOnGroupSelectedListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.group_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String group = groups.get(i);

        viewHolder.groupText.setText(group);
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckBox groupText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groupText = itemView.findViewById(R.id.groupNameId);
            groupText.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (this.groupText.isChecked()) {
                Log.i(Util.TAG, "onClick: here in the isChecked method!");
                mOnGroupSelectedListener.groupSelected(groupText.getText().toString());
            } else {
                mOnGroupSelectedListener.groupRemoved(groupText.getText().toString());
            }
        }
    }

}
