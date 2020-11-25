package me.threedr3am.thymeleaf.controller.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.threedr3am.thymeleaf.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author threedr3am
 */
@Api("用户信息相关接口")
@Controller
@RequestMapping(value = "/auth/user")
public class UserInfoController {

    @ApiOperation("添加用户")
    @ResponseBody
    @PutMapping(value = "/{username}")
    public Result<Boolean> addUser(@PathVariable(name = "username") String username) {
        return Result.<Boolean>builder()
            .success(true)
            .result(true)
            .build();
    }

    @ApiOperation("获取用户信息")
    @ResponseBody
    @GetMapping(value = "/{username}")
    public Result<String> userInfo(@PathVariable(name = "username") String username) {
        return Result.<String>builder()
            .success(true)
            .result(username)
            .build();
    }

    @ApiOperation("修改用户信息")
    @ResponseBody
    @PostMapping(value = "/{username}")
    public Result<Boolean> modifyUser(
        @PathVariable(name = "username") String username,
        @RequestParam(name = "password") String password,
        @RequestParam(name = "phone") String phone,
        @RequestParam(name = "addr") String addr
    ) {
        return Result.<Boolean>builder()
            .success(true)
            .result(true)
            .build();
    }

    @ApiOperation("删除用户")
    @DeleteMapping(value = "/{username}")
    public void deleteUser(@PathVariable(name = "username") String username) {}
}
