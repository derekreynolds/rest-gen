package <%= package %>.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import <%= package %>.domain.model.<%= entity %>;

/**
 * Repository for the {@link <%= entity %>}.
 * @author <%= username %>
 *
 */
public interface <%= entity %>Repository extends JpaRepository<<%= entity %>, Long> {
		
}
