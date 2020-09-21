package com.cky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.res.ResJSONBean;
import com.cky.dao.SysMenuDAO;
import com.cky.entity.SysMenu;
import com.cky.entity.SysRoleMenu;
import com.cky.service.SysMenuService;
import com.cky.service.SysRoleMenuService;
import com.cky.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDAO sysMenuDAO;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

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
        List<SysMenu> sysMenus = sysMenuDAO.selectAll();
        List<SysMenu> supers = sysMenus.stream().filter(sysMenu ->
                StringUtils.isEmpty(sysMenu.getParentId()))
                .collect(Collectors.toList());
        sysMenus.removeAll(supers);
        supers.sort(Comparator.comparingInt(SysMenu::getOrderNum));
        JSONArray jsonArr = new JSONArray();
        for (SysMenu sysMenu : supers) {
            SysMenu child = child(sysMenu, sysMenus, 0, 0);
            jsonArr.add(child);
        }
        return jsonArr;
    }

    @Override
    public List<SysMenu> getMenuChildrenAll(String id) {
        return null;
    }

    @Override
    public JSONArray getTreeUtil(String roleId) {
        TreeUtil treeUtil = null;
        List<SysMenu> sysMenus = sysMenuDAO.selectAll();
        List<SysMenu> supers = sysMenus.stream().filter(sysMenu ->
                StringUtils.isEmpty(sysMenu.getParentId()))
                .collect(Collectors.toList());
        sysMenus.removeAll(supers);
        supers.sort(Comparator.comparingInt(SysMenu::getOrderNum));
        JSONArray jsonArr = new JSONArray();
        for (SysMenu sysMenu : supers) {
            treeUtil = getChildByTree(sysMenu, sysMenus, 0, null, roleId);
            jsonArr.add(treeUtil);
        }
        return jsonArr;

    }

    public TreeUtil getChildByTree(SysMenu sysMenu, List<SysMenu> sysMenus, int layer, String pId, String roleId) {
        layer++;
        List<SysMenu> childSysMenu = sysMenus.stream().filter(s ->
                s.getParentId().equals(sysMenu.getId())).collect(Collectors.toList());
        sysMenus.removeAll(childSysMenu);
        TreeUtil treeUtil = new TreeUtil();
        treeUtil.setId(sysMenu.getId());
        treeUtil.setName(sysMenu.getName());
        treeUtil.setLayer(layer);
        treeUtil.setPId(pId);
        /**判断是否存在*/
        if (!StringUtils.isEmpty(roleId)) {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(sysMenu.getId());
            sysRoleMenu.setRoleId(roleId);
            int count = sysRoleMenuService.selectCountByCondition(sysRoleMenu);
            if (count > 0)
                treeUtil.setChecked(true);
        }
        for (SysMenu menu : childSysMenu) {
            TreeUtil m = getChildByTree(menu, sysMenus, layer, menu.getId(), roleId);
            treeUtil.getChildren().add(m);
        }
        return treeUtil;
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

    public SysMenu child(SysMenu sysMenu, List<SysMenu> sysMenus, Integer pNum, Integer num) {
        List<SysMenu> childSysMenu = sysMenus.stream().filter(s ->
                s.getParentId().equals(sysMenu.getId())).collect(Collectors.toList());
        sysMenus.removeAll(childSysMenu);
        SysMenu m;
        for (SysMenu menu : childSysMenu) {
            ++num;
            m = child(menu, sysMenus, pNum, num);
            sysMenu.addChild(menu);
        }
        return sysMenu;
    }

    @Override
    public ResJSONBean del(String id) {
        ResJSONBean json = new ResJSONBean();
        json.setFlag(false);
        if (StringUtils.isEmpty(id)) {
            json.setMsg("获取数据失败,请刷新重试!");
            return json;
        }
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setMenuId(id);
        int count = sysRoleMenuService.selectCount(sysRoleMenu);
        //存在角色绑定不能删除
        if (count > 0) {
            json.setMsg("本菜单存在绑定角色,请先解除绑定!");
            return json;
        }
        //存在下级菜单 不能解除
        SysMenu sysMenu = new SysMenu();
        sysMenu.setParentId(id);
        if (sysMenuDAO.selectCount(sysMenu) > 0) {
            json.setMsg("存在子菜单,请先删除子菜单!");
            return json;
        }
        boolean isDel = sysMenuDAO.deleteByPrimaryKey(id) > 0;
        if (isDel) {
            json.setMsg("删除成功");
            json.setFlag(true);
        } else {
            json.setMsg("删除失败");
        }
        return json;
    }

    @Override
    public SysMenu selectByPrimaryKey(String id) {
        return sysMenuDAO.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateByPrimaryKeySelective(SysMenu sysMenu) {
        return sysMenuDAO.updateByPrimaryKeySelective(sysMenu);
    }


}
