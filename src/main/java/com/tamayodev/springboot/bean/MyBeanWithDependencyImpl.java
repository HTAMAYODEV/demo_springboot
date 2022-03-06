package com.tamayodev.springboot.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImpl implements MyBeanWithDependency {
    //
    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImpl.class);
    //
    private MyOperation myOperation;

    public MyBeanWithDependencyImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("printWithDependency:init");
        int number = 1;
        System.out.println("DependencyImpl: " + myOperation.sum(number));
        LOGGER.debug("printWithDependency:debug -> " + number);
        LOGGER.info("printWithDependency:end");
    }
}
