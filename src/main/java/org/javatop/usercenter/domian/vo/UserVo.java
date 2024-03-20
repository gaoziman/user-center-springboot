package org.javatop.usercenter.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1875893L;

    private String userAccount;

    private String userPassword;


}
