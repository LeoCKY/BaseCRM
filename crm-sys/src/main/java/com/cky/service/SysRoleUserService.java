package com.cky.service;

import com.cky.entity.SysRoleUser;

import java.util.List;

public interface SysRoleUserService {

    List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser);

    int selectCountByCondition(SysRoleUser sysRoleUser);

    int insertSelective(SysRoleUser sysRoleUser);

    int deleteByPrimaryKey(SysRoleUser sysRoleUser);
}
