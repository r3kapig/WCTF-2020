package me.threedr3am.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author threedr3am
 */
@SpringBootApplication
public class ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ThymeleafApplication.class);
        app.run(args);
    }
}
