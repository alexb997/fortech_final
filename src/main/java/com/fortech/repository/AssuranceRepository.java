package com.fortech.repository;

import com.fortech.models.Assurance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuranceRepository  extends MongoRepository<Assurance, String> {

}
