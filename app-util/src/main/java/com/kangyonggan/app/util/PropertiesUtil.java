package com.kangyonggan.app.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public final class PropertiesUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private PropertiesUtil() {

    }

    /**
     * 读取配置文件
     *
     * @param in      配置文件的输入流
     * @param charset 编码
     * @return 返回配置
     * @throws Exception 可能会发生的异常
     */
    public static Properties readProperties(InputStream in, String charset) throws Exception {
        Properties props = new Properties();
        try {
            InputStreamReader reader = new InputStreamReader(in, charset);
            props.load(reader);
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return props;
    }

    /**
     * 读取配置文件
     *
     * @param in 配置文件的输入流
     * @return 返回配置
     * @throws Exception 可能会发生的异常
     */
    public static Properties readProperties(InputStream in) throws Exception {
        return readProperties(in, "UTF-8");
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @param charset  编码
     * @return 返回配置
     * @throws Exception 可能会发生的异常
     */
    public static Properties readProperties(String fileName, String charset) throws Exception {
        return readProperties(new FileInputStream(fileName), charset);
    }

    /**
     * 读取配置文件
     *
     * @param fileName 配置文件的全路径
     * @return 返回配置
     * @throws Exception 可能会发生的异常
     */
    public static Properties readProperties(String fileName) throws Exception {
        return readProperties(fileName, "UTF-8");
    }
}
