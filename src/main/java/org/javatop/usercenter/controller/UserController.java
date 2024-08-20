package org.javatop.usercenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.javatop.usercenter.common.enums.HttpStatusEnum;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.dto.UserDto;
import org.javatop.usercenter.domian.vo.user.req.*;
import org.javatop.usercenter.exception.BizException;
import org.javatop.usercenter.service.UserService;
import org.javatop.usercenter.util.PageResponse;
import org.javatop.usercenter.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.javatop.usercenter.common.constant.UserConstant.ADMIN_ROLE;
import static org.javatop.usercenter.common.constant.UserConstant.USER_LOGIN_STATE;

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
     */
    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    /**
     * 用户注册
     *
     * @param vo 注册信息
     * @return Result
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterVO vo) {
        return userService.register(vo.getUserAccount(), vo.getUserPassword(), vo.getCheckPassword());
    }


    /**
     * 用户登录
     *
     * @param userVo  用户信息
     * @param request 请求
     * @return 登录成功-返回用户信息，登录失败-抛出异常
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserVO userVo, HttpServletRequest request) {
        String userAccount = userVo.getUserAccount();
        String userPassword = userVo.getUserPassword();
        return userService.login(userAccount, userPassword, request);
    }


    /**
     * 用户查询
     */
    @PostMapping("/pageList")
    public PageResponse userPageList(@RequestBody UserPageListVO userPageListVO, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BizException(HttpStatusEnum.UNAUTHORIZED);
        }
        return userService.pageList(userPageListVO);
    }


    /**
     * 删除用户
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteUser(@PathVariable Long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            throw new BizException(HttpStatusEnum.UNAUTHORIZED);
        }
        // 判断用户id是否合法
        if (id <= 0) {
            log.info("用户id不合法");
            throw new BizException(HttpStatusEnum.ERROR);
        }
        boolean b = userService.removeById(id);

        if (!b) {
            log.info("删除用户失败");
            throw new BizException(HttpStatusEnum.ERROR);
        }
        return Result.success();
    }

    /**
     * 更新用户
     */
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UpdateUserVO updateUserVO) {
        return userService.updateUser(updateUserVO);
    }


    /**
     * 判断用户是否为管理员
     *
     * @param request 请求
     * @return true-是管理员，false-不是管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 判断是否为管理员
        UserDto userDto = (UserDto) request.getSession().getAttribute(USER_LOGIN_STATE);
        return userDto != null && userDto.getUserRole() == ADMIN_ROLE;
    }
}
