package persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import exceptions.UserNotFoundException;
import persistence.domain.User;
import utils.JsonUtils;

@Default
@Transactional(value = TxType.SUPPORTS)
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	@Inject
	private JsonUtils json;

	@Override
	public String getAllUsers() {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u", User.class);
		return json.toJson(query.getResultList());
	}

	@Override
	public String getUser(long id) throws UserNotFoundException {
		return json.toJson(getUserById(id));
	}
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public String verifyUser(String user) {
		User u = json.toObj(user, User.class);
		TypedQuery<Long> query = manager.createQuery("SELECT u.id FROM User u WHERE u.email='" + u.getEmail() 
											+ "' AND u.password='" + u.getPassword() + "'", Long.class);
		return json.toJson(query.getSingleResult());
	}
	
	private User getUserById(long id) throws UserNotFoundException {
		User user = manager.find(User.class, id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}
	
	private boolean validAccount(User user) {
		long count = manager.createQuery("SELECT COUNT(u.id) FROM User u WHERE u.email='" + user.getEmail() + "'", Long.class).getSingleResult();
		return count == 0;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createUser(String user) {
		User u = json.toObj(user, User.class);
		if(validAccount(u)) {
			manager.persist(u);
			return "{\"message\":\"user successfully added\"}";
		}
		return "{\"error\": \"true\", \"message\":\"An account has already been created with this email address\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String deleteUser(long id) throws UserNotFoundException {
		manager.remove(getUserById(id));
		return "{\"message\":\"user successfully deleted\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateUser(long id, String user) throws UserNotFoundException {
		User uOld = getUserById(id);
		User uNew = json.toObj(user, User.class);
		if(uNew.getFirstName() != null) uOld.setFirstName(uNew.getFirstName());
		if(uNew.getLastName() != null) uOld.setLastName(uNew.getLastName());
		if(uNew.getEmail() != null) uOld.setEmail(uNew.getEmail());
		if(uNew.getPassword() != null) uOld.setPassword(uNew.getPassword());
		return "{\"message\":\"user successfully updated\"}";
	}
}
