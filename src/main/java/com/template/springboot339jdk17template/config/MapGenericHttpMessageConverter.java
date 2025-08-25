package com.template.springboot339jdk17template.config;

import com.alibaba.cloud.commons.io.IOUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapGenericHttpMessageConverter implements GenericHttpMessageConverter<Object> {

    private ObjectMapper objectMapper;

    public MapGenericHttpMessageConverter(ObjectMapper objectMapper) {
        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON);
    }

    @Override
    public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        if (type.toString().startsWith("java.util.Map")) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String s = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
        TypeReference<Map> typeReference = new TypeReference<Map>() {
            @Override
            public Type getType() {
                return type;
            }
        };
        Map m = objectMapper.readValue(s, typeReference);
        return m;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public void write(Object t, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    }

}
