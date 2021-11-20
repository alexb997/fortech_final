package com.fortech.repository;

import com.fortech.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findByManufacturerContaining(String manufacturer);
    List<Car> findByAssured(boolean assured);
}
