package com.example.todolistapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolistapp.R;
import com.example.todolistapp.models.Todo;

import java.util.List;

public class TodoAdpater extends RecyclerView.Adapter<TodoAdpater.ViewHolder> {

    private Context context;
    private List<Todo> todoList;

    public TodoAdpater(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.todo, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Todo todo = todoList.get(i);

        viewHolder.title.setText(todo.getTitle());
        viewHolder.description.setText(todo.getDescription());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        android.widget.TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todoTitleId);
            description = itemView.findViewById(R.id.todoDescriptionId);
        }
    }

}
