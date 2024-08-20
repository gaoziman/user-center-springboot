package org.javatop.usercenter.domian.base;

import lombok.Data;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 16:32
 * @description : 分页查询基类
 */
@Data
public class BasePageQuery {
    /**
     * 当前页码, 默认第一页
     */
    private Long pageNum = 1L;
    /**
     * 每页展示的数据数量，默认每页展示 10 条数据
     */
    private Long pageSize = 5L;
}

