package service;

public interface ReviewService {

	public String getAllReviews();
	public String getReview(long id);	
	public String createReview(long userId, long gameId, String review);
	public String deleteReview(long id);
	public String updateReview(long id, String review);
}
