package com.str.designpatterns.templatemethod;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSetting {

    public String getSetting(String key) {
        // 先从缓存读取
        String value = lookupCache(key);
        // 缓存中没有，再从数据库中读取
        if (value == null) {
            value = readFromDatabase(key);
            putIntoCache(key, value);
            System.out.print("load from database:");
        } else {
            System.out.print("load from cache: ");
        }

        return value;
    }

    // 模拟key-value数据库
    private static Map<String, String> map = new HashMap<>();
    static {
        map.put("name", "cjm");
        map.put("gender", "male");
        map.put("age", "44");
        map.put("phone", "2081027");
    }

    // 从模拟key-value数据库中读取
    private  String readFromDatabase(String key) {
        return map.get(key);
    }

    protected abstract String lookupCache(String key);

    protected abstract void putIntoCache(String key, String value);
}

