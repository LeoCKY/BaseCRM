package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysRoleDAO;
import com.cky.mapper.SysRoleMapper;
import com.cky.entity.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SysRoleDAOImpl extends BaseDAOImpl<SysRole, String> implements SysRoleDAO {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public BaseMapper<SysRole, String> getMapper() {
        return sysRoleMapper;
    }


}
