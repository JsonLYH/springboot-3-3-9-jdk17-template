package com.template.springboot339jdk17template.util;


import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    /**
     * 尝试解析Long类型，如果解析失败，返回null
     * @param str
     * @return
     */
    public static Long parseLong(String str) {
        try {
            return Long.parseLong(str);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 获取时区,如果请求头中没有timezone，则返回系统默认时区
     * @return
     */
    public static String getTimeZone() {
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest httpServletRequest= attributes.getRequest();
            String timezone = httpServletRequest.getHeader("timezone");
            if(StrUtil.isBlank(timezone)) return getDefaultTimeZoneAsGMTOffset();
            return timezone;
        }catch (Exception e){
            //获取本系统时区
            return getDefaultTimeZoneAsGMTOffset();
        }
    }

    /**
     * 获取当前时区的GMT偏移量格式，如GMT+8
     *
     * @return 时区的GMT偏移量字符串
     */
    public static String getDefaultTimeZoneAsGMTOffset() {
        // 获取当前系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 获取当前时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // 获取与GMT的偏移量
        String offset = zonedDateTime.format(DateTimeFormatter.ofPattern("Z"));
        return "GMT" + offset;
    }
}
