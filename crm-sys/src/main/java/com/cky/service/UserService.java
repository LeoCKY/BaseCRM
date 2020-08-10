package com.cky.service;

import com.cky.model.system.entity.User;
import com.cky.model.system.entity.UserRole;

import java.util.List;

public interface UserService {

    User login(String account);

    User selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param user
     * @return
     */
    int add(User user);

    int checkUser(String username);


    int updateByPrimaryKey(User sysUser);

    List<UserRole> selectByCondition(UserRole sysRoleUser);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    int rePass(User user);


    List<User> getUserByRoleId(String roleId);

    void setMenuAndRoles(String username);

    void updateCurrent(User user);
}
