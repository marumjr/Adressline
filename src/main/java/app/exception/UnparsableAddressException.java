package app.exception;

public class UnparsableAddressException extends Exception {

	private static final long serialVersionUID = -3637916312269858386L;
	
	private String message;
	
	/**
	 * @param message
	 * 		Message carried out by the Exception
	 */
	public UnparsableAddressException(String message) {
		super();
		this.message = message;
	}

	/**
	 * Format the message with the arguments given
	 * 
	 * @param message
	 * 		Message carried out by the Exception
	 * @param args
	 * 		Arguments to be set in the message
	 */
	public UnparsableAddressException(String message, Object... args) {
		super();
		this.message = String.format(message, args);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
