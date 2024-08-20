package org.javatop.usercenter.domian.vo.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-19 19:45
 * @description : 更新用户信息VO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserVO  implements Serializable {

    private static final long serialVersionUID = 1875893L;

    private String userAccount;

    @NotBlank(message = "用户昵称不能为空")
    @Length(min = 4, max = 15, message = "用户昵称长度必须在4到15之间")
    private String username;



    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "头像地址不能为空")
    private String avatarUrl;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotNull(message = "性别不能为空")
    private Integer gender;


}
