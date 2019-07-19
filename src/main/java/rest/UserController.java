package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.UserService;

@Path("/user")
public class UserController {

	@Inject
	private UserService service;
	
	@GET
	@Path("/allUsers")
	public String getAllUsers() {
		return service.getAllUsers();
	}

	@GET
	@Path("/getUser/{id}")
	public String getUser(@PathParam("id") long id) {
		return service.getUser(id);
	}

	@POST
	@Path("/createUser")
	public String createUser(String user) {
		return service.createUser(user);
	}

	@DELETE
	@Path("/deleteUser/{id}")
	public String deleteUser(@PathParam("id") long id) {
		return service.deleteUser(id);
	}

	@POST
	@Path("/updateUser/{id}")
	public String updateUser(@PathParam("id") long id, String user) {
		return service.updateUser(id, user);
	}
	
}
