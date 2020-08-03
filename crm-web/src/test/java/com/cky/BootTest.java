package com.cky;


import com.Application;
import com.cky.dao.UserDAO;
import com.cky.model.system.entity.User;
import com.cky.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BootTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RedisService redisService;

    @Test
    public void testStartJob() throws Exception {
        //12
        User user = userDAO.selectByPrimaryKey("acfc0e9232f54732a5d9ffe9071bf572");
        System.out.println(user);
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