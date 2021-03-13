package com.str.orm;

import java.util.List;

public final class From<T> extends CriteriaQuery<T> {

    From(Criteria<T> criteria, Mapper<T> mapper) {
        super(criteria);
        this.criteria.mapper = mapper;
        this.criteria.clazz = mapper.entityClass;
        this.criteria.table = mapper.tableName;
    }

    /**
     * Add where clause.
     *
     * @param clause Like "name = ?, age = ?".
     * @param args Arguments ot match clause. eg. "晓燕", "38"
     * @return CriteriaQuery object.
     * */
    public Where<T> where(String clause, Object... args) {
        return new Where<>(this.criteria, clause, args);
    }

    /**
     * Add order by clause.
     *
     * @param orderBy Field of order by.
     * */
    public OrderBy<T> orderBy(String orderBy) {
        return new OrderBy<>(this.criteria, orderBy);
    }

    /**
     * Add limit clause.
     *
     * */
    public Limit<T> limit(int maxResults) {
        return limit(0, maxResults);
    }

    public Limit<T> limit(int offset, int maxResults) {
        return new Limit<>(this.criteria, offset, maxResults);
    }

    /**
     * Get all results as list.
     * */
    public List<T> list() {
        return this.criteria.list();
    }

    /**
     * Get first row of the query, or null if no result found.
     * */
    public T first() {
        return this.criteria.first();
    }

    /**
     * Get unique result of the query,
     * Exception will throw if no result found or more than 1 results found.
     *
     * @return T modelInstance
     * */
    public T unique() {
        return this.criteria.unique();
    }
}
