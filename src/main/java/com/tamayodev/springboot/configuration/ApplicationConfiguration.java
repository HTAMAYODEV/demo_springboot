package com.tamayodev.springboot.configuration;

import com.tamayodev.springboot.bean.MyBeanWithProperties;
import com.tamayodev.springboot.bean.MyBeanWithPropertiesImpl;
import com.tamayodev.springboot.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(UserPojo.class)
public class ApplicationConfiguration {
    //@PropertySource("classpath:application.properties")
    // application.properties
    @Value("${value.name}")
    private String userName;
    @Value("${value.lastname}")
    private String userLastName;
    @Value("${value.random}")
    private String userRandom;
    // Custom Properties
    /*@Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;*/

    //
    @Bean
    public MyBeanWithProperties beanWithProperties() {
        return new MyBeanWithPropertiesImpl(userName, userLastName, userRandom);
    }

    /*@Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }*/
    //
}
