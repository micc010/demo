package com.github.rogerli.generate.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ToolConfiguration {

    private boolean isShowBanner = true;
    private boolean isRestful = true;
    private String url;
    private String username;
    private String password;
    private String driverName;
    private List<String> tableList = new ArrayList<String>();
    private String tablePrefix;
    private String packageName;
    private String moduleName;
    private String entityNameSpace;
    private String daoNameSpace;
    private String serviceNameSpace;
    private String webNameSpace;
    private String restfulNameSpace;
    private String fileLocation;


    public void loadParam(String configurationLocation) throws IOException {
        String tableNameList;
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(configurationLocation)));
        String banner = properties.getProperty("showBanner");
        if (banner != null) {
            isShowBanner = Boolean.parseBoolean(banner);
        }
        String restful = properties.getProperty("restful");
        if (restful != null) {
            isRestful = Boolean.parseBoolean(restful);
        }
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
        driverName = properties.getProperty("driverName");
        tableNameList = properties.getProperty("tableNameList");
        tablePrefix = properties.getProperty("tablePrefix");
        packageName = properties.getProperty("packageName");
        moduleName = properties.getProperty("moduleName");
        entityNameSpace = properties.getProperty("entityNameSpace");
        daoNameSpace = properties.getProperty("daoNameSpace");
        serviceNameSpace = properties.getProperty("serviceNameSpace");
        webNameSpace = properties.getProperty("webNameSpace");
        restfulNameSpace = properties.getProperty("restfulNameSpace");
        fileLocation = properties.getProperty("fileLocation");
        String[] tbList = tableNameList.split(",");
        for (int i = 0; i < tbList.length; i++) {
            tableList.add(tbList[i]);
        }
    }

    public boolean isShowBanner() {
        return isShowBanner;
    }

    public void setShowBanner(boolean isShowBanner) {
        this.isShowBanner = isShowBanner;
    }

    public boolean isRestful() {
        return isRestful;
    }

    public void setRestful(boolean restful) {
        isRestful = restful;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<String> getTableList() {
        return tableList;
    }

    public void setTableList(List<String> tableList) {
        this.tableList = tableList;
    }

    public String getFileLocation() {
        if (fileLocation.endsWith("/")) {
            return fileLocation;
        } else {
            return fileLocation + "/";
        }
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    private String getPackageName(String className) {
        return packageName.toLowerCase() + "." + moduleName + "." + className.toLowerCase();
    }

    private String getPackagePath(String className) {
        return getFileLocation() + getPackageName(className).replaceAll("\\.", "/");
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDaoPackage(String className) {
        return getPackageName(className) + "." + daoNameSpace;
    }

    public String getDaoName(String className) {
        return className + "Mapper";
    }

    public String getDaoPackageName(String className) {
        return getDaoPackage(className) + "." + getDaoName(className);
    }

    public String getDaoPath(String className) {
        return getPackagePath(className) + "/" + daoNameSpace + "/";
    }

    public String getServicePackage(String className) {
        return getPackageName(className) + "." + serviceNameSpace;
    }

    public String getServiceName(String className) {
        return className + "Service";
    }

    public String getServicePackageName(String className) {
        return getServicePackage(className) + "." + getServiceName(className);
    }

    public String getServicePath(String className) {
        return getPackagePath(className) + "/" + serviceNameSpace + "/";
    }

    public String getEntityPackageName(String className) {
        return getEntityPackage(className) + "." + className;
    }

    public String getEntityName(String className) {
        return className;
    }

    public String getEntityPackage(String className) {
        return getPackageName(className) + "." + entityNameSpace;
    }

    public String getEntityPath(String className) {
        return getPackagePath(className) + "/" + entityNameSpace + "/";
    }

    public String getFormControllerName(String className) {
        return className + "TmplController";
    }

    public String getFormPackage(String className) {
        return getPackageName(className) + "." + webNameSpace;
    }

    public String getFormPath(String className) {
        return getPackagePath(className) + "/" + webNameSpace + "/";
    }

    public String getRestfulPackage(String className) {
        return getPackageName(className) + "." + restfulNameSpace;
    }

    public String getRestfulControllerName(String className) {
        return className + "Endpoint";
    }

    public String getRestfulPath(String className) {
        return getPackagePath(className) + "/" + restfulNameSpace + "/";
    }

    public String tableNameToEntityName(String tableName) {
        StringBuilder sb = new StringBuilder();
        tableName = tableName.replace(tablePrefix, "");
        String[] tableNameList = tableName.toLowerCase().split("_");
        for (int k = 0; k < tableNameList.length; k++) {
            sb.append(upperFirstChar(tableNameList[k]));
        }
        return sb.toString();
    }

    public String upperFirstChar(String propertyName) {
        if (propertyName == null || propertyName.length() == 0) {
            return "";
        }
        String theTableName = propertyName.substring(0, 1);
        theTableName = theTableName.toUpperCase();
        return theTableName + propertyName.substring(1, propertyName.length());
    }

    public String colNameToPropertyName(String colName) {
        String[] colNameList = colName.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder();
        if (colNameList.length == 1) {
            return colName.toLowerCase();
        }

        for (int i = 0; i < colNameList.length; i++) {
            String col = colNameList[i];
            if (i == 0) {
                sb.append(col);
            } else {
                String theColName = col.substring(0, 1);
                theColName = theColName.toUpperCase();
                sb.append(theColName + col.substring(1, col.length()));
            }
        }
        return sb.toString();
    }

}