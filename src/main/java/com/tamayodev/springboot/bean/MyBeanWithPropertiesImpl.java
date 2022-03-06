package com.tamayodev.springboot.bean;

public class MyBeanWithPropertiesImpl implements MyBeanWithProperties {
    //
    private String name;
    private String lastName;
    private String random;
    //
    public MyBeanWithPropertiesImpl(String name, String lastName, String random) {
        this.name = name;
        this.lastName = lastName;
        this.random = random;
    }
    //
    @Override
    public String getUser() {
        return name + " " + lastName + "-" + random;
    }
}
