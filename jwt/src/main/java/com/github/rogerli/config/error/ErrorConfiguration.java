package com.github.rogerli.config.error;

import com.github.rogerli.config.jwt.mvc.JwtWebMvcConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@AutoConfigureBefore(JwtWebMvcConfiguration.class)
@EnableConfigurationProperties(ResourceProperties.class)
public class ErrorConfiguration {

    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes (RequestAttributes requestAttributes,
                                                           boolean includeStackTrace){
                Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
                errorAttributes.put("timestamp", new Date());
                addStatus(errorAttributes, requestAttributes);
                addPath(errorAttributes, requestAttributes);
                return errorAttributes;
            }

            private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
                return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
            }

            private void addStatus(Map<String, Object> errorAttributes,
                                   RequestAttributes requestAttributes) {
                Integer status = getAttribute(requestAttributes,
                        "javax.servlet.error.status_code");
                if (status == null) {
                    errorAttributes.put("status", 999);
                    errorAttributes.put("message", "None");
                    return;
                }
                errorAttributes.put("status", status);
                try {
                    errorAttributes.put("message", HttpStatus.valueOf(status).getReasonPhrase());
                }
                catch (Exception ex) {
                    // Unable to obtain a reason
                    errorAttributes.put("message", "Http Status " + status);
                }
            }

            private void addPath(Map<String, Object> errorAttributes,
                                 RequestAttributes requestAttributes) {
                String path = getAttribute(requestAttributes, "javax.servlet.error.request_uri");
                if (path != null) {
                    errorAttributes.put("path", path);
                }
            }
        };
    }
}
