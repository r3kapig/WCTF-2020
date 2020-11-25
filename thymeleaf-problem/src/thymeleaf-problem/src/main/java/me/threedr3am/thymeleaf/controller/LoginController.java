package me.threedr3am.thymeleaf.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import me.threedr3am.thymeleaf.entity.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author threedr3am
 */
@Slf4j
@Api("登录相关接口")
@Controller
public class LoginController {

    private Algorithm algorithm;

    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public String login(
        @RequestParam(name = "username") String username, @RequestParam(name = "password") String password,
        HttpServletResponse response
        ) {
        if (StringUtils.equals("admin", username) && StringUtils.equals("akdhbquygenjkqndjknamlkndm24i284u2kjsnfjhbwhjebqnlknakjdnakj2428472y4782", password)) {
            Date iatDate = new Date();

            String token = JWT.create()
                .withIssuer("XX-Manager")
                .withIssuedAt(iatDate)
                .withClaim("role", "admin")
                .sign(algorithm);

            Cookie cookie = new Cookie("SESSION", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(2 * 60 * 24);
            response.addCookie(cookie);

            return "home";
        }
        return "login-error";
    }

    @ApiOperation("登录页")
    @GetMapping(value = "/login")
    public String login(
        @CookieValue(value = "SESSION", required = false) String session,
        HttpServletResponse response
    ) {
        if (StringUtils.isEmpty(session)) {
            Date iatDate = new Date();

            String token = JWT.create()
                .withIssuer("XX-Manager")
                .withIssuedAt(iatDate)
                .withClaim("role", "guest")
                .sign(algorithm);

            Cookie cookie = new Cookie("SESSION", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(2 * 60 * 24);
            response.addCookie(cookie);
            session = token;
        }
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(session);
            Claim roleClaim = jwt.getClaim("role");
            if (!roleClaim.isNull() && StringUtils.equals("admin", roleClaim.asString())) {
                return "home";
            }
        } catch (Exception e) {
            log.error("jwt校验失败");
        }
        return "login";
    }

    @Autowired
    public LoginController(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
