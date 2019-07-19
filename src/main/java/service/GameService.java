package service;

public interface GameService {
	
	public String getGames(String name);
	public String getGame(long id);
	public String createGame(String game);
	public String updateGame(long id, String game);
	public String removeGame(long id);
}
