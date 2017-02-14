package com.github.rogerli.generate.info;

public class ColumnInfo {

    private String columnName;
    private String jdbcType;
    private String propertyName;
    private String javaType;
    private String methodName;

    private String columnComment;
    private int columnSize;

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        String[] str = javaType.split("\\.");
        javaType = str[str.length - 1];
        if (javaType.equals("BigDecimal")) {
            javaType = "BigDecimal";
        }
        if (javaType.equals("TIMESTAMP")) {
            javaType = "Timestamp";
        }
        if (javaType.equals("INT")) {
            javaType = "INTEGER";
        }
        this.javaType = javaType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        if (jdbcType.equals("VARCHAR2")) {
            jdbcType = "VARCHAR";
        }
        if (jdbcType.equals("NUMBER")) {
            jdbcType = "NUMERIC";
        }
        if (jdbcType.equals("NUMBER")) {
            jdbcType = "NUMERIC";
        }
        if (jdbcType.equals("CHAR")) {
            jdbcType = "VARCHAR";
        }
        if (jdbcType.equals("DATETIME")) {
            jdbcType = "TIMESTAMP";
        }
        if (jdbcType.equals("INT")) {
            jdbcType = "INTEGER";
        }
        this.jdbcType = jdbcType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
