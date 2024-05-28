package com.example.eventsystemuserservice.security.models;

import com.example.eventsystemuserservice.models.Authorities;
import com.example.eventsystemuserservice.models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@JsonDeserialize
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private List<GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long userId;
    private String email;

    public CustomUserDetails(){}

    public CustomUserDetails(User user){
        this.password = user.getHashedPassword();
        this.username = user.getUserName();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = getGrantedAuthorities(user.getAuthorities());
        this.userId = user.getId();
        this.email = user.getEmail();
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<Authorities> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authorities role : roles){
            grantedAuthorities.add(new CustomGrantedAuthority(role.getName()));
        }

        return grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
