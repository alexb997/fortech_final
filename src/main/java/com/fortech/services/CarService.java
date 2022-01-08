package com.fortech.services;

import com.fortech.models.Car;
import com.fortech.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    final private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> findAll(Pageable pageable){
        return carRepository.findAll(pageable);
    }
    public Page<Car> findBy(String keyword,Pageable pageable){
        return carRepository.findAllByBrandMatchesRegexOrNameMatchesRegexOrKindMatchesRegex(keyword,keyword,keyword,pageable);
    }
    public Page<Car> findSome(String by,String keyword,Pageable pageable){
        return handleBy(by,keyword,pageable);
    }


    public Optional<Car> findById(String id){
        return carRepository.findById(id);
    }

    public Car addNewCar(Car car) throws IllegalArgumentException{
        return carRepository.save(car);
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

    private Page<Car> handleBy( String by, String keyword, Pageable pageable){
        switch (by){
            case "type":
                return carRepository.findAllByKindMatchesRegex(keyword,pageable);
            case "brand":
                return carRepository.findAllByBrandMatchesRegex(keyword,pageable);
            default:
                return carRepository.findAllByNameMatchesRegex(keyword,pageable);
        }
    }
}
