package com.fortech.controllers;

import com.fortech.models.Car;
import com.fortech.services.CarService;
import com.fortech.validators.CarValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cars")
public class CarController {

    final private CarService carService;

    private static final Logger LOGGER=LoggerFactory.getLogger(CarController.class);

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(
                                                @RequestParam(required = false) String by,
                                                @RequestParam(required = false) String keyword,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "3") int size) {
        try {
            List<Car> cars;
            Pageable paging = PageRequest.of(page, size);

            Page<Car> pageCars;
            if(keyword==null || keyword.isEmpty()) {
                pageCars = carService.findAll(paging);
            }else{
                if( by==null || by.isEmpty() ){
                    pageCars = carService.findBy(keyword,paging);
                }else{
                    pageCars= carService.findSome(by,keyword,paging);
                }
            }
            cars = pageCars.getContent();
            Response response = new Response(cars,pageCars.getTotalPages(),pageCars.getTotalElements(),pageCars.getNumber());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Cars not found!",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
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
            if(CarValidator.isValid(car)) {
                return new ResponseEntity<>(newCar, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.info("Couldn't create car!",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") String id, @RequestBody Car car) {
        Optional<Car> carData = carService.findById(id);

        if (carData.isPresent()) {
            Car newCar = carData.get();
            newCar.setName(car.getName());
            newCar.setBrand(car.getBrand());
            newCar.setKind(car.getKind());
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
            LOGGER.info("Car removal didn't work",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cars")
    public ResponseEntity<HttpStatus> deleteAllCars() {
        try {
            carService.removeAllCars();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Cars removal didn't work",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
