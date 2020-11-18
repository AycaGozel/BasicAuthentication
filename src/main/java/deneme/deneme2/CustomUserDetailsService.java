package deneme.deneme2;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
   // private DummyUserService dummyUserService;
	 /*  @Autowired
	    private DummyUserService dummyUserService;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return dummyUserService.getUserByUsername(username);
	    }
	    */
        @Autowired
        private  UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> byUsername = userRepository.findByUsername(username);

            return byUsername.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı"));
           
        }
    }

