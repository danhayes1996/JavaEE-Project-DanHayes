package persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import exceptions.ReviewNotFoundException;
import persistence.domain.Review;
import utils.JsonUtils;

@Default
@Transactional(value = TxType.SUPPORTS)
public class ReviewRepositoryImpl implements ReviewRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	@Inject
	private JsonUtils json;
	
	@Override
	public String getAllReviews() {
		TypedQuery<Review> query = manager.createQuery("SELECT r FROM Review r", Review.class);
		return json.toJson(query.getResultList());
	}

	@Override
	public String getReview(long id) throws ReviewNotFoundException {
		return json.toJson(getReviewById(id));
	}
	
	private Review getReviewById(long id) throws ReviewNotFoundException {
		Review review = manager.find(Review.class, id);
		if(review == null) {
			throw new ReviewNotFoundException(id);
		}
		return review;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createReview(String review) {
		Review r = json.toObj(review, Review.class);
		manager.persist(r);
		return "{\"message\":\"review successfully added\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteReview(long id) {
		Review review = getReviewById(id);
		manager.remove(review);
		return "{\"message\":\"review successfully deleted\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateReview(long id, String review) {
		Review rOld = getReviewById(id);
		Review uNew = json.toObj(review, Review.class);
		rOld.setTitle(uNew.getTitle());
		rOld.setContent(uNew.getContent());
		rOld.setUpvotes(uNew.getUpvotes());
		return "{\"message\":\"review successfully updated\"}";
	}
}
