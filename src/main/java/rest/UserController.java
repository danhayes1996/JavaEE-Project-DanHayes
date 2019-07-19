package rest;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.UserService;

@Path("/user")
public class UserController {

	@Inject
	private UserService service;
	
	@Path("/allUsers")
	public String getAllUsers() {
		return service.getAllUsers();
	}
	
	@Path("/getUser/{id}")
	public String getUser(@PathParam("id") long id) {
		return service.getUser(id);
	}

	@Path("/createUser")
	public String createUser(String user) {
		return service.createUser(user);
	}

	@Path("/deleteUser/{id}")
	public String deleteUser(@PathParam("id") long id) {
		return service.deleteUser(id);
	}

	@Path("/updateUser/{id}")
	public String updateUser(@PathParam("id") long id, String user) {
		return service.updateUser(id, user);
	}
	
}
