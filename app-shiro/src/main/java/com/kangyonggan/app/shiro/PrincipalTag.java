package com.kangyonggan.app.shiro;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class PrincipalTag extends AbstractSuperTag {
    static final Logger log = Logger.getLogger("PrincipalTag");

    String getType(Map params) {
        return getParam(params, "type");
    }

    String getProperty(Map params) {
        return getParam(params, "property");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String result = null;

        if (getSubject() != null) {
            Object principal;

            if (getType(params) == null) {
                principal = getSubject().getPrincipal();
            } else {
                principal = getPrincipalFromClassName(params);
            }

            if (principal != null) {
                String property = getProperty(params);

                if (property == null) {
                    result = principal.toString();
                } else {
                    result = getPrincipalProperty(principal, property);
                }
            }
        }

        if (result != null) {
            try {
                env.getOut().write(result);
            } catch (IOException ex) {
                throw new TemplateException("Error writing [" + result + "] to Freemarker.", ex, env);
            }
        }
    }

    Object getPrincipalFromClassName(Map params) {
        String type = getType(params);

        try {
            Class cls = Class.forName(type);

            return getSubject().getPrincipals().oneByType(cls);
        } catch (ClassNotFoundException ex) {
            log.error("Unable to find class for name [" + type + "]", ex);
        }

        return null;
    }
}