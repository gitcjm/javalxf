package com.str.designpatterns.templatemethod;

import redis.clients.jedis.Jedis;

public class RedisSetting extends AbstractSetting {

    private Jedis jedis = new Jedis("localhost", 6379);

    @Override
    protected String lookupCache(String key) {
        return jedis.get(key);
    }

    @Override
    protected void putIntoCache(String key, String value) {
        jedis.set(key, value);
    }
}
