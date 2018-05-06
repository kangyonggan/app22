package com.kangyonggan.app.shiro;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;

/**
 * @author kangyonggan
 * @since 16/4/29
 */
public class ShiroTags extends SimpleHash {

    public ShiroTags() {
        super((ObjectWrapper) null);
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
}