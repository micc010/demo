package com.github.rogerli.flow.define.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;

public class Path extends BaseModel implements Serializable {

    private String id;

    private String flowId;

    private String prefixId;

    private String suffixId;

    private String name;

    private String displayName;

    private String isDefault;

    private String sign;

    private String pathType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(String prefixId) {
        this.prefixId = prefixId;
    }

    public String getSuffixId() {
        return suffixId;
    }

    public void setSuffixId(String suffixId) {
        this.suffixId = suffixId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }
}