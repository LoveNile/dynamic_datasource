package me.zk.config.datasource;

/**
 *
 * 数据
 * @author zhangkai
 * @create 2021/1/7
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void set(String dataSource) {
        context.set(dataSource);
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }

}
