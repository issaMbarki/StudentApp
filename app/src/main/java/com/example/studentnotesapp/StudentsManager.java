package com.example.studentnotesapp;


import java.util.List;
import java.util.Map;

public class StudentsManager {
    private final List<Student> sudents;

    public StudentsManager(Map<String, Double> notes, List<Student> sudents) {
        this.sudents = sudents;

    }

}
