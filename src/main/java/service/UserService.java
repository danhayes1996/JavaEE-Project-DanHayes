package service;

public interface UserService {

	public String getAllUsers();
	public String getUser(long id);
	public String verifyUser(String user);
	public String createUser(String user);
	public String deleteUser(long id);
	public String updateUser(long id, String user);
}
