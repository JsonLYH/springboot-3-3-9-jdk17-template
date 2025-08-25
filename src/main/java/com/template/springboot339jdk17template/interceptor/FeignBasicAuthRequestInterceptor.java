package com.template.springboot339jdk17template.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Enumeration;

@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("======FeignBasicAuthRequestInterceptor======");
        // 此种方式是线程安全的
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        // 不为空时取出请求中的header 原封不动的设置到feign请求中
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            // 遍历设置 也可从request取出整个Header 写到RequestTemplate 中
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    // 跳过 content-length
                    if (name.equals("content-length")){
                        continue;
                    }
                    requestTemplate.header(name, values);
                }
                //区分feign调用
                requestTemplate.header("feign","1");
            }
        }
    }
}
