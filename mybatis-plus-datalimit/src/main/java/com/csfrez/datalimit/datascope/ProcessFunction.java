package com.csfrez.datalimit.datascope;

import net.sf.jsqlparser.statement.Statement;

@FunctionalInterface
public interface ProcessFunction {

    void process(Statement statement, int index);

}