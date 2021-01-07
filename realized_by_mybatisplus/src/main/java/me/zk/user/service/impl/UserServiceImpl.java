package me.zk.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import me.zk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhangkai
 * @create 2020/12/29
 */
@Service
@DS("master")
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void sys1() {
        System.out.println(jdbcTemplate);
    }
    @Override
    @DS("slave_1")
    public void sys2() {
        System.out.println(jdbcTemplate);
    }
}
