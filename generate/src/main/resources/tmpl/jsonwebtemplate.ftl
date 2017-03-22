package ${packageName};

import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.AbstractJsonController;
import ${typeName};
import ${serviceName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(${entityName}TmplController.CONTENT_PATH)
public class ${entityName}JsonController extends AbstractJsonController<${entityName}, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(${entityName}JsonController.class);

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    @Override
    protected Service getService() {
        return ${entityName?uncap_first}Service;
    }

}