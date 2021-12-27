package com.fortech.repository.implementation;

import com.fortech.models.Client;
import com.fortech.repository.CustomClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomClientRepositoryImplementation implements CustomClientRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Page<Client> findAllBy(String username,Pageable page){
        final Query query = new Query().with(page);
        final List<Criteria> criteria = new ArrayList<>();
//        if (username != null && !username.isEmpty())
            criteria.add(Criteria.where("username").is(username));
//        if (address != null && !address.isEmpty())
//            criteria.add(Criteria.where("address").is(address));
//        if (!criteria.isEmpty())
//            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
        List<Client> list = mongoTemplate.find(query, Client.class);
//        List<Client> list = mongoTemplate.find(query, Client.class);
        return PageableExecutionUtils.getPage(
                list,
                page,
                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Client.class));
    }
}
