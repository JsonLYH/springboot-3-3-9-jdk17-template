package com.template.springboot339jdk17template.config;

import com.template.springboot339jdk17template.util.Util;
import org.springframework.format.Formatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomDateFormatter implements Formatter<Long> {
    private static final String[] DATE_FORMATS = new String[] {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy/MM/dd",
            "MM/dd/yyyy",
            "dd-MM-yyyy"
    };

    @Override
    public Long parse(String text, Locale locale) throws ParseException {
        LocalDateTime localDateTime=null;
        Long parseData = Util.parseLong(text);
        if(parseData !=null) return parseData;
        for (int i = 0; i < DATE_FORMATS.length; i++) {
            try{
                if(i==0){
                    localDateTime = LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATE_FORMATS[i]));
                }else{
                    LocalDate localDate = LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_FORMATS[i]));
                    localDateTime = localDate.atStartOfDay();
                }
                break;
            }catch (Exception ex){
                continue;
            }
        }
        ZoneId zoneId = ZoneId.of(Util.getTimeZone());
        return localDateTime ==null ? Long.valueOf(text) : localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    @Override
    public String print(Long object, Locale locale) {
        return null;
    }
}
