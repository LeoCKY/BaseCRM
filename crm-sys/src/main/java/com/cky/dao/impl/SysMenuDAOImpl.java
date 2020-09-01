package com.cky.dao.impl;


import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysMenuDAO;
import com.cky.mapper.SysMenuMapper;
import com.cky.entity.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
@Slf4j
public class SysMenuDAOImpl extends BaseDAOImpl<SysMenu, String> implements SysMenuDAO {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public BaseMapper<SysMenu, String> getMapper() {
        return sysMenuMapper;
    }


    @Override
    public List<SysMenu> getMenuNotSuper() {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andIsNull("parentId");
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> getMenuChildren(String id) {
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("parentId", id);
        return sysMenuMapper.selectByExample(example);
    }

    @Override
    public List<SysMenu> getUserMenu(String id) {
        return sysMenuMapper.getUserMenu(id);
    }
}
