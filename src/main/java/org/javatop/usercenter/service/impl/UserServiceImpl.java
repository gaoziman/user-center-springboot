package org.javatop.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.javatop.usercenter.common.enums.HttpStatusEnum;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.domian.dto.UserDto;
import org.javatop.usercenter.exception.BizException;
import org.javatop.usercenter.mapper.MapUserMapper;
import org.javatop.usercenter.mapper.UserMapper;
import org.javatop.usercenter.service.UserService;
import org.javatop.usercenter.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

import static org.javatop.usercenter.common.constant.UserConstant.USERACCOUNT_PATTERN;
import static org.javatop.usercenter.common.constant.UserConstant.USER_LOGIN_STATE;


/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-17 23:19
 * @description :
 */

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    // 编译正则表达式为Pattern对象
    private static final Pattern pattern = Pattern.compile(USERACCOUNT_PATTERN);
    public static final String SALT = "Leo";

    @Autowired
    private MapUserMapper mapUserMapper;


    /**
     * 校验用户名是否合法
     *
     * @param userAccount 用户名
     * @return true 合法，false 不合法
     */
    public static boolean validateUserAccount(String userAccount) {
        // 使用Pattern对象进行匹配检查
        return pattern.matcher(userAccount).matches();
    }

    /**
     * 注册
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 确认密码
     * @return 注册成功后返回用户id
     */
    @Override
    public Result register(String userAccount, String userPassword, String checkPassword) {
        // 1.4 账户名不包含特殊字符

        if (!validateUserAccount(userAccount)) {
            throw new BizException(HttpStatusEnum.PARAM_NOT_VALID);
        }

        // 1.5 账户名不能重复
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("userAccount", userAccount));
        if (user != null) {
            throw new BizException(HttpStatusEnum.USERNAME_EXIST);
        }

        // 1.6 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BizException(HttpStatusEnum.PASSWORD_NOT_MATCH);
        }
        // 2.加密 加盐 + 用户密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3.注册用户
        User saveUser = new User();
        saveUser.setUserAccount(userAccount);
        saveUser.setUserPassword(encryptPassword);
        saveUser.setUsername(userAccount);
        int row = baseMapper.insert(saveUser);
        if (row != 1) {
            throw new BizException(HttpStatusEnum.ERROR);
        }
        log.info("saveUser:{}", userAccount);
        return Result.success();
    }


    /**
     * 登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @return 登录成功后返回用户信息
     */
    @Override
    public Result login(String userAccount, String userPassword, HttpServletRequest request) {
        if (!validateUserAccount(userAccount)) {
            log.info("账户名包含特殊字符,selectUser:{}", userAccount);
            throw new BizException(HttpStatusEnum.PARAM_NOT_VALID);
        }

        // 2. 检验用户输入密码是否和数据库密码一致。需要和数据库中的密文密码去对比
        User selectUser = baseMapper.selectOne(new QueryWrapper<User>().eq("userAccount", userAccount));
        // 用户不存在
        if (selectUser == null) {
            log.info("用户不存在,selectUser:{}", userAccount);
            throw new BizException(HttpStatusEnum.USERNAME_NOT_FOUND);
        }
        if (!selectUser.getUserPassword().equals(DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes()))) {
            // 密码错误
            log.info("密码错误,selectUser:{}", userAccount);
            throw new BizException(HttpStatusEnum.USERNAME_OR_PWD_ERROR);
        }
        // 3. 将用户信息进行脱敏。并返回user
        UserDto userDto = mapUserMapper.toUserDto(selectUser);
        request.getSession().setAttribute(USER_LOGIN_STATE, userDto);
        return Result.success(userDto);
    }

}
