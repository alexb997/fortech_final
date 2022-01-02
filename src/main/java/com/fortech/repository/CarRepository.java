package com.fortech.repository;

import com.fortech.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, String> {
    Page<Car> findAllByBrandMatchesRegexOrNameMatchesRegexOrKindMatchesRegex(String brand,String name,String kind, Pageable pageable);
}
