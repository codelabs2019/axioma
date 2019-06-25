package com.cjbensan.axiomaapp.domain;

public class Topic {

    private String number;
    private String name;
    private String classes;
    private String exercises;

    public Topic(String number, String name, String classes, String exercises) {
        this.number = number;
        this.name = name;
        this.classes = classes;
        this.exercises = exercises;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getExercises() {
        return exercises;
    }

    public void setExercises(String exercises) {
        this.exercises = exercises;
    }
}
