package com.csfrez.datalimit.datascope;

import com.csfrez.datalimit.annotation.JsonBind;
import com.csfrez.datalimit.databind.IJsonBindStrategy;
import com.fasterxml.jackson.core.JsonGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class O0000ooO {
    public O0000ooO() {
    }

    public static boolean O000000o(SqlCommandType var0) {
        return SqlCommandType.UPDATE == var0 || SqlCommandType.INSERT == var0;
    }

    public static void convertSql(Invocation var0, Function<String, String> var1) {
        Object[] var2 = var0.getArgs();
        MappedStatement var3 = (MappedStatement)var2[0];
        BoundSql var4 = var3.getBoundSql(var2[1]);
        var2[0] = O000000o(var3, new BoundSqlConfig(var3.getConfiguration(), (String)var1.apply(var4.getSql()), var4));
    }

    public static MappedStatement O000000o(MappedStatement var0, BoundSqlConfig var1) {
        return O000000o(var0, (SqlSource)(new O000000o(var1)));
    }

    private static MappedStatement O000000o(MappedStatement var0, SqlSource var1) {
        MappedStatement.Builder var2 = new MappedStatement.Builder(var0.getConfiguration(), var0.getId(), var1, var0.getSqlCommandType());
        var2.resource(var0.getResource());
        var2.parameterMap(var0.getParameterMap());
        var2.resultMaps(var0.getResultMaps());
        var2.fetchSize(var0.getFetchSize());
        var2.timeout(var0.getTimeout());
        var2.statementType(var0.getStatementType());
        var2.resultSetType(var0.getResultSetType());
        var2.cache(var0.getCache());
        var2.flushCacheRequired(var0.isFlushCacheRequired());
        var2.useCache(var0.isUseCache());
        var2.resultOrdered(var0.isResultOrdered());
        var2.keyGenerator(var0.getKeyGenerator());
        if (null != var0.getKeyProperties() && var0.getKeyProperties().length > 0) {
            var2.keyProperty((String) Arrays.stream(var0.getKeyProperties()).collect(Collectors.joining(",")));
        }

        if (null != var0.getKeyColumns() && var0.getKeyColumns().length > 0) {
            var2.keyColumn((String)Arrays.stream(var0.getKeyColumns()).collect(Collectors.joining(",")));
        }

        var2.databaseId(var0.getDatabaseId());
        var2.lang(var0.getLang());
        if (null != var0.getResultSets() && var0.getResultSets().length > 0) {
            var2.resultSets((String)Arrays.stream(var0.getResultSets()).collect(Collectors.joining(",")));
        }

        return var2.build();
    }

    public static void O000000o(Object var0, JsonGenerator var1, IJsonBindStrategy var2) throws IOException {
        var1.writeStartObject();
        JsonBind var3 = (JsonBind) AnnotationUtils.findAnnotation(var0.getClass(), JsonBind.class);
        if (null != var3) {
            try {
                O0000oo.O000000o(var0, var1, var2, var3);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        var1.writeEndObject();
    }
}