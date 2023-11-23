package com.csfrez.demo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Frez
 *
 */
public class RestResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public RestResult() {
        put("code", 0);
    }

    public static RestResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static RestResult error(String msg) {
        return error(500, msg);
    }

    public static RestResult error(int code, String msg) {
        RestResult r = new RestResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static RestResult ok(String msg) {
        RestResult r = new RestResult();
        r.put("msg", msg);
        return r;
    }

    public static RestResult ok(Map<String, Object> map) {
        RestResult r = new RestResult();
        r.putAll(map);
        return r;
    }

    public static RestResult ok() {
        return new RestResult();
    }

    public RestResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
