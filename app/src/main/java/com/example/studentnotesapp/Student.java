package com.example.studentnotesapp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Student implements Serializable {
    private final String nom;
    private final String prenom;
    private final String filiere;
    private final String image;
    //private final Double moyenne;
    private final List<SubjectGrades> subjectsGrades;
    //private final Map<String, Double> notes;

    public Student(String nom, String prenom, String filiere, List<SubjectGrades> subjectsGrades) {
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.image = generateImage();
        this.subjectsGrades = subjectsGrades;
        //this.moyenne = claclulMoyenne(subjectsGrades);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getFiliere() {
        return filiere;
    }


    public Double calculMoyenne() {
        double moyenne = 0.0;
        for (SubjectGrades subjectsGrade : subjectsGrades
        ) {
            moyenne += subjectsGrade.getNote() / subjectsGrades.size();
        }
        return Math.round(moyenne * 100.0) / 100.0;
    }

    public List<SubjectGrades> getSubjectsGrades() {
        return subjectsGrades;
    }

    public String getImage() {
        return image;
    }

    private String generateImage() {
        int random = new Random().nextInt(5) + 1;
        return "https://randomuser.me/api/portraits/med/men/" + random + ".jpg";
    }

    public String getFullName() {
        return nom + " " + prenom;
    }
}
