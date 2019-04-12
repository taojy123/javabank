package pro.bank.exception;

public class SystemError2HtmlException extends RuntimeException{

	private static final long serialVersionUID = 7042228854752636160L;

	public SystemError2HtmlException() {
		super();
	}

	public SystemError2HtmlException(String message) {
		super(message);
	}

	public SystemError2HtmlException(Throwable cause) {
		super(cause);
	}

	public SystemError2HtmlException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemError2HtmlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
