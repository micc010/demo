package com.github.rogerli.flow.define.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;

/**
 * Created by roger on 2017/2/21.
 */
public class NodeObj extends BaseModel implements Serializable{

    private String id;

    private String flowId;

    private String nodeId;

    private String objId;

    private String objType;

    private String objName;

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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}
