package me.zk.user.service;

import me.zk.user.entity.User;

/**
 * @author zhangkai
 * @create 2021/1/6
 */
public interface UserService {

    void insertUser(User user);

    int findAllSize();
}
