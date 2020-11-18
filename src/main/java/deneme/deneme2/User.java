package deneme.deneme2;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	//@Column(name="username",nullable=false,unique=true)
    private String username;
    
    //@Column(name="password",nullable=false)
    private String encryptedPassword;
   
    public User( String username, String password,String role) {
	
		this.username = username;
		this.encryptedPassword = password;
	}
    public User( String username, String password) {
    	
		this.username = username;
		this.encryptedPassword = password;
	}
    public User() {
	}
   //@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   // @JoinTable(name = "roles", joinColumns = @JoinColumn(name = "role"))
    
    //@OneToMany(fetch = FetchType.LAZY)
   // @JoinColumn(name = "role", nullable = false)
    
    public User(long l, String string, String hashedPassword, HashSet<Role> roles2) {
		// TODO Auto-generated constructor stub
	}
     /*
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return (Collection<? extends GrantedAuthority>) roles;}
*/
    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}



