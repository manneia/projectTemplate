package com.manneia.template.service;

import com.manneia.template.common.BaseResponse;
import com.manneia.template.model.po.UserPo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manneia.template.model.dto.UserLoginDto;
import com.manneia.template.model.dto.UserRegisterDto;
import com.manneia.template.model.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author lkx
 * @description 针对表【manneia_user(用户表)】的数据库操作Service
 * @createDate 2024-09-06 19:03:55
 */
@SuppressWarnings("unused")
public interface UserService extends IService<UserPo> {

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册请求
     * @return 返回用户id
     */
    BaseResponse<Long> userRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户登录
     * @param userLoginDto 用户登录请求
     * @param request 请求
     * @return 返回脱敏后的用户信息
     */
    BaseResponse<UserVo> userLogin(UserLoginDto userLoginDto, HttpServletRequest request);

    UserVo getLoginUserVo(UserPo userPo);

    BaseResponse<Boolean> userLogout(HttpServletRequest httpServletRequest);
}
