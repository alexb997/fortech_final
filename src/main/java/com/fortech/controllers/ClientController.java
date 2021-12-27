package com.fortech.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.fortech.models.Client;
import com.fortech.controllers.Response;
import com.fortech.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/clients")
public class ClientController {

    final ClientService clientService;

    private static final Logger LOGGER= LoggerFactory.getLogger(ClientController.class);

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<Map<String, Object>> getAllClients(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Long phone,
            //add cars param filter
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        try {
            List<Client> clients;
            Pageable paging = PageRequest.of(page, size);

//            Page<Client> pageClients;
//            pageClients = clientService.findAll(paging);

//            clients = pageClients.getContent();
//            Response response = new Response(clients,pageClients.getTotalPages(),pageClients.getTotalElements(),pageClients.getNumber());
//            return new ResponseEntity(response, HttpStatus.OK);
            clients = clientService.findByFilters(username,address,paging);
            return new ResponseEntity(clients, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Couldn't find clients ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
            LOGGER.error("Couldn't create client ", e);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error("Couldn't create client ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") String id, @RequestBody Client client) {
        Optional<Client> clientData = clientService.findById(id);

        if (clientData.isPresent()) {
            Client newClient = clientData.get();
            newClient.setUsername(client.getUsername());
            newClient.setOwnedCars(client.getOwnedCars());
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
            LOGGER.error("Removal of client didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/clients")
    public ResponseEntity<HttpStatus> deleteAllClients() {
        try {
            clientService.removeAllClients();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error("Removal of clients didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
