package com.cky.service.impl;

import com.cky.dao.SysRoleMenuDAO;
import com.cky.entity.SysRoleMenu;
import com.cky.service.SysRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuDAO sysRoleMenuDAO;

    @Override
    public int insert(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDAO.insert(sysRoleMenu);
    }

    @Override
    public List<SysRoleMenu> selectByCondition(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDAO.select(sysRoleMenu);
    }

    @Override
    public int deleteByPrimaryKey(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDAO.deleteByPrimaryKey(sysRoleMenu);
    }

    @Override
    public int selectCountByCondition(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuDAO.selectCount(sysRoleMenu);
    }

    @Override
    public int selectCount(SysRoleMenu sysRoleMenu) {
        return 0;
    }
}
