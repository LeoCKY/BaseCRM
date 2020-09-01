package com.cky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cky.dao.SysMenuDAO;
import com.cky.entity.SysMenu;
import com.cky.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDAO sysMenuDAO;

    @Override
    public List<SysMenu> getMenuNotSuper() {
        return null;
    }

    @Override
    public int insert(SysMenu menu) {
        return 0;
    }

    @Override
    public List<SysMenu> getMenuChildren(String id) {
        return null;
    }

    @Override
    public JSONArray getMenuJsonList() {
        return null;
    }

    @Override
    public List<SysMenu> getMenuChildrenAll(String id) {
        return null;
    }

    @Override
    public JSONArray getTreeUtil(String roleId) {
        return null;
    }

    @Override
    public List<SysMenu> getUserMenu(String id) {
        return sysMenuDAO.getUserMenu(id);
    }

    @Override
    public JSONArray getMenuJsonByUser(List<SysMenu> menuList) {
        JSONArray jsonArr = new JSONArray();
        Collections.sort(menuList, (o1, o2) -> {
            if (o1.getOrderNum() == null || o2.getOrderNum() == null) {
                return -1;
            }
            if (o1.getOrderNum() > o2.getOrderNum()) {
                return 1;
            }
            if (o1.getOrderNum().equals(o2.getOrderNum())) {
                return 0;
            }
            return -1;
        });
        int pNum = 1000;
        for (SysMenu menu : menuList) {
            if (StringUtils.isEmpty(menu.getParentId())) {
                SysMenu sysMenu = getChildren(menu, pNum, 0, menuList);
                jsonArr.add(sysMenu);
                pNum += 1000;
            }
        }
        return jsonArr;
    }

    public SysMenu getChildren(SysMenu menu, int pNum, int num, List<SysMenu> menuList) {
        for (SysMenu menus : menuList) {
            if (menu.getId().equals(menus.getParentId()) && menus.getMenuType() == 0) {
                ++num;
                SysMenu m = getChildren(menus, pNum, num, menuList);
                m.setNum(pNum + num);
                menu.addChild(m);
            }
        }
        return menu;

    }
}
