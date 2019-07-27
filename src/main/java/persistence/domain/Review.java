package persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;
	private int upvotes;
	
//	@ManyToOne
//	@JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
//	private User user;
//	
//	@ManyToOne
//	@JoinColumn(foreignKey = @ForeignKey(name = "FK_GAME"))
//	private Game game;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public Game getGame() {
//		return game;
//	}
//	public void setGame(Game game) {
//		this.game = game;
//	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUpvotes() {
		return upvotes;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	// private Date date??
}
