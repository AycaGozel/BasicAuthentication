package deneme.deneme2;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ControllerSecurity {

    @GetMapping("/deneme1")
    ///@PreAuthorize("hasAuthority('ADMIN')")
    public String hellospringsecurity() {
        return "deneme1";
    }
    @GetMapping("/deneme2")
    //@PreAuthorize("hasAuthority('USER')")
    public String hellospringsecurity2() {
        return "deneme2";
    }
    
}