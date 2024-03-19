package org.javatop.usercenter.service;

import org.javatop.usercenter.domian.User;
import com.baomidou.mybatisplus.extension.service.IService;
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
    long register(String userAccount ,String userPassword,String checkPassword);


}
