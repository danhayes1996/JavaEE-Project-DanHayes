package persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import exceptions.GameNotFoundException;
import persistence.domain.Game;
import utils.JsonUtils;

@Default
@Transactional(value = TxType.SUPPORTS)
public class GameRepositoryImpl implements GameRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JsonUtils json;

	@Override
	@Transactional(value = TxType.SUPPORTS)
	public String getGames(String name) {
		TypedQuery<Game> query = manager
				.createQuery("SELECT g FROM Game g WHERE UPPER(g.name) LIKE UPPER('%" + name + "%')", Game.class);
		return json.toJson(query.getResultList());
	}

	@Override
	@Transactional(value = TxType.SUPPORTS)
	public String getGame(long id) throws GameNotFoundException {
		return json.toJson(getGameById(id));
	}

	@Transactional(value = TxType.SUPPORTS)
	private Game getGameById(long id) throws GameNotFoundException {
		Game game = manager.find(Game.class, id);
		if (game == null) {
			throw new GameNotFoundException(id);
		}
		return game;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String createGame(String game) {
		Game g = json.toObj(game, Game.class);
		manager.persist(g);
		return "{\"message\":\"game successfully added\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String updateGame(long id, String game) throws GameNotFoundException {
		Game gNew = json.toObj(game, Game.class);
		Game gOld = getGameById(id);
		if(gNew.getName() != null) gOld.setName(gNew.getName());
		if(gNew.getDescription() != null) gOld.setDescription(gNew.getDescription());
		if(gNew.getAgeRating() != null) gOld.setAgeRating(gNew.getAgeRating());
		return "{\"message\":\"game successfully updated\"}";
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String removeGame(long id) throws GameNotFoundException {
		manager.remove(getGameById(id));
		return "{\"message\":\"game successfully deleted\"}";
	}
}
