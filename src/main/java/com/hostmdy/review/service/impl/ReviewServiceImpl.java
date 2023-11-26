package com.hostmdy.review.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.review.domain.Phone;
import com.hostmdy.review.domain.Review;
import com.hostmdy.review.repository.PhoneRepository;
import com.hostmdy.review.repository.ReviewRepository;
import com.hostmdy.review.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private final PhoneRepository phoneRepository;
	private final ReviewRepository reviewRepository;
	public ReviewServiceImpl(PhoneRepository phoneRepository, ReviewRepository reviewRepository) {
		super();
		this.phoneRepository = phoneRepository;
		this.reviewRepository = reviewRepository;
	}
	@Override
	public Review saveReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepository.save(review);
	}
	@Override
	public Review createReview(Review review, Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}
		Phone phone = phoneOpt.get();
		phone.getReviews().add(review);
		review.setPhone(phone);
		
		return saveReview(review);
	}
	@Override
	public List<Review> getAllReviewByPhone(Long phoneId) {
		// TODO Auto-generated method stub
		Optional<Phone> phoneOpt = phoneRepository.findById(phoneId);

		if (phoneOpt.isEmpty()) {
			throw new NullPointerException("Coundn't find the Phone");
		}
		return reviewRepository.findByPhone(phoneOpt.get());
	}
	@Override
	public Optional<Review> getReviewById(Long reviewId) {
		// TODO Auto-generated method stub
		return reviewRepository.findById(reviewId);
	}
	@Override
	public void deleteReviewById(Long reviewId) {
		// TODO Auto-generated method stub
		reviewRepository.deleteById(reviewId);
	}

}
