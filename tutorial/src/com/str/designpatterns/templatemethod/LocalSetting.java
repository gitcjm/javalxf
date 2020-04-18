package com.str.designpatterns.templatemethod;

import java.util.HashMap;
import java.util.Map;


public class LocalSetting extends AbstractSetting {
    private Map<String, String> map = new HashMap<>();

    @Override
    protected String lookupCache(String key) {
        return map.get(key);
    }

    @Override
    protected void putIntoCache(String key, String value) {
        map.put(key, value);
    }
}

