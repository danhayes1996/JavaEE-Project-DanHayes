package persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String ageRating;
	@Column(columnDefinition = "integer default 0")
	private int averageRating;
	@Column(columnDefinition = "integer default 0")
	private int numOfRatings;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "game")
//	private Set<Review> reviews = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	public int getNumOfRatings() {
		return numOfRatings;
	}

	public void setNumOfRatings(int numOfRatings) {
		this.numOfRatings = numOfRatings;
	}

//	public Set<Review> getReviews() {
//		return reviews;
//	}
//
//	public void setReviews(Set<Review> reviews) {
//		this.reviews = reviews;
//	}

}
