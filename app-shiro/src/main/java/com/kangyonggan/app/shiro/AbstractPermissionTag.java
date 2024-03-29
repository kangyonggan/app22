package com.kangyonggan.app.shiro;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;


/**
 * @author kangyonggan
 * @since 16/4/29
 */
public abstract class AbstractPermissionTag extends AbstractSuperTag {
    String getName(Map params) {
        return getParam(params, "name");
    }

    @Override
    protected void verifyParameters(Map params) throws TemplateModelException {
        String permission = getName(params);

        if (permission == null || permission.length() == 0) {
            throw new TemplateModelException("The 'name' tag attribute must be set.");
        }
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String p = getName(params);

        boolean show = showTagBody(p);
        if (show) {
            renderBody(env, body);
        }
    }

    protected boolean isPermitted(String p) {
        return getSubject() != null && getSubject().isPermitted(p);
    }

    /**
     * showTagBody
     *
     * @param p p
     * @return boolean
     */
    protected abstract boolean showTagBody(String p);
}
