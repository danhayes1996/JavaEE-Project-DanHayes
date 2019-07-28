package service;

import javax.inject.Inject;

import exceptions.GameNotFoundException;
import persistence.repository.GameRepository;

public class GameServiceImpl implements GameService {

	@Inject
	private GameRepository repo;
	
	@Override
	public String getGames(String name) {
		return repo.getGames(name);
	}

	@Override
	public String getGame(long id) {
		try {
			return repo.getGame(id);
		} catch (GameNotFoundException gnfe) {
			return "{\"error\":\"true\", \"message\":\"Get Game Failed: No Game Found\"}";
		}
	}

	@Override
	public String createGame(String game) {
		return repo.createGame(game);
	}

	@Override
	public String updateGame(long id, String game) {
		try {
			return repo.updateGame(id, game);
		} catch (GameNotFoundException gnfe) {
			return "{\"error\":\"true\", \"message\":\"Update Failed: No Game Found\"}";
		}
	}

	@Override
	public String removeGame(long id) {
		try {
			return repo.removeGame(id);
		} catch (GameNotFoundException gnfe) {
			return "{\"error\":\"true\", \"message\":\"Remove Failed: No Game Found\"}";
		}
	}

}
