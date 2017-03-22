package ${packageName};

import com.github.rogerli.framework.web.AbstractTmplController;
import ${typeName};
import ${serviceName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(${className}.CONTENT_PATH)
public class ${className} extends AbstractTmplController<${entityName}, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(${className}.class);

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    public static final String FILE_PATH = "${moduleName}/${entityName?lower_case}";
    public static final String CONTENT_PATH = "/" + FILE_PATH;

    @Override
    protected String getFilePath() {
        return FILE_PATH;
    }

    @Override
    protected String getContentPath() {
        return CONTENT_PATH;
    }

}