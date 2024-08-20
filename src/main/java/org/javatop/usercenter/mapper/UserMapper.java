package org.javatop.usercenter.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.javatop.usercenter.domian.User;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-17 23:19
 * @description :
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    default Page<User> selectPageList(Long pageNum, Long pageSize, String userAccount, String email, String phone) {
        // 分页对象(查询第几页、每页多少数据)
        Page<User> page = new Page<>(pageNum, pageSize);
        // 构建查询条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        // 如果参数不为空，才加入查询条件
        if (userAccount != null && !userAccount.trim().isEmpty()) {
            wrapper.like(User::getUserAccount, userAccount);
        }
        if (email != null && !email.trim().isEmpty()) {
            wrapper.like(User::getEmail, email);
        }
        if (phone != null && !phone.trim().isEmpty()) {
            wrapper.like(User::getPhone, phone);
        }

        // 执行分页查询
        return this.selectPage(page, wrapper);
    }

}