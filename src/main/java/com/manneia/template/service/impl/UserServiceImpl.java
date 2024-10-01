package com.manneia.template.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manneia.template.common.BaseResponse;
import com.manneia.template.constant.UserConstant;
import com.manneia.template.enums.ErrorCode;
import com.manneia.template.exception.BusinessException;
import com.manneia.template.mapper.UserMapper;
import com.manneia.template.model.dto.UserLoginDto;
import com.manneia.template.model.dto.UserRegisterDto;
import com.manneia.template.model.po.UserPo;
import com.manneia.template.model.vo.UserVo;
import com.manneia.template.service.UserService;
import com.manneia.template.utils.MD5Utils;
import com.manneia.template.utils.ResultUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author lkx
 * @description 针对表【manneia_user(用户表)】的数据库操作Service实现
 * @createDate 2024-09-06 19:03:55
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPo>
        implements UserService {

    // 定义一个类级别的锁对象
    private final Object lock = new Object();
    @Resource
    private UserMapper userMapper;

    @Resource
    private MD5Utils md5Utils;

    @Override
    public BaseResponse<Long> userRegister(UserRegisterDto userRegisterDto) {
        if (ObjectUtil.isNull(userRegisterDto) &&
                ObjectUtil.isEmpty(userRegisterDto)) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, ErrorCode.PARAMS_NULL_ERROR.getMessage());
        }
        String userAccount = userRegisterDto.getUserAccount();
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        if (username.length() < 2 || username.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度必须在2-20位之间");
        }
        boolean passwordLength = password.length() < 8 || password.length() > 30;
        if (passwordLength) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度必须在8-30位之间");
        }
        if (md5Utils.isValidPassword(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码必须包含数字、字母、特殊字符至少两种");
        }
        if (checkPassword.length() != password.length()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码长度不一致");
        }
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (lock) {
            // 账户不能重复
            Long count = userMapper.selectCount(Wrappers.lambdaQuery(UserPo.class)
                    .eq(UserPo::getUserAccount, userAccount));
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户已存在");
            }
            // 加密
            String encryptPassword = md5Utils.encrypt(password);
            UserPo userPo = new UserPo();
            userPo.setUsername(username);
            userPo.setUserAccount(userAccount);
            userPo.setUserPassword(encryptPassword);
            boolean save = this.save(userPo);
            if (Boolean.FALSE.equals(save)) {
                throw new BusinessException(ErrorCode.SQL_ERROR, "注册失败," + ErrorCode.SQL_ERROR.getMessage());
            }
            return ResultUtils.success(userPo.getId());
        }
    }

    @Override
    public BaseResponse<UserVo> userLogin(UserLoginDto userLoginDto, HttpServletRequest request) {
        // 1. 校验登录入参
        if (ObjectUtil.isNull(userLoginDto) &&
                ObjectUtil.isEmpty(userLoginDto)) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, ErrorCode.PARAMS_NULL_ERROR.getMessage());
        }
        String userAccount = userLoginDto.getUserAccount();
        String userPassword = userLoginDto.getUserPassword();
        if (userAccount.length() < 2 || userAccount.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度必须在2-20位之间");
        }
        if (userPassword.length() < 8 || userPassword.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度必须在8-30位之间");
        }
        // 2. 加密, 校验用户是否存在
        String encryptPassword = md5Utils.encrypt(userPassword);
        UserPo userPo = userMapper.selectOne(Wrappers.lambdaQuery(UserPo.class)
                .eq(UserPo::getUserAccount, userAccount)
                .eq(UserPo::getUserPassword, encryptPassword));
        if (ObjectUtil.isNotNull(userPo)) {
            // 3. 记录用户的登录态
            request.getSession().setAttribute(UserConstant.USER_STATUS_LOGIN, userPo);
            return ResultUtils.success(this.getLoginUserVo(userPo));
        } else {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, null, "用户不存在");
        }
    }

    @Override
    public UserVo getLoginUserVo(UserPo userPo) {
        if (userPo == null) {
            return null;
        }
        UserVo loginUserVo = new UserVo();
        BeanUtils.copyProperties(userPo, loginUserVo);
        return loginUserVo;
    }

    @Override
    public BaseResponse<Boolean> userLogout(HttpServletRequest httpServletRequest) {
        return null;
    }
}




