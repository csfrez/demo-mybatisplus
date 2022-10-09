package com.csfrez.datalimit.datascope;

import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.csfrez.datalimit.annotation.DataScope;
import com.csfrez.datalimit.util.StringUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DataScopeUtil {

    private static final Logger log = LoggerFactory.getLogger(DataScopeUtil.class);
    private static final DataScopeProperty dataScopeProperty = new DataScopeProperty();
    private static Map<String, DataScopeProperty> dataScopePropertyMap = new ConcurrentHashMap(16);

    public static DataScopeProperty getDataScopeProperty(String id) throws Exception {
        DataScopeProperty dsp = dataScopePropertyMap.get(id);
        if (null == dsp) {
            String classPath = StringUtils.getClassPath(id);
            if (null == classPath) {
                return null;
            }

            dsp = dataScopePropertyMap.get(classPath);
            if (dataScopeProperty == dsp) {
                return null;
            }

            if (null == dsp) {
                Class<?> clazz = Class.forName(classPath);
                Method[] methods = clazz.getMethods();
                int length = methods.length;

                for(int index = 0; index < length; index++) {
                    Method method = methods[index];
                    DataScope ds = method.getAnnotation(DataScope.class);
                    if (null != ds) {
                        dataScopePropertyMap.put(classPath + "." + method.getName(), new DataScopeProperty(ds));
                    }
                }

                DataScope dataScope = clazz.getAnnotation(DataScope.class);
                dataScopePropertyMap.put(classPath, null == dataScope ? dataScopeProperty : new DataScopeProperty(dataScope));
                dsp = dataScopePropertyMap.get(id);
                if (null == dsp) {
                    dsp = dataScopePropertyMap.get(classPath);
                }
            }
        }
        return dsp;
    }

    public static void processStatements(Object[] args, MappedStatement mappedStatement, ProcessFunction processFunction) {
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();
        if (log.isDebugEnabled()) {
            log.debug("original SQL: {}", sql);
        }

        try {
            StringBuffer stringBuffer = new StringBuffer();
            Statements statements = CCJSqlParserUtil.parseStatements(sql);
            int index = 0;

            for(Iterator iterator = statements.getStatements().iterator(); iterator.hasNext(); index++) {
                Statement statement = (Statement)iterator.next();
                if (index > 0) {
                    stringBuffer.append(";");
                }
                processFunction.process(statement, index);
                stringBuffer.append(statement);
            }

            args[0] = getMappedStatement(mappedStatement, new BoundSqlConfig(mappedStatement.getConfiguration(), stringBuffer.toString(), boundSql));
            if (log.isDebugEnabled()) {
                log.debug("dataScope execute SQL: {}", stringBuffer);
            }

        } catch (JSQLParserException jsqlParserException) {
            throw ExceptionUtils.mpe("Failed to process, Error SQL: %s", jsqlParserException.getCause(), new Object[]{sql});
        }
    }


    public static MappedStatement getMappedStatement(MappedStatement mappedStatement, BoundSqlConfig boundSqlConfig) {
        return getMappedStatement(mappedStatement, new DataScopeSqlSource(boundSqlConfig));
    }

    private static MappedStatement getMappedStatement(MappedStatement mappedStatement, SqlSource sqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), sqlSource, mappedStatement.getSqlCommandType());
        builder.resource(mappedStatement.getResource());
        builder.parameterMap(mappedStatement.getParameterMap());
        builder.resultMaps(mappedStatement.getResultMaps());
        builder.fetchSize(mappedStatement.getFetchSize());
        builder.timeout(mappedStatement.getTimeout());
        builder.statementType(mappedStatement.getStatementType());
        builder.resultSetType(mappedStatement.getResultSetType());
        builder.cache(mappedStatement.getCache());
        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
        builder.useCache(mappedStatement.isUseCache());
        builder.resultOrdered(mappedStatement.isResultOrdered());
        builder.keyGenerator(mappedStatement.getKeyGenerator());
        if (null != mappedStatement.getKeyProperties() && mappedStatement.getKeyProperties().length > 0) {
            builder.keyProperty((String) Arrays.stream(mappedStatement.getKeyProperties()).collect(Collectors.joining(",")));
        }

        if (null != mappedStatement.getKeyColumns() && mappedStatement.getKeyColumns().length > 0) {
            builder.keyColumn((String)Arrays.stream(mappedStatement.getKeyColumns()).collect(Collectors.joining(",")));
        }

        builder.databaseId(mappedStatement.getDatabaseId());
        builder.lang(mappedStatement.getLang());
        if (null != mappedStatement.getResultSets() && mappedStatement.getResultSets().length > 0) {
            builder.resultSets((String)Arrays.stream(mappedStatement.getResultSets()).collect(Collectors.joining(",")));
        }

        return builder.build();
    }
}