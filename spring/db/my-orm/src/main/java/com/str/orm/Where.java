package com.str.orm;

import java.util.ArrayList;
import java.util.List;

/**
 * select .. from ... WHERE ...
 * */
public final class Where<T> extends CriteriaQuery<T> {

    /**
     * @param clause Like "fieldName1 = ?, fieldName2 = ?".
     * @param params 与where条件匹配的值参数
     * */
    Where(Criteria<T> criteria, String clause, Object... params) {
        super(criteria);
        this.criteria.where = clause;
        this.criteria.whereParams = new ArrayList<>();
        for (Object param : params) {
            this.criteria.whereParams.add(param);
        }
    }

    public Limit<T> limit(int maxResults) {
        return limit(0, maxResults);
    }

    public Limit<T> limit(int offset, int maxResults) {
        return new Limit<>(this.criteria, offset, maxResults);
    }

    public OrderBy<T> orderBy(String orderBy) {
        return new OrderBy<>(this.criteria, orderBy);
    }

    // 为什么每个条件类中都有此方法？
    public List<T> list() {
        return this.criteria.list();
    }

    public T first() {
        return this.criteria.first();
    }

    public T unique() {
        return this.criteria.unique();
    }
}
