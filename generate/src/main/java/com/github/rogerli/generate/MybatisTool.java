package com.github.rogerli.generate;

import com.github.rogerli.generate.config.ToolConfiguration;
import com.github.rogerli.generate.info.Banner;
import com.github.rogerli.generate.info.ColumnInfo;
import com.github.rogerli.generate.info.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTool {

    private Banner banner;
    private Connection connection;
    private PreparedStatement st = null;
    Configuration configuration = new Configuration();

    private ToolConfiguration toolConfiguration;

    private File fileLocation;

    public ToolConfiguration getToolConfiguration() {
        return toolConfiguration;
    }

    public void setToolConfiguration(ToolConfiguration toolConfiguration) {
        this.toolConfiguration = toolConfiguration;
    }

    public void generate() {
        init();
        connectToDb();
        createFile();
        closeConnection();
    }

    private void init() {
        if (toolConfiguration.isShowBanner()) {
            banner = new Banner();
            banner.showBanner();
        }
        if (configuration == null) {
            configuration = new Configuration();
        }
        configuration.setClassForTemplateLoading(getClass(), "/tmpl");
    }

    private void connectToDb() {
        try {
            Class.forName(toolConfiguration.getDriverName());
            connection = DriverManager.getConnection(toolConfiguration.getUrl(), toolConfiguration.getUsername(), toolConfiguration.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createFile() {

        fileLocation = new File(toolConfiguration.getFileLocation());
        if (!fileLocation.exists()) {
            fileLocation.mkdirs();
        }

        try {

            for (TableInfo info :
                    toolConfiguration.getTableList()) {

                String className = toolConfiguration.tableNameToEntityName(info.getTableName());

                st = connection.prepareStatement("select * from " + info.getTableName());
                st.execute();
                ResultSetMetaData rsmd = st.getMetaData();

                List<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    ColumnInfo columnInfo = new ColumnInfo();
                    columnInfo.setColumnName(rsmd.getColumnName(i));
                    columnInfo.setPropertyName(toolConfiguration.colNameToPropertyName(columnInfo.getColumnName()));
                    columnInfo.setJdbcType(rsmd.getColumnTypeName(i));
                    columnInfo.setJavaType(rsmd.getColumnClassName(i));
                    columnInfo.setColumnSize(rsmd.getColumnDisplaySize(i));
                    columnInfo.setMethodName(toolConfiguration.upperFirstChar(columnInfo.getPropertyName()));
                    columnInfoList.add(columnInfo);
                }

                DatabaseMetaData dbmd = connection.getMetaData();
                ResultSet rs = dbmd.getTables(null, null, info.getTableName(), null);
                while (rs.next()) {
                    info.setTableComment(rs.getString("REMARKS"));
                }
                rs.close();

                rs = dbmd.getColumns(null, null, info.getTableName(), null);
                while (rs.next()) {
                    String columnName = rs.getString("COLUMN_NAME");
                    for (ColumnInfo col :
                            columnInfoList) {
                        if (col.getColumnName().equals(columnName)) {
                            col.setColumnComment(rs.getString("REMARKS"));
                            break;
                        }
                    }
                }
                rs.close();

                renderModel(className, columnInfoList);
                renderDao(className);
                renderXmlMapper(info, className, columnInfoList);
                renderService(className);
                if (!toolConfiguration.isRestful()) {
                    renderJson(className, "jsonwebtemplate.ftl");
                    renderWeb(className);
                    renderTmpl(className, info, columnInfoList);
                } else {
                    renderRestful(className, "restfultemplate.ftl");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void renderModel(String className, List<ColumnInfo> tableInfoList) {

        String packageName = toolConfiguration.getEntityPackage(className);
        String entityPath = toolConfiguration.getEntityPath(className);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("packageName", packageName);
        data.put("className", className);
        data.put("propertyList", tableInfoList);

        Template template;
        try {
            File subFile = new File(entityPath);
            subFile.mkdirs();
            template = configuration.getTemplate("entitytemplate.ftl");
            FileOutputStream fos = new FileOutputStream(new File(entityPath + className + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderDao(String className) {

        String packageName = toolConfiguration.getDaoPackage(className);
        String typeName = toolConfiguration.getEntityPackageName(className);
        String daoName = toolConfiguration.getDaoName(className);
        String entityName = toolConfiguration.getEntityName(className);

        String daoPath = toolConfiguration.getDaoPath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("className", daoName);
        data.put("typeName", typeName);
        data.put("entityName", entityName);

        Template template;
        try {
            File subFile = new File(daoPath);
            subFile.mkdirs();
            template = configuration.getTemplate("mappertemplate.ftl");
            FileOutputStream fos = new FileOutputStream(new File(daoPath + daoName + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderXmlMapper(TableInfo info, String className, List<ColumnInfo> columnInfoList) {

        Map<String, Object> data = new HashMap<String, Object>();
        String typeName = toolConfiguration.getEntityPackageName(className);
        String nameSpace = toolConfiguration.getDaoPackageName(className);
        String daoName = toolConfiguration.getDaoName(className);

        String daoPath = toolConfiguration.getDaoPath(className);

        String colStr = "";
        for (int i = 0; i < columnInfoList.size(); i++) {
            colStr += columnInfoList.get(i).getColumnName();
            if (i != (columnInfoList.size() - 1)) {
                colStr += ",";
            }
        }

        data.put("columnList", columnInfoList);
        data.put("typeName", typeName);
        data.put("nameSpace", nameSpace);
        data.put("colStr", colStr);
        data.put("info", info);
        Template template;
        try {

            File subFile = new File(daoPath);
            subFile.mkdirs();
            template = configuration.getTemplate("xmltemplate.ftl");
            FileOutputStream fos = new FileOutputStream(new File(daoPath + daoName + ".xml"), false);
            template.process(data, new OutputStreamWriter(fos));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成Service代码
     *
     * @param className
     * @author HZH
     */
    private void renderService(String className) {

        String packageName = toolConfiguration.getServicePackage(className);
        String typeName = toolConfiguration.getEntityPackageName(className);
        String serviceName = toolConfiguration.getServiceName(className);
        String daoName = toolConfiguration.getDaoPackageName(className);
        String entityName = toolConfiguration.getEntityName(className);

        String servicePath = toolConfiguration.getServicePath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("className", serviceName);
        data.put("entityName", entityName);
        data.put("daoName", daoName);
        data.put("typeName", typeName);

        Template template;
        try {
            File subFile = new File(servicePath);
            subFile.mkdirs();
            template = configuration.getTemplate("servicetemplate.ftl");
            FileOutputStream fos = new FileOutputStream(new File(servicePath + serviceName + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderWeb(String className) {
        String packageName = toolConfiguration.getFormPackage(className);
        String typeName = toolConfiguration.getEntityPackageName(className);
        String controllerName = toolConfiguration.getFormControllerName(className);
        String entityName = toolConfiguration.getEntityName(className);
        String serviceName = toolConfiguration.getServicePackageName(className);

        String formPath = toolConfiguration.getFormPath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("className", controllerName);
        data.put("entityName", entityName);
        data.put("serviceName", serviceName);
        data.put("typeName", typeName);
        data.put("moduleName", toolConfiguration.getModuleName());

        Template template;
        try {
            File subFile = new File(formPath);
            subFile.mkdirs();
            template = configuration.getTemplate("formwebtemplate.ftl");
            FileOutputStream fos = new FileOutputStream(new File(formPath + controllerName + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderJson(String className, String templateName) {
        String packageName = toolConfiguration.getRestfulPackage(className);
        String typeName = toolConfiguration.getEntityPackageName(className);
        String entityName = toolConfiguration.getEntityName(className);
        String controllerName = entityName + "JsonController";
        String serviceName = toolConfiguration.getServicePackageName(className);

        String restfulPath = toolConfiguration.getRestfulPath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("className", controllerName);
        data.put("entityName", entityName);
        data.put("serviceName", serviceName);
        data.put("typeName", typeName);
        data.put("moduleName", toolConfiguration.getModuleName());

        Template template;
        try {
            File subFile = new File(restfulPath);
            subFile.mkdirs();
            template = configuration.getTemplate(templateName);
            FileOutputStream fos = new FileOutputStream(new File(restfulPath + controllerName + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderRestful(String className, String templateName) {
        String packageName = toolConfiguration.getRestfulPackage(className);
        String typeName = toolConfiguration.getEntityPackageName(className);
        String controllerName = toolConfiguration.getRestfulControllerName(className);
        String entityName = toolConfiguration.getEntityName(className);
        String serviceName = toolConfiguration.getServicePackageName(className);

        String restfulPath = toolConfiguration.getRestfulPath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("packageName", packageName);
        data.put("className", controllerName);
        data.put("entityName", entityName);
        data.put("serviceName", serviceName);
        data.put("typeName", typeName);
        data.put("moduleName", toolConfiguration.getModuleName());

        Template template;
        try {
            File subFile = new File(restfulPath);
            subFile.mkdirs();
            template = configuration.getTemplate(templateName);
            FileOutputStream fos = new FileOutputStream(new File(restfulPath + controllerName + ".java"));
            template.process(data, new OutputStreamWriter(fos));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void renderTmpl(String className, TableInfo info, List<ColumnInfo> tableInfoList) {
        String entityName = toolConfiguration.getEntityName(className);

        String tmplPath = toolConfiguration.getTmplPath(className);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("entityName", entityName);
        data.put("moduleName", toolConfiguration.getModuleName());
        data.put("propertyList", tableInfoList);
        data.put("entity", info);

        Template template;
        try {
            File subFile = new File(tmplPath);
            subFile.mkdirs();

            // TODO view edit list
            template = configuration.getTemplate("htmlviewtemplate.ftl");
            FileOutputStream view = new FileOutputStream(new File(tmplPath + "view.ftl"));
            template.process(data, new OutputStreamWriter(view));

            template = configuration.getTemplate("htmledittemplate.ftl");
            FileOutputStream edit = new FileOutputStream(new File(tmplPath + "edit.ftl"));
            template.process(data, new OutputStreamWriter(edit));

            template = configuration.getTemplate("htmllisttemplate.ftl");
            FileOutputStream list = new FileOutputStream(new File(tmplPath + "list.ftl"));
            template.process(data, new OutputStreamWriter(list));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
