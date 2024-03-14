package com.smart.orm.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.smart.orm.mybatisplus.entity.BaseEntity;
import lombok.Setter;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

@Setter
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入数据填充公共数据
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = getNow();
        if (metaObject.hasSetter(BaseEntity.CREATE_TIME)) {
            this.setFieldValByName(BaseEntity.CREATE_TIME, now, metaObject);
        }
        if (metaObject.hasSetter(BaseEntity.UPDATE_TIME)) {
            this.setFieldValByName(BaseEntity.UPDATE_TIME, now, metaObject);
        }
    }

    /**
     * 更新数据填充公共数据
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = getNow();
        if (metaObject.hasSetter(BaseEntity.UPDATE_TIME)) {
            this.setFieldValByName(BaseEntity.UPDATE_TIME, now, metaObject);
        }
    }

    private Date getNow(){
        return new Date();
    }
}