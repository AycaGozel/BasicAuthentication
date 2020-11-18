package deneme.deneme2;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private  UserRepository userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userService.findByUsername(username);

        return byUsername.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı Bulunamadı"));
    }
}
//public ve finalları sildim
// interface UserRepository extends CrudRepository<User, Long> {

    //Optional<User> findByUsername(@Param("username") String username);
//}
 
 

 // Save student entity in the h2 database.
 //public void save(final Student student) {
    // repository.save(student);
 //}

 // Get all students from the h2 database.
 //public List<Student> getAll() {
    // final List<Student> students = new ArrayList<>();
   //  repository.findAll().forEach(student -> students.add(student));
  //   return students;
 //}
