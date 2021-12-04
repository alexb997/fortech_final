//package com.fortech.controllers;
//
//import com.fortech.models.Car;
//import com.fortech.services.CarService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = CarController.class)
//@WithMockUser
//public class CarControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CarService carService;
//
//    Car mockCar = new Car("21xAT","Toyota");
//
//    String exampleCarJson = "{\"plate\":\"21xAT\",\"manufacturer\":\"Toyota\",\"assured\":\"false\"}";
//
//    @Test
//    public void getCarByIdTest() throws Exception {
//        Mockito.when(
//                carService.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockCar));
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/api/cars/61990f826a5e1f48302ef8d7").accept(
//                MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        System.out.println(result.getResponse());
//        String expected = "{plate:21xAT,manufacturer:Toyota,assured:false}";
//
//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
//    }
//
//    @Test
//    public void createCarTest() throws Exception {
//        Car mockCar = new Car("11xSS", "Ferrari");
//
//        Mockito.when(carService.addNewCar(Mockito.any(Car.class))).thenReturn(mockCar);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/api/cars")
//                .accept(MediaType.APPLICATION_JSON).content(exampleCarJson)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//
//    @Test
//    public void deleteCarTest() throws Exception {
//        Car mockCar = new Car("11xSS", "Ferrari");
//
//        Mockito.when(carService.findById(Mockito.anyString()))
//                .thenReturn(java.util.Optional.of(mockCar));
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/api/cars/61990f826a5e1f48302ef8d7")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        System.out.println("HERE!!!!!!!!-"+ response);
//        assertEquals(204,response.getStatus());
//    }
//
//    @Test
//    public void updateCarTest() throws Exception {
//        Car mockCar = new Car("11xXT", "Dacia");
//        Car mockCarUpdated = new Car("12xXT","Dacia");
//        mockCarUpdated.setAssured(true);
//        String mockStringCarUpdated ="{\"plate\":\"12xXT\",\"manufacturer\":\"Dacia\",\"assured\":\"true\"}";
//
//        Mockito.when(carService.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockCar));
//        Mockito.when(carService.updateCar(Mockito.any(Car.class))).thenReturn(mockCarUpdated);
//
//        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
//                "/api/cars/61990f826a5e1f48302ef8d7").accept(
//                MediaType.APPLICATION_JSON);
//        RequestBuilder requestBuilderPut = MockMvcRequestBuilders
//                .put("/api/cars/61990f826a5e1f48302ef8d7")
//                .accept(MediaType.APPLICATION_JSON).content(mockStringCarUpdated)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult resultGet = mockMvc.perform(requestBuilderGet).andReturn();
//        System.out.println(resultGet.getResponse());
//        String expected = "{plate:11xXT,manufacturer:Dacia,assured:false}";
//        JSONAssert.assertEquals(expected, resultGet.getResponse()
//                .getContentAsString(), false);
//
//        MvcResult resultPut = mockMvc.perform(requestBuilderPut).andReturn();
//        String expectedUpdate = "{plate:12xXT,manufacturer:Dacia,assured:true}";
//        JSONAssert.assertEquals(expectedUpdate, resultPut.getResponse()
//                .getContentAsString(),false);
//    }
//}
