package com.github.rogerli.system.user.entity;

import com.github.rogerli.framework.model.BaseModel;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

public class UserMeModel extends User implements Serializable {

    private int status;
    private String username;
    private String organId;
    private String organName;
    private List<GrantedAuthority> authorities;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}