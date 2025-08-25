package com.template.springboot339jdk17template.CusSerialize;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.template.springboot339jdk17template.util.Util;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 反序列化 时间字符串转时间戳
 */
@Component
public class LocalDateTimeToLongDeserializer extends JsonDeserializer<Long> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss XXX");
    private static final String[] formats= {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String timezone = Util.getTimeZone();
        //时区
        ZoneId zoneId = ZoneId.of(timezone);
        String dateTimeStr = p.getText();
        if(StrUtil.isBlank(dateTimeStr)) return null;
        //1.判断入参是不是时间戳,如果是时间戳直接返回
        Long timestamp = Util.parseLong(dateTimeStr);
        if(timestamp!=null){
            return timestamp;
        }
        try{
            //2.如果前端传入带时区偏移量的时间字符串，比如2022-02-11 07:14:15 +08:00
            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
            //3.将OffsetDateTime转换为Instant（已经是UTC时区），然后转换为自1970年1月1日UTC以来的毫秒数
            return offsetDateTime.toInstant().toEpochMilli();
        }catch (Exception e){
            LocalDateTime localDateTime=null;
            for (int i =0; i<formats.length; i++){
                try{
                    if(i==0){
                        LocalDate localDate = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern(formats[i]));
                        localDateTime = localDate.atStartOfDay();
                    }else{
                        localDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(formats[i]));
                    }
                    break;
                }catch (Exception ex){
                    continue;
                }
            }
            if(localDateTime!=null){
                return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
            }
            throw new RuntimeException("前端传入时间格式错误");
        }
    }
}
