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

public class StudentsActivity extends AppCompatActivity {

    private Button btnPrevious, btnNext;
    private ArrayList<Student> students = new ArrayList<>();
    private int currentIndex = 0;
    private TextView studentName;
    private ImageView studentImage;
    private TextView studentMoyenne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        ArrayList<Student> students = (ArrayList<Student>) getIntent().getSerializableExtra("students");
        //recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEtudiant);
        StudentAdapter adapter = new StudentAdapter(this, students, (position) -> {
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainIntent.putExtra("students", students);
            mainIntent.putExtra("position", position);
            startActivity(mainIntent);

        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}