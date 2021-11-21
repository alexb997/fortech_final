package com.fortech.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortech.models.Car;
import com.fortech.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    CarRepository carRepository;

    Car car1 = new Car("14x25","Toyota",true);
    Car car2 = new Car("15xTY","Volvo",true);
    Car car3 = new Car("16xYR","Toyota",true);

    @Test
    public void getAllCars_success() throws Exception {
        List<Car> records = new ArrayList<>(Arrays.asList(car1, car2, car3));

        Mockito.when(carRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].plate", is("15xTY")));
    }
}
