package com.cky;

import com.Application;
import com.cky.dao.SysMenuDAO;
import com.cky.dao.SysUserDAO;
import com.cky.entity.SysMenu;
import com.cky.entity.SysUser;
import com.cky.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BootTest {

    @Autowired
    SysUserDAO sysUserDAO;

    @Autowired
    SysMenuDAO sysMenuDAO;

    @Autowired
    RedisService redisService;

    private final static String ADMIN_ID = "acfc0e9232f54732a5d9ffe9071bf572";

    @Test
    public void testStartJob() throws Exception {
        //12
        SysUser user = sysUserDAO.selectByPrimaryKey(ADMIN_ID);
        System.out.println(user);
    }

    @Test
    public void testMenuByNotSuper() throws Exception {
        List<SysMenu> sysMenuList = sysMenuDAO.getMenuNotSuper();
        for (SysMenu menu : sysMenuList) {
            System.out.println(menu);
        }
    }


    @Test
    public void testMenu() throws Exception {
        List<SysMenu> sysMenuList = sysMenuDAO.getUserMenu(ADMIN_ID);
        for (SysMenu menu : sysMenuList) {
            System.out.println(menu);
        }
    }


    @Test
    public void redisTest() throws InterruptedException {
        redisService.set("str", "你好 世界", 10L);
        String str = redisService.get("str");
        System.out.println("value:" + str);
        Thread.sleep(1000 * 10L);
        System.out.println("value:" + redisService.get("str"));
    }

}