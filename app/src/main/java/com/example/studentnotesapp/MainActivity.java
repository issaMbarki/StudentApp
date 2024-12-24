package com.example.studentnotesapp;

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
    private ArrayList<Student> students = new ArrayList<>();
    private int currentIndex = 0;
    private TextView studentName;
    private ImageView studentImage;
    private TextView studentMoyenne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Charger les données depuis l'API
        fetchStudentData();

        // Ajouter les événements des boutons
        btnPrevious.setOnClickListener(v -> showPreviousStudent());
        btnNext.setOnClickListener(v -> showNextStudent());

        studentName = (TextView) findViewById(R.id.studentName);
        studentImage = (ImageView) findViewById(R.id.studentImage);
        studentMoyenne = (TextView) findViewById(R.id.moyenne);
    }

    // Méthode pour récupérer les données via l'API
    private void fetchStudentData() {
        String url = "http://159.223.212.217:3000/students"; // URL locale pour JSON Server
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject studentJson = response.getJSONObject(i);
                    String nom = studentJson.getString("nom");
                    String prenom = studentJson.getString("prenom");
                    String filiere = studentJson.getString("filiere");
                    List<SubjectGrades> subjectsGrades = new ArrayList<>();
                    JSONObject notesJson = studentJson.getJSONObject("notes");
                    for (Iterator<String> it = notesJson.keys(); it.hasNext(); ) {
                        String subjectName = it.next();
                        double value = notesJson.getDouble(subjectName);
                        subjectsGrades.add(new SubjectGrades(subjectName, value));
                    }
                    students.add(new Student(nom, prenom, filiere, subjectsGrades));
                }

                // Afficher le premier étudiant
                showStudent(currentIndex);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, "Erreur de récupération des données" + error, Toast.LENGTH_LONG).show();
        });

        queue.add(jsonArrayRequest);
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