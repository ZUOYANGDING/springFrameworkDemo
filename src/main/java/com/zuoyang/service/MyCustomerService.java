package com.zuoyang.service;

import core.annotations.MyAutowired;
import core.annotations.MyService;

@MyService(name = "my_customer")
public class MyCustomerService {
    @MyAutowired
    private UserService userService;
}
