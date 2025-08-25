package com.template.springboot339jdk17template.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import java.io.IOException;

public class CustomJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    private final ObjectMapper objectMapper;

    public CustomJsonHttpMessageConverter(ObjectMapper objectMapper) {
        super(MediaType.valueOf("text/json"), MediaType.APPLICATION_JSON); // 支持 text/json 和 application/json
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true; // 支持所有类型对象
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        return objectMapper.readValue(inputMessage.getBody(), clazz);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException {
        outputMessage.getBody().write(objectMapper.writeValueAsBytes(o));
    }
}
