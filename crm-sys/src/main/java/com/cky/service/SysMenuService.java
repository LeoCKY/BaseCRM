package com.cky.service;

import com.alibaba.fastjson.JSONArray;
import com.cky.entity.SysMenu;


import java.util.List;


public interface SysMenuService {

    List<SysMenu> getMenuNotSuper();

    int insert(SysMenu menu);

    List<SysMenu> getMenuChildren(String id);

    public JSONArray getMenuJsonList();

    List<SysMenu> getMenuChildrenAll(String id);

    public JSONArray getTreeUtil(String roleId);

    List<SysMenu> getUserMenu(String id);

    public JSONArray getMenuJsonByUser(List<SysMenu> menuList);

}
