package com.cky.service;

import com.cky.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService {

    int insert(SysRoleMenu sysRoleMenu);

    int selectCountByCondition(SysRoleMenu sysRoleMenu);

    List<SysRoleMenu> selectByCondition(SysRoleMenu sysRoleMenu);

    int deleteByPrimaryKey(SysRoleMenu sysRoleMenu);

    int selectCount(SysRoleMenu sysRoleMenu);
}
