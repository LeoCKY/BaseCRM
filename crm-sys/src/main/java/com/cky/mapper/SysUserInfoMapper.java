package com.cky.mapper;


import com.cky.entity.SysUserInfo;

public interface SysUserInfoMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysUserInfo record);

    int insertSelective(SysUserInfo record);

    SysUserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserInfo record);

    int updateByPrimaryKey(SysUserInfo record);
}