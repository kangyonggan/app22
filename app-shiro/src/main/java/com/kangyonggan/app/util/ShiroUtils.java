package com.kangyonggan.app.util;

import com.kangyonggan.app.bean.ShiroUser;
import org.apache.shiro.SecurityUtils;

/**
 * @author kangyonggan
 * @since 5/5/18
 */
public class ShiroUtils {

    /**
     * 获取当前登录的用户
     *
     * @return 返回当前登录的用户
     */
    public static ShiroUser getShiroUser() {
        try {
            return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前登录的用户名
     *
     * @return 返回当前登录的用户名
     */
    public static String getShiroUsername() {
        try {
            return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUsername();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return 返回当前登录用户的ID
     */
    public static Long getShiroId() {
        try {
            return ((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getId();
        } catch (Exception e) {
            return null;
        }
    }

}
