package com.github.rogerli.system.role.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;

public class Role extends BaseModel implements Serializable{

    private String id;

    private String role;

    private String roleName;

    private String descriptions;

    private Integer available;

    private Integer sort;

    private String organId;

    private Integer isAdmin;

    private Integer isClient;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsClient() {
        return isClient;
    }

    public void setIsClient(Integer isClient) {
        this.isClient = isClient;
    }
}