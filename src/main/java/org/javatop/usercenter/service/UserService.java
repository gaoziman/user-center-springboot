package org.javatop.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.vo.user.req.UpdateUserVO;
import org.javatop.usercenter.domian.vo.user.req.UserPageListVO;
import org.javatop.usercenter.util.PageResponse;
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


    /**
     * 更新用户信息
     * @param updateUserVO 用户信息
     * @return 更新成功-返回用户信息，更新失败-抛出异常
     */
    Result updateUser(UpdateUserVO updateUserVO);


    /**
     * 分页查询用户列表
     * @param userPageListVO 分页查询条件
     * @return 分页查询结果
     */
    PageResponse pageList(UserPageListVO userPageListVO);
}
