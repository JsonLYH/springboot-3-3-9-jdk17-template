package com.template.springboot339jdk17template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Springboot339Jdk17TemplateApplication {
	public static void main(String[] args) {
		SpringApplication.run(Springboot339Jdk17TemplateApplication.class, args);
	}

}
