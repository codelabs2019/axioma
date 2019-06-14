package com.cjbensan.axiomaapp.domain;

public class University {
    private final int thumbnail;
    private final String code;
    private final String name;
    private String date;

    public University(int thumbnail, String code, String name, String date) {
        this.thumbnail = thumbnail;
        this.code = code;
        this.name = name;
        this.date = date;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
