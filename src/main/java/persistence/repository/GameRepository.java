package persistence.repository;

import exceptions.GameNotFoundException;

public interface GameRepository {

	public String getGames(String name);
	public String getGame(long id) throws GameNotFoundException;
	public String getNewReleases();
	public String createGame(String game);
	public String updateGame(long id, String game) throws GameNotFoundException;
	public String removeGame(long id) throws GameNotFoundException;
}
