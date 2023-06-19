package dev.jdevv.techblogger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


@EqualsAndHashCode
@ToString
public class User {

    private Long userId;
    private String userName;
    private String name;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private boolean isActivated;

    private Set<Authority> authorities = new HashSet<>();

    public User() {
    }

    public User(Long userId, String userName, String name, String password, String authorities) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.password = password;
        if (authorities != null) this.setAuthorities(authorities);
        this.isActivated = true;
    }

    public User(String userName, String password, String authorities, String name) {
        this(0L, userName, password, authorities, name);
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivated() {
        return isActivated;
    }
    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public String getAuthoritiesString() {
        String authString = "";
        for (Authority auth : authorities) {
            if (authString.length() == 0) {
                authString += auth.getName();
            } else {
                authString += "," + auth.getName();
            }
        }
        return authString;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for (String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role.toUpperCase();
            this.authorities.add(new Authority(authority));
        }
    }

}
