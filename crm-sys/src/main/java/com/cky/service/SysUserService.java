package com.cky.service;

import com.cky.entity.SysRoleUser;
import com.cky.entity.SysUser;

import java.util.List;

public interface SysUserService {

    SysUser login(String account);

    SysUser selectByPrimaryKey(String id);

    /**
     * 新增
     *
     * @param sysUser
     * @return
     */
    int add(SysUser sysUser);

    int checkUser(String username);


    int updateByPrimaryKey(SysUser sysUser);

    List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser);

    /**
     * 更新密码
     *
     * @param SysUser
     * @return
     */
    int rePass(SysUser SysUser);


    List<SysUser> getUserByRoleId(String roleId);

    void setMenuAndRoles(String username);

    void updateCurrent(SysUser SysUser);
}
