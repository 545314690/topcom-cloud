package com.topcom.cms.web.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello")
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello:" + instance.getHost() + ",service_id: " + instance.getServiceId());
        return "hello world";
    }

    @RequestMapping(value = "/add")
    public int add(int a, int b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add:" + instance.getHost() + ",service_id: " + instance.getServiceId());
        return a + b;
    }
}
