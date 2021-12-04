package com.fortech.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String username;
    private List<Car> ownedCars;

    public Client(){}

    public Client(String username) {
        this.username = username;
    }

    public Client(String username, List<Car> ownedCars) {
        this.username = username;
        this.ownedCars = ownedCars;
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

    public List<Car> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<Car> ownedCars) {
        this.ownedCars = ownedCars;
    }

    @Override
    public String toString() {
        return "ID:"+ id +'\'' +
                "This client "+ username +
                " owns the cars " + ownedCars;
    }
}
