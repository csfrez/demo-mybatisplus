package com.csfrez.datalimit.datascope;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.util.Map;

public class BoundSqlConfig {
    private final MetaObject metaObject;
    private final BoundSql boundSql;

    public BoundSqlConfig(Configuration var1, String var2, BoundSql var3) {
        this.metaObject = SystemMetaObject.forObject(var3);
        this.boundSql = new BoundSql(var1, var2, var3.getParameterMappings(), var3.getParameterObject());
    }

    public BoundSql O000Oo0O() {
        return this.boundSql;
    }

    public Map<String, Object> O000Oo0o() {
        return (Map)this.O000O00o("additionalParameters");
    }

    private <T> T O000O00o(String var1) {
        return (T) this.metaObject.getValue(var1);
    }
}