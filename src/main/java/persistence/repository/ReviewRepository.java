package persistence.repository;

import exceptions.ReviewNotFoundException;

public interface ReviewRepository {

	public String getAllReviews();
	public String getReview(long id) throws ReviewNotFoundException;
	public String getReviewByGameId(long gameId);
	public String createReview(long userId, long gameId, String review); 
	public String deleteReview(long id) throws ReviewNotFoundException;
	public String updateReview(long id, String review) throws ReviewNotFoundException;
}
