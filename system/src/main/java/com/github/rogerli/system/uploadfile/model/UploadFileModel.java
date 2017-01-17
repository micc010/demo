/**
 * @文件名称： UploadFileModel.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/17
 * @作者 ： Roger
 */
package com.github.rogerli.system.uploadfile.model;

import java.util.List;

/**
 * @author Roger
 * @create 2017/1/17 17:42
 */
public class UploadFileModel {

    private List<String> idList;
    private String itemId;

    public UploadFileModel(List<String> idList, String itemId) {
        this.idList = idList;
        this.itemId = itemId;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
