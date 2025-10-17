package com.template.springboot339jdk17template.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.template.springboot339jdk17template.CusSerialize.LocalDateTimeToLongDeserializer;
import com.template.springboot339jdk17template.CusSerialize.LongToDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntityTimeStamp implements Serializable {


    private static final long serialVersionUID = -7959490808407820804L;

    @TableField(fill = FieldFill.INSERT)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = LongToDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeToLongDeserializer.class)
    private Long createTimestamp;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = LongToDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeToLongDeserializer.class)
    private Long updateTimestamp;
}
