package com.zuoyang.service;

import core.annotations.MyLazy;
import core.annotations.MyService;

@MyService
@MyLazy
public class MyLazyService {
    public MyLazyService() {
        System.out.println("test for Lazy Loading................");
    }
}
