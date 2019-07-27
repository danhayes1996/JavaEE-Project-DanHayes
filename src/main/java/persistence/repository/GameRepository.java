package persistence.repository;

public interface GameRepository {

	public String getGames(String name);
	public String getGame(long id);
	public String createGame(String game);
	public String updateGame(long id, String game);
	public String removeGame(long id);
}
