package com.hostmdy.review.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.review.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {
	
	Optional<Admin> findByName(String name);

}
