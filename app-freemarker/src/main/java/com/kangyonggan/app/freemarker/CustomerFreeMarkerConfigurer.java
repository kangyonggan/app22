package com.kangyonggan.app.freemarker;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class CustomerFreeMarkerConfigurer extends FreeMarkerConfigurer {

    public CustomerFreeMarkerConfigurer() {
        setTemplateLoaderPaths("/WEB-INF/templates/", "classpath:/templates/");
        setDefaultEncoding("UTF-8");

        setSettings(null);
        setVariables(null);
    }

    public void setSettings(Map<String, String> settings) {
        Properties freemarkerSettings = new Properties();
        freemarkerSettings.setProperty("template_update_delay", "10");
        freemarkerSettings.setProperty("defaultEncoding", "UTF-8");
        freemarkerSettings.setProperty("url_escaping_charset", "UTF-8");
        freemarkerSettings.setProperty("locale", "zh_CN");
        freemarkerSettings.setProperty("boolean_format", "true,false");
        freemarkerSettings.setProperty("datetime_format", "HH:mm:ss");
        freemarkerSettings.setProperty("time_format", "yyyy-MM-dd HH:mm:ss");
        freemarkerSettings.setProperty("date_format", "yyyy-MM-dd");
        freemarkerSettings.setProperty("number_format", "#.##");
        freemarkerSettings.setProperty("whitespace_stripping", "true");
        freemarkerSettings.setProperty("auto_import", "spring.ftl as s, common.ftl as c");

        if (settings != null) {
            for (String key : settings.keySet()) {
                freemarkerSettings.setProperty(key, settings.get(key));
            }
        }

        setFreemarkerSettings(freemarkerSettings);
    }

    public void setVariables(Map<String, Object> variables) {
        Map<String, Object> freemarkerVariables = new HashMap<>(4);
        freemarkerVariables.put("block", new BlockDirective());
        freemarkerVariables.put("extends", new ExtendsDirective());
        freemarkerVariables.put("override", new OverrideDirective());
        freemarkerVariables.put("super", new SuperDirective());

        if (variables != null) {
            for (String key : variables.keySet()) {
                freemarkerVariables.put(key, variables.get(key));
            }
        }

        setFreemarkerVariables(freemarkerVariables);
    }

}
