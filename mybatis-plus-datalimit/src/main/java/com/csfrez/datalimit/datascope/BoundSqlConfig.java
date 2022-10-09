package com.csfrez.datalimit.datascope;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;

import java.util.Map;

public class BoundSqlConfig {
    private final MetaObject metaObject;
    private final BoundSql boundSql;

    public BoundSqlConfig(Configuration configuration, String sql, BoundSql boundSql) {
        this.metaObject = SystemMetaObject.forObject(boundSql);
        this.boundSql = new BoundSql(configuration, sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
    }

    public BoundSql getBoundSql(){
        return this.boundSql;
    }
    /*public BoundSql O000Oo0O() {
        return this.boundSql;
    }*/

    public Map<String, Object> additionalParameters(){
        return this.getMetaObjectValue("additionalParameters");
    }
    /*public Map<String, Object> O000Oo0o() {
        return (Map)this.O000O00o("additionalParameters");
    }*/

    private <T> T getMetaObjectValue(String name){
        return (T) this.metaObject.getValue(name);
    }

    /*private <T> T O000O00o(String var1) {
        return (T) this.metaObject.getValue(var1);
    }*/
}