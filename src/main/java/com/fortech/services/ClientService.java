package com.fortech.services;

import com.fortech.models.Client;
import com.fortech.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    final private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(String id){
        return clientRepository.findById(id);
    }

    public Client addNewClient(Client client) throws IllegalArgumentException{
        return clientRepository.save(new Client(client.getUsername()));
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
}