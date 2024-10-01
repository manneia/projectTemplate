package com.manneia.template.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.manneia.template.common.BaseResponse;
import com.manneia.template.model.dto.UserLoginDto;
import com.manneia.template.model.dto.UserRegisterDto;
import com.manneia.template.model.vo.UserVo;
import com.manneia.template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luokaixuan
 * @description 用户控制类
 * @created 2024/9/6 19:12
 */
@RestController
@RequestMapping(value = "user", produces = "application/json;charset=UTF-8")
@Tag(name = "用户接口文档")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "register")
    @ApiOperationSupport(author = "luokaixuan")
    @Operation(summary = "用户注册", description = "用于用户注册")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.userRegister(userRegisterDto);
    }

    @PostMapping(value = "login")
    @ApiOperationSupport(author = "luokaixuan")
    @Operation(summary = "用户登录", description = "用于用户登录")
    public BaseResponse<UserVo> userLogin(
            @RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        return userService.userLogin(userLoginDto, request);
    }

    @PostMapping(value = "logout")
    @ApiOperationSupport(author = "luokaixuan")
    @Operation(summary = "退出登录", description = "用于用户退出登录")
    public BaseResponse<Boolean> userLogout(HttpServletRequest httpServletRequest) {
        return userService.userLogout(httpServletRequest);
    }
}
