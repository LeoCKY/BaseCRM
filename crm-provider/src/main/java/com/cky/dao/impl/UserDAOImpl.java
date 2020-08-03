package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.UserDAO;
import com.cky.mapper.UserMapper;
import com.cky.model.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDAOImpl extends BaseDAOImpl<User, String> implements UserDAO {

    @Autowired
    UserMapper userMapper;

    @Override
    public BaseMapper<User, String> getMapper() {
        return userMapper;
    }
}
