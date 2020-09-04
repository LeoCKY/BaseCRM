package com.cky.service;

import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysRole;

import java.util.List;

public interface SysRoleService {

    SysRole selectByPrimaryKey(String id);

    ResJSONBean addRole(SysRole sysRole, String[] menus);

    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectListByPage(SysRole sysRole);

    ResJSONBean updateUser(SysRole role, String[] menus);

    ResJSONBean del(String id);

    ReType show(SysRole role, int page, int limit);

    String showAll(SysRole role);

}
