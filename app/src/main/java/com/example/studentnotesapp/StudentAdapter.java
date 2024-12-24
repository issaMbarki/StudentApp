package com.example.studentnotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private final List<Student> students;
    private final Context context;

    public interface OnStudentClickListener {
        void onStudentClick(int position);
    }

    private final OnStudentClickListener listener;

    public StudentAdapter(Context context, List<Student> students, OnStudentClickListener listener) {
        this.students = students;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.etudiant_row, parent, false);
        return new StudentAdapter.StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentHolder holder, int position) {
        String studentFullName = students.get(position).getFullName();
        if (studentFullName.length() > 18) {
            studentFullName = studentFullName.substring(0, 18).concat("...");
        }

        holder.StudentName.setText(studentFullName);
        holder.StudentMoyenne.setText(students.get(position).calculMoyenne().toString() + "/20");
        holder.StudentImage.setImageResource(R.drawable.ic_launcher_foreground);
        holder.itemView.setOnClickListener(v -> listener.onStudentClick(position));

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class StudentHolder extends RecyclerView.ViewHolder {
        ImageView StudentImage;
        TextView StudentMoyenne;
        TextView StudentName;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);
            StudentImage = itemView.findViewById(R.id.etudiantImage);
            StudentMoyenne = itemView.findViewById(R.id.etudiantMoyenne);
            StudentName = itemView.findViewById(R.id.etudiantNomPrenom);

        }
    }
}
