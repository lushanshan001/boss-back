package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dao.impl.UserDao;
import com.doMain.User;
import com.exception.MyException;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        String email=user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        if (username==null || password==null||email==null){
            throw new MyException("输入的用户名或密码不能为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User u =  userDao.selectOne(queryWrapper);
        if (u!=null){
            throw new MyException("邮箱已经被注册");
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        user.setPassword(md5Password);
        user.setUsername(username);
        Integer rows = userDao.insert(user);
        if (rows != 1) {
            throw new MyException("添加数据失败");
        }
    }

    @Override
    public User login(String email, String password) {
        if (email==null || password==null){
            throw new MyException("输入账户或密码为空");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        queryWrapper.eq("is_delete",0);
        User t = userDao.selectOne(queryWrapper);
        if (t==null){
            throw new MyException("找不到用户信息");
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        if (!t.getPassword().equals(md5Password)) {
            throw new MyException("密码不正确");
        }
        User user = new User();
        user.setId(t.getId());
        user.setUsername(t.getUsername());
        user.setAvatar(t.getAvatar());
        return user;
    }

    @Override
    public User emailExist(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User u =  userDao.selectOne(queryWrapper);
        return u;
    }
}
