/**
 * @文件名称： TableInfo.java
 * @文件描述：
 * @版权所有：(C)2017-2028
 * @公司：
 * @完成日期: 2017/1/16
 * @作者 ： Roger
 */
package com.github.rogerli.generate.info;

/**
 * @author Roger
 * @create 2017/1/16 23:28
 */
public class TableInfo {

    private String tableName;

    private String keyName;

    public String getTableName() {
        return tableName;
    }

    public TableInfo(){

    }

    public TableInfo(String tableName, String keyName) {
        this.tableName = tableName;
        this.keyName = keyName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
