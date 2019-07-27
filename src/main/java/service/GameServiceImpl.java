package service;

import javax.inject.Inject;

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
		return repo.getGame(id);
		
	}

	@Override
	public String createGame(String game) {
		return repo.createGame(game);
		
	}

	@Override
	public String updateGame(long id, String game) {
		return repo.updateGame(id, game);
		
	}

	@Override
	public String removeGame(long id) {
		return repo.removeGame(id);
		
	}

}
