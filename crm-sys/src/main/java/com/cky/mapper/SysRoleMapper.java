package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole, String> {

    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}