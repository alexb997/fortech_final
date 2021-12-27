package com.fortech.services;

import com.fortech.models.Client;
import com.fortech.repository.ClientRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {
    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> findAll( Pageable pageable){
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> findById(String id){
        return clientRepository.findById(id);
    }

    public Client addNewClient(Client client) throws IllegalArgumentException{
        return clientRepository.save(new Client(client.getUsername(),client.getOwnedCars(), client.getPhone(), client.getAddress()));
    }

    public Client updateClient(Client client){
        return clientRepository.save(client);
    }

    public void removeClientById(String id){
        clientRepository.deleteById(id);
    }

    public void removeAllClients(){
        clientRepository.deleteAll();
    }

    public Page<Client> findBy(String username,String address, Pageable pageable) {
        return clientRepository.findByUsernameAndAddress(username,address,pageable);
    }


}
