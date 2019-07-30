package service;

import javax.inject.Inject;

import exceptions.GameNotFoundException;
import exceptions.ReviewNotFoundException;
import exceptions.UserNotFoundException;
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
	public String createReview(long userId, long gameId, String review) {
		try {
			return repo.createReview(userId, gameId, review);
		} catch(UserNotFoundException unfe) {
			return "{\"error\":\"true\", \"message\":\"Create Review Failed: No User Found\"}";
		} catch (GameNotFoundException gnfe) {
			return "{\"error\":\"true\", \"message\":\"Create Review Failed: No Game Found\"}";
		}
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
