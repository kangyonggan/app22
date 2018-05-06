package com.kangyonggan.app.bean;

import lombok.Data;

/**
 * 当前登录的用户
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Data
public class ShiroUser {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

}
