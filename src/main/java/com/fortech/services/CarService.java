package com.fortech.services;

import com.fortech.models.Car;
import com.fortech.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().addAll(cars);
        return cars;
    }
    public List<Car> findByManufacturer(String manufacturer){
        List<Car> cars = new ArrayList<>();
        carRepository.findByManufacturerContaining(manufacturer).addAll(cars);
        return cars;
    }

    public Optional<Car> findById(String id){
        return carRepository.findById(id);
    }

    public Car addNewCar(Car car){
        return carRepository.save(new Car(car.getPlate(),car.getManufacturer(),false));
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
