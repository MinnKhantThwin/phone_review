package com.hostmdy.review.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.domain.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	List<Review> findByPhone(Phone phone);

}
