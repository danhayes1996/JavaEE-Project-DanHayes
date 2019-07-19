package persistence.repository;

public interface ReviewRepository {

	public String getAllReviews();
	public String getReview(long id);
	public String createReview(String review); //TODO: add User who made review??
	public String deleteReview(long id);
	public String updateReview(long id, String review);
}
