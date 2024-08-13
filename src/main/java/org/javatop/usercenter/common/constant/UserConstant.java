package org.javatop.usercenter.common.constant;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-22 9:44
 * @description :
 */
public class UserConstant {

    /* 正则校验  正则表达式规则：以字母开头，仅包含字母、数字和下划线*/
    public static final String USERACCOUNT_PATTERN = "^[A-Za-z]\\w+$";

    /* 用户登录相关 */
    public static final String USER_LOGIN_STATE = "userLoginState";


    /* 权限相关 */

    // 普通用户权限默认值
    public static final int DEFAULT_ROLE = 0;

    // 管理员权限
    public static final int ADMIN_ROLE = 1;
}
