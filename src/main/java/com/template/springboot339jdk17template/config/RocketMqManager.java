package com.template.springboot339jdk17template.config;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class RocketMqManager implements EnvironmentAware {

    private Environment environment;

    public String getValue(String value) {
        String profile = getProfile();
        //如果是生产环境则直接返回value
        if(isProduct(profile)) {
            return value;
        } else {
            return value + "_" + profile;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * 是否是生产环境
     * @param profile
     * @return
     */
    private boolean isProduct(String profile) {
        return "prod".equals(profile);
    }

    private String getProfile(){
        String[] activeProfiles = environment.getActiveProfiles();
        if(ArrayUtils.isEmpty(activeProfiles)){
            throw new RuntimeException();
        }
        return activeProfiles[0];
    }

}

