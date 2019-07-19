package service;

import javax.inject.Inject;

import persistence.repository.ReviewRepository;

public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewRepository repo;
	
	@Override
	public String getReview(long id) {
		return repo.getReview(id);
	}

	@Override
	public String createReview(String review) {
		return repo.createReview(review);
	}

	@Override
	public String deleteReview(long id) {
		return repo.deleteReview(id);
	}

	@Override
	public String updateReview(long id, String review) {
		return repo.updateReview(id, review);
	}

}
