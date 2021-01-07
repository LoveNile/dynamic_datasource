package me.zk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhangkai
 * @create 2021/1/5
 */

@SpringBootApplication
@EnableAspectJAutoProxy
public class DruidApplication {
    public static void main(String[] args) {
        SpringApplication.run(DruidApplication.class,args);
    }
}
