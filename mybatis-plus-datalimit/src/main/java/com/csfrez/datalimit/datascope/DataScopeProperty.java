package com.csfrez.datalimit.datascope;

import com.csfrez.datalimit.annotation.DataColumn;
import com.csfrez.datalimit.annotation.DataScope;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataScopeProperty {

    private String type;

    private List<DataColumnProperty> columns;

    public DataScopeProperty() {
    }

    public DataScopeProperty(DataScope dataScope) {
        this.type = dataScope.type();
        this.setColumns(dataScope.value());
    }

    public void setColumns(DataColumn[] dataColumns) {
        if (null != dataColumns && dataColumns.length > 0) {
            this.columns = (List) Arrays.stream(dataColumns).map((dataColumn) -> {
                return new DataColumnProperty(dataColumn.alias(), dataColumn.name());
            }).collect(Collectors.toList());
        }
    }

    public String getType() {
        return this.type;
    }

    public List<DataColumnProperty> getColumns() {
        return this.columns;
    }

    public void setType(String type) {
        this.type = type;
    }
}