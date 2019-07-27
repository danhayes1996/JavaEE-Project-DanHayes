package rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import service.UserService;

@Path("/user")
public class UserController {

	@Inject
	private UserService service;
	
	@GET
	@Path("/all")
	public String getAllUsers() {
		return service.getAllUsers();
	}

	@GET
	@Path("/get/{id}")
	public String getUser(@PathParam("id") long id) {
		return service.getUser(id);
	}
	
	@POST
	@Path("/verify")
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyUser(String user) {
		return service.verifyUser(user);
	}
	
	@POST
	@Path("/create")
	public String createUser(String user) {
		return service.createUser(user);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteUser(@PathParam("id") long id) {
		return service.deleteUser(id);
	}

	@POST
	@Path("/update/{id}")
	public String updateUser(@PathParam("id") long id, String user) {
		return service.updateUser(id, user);
	}
	
}
