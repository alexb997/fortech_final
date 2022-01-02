package com.fortech.repository;

import com.fortech.models.Assurance;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository  extends PagingAndSortingRepository<Assurance, String> {

}
