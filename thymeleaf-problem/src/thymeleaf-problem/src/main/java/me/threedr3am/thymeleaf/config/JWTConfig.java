package me.threedr3am.thymeleaf.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jingfeng
 */
@Configuration
public class JWTConfig {

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256("admin");
    }
}
