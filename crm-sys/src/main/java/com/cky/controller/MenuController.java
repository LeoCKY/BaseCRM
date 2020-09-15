package com.cky.controller;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.controller.BaseController;
import com.cky.entity.SysMenu;
import com.cky.base.res.ResJSONBean;
import com.cky.service.SysMenuService;
import com.cky.util.BeanUtil;
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
 * 菜单
 */
@RequestMapping("/menu")
@Controller
@Api(value = "菜单管理",description="菜单业务处理")
public class MenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 展示 tree
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "/showMenu", httpMethod = "GET", notes = "展示菜单")
    @GetMapping(value = "showMenu")
    @RequiresPermissions("menu:show")
    public String showMenu(Model model) {
        JSONArray ja = sysMenuService.getMenuJsonList();
        model.addAttribute("menus", ja.toJSONString());
        return "/system/menu/menuList";
    }

    @GetMapping(value = "showAddMenu")
    public String addMenu(Model model) {
        JSONArray ja = sysMenuService.getMenuJsonList();
        model.addAttribute("menus", ja.toJSONString());
        return "/system/menu/add-menu";
    }

//    @Log(desc = "添加菜单", type = LOG_TYPE.UPDATE)
    @ApiOperation(value = "/addMenu", httpMethod = "POST", notes = "添加菜单")
    @PostMapping(value = "addMenu")
    @ResponseBody
    public ResJSONBean addMenu(SysMenu sysMenu, Model model) {
        ResJSONBean jsonUtil = new ResJSONBean();
        jsonUtil.setFlag(false);
        if (sysMenu == null) {
            jsonUtil.setMsg("获取数据失败");
            return jsonUtil;
        }
        if (StringUtils.isEmpty(sysMenu.getParentId())) {
            sysMenu.setParentId(null);
        }
        if (StringUtils.isEmpty(sysMenu.getUrl())) {
            sysMenu.setUrl(null);
        }
        if (StringUtils.isEmpty(sysMenu.getPermission())) {
            sysMenu.setPermission(null);
        }

        try {
            if (sysMenu.getMenuType() == 2) {
                sysMenu.setMenuType((byte) 0);
            }
//            sysMenuService.insertSelective(sysMenu);
            jsonUtil.setMsg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonUtil.setMsg("添加失败");
        }
        return jsonUtil;
    }

    @GetMapping(value = "showUpdateMenu")
    public String showUpdateMenu(Model model, String id) {
        SysMenu sysMenu = sysMenuService.selectByPrimaryKey(id);
        JSONArray ja = sysMenuService.getMenuJsonList();
        model.addAttribute("menus", ja.toJSONString());
        model.addAttribute("sysMenu", sysMenu);
        if (null != sysMenu.getParentId()) {
            SysMenu pSysMenu = sysMenuService.selectByPrimaryKey(sysMenu.getParentId());
            model.addAttribute("pName", pSysMenu.getName());
        }
        return "/system/menu/update-menu";
    }


//    @Log(desc = "更新菜单", type = LOG_TYPE.ADD)
    @PostMapping(value = "updateMenu")
    @ResponseBody
    public ResJSONBean updateMenu(SysMenu sysMenu) {
        SysMenu oldMenu = sysMenuService.selectByPrimaryKey(sysMenu.getId());
        BeanUtil.copyNotNullBean(sysMenu, oldMenu);
        sysMenuService.updateByPrimaryKeySelective(oldMenu);
        return ResJSONBean.Success("保存成功");
    }

//    @Log(desc = "删除菜单", type = LOG_TYPE.DEL)
    @PostMapping("/menu-del")
    @ResponseBody
    public ResJSONBean del(String id) {
        return sysMenuService.del(id);
    }

}
