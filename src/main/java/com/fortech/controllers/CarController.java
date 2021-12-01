package com.fortech.controllers;

import com.fortech.models.Car;
import com.fortech.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cars")
public class CarController {

    final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(@RequestParam(required = false) String manufacturer) {
        try {
            List<Car> cars;
            if(manufacturer==null) {
                cars = carService.findAll();
            }else{
                cars = carService.findByManufacturer(manufacturer);
            }
            return cars;
        } catch (Exception e) {
            //Gandeste mesaj, clientul nu trebuie sa stie probleme la db
            throw new IllegalArgumentException("No cars found");
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") String id) {
        Optional<Car> carData = carService.findById(id);

        return carData.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            Car newCar = carService.addNewCar(car);
            if(car.getPlate() != null && !car.getPlate().isEmpty()
                    && car.getManufacturer() != null && !car.getManufacturer().isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        Optional<Car> carData = carService.findById(id);

        if (carData.isPresent()) {
            Car newCar = carData.get();
            newCar.setPlate(car.getPlate());
            newCar.setManufacturer(car.getManufacturer());
            newCar.setAssured(car.isAssured());
            return new ResponseEntity<>(carService.updateCar(newCar), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") String id) {
        try {
            carService.removeCarById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<HttpStatus> deleteAllCars() {
        try {
            carService.removeAllCars();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/assured")
    public ResponseEntity<List<Car>> findByAssured(@RequestParam boolean assured) {
        try {
            List<Car> cars = carService.findByAssurance(assured);

            if (cars.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cars, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
