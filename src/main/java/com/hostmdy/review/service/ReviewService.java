package com.hostmdy.review.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.review.domain.Review;

public interface ReviewService {
	
	Review saveReview(Review review);
	Review createReview(Review review, Long phoneId);
	List<Review> getAllReviewByPhone(Long phoneId);
	Optional<Review> getReviewById(Long reviewId);
	void deleteReviewById(Long reviewId);

}
