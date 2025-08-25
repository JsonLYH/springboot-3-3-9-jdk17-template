package com.template.springboot339jdk17template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMqConfig {
    @Bean("rocketMqManager")
    public RocketMqManager rocketMqManager(){
        return new RocketMqManager();
    }
}
