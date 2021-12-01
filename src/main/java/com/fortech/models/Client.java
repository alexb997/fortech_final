package com.fortech.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String username;
    private String carID;

    public Client(){}

    public Client(String username) {
        this.username = username;
    }

    public Client(String username, String carID) {
        this.username = username;
        this.carID = carID;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    @Override
    public String toString() {
        return "ID:"+ id +'\'' +
                "This client "+ username +
                " owns the car with ID: " + carID;
    }
}
