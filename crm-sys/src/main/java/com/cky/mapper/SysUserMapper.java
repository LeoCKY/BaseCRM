package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser,String> {

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

}