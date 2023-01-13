package loyality_platform_model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoyalityPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoyalityPlatformApplication.class, args);
    }

    @RequestMapping(value="/")
    public String helloWorldTest(){
        return "Hello Word";
    }
}
