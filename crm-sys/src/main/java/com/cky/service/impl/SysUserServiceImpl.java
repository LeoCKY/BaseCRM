package com.cky.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.base.user.CurrentMenu;
import com.cky.base.user.CurrentRole;
import com.cky.base.user.CurrentUser;
import com.cky.dao.SysUserDAO;
import com.cky.dao.SysUserInfoDAO;
import com.cky.entity.*;
import com.cky.service.SysMenuService;
import com.cky.service.SysRoleService;
import com.cky.service.SysRoleUserService;
import com.cky.service.SysUserService;
import com.cky.shiro.Principal;
import com.cky.util.BeanUtil;
import com.cky.util.Checkbox;
import com.cky.util.CurrentUtil;
import com.cky.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
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
    private SysUserInfoDAO userInfoDAO;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    private static final String ADMIN = "admin";

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
        return userDAO.insertSelective(SysUser);
    }

    @Override
    public int checkUser(String account) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("account", account);
        int count = userDAO.selectCountByExample(example);
        return count;
    }

    @Override
    public int updateByPrimaryKey(SysUser sysUser) {
        return 0;
    }

    @Override
    public int rePass(SysUser sysUser) {
        return userDAO.updateByPrimaryKeySelective(sysUser);
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
        CurrentUser currentUser = new CurrentUser(s.getId(), s.getAccount(), s.getEmail(), "", "", "");
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
    public void updateCurrent(SysUser sysUser) {
        CurrentUser principal = Principal.getPrincipal();
        if (principal.getId().equals(sysUser.getId())) {
            //当前用户
            CurrentUser currentUse = Principal.getCurrentUse();
            Session session = Principal.getSession();
//            currentUse.setPhoto(sysUser.getPhoto());
            session.setAttribute(CurrentUtil.CURRENT_PRINCIPAL, currentUse);

        }
    }

    @Override
    public List<SysUser> selectListByPage(SysUser sysUser) {
        return userDAO.selectListByPage(sysUser);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public ResJSONBean delById(String id, boolean flag) {
        if (StringUtils.isEmpty(id)) {
            return ResJSONBean.error("获取数据失败");
        }
        ResJSONBean j = new ResJSONBean();
        try {
            SysUser sysUser = selectByPrimaryKey(id);
            if (ADMIN.equals(sysUser.getAccount())) {
                return ResJSONBean.error("超管无法删除");
            }
            SysRoleUser roleUser = new SysRoleUser();
            roleUser.setUserId(id);
            int count = sysRoleUserService.selectCountByCondition(roleUser);
            if (count > 0) {
                return ResJSONBean.error("账户已经绑定角色，无法删除");
            }
            if (flag) {
                //逻辑
                sysUser.setIsDel(true);
                updateByPrimaryKeySelective(sysUser);
            } else {
                //物理
                SysUserInfo info = new SysUserInfo();
                info.setUserId(id);
                userInfoDAO.delete(info);
                userDAO.deleteByPrimaryKey(id);
            }
            j.setMsg("删除成功");
        } catch (Exception e) {
            j.setMsg("删除失败");
            j.setFlag(false);
            e.printStackTrace();
        }
        return j;
    }

    @Override
    public List<Checkbox> getUserRoleByJson(String id) {
        List<SysRole> roleList = roleService.selectListByPage(new SysRole());
        SysRoleUser sysRoleUser = new SysRoleUser();
        sysRoleUser.setUserId(id);
        List<SysRoleUser> kList = sysRoleUserService.selectByCondition(sysRoleUser);

        List<Checkbox> checkboxList = new ArrayList<>();
        Checkbox checkbox;
        for (SysRole sysRole : roleList) {
            checkbox = new Checkbox();
            checkbox.setId(sysRole.getId());
            checkbox.setName(sysRole.getName());
            for (SysRoleUser sysRoleUser1 : kList) {
                if (sysRoleUser1.getRoleId().equals(sysRole.getId())) {
                    checkbox.setCheck(true);
                    break;
                }
            }
            checkboxList.add(checkbox);
        }
        return checkboxList;
    }

    @Override
    public ReType show(SysUser SysUser, int page, int limit) {
        return userDAO.show(SysUser, page, limit);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser sysUser) {
        userDAO.updateByPrimaryKeySelective(sysUser);
        SysUserInfo info = sysUser.getUserInfo();
        if (info != null) {
            userInfoDAO.updateByPrimaryKeySelective(info);
        }
        return 0;
    }

    @Override
    public int insertSelective(SysUser sysUser) {

        String salt = RandomUtil.randomString(64);
        String pwd = Md5Util.getMD5(sysUser.getPassword().trim(), sysUser.getAccount() + salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(pwd);

        userDAO.insertSelective(sysUser);
        SysUserInfo info = sysUser.getUserInfo();
        if (info != null) {
            info.setUserId(sysUser.getId());
            userInfoDAO.insertSelective(info);
        }
        return 1;
    }
}
