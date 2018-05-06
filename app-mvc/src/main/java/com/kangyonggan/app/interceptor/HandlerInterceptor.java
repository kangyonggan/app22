package com.kangyonggan.app.interceptor;

import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.app.annotation.Token;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Log4j2
public class HandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 存放当前请求
     */
    private static ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.CHECK) {
                if (isRepeatSubmit(request, token)) {
                    return false;
                }
            }
        }

        // 保存当前请求
        currentRequest.set(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Token token = method.getMethodAnnotation(Token.class);
            if (token != null && token.type() == Token.Type.GENERATE) {
                String random = UUID.randomUUID().toString();
                modelAndView.addObject("_token", random);
                request.getSession().setAttribute(token.key(), random);
                log.info("生成一个token，key={}, value={}", token.key(), random);
            }
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 从本地线程中移除请求
        currentRequest.remove();
    }

    /**
     * 获取当前请求
     *
     * @return 当前请求
     */
    public static HttpServletRequest getRequest() {
        return currentRequest.get();
    }

    /**
     * 校验是否重复提交
     *
     * @param request 请求
     * @param token   记号
     * @return 重复提交返回true，否则返回false
     */
    private boolean isRepeatSubmit(HttpServletRequest request, Token token) {
        try {
            String random = request.getParameter("_token");
            String sessionRandom = (String) request.getSession().getAttribute(token.key());
            log.info("校验是否重复提交，key={}, random={}, sessionRandom={}", token.key(), random, sessionRandom);
            if (StringUtil.hasEmpty(random, sessionRandom)) {
                return true;
            }
            return !random.equals(sessionRandom);
        } catch (Exception e) {
            log.error("校验是否重复提交异常", e);
            return true;
        } finally {
            request.getSession().removeAttribute(token.key());
        }
    }
}
