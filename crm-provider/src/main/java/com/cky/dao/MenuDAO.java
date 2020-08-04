package com.cky.dao;

import com.cky.base.dao.BaseDAO;
import com.cky.model.system.entity.Menu;

import java.util.List;

public interface MenuDAO extends BaseDAO<Menu, String> {

    List<Menu> getMenuNotSuper();

    List<Menu> getMenuChildren(String id);

    List<Menu> getUserMenu(String id);
}
