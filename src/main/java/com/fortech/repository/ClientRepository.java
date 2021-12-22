package com.fortech.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fortech.models.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, String> {

    Page<Client> findByUsernameContaining(String username, Pageable pageable);
}
