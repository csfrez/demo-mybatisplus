package com.csfrez.datalimit.datascope;

import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AbstractDataScopeProvider implements IDataScopeProvider {
    private static final Logger log = LoggerFactory.getLogger(AbstractDataScopeProvider.class);

    public AbstractDataScopeProvider() {
    }

    public void sqlRender(Object[] var1, MappedStatement var2, SqlCommandType var3) throws Exception {
        DataScopeProperty var4 = O0000O0o.O0000oOO(var2.getId());
        if (null != var4) {
            if (var3 == SqlCommandType.INSERT) {
                this.processInsert(var1, var2, var4);
            } else if (var3 == SqlCommandType.UPDATE) {
                this.processUpdate(var1, var2, var4);
            } else if (var3 == SqlCommandType.DELETE) {
                this.processDelete(var1, var2, var4);
            } else if (var3 == SqlCommandType.SELECT) {
                this.processSelect(var1, var2, var4);
            }
        }

    }

    public void processInsert(Object[] var1, MappedStatement var2, DataScopeProperty var3) {
    }

    public void processUpdate(Object[] var1, MappedStatement var2, DataScopeProperty var3) {
    }

    public void processDelete(Object[] var1, MappedStatement var2, DataScopeProperty var3) {
    }

    public void processSelect(Object[] var1, MappedStatement var2, DataScopeProperty var3) {
        this.processStatements(var1, var2, (var3x, var4) -> {
            this.processSelect((Select) var3x, var4, var1, var3);
        });
    }

    public void processSelect(Select var1, int var2, Object[] var3, DataScopeProperty var4) {
        SelectBody var5 = var1.getSelectBody();
        if (var5 instanceof PlainSelect) {
            this.setWhere((PlainSelect) var5, var3, var4);
        } else if (var5 instanceof SetOperationList) {
            SetOperationList var6 = (SetOperationList) var5;
            List var7 = var6.getSelects();
            var7.forEach((var3x) -> {
                this.setWhere((PlainSelect) var3x, var3, var4);
            });
        }

    }

    public abstract void setWhere(PlainSelect var1, Object[] var2, DataScopeProperty var3);

    public void processStatements(Object[] var1, MappedStatement var2, O00000o var3) {
        O0000O0o.processStatements(var1, var2, var3);
    }
}