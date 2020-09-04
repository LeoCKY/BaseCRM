package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu,String> {

    int deleteByPrimaryKey(SysRoleMenu key);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);
}