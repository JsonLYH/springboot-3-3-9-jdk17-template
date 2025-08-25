package com.template.springboot339jdk17template.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.springboot339jdk17template.util.CustomJsonHttpMessageConverter;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {
//    @Bean
//    public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory){
//        return new RestTemplate(requestFactory);
//    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ClientHttpRequestFactory requestFactory) {
        List<HttpMessageConverter<?>> converters = builder.build().getMessageConverters();
        converters.add(new CustomJsonHttpMessageConverter(new ObjectMapper())); // 添加自定义转换器
        RestTemplate restTemplate = builder.messageConverters(converters).build();
        restTemplate.setRequestFactory(requestFactory); // 设置请求工厂
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory(){
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        //设置连接池最大是500个连接
        connectionManager.setMaxTotal(500);
        //MaxPerRoute是对maxtotal的细分，每个主机的并发最大是300，route是指域名
        connectionManager.setDefaultMaxPerRoute(300);
        RequestConfig requestConfig = RequestConfig.custom()
                //返回数据的超时时间
                .setConnectionRequestTimeout(120, TimeUnit.SECONDS)
                .setResponseTimeout(120,TimeUnit.SECONDS)
                .build();
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
        return new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
    }
}