package <%= package %>.service;

import <%= package %>.domain.model.<%= entity %>;

public class <%= entity %>TestUtil {

	/**
	 * Creates a default {@link <%= entity %>}
	 * @return {@link <%= entity %>}
	 */
	public static <%= entity %> create() {
		
		<%= entity %> <%= entityCamelCase %> = new <%= entity %>();		
	
		return <%= entityCamelCase %>;
	}
	
}