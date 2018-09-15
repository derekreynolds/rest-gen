package <%= package %>.web.rest.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import <%= package %>.domain.model.<%= entity %>;

/**
 * Validator for the {@link <%= entity %>}. 
 * 
 * @author <%= username %>
 *
 */
public class <%= entity %>Validator implements Validator {
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return <%= entity %>.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		<%= entity %> <%= entityCamelCase %> = (<%= entity %>)target; 
		
	}

}
