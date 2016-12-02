package com.github.rogerli.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author roger.li
 */
@Configuration
public class DruidDataSourceConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    @Autowired
    private DataSourceProperties properties;

    /**
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        LOGGER.debug("======Registered druid servlet======");
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    /**
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        LOGGER.debug("======Registered druid filter======");
        return filterRegistrationBean;
    }

    /**
     *
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        try {
            LOGGER.debug("======Setting 'application.properties' into druid======");
            druidDataSource.setFilters("stat, wall"); // TODO filter
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initial Druid DataSource\n" + e);
        }
        return druidDataSource;
    }

}
