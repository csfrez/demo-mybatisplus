package com.csfrez.datalimit.config;

import com.csfrez.datalimit.databind.IJsonBindStrategy;
import com.csfrez.datalimit.datascope.IDataScopeProvider;
import com.csfrez.datalimit.intercept.DatalimitIntercept;
import com.csfrez.datalimit.license.IGrantSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;


@Lazy
@Configuration(
        proxyBeanMethods = false
)
public class DatalimitConfig {

    @Autowired(
            required = false
    )
    private IDataScopeProvider dataScopeProvider;

    @Autowired(
            required = false
    )
    private IJsonBindStrategy jsonBindStrategy;

    @Autowired(
            required = false
    )
    private IGrantSupplier grantSupplier;

    @Order
    @Bean
    @ConditionalOnMissingBean
    public DatalimitIntercept datalimitIntercept(){
        return new DatalimitIntercept(this.dataScopeProvider);
    }
}
