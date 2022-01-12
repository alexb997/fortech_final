package com.fortech.models;

public class OwnedCar extends Car{
    private String plate;
    //each car can have its own different assurancePlan-> to be checked
    private Boolean assured;
    //Check what else is needed... Car status or details like km on board...

    //Probably this one i'll use, but in case i mess up i'll use the other.
    public OwnedCar(Car car, String plate, Boolean assured) {
        //think, makes new instance of OwnedCar so think this is right: will check later
        this.setName(car.getName());
        this.setKind(car.getKind());
        this.setBrand(car.getBrand());
        this.plate = plate;
        this.assured = assured;
    }

    public OwnedCar(String name, String brand,String kind, String plate, Boolean assured) {
        super(name, brand, kind);
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
