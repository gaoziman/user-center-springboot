package org.javatop.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

}