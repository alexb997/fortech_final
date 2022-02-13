package com.fortech.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fortech.models.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, String> {

    Page<Client> findByUsernameMatchesRegexOrAddressMatchesRegex(String username,String address,Pageable pageable);
    Optional<Client> findClientByUsername(String username);
    Page<Client> findByAddress(String address);
    Page<Client> findByBanking(String address);
}
