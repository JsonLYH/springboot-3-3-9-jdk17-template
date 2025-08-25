package com.template.springboot339jdk17template.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        //让Get请求，也走自定义的日期格式化
        registry.addFormatter(new CustomDateFormatter());
    }
}
