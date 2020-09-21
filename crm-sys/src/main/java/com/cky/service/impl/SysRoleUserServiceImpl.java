package com.cky.service.impl;

import com.cky.dao.SysRoleUserDAO;
import com.cky.entity.SysRoleUser;
import com.cky.service.SysRoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserDAO sysRoleUserDAO;

    @Override
    public List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser) {
        return sysRoleUserDAO.select(sysRoleUser);
    }

    @Override
    public int selectCountByCondition(SysRoleUser sysRoleUser) {
        return sysRoleUserDAO.selectCount(sysRoleUser);
    }

    @Override
    public int insertSelective(SysRoleUser sysRoleUser) {
        return sysRoleUserDAO.insertSelective(sysRoleUser);
    }

    @Override
    public int deleteByPrimaryKey(SysRoleUser sysRoleUser) {
        return sysRoleUserDAO.delete(sysRoleUser);
    }
}
