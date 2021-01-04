package com.zuoyang;

import com.zuoyang.service.MyCustomerService;
import com.zuoyang.service.MyLazyService;
import com.zuoyang.service.UserService;
import context.MyAnnotationApplicationContext;

import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        MyAnnotationApplicationContext context = new MyAnnotationApplicationContext("com.zuoyang");
        System.out.println("Finish create the IOC.................");
        // test the component IOC with name
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService);

        // test the component IOC with customer IOC
        MyCustomerService myCustomerService = (MyCustomerService) context.getBean("my_customer");
        System.out.println(myCustomerService);

        // test lazy loading
        Object myLazyService = context.getBean("myLazyService");
        System.out.println(myLazyService);

        // test dependency loop

    }
}
