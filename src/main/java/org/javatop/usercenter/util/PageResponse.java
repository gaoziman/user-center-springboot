package org.javatop.usercenter.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import org.javatop.usercenter.common.enums.HttpStatusEnum;

import java.util.List;
import java.util.Objects;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-07-25 16:29
 * @description : 分页查询结果
 */
@Data
public class PageResponse<T> extends Result<List<T>> {
    /**
     * 总记录数
     */
    private long total;

    /**
     * 每页显示的记录数，默认每页显示 10 条
     */
    private long pageSize;

    /**
     * 当前页码
     */
    private long pageNum;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 成功响应
     * @param page Mybatis Plus 提供的分页接口
     * @param data 分页查询结果
     * @return PageResponse
     * @param <T> 分页查询结果的类型
     */
    public static <T> PageResponse<T> success(IPage page, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setStatus(true);
        response.setCode(HttpStatusEnum.SUCCESS.getCode());
        response.setMessage(HttpStatusEnum.SUCCESS.getMessage());
        response.setPageNum(Objects.isNull(page) ? 1L : page.getCurrent());
        response.setPageSize(Objects.isNull(page) ? 5L : page.getSize());
        response.setPages(Objects.isNull(page) ? 0L : page.getPages());
        response.setTotal(Objects.isNull(page) ? 0L : page.getTotal());
        response.setData(data);
        return response;
    }
}
