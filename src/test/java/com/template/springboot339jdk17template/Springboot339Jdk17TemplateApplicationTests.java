package com.template.springboot339jdk17template;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.template.springboot339jdk17template.mapper")
@SpringBootTest
class Springboot339Jdk17TemplateApplicationTests {

	@Test
	void contextLoads() {

	}

}
