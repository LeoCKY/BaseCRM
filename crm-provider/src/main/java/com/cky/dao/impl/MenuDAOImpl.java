package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.MenuDAO;
import com.cky.mapper.MenuMapper;
import com.cky.model.system.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class MenuDAOImpl extends BaseDAOImpl<Menu, String> implements MenuDAO {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public BaseMapper<Menu, String> getMapper() {
        return menuMapper;
    }


    @Override
    public List<Menu> getMenuNotSuper() {
        Example example = new Example(Menu.class);
        example.createCriteria().andEqualTo("parentId", null);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> getMenuChildren(String id) {
        Example example = new Example(Menu.class);
        example.createCriteria().andEqualTo("parentId", id);
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> getUserMenu(String id) {
        return null;
    }
}
