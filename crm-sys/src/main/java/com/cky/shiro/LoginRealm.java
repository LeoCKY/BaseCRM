package com.cky.shiro;


import com.cky.base.user.CurrentMenu;
import com.cky.base.user.CurrentRole;
import com.cky.base.user.CurrentUser;
import com.cky.entity.SysUser;
import com.cky.service.SysUserService;
import com.cky.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        CurrentUser user = (CurrentUser) principalCollection.getPrimaryPrincipal();
        Set<String> realmNames = principalCollection.getRealmNames();
        List<String> realmNameList = new ArrayList<>(realmNames);
        if ("BlogLogin".equals(realmNameList.get(0))) {
//            String[] roles = JWTUtil.getRoles(user.getUsername());
//            assert roles != null;
//            for (String role : roles) {
//                info.addRole(role);
//            }
        } else {
            //根据用户获取角色 根据角色获取所有按钮权限
            CurrentUser cUser = (CurrentUser) Principal.getSession().getAttribute("currentPrincipal");
            for (CurrentRole cRole : cUser.getCurrentRoleList()) {
                info.addRole(cRole.getId());
            }

            for (CurrentMenu cMenu : cUser.getCurrentMenuList()) {
                if (!StringUtils.isEmpty(cMenu.getPermission())) {
                    info.addStringPermission(cMenu.getPermission());
                }
            }
        }
        return info;
    }

    /**
     * 获取认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        SysUser s = null;
        try {
            s = sysUserService.login(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s == null) {
            throw new UnknownAccountException("The authentication information is incorrect");
        }
        CurrentUser user = new CurrentUser();
        BeanUtil.copyNotNullBean(s,user);
        sysUserService.setMenuAndRoles(username);
        ByteSource byteSource = ByteSource.Util.bytes(username + s.getSalt());
        return new SimpleAuthenticationInfo(user, s.getPassword(), byteSource, getName());
    }
}
