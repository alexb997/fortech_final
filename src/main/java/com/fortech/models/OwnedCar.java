package com.fortech.models;

public class OwnedCar extends Car{
    private String plate;
    private Boolean assured;

    public OwnedCar(Car car, String plate, Boolean assured) {
        this.setName(car.getName());
        this.setKind(car.getKind());
        this.setBrand(car.getBrand());
        this.plate = plate;
        this.assured = assured;
    }

    public OwnedCar(String name, String brand,String kind, String imageURL, String plate, Boolean assured) {
        super(name, brand, kind, imageURL);
        this.plate = plate;
        this.assured = assured;
    }

    public Boolean isAssured() {
        return assured;
    }

    public void setAssured(Boolean assured) {
        this.assured = assured;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "OwnedCar{" +
                "plate='" + plate + '\'' +
                '}';
    }
}
