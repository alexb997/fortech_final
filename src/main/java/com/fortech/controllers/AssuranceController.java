package com.fortech.controllers;

import com.fortech.models.Assurance;
import com.fortech.services.AssuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/assurance")
public class AssuranceController {

    final AssuranceService assuranceService;

    private static final Logger LOGGER= LoggerFactory.getLogger(AssuranceController.class);

    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @GetMapping("/plans")
    public ResponseEntity<List<Assurance>> getAllAssurances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            List<Assurance> assurances;
            Pageable paging = PageRequest.of(page, size);

            //Change from Assurance to insurance everywhere
            Page<Assurance> pageInsurances;
            pageInsurances = assuranceService.findAll(paging);
            assurances= pageInsurances.getContent();
            Response response = new Response(assurances,pageInsurances.getTotalPages(),pageInsurances.getTotalElements(),pageInsurances.getNumber());
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            //Change to Logger.error from .info
            LOGGER.error("Couldn't find insurance plans ", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<Assurance> getAssuranceById(@PathVariable("id") String id) {
        Optional<Assurance> assuranceData = assuranceService.findById(id);
        return assuranceData.map(assurance -> new ResponseEntity<>(assurance, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/plans")
    public ResponseEntity<Assurance> createAssurance(@RequestBody Assurance assurance) {
        try {
            Assurance newAssurance = assuranceService.addNewAssurancePlan(assurance);
            return new ResponseEntity<>(newAssurance, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            LOGGER.info("Couldn't create assurance ", e);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Couldn't create assurance ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<Assurance> updateAssurance(@PathVariable("id") String id, @RequestBody Assurance assurance) {
        Optional<Assurance> assuranceData = assuranceService.findById(id);

        if (assuranceData.isPresent()) {
            Assurance newAssurance = assuranceData.get();
            newAssurance.setTitle(assurance.getTitle());
            newAssurance.setDescription(assurance.getDescription());
            newAssurance.setDefaultPeriod(assurance.getDefaultPeriod());
            return new ResponseEntity<>(assuranceService.updateAssurancePlan(newAssurance), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<HttpStatus> deleteAssurance(@PathVariable("id") String id) {
        try {
            assuranceService.removeAssurancePlanById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Removal of assurance didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/assurances")
    public ResponseEntity<HttpStatus> deleteAllAssurances() {
        try {
            assuranceService.removeAllAssurancePlans();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.info("Removal of assurances didn't work ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
