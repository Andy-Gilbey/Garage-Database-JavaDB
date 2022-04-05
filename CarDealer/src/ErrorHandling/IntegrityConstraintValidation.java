package ErrorHandling;

/**
 * @author Andrew Gilbey/C00263656

 *
 */
public class IntegrityConstraintValidation extends Exception {

	public IntegrityConstraintValidation() {
		super();
		
	}

	public IntegrityConstraintValidation(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public IntegrityConstraintValidation(String message, Throwable cause) {
		super(message, cause);
		
	}

	public IntegrityConstraintValidation(String message) {
		super(message);
		
	}

	public IntegrityConstraintValidation(Throwable cause) {
		super(cause);
		
	}

}
