package <%= package %>.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import <%= package %>.domain.model.<%= entity %>;
import <%= package %>.repository.<%= entity %>Repository;

/**
 * Implementation of {@link <%= entity %>Service} for the {@link <%= entity %>}.
 * @author <%= username %>
 *
 */
@Service
@Transactional(readOnly = true)
public class <%= entity %>ServiceImpl extends EntityServiceImpl<<%= entity %>> implements <%= entity %>Service {
	
	private final <%= entity %>Repository <%= entityCamelCase %>Repository;	

	@Autowired
	public <%= entity %>ServiceImpl(final <%= entity %>Repository <%= entityCamelCase %>Repository) {
		super(<%= entityCamelCase %>Repository);
		this.<%= entityCamelCase %>Repository = <%= entityCamelCase %>Repository;
	}
			
}
