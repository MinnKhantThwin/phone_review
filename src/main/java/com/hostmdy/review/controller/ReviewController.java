package com.hostmdy.review.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.review.domain.Review;
import com.hostmdy.review.service.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@GetMapping("/{phoneId}/add")
	public String newReviewForm(@PathVariable Long phoneId, Model model) {
		Review review = new Review();
		model.addAttribute("review", review);
		model.addAttribute("phoneId", phoneId);
		return "review/add-review";
	}
	
	@PostMapping("/add")
	public String createReview(@RequestParam Long phoneId, @ModelAttribute Review review) {
		reviewService.createReview(review, phoneId);
		return "redirect:/phone/"+phoneId+"/show";
	}
	
	@GetMapping("/{reviewId}/edit")
	public String updateCameraForm(@PathVariable Long reviewId, Model model) {
		Optional<Review> reviewOpt = reviewService.getReviewById(reviewId);
		if(reviewOpt.isEmpty()) {
			throw new NullPointerException("Review can't be found");
		}
		Review review = reviewOpt.get();
		model.addAttribute("review", review);
		model.addAttribute("phoneId", review.getPhone().getId());
		return "review/add-review";
	}
	
	@GetMapping("/{reviewId}/delete/{phoneId}")
	public String deleteReview(@PathVariable Long reviewId, @PathVariable Long phoneId) {
		reviewService.deleteReviewById(reviewId);
		return "redirect:/phone/"+phoneId+"/show";
	}

}
