package com.manneia.template.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luokaixuan
 * @description com.manneia.template.controller
 * @created 2024/9/6 10:35
 */
@RequestMapping(value = "test", produces = "application/json;charset=UTF-8")
@RestController
@Tag(name = "测试接口文档")
public class TestController {

    @GetMapping(value = "getString")
    @ApiOperationSupport(author = "luokaixuan")
    @Operation(summary = "test", description = "用于接口文档和接口的测试")
    public String test() {
        return "test";
    }
}
