package persistence.repository;

import exceptions.ReviewNotFoundException;

public interface ReviewRepository {

	public String getAllReviews();
	public String getReview(long id) throws ReviewNotFoundException;
	public String createReview(String review); 
	public String deleteReview(long id) throws ReviewNotFoundException;
	public String updateReview(long id, String review) throws ReviewNotFoundException;
}
