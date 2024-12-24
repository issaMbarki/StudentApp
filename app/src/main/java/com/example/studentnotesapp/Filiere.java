package com.example.studentnotesapp;

import java.util.List;

public class Filiere {
    private final String nomFiliere;
    private final String description;
    private final List<Student> students;

    public Filiere(String nomFiliere, String description, List<Student> students) {
        this.nomFiliere = nomFiliere;
        this.description = description;
        this.students = students;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public List<Student> getStudents() {
        return students;
    }

    public String getDescription() {
        return description;
    }
}
