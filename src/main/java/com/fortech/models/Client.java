package com.fortech.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    private String username;
    private List<OwnedCar> ownedCars;
    private Long phone;
    private String address;
    //Check details needed for insurance plans and add
    //
//    Personal information -ok
//    Information about your driving history -how to show a file, docx or pdf kind....
//    Vehicle information- ownedCar stuff
//    Banking information- probably i'll just put 3 criterias: low, balanced, high to not go in details
//    Supplemental information to qualify for discounts -Discounts?


    public Client(){}

    public Client(String username) {
        this.username = username;
    }

    public Client(String username, List<OwnedCar> ownedCars, Long phone, String address) {
        this.username = username;
        this.ownedCars = ownedCars;
        this.phone = phone;
        this.address = address;
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

    public List<OwnedCar> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<OwnedCar> ownedCars) {
        this.ownedCars = ownedCars;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", ownedCars=" + ownedCars +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
