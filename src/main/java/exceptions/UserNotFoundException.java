package exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(long id) {
		super("" + id); //add id to exception message
	}
	
}
