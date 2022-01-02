package com.fortech.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car {

    @Id
    private String id;
    private String name;
    private String kind;
    private String brand;
    //type is a taken keyword in mongo, believe it expects a object
    //add manufacturer icon Ex: Ferrari
    //Maybe a ENUM with type of cars?
    //Try add image

    //add details - are they needed?

    public Car(){}

    public Car(String name, String brand, String kind) {
        this.name = name;
        this.brand = brand;
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "ID:"+ id +'\'' +
                "The car by type "+ kind +
                ", brand " + brand;
    }
}
