package com.csfrez.datalimit.datascope;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Iterator;
import java.util.Map;

public class DataScopeSqlSource implements SqlSource {
    private BoundSql boundSql;

    public DataScopeSqlSource(BoundSqlConfig boundSqlConfig) {
        this.boundSql = boundSqlConfig.getBoundSql();
        Map<String, Object> additionalParameters = boundSqlConfig.additionalParameters();
        if (null != additionalParameters) {
            Iterator iterator = additionalParameters.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry var4 = (Map.Entry)iterator.next();
                this.boundSql.setAdditionalParameter((String)var4.getKey(), var4.getValue());
            }
        }
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return this.boundSql;
    }
}