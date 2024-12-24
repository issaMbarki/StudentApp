package com.example.studentnotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesRecyvlerViewAdapter extends RecyclerView.Adapter<NotesRecyvlerViewAdapter.NoteHolder> {
    private final List<SubjectGrades> subjectGrades;
    private final Context context;

    public NotesRecyvlerViewAdapter(Context context, List<SubjectGrades> subjectGrades) {
        this.subjectGrades = subjectGrades;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesRecyvlerViewAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_row, parent, false);
        return new NotesRecyvlerViewAdapter.NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyvlerViewAdapter.NoteHolder holder, int position) {
        String subjectName = subjectGrades.get(position).getSubjectName();
        if (subjectName.length() > 18) {
            subjectName = subjectName.substring(0, 18).concat("...");
        }
        holder.subjectName.setText(subjectName);
        holder.noteSurVingt.setText(subjectGrades.get(position).getNote().toString() + "/20");
        holder.subjectImg.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return subjectGrades.size();
    }

    public static class NoteHolder extends RecyclerView.ViewHolder {
        ImageView subjectImg;
        TextView subjectName;
        TextView noteSurVingt;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            subjectImg = itemView.findViewById(R.id.imageView);
            subjectName = itemView.findViewById(R.id.textView);
            noteSurVingt = itemView.findViewById(R.id.noteSurVingt);

        }
    }
}
