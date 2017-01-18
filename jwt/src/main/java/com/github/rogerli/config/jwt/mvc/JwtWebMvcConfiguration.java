/**
 * @文件名称： WebMvcRestfulConfiguration.java
 * @文件描述：
 * @版权所有：(C)2016-2028
 * @公司：
 * @完成日期: 2016/12/5
 * @作者 ： Roger
 */
package com.github.rogerli.config.jwt.mvc;

import com.github.rogerli.config.mvc.LocalizationConfiguration;
import com.github.rogerli.config.mvc.RestfulRequestMappingHandlerMapping;
import com.github.rogerli.utils.error.ErrorCode;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Roger
 * @description
 * @create 2016/12/5 2:58
 */
@Configuration
@ComponentScan(
        basePackages = "com.github.rogerli",
        useDefaultFilters = false,
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class, RestControllerAdvice.class})}
)
@AutoConfigureAfter({LocalizationConfiguration.class})
public class JwtWebMvcConfiguration extends WebMvcConfigurationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtWebMvcConfiguration.class);

    @Autowired
    private MessageSource messageSource;

    @Override
    protected Validator getValidator() {
        LOGGER.info("======Validator======");
        LocalValidatorFactoryBean localValidatorFactoryBean =
                new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

    /**
     * 优化restful
     *
     * @return
     */
    @Override
    protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        LOGGER.info("======RequestMappingHandlerMapping======");
        return new RestfulRequestMappingHandlerMapping();
//        return  new RequestMappingHandlerMapping();
    }

    /**
     * 默认视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOGGER.info("======addViewControllers======");

//        registry.addViewController("").setViewName("index");
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/login").setViewName("login");

        super.addViewControllers(registry);
    }

    /**
     * 增加resourceHandlerMapping
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        LOGGER.info("======addResourceHandlers======");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/",
                "classpath:/public/",
                "classpath:/resources/",
                "classpath:/META-INF/resources/").setCachePeriod(31556926);
        super.addResourceHandlers(registry);
    }

    /**
     * 自定义解析器实现请求参数绑定方法
     *
     * @param argumentResolvers
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        LOGGER.info("======addArgumentResolvers======");
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 自定义处理器实现返回值处理
     *
     * @param returnValueHandlers
     */
    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        LOGGER.info("======addReturnValueHandlers======");
        super.addReturnValueHandlers(returnValueHandlers);
    }

    /**
     * 配置messageConverter
     * ByteArrayHttpMessageConverter
     * ResourceHttpMessageConverter
     * SourceHttpMessageConverter
     * AllEncompassingFormHttpMessageConverter
     * 根据环境还可能有
     * romePresent：AtomFeedHttpMessageConverter，RssChannelHttpMessageConverter
     * jackson2XmlPresent：MappingJackson2XmlHttpMessageConverter
     * jaxb2Present：Jaxb2RootElementHttpMessageConverter
     * jackson2Present：MappingJackson2HttpMessageConverter
     * gsonPresent：GsonHttpMessageConverter
     *
     * @param converters
     */
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        LOGGER.info("======MappingJackson2HttpMessageConverter======");
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text", "html", UTF_8));
        list.add(new MediaType("text", "json"));
        list.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(list);
        converters.add(converter);
    }

    /**
     * 配置messageConverter, 默认的有
     * ByteArrayHttpMessageConverter
     * ResourceHttpMessageConverter
     * SourceHttpMessageConverter
     * AllEncompassingFormHttpMessageConverter
     * 根据环境还可能有
     * romePresent：AtomFeedHttpMessageConverter，RssChannelHttpMessageConverter
     * jackson2XmlPresent：MappingJackson2XmlHttpMessageConverter
     * jaxb2Present：Jaxb2RootElementHttpMessageConverter
     * jackson2Present：MappingJackson2HttpMessageConverter
     * gsonPresent：GsonHttpMessageConverter
     *
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        LOGGER.info("======CommonsMultipartResolver======");
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(5000000);
        resolver.setMaxInMemorySize(8192);
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        LOGGER.info("======MappingJackson2HttpMessageConverter======");
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(new MediaType("text", "html"));
        list.add(new MediaType("text", "html", UTF_8));
        list.add(new MediaType("text", "json"));
        list.add(new MediaType("text", "json", UTF_8));
        list.add(new MediaType("text", "plain"));
        list.add(new MediaType("text", "plain", UTF_8));
        list.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(list);
        return converter;
    }

//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
//        Map<String, Object> map = new LinkedHashMap<>();
//        ResourceHttpRequestHandler resourceHttpRequestHandler = new ResourceHttpRequestHandler();
//        List<Resource> locations = new ArrayList<>();
////        locations.add(new ServletContextResource(getServletContext(), "/"));
//        locations.add(new ClassPathResource("META-INF/resources"));
//        locations.add(new ClassPathResource("resources/"));
//        locations.add(new ClassPathResource("static/"));
//        locations.add(new ClassPathResource("public/"));
//        resourceHttpRequestHandler.setLocations(locations);
////        resourceHttpRequestHandler.setServletContext(getServletContext());
//        resourceHttpRequestHandler.setApplicationContext(getApplicationContext());
//
//        List<ResourceResolver> resourceResolvers = new ArrayList<>();
//        PathResourceResolver resourceResolver = new PathResourceResolver();
//        resourceResolver.setAllowedLocations(
////                new ServletContextResource(getServletContext(), "/"),
//                new ClassPathResource("META-INF/resources"), new ClassPathResource("resources/"), new ClassPathResource("static/"), new ClassPathResource("public/"));
//        resourceResolvers.add(resourceResolver);
//
//        resourceHttpRequestHandler.setResourceResolvers(resourceResolvers);
//        map.put("/**", resourceHttpRequestHandler);
//        simpleUrlHandlerMapping.setUrlMap(map);
//        ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();
//        Map<String, ResourceHttpRequestHandler> handlerMap = new LinkedHashMap<>();
//        handlerMap.put("/**", resourceHttpRequestHandler);
//        resourceUrlProvider.setHandlerMap(handlerMap);
//        ResourceUrlProviderExposingInterceptor interceptor = new ResourceUrlProviderExposingInterceptor(resourceUrlProvider);
//        simpleUrlHandlerMapping.setInterceptors(new Object[]{interceptor});
//        return simpleUrlHandlerMapping;
//    }

}
