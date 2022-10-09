package com.csfrez.datalimit.databind;

import java.util.Map;
import java.util.function.Function;

public interface IJsonBindStrategy {
    default Map<String, Object> handle(String type, Object obj) {
        return (Map)((Function)this.getStrategyFunctionMap().get(type)).apply(obj);
    }

    Map<String, Function<Object, Map<String, Object>>> getStrategyFunctionMap();
}