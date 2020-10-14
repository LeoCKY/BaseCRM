package com.cky.controller;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.controller.BaseController;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysMenu;
import com.cky.entity.SysOrganization;
import com.cky.service.SysOrganizationService;
import com.cky.util.BeanUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/org")
@Slf4j
public class OrganizationController extends BaseController {

    @Autowired
    private SysOrganizationService organizationService;


    @GetMapping(value = "showOrg")
    @RequiresPermissions("org:show")
    public String toPage(Model model) {
        JSONArray ja = organizationService.getOrgJsonList();
        model.addAttribute("orgTree", ja.toJSONString());
        return "/system/org/orgList";
    }

    @GetMapping(value = "showAddOrg")
//    @RequiresPermissions("org:add")
    public String showAddOrg(Model model) {
        JSONArray ja = organizationService.getOrgJsonList();
        model.addAttribute("orgs", ja.toJSONString());
        return "/system/org/add-org";
    }


    @ApiOperation(value = "/addOrg", httpMethod = "POST", notes = "添加組織/部門")
    @PostMapping(value = "addOrg")
    @ResponseBody
    public ResJSONBean addOrg(SysOrganization org) {
        ResJSONBean res = new ResJSONBean();
        res.setFlag(false);
        try {
            if (StringUtils.isEmpty(org.getParentId())) {
                org.setParentId(null);
            }
            organizationService.add(org);
            res.setFlag(true);
            res.setMsg("Insert Success.");
        } catch (Exception e) {
            log.error("addOrg error : {} ", e);
            res.setMsg("System Error.");
        }
        return res;
    }

    @GetMapping(value = "showUpdateOrg")
    public String showUpdateOrg(Model model, String id) {
        SysOrganization sysOrg = organizationService.selectByPrimaryKey(id);
        JSONArray ja = organizationService.getOrgJsonList();
        model.addAttribute("orgs", ja.toJSONString());
        model.addAttribute("sysOrg", sysOrg);
        System.out.println("sysOrg : " + sysOrg);
        if (null != sysOrg.getParentId()) {
            SysOrganization pOrg = organizationService.selectByPrimaryKey(sysOrg.getParentId());
            model.addAttribute("pName", pOrg.getName());
        }
        return "/system/org/update-org";
    }


    //    @Log(desc = "更新菜單", type = LOG_TYPE.ADD)
    @PostMapping(value = "updateOrg")
    @ResponseBody
    public ResJSONBean updateOrg(SysOrganization sysOrg) {
        SysOrganization oldOrg = organizationService.selectByPrimaryKey(sysOrg.getId());
        BeanUtil.copyNotNullBean(sysOrg, oldOrg);
        organizationService.updateById(sysOrg);
        return ResJSONBean.Success("保存成功");
    }

    //    @Log(desc = "刪除菜單", type = LOG_TYPE.DEL)
    @PostMapping("/menu-del")
    @ResponseBody
    public ResJSONBean del(String id) {
        return organizationService.delById(id);
    }
}
