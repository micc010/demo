package com.github.rogerli.system.uploadfile.entity;

import com.github.rogerli.framework.model.BaseModel;

import java.sql.Timestamp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UploadFile extends BaseModel implements Serializable {

	private String id;
	private String itemId;
	private String userId;
	private String itemType;
	private String title;
	private String filePath;
	private String saveName;
	private String originName;
	private Timestamp createTime;

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
	public String getItemId () {
		return itemId;
	}

    /**
     *
     * @param itemId
     */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
    /**
     *
     * @return
     */
	public String getUserId () {
		return userId;
	}

    /**
     *
     * @param userId
     */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    /**
     *
     * @return
     */
	public String getItemType () {
		return itemType;
	}

    /**
     *
     * @param itemType
     */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
    /**
     *
     * @return
     */
	public String getTitle () {
		return title;
	}

    /**
     *
     * @param title
     */
	public void setTitle(String title) {
		this.title = title;
	}
    /**
     *
     * @return
     */
	public String getFilePath () {
		return filePath;
	}

    /**
     *
     * @param filePath
     */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
    /**
     *
     * @return
     */
	public String getSaveName () {
		return saveName;
	}

    /**
     *
     * @param saveName
     */
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
    /**
     *
     * @return
     */
	public String getOriginName () {
		return originName;
	}

    /**
     *
     * @param originName
     */
	public void setOriginName(String originName) {
		this.originName = originName;
	}
    /**
     *
     * @return
     */
	public Timestamp getCreateTime () {
		return createTime;
	}

    /**
     *
     * @param createTime
     */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}