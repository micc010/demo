package ${packageName};

import com.github.rogerli.framework.service.AbstractService;
import ${daoName};
import ${typeName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ${className} extends AbstractService<${entityName}, String, ${entityName}Mapper> {

	private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

	@Autowired
	private ${entityName}Mapper ${entityName?uncap_first}Mapper;

	@Override
	protected ${entityName}Mapper getMapper() {
		return ${entityName?uncap_first}Mapper;
	}

}
