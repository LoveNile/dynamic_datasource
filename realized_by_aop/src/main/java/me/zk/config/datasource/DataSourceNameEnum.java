package me.zk.config.datasource;

/**
 * @author zhangkai
 * @create 2021/1/7
 */
public enum DataSourceNameEnum {
    MASTER("master"),
    SLAVE("slave");

    private String name;

    private DataSourceNameEnum(String name) {
        this.name  = name;
    }

    public String getName() {
        return name;
    }

}
