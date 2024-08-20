package org.javatop.usercenter.domian.vo.user.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-20 22:18
 * @description : 封装一个对象来记录所有的请求参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1875893L;


    @NotBlank(message = "用户名不能为空")
    @Length(min = 4, max = 15, message = "用户名长度必须在4到15之间")
    private String userAccount;



    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 10, message = "密码长度必须在6到10之间")
    private String userPassword;


}
