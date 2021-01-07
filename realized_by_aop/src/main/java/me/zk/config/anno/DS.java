package me.zk.config.anno;

import me.zk.config.datasource.DataSourceNameEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangkai
 * @create 2021/1/7
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface DS {
    DataSourceNameEnum dataSourceName() default DataSourceNameEnum.MASTER;

    boolean clear() default true;
}
