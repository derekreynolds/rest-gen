package <%= package %>.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import <%= package %>.domain.model.<%= entity %>;
import <%= package %>.repository.<%= entity %>Repository;

/**
 * @author <%= username %>
 *
 */
public class <%= entity %>ServiceImplTest {


	@Mock
	private <%= entity %>Repository <%= entityCamelCase %>Repository;
	
	private <%= entity %>Service <%= entityCamelCase %>Service;
	
	public <%= entity %>ServiceImplTest() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		<%= entityCamelCase %>Service = new <%= entity %>ServiceImpl(<%= entityCamelCase %>Repository);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link <%= package %>.service.<%= entity %>ServiceImpl#save(<%= package %>.domain.model.<%= entity %>)}.
	 */
	@Test
	public void testSave() {
		
		<%= entity %> <%= entityCamelCase %> = new <%= entity %>();		
		
		<%= entityCamelCase %>Service.save(<%= entityCamelCase %>);
		
		verify(<%= entityCamelCase %>Repository, times(1)).save(<%= entityCamelCase %>);
		
	}

}