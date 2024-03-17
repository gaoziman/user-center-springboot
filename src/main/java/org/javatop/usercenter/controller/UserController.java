package org.javatop.usercenter.controller;

import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.service.UserService;
import org.javatop.usercenter.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * (star-code.`user`)表控制层
 *
 * @author Leo
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable Integer id) {
        return userService.getById(id);
    }

}
