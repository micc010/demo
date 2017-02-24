package com.github.rogerli.flow.define.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;

public class Node extends BaseModel implements Serializable {
    private String id;

    private String flowId;

    private String name;

    private String displayName;

    private String nodeType;

    private String url;

    private String viewUrl;

    private String isAuto;

    private String hasActor;

    private String autoService;

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

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    public String getHasActor() {
        return hasActor;
    }

    public void setHasActor(String hasActor) {
        this.hasActor = hasActor;
    }

    public String getAutoService() {
        return autoService;
    }

    public void setAutoService(String autoService) {
        this.autoService = autoService;
    }
}