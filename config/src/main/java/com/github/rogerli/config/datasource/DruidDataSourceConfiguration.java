package com.github.rogerli.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    /**
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        LOGGER.debug("======Registered druid servlet======");
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    /**
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
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource druidDataSource(@Value("${spring.datasource.driverClassName}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${spring.datasource.initialSize}") int initialSize,
                                      @Value("${spring.datasource.maxActive}") int maxActive,
                                      @Value("${spring.datasource.minIdle}") int minIdle,
                                      @Value("${spring.datasource.validationQuery}") String validationQuery,
                                      @Value("${druid.poolPreparedStatements}") boolean poolPreparedStatements,
                                      @Value("${druid.maxOpenPreparedStatements}") int maxOpenPreparedStatements,
                                      @Value("${spring.datasource.removeAbandonedTimeout}") int removeAbandonedTimeout,
                                      @Value("${spring.datasource.removeAbandoned}") boolean removeAbandoned) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(maxActive);
        druidDataSource.setMaxActive(minIdle);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);

        // 开启PSCache，需要数据库支持
        druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        try {
            LOGGER.debug("======Setting 'application.jwtProperties' into druid======");
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not initial Druid DataSource\n" + e);
        }
        return druidDataSource;
    }

}
