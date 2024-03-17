package org.javatop.usercenter;

import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.mapper.UserMapper;
import org.javatop.usercenter.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : Leo
 * @version 1.0
 * @date 2024-03-18 00:00
 * @description :
 */
@SpringBootTest(classes = UserCenterApplication.class)
public class UserTest {


    @Resource
    private UserService userService;


    /**
     *  用于测试:
     */
    @Test
    public void testSave() {
        // 添加用户
        User user = new User();

        user.setUsername("lisi");
        user.setUserAccount("lisi");
        user.setUserPassword("123456");
        user.setGender(0);
        user.setEmail("leo@qq.com");
        user.setPhone("13812345678");
        user.setAvatarUrl("https://avatars.githubusercontent.com/u/52664651?v=4");
        user.setUserRole(0);
        user.setUserStatus(0);

        // 保存用户
        userService.save(user);

    }
    
    
    /**
     *  用于测试: 修改用户
     */
    @Test
    public void testUpdate() {
        // 修改用户
        User user = userService.getById(51);
        user.setEmail("lisi@qq.com");
        // 修改用户
        userService.updateById(user);

    }
}
