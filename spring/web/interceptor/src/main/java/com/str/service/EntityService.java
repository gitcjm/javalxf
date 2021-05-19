package com.str.service;

import java.util.List;

/**
 * 有了Entity Service接口定义后，缓存定义就方便了
 * */
public interface EntityService<E> {
    E getEntityById(long id);
    List<E> getEntityList(int pageIndex, int pageSize);
}
