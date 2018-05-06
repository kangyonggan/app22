package com.kangyonggan.app.shiro;


/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class LacksPermissionTag extends AbstractPermissionTag {
    @Override
    protected boolean showTagBody(String p) {
        return !isPermitted(p);
    }
}
