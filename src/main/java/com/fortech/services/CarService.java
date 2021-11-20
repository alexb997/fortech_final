package com.fortech.services;

import com.fortech.models.Car;
import com.fortech.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> findAll(){
        List<Car> cars = new ArrayList<Car>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }
    public List<Car> findByManufacturer(String manufacturer){
        List<Car> cars = new ArrayList<Car>();
        carRepository.findByManufacturerContaining(manufacturer).forEach(cars::add);
        return cars;
    }

    public Optional<Car> findById(String id){
        return carRepository.findById(id);
    }

}
