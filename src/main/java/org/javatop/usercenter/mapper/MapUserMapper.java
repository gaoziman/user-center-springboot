package org.javatop.usercenter.mapper;

import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.dto.UserDto;
import org.mapstruct.Mapper;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-20 22:23
 * @description : 转换mapper
 */
@Mapper(componentModel = "spring")
public interface MapUserMapper {


    /**
     * 将用户对象转换为用户Dto对象
     * @param user 原始用户对象
     * @return 转换后的用户Dto对象
     */
    UserDto toUserDto(User user);
}


