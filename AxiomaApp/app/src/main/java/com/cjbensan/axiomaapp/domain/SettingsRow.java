package com.cjbensan.axiomaapp.domain;

public class SettingsRow {
    private final String id;
    private final String label;
    private String value;

    public SettingsRow(String id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
