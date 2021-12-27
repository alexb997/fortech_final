package com.fortech.repository;

import com.fortech.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomClientRepository {
    public List<Client> findBy(String username,String address, Pageable page);
}
