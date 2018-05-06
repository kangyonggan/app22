package com.kangyonggan.app.handle;

import com.alibaba.fastjson.JSON;
import com.kangyonggan.extra.core.handle.LogHandle;
import lombok.extern.log4j.Log4j2;

/**
 * @author kangyonggan
 * @since 5/5/18
 */
@Log4j2
public class Log4j2Handle implements LogHandle {

    /**
     * 包名
     */
    private String packageName;

    public Log4j2Handle(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 在方法调用前调用
     *
     * @param methodName 方法名
     * @param params     参数
     */
    @Override
    public void logBefore(String methodName, Object... params) {
        log(methodName, String.format("方法入参：%s", JSON.toJSONString(params)));
    }

    /**
     * 在方法调用后调用
     *
     * @param methodName  方法名
     * @param startTime   开始时间
     * @param returnValue 返回值
     * @return 返回方法的返回值
     */
    @Override
    public Object logAfter(String methodName, Long startTime, Object returnValue) {
        log(methodName, String.format("方法出参：%s", JSON.toJSONString(returnValue)));
        log(methodName, String.format("方法耗时：%d毫秒", System.currentTimeMillis() - startTime));
        return returnValue;
    }

    /**
     * 打印日志
     *
     * @param methodName 方法名
     * @param msg        日志信息
     */
    @Override
    public void log(String methodName, String msg) {
        log.info(String.format("[%s]<%s>: %s", packageName, methodName, msg));
    }
}
