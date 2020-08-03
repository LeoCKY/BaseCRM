package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.RoleDAO;
import com.cky.mapper.RoleMapper;
import com.cky.model.system.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleDAOImpl extends BaseDAOImpl<Role, String> implements RoleDAO {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseMapper<Role, String> getMapper() {
        return roleMapper;
    }


}
