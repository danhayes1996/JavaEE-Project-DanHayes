package service;

import javax.inject.Inject;

import exceptions.ReviewNotFoundException;
import persistence.repository.ReviewRepository;

public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewRepository repo;

	@Override
	public String getAllReviews() {
		return repo.getAllReviews();
	}
	
	@Override
	public String getReview(long id) {
		try {
			return repo.getReview(id);
		} catch(ReviewNotFoundException rnfe) {
			return "{\"error\":\"true\", \"message\":\"Get Review Failed: No review Found\"}";
		}
	}

	@Override
	public String createReview(String review) {
		return repo.createReview(review);
	}

	@Override
	public String deleteReview(long id) {
		try {
			return repo.deleteReview(id);
		} catch(ReviewNotFoundException rnfe) {
			return "{\"error\":\"true\", \"message\":\"Delete Failed: No review Found\"}";
		}
	}

	@Override
	public String updateReview(long id, String review) {
		try {
			return repo.updateReview(id, review);
		} catch(ReviewNotFoundException rnfe) {
			return "{\"error\":\"true\", \"message\":\"Update Failed: No review Found\"}";
		}
	}
}
