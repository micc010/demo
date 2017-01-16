package ${packageName};

import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.AbstractRestfulController;
import ${typeName};
import ${serviceName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${moduleName}/${entityName?lower_case}s")
public class ${className} extends AbstractRestfulController<${entityName}, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    @Override
    protected Service getService() {
        return ${entityName?uncap_first}Service;
    }

}