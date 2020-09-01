package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysUserDAO;
import com.cky.entity.SysUser;
import com.cky.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SysUserDAOImpl extends BaseDAOImpl<SysUser, String> implements SysUserDAO {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public BaseMapper<SysUser, String> getMapper() {
        return sysUserMapper;
    }
}
