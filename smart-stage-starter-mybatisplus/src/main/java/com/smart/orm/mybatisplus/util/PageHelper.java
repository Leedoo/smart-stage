package com.smart.orm.mybatisplus.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.smart.core.entity.Page;

public class PageHelper {

    /**
     * 分页Entity转换抽象
     *
     * @param page
     * @return
     */
    public static <T> Page<T> convert(IPage<T> page) {
        Page<T> p = Page.of(page.getCurrent(), page.getSize(), page.getTotal());
        p.setRecords(page.getRecords());
        return p;
    }
}
