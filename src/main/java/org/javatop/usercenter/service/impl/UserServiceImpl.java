package org.javatop.usercenter.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.javatop.usercenter.mapper.UserMapper;
import org.javatop.usercenter.domian.User;
import org.javatop.usercenter.service.UserService;
/**
 * @author : Leo
 * @date  2024-03-17 21:29
 * @version 1.0
 * @description :
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
