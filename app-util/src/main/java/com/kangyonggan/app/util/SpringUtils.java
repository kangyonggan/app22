package com.kangyonggan.app.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring辅助类
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    /**
     * 上下文
     */
    private static ApplicationContext context;

    /**
     * 注入上下文
     *
     * @param applicationContext 上下文
     * @throws BeansException 注入异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 把对象加入Spring上下文
     *
     * @param bean 要加入spring的bean
     */
    public static void autowire(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    /**
     * 根据bean名字获取bean
     *
     * @param beanName bean的名字
     * @return 返回bean对象
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * 根据bean的class获取bean
     *
     * @param clzz bean的class
     * @param <T>  泛型
     * @return 返回bean对象
     */
    public static <T> T getBean(Class<T> clzz) {
        return context.getBean(clzz);
    }
}
