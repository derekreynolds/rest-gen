package <%= package %>.web.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import <%= package %>.domain.model.<%= entity %>;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


/**
 * Resource representation of the {@link <%= entity %>}
 * 
 * @author <%= username %>
 *
 */
public class <%= entity %>Resource extends ResourceSupport {

	@JsonUnwrapped
	private final <%= entity %> <%= entityCamelCase %>;
		
	public <%= entity %>Resource(final <%= entity %> <%= entityCamelCase %>) {
		this.<%= entityCamelCase %> = <%= entityCamelCase %>;		
	}
	
}
