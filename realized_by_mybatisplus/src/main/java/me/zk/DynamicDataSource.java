package me.zk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangkai
 * @create 2020/12/29
 */

@SpringBootApplication
@MapperScan("me.zk.**.mapper")
public class DynamicDataSource {

}
