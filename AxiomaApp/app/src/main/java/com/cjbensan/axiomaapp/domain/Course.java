package com.cjbensan.axiomaapp.domain;

public class Course {

    private final int thumbnail;
    private final String name;
    private String teacher;
    private String classes;
    private String exercises;

    public Course(int thumbnail, String name, String teacher, String classes, String exercises) {
        this.thumbnail = thumbnail;
        this.name = name;
        this.teacher = teacher;
        this.classes = classes;
        this.exercises = exercises;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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
