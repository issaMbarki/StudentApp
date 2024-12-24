package com.example.studentnotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btnPrevious, btnNext;
    private int currentIndex = 0;
    private TextView studentName;
    private ImageView studentImage;
    private TextView studentMoyenne;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentIndex = getIntent().getIntExtra("position", 0);
        //Student student = (Student) getIntent().getSerializableExtra("student");
        students = (ArrayList<Student>) getIntent().getSerializableExtra("students");


        //recycler view
//        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
//        NotesRecyvlerViewAdapter adapter = new NotesRecyvlerViewAdapter(this, students.get(currentIndex).getSubjectsGrades());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);


        // Ajouter les événements des boutons
        btnPrevious.setOnClickListener(v -> showPreviousStudent());
        btnNext.setOnClickListener(v -> showNextStudent());

        studentName = (TextView) findViewById(R.id.studentName);
        studentImage = (ImageView) findViewById(R.id.studentImage);
        studentMoyenne = (TextView) findViewById(R.id.moyenne);
        showStudent(currentIndex);
    }


    // Méthode pour afficher les informations d'un étudiant
    private void showStudent(int index) {
        if (students.isEmpty()) return;
        studentName.setText(students.get(index).getFullName());
        studentMoyenne.setText(students.get(index).calculMoyenne().toString() + "/20");
        // Charger l'image avec Picasso
        Picasso.get()
                .load(students.get(index).getImage())
                // .placeholder(R.drawable.placeholder) // Optionnel : image de chargement
                //.error(R.drawable.error) // Optionnel : image en cas d'erreur
                .into(studentImage);
        //recycler view
        RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
        NotesRecyvlerViewAdapter adapter = new NotesRecyvlerViewAdapter(this, students.get(currentIndex).getSubjectsGrades());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Afficher l'étudiant précédent
    private void showPreviousStudent() {
        if (currentIndex > 0) {
            currentIndex--;
            showStudent(currentIndex);
        } else {
            Toast.makeText(this, "Premier étudiant atteint", Toast.LENGTH_SHORT).show();
        }
    }

    // Afficher l'étudiant suivant
    private void showNextStudent() {
        if (currentIndex < students.size() - 1) {
            currentIndex++;
            showStudent(currentIndex);
        } else {
            Toast.makeText(this, "Dernier étudiant atteint", Toast.LENGTH_SHORT).show();
        }
    }
}