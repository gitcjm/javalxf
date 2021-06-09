package com.str.cache;

/**
 * 还未实现
 * */
public class MemcachedCache<E> extends AbstractCache<E> {
    @Override
    protected E lookupCache(long id) {
        return null;
    }

    @Override
    protected void putIntoCache(long id, E e) {

    }

    @Override
    protected E readFromDB(long id) {
        return null;
    }
}
