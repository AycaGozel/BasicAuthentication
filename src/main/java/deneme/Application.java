package deneme;
import java.util.HashSet;

import javax.management.relation.Role;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import deneme.deneme2.User;
import deneme.deneme2.UserRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }/*
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                
                HashSet<Role> roles = new HashSet<>();
                roles.add(new Role("user", null));
                User user = new User(1l, "username", hashedPassword, roles);
                repository.save(user);
            }
        };*/
  
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
            	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String hashedPassword = bCryptPasswordEncoder.encode("123");
                
                User user = new User("username", hashedPassword);
                repository.save(user);
            }
        };}

}