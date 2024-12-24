package com.example.studentnotesapp;

import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FiliereActivity extends AppCompatActivity {
    private ArrayList<Filiere> filieres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filiere);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fetchStudentData();
    }

    private void fetchStudentData() {
        String url = "http://159.223.212.217:3000/filieres"; // URL locale pour JSON Server
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    List<Student> students = new ArrayList<>();
                    JSONObject filiereJson = response.getJSONObject(i);
                    String nomFiliere = filiereJson.getString("nom");
                    String descriptionFiliere = filiereJson.getString("description");
                    JSONArray studentsArray = filiereJson.getJSONArray("students");
                    // Iterate over the students JSONArray
                    for (int j = 0; j < studentsArray.length(); j++) {
                        JSONObject studentJson = studentsArray.getJSONObject(j); // Get the current student as JSONObject


                        String nom = studentJson.getString("nom");
                        String prenom = studentJson.getString("prenom");
                        JSONObject notesJson = studentJson.getJSONObject("notes");

                        List<SubjectGrades> subjectsGrades = new ArrayList<>();
                        for (Iterator<String> it = notesJson.keys(); it.hasNext(); ) {
                            String subjectName = it.next();
                            double value = notesJson.getDouble(subjectName);
                            subjectsGrades.add(new SubjectGrades(subjectName, value));
                        }
                        students.add(new Student(nom, prenom, "filiere", subjectsGrades));
                    }

                    filieres.add(new Filiere(nomFiliere, descriptionFiliere, students));
                }

                //recycler view
                RecyclerView recyclerView = findViewById(R.id.notesRecyclerView);
                FiliereAdapter adapter = new FiliereAdapter(this, filieres);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(this, "Erreur de récupération des données" + error, Toast.LENGTH_LONG).show();
        });

        queue.add(jsonArrayRequest);
    }
}