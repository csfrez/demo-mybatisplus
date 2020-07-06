package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.SysUserEntity;
import com.example.demo.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller公共组件
 *
 * @author Frez
 *
 */
abstract class AbstractController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 常量帮助类
     */
    @Resource
    protected Constant constant;

    /**
     * 获取当前登录管理员
     *
     * @return 管理员
     */
    protected SysUserEntity getAdmin() {
        return null;
    }

    /**
     * 获取当前登录管理员ID
     *
     * @return 管理员ID
     */
    protected Long getAdminId() {
        return 1L;
    }

    /**
     * 解析成一个数组(批量操作用)
     *
     * @param ja JSONArray
     * @return Long[]
     */
    protected Long[] toArrays(JSONArray ja) {
        Long[] objs = new Long[ja.size()];
        for (int i = 0; i < ja.size(); i++) {
            objs[i] = Long.valueOf(ja.get(i).toString());
        }
        return objs;
    }

    /**
     * 根据JSON字符串返回对应的Value
     *
     * @param search   要解析Json的字符串
     * @param keyNames 查询的Names
     * @return Map<String, T>
     */
    @SuppressWarnings("unchecked")
    protected <T> Map<String, T> parseObject(String search, String... keyNames) {
        JSONObject parseObject = JSONArray.parseObject(search);
        if (null != parseObject && null != keyNames) {
            Map<String, T> map = new HashMap<String, T>();
            for (String key : keyNames) {
                map.put(key, (T) parseObject.get(key));
            }
            return map;
        }
        return null;
    }
}
