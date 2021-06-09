package com.str.cache;

import org.springframework.stereotype.Component;

/**
 * 采用模板方法模式，实现缓存抽象基类
 * 子类通过map或redis、memcached实现缓存
 * */
public abstract class AbstractCache<E> {

    public final E getEntity(long id) {
        E e = lookupCache(id);
        if (e == null) {
            e = readFromDB(id);
            putIntoCache(id, e);
        }
        return e;
    }

    protected abstract E lookupCache(long id);
    protected abstract void putIntoCache(long id, E e);
    protected abstract E readFromDB(long id);
}
