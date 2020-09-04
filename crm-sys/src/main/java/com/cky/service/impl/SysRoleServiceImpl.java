package com.cky.service.impl;

import com.alibaba.fastjson.JSON;
import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.dao.SysRoleDAO;
import com.cky.entity.SysRole;
import com.cky.entity.SysRoleMenu;
import com.cky.service.SysRoleMenuService;
import com.cky.service.SysRoleService;
import com.cky.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDAO sysRoleDAO;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public SysRole selectByPrimaryKey(String id) {
        return sysRoleDAO.selectByPrimaryKey(id);
    }

    @Override
    public ResJSONBean addRole(SysRole sysRole, String[] menus) {
        ResJSONBean res = new ResJSONBean();
        try {
            sysRoleDAO.insertSelective(sysRole);
            //操作role-menu data
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(sysRole.getId());

            if (menus != null) {
                for (String menu : menus) {
                    sysRoleMenu.setMenuId(menu);
                    sysRoleMenuService.insert(sysRoleMenu);
                }
            }

        } catch (Exception ex) {
            res.setMsg("保存失败");
            res.setFlag(false);
            log.error("SysRoleService error : {} ", ex.getMessage());
        }


        return res;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysRoleDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole record) {
        return 0;
    }

    @Override
    public int insertSelective(SysRole record) {
        return sysRoleDAO.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return 0;
    }

    @Override
    public List<SysRole> selectListByPage(SysRole sysRole) {
        return sysRoleDAO.selectListByPage(sysRole);
    }

    @Override
    public ResJSONBean updateUser(SysRole role, String[] menus) {
        ResJSONBean res = new ResJSONBean();
        res.setFlag(false);
        try {
            SysRole oldRole = selectByPrimaryKey(role.getId());
            BeanUtil.copyNotNullBean(role, oldRole);
            updateByPrimaryKeySelective(oldRole);

            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getId());
            List<SysRoleMenu> menuList = sysRoleMenuService.selectByCondition(sysRoleMenu);
            for (SysRoleMenu sysRoleMenu1 : menuList) {
                sysRoleMenuService.deleteByPrimaryKey(sysRoleMenu1);
            }
            if (menus != null)
                for (String menu : menus) {
                    sysRoleMenu.setMenuId(menu);
                    sysRoleMenuService.insert(sysRoleMenu);
                }
            res.setFlag(true);
            res.setMsg("修改成功");
        } catch (Exception e) {
            res.setMsg("修改失败");
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public ResJSONBean del(String id) {
        return null;
    }

    @Override
    public ReType show(SysRole role, int page, int limit) {
        return sysRoleDAO.show(role, page, limit);
    }

    @Override
    public String showAll(SysRole role) {
        List<SysRole> roles = sysRoleDAO.selectListByPage(role);
        return JSON.toJSONString(roles);
    }
}
