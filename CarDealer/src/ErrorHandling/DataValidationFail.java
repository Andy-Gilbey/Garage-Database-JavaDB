package ErrorHandling;

/**
 * @author Andrew Gilbey/C00263656
 
 *
 */
public class DataValidationFail extends Exception{

	public DataValidationFail() {
		super();

	}

	public DataValidationFail(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DataValidationFail(String message, Throwable cause) {
		super(message, cause);

	}

	public DataValidationFail(String message) {
		super(message);

	}

	public DataValidationFail(Throwable cause) {
		super(cause);

	}

}
