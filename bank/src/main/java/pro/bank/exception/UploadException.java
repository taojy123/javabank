package pro.bank.exception;

public class UploadException extends RuntimeException{

	private static final long serialVersionUID = -3382171898553669673L;

	public UploadException() {
		super();
	}

	public UploadException(String message) {
		super(message);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
