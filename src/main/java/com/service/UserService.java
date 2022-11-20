package com.service;
import com.dao.impl.UserDao;
import com.doMain.User;

public interface UserService {

    /**
     * 邮箱是否已注册
     * @param email
     * @return
     */
    User emailExist(String email);

    /**
     * 用户注册
     * @param
     */
    void register(User user);

    /**
     * 用户登录
     * @param  username 用户名
     * @param  password 用户密码
     * @return
     */
    User login(String username,String password);
}
