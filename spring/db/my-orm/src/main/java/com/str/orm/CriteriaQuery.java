package com.str.orm;

/**
 * Base criteria query.
 * CriteriaQuery 就是持有一个不可更改的Criteria（标准查询语句）
 * */
abstract class CriteriaQuery<T> {
    protected final Criteria<T> criteria;

    CriteriaQuery(Criteria<T> criteria) {
        this.criteria = criteria;
    }

    String sql() {
        return criteria.sql();
    }
}
