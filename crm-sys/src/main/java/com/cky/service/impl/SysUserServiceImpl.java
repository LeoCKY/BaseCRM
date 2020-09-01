package com.cky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.user.CurrentMenu;
import com.cky.base.user.CurrentRole;
import com.cky.base.user.CurrentUser;
import com.cky.dao.SysUserDAO;
import com.cky.entity.SysMenu;
import com.cky.entity.SysRole;
import com.cky.entity.SysRoleUser;
import com.cky.entity.SysUser;
import com.cky.service.SysMenuService;
import com.cky.service.SysUserService;
import com.cky.shiro.Principal;
import com.cky.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDAO userDAO;

    @Autowired
    private SysMenuService menuService;

    @Override
    public SysUser login(String account) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("account", account);
        SysUser SysUser = userDAO.selectOneByExample(example);
        return SysUser;
    }

    @Override
    public SysUser selectByPrimaryKey(String id) {
        return userDAO.selectByPrimaryKey(id);
    }

    @Override
    public int add(SysUser SysUser) {
        return 0;
    }

    @Override
    public int checkUser(String username) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SysUser sysUser) {
        return 0;
    }

    @Override
    public List<SysRoleUser> selectByCondition(SysRoleUser sysRoleUser) {
        return null;
    }

    @Override
    public int rePass(SysUser SysUser) {
        return 0;
    }

    @Override
    public List<SysUser> getUserByRoleId(String roleId) {
        return null;
    }

    @Override
    public void setMenuAndRoles(String account) {
        SysUser s = new SysUser();
        s.setAccount(account);
        s = userDAO.selectOne(s);
        CurrentUser currentUser = new CurrentUser(s.getId(), s.getAccount(),s.getEmail(), "", "","");
        Subject subject = Principal.getSubject();
        /*角色权限封装进去*/
        //根据用户获取菜单
        Session session = subject.getSession();

        List<SysMenu> menuList = menuService.getUserMenu(s.getId());
        JSONArray json = menuService.getMenuJsonByUser(menuList);
        session.setAttribute("menu", json);

        List<CurrentMenu> currentMenuList = new ArrayList<>();
        Set<SysRole> roleList = new HashSet<>();
        for (SysMenu m : menuList) {
            CurrentMenu currentMenu = new CurrentMenu();
            BeanUtil.copyNotNullBean(m, currentMenu);
            currentMenuList.add(currentMenu);
            roleList.addAll(m.getRoleList());
        }
        List<CurrentRole> currentRoleList = new ArrayList<>();

        for (SysRole r : roleList) {
            CurrentRole role = new CurrentRole();
            BeanUtil.copyNotNullBean(r, role);
            currentRoleList.add(role);
        }
        currentUser.setCurrentRoleList(currentRoleList);
        currentUser.setCurrentMenuList(currentMenuList);
        session.setAttribute("currentPrincipal", currentUser);
    }

    @Override
    public void updateCurrent(SysUser SysUser) {

    }
}
