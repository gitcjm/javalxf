package com.str.cache;

import org.springframework.stereotype.Component;

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
