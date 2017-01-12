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
	</#list>
}