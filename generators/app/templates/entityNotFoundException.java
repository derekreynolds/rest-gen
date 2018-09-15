package <%= package %>.domain.exception;

/**
 * Exception thrown when a {@link <%= entity %>} can't be found.
 * 
 * @author <%= username %>
 *
 */
public class <%= entity %>NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public <%= entity %>NotFoundException() {
		super();
	}
	
	public <%= entity %>NotFoundException(final String message) {
		super(message);
	}

	public <%= entity %>NotFoundException(final String message, final Exception exception) {
		super(message, exception);
	}
	
}