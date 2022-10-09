package com.csfrez.datalimit.intercept;

import com.csfrez.datalimit.datascope.IDataScopeProvider;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
), @Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
)})
public class DatalimitIntercept implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(DatalimitIntercept.class);

    private IDataScopeProvider dataScopeProvider;

    public DatalimitIntercept(IDataScopeProvider dataScopeProvider){
        this.dataScopeProvider = dataScopeProvider;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)args[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (sqlCommandType != SqlCommandType.UNKNOWN && sqlCommandType != SqlCommandType.FLUSH) {
            if (null != dataScopeProvider) {
                dataScopeProvider.sqlRender(args, mappedStatement, sqlCommandType);
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
