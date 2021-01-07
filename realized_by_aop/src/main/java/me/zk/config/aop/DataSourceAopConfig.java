package me.zk.config.aop;

import me.zk.config.anno.DS;
import me.zk.config.datasource.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhangkai
 * @create 2021/1/7
 */

@Aspect
@Component
public class DataSourceAopConfig {

    @Pointcut("@annotation(me.zk.config.anno.DS)")
    public void dataSourcePointcut(){}

    @Around(value = "dataSourcePointcut()")
    public Object changeDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clear = false;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            DS ds = method.getAnnotation(DS.class);
            clear = ds.clear();
            System.out.println(Thread.currentThread() + ",数据切换为：" + ds.dataSourceName().getName());
            DataSourceContextHolder.set(ds.dataSourceName().getName());
            return joinPoint.proceed();
        } finally {
            if (clear) {
                DataSourceContextHolder.clear();
            }
        }
    }
}
