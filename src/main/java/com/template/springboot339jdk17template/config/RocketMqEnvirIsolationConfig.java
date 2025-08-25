package com.template.springboot339jdk17template.config;

import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * RocketMQ多环境隔离配置
 * 原理：对于每个配置的Bean在实例化前，拿到Bean的监听器注解把group或者topic改掉
 */
@Configuration
public class RocketMqEnvirIsolationConfig implements BeanPostProcessor {
    @Autowired
    RocketMqManager rocketMqManager;
    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean,
                                                  @NonNull String beanName) throws BeansException {
        if (bean instanceof DefaultRocketMQListenerContainer) {
            DefaultRocketMQListenerContainer container = (DefaultRocketMQListenerContainer) bean;
            container.setTopic(rocketMqManager.getValue(container.getTopic()));
            container.setConsumerGroup(rocketMqManager.getValue(container.getConsumerGroup()));
            return container;
        }
        return bean;
    }
}