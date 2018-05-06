package com.kangyonggan.app.util;

import org.pegdown.PegDownProcessor;

/**
 * markdown工具类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
public final class MarkdownUtil {

    /**
     * 私有构造, 任何时候都不能实例化
     */
    private MarkdownUtil() {

    }

    /**
     * markdown语法转html语法
     *
     * @param markdown markdown语法字符串
     * @return 返回html
     */
    public static String markdownToHtml(String markdown) {
        return new PegDownProcessor(Integer.MAX_VALUE).markdownToHtml(markdown);
    }

}
