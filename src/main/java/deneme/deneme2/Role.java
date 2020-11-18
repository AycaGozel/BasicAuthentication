package deneme.deneme2;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private String role;

	  public Role(String role) {
			super();
			this.role = role;
		}
	  public Role() {
			
		}
    @Override
    public String getAuthority() {
        return role;
    }

}