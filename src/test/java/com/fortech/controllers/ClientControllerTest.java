package com.fortech.controllers;

import com.fortech.models.Car;
import com.fortech.models.Client;
import com.fortech.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TO BE EDITED ON NEW SCHEMA
@RunWith(SpringRunner.class)
@WebMvcTest(value = ClientController.class)
@WithMockUser
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    Car car1 = new Car("21xAT","Toyota");
    Car car2 = new Car( "22xXT","Dacia");

    List<Car> cars= new ArrayList<>();

    Client mockClient = new Client("Jack", cars);

    String exampleClientJson = "{\"username\":\"Jack\",\"CarId\":\"aaaa\"}";

    @Test
    public void getClientByIdTest() throws Exception {
        Mockito.when(
                clientService.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockClient));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/clients/clients/61990f826a5e1f48302ef8d7").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = exampleClientJson;

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void createClientTest() throws Exception {
        Client mockClient = new Client("Dan", "11xSS");

        Mockito.when(clientService.addNewClient(Mockito.any(Client.class))).thenReturn(mockClient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/clients/clients")
                .accept(MediaType.APPLICATION_JSON).content(exampleClientJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void deleteClientTest() throws Exception {
        Client mockClient = new Client("Dan", "11xSS");

        Mockito.when(clientService.findById(Mockito.anyString()))
                .thenReturn(java.util.Optional.of(mockClient));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/clients/clients/61990f826a5e1f48302ef8d7")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        System.out.println("HERE!!!!!!!!-"+ response);
        assertEquals(204,response.getStatus());
    }

    @Test
    public void updateClientTest() throws Exception {
        Client mockClient = new Client("Dan", "11xSS");
        Client mockClientUpdated = new Client("Ron","12xSS");
        String mockStringClientUpdated ="{\"username\":\"Ron\",\"carID\":\"12xSS\",}";

        Mockito.when(clientService.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockClient));
        Mockito.when(clientService.updateClient(Mockito.any(Client.class))).thenReturn(mockClientUpdated);

        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get(
                "/clients/clients/61990f826a5e1f48302ef8d7").accept(
                MediaType.APPLICATION_JSON);
        RequestBuilder requestBuilderPut = MockMvcRequestBuilders
                .put("/clients/clients/61990f826a5e1f48302ef8d7")
                .accept(MediaType.APPLICATION_JSON).content(mockStringClientUpdated)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult resultGet = mockMvc.perform(requestBuilderGet).andReturn();
        System.out.println(resultGet.getResponse());
        String expected = "{username:Dan,carID:11xSS}";
        JSONAssert.assertEquals(expected, resultGet.getResponse()
                .getContentAsString(), false);

        MvcResult resultPut = mockMvc.perform(requestBuilderPut).andReturn();
        String expectedUpdate = "{username:Ron,carID:12xSS}";
        JSONAssert.assertEquals(expectedUpdate, resultPut.getResponse()
                .getContentAsString(),false);
    }
}
