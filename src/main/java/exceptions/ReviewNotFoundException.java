package exceptions;

public class ReviewNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException(long id) {
		super("" + id);
	}
	
}
