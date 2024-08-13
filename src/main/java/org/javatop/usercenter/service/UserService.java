package org.javatop.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Leo
 * @date  2024-03-17 23:19
 * @version 1.0
 * @description :
 */

public interface UserService extends IService<User>{


    /**
     * 注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 注册成功-返回用户id，注册失败-抛出异常
     */
    Result register(String userAccount , String userPassword, String checkPassword);


    /**
     * 登录
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 登录成功-返回用户信息，登录失败-抛出异常
     */
    Result login(String userAccount , String userPassword, HttpServletRequest request);

}
