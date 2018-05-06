package com.kangyonggan.app.shiro;


/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class LacksRoleTag extends AbstractRoleTag {
    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasRole = getSubject() != null && getSubject().hasRole(roleName);
        return !hasRole;
    }
}
