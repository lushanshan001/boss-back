package com.dao.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doMain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
