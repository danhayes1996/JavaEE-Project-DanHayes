package service;

import javax.inject.Inject;

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
		return repo.getUser(id);
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
