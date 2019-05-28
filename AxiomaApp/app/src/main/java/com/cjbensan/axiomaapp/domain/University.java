package com.cjbensan.axiomaapp.domain;

public class University {
    private final String name;
    private final int thumbnail;

    public University(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
