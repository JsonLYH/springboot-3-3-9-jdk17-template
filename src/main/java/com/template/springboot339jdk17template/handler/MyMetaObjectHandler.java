package com.template.springboot339jdk17template.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author：weidaoru
 * @Date：2023/2/21 11:49
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增填充创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //判断是否有创建时间
        if (metaObject.hasSetter("createTime")) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
        //判断是否有更新时间
        if (metaObject.hasSetter("updateTime")) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
        //判断是否有创建时间戳
        if (metaObject.hasSetter("createTimestamp")) {
            this.setFieldValByName("createTimestamp",System.currentTimeMillis(),metaObject);
        }
        //判断是否有更新时间戳
        if (metaObject.hasSetter("updateTimestamp")) {
            this.setFieldValByName("updateTimestamp",System.currentTimeMillis(),metaObject);
        }
    }

    /**
     * 更新填充更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //判断是否有更新时间
        if (metaObject.hasSetter("updateTime")) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
        //判断是否有更新时间戳
        if (metaObject.hasSetter("updateTimestamp")) {
            this.setFieldValByName("updateTimestamp",System.currentTimeMillis(),metaObject);
        }
    }
}
