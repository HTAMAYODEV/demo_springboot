package com.tamayodev.springboot.bean;

public class MyBeanImpl implements MyBean {
    @Override
    public void print() {
        System.out.println("Print from Bean!");
    }
}
