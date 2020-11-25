package me.threedr3am.thymeleaf.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class SessionFilter extends OncePerRequestFilter {

    private Algorithm algorithm;

    public SessionFilter(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        String token = "";
        if (cookies == null) {
            noPermission(response);
            return;
        }
        for (Cookie cookie : cookies) {
            if ("SESSION".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(token)) {
            noPermission(response);
            return;
        }
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Claim roleClaim = jwt.getClaim("role");
            if (roleClaim.isNull() || !StringUtils.equals("admin", roleClaim.asString())) {
                noPermission(response);
                return;
            }
        } catch (JWTVerificationException e) {
            log.error("jwt校验失败");
            noPermission(response);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void noPermission(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getOutputStream().write("当前用户无权访问".getBytes("UTF-8"));
        response.setStatus(403);
    }
}
