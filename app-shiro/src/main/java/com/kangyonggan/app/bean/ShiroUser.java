package com.kangyonggan.app.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 当前登录的用户
 *
 * @author kangyonggan
 * @since 5/5/18
 */
@Data
public class ShiroUser implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;
}
