package com.kangyonggan.app.shiro;


/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class HasRoleTag extends AbstractRoleTag {
    @Override
    protected boolean showTagBody(String roleName) {
        return getSubject() != null && getSubject().hasRole(roleName);
    }
}
