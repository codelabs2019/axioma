package com.cjbensan.axiomaapp.domain;

public class TopicExercise {

    private final String number;
    private final String name;

    public TopicExercise(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
