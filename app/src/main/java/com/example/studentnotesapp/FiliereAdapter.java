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

public class FiliereAdapter extends RecyclerView.Adapter<FiliereAdapter.FiliereHolder> {
    private final List<Filiere> filieres;
    private final Context context;

    public FiliereAdapter(Context context, List<Filiere> filieres) {
        this.filieres = filieres;
        this.context = context;
    }

    @NonNull
    @Override
    public FiliereAdapter.FiliereHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_row, parent, false);
        return new FiliereAdapter.FiliereHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FiliereAdapter.FiliereHolder holder, int position) {
        String nomFiliere = filieres.get(position).getNomFiliere();
        if (nomFiliere.length() > 18) {
            nomFiliere = nomFiliere.substring(0, 18).concat("...");
        }
        holder.filiereName.setText(nomFiliere);
        holder.filiereDescription.setText(filieres.get(position).getDescription());
        holder.filiereImg.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return filieres.size();
    }

    public static class FiliereHolder extends RecyclerView.ViewHolder {
        ImageView filiereImg;
        TextView filiereDescription;
        TextView filiereName;

        public FiliereHolder(@NonNull View itemView) {
            super(itemView);
            filiereImg = itemView.findViewById(R.id.filiereImage);
            filiereDescription = itemView.findViewById(R.id.textView);
            filiereName = itemView.findViewById(R.id.filiereName);

        }
    }
}