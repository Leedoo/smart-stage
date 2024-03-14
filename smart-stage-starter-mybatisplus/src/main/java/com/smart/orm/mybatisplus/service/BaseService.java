package com.smart.orm.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.core.entity.Page;
import com.smart.orm.mybatisplus.util.PageHelper;

/**
 * MybatisPlus基础Service
 *
 * @param <T>
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 提供新的分页方法，统一分页返回Entity
     *
     * @param page
     * @param limit
     * @return
     */
    default Page<T> findPage(long page, long limit) {
        IPage<T> t = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page, limit));
        return PageHelper.convert(t);
    }

    /**
     * 提供新的分页方法，统一分页返回Entity
     *
     * @param page
     * @param limit
     * @param wrapper
     * @return
     */
    default Page<T> findPage(long page, long limit, Wrapper<T> wrapper) {
        IPage<T> t = page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(page, limit), wrapper);
        return PageHelper.convert(t);
    }
}