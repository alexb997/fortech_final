package com.fortech.models;

public class OwnedCar extends Car{
    private String plate;
    private String VIN;
    //each car can have its own different assurancePlan-> to be checked
    private Assurance chosenAssurance;
    //Check what else is needed... Car status or details like km on board...

    //Probably this one i'll use, but in case i mess up i'll use the other.
    public OwnedCar(Car car, String plate, Assurance chosenAssurance, String VIN) {
        //think, makes new instance of OwnedCar so think this is right: will check later
        this.setName(car.getName());
        this.setKind(car.getKind());
        this.setBrand(car.getBrand());
        this.plate = plate;
        this.chosenAssurance = chosenAssurance;
        this.VIN = VIN;
    }

    public OwnedCar(String name, String brand,String type, String plate, Assurance chosenAssurance) {
        super(name, brand, type);
        this.plate = plate;
        this.chosenAssurance = chosenAssurance;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Assurance getChosenAssurance() {
        return chosenAssurance;
    }

    public void setChosenAssurance(Assurance chosenAssurance) {
        this.chosenAssurance = chosenAssurance;
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
