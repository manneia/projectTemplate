package com.manneia.template.model.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @author lkx
 * @description 返回给前端的用户信息
 */
@Data
public class UserVo implements Serializable {
    /**
     * 主键用户唯一标识
     */
    private Long id;

    /**
     * 用户登录账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别: 0-男 1-女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态: 0-正常 1-禁用
     */
    private Integer userStatus;

    /**
     * 用户角色: 0-普通用户 2-vip用户 3-管理员
     */
    private Integer userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}