package com.fortech.repository;

import com.fortech.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomClientRepository {
    public Page<Client> findAllBy(String username, Pageable page);
}
