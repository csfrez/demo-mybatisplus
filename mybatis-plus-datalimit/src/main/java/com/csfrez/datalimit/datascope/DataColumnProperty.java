package com.csfrez.datalimit.datascope;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

public class DataColumnProperty {
    private String alias;
    private String name;

    public String getAliasDotName() {
        return StringUtils.isBlank(this.alias) ? this.name : this.alias + "." + this.name;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getName() {
        return this.name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataColumnProperty() {
    }

    public DataColumnProperty(String alias, String name) {
        this.alias = alias;
        this.name = name;
    }
}
