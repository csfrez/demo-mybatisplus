package com.csfrez.datalimit.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.csfrez.datalimit.datascope.IDataScopeProvider;
import com.csfrez.datalimit.intercept.DatalimitIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;


@Lazy
@Configuration(proxyBeanMethods = false)
public class DatalimitConfig {

    @Autowired(required = false)
    private IDataScopeProvider dataScopeProvider;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // DbType.H2 不是必须指定的，指定性能会更好无需自动匹配多数据源例外。
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    @Order
    @Bean
    @ConditionalOnMissingBean
    public DatalimitIntercept datalimitIntercept(){
        return new DatalimitIntercept(this.dataScopeProvider);
    }


}
