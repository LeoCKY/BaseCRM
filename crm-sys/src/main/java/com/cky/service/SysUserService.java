package com.cky.service;

import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysUser;
import com.cky.entity.SysUserInfo;
import com.cky.util.Checkbox;

import java.util.List;

public interface SysUserService {


    SysUser login(String username);


    SysUser selectByPrimaryKey(String id);

    SysUserInfo selectInfoByUID(String uid);

    /**
     * 分页查询
     *
     * @param
     * @return
     */
    List<SysUser> selectListByPage(SysUser sysUser);

    int count();

    /**
     * 新增
     *
     * @param user
     * @return
     */
    int add(SysUser user);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ResJSONBean delById(String id, boolean flag);

    int checkUser(String username);

    int updateByPrimaryKey(SysUser sysUser);


    List<Checkbox> getUserRoleByJson(String id);

    /**
     * 更新密码
     *
     * @param user
     * @return
     */
    int rePass(SysUser user);


    List<SysUser> getUserByRoleId(String roleId);

    public void setMenuAndRoles(String username);

    void updateCurrent(SysUser SysUser);

    ReType show(SysUser SysUser, int page, int limit);

    int updateByPrimaryKeySelective(SysUser SysUser);

    int insertSelective(SysUser sysUser);

    int updateUserInfoByPrimaryKeySelective(SysUserInfo info);
}
