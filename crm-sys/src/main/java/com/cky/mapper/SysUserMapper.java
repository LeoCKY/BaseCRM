package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser, String> {

    List<SysUser> selectListByPage(SysUser sysUser);

}