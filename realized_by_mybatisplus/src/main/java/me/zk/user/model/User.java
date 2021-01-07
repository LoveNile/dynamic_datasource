package me.zk.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhangkai
 * @create 2020/12/29
 */
@TableName("user")
@Data
public class User {
    private int id;
    private String username;
    private String password;
}
