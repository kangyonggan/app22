package com.kangyonggan.app.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class CustomerFreeMarkerViewResolver extends FreeMarkerViewResolver {

    public CustomerFreeMarkerViewResolver() {
        setViewClass(FreeMarkerView.class);
        setCache(true);
        setPrefix("");
        setSuffix(".ftl");
        setContentType("text/html;charset=utf-8");
        setRequestContextAttribute("rca");
        setExposeRequestAttributes(true);
        setExposeSessionAttributes(true);
        setExposeSpringMacroHelpers(true);
    }

}
