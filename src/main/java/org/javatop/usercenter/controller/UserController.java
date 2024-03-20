package org.javatop.usercenter.controller;

import org.apache.commons.lang3.StringUtils;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.dto.UserDto;
import org.javatop.usercenter.domian.vo.UserVo;
import org.javatop.usercenter.service.UserService;
import org.javatop.usercenter.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UnknownFormatConversionException;

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
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 注册成功-返回用户id，注册失败-抛出异常
     */
    @PostMapping("/register")
    public Long register(String userAccount, String userPassword, String checkPassword) {
        return userService.register(userAccount, userPassword, checkPassword);
    }


    /**
     * 用户登录
     * @param userVo 用户信息
     * @param request 请求
     * @return 登录成功-返回用户信息，登录失败-抛出异常
     */
    @PostMapping("/login")
    public UserDto login(@RequestBody UserVo userVo, HttpServletRequest request) {
        if (userVo == null){
            return null;
        }
        String userAccount = userVo.getUserAccount();
        String userPassword = userVo.getUserPassword();
        // 首先校验一遍参数
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        return userService.login(userAccount,userPassword,request);
    }

}
