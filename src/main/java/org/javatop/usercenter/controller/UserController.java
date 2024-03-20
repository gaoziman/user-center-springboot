package org.javatop.usercenter.controller;

import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.service.UserService;
import org.javatop.usercenter.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * (user)表控制层
 *
 * @author Leo
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 查询所有用户
     *
     * @return {@link List}<{@link User}>
     */
    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    /**
     * 注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 注册成功-返回用户id，注册失败-抛出异常
     */
    @PostMapping("/register")
    public Long register(String userAccount, String userPassword, String checkPassword) {
        return userService.register(userAccount, userPassword, checkPassword);
    }

}
