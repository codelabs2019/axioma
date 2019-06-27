package com.cjbensan.axiomaapp.domain;

public class CourseExercise {

    private final int thumbnail;
    private final String name;

    public CourseExercise(int thumbnail, String name) {
        this.thumbnail = thumbnail;
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }
}
