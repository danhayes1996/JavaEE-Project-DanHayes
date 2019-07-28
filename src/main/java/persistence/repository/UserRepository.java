package persistence.repository;

import javax.persistence.NoResultException;

import exceptions.UserNotFoundException;

public interface UserRepository {

	public String getAllUsers();
	public String getUser(long id) throws NoResultException;
	public String verifyUser(String user) throws UserNotFoundException;
	public String createUser(String user);
	public String deleteUser(long id) throws UserNotFoundException;
	public String updateUser(long id, String user) throws UserNotFoundException;
}
