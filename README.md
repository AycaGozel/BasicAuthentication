# BasicAuthentication
Spring Security Basic Authentication using JWT Rest Service - H2 Database. 

## Installation
Eclipse IDE
Java 11 
Postman 

## File structure
```
BasicAuthentication/
 │
 ├── src/main/java/
 │   └── deneme
 │       ├── deneme.deneme2.Authentication
 │       │   └── AuthRestController.java
 │       │   └── AuthRest.java
 │       │
 │       ├── deneme.deneme2.Controller
 │       │   └── ControllerSecurity.java
 │       │
 │       ├── deneme.deneme2.Model
 │       │   ├── User.java
 │       │   └── Role.java
 │       │
 │       ├── deneme.deneme2.Service
 │       │   ├── CustomUserDetailsService.java
 │       │   └── DummyUserService.java
 │       │   └── UserService.java
 │       │
 │       ├── deneme.deneme2.Repository
 │       │   └── UserRepository.java
 │       │
 │       ├── deneme.deneme2.Security
 │       │   ├── JwtFilter.java
 │       │   ├── JwtUtil.java
 │       │   ├── SpringSecurityConfig.java
 │       │
 │       │
 │       └── Application.java
 │
 ├── src/main/resources/
 │   └── application.properties
 │
 ├── .gitignore
 ├── LICENSE
 ├── README.md
 └── pom.xml
```
## Dependency
Dependencies are added for SpringBoot Security, H2 Database, Data Jpa (to use annotations), Json web token.
```
 <dependencies>  
 	   <dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-web</artifactId>
	   </dependency>
     <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
     </dependency>
     <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter-data-jpa</artifactId>
     </dependency>
     <dependency>
		        <groupId>org.springframework.boot</groupId>
		        <artifactId>spring-boot-starter-security</artifactId>
     </dependency>
     <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.2.3.Final</version>
     </dependency>
	   <dependency>
		        <groupId>io.jsonwebtoken</groupId>
		        <artifactId>jjwt</artifactId>
		        <version>0.9.1</version>
     </dependency>
</dependencies>  
```


## How to use this code?
1. Run the Application.java class by selecting the "Maven Build.." as an option . Application.java class starts the Spring Framework and applies user to the H2 database.
2. In order to generate token from the Postman, POST the JSON rows to the http://localhost:8080/login URI. Token will be generated as a respond.
```
{
"username":"username",
"password":"123"
}
```
3. To authantica the login page, you can either GET the http://localhost:8080/deneme1 or http://localhost:8080/deneme2 URIs. Both of them are possible. By copying the token to the Bearer Token from the Authorization tab, 
the authentication process will be completed as succesfully. 


## Implementation Details

The project includes two different type of usage. If the H2 database related dependencies and UserService and UserRepository classeses are commented, the project can be executed 
without using H2 DB insertion.
# Token Generation

Spring Security creates the login page default and /login URI to authenticate to change the path as /deneme1 and /deneme2 we should change the controller by adding the 
@GetMapping annotation.
``` java
@RestController
public class ControllerSecurity {
    @GetMapping("hello")
    public String hellospringsecurity() {
        return "Hello";
    }
}
```

Also give permission to authenticated users to this path from the SecurityConfig.java class. Also, sessionCreationPolicy should be STATELESS. In order to
generate token jwtFilter object should be applied to the addFilterBefore method. 
```
 protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
```
If requested username and password is matched, the token will be generated from the AuthRestController class on the createToken method. 
Do not forget to add @PostMapping annotation to give a path for token generation.
``` java
 @PostMapping("/login")
    public String creteToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }
```
# User Info Generation
UserRepository object will be sent to loadUserByUserName method to get the username. 
``` java 
@Service
public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private  UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> byUsername = userRepository.findByUsername(username);
        }
    }
```
Username info will be gotten from the User.java class. 
``` java 
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(@Param("username") String username);
}
```
# H2 DB Connection
User info can be taken from the H2 DB. At the beginning, the user info should be given on the CommandLineRunner method on the Application.class.
The important thing is the password info should be encripted by using bCryptPasswordEncoder, otherwise 403 authentication error will be generated and Encoded password does not 
BCripted error will be occured.

``` java 
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
```        

The detailed configuration info should be apply on the application.properties for H2 database. Browser to see the H2 db default URL is http://localhost:/h2-console,
also it is available to custom the URL  by adding this line spring.h2.console.path=/h2 to application.properties.
 ```  java     
   spring.application.name=SpringbootH2Database
spring.datasource.url = jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.hibernate.ddl-auto=update  # To reach the db and the entity classes, do not write none!
spring.h2.console.enabled=true # Enabling H2 Console
  ``` 
To observe the H2 web browser, the /h2 path should be permitted and http.headers().frameOptions().disable(); line should be applied to the http headers on the SecurityConfig class.
```
 protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable();
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
```
        
        
        
