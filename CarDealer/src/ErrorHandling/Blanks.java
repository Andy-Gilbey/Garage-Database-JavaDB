package ErrorHandling;

public class Blanks extends Exception{

	public Blanks() {
		super();
		
	}

	public Blanks(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public Blanks(String message, Throwable cause) {
		super(message, cause);

	}

	public Blanks(String message) {
		super(message);
		System.out.println("Error");
	}

	public Blanks(Throwable cause) {
		super(cause);
	
	}


	
	
	
}
