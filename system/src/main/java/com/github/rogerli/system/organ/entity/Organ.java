package com.github.rogerli.system.organ.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;

public class Organ extends BaseModel implements Serializable{

    private String id;

    private String name;

    private String code;

    private String shortName;

    private String type;

    private String parentId;

    private Integer available;

    private Integer sort;

    private Integer checkable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public Integer getCheckable() {
        return checkable;
    }

    public void setCheckable(Integer checkable) {
        this.checkable = checkable;
    }
}