package service;

public interface ReviewService {

	public String getAllReviews();
	public String getReview(long id);	
	public String createReview(String review);
	public String deleteReview(long id);
	public String updateReview(long id, String review);
}
