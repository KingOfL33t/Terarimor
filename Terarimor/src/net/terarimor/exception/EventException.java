package net.terarimor.exception;

public class EventException extends Exception {
	
	private static final long serialVersionUID = 5399364797206259554L;
	
	private final Throwable cause;


	public EventException(Throwable throwable) {
		cause = throwable;
	}

	public EventException() {
		cause = null;
	}

	public EventException(Throwable cause, String message) {
		super(message);
		this.cause = cause;
	}

	public EventException(String message) {
		super(message);
		cause = null;
	}

	/**
	 * * If applicable, returns the Exception that triggered this Exception
	 * returns Inner exception, or null if one does not exist
	 */
	@Override
	public Throwable getCause() {
		return cause;
	}
}