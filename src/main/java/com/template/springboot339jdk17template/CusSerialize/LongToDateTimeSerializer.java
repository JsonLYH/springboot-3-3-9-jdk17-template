package com.template.springboot339jdk17template.CusSerialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.template.springboot339jdk17template.util.Util;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 序列化 时间戳-》日期时间
 */
@Component
public class LongToDateTimeSerializer extends JsonSerializer<Long> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String timezone = Util.getTimeZone();
        //时区
        ZoneId zoneId = ZoneId.of(timezone);
        if (value == null) {
            gen.writeNull();
        } else {
            Instant instant = Instant.ofEpochMilli(value);
            String formattedDateTime = DATE_TIME_FORMATTER.withZone(zoneId).format(instant);
            gen.writeString(formattedDateTime);
        }
    }
}
