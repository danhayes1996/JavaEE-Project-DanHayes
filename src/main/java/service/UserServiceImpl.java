package service;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import exceptions.UserNotFoundException;
import persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;
	
	@Override
	public String getAllUsers() {
		return repo.getAllUsers();
	}

	@Override
	public String getUser(long id) {
		try {
			return repo.getUser(id);
		} catch (UserNotFoundException unfe) {
			return "{\"error\":\"true\", \"message\":\"No User Found\"}";
		}
	}
	
	@Override
	public String verifyUser(String user) {
		try {
			return repo.verifyUser(user);
		} catch (NoResultException nre) {
			return "{\"error\":\"true\", \"message\":\"No User Found\"}";
		}
	}

	@Override
	public String createUser(String user) {
		return repo.createUser(user);
	}

	@Override
	public String deleteUser(long id) {
		return repo.deleteUser(id);
	}

	@Override
	public String updateUser(long id, String user) {
		return repo.updateUser(id, user);
	}
}
