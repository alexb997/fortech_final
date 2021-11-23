package com.fortech.services;

import com.fortech.models.Car;
import com.fortech.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }
    public List<Car> findByManufacturer(String manufacturer){
        return carRepository.findByManufacturerContaining(manufacturer);
    }

    public Optional<Car> findById(String id){
        return carRepository.findById(id);
    }

    public Car addNewCar(Car car) throws IllegalArgumentException{
        if(car.getPlate() != null && !car.getPlate().isEmpty()
                && car.getManufacturer() != null && !car.getManufacturer().isEmpty()) {
            return carRepository.save(new Car(car.getPlate(), car.getManufacturer()));
        }
        throw new IllegalArgumentException("Invalid values for Car properties");
    }

    public Car updateCar(Car car){
        return carRepository.save(car);
    }

    public void removeCarById(String id){
        carRepository.deleteById(id);
    }

    public void removeAllCars(){
        carRepository.deleteAll();
    }

    public List<Car> findByAssurance(boolean assured) {
        return carRepository.findByAssured(assured);
    }
}
