package com.hostmdy.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.review.domain.Phone;

public interface PhoneRepository extends CrudRepository<Phone, Long> {
	
	Optional<Phone> findByNameContainingIgnoreCase(String name);
	List<Phone> findByBrandContainingIgnoreCase(String brand);
	List<Phone> findByModelContainingIgnoreCase(String model);

}
