package pro.bank.exception;

public class UploadFileException extends RuntimeException{

	private static final long serialVersionUID = -3382171898553669673L;

	public UploadFileException() {
		super();
	}

	public UploadFileException(String message) {
		super(message);
	}

	public UploadFileException(Throwable cause) {
		super(cause);
	}

	public UploadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
