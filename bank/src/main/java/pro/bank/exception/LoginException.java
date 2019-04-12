package pro.bank.exception;

public class LoginException extends RuntimeException{

	private static final long serialVersionUID = -3382171898553669673L;

	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(Throwable cause) {
		super(cause);
	}

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
