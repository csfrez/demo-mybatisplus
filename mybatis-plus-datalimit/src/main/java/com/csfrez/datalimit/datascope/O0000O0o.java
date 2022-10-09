package com.csfrez.datalimit.datascope;

import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.csfrez.datalimit.annotation.DataScope;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class O0000O0o {
    private static final Logger log = LoggerFactory.getLogger(O0000O0o.class);
    public static final DataScopeProperty O000O0oO = new DataScopeProperty();
    private static Map<String, DataScopeProperty> O000O0oo = new ConcurrentHashMap(16);

    public O0000O0o() {
    }

    public static void O0000oo0() {
        O000O0oo = new ConcurrentHashMap(16);
    }

    public static DataScopeProperty O0000oOO(String var0) throws Exception {
        DataScopeProperty var1 = (DataScopeProperty)O000O0oo.get(var0);
        if (null == var1) {
            String var2 = O0000oo.O000O0OO(var0);
            if (null == var2) {
                return null;
            }

            var1 = (DataScopeProperty)O000O0oo.get(var2);
            if (O000O0oO == var1) {
                return null;
            }

            if (null == var1) {
                Class var3 = Class.forName(var2);
                Method[] var4 = var3.getMethods();
                int var5 = var4.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Method var7 = var4[var6];
                    DataScope var8 = (DataScope)var7.getAnnotation(DataScope.class);
                    if (null != var8) {
                        O000O0oo.put(var2 + "." + var7.getName(), new DataScopeProperty(var8));
                    }
                }

                DataScope var9 = (DataScope)var3.getAnnotation(DataScope.class);
                O000O0oo.put(var2, null == var9 ? O000O0oO : new DataScopeProperty(var9));
                var1 = (DataScopeProperty)O000O0oo.get(var0);
                if (null == var1) {
                    var1 = (DataScopeProperty)O000O0oo.get(var2);
                }
            }
        }

        return var1;
    }

    public static void processStatements(Object[] var0, MappedStatement var1, O00000o var2) {
        BoundSql var3 = var1.getBoundSql(var0[1]);
        String var4 = var3.getSql();
        if (log.isDebugEnabled()) {
            log.debug("original SQL: " + var4);
        }

        try {
            StringBuffer var5 = new StringBuffer();
            Statements var6 = CCJSqlParserUtil.parseStatements(var4);
            int var7 = 0;

            for(Iterator var8 = var6.getStatements().iterator(); var8.hasNext(); ++var7) {
                Statement var9 = (Statement)var8.next();
                if (var7 > 0) {
                    var5.append(";");
                }

                var2.process(var9, var7);
                var5.append(var9);
            }

            var0[0] = O0000ooO.O000000o(var1, new BoundSqlConfig(var1.getConfiguration(), var5.toString(), var3));
            if (log.isDebugEnabled()) {
                log.debug("dataScope execute sql: {}", var5);
            }

        } catch (JSQLParserException var10) {
            throw ExceptionUtils.mpe("Failed to process, Error SQL: %s", var10.getCause(), new Object[]{var4});
        }
    }
}