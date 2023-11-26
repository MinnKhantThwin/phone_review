package com.hostmdy.review.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.review.domain.Display;
import com.hostmdy.review.domain.Phone;


public interface DisplayRepository extends CrudRepository<Display, Long> {

	List<Display> findByPhone(Phone phone);
	
}
