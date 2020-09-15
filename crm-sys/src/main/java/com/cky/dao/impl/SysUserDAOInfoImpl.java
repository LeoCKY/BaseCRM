package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysUserInfoDAO;
import com.cky.entity.SysUserInfo;
import com.cky.mapper.SysUserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SysUserDAOInfoImpl extends BaseDAOImpl<SysUserInfo, String> implements SysUserInfoDAO {

    @Autowired
    SysUserInfoMapper sysUserMapper;

    @Override
    public BaseMapper<SysUserInfo, String> getMapper() {
        return sysUserMapper;
    }
}
