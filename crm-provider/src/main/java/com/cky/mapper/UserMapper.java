package com.cky.mapper;

import com.cky.base.mapper.BaseMapper;
import com.cky.model.system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User, String> {

    User login(@Param("username") String username);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    int rePass(User user);

    List<User> getUserByRoleId(@Param("roleId") String roleId);
}
