package deneme.deneme2;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);
}