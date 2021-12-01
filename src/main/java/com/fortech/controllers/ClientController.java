package com.fortech.controllers;

import com.fortech.models.Car;
import com.fortech.models.Client;
import com.fortech.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;

    private static final Logger LOGGER= LoggerFactory.getLogger(CarController.class);

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        try {
            List<Client> clients;
            clients = clientService.findAll();
            return new ResponseEntity<>(clients,HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Couldn't find clients ", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") String id) {
        Optional<Client> clientData = clientService.findById(id);
        return clientData.map(client -> new ResponseEntity<>(client, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client newClient = clientService.addNewClient(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            LOGGER.info("Couldn't create client ", e);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Couldn't create client ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
        Optional<Client> clientData = clientService.findById(id);

        if (clientData.isPresent()) {
            Client newClient = clientData.get();
            newClient.setUsername(client.getCarID());
            newClient.setCarID(client.getCarID());
            return new ResponseEntity<>(clientService.updateClient(newClient), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable("id") String id) {
        try {
            clientService.removeClientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Removal of client didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clients")
    public ResponseEntity<HttpStatus> deleteAllClients() {
        try {
            clientService.removeAllClients();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Removal of clients didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
