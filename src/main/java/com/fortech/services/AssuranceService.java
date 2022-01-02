package com.fortech.services;

import com.fortech.models.Assurance;
import com.fortech.repository.AssuranceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssuranceService {

    final private AssuranceRepository assuranceRepository;

    public AssuranceService(AssuranceRepository assuranceRepository) {
        this.assuranceRepository = assuranceRepository;
    }

    public Page<Assurance> findAll(Pageable pageable){
        return assuranceRepository.findAll(pageable);
    }

    public Optional<Assurance> findById(String id){
        return assuranceRepository.findById(id);
    }

    public Assurance addNewAssurancePlan(Assurance assurance) throws IllegalArgumentException{
        return assuranceRepository.save(new Assurance(assurance));
    }

    public Assurance updateAssurancePlan(Assurance assurance){
        return assuranceRepository.save(assurance);
    }

    public void removeAssurancePlanById(String id){
        assuranceRepository.deleteById(id);
    }

    public void removeAllAssurancePlans(){
        assuranceRepository.deleteAll();
    }
}
