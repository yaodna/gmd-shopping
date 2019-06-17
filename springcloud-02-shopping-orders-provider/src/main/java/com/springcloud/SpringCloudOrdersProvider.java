package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.springcloud.dao")
@EnableEurekaClient
public class SpringCloudOrdersProvider {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudOrdersProvider.class, args);

	}
}
