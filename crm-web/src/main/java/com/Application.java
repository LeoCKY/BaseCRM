package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Arrays;

@EnableWebMvc
@SpringBootApplication
@EnableTransactionManagement  // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@ComponentScan({"com.cky"})
@MapperScan(basePackages = {"com.cky.mapper"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        String[] names = applicationContext.getBeanDefinitionNames();
        //1.8 forEach循环
        Arrays.asList(names).forEach(System.out::println);
        System.out.println("Server start succ");
    }
}
