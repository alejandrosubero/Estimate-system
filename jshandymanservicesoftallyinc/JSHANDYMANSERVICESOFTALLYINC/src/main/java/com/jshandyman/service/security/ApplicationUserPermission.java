package com.jshandyman.service.security;

public enum ApplicationUserPermission {

    BUG_READ("bug:read"),
    BUG_WRITE("bug:write"),
    MANAGER_READ("manager:read"),
    MANAGER_WRITE("manager:write"),
    PROYECT_READ("proyect:read"),
    PROYECT_WRITE("proyect:write"),

    CONFIG_MANAGER_READ("manager:read"),

    CONFIG_MANAGER_WRITE("manager:write"),;


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
