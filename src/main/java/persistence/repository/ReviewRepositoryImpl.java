package persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import exceptions.GameNotFoundException;
import exceptions.ReviewNotFoundException;
import exceptions.UserNotFoundException;
import persistence.domain.Game;
import persistence.domain.Review;
import persistence.domain.User;
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

	@Override
	public String getReviewByGameId(long gameId) {
		TypedQuery<Review> query = manager.createQuery("SELECT r FROM Review r WHERE r.game=" + gameId + " ORDER BY r.upvotes DESC", Review.class);
		return json.toJson(query.getResultList());
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
	public String createReview(long userId, long gameId, String review) throws GameNotFoundException, UserNotFoundException {
		User u = manager.find(User.class, userId);
		Game g = manager.find(Game.class, gameId);
		
		if(u == null) throw new UserNotFoundException(userId);
		if(g == null) throw new GameNotFoundException(gameId);
		
		Review r = json.toObj(review, Review.class);
		
		r.setUser(u);
		r.setGame(g);
		manager.persist(r);
		
		return "{\"message\":\"review successfully added\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteReview(long id) throws ReviewNotFoundException {
		Review review = getReviewById(id);
		manager.remove(review);
		return "{\"message\":\"review successfully deleted\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateReview(long id, String review) throws ReviewNotFoundException {
		Review rOld = getReviewById(id);
		Review rNew = json.toObj(review, Review.class);
		if(rNew.getTitle() != null) rOld.setTitle(rNew.getTitle());
		if(rNew.getContent() != null) rOld.setContent(rNew.getContent());
		rOld.setUpvotes(rNew.getUpvotes());
		return "{\"message\":\"review successfully updated\"}";
	}
}
