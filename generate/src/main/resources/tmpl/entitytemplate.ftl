package ${packageName};

import com.github.rogerli.framework.model.BaseModel;

import java.sql.Timestamp;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ${className} extends BaseModel implements Serializable {

	<#list propertyList as property>
	private ${property.javaType} ${property.propertyName};
	</#list>

	<#list propertyList as property>
    /**
     *
     * @return
     */
	public ${property.javaType} get${property.methodName} () {
		return ${property.propertyName};
	}

    /**
     *
     * @param ${property.propertyName}
     */
	public void set${property.methodName}(${property.javaType} ${property.propertyName}) {
		this.${property.propertyName} = ${property.propertyName};
	}

    <#if "${property.propertyName}" == "yd">
    /**
     *
     * @return
     */
    public ${property.javaType} getX${property.propertyName} () {
        if (this.yd == null) {
            return null;
        }
        if (this.yd == "12") {
            return "1";
        } else {
            return (Integer.parseInt(this.yd) + 1) + "";
        }
    }
    </#if>
    <#if "${property.propertyName}" == "nd">
    /**
    *
    * @return
    */
    public ${property.javaType} getX${property.propertyName} () {
        if (this.yd == null || this.nd == null) {
            return null;
        }
        if(this.yd == "12") {
            return (Integer.parseInt(this.nd) + 1) + "";
        } else {
            return this.nd;
        }
    }
    </#if>
	</#list>
}