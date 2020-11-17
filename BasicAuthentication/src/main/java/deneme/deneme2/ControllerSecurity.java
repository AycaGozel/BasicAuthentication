package deneme.deneme2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerSecurity {

    @GetMapping("/deneme")
    public String hellospringsecurity() {
        return "deneme";
    }
    @GetMapping("/deneme2")
    public String hellospringsecurity2() {
        return "deneme2";
    }
    
}