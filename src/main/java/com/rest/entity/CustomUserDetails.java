package com.rest.entity;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;
import static org.springframework.util.StringUtils.collectionToCommaDelimitedString;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails extends User implements UserDetails {

    private static final long serialVersionUID = 1L;
    private List<String> userRoles;

    public CustomUserDetails(User user, List<String> userRoles) {
        super(user.getUserId(), user.getUserName(), user.getLogin(), user.getPassword());
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = collectionToCommaDelimitedString(userRoles);
        return commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}