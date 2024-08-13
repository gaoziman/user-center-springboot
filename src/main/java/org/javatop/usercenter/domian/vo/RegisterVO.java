package org.javatop.usercenter.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-08-14 00:51
 * @description : 注册VO
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO implements Serializable {

    private static final long serialVersionUID = 1875823L;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 15, message = "用户名长度必须在4到15之间")
    public String userAccount;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6到20之间")
    public String userPassword;

    @NotBlank(message = "确认密码不能为空")
    public String checkPassword;
}
