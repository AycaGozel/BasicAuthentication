package deneme.deneme2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DummyUserService {
	
	//private static final String ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";
	@Autowired
	private PasswordEncoder passwordEncoder;

    private Map<String, User> users = new HashMap<String, User>();

    @PostConstruct
    public void initialize() {
        users.put("yusuf", new User("yusuf", passwordEncoder.encode("123"),(Collection<? extends GrantedAuthority>) new ArrayList<Object>()));
        users.put("metin", new User("metin", passwordEncoder.encode("123"),(Collection<? extends GrantedAuthority>) new ArrayList<Object>()));
        users.put("dilek", new User("dilek", passwordEncoder.encode("123"),(Collection<? extends GrantedAuthority>) new ArrayList<Object>()));
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }
}