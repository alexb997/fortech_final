package com.fortech.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fortech.models.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, String> {

//    Page<Client> findByUsernameContaining(String username, Pageable pageable);
//    Page<Client> findByFilters(String username,String address, Long phone , Pageable pageable);
    Page<Client> findBy(, Pageable pageable);
}
