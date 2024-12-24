package com.example.studentnotesapp;

public class SubjectGrades {
    private final String subjectName;
    private final double note;

    public SubjectGrades(String subjectName, double note) {
        this.subjectName = subjectName;
        this.note = note;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Double getNote() {
        return note;
    }
}
