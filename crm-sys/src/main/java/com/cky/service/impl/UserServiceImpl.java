package com.cky.service.impl;

import com.cky.dao.UserDAO;
import com.cky.model.system.entity.User;
import com.cky.model.system.entity.UserRole;
import com.cky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User login(String account) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("account", account);
        User user = userDAO.selectOneByExample(example);
        return user;
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return userDAO.selectByPrimaryKey(id);
    }

    @Override
    public int add(User user) {
        return 0;
    }

    @Override
    public int checkUser(String username) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User sysUser) {
        return 0;
    }

    @Override
    public List<UserRole> selectByCondition(UserRole sysRoleUser) {
        return null;
    }

    @Override
    public int rePass(User user) {
        return 0;
    }

    @Override
    public List<User> getUserByRoleId(String roleId) {
        return null;
    }

    @Override
    public void setMenuAndRoles(String username) {


    }

    @Override
    public void updateCurrent(User user) {

    }
}
