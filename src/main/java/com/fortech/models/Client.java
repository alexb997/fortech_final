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
    private List<String> ownedCars;
    private Long phone;
    private String address;
    private String banking;

    public Client(){}

    public Client(String username) {
        this.username = username;
    }

    public Client(String username, List<String> ownedCars, Long phone, String address, String banking) {
        this.username = username;
        this.ownedCars = ownedCars;
        this.phone = phone;
        this.address = address;
        this.banking = banking;
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

    public List<String> getOwnedCars() {
        return ownedCars;
    }

    public void setOwnedCars(List<String> ownedCars) {
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

    public String getBanking() {
        return banking;
    }

    public void setBanking(String banking) {
        this.banking = banking;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", ownedCars=" + ownedCars +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", banking='" + banking + '\'' +
                '}';
    }
}
