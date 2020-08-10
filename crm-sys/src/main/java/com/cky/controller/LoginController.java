package com.cky.controller;

import com.cky.filter.VerifyCodeUtils;
import com.cky.model.system.entity.User;
import com.cky.service.UserService;
import com.cky.shiro.Principal;
import com.cky.util.CustomUsernamePasswordToken;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    private static final String CODE_ERROR = "code.error";

    @GetMapping(value = "")
    public String loginInit() {
        return loginCheck();
    }

    @GetMapping(value = "goLogin")
    public String goLogin(Model model) {
        Subject sub = SecurityUtils.getSubject();
        if (sub.isAuthenticated()) {
            return "/main/main";
        } else {
            model.addAttribute("message", "请重新登录");
            return "/login";
        }
    }

    @GetMapping(value = "/login")
    public String loginCheck() {
        Subject sub = SecurityUtils.getSubject();
        Boolean flag2 = sub.isRemembered();
        boolean flag = sub.isAuthenticated() || flag2;
        if (flag) {
            return "/main/main";
        }
        return "/login";
    }

    @GetMapping(value = "/getCode")
    public void getYzm(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            log.info("verifyCode:{}", verifyCode);
            //存入会话session
            HttpSession session = request.getSession(true);
            session.setAttribute("_code", verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录动作
     *
     * @param user
     * @param model
     * @param rememberMe
     * @return
     */
    @ApiOperation(value = "/login", httpMethod = "POST", notes = "登录 method")
    @PostMapping(value = "/login")
    public String login(User user, Model model, String rememberMe, HttpServletRequest request) {
        String codeMsg = (String) request.getAttribute("shiroLoginFailure");
        if (CODE_ERROR.equals(codeMsg)) {
            model.addAttribute("message", "验证码错误");
            return "/login";
        }
        CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(user.getAccount().trim(),
                user.getPassword(), "UserLogin");
        Subject subject = Principal.getSubject();
        String msg = null;
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                token.getUsername();
                return "redirect:/main";
            }
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            msg = "用户名/密码错误";
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败多次，账户锁定10分钟";
        }
        if (msg != null) {
            model.addAttribute("message", msg);
        }
        return "/login";
    }
}
