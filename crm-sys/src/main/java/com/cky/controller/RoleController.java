package com.cky.controller;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.controller.BaseController;
import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysRole;
import com.cky.service.SysMenuService;
import com.cky.service.SysRoleMenuService;
import com.cky.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 角色业务
 */
@Controller
@RequestMapping(value = "/role")
@Api(value = "用户角色管理", description = "角色业务处理")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private SysRoleMenuService roleMenuService;

    @GetMapping(value = "showRole")
    @RequiresPermissions(value = "role:show")
    public String showRole(Model model) {
        return "/system/role/roleList";
    }

    @ApiOperation(value = "/showRoleList", httpMethod = "GET", notes = "展示角色")
    @GetMapping(value = "showRoleList")
    @ResponseBody
    @RequiresPermissions("role:show")
    public ReType showRoleList(SysRole role, Model model, String page, String limit) {
        ReType reType = roleService.show(role, Integer.valueOf(page), Integer.valueOf(limit));
        return reType;
    }

    @ApiOperation(value = "/showaLLRoleList", httpMethod = "GET", notes = "展示角色")
    @GetMapping(value = "showaLLRoleList")
    @ResponseBody
    @RequiresPermissions("role:show")
    public String showRoleList(SysRole role, Model model) {
        return roleService.showAll(role);
    }


    @GetMapping(value = "showAddRole")
    public String goAddRole(Model model) {
        JSONArray jsonArray = menuService.getTreeUtil(null);
        model.addAttribute("menus", jsonArray.toJSONString());
        return "/system/role/add-role";
    }

    @ApiOperation(value = "/addRole", httpMethod = "POST", notes = "添加角色")
//    @Log(desc = "添加角色")
    @PostMapping(value = "addRole")
    @ResponseBody
    public ResJSONBean addRole(SysRole sysRole, String[] menus) {
        if (StringUtils.isEmpty(sysRole.getName())) {
            ResJSONBean.error("角色名称不能为空");
        }
        return roleService.addRole(sysRole, menus);
    }

    @GetMapping(value = "updateRole")
    public String updateRole(String id, Model model, boolean detail) {
        if (StringUtils.isNotEmpty(id)) {
            SysRole role = roleService.selectByPrimaryKey(id);
            model.addAttribute("role", role);
            JSONArray jsonArray = menuService.getTreeUtil(id);
            model.addAttribute("menus", jsonArray.toJSONString());
        }
        model.addAttribute("detail", detail);
        return "system/role/update-role";
    }

    @ApiOperation(value = "/updateRole", httpMethod = "POST", notes = "更新角色")
//    @Log(desc = "更新角色")
    @PostMapping(value = "updateRole")
    @ResponseBody
    public ResJSONBean updateUser(SysRole role, String[] menus) {
        if (role == null) {
            return ResJSONBean.error("获取数据失败");
        }
        return roleService.updateUser(role, menus);
    }

    @ApiOperation(value = "/del", httpMethod = "POST", notes = "删除角色")
//    @Log(desc = "删除角色", type = LOG_TYPE.DEL)
    @PostMapping(value = "del")
    @ResponseBody
    @RequiresPermissions("role:del")
    public ResJSONBean del(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResJSONBean.error("获取数据失败");
        }
        return roleService.del(id);
    }

}
