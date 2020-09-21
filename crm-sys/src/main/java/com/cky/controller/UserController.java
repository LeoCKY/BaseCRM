package com.cky.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cky.base.controller.BaseController;
import com.cky.base.res.ReType;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysRoleUser;
import com.cky.entity.SysUser;
import com.cky.entity.SysUserInfo;
import com.cky.service.SysRoleUserService;
import com.cky.service.SysUserService;
import com.cky.util.BeanUtil;
import com.cky.util.Checkbox;
import com.cky.util.Md5Util;
import com.cky.util.UploadUtil;
import com.cky.vo.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户管理
 */
//@Api(value="user")
@Controller
@RequestMapping(value = "/user")
@Api(value = "用户管理",description="用户管理业务")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleUserService roleUserService;

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @GetMapping(value = "mainTest")
    @RequiresPermissions("user:show")
    public String showTest() {
        return "system/user/mainTest";
    }

    @GetMapping(value = "showUser")
    @RequiresPermissions("user:show")
    public String showUser(Model model) {
        return "/system/user/userList";
    }

    @GetMapping(value = "showUserList")
    @ResponseBody
    @RequiresPermissions("user:show")
    public ReType showUser(Model model, SysUser user, String page, String limit) {
        return userService.show(user, Integer.valueOf(page), Integer.valueOf(limit));
    }

    @ApiOperation(value = "/listByRoleId", httpMethod = "GET", notes = "展示角色")
    @GetMapping(value = "listByRoleId")
    @ResponseBody
    @RequiresPermissions("user:show")
    public String showUser(Model model, String roleId, int page, int limit) {
        JSONObject returnValue = new JSONObject();
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SysUser> users = userService.getUserByRoleId(roleId);
        returnValue.put("users", users);
        returnValue.put("totals", startPage.getTotal());
        return JSON.toJSONString(returnValue);
    }


    @GetMapping(value = "showAddUser")
    public String goAddUser(Model model) {
        List<Checkbox> checkboxList = userService.getUserRoleByJson(null);
        model.addAttribute("boxJson", checkboxList);
        return "/system/user/add-user";
    }

    @ApiOperation(value = "/addUser", httpMethod = "POST", notes = "添加用户")
//    @Log(desc = "添加用户")
    @PostMapping(value = "addUser")
    @ResponseBody
    public ResJSONBean addUser(UserVO userVO, String[] role) {
        if (userVO == null) {
            return ResJSONBean.error("获取数据失败");
        }

        if (StringUtils.isBlank(userVO.getFName()) || StringUtils.isBlank(userVO.getLName())) {
            return ResJSONBean.error("用户名不能为空");
        }

        if (StringUtils.isBlank(userVO.getPassword())) {
            return ResJSONBean.error("密码不能为空");
        }

        if (role == null) {
            return ResJSONBean.error("请选择角色");
        }

        int result = userService.checkUser(userVO.getAccount());
        if (result > 0) {
            return ResJSONBean.error("用户名已存在");
        }

        ResJSONBean j = new ResJSONBean();
        try {
            SysUser user = new SysUser();
            BeanUtil.copyNotNullBean(userVO, user);
            SysUserInfo info = new SysUserInfo();
            BeanUtil.copyNotNullBean(userVO, info);
            user.setUserInfo(info);
            userService.insertSelective(user);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(user.getId());
            for (String r : role) {
                sysRoleUser.setRoleId(r);
                roleUserService.insertSelective(sysRoleUser);
            }
            j.setMsg("保存成功");
        } catch (Exception e) {
            log.error("addUser error : {}", e.getMessage());
            j.setMsg("保存失败");
            j.setFlag(false);
        }
        return j;
    }

    @GetMapping(value = "updateUser")
    public String goUpdateUser(String id, Model model, boolean detail) {
        if (StringUtils.isNotEmpty(id)) {
            //用户-角色
            List<Checkbox> checkboxList = userService.getUserRoleByJson(id);
            SysUser example = new SysUser();
            example.setId(id);
            ReType reType =  userService.show(example, Integer.valueOf(0), Integer.valueOf(1));
            model.addAttribute("user", reType.getData().get(0));
            model.addAttribute("boxJson", checkboxList);
        }
        model.addAttribute("detail", detail);
        return "system/user/update-user";
    }

    @ApiOperation(value = "/updateUser", httpMethod = "POST", notes = "更新用户")
//    @Log(desc = "更新用户", type = LOG_TYPE.UPDATE)
    @PostMapping(value = "updateUser")
    @ResponseBody
    public ResJSONBean updateUser(UserVO userVo, String role[]) {
        ResJSONBean jsonUtil = new ResJSONBean();
        jsonUtil.setFlag(false);
        if (userVo == null) {
            jsonUtil.setMsg("获取数据失败");
            return jsonUtil;
        }
        try {
            SysUser oldUser = userService.selectByPrimaryKey(userVo.getUid());
            BeanUtil.copyNotNullBean(userVo, oldUser);
            userService.updateByPrimaryKeySelective(oldUser);
            SysUserInfo userInfo = userService.selectInfoByUID(userVo.getUid());
            BeanUtil.copyNotNullBean(userVo, userInfo);
            userService.updateUserInfoByPrimaryKeySelective(userInfo);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(oldUser.getId());
            List<SysRoleUser> keyList = sysRoleUserService.selectByCondition(sysRoleUser);
            for (SysRoleUser sysRoleUser1 : keyList) {
                roleUserService.deleteByPrimaryKey(sysRoleUser1);
            }
            if (role != null) {
                for (String r : role) {
                    sysRoleUser.setRoleId(r);
                    roleUserService.insertSelective(sysRoleUser);
                }
            }
            jsonUtil.setFlag(true);
            jsonUtil.setMsg("修改成功");
            userService.updateCurrent(oldUser);
        } catch (Exception e) {
            log.error("updateUser error : {} ", e.getMessage());
            e.printStackTrace();
        }
        return jsonUtil;
    }

//    @Log(desc = "删除用户", type = LOG_TYPE.DEL)
    @ApiOperation(value = "/del", httpMethod = "POST", notes = "删除用户")
    @PostMapping(value = "/del")
    @ResponseBody
    @RequiresPermissions("user:del")
    public ResJSONBean del(String id, boolean flag) {
        return userService.delById(id, flag);
    }

    @GetMapping(value = "goRePass")
    public String goRePass(String id, Model model) {
        if (StringUtils.isEmpty(id)) {
            return "获取账户信息失败";
        }
        SysUser user = userService.selectByPrimaryKey(id);
        model.addAttribute("user", user);
        return "/system/user/re-pass";
    }

    /**
     * 修改密码
     *
     * @param id
     * @param pass
     * @param newPwd
     * @return
     */
//    @Log(desc = "修改密码", type = LOG_TYPE.UPDATE)
    @PostMapping(value = "rePass")
    @ResponseBody
    @RequiresPermissions("user:repass")
    public ResJSONBean rePass(String id, String pass, String newPwd) {
        boolean flag = StringUtils.isEmpty(id) || StringUtils.isEmpty(pass) || StringUtils.isEmpty(newPwd);
        ResJSONBean j = new ResJSONBean();
        j.setFlag(false);
        if (flag) {
            j.setMsg("获取数据失败，修改失败");
            return j;
        }
        SysUser user = userService.selectByPrimaryKey(id);
        newPwd = Md5Util.getMD5(user.getPassword().trim(), user.getAccount() + user.getSalt());
        pass = Md5Util.getMD5(user.getPassword().trim(), user.getAccount() + user.getSalt());
        if (!pass.equals(user.getPassword())) {
            j.setMsg("密码不正确");
            return j;
        }
        if (newPwd.equals(user.getPassword())) {
            j.setMsg("新密码不能与旧密码相同");

            return j;
        }
        user.setPassword(newPwd);
        try {
            userService.rePass(user);
            j.setMsg("修改成功");
            j.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    @Autowired
    UploadUtil uploadUtil;

    /**
     * 头像上传 目前首先相对路径
     */
    @PostMapping(value = "upload")
    @ResponseBody
    public ResJSONBean imgUpload(HttpServletRequest req, @RequestParam("file") MultipartFile file,
                              ModelMap model) {
        String fileName = uploadUtil.upload(file);
        ResJSONBean j = new ResJSONBean();
        j.setMsg(fileName);
        return j;
    }

    /**
     * 验证用户名是否存在
     */
    @GetMapping(value = "checkUser")
    @ResponseBody
    public ResJSONBean checkUser(String uname, HttpServletRequest req) {
        ResJSONBean j = new ResJSONBean();
        j.setFlag(Boolean.FALSE);
        if (StringUtils.isEmpty(uname)) {
            j.setMsg("获取数据失败");
            return j;
        }
        int result = userService.checkUser(uname);
        if (result > 0) {
            j.setMsg("用户名已存在");
            return j;
        }
        j.setFlag(true);
        return j;
    }


}
