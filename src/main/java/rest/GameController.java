package rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import service.GameService;

@Path("/game")
public class GameController {

	@Inject
	private GameService service;
	
	@GET
	@Path("/getGames/{name}")
	public String getGames(@PathParam("name") String name) {
		return service.getGames(name);
	}
	
	@GET
	@Path("/getGame/{id}")
	public String getGame(@PathParam("id") long id) {
		return service.getGame(id);
	}
	
	@POST
	@Path("/createGame")
	public String createGame(String game) {
		return service.createGame(game);
	}
	
	@POST
	@Path("/updateGame/{id}")
	public String updateGame(@PathParam("id") long id, String game) {
		return service.updateGame(id, game);
	}
	
	@POST
	@Path("/removeGame/{id}")
	public String removeGame(@PathParam("id") long id) {
		return service.removeGame(id);
	}
}
