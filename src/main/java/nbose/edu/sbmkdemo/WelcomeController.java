package nbose.edu.sbmkdemo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WelcomeController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot Running Inside Docker in Minikube Pod !!";
    }
}