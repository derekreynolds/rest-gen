package <%= package %>.web.rest;

import com.evolvingreality.common.web.util.TestUtil;
import com.evolvingreality.common.web.util.WebUtil;
import <%= package %>.domain.model.<%= entity %>;
import <%= package %>.service.<%= entity %>ServiceImpl;
import <%= package %>.domain.<%= entity %>TestUtil;
import <%= package %>.web.rest.resource.<%= entity %>ResourceAssembler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.mvc.ControllerLinkBuilderFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

/**
 * Test class for the <%= entity %>Controller REST controller.
 *
 * @see <%= entity %>Controller
 */
public class <%= entity %>ControllerTest {

    @Mock
    private <%= entity %>ServiceImpl <%= entityCamelCase %>Service;
    
    private <%= entity %>ResourceAssembler <%= entityCamelCase %>ResourceAssembler = new <%= entity %>ResourceAssembler(new ControllerEntityLinks(Collections.singleton(<%= entity %>Controller.class), new ControllerLinkBuilderFactory()));

    private PagedResourcesAssembler<<%= entity %>> <%= entityCamelCase %>PagedResourcesAssembler = new PagedResourcesAssembler<<%= entity %>>(new HateoasPageableHandlerMethodArgumentResolver(), null);

    private MockMvc restMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        <%= entity %>Controller <%= entityCamelCase %>Controller = new <%= entity %>Controller(<%= entityCamelCase %>Service, <%= entityCamelCase %>ResourceAssembler, <%= entityCamelCase %>PagedResourcesAssembler);

        this.restMvc = MockMvcBuilders.standaloneSetup(<%= entityCamelCase %>Controller)
        		.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();

    }

    @Test
    public void testGetAll() throws Exception {
    	
    	<%= entity %> <%= entityCamelCase %>1 = <%= entity %>TestUtil.create();
    	<%= entity %> <%= entityCamelCase %>2 = <%= entity %>TestUtil.create();
    	
    	List<<%= entity %>> <%= entityCamelCase %>s = new ArrayList<>();
    	<%= entityCamelCase %>s.add(<%= entityCamelCase %>1);
    	<%= entityCamelCase %>s.add(<%= entityCamelCase %>2);
    	
    	Page<<%= entity %>> page = new PageImpl<>(<%= entityCamelCase %>s, new PageRequest(0, 20), 2);
    	
    	doReturn(page).when(<%= entityCamelCase %>Service).getPage(any());
    	
    	restMvc.perform(get(<%= entity %>Controller.URI + "?page=0&size=20")
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalPages").value(1))
                .andExpect(jsonPath("$.page.size").value(20))
                .andExpect(jsonPath("$.page.totalElements").value(2))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andDo(print());
    }
    
    @Test
    public void testGetOne() throws Exception {
    	
    	Optional<<%= entity %>> <%= entityCamelCase %> = Optional.of(<%= entity %>TestUtil.create());
    	    	    	
    	doReturn(<%= entityCamelCase %>).when(<%= entityCamelCase %>Service).get(1L);
    	  	
    	restMvc.perform(get(<%= entity %>Controller.URI + "/1")
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Test
    public void testPost() throws Exception {
    	
    	<%= entity %> <%= entityCamelCase %> = <%= entity %>TestUtil.create();
    	    
    	<%= entityCamelCase %>.setId(1L);
    	
    	Optional<<%= entity %>> <%= entityCamelCase %>Optional = Optional.of(<%= entityCamelCase %>);
    	
    	doReturn(<%= entityCamelCase %>Optional).when(<%= entityCamelCase %>Service).get(1L);    	
    	
    	doAnswer(new Answer<<%= entity %>>() {
            public <%= entity %> answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                if (args[0] instanceof <%= entity %>)
                	((<%= entity %>)args[0]).setId(1L);
                
                return (<%= entity %>)args[0];
            }
        }).when(<%= entityCamelCase %>Service).save(any(<%= entity %>.class));    	
    	
    	restMvc.perform(post(<%= entity %>Controller.URI)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(<%= entityCamelCase %>))
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andDo(print());
    	
    	verify(<%= entityCamelCase %>Service, times(1)).save(any(<%= entity %>.class));
    }
    
    @Test
    public void testPostConflict() throws Exception {
    	
    	<%= entity %> <%= entityCamelCase %> = <%= entity %>TestUtil.create();
    	    
    	<%= entityCamelCase %>.setId(1L);    	
    	
    	Optional<<%= entity %>> <%= entityCamelCase %>Optional = Optional.of(<%= entityCamelCase %>);
    	
    	doReturn(<%= entityCamelCase %>Optional).when(<%= entityCamelCase %>Service).get(1L);
    	    	    	                      
    	restMvc.perform(post(<%= entity %>Controller.URI)
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(<%= entityCamelCase %>))
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isConflict())
                .andDo(print());
    }
    
    @Test
    public void testPut() throws Exception {
    	
    	<%= entity %> <%= entityCamelCase %> = <%= entity %>TestUtil.create();  
        <%= entityCamelCase %>.setId(1L);  

        doAnswer(new Answer<<%= entity %>>() {
            public <%= entity %> answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                
                return (<%= entity %>)args[0];
            }
        }).when(<%= entityCamelCase %>Service).save(any(<%= entity %>.class));	    
   	
    	restMvc.perform(put(<%= entity %>Controller.URI + "/1")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
    			.content(TestUtil.convertObjectToJsonBytes(<%= entityCamelCase %>))
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    	
    	verify(<%= entityCamelCase %>Service, times(1)).save(any(<%= entity %>.class));
    }
    
    @Test
    public void testDelete() throws Exception {
    	   	
    	restMvc.perform(delete(<%= entity %>Controller.URI + "/1")
    			.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(WebUtil.MEDIA_TYPE_HAL_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    	
    	verify(<%= entityCamelCase %>Service, times(1)).delete(anyLong());
    }

}