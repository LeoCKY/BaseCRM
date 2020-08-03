package com.cky.util;

import com.cky.base.user.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class CurrentUtil {

    public final static String CURRENT_PRINCIPAL = "currentPrincipal";

    /**
     * 獲取當前用戶
     */
    public static CurrentUser getUser() {
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (CurrentUser) session.getAttribute(CURRENT_PRINCIPAL);
    }
}

