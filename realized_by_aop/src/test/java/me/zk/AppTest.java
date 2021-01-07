package me.zk;

import me.zk.config.datasource.DruidDataSourceProperties;
import me.zk.user.entity.User;
import me.zk.user.mapper.UserMapper;
import me.zk.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhangkai
 * @create 2021/1/6
 */

@SpringBootTest(classes = DruidApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest {

    @Autowired
    @Qualifier("slaveProperties")
    private DruidDataSourceProperties slaveProperties;
    @Autowired
    @Qualifier("masterProperties")
    private DruidDataSourceProperties masterProperties;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;
    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private UserService userService2;
    @Test
    public void test(){
        System.out.println(masterProperties.getUrl());
        System.out.println(slaveProperties.getUrl());
    }

    @Test
    public void dataSourceTest() throws Exception{
        System.out.println(masterDataSource.getConnection());
        System.out.println(slaveDataSource.getConnection());
    }

    @Test
    public void getMapper() {
        System.out.println(userMapper.getUsers().size());
    }

    @Test
    public void testInsert() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final User user = new User();
//        userService.insertUser(user);
        if (userService == userService2) {
            System.out.println(true);
        }
        for (int i = 0; i< 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    userService.findAllSize();
                }

            },"线程" + i).start();
        }
        for (int i = 0; i< 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    userService2.insertUser(user);
                }

            }).start();
        }
        countDownLatch.countDown();
        System.in.read();
    }
}
