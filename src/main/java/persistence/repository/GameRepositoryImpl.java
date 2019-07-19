package persistence.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import exceptions.GameNotFoundException;
import persistence.domain.Game;
import utils.JsonUtils;

public class GameRepositoryImpl implements GameRepository {

	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	@Inject
	private JsonUtils json;
	
	@Override
	public String getGames(String name) {
		TypedQuery<Game> query = manager.createQuery("SELECT g FROM Game g WHERE g.name LIKE %" + name + "%", Game.class);
		return json.toJson(query.getResultList());
	}

	@Override
	public String getGame(long id) {
		return json.toJson(getGameById(id));
	}
	
	private Game getGameById(long id) throws GameNotFoundException {
		Game game = manager.find(Game.class, id);
		if(game == null) {
			throw new GameNotFoundException(id);
		}
		return game;
	}

	@Override
	public String createGame(String game) {
		Game g = json.toObj(game, Game.class);
		manager.persist(g);
		return "{\"message\":\"game successfully added\"}";
	}

	@Override
	public String updateGame(long id, String game) throws GameNotFoundException {
		Game gNew = json.toObj(game, Game.class);
		Game gOld = getGameById(id);
		gOld.setName(gNew.getName());
		gOld.setDescription(gNew.getDescription());
		gOld.setAgeRating(gNew.getAgeRating());
		return "{\"message\":\"game successfully updated\"}";
	}

	@Override
	public String removeGame(long id) throws GameNotFoundException {
		manager.remove(getGameById(id));
		return "{\"message\":\"game successfully deleted\"}";
	}
}
