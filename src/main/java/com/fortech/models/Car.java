package com.fortech.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class Car {

    @Id
    private String id;

    private String plate;
    private boolean assured;
    private String manufacturer;

    public Car(){}

    public Car(String plate,String manufacturer) {
        this.plate = plate;
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isAssured() {
        return assured;
    }

    public void setAssured(boolean assured) {
        this.assured = assured;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "ID:"+ id +'\'' +
                "The car with plate number " + plate +
                ", manufactured by " + manufacturer +
                (assured ? " is assured!": "is not assured!");
    }
}
