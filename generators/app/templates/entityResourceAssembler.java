package <%= package %>.web.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import <%= package %>.domain.model.<%= entity %>;
import <%= package %>.web.rest.<%= entity %>Controller;

/**
 * Assembler for {@link <%= entity %>} to {@link <%= entity %>Resource}.
 * 
 * @author <%= username %>
 *
 */
@Component
public class <%= entity %>ResourceAssembler extends ResourceAssemblerSupport<<%= entity %>, <%= entity %>Resource> {
	

    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";
		 
	private final EntityLinks entityLinks;
		
	@Autowired
	public <%= entity %>ResourceAssembler(final EntityLinks entityLinks) {
		super(<%= entity %>Controller.class, <%= entity %>Resource.class);
		this.entityLinks = entityLinks;
	}

	@Override
	public <%= entity %>Resource toResource(final <%= entity %> <%= entityCamelCase %>) {	
		
		<%= entity %>Resource resource = new <%= entity %>Resource(<%= entityCamelCase %>);
		
		final Link selfLink = entityLinks.linkToSingleResource(<%= entityCamelCase %>);
	    
		resource.add(selfLink.withSelfRel());
	    resource.add(selfLink.withRel(UPDATE_REL));
	    resource.add(selfLink.withRel(DELETE_REL));				
				
		return resource;
	}
	
}
