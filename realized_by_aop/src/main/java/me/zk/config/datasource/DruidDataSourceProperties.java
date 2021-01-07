package me.zk.config.datasource;

import lombok.Data;

/**
 * @author zhangkai
 * @create 2021/1/6
 */
@Data
public class DruidDataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClass;

}
