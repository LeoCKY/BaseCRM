package com.cky.service;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysMenu;

import java.util.List;


public interface SysMenuService {

    List<SysMenu> getMenuNotSuper();

    int insert(SysMenu menu);

    List<SysMenu> getMenuChildren(String id);

    JSONArray getMenuJsonList();

    List<SysMenu> getMenuChildrenAll(String id);

    JSONArray getTreeUtil(String roleId);

    List<SysMenu> getUserMenu(String id);

    JSONArray getMenuJsonByUser(List<SysMenu> menuList);

    ResJSONBean del(String id);

    SysMenu selectByPrimaryKey(String id);

    Integer updateByPrimaryKeySelective(SysMenu sysMenu);

    int insertSelective(SysMenu sysMenu);

}
