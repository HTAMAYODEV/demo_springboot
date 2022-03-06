package com.tamayodev.springboot.configuration;

import com.tamayodev.springboot.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation() {
        return new MyBean2Impl();
    }

    @Bean
    public MyOperation beanOperationOperation() {
        return new MyOperationImpl();
    }

    @Bean
    public MyBeanWithDependency beanWithDependency(MyOperation myOperation) {
        return new MyBeanWithDependencyImpl(myOperation);
    }
}
