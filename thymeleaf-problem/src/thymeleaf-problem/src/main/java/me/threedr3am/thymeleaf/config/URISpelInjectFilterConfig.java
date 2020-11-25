package me.threedr3am.thymeleaf.config;

import me.threedr3am.thymeleaf.filter.URISpelInjectFilter;
import java.util.Collections;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author threedr3am
 */
@Configuration
public class URISpelInjectFilterConfig {

    @Bean
    FilterRegistrationBean<URISpelInjectFilter> uriSpelInjectFilter() {
        URISpelInjectFilter uriSpelInjectFilter = new URISpelInjectFilter();
        FilterRegistrationBean<URISpelInjectFilter> bean = new FilterRegistrationBean<>(uriSpelInjectFilter);
        bean.setUrlPatterns(Collections.singletonList("/auth/*"));
        bean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return bean;
    }

}
