package com.kangyonggan.app.shiro;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public abstract class AbstractRoleTag extends AbstractSuperTag {
    String getName(Map params) {
        return getParam(params, "name");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody(getName(params));
        if (show) {
            renderBody(env, body);
        }
    }

    /**
     * showTagBody
     *
     * @param roleName roleName
     * @return boolean
     */
    protected abstract boolean showTagBody(String roleName);
}