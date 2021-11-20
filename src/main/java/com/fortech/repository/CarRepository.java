package com.fortech.repository;

import com.fortech.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    List<Car> findByManufacturerContaining(String manufacturer);
    List<Car> findByAssured(boolean assured);
}
