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

/**
 * @author threedr3am
 */
@Api("角色相关接口")
@Controller
@RequestMapping(value = "/auth/role")
public class RoleController {

    @ApiOperation("添加角色")
    @ResponseBody
    @PutMapping(value = "/{role}")
    public Result<Boolean> addRole(@PathVariable(name = "role") String role) {
        return Result.<Boolean>builder()
            .success(true)
            .result(true)
            .build();
    }

    @ApiOperation("获取角色信息")
    @ResponseBody
    @GetMapping(value = "/{role}")
    public Result<String> role(@PathVariable(name = "role") String role) {
        return Result.<String>builder()
            .success(true)
            .result(role)
            .build();
    }

    @ApiOperation("修改角色信息")
    @ResponseBody
    @PostMapping(value = "/{role}")
    public Result<Boolean> modifyRole(
        @PathVariable(name = "role") String role,
        @RequestParam(name = "desc") String desc
    ) {
        return Result.<Boolean>builder()
            .success(true)
            .result(true)
            .build();
    }

    @ApiOperation("删除角色")
    @DeleteMapping(value = "/{role}")
    public Result<Boolean> deleteRole(@PathVariable(name = "role") String role) {
        return Result.<Boolean>builder()
            .success(true)
            .result(true)
            .build();
    }
}
