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

}
