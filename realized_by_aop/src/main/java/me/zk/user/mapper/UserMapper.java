package me.zk.user.mapper;

import me.zk.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhangkai
 * @create 2021/1/6
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    @Insert({"INSERT into user(username,password) values (#{username},#{password})"})
    void insertUser(User user);
}
