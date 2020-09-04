package com.cky.dao;



import com.cky.base.dao.BaseDAO;
import com.cky.entity.SysMenu;

import java.util.List;

public interface SysMenuDAO extends BaseDAO<SysMenu, String> {

    List<SysMenu> getMenuNotSuper();

    List<SysMenu> getMenuChildren(String id);

    List<SysMenu> getUserMenu(String id);


}
