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

    @Override
    public void sqlRender(Object[] args, MappedStatement mappedStatement, SqlCommandType sqlCommandType) throws Exception {
        DataScopeProperty dataScopeProperty = DataScopeUtil.getDataScopeProperty(mappedStatement.getId());
        if (null != dataScopeProperty) {
            if (sqlCommandType == SqlCommandType.INSERT) {
                this.processInsert(args, mappedStatement, dataScopeProperty);
            } else if (sqlCommandType == SqlCommandType.UPDATE) {
                this.processUpdate(args, mappedStatement, dataScopeProperty);
            } else if (sqlCommandType == SqlCommandType.DELETE) {
                this.processDelete(args, mappedStatement, dataScopeProperty);
            } else if (sqlCommandType == SqlCommandType.SELECT) {
                this.processSelect(args, mappedStatement, dataScopeProperty);
            }
        }

    }

    public void processInsert(Object[] args, MappedStatement mappedStatement, DataScopeProperty dataScopeProperty) {
    }

    public void processUpdate(Object[] args, MappedStatement mappedStatement, DataScopeProperty dataScopeProperty) {
    }

    public void processDelete(Object[] args, MappedStatement mappedStatement, DataScopeProperty dataScopeProperty) {
    }

    public void processSelect(Object[] args, MappedStatement mappedStatement, DataScopeProperty dataScopeProperty) {
        this.processStatements(args, mappedStatement, (statement, index) -> {
            this.processSelect((Select) statement, index, args, dataScopeProperty);
        });
    }

    public void processSelect(Select select, int index, Object[] args, DataScopeProperty dataScopeProperty) {
        SelectBody selectBody = select.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            this.setWhere((PlainSelect) selectBody, args, dataScopeProperty);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) selectBody;
            List<SelectBody> selectBodyList = setOperationList.getSelects();
            selectBodyList.forEach((sb) -> {
                this.setWhere((PlainSelect) sb, args, dataScopeProperty);
            });
        }

    }

    public abstract void setWhere(PlainSelect plainSelect, Object[] args, DataScopeProperty dataScopeProperty);

    public void processStatements(Object[] args, MappedStatement mappedStatement, ProcessFunction processFunction) {
        DataScopeUtil.processStatements(args, mappedStatement, processFunction);
    }
}