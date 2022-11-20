package com.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.dao.impl.UserDao;
import com.doMain.User;
import com.service.UserService;
import com.util.EmailSender;
import com.util.Result;
import com.util.ResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @SneakyThrows
    @PostMapping("/sendCode")
    public Result sendCode(@RequestBody User user){
        String code = EmailSender.sendEmail(user.getEmail());
        if(code.equals("-1"))return Result.error(ResultCode.ERROR,"发送验证码失败");
        //将验证码缓存到redis，有效时间5分钟
        redisTemplate.opsForValue().set(user.getEmail(),code,5, TimeUnit.MINUTES);
        return Result.success();
    }

    /**
     * 用户注册
     * @param
     * @return
     */
    @PostMapping("/register")
    public Result reg(@RequestBody User user){
        userService.register(user);
        return Result.success();
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(String username, String password) {
        User login = userService.login(username, password);
        return Result.success(login);
    }

    @SneakyThrows
    @PostMapping("/forget-password")
    public Result forgetPasswordIsExist(@PathVariable("email") String email) {
        User user = userService.emailExist(email);
        if(user == null){//邮箱没有注册
            return Result.error(ResultCode.ERROR,"您还未注册");
        }else{//邮箱已经注册发送验证码
            String code = EmailSender.sendEmail(user.getEmail());
            if(code.equals("-1"))return Result.error(ResultCode.ERROR,"发送验证码失败");
            //将验证码缓存到redis，有效时间5分钟
            redisTemplate.opsForValue().set(user.getEmail(),code,5, TimeUnit.MINUTES);
            return Result.success();
        }
    }

}
