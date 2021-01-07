package me.zk.config.datasource;

import lombok.Data;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @author zhangkai
 * @create 2021/1/6
 */
@Data
public class DataSourceRouter extends AbstractRoutingDataSource {



    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.get();
    }
}
