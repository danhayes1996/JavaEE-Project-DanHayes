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
	
	private User getUserById(long id) throws UserNotFoundException {
		User user = manager.find(User.class, id);
		if(user == null) {
			throw new UserNotFoundException(id);
		}
		return user;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createUser(String user) {
		User u = json.toObj(user, User.class);
		manager.persist(u);
		return "{\"message\":\"user successfully added\"}";
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
		uOld.setFirstName(uNew.getFirstName());
		uOld.setLastName(uNew.getLastName());
		uOld.setEmail(uNew.getEmail());
		uOld.setPassword(uNew.getPassword());
		return "{\"message\":\"user successfully updated\"}";
	}
}
