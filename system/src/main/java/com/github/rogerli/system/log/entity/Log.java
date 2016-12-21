package com.github.rogerli.system.log.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

public class Log extends BaseModel implements Serializable{
    private String id;

    private Date logTime;

    private String logIp;

    private String loginId;

    private String logOperate;

    private String loginName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLogOperate() {
        return logOperate;
    }

    public void setLogOperate(String logOperate) {
        this.logOperate = logOperate;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}