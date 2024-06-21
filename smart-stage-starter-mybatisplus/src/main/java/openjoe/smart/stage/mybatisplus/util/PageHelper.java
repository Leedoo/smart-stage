package openjoe.smart.stage.mybatisplus.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import openjoe.smart.stage.core.entity.Page;

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
