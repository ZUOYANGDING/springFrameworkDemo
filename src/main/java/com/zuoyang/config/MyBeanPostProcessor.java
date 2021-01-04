package com.zuoyang.config;

import core.BeanPostProcessor;
import core.annotations.MyComponent;

@MyComponent
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInit(String beanName, Object bean) {
        System.out.println("Do the initializing process for bean:=====" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInit(String beanName, Object bean) {
        System.out.println("Do the processing after initializing of bean:=====" + beanName);
        return bean;
    }
}
