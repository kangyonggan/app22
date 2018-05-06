package com.kangyonggan.app.constants;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface AppConstants {

    /**
     * Hash algorithm
     */
    String HASH_ALGORITHM = "SHA-1";

    /**
     * Hash Interations
     */
    int HASH_INTERATIONS = 2;

    /**
     * Salt Size
     */
    int SALT_SIZE = 8;

    /**
     * 把验证码存放在session中的key
     */
    String KEY_CAPTCHA = "key-captcha";


}
