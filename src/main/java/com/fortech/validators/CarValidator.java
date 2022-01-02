package com.fortech.validators;

import com.fortech.models.Car;

public class CarValidator {
    public static boolean isValid(Car car){
        return !( car.getKind() == null || car.getKind().isEmpty()
                || car.getBrand() == null || car.getBrand().isEmpty()
                || car.getName() == null || car.getName().isEmpty());
    }
}
