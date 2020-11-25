package me.threedr3am.thymeleaf.config;

import com.auth0.jwt.algorithms.Algorithm;
import java.util.Collections;
import me.threedr3am.thymeleaf.filter.SessionFilter;
import me.threedr3am.thymeleaf.filter.URISpelInjectFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author threedr3am
 */
@Configuration
public class SessionFilterConfig {

    @Bean
    FilterRegistrationBean<SessionFilter> sessionFilter(Algorithm algorithm) {
        SessionFilter sessionFilter = new SessionFilter(algorithm);
        FilterRegistrationBean<SessionFilter> bean = new FilterRegistrationBean<>(sessionFilter);
        bean.setUrlPatterns(Collections.singletonList("/auth/*"));
        bean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return bean;
    }

}
