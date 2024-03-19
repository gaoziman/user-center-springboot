package org.javatop.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javatop.usercenter.mapper.UserMapper;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.service.UserService;
import org.springframework.util.DigestUtils;


/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-17 23:19
 * @description :
 */

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 正则表达式规则：以字母开头，仅包含字母、数字和下划线
    private static final String USERACCOUNT_PATTERN = "^[A-Za-z]\\w+$";

    // 编译正则表达式为Pattern对象
    private static final Pattern pattern = Pattern.compile(USERACCOUNT_PATTERN);
    public static final String SALT = "Leo";

    /**
     * 校验用户名是否合法
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
    public long register(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        // 1.1 非空判断
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }

        // 1.2 账户长度 不小于 4 位
        if (userAccount.length() < 4) {
            return -1;
        }
        // 1.3 密码长度 不小于 8 位
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            return -1;
        }

        //1.4 账户名不包含特殊字符

        if (!validateUserAccount(userAccount)) {
            return -1;
        }

        //1.5 账户名不能重复
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("userAccount", userAccount));
        if (user != null) {
            return -1;
        }

        //1.6 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
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
            return -1;
        }
        log.info("saveUser:{}", userAccount);
        return saveUser.getId();
    }
}
