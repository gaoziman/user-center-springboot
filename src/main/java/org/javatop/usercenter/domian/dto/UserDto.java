package org.javatop.usercenter.domian.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-20 22:18
 * @description : 返回给前端的用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String userAccount;

    private String phone;

    private String email;

    private Integer gender;

    private Integer userRole;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
