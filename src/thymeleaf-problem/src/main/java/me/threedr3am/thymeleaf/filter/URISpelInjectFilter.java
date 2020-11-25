package me.threedr3am.thymeleaf.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author threedr3am
 */
public class URISpelInjectFilter extends OncePerRequestFilter {

    private final Pattern pattern = Pattern.compile("'|\"");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = URLDecoder.decode(request.getRequestURI(), "UTF-8");
        if (pattern.matcher(uri).find()) {
            response.getOutputStream().println("You can't input SPEL to URI,unless you can bypass.");
            return;
        }
        filterChain.doFilter(request, response);
    }
}

