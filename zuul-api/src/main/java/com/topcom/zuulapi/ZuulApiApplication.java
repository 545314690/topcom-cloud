package com.topcom.zuulapi;


import com.topcom.zuulapi.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
public class ZuulApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiApplication.class, args);
	}
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

}
