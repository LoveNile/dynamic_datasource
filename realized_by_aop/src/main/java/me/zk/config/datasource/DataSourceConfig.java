package me.zk.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * 数据源配置类
 * @author zhangkai
 * @create 2021/1/5
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "masterProperties")
    @ConfigurationProperties(prefix = "druid.master")
    public DruidDataSourceProperties getMasterProperties() {
        return new DruidDataSourceProperties();
    }

    @Bean(name = "slaveProperties")
    @ConfigurationProperties(prefix = "druid.slave")
    public DruidDataSourceProperties getSlaveProperties() {
        return new DruidDataSourceProperties();
    }


    @Bean(name = "masterDataSource")
    public DataSource getMasterDataSource(@Qualifier("masterProperties") DruidDataSourceProperties properties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setDriverClassName(properties.getDriverClass());
        druidDataSource.setPassword(properties.getPassword());

        return druidDataSource;
    }

    @Bean(name = "slaveDataSource")
    public DataSource getSlaveDataSource(@Qualifier("slaveProperties") DruidDataSourceProperties properties){
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUsername(properties.getUsername());
        slaveDataSource.setUrl(properties.getUrl());
        slaveDataSource.setDriverClassName(properties.getDriverClass());
        slaveDataSource.setPassword(properties.getPassword());
        return slaveDataSource;
    }

    @Bean(name = "dataSourceRouter")
    public DataSourceRouter dataSourceRouter(@Qualifier("masterDataSource")DataSource masterDataSource,
                                             @Qualifier("slaveDataSource")DataSource slaveDataSource) {
        DataSourceRouter router = new DataSourceRouter();
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNameEnum.MASTER.getName(), masterDataSource);
        if (slaveDataSource != null) {
            targetDataSources.put(DataSourceNameEnum.SLAVE.getName(), slaveDataSource);
        }
        router.setTargetDataSources(targetDataSources);
        router.setDefaultTargetDataSource(masterDataSource);
        return router;
    }
}
