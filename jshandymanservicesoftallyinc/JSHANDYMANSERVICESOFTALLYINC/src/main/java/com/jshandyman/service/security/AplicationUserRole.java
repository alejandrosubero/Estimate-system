package com.jshandyman.service.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.jshandyman.service.security.ApplicationUserPermission.*;


public enum AplicationUserRole {

    USER(Sets.newHashSet(BUG_READ, BUG_WRITE, PROYECT_READ, PROYECT_WRITE)),
    ADMIN(Sets.newHashSet(BUG_READ, BUG_WRITE, MANAGER_READ, MANAGER_WRITE, PROYECT_READ, PROYECT_WRITE)),
    ITADMIN(Sets.newHashSet(MANAGER_READ, MANAGER_WRITE));


    private final Set<ApplicationUserPermission> permissions;

    AplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
