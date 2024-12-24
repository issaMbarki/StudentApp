package com.example.studentnotesapp;

import java.io.Serializable;

public class SubjectGrades implements Serializable {
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
