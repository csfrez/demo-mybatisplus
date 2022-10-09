package com.csfrez.datalimit.datascope;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Iterator;
import java.util.Map;

public class O000000o implements SqlSource {
    private BoundSql boundSql;

    public O000000o(BoundSqlConfig var1) {
        this.boundSql = var1.O000Oo0O();
        Map var2 = var1.O000Oo0o();
        if (null != var2) {
            Iterator var3 = var2.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry var4 = (Map.Entry)var3.next();
                this.boundSql.setAdditionalParameter((String)var4.getKey(), var4.getValue());
            }
        }
    }

    public BoundSql getBoundSql(Object var1) {
        return this.boundSql;
    }
}
