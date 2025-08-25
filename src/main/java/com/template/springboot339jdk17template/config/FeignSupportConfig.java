package com.template.springboot339jdk17template.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.springboot339jdk17template.interceptor.FeignBasicAuthRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignSupportConfig {
    @Autowired
    private ObjectMapper objectMapper;
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    @Bean
    public MapGenericHttpMessageConverter mapGenericHttpMessageConverter() {
        //经过feign的数据去除null值字段
        return new MapGenericHttpMessageConverter(objectMapper);
    }
}