package com.example.filestorageprojectone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context context;
    private List<Student> students;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.student_row, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Student student = students.get(i);

        viewHolder.name.setText(student.getName());
        viewHolder.score.setText(Integer.toString(student.getScore()));
        viewHolder.date.setText(student.getDate());
        viewHolder.id.setText(String.format("%d", i+1));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        android.widget.TextView id, name, score, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.studentNameId);
            score = itemView.findViewById(R.id.studentScoreId);
            id = itemView.findViewById(R.id.studentId);
            date = itemView.findViewById(R.id.studentDateId);
        }
    }

}
