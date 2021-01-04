package com.zuoyang.service;

import core.annotations.MyAutowired;
import core.annotations.MyComponent;

/**
 * test autowire
 */
@MyComponent
public class UserService {
    @MyAutowired
    private MyCustomerService my_customer;

    public MyCustomerService getMyCustomerService() {
        return my_customer;
    }
}
