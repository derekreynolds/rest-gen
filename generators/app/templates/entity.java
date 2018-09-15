package <%= package %>.domain.model;

import com.evolvingreality.common.persistence.domain.EntityBase;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

import org.springframework.hateoas.Identifiable;


/**
 * A <%= entity %>.
 * @author <%= username %>
 */
@Entity
@Table(name = "<%= entity.toUpperCase() %>")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class <%= entity %> extends EntityBase implements UserDetails, Serializable, Identifiable<Long> {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
