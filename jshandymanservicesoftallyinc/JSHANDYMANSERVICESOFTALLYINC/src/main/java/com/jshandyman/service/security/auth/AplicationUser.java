package com.jshandyman.service.security.auth;


import com.jshandyman.service.entitys.User;
import com.jshandyman.service.security.AplicationUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



import java.util.Collection;
import java.util.Set;

import static com.jshandyman.service.security.AplicationUserRole.ADMIN;
import static com.jshandyman.service.security.AplicationUserRole.USER;


public class AplicationUser implements UserDetails {

    private Set<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final Boolean isAccountNonExpired;
    private final Boolean isAccountNonLocked;
    private final Boolean isCredentialsNonExpired;
    private final Boolean isEnabled;

    public AplicationUser(User userAuth) {
        this.isAccountNonExpired = userAuth.getAccountNonExpired();
        this.isAccountNonLocked = userAuth.getAccountNonLocked();
        this.isCredentialsNonExpired = userAuth.getAccountNonExpired();
        this.isEnabled = userAuth.getEnabled();
        this.username = userAuth.getUserName();
        this.password = userAuth.getPassword();
        this.grantedAuthorities = getRol(userAuth.getRol()).getGrantedAuthorities();
    }

    public AplicationUserRole getRol(String rol) {
        AplicationUserRole userRol = null;
        if (rol.equals("USER")) {
            userRol = USER;
        }
        if (rol.equals("ADMIN")) {
            userRol = ADMIN;
        }
        return userRol;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
