package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysRoleUserDAO;
import com.cky.entity.SysRoleUser;
import com.cky.mapper.SysRoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SysRoleUserDAOImpl extends BaseDAOImpl<SysRoleUser, String> implements SysRoleUserDAO {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public BaseMapper<SysRoleUser, String> getMapper() {
        return sysRoleUserMapper;
    }
}
