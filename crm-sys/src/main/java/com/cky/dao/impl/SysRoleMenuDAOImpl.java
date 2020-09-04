package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysRoleMenuDAO;
import com.cky.entity.SysRoleMenu;
import com.cky.mapper.SysRoleMenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SysRoleMenuDAOImpl extends BaseDAOImpl<SysRoleMenu, String> implements SysRoleMenuDAO {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public BaseMapper<SysRoleMenu, String> getMapper() {
        return sysRoleMenuMapper;
    }
}
