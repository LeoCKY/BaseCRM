package com.cky.mapper;


import com.cky.entity.SysRoleMenuKey;

public interface SysRoleMenuMapper {

    int deleteByPrimaryKey(SysRoleMenuKey key);

    int insert(SysRoleMenuKey record);

    int insertSelective(SysRoleMenuKey record);
}