package com.github.rogerli.system.operation.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.sql.Timestamp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Operation extends BaseModel implements Serializable {

	private String id;
	private String workId;
	private String workType;
	private String oldValue;
	private Date modifyDate;
	private String modifyUser;
	private String modifyOrgan;
	private String newValue;
	private String remark;
	private String workClass;

    /**
     *
     * @return
     */
	public String getId () {
		return id;
	}

    /**
     *
     * @param id
     */
	public void setId(String id) {
		this.id = id;
	}

    /**
     *
     * @return
     */
	public String getWorkId () {
		return workId;
	}

    /**
     *
     * @param workId
     */
	public void setWorkId(String workId) {
		this.workId = workId;
	}

    /**
     *
     * @return
     */
	public String getWorkType () {
		return workType;
	}

    /**
     *
     * @param workType
     */
	public void setWorkType(String workType) {
		this.workType = workType;
	}

    /**
     *
     * @return
     */
	public String getOldValue () {
		return oldValue;
	}

    /**
     *
     * @param oldValue
     */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

    /**
     *
     * @return
     */
	public Date getModifyDate () {
		return modifyDate;
	}

    /**
     *
     * @param modifyDate
     */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

    /**
     *
     * @return
     */
	public String getModifyUser () {
		return modifyUser;
	}

    /**
     *
     * @param modifyUser
     */
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

    /**
     *
     * @return
     */
	public String getModifyOrgan () {
		return modifyOrgan;
	}

    /**
     *
     * @param modifyOrgan
     */
	public void setModifyOrgan(String modifyOrgan) {
		this.modifyOrgan = modifyOrgan;
	}

    /**
     *
     * @return
     */
	public String getNewValue () {
		return newValue;
	}

    /**
     *
     * @param newValue
     */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

    /**
     *
     * @return
     */
	public String getRemark () {
		return remark;
	}

    /**
     *
     * @param remark
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    /**
     *
     * @return
     */
	public String getWorkClass () {
		return workClass;
	}

    /**
     *
     * @param workClass
     */
	public void setWorkClass(String workClass) {
		this.workClass = workClass;
	}

}