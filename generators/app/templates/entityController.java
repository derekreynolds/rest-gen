package <%= package %>.web.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evolvingreality.common.web.util.WebUtil;
import <%= package %>.domain.exception.<%= entity %>NotFoundException;
import <%= package %>.domain.model.<%= entity %>;
import <%= package %>.service.<%= entity %>Service;
import <%= package %>.web.rest.resource.<%= entity %>Resource;
import <%= package %>.web.rest.resource.<%= entity %>ResourceAssembler;
import <%= package %>.web.rest.validator.<%= entity %>Validator;

/**
 * Rest controller for a {@link <%= entity %>}.
 * 
 * @author <%= username %>
 *
 */
@CrossOrigin(origins = "*")
@ExposesResourceFor(<%= entity %>.class)
@RestController
@RequestMapping(value=<%= entity %>Controller.URI, produces = WebUtil.MEDIA_TYPE_HAL_JSON)
public class <%= entity %>Controller {

    public static final URI ="/api/<%= entity.toLowerCase() %>s";

	private final Logger log = LoggerFactory.getLogger(getClass());
    
    private final <%= entity %>Service <%= entityCamelCase %>Service;
    
    private final <%= entity %>ResourceAssembler <%= entityCamelCase %>ResourceAssembler;    
   
    private final PagedResourcesAssembler<<%= entity %>> <%= entityCamelCase %>PagedResourcesAssembler;
    
    @Autowired
    public <%= entity %>Controller(final <%= entity %>Service <%= entityCamelCase %>Service, final <%= entity %>ResourceAssembler <%= entityCamelCase %>ResourceAssembler, final PagedResourcesAssembler<<%= entity %>> <%= entityCamelCase %>PagedResourcesAssembler) {
    	this.<%= entityCamelCase %>Service = <%= entityCamelCase %>Service;
    	this.<%= entityCamelCase %>ResourceAssembler = <%= entityCamelCase %>ResourceAssembler;
        this.<%= entityCamelCase %>PagedResourcesAssembler = <%= entityCamelCase %>PagedResourcesAssembler;
    }
    
    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.setValidator(new <%= entity %>Validator());
    }
   
	/**
	 * Gets all the {@link <%= entity %>}.
	 * @param pageable - the {@link Pageable} into the {@link <%= entity %>} we want.
	 * @return a {@link PagedResources} containing the {@link <%= entity %>Resource}.
	 */
    @GetMapping
    public PagedResources<<%= entity %>Resource> getAll(final Pageable pageable) {
        log.debug("REST request to get all <%= entity %>");
        return <%= entityCamelCase %>PagedResourcesAssembler.toResource(<%= entityCamelCase %>Service.findAll(pageable), this.<%= entityCamelCase %>ResourceAssembler);
    }

    /**
     * Get the {@link <%= entity %>} for the id 
     * @param id - the id of the <%= entityCamelCase %> we want.
     * @return a {@link ResponseEntity} with HTTP OK with a {@link <%= entity %>Resource}.
     * @exception <%= entity %>NotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<<%= entity %>Resource> get(@PathVariable final Long id) {
    	
        log.debug("REST request to get <%= entity %> : {}", id);
        
        return new ResponseEntity<<%= entity %>Resource>(this.<%= entityCamelCase %>ResourceAssembler.toResource(<%= entityCamelCase %>Service.findOne(id).orElseThrow(() -> new <%= entity %>NotFoundException())), HttpStatus.OK);

    }
    
    /**
     * Post a {@link <%= entity %>} to be created.
     * 
     * @param <%= entityCamelCase %> - the {@link <%= entity %>} to create
     * @return a {@link ResponseEntity} with HTTP OK with a {@link <%= entity %>Resource}.
     */
    @PostMapping
    public ResponseEntity<<%= entity %>Resource> post(@Valid @RequestBody final <%= entity %> <%= entityCamelCase %>) {
    	
    	log.debug("REST request to create <%= entity %> : {}", <%= entityCamelCase %>);
    	  	    	
    	return new ResponseEntity<<%= entity %>Resource>(this.<%= entityCamelCase %>ResourceAssembler.toResource(this.<%= entityCamelCase %>Service.save(<%= entityCamelCase %>)), HttpStatus.CREATED);    			

    }
    
    /**
     * Put a {@link <%= entity %>} to be updated.
     * @param id - the id of the <%= entityCamelCase %>.
     * @param <%= entityCamelCase %> - the {@link <%= entity %>} to update.
     * @return a {@link ResponseEntity} with HTTP OK with a {@link<%= entity %>Resource}.
     */
    @PutMapping("/{id}")
    public ResponseEntity<<%= entity %>Resource> put(@PathVariable final Long id, @RequestBody final <%= entity %> <%= entityCamelCase %>) {
    	
    	log.debug("REST request to update <%= entity %> : {}", <%= entityCamelCase %>.getId());
    	
    	return ResponseEntity.ok(this.<%= entityCamelCase %>ResourceAssembler.toResource(this.<%= entityCamelCase %>Service.save(<%= entityCamelCase %>)));
  
    }
    
	/**
	 * Delete a {@link <%= entity %>}
	 * @param id - the id of the {@link <%= entity %>} to delete
	 * @return a {@link ResponseEntity} with HTTP NO_CONTENT.
	 */
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
    	
    	log.debug("REST request to delete <%= entity %> : {}", id);
    	
    	this.<%= entityCamelCase %>Service.delete(id);
    	
    	return ResponseEntity.noContent().build();    			

    }	
    
}
