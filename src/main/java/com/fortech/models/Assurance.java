package com.fortech.models;

import org.springframework.data.annotation.Id;

public class Assurance {
    @Id
    private String id;

    private String title;
    private String description;

    private Integer defaultPeriod;

    public Assurance() {
    }

    public Assurance(String title, String description, Integer defaultPeriod) {
        this.title = title;
        this.description = description;
        this.defaultPeriod = defaultPeriod;
    }

    public Assurance(Assurance assurance){
        this.id= assurance.getId();
        this.title = assurance.getTitle();
        this.description = assurance.getDescription();
        this.defaultPeriod = assurance.getDefaultPeriod();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDefaultPeriod() {
        return defaultPeriod;
    }

    public void setDefaultPeriod(Integer defaultPeriod) {
        this.defaultPeriod = defaultPeriod;
    }

    @Override
    public String toString() {
        return "Assurance{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", defaultPeriod=" + defaultPeriod +
                '}';
    }
}
