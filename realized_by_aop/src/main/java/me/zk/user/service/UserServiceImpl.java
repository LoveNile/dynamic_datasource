package me.zk.user.service;

import me.zk.config.anno.DS;
import me.zk.config.datasource.DataSourceNameEnum;
import me.zk.user.entity.User;
import me.zk.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhangkai
 * @create 2021/1/6
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @DS(dataSourceName = DataSourceNameEnum.MASTER)
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    @DS(dataSourceName = DataSourceNameEnum.SLAVE)
    public int findAllSize() {
        return userMapper.getUsers().size();
    }


}
