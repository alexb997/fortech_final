package com.fortech.controllers;

import com.fortech.models.Car;
import com.fortech.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CarController.class)
@WithMockUser
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    Car mockCar = new Car("21xAT","Toyota");

    String exampleCourseJson = "{\"plate\":\"21xAT\",\"manufacturer\":\"Toyota\",\"assured\":\"false\"}";

    @Test
    public void getCarById() throws Exception {
        Mockito.when(
                carService.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockCar));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/cars/61990f826a5e1f48302ef8d7").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{plate:21xAT,manufacturer:Toyota,assured:false}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}