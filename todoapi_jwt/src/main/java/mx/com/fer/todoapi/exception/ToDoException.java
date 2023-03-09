package mx.com.fer.todoapi.exception;

public class ToDoException extends RuntimeException {

	private static final long serialVersionUID = 7719663010774240192L;
	
	public final static String MESSAGE_ID_NOT_FOUND = "TODO task with id:'%s' was not found";
	public ToDoException() {
		super();
	}

	public ToDoException(String message) {
		super(message);
	}

	public ToDoException(String message, Object... params) {
		super(String.format(message, params));
	}
	

}
