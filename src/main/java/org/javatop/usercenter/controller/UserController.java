package org.javatop.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.dto.UserDto;
import org.javatop.usercenter.domian.vo.UserVo;
import org.javatop.usercenter.service.UserService;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.javatop.usercenter.constant.UserConstant.ADMIN_ROLE;
import static org.javatop.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * (user)表控制层
 *
 * @author Leo
 */
@RestController
@RequestMapping("/user")
@Slf4j
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
    public UserDto login(@RequestBody UserVo userVo, HttpServletRequest request){
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


    /**
     * 用户查询
     * @param username 用户名
     * @param request 请求
     * @return
     */
    @GetMapping("/search")
    public List<User> searchUser(String username,HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            wrapper.like("username", username);
        }
        return userService.list(wrapper);
    }


    /**
     * 删除用户
     * @param id 用户id
     * @param request 请求
     * @return
     */
    @PostMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id,HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        // 判断用户id是否合法
        if (id <= 0) {
            log.info("用户id不合法");
            return false;
        }
        return userService.removeById(id);
    }


    /**
     * 判断用户是否为管理员
     * @param request 请求
     * @return true-是管理员，false-不是管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 判断是否为管理员
        UserDto userDto = (UserDto) request.getSession().getAttribute(USER_LOGIN_STATE);
        return userDto != null && userDto.getUserRole() == ADMIN_ROLE;
    }
}
