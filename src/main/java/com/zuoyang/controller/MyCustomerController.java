package com.zuoyang.controller;

import com.zuoyang.service.UserService;
import core.annotations.MyAutowired;
import core.annotations.MyController;

@MyController
public class MyCustomerController {
    @MyAutowired
    private UserService userService;
}
