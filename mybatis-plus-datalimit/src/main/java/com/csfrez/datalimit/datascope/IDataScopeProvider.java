package com.csfrez.datalimit.datascope;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

public interface IDataScopeProvider {

    void sqlRender(Object[] args, MappedStatement mappedStatement, SqlCommandType sqlCommandType) throws Exception;

}
