package com.fortech.services;

import com.fortech.models.Client;
import com.fortech.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
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
        ArrayList<String> ownedCars = new ArrayList<String>() {{
            add("car1");
            add("car2");
            add("car3");
        }};
        return clientRepository.save(new Client(client.getUsername(), ownedCars, client.getPhone(), client.getAddress(),client.getBanking()));
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

    public Optional<Client> findByUsername(String username){
         return clientRepository.findClientByUsername(username);
    };

    public Page<Client> findBy(String keyword, Pageable pageable) {
        return clientRepository.findByUsernameMatchesRegexOrAddressMatchesRegex(keyword,keyword,pageable);
    }
}
