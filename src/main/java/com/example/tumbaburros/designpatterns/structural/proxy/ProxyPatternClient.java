package com.example.tumbaburros.designpatterns.structural.proxy;

public class ProxyPatternClient {
    {
        OfficeInternetAccess access = new ProxyInternetAccess("Ashwani Rajput");
        access.grantInternetAccess();
    }
}
