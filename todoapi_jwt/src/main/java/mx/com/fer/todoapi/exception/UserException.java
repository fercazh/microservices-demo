package mx.com.fer.todoapi.exception;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = -5434382773827506786L;

	public final static String MESSAGE_ID_NOT_FOUND = "User with id:'%s' was not found.";
	public final static String MESSAGE_LOGIN = "Incorrect username or password.";

	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(String message, Object... params) {
		super(String.format(message, params));
	}

}
