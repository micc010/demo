package ${packageName};

import com.github.rogerli.framework.service.Service;
import com.github.rogerli.framework.web.AbstractFormJsonController;
import ${typeName};
import ${serviceName};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/${entityName?lower_case}")
public class ${jsonClassName} extends AbstractFormJsonController<${entityName}, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(${jsonClassName}.class);

    @Autowired
    private ${entityName}Service ${entityName?lower_case}Service;

    @Override
    protected Service getService() {
        return ${entityName?lower_case}Service;
    }

}