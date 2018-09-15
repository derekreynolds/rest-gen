package <%= package %>.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import <%= package %>.Application;
import <%= package %>.domain.model.<%= entity %>;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <%= username %>
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class <%= entity %>ServiceImplIT {

	@Autowired
	private <%= entity %>Service <%= entityCamelCase %>Service;

	
	/**
	 * Test method for {@link <%= package %>.service.<%= entity %>ServiceImpl#save(<%= package %>.domain.model.<%= entity %>)}.
	 */
	@Test
	public void testSaveSuccess() {
		
		<%= entity %> <%= entityCamelCase %> = <%= entity %>TestUtil.create();
				
		<%= entityCamelCase %>Service.save(<%= entityCamelCase %>);
		
		assertThat(<%= entityCamelCase %>.getId()).isNotNull();
		assertThat(<%= entityCamelCase %>.getCreatedDate()).isNotNull();
		assertThat(<%= entityCamelCase %>.getCreatedBy()).isEqualTo("system");
		assertThat(<%= entityCamelCase %>.getLastModifiedDate()).isNotNull();
		assertThat(<%= entityCamelCase %>.getLastModifiedBy()).isEqualTo("system");
	}

}